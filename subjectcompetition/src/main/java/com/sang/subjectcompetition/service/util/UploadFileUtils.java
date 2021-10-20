package com.sang.subjectcompetition.service.util;

import com.sang.subjectcompetition.entity.WorkFile;
import com.sang.subjectcompetition.service.WorkFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.geom.AreaOp;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Component
@ConfigurationProperties(prefix ="file-upload")
public class UploadFileUtils {

    @Autowired
    private WorkFileService workFileService;

    @Value("${file-upload.image.winUrl}")
    private String winUrl;

    @Value("${file-upload.image.linuxUrl}")
    private String linuxUrl;

    private static String OS = System.getProperty("os.name").toLowerCase();

    public  String datePath(){
        Date date = new Date();
        Calendar calendar=Calendar.getInstance();
        int DATE = calendar.get(Calendar.DATE);
        int MONTH = calendar.get(Calendar.MONTH) + 1;
        int YEAR = calendar.get(Calendar.YEAR);
        return YEAR+ "/" +MONTH+ "/" +DATE+"/";

    }

    public  String uploadFile(MultipartFile image,String infoPath) throws FileNotFoundException {
        String originalFilename = image.getOriginalFilename();
        String type = originalFilename.substring(originalFilename.lastIndexOf("."));
        String beanPath;
        if(OS.startsWith("win")){
            beanPath=winUrl;
        }else {
            beanPath=linuxUrl;
        }
        String filePath="/upload/awards"+infoPath+datePath();
        String fileName= UUID.randomUUID().toString().replace("-","_")+type;
        File file=new File(beanPath+filePath+fileName);
        mkdir(file.getPath());
        try {
            image.transferTo(file);
            return filePath+fileName;
        }catch (Exception e){
            return "上传失败";
        }
    }

    public  String uploadWorkFile(MultipartFile image,String infoPath) throws FileNotFoundException {
        String originalFilename = image.getOriginalFilename();
        String beanPath;
        if(OS.startsWith("win")){
            beanPath=winUrl;
        }else {
            beanPath=linuxUrl;
        }
        String filePath="/upload/work"+infoPath+datePath();
        String fileName= originalFilename;
        File file=new File(beanPath+filePath+fileName);
        mkdir(file.getPath());
        try {
            image.transferTo(file);
            return filePath+fileName;
        }catch (Exception e){
            return "上传失败";
        }
    }

    public List<WorkFile> uploadFile(MultipartFile[] uploadFiles, String infoPath){
        String beanPath;
        if(OS.startsWith("win")){
            beanPath=winUrl;
        }else {
            beanPath=linuxUrl;
        }
        List<WorkFile> workFiles=new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String originalFilename = uploadFile.getOriginalFilename();
            String filePath="/upload/work"+infoPath+datePath();
            File file=new File(beanPath+filePath+originalFilename);
            mkdir(file.getPath());
            try {
                uploadFile.transferTo(file);
                WorkFile workFile=new WorkFile();
                workFile.setFileUrl(filePath+originalFilename);
                workFile.setFileName(uploadFile.getOriginalFilename());
                workFiles.add(workFile);
                workFileService.save(workFile);
            }catch (Exception e){
                return null;
            }
        }
        return workFiles;
    }

    public  String convertBase64ToFile(String fileBase64String, String info) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        String projectBeanPath="src"+"/"+"main"+"/"+"resources"+"/"+"static";
        String filePath="/"+"upload"+"/"+info+datePath()+ UUID.randomUUID()+".jpg";
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bfile = decoder.decodeBuffer(fileBase64String);
            String fullPath=projectBeanPath+filePath;
            mkdir(fullPath);
            file = new File(fullPath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    //awardsInfo包含信息
    public  String base64StrToFile(String base64Str,String awardsInfo) throws IOException {
        String fileName=UUID.randomUUID().toString()+".jpg";
        String projectBeanPath="src/main/resources/static";
        String resultPath="/upload/"+awardsInfo+datePath();
        String parentPath=projectBeanPath+resultPath;

        BASE64Decoder decoder = new BASE64Decoder();
        //前台在用Ajax传base64值的时候会把base64中的+换成空格，所以需要替换回来。
        String baseValue = base64Str.replaceAll(" ", "+");
        byte[] b = decoder.decodeBuffer( baseValue.replace("data:image/png;base64,", ""));
        for (int i = 0; i < b.length; ++i) {
            // 调整异常数据
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        String imgPath = parentPath + fileName;
        mkdir(imgPath);
        OutputStream out = new FileOutputStream(imgPath);
        //写入数据
        out.write(b);
        out.flush();
        out.close();
        return resultPath+fileName;
        /*mkdir(parentPath);
        File file = new File(parentPath);
        FileOutputStream out = null;
        try {
            byte[] bytes1 = new BASE64Decoder().decodeBuffer(base64Str);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes1);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int byteSum = 0;
            int byteRead = 0;
            while ((byteRead = in.read(buffer)) != -1){
                byteSum += byteRead;
                out.write(buffer,0,byteRead);
            }
        }catch (Exception ex){
            throw new RuntimeException("transform base64 String into file 出错",ex);
        }finally {
            try {
                if(null != out){
                    out.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
       if(file!=null) return resultPath;
       else return null;*/
    }
    public  String base64ToImg(String imgStr, String path) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(imgStr);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        String info=path;
        String projectBeanPath="src"+"/"+"main"+"/"+"resources"+"/"+"static";
        path=projectBeanPath+"/"+"upload"+"/"+info+datePath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String fileName=UUID.randomUUID() + ".png";
        String imgPath = path + fileName;
        OutputStream out = new FileOutputStream(imgPath);
        //写入数据
        out.write(b);
        out.flush();
        out.close();
        return "/"+"upload"+"/"+info+datePath()+fileName + ".png";
    }
    public  void mkdir(String path) {
        File fd = new File(path).getParentFile();
        try {
            if (!fd.exists()) {
                fd.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fd = null;
        }
    }

    public  String getFileName(HttpServletRequest request,String fileName) throws UnsupportedEncodingException {
        String[] IEBrowserKeyWords={"MSIE","Trident","Edge"};
        String userAgent=request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if(userAgent.contains(keyWord)){
                return URLEncoder.encode(fileName,"UTF-8").replace("+"," ");
            }
        }
        return new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
    }
}
