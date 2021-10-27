package com.sang.subjectcompetition.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sang.subjectcompetition.common.WechatUtil;
import com.sang.subjectcompetition.entity.Student;
import com.sang.subjectcompetition.entity.resultInfo.WxResult;
import com.sang.subjectcompetition.service.StudentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/onLogin")
    @ResponseBody
    public WxResult onLogin(@RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "rawData", required = false) String rawData,
                            @RequestParam(value = "encrypteData", required = false) String encrypteData,
                            @RequestParam(value = "iv", required = false) String iv) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code

        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        System.out.println(openid);
        String sessionKey = SessionKeyOpenId.getString("session_key");
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
      /*  User user = this.userMapper.selectById(openid);*/
        Student student = studentService.findStudentByOppenId(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (student!=null){//登录过
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");

            student.setWxName(nickName);
            student.setAvatarUrl(avatarUrl);
            student.setGender(Integer.parseInt(gender));
            student.setSessionKey(sessionKey);
            student.setSkey(skey);
            studentService.updateStudent(student);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("skey",skey);
            jsonObject.put("studentId",student.getId());
            WxResult result = WxResult.build(200, "查询到数据", jsonObject);
            return result;
        }else{
            WxResult result = WxResult.build(400, "还未绑定账号", null);
            return result;
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public WxResult register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("code") String code,
                             @RequestParam(value = "rawData", required = false) String rawData){
        Student student = studentService.findStudentByUsernameAndPassword(username,password);
        if(student!=null){
            JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
            String sessionKey = SessionKeyOpenId.getString("session_key");
            String openid = SessionKeyOpenId.getString("openid");
            JSONObject rawDataJson = JSON.parseObject(rawData);
            String skey = UUID.randomUUID().toString();
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");

            student.setOpenId(openid);
            student.setWxName(nickName);
            student.setAvatarUrl(avatarUrl);
            student.setGender(Integer.parseInt(gender));
            student.setSessionKey(sessionKey);
            student.setSkey(skey);
            studentService.updateStudent(student);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("skey",skey);
            jsonObject.put("studentId",student.getId());
            WxResult result = WxResult.build(200, "绑定成功！", jsonObject);
            return result;
        }else {
            WxResult result = WxResult.build(400, "用户名或者密码错误，无法绑定", null);
            return result;
        }
    }
}
