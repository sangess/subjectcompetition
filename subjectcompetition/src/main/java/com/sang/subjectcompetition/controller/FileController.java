package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.WorkFile;
import com.sang.subjectcompetition.service.WorkFileService;
import com.sang.subjectcompetition.service.util.UploadFileUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file-upload.image.winUrl}")
    private String winUrl;

    @Value("${file-upload.image.linuxUrl}")
    private String linuxUrl;

    @Autowired
    private WorkFileService workFileService;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    private static String OS = System.getProperty("os.name").toLowerCase();


    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId, HttpServletRequest request) throws UnsupportedEncodingException {
        String rootBean;
        if(OS.startsWith("win")){
            rootBean=winUrl;
        }else {
            rootBean=linuxUrl;
        }
        WorkFile workFile = workFileService.findWorkFileById(fileId);
        String fileName=workFile.getFileName();
        String fileUrl=workFile.getFileUrl();
        File file=new File(rootBean+fileUrl);
        HttpHeaders httpHeaders=new HttpHeaders();
        fileName=uploadFileUtils.getFileName(request,fileName);
        httpHeaders.setContentDispositionFormData("attachment",fileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(e.getMessage().getBytes(),HttpStatus.EXPECTATION_FAILED);
        }
    }


}
