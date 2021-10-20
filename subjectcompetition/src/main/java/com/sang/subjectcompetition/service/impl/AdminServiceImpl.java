package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.Admin;
import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Comp;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.AdminRepository;
import com.sang.subjectcompetition.service.AdminService;
import com.sang.subjectcompetition.service.CollegeService;
import com.sang.subjectcompetition.service.College_CompService;
import com.sang.subjectcompetition.service.CompService;
import com.sang.subjectcompetition.service.util.ColorUtils;
import com.sang.subjectcompetition.service.util.UUIDUtil;
import com.sang.subjectcompetition.service.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CompService compService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private College_CompService college_compService;


    //基本操作----------------------------------------------------------------------------

    //注册管理员账号
    @Override
    public UserInfo save(Admin admin){
        UserInfo userInfo=new UserInfo();
        Admin admin_save = adminRepository.save(admin);
        if(admin.getTargetId()==null)admin_save.setTargetId(UUID.randomUUID().toString());
        if (admin_save!=null){
            userInfo.setCode(201);
            userInfo.setMessage("注册成功！");
            userInfo.setData(admin_save);
        }else {
            userInfo.setCode(401);
            userInfo.setMessage("注册失败！");
        }
        return userInfo;
    }

    @Override
    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin getAdminById(Integer id) {
        Admin admin = adminRepository.findById(id).orElse(null);
        return admin;
    }

    //业务逻辑层往下------------------------------------------------------------------------

    /**
     * 展示全部学院全部信息
     * @return
     */
    @Override
    public List<College> getAllColleges() {
        List<College> collegeList = collegeService.findAll();
        return collegeList;
    }

    /**
     * 创建院系账户
     * @param college
     * @return
     */
    @Override
    public UserInfo createCollege(College college) {
        UserInfo userInfo=new UserInfo();
        college.setUsername(UUIDUtil.getUUIDInOrderId().toString());
        college.setTargetId(UUID.randomUUID().toString());
        UserInfo college_save = collegeService.save(college);
        if (college_save.getCode()==200){
            userInfo.setCode(200);
            userInfo.setMessage("创建成功！");
            userInfo.setData(college_save.getData());
            return userInfo;
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("创建失败！");
            return userInfo;
        }
    }

    /**
     * 管理员信息修改
     * @param admin
     * @return
     */
    @Override
    public UserInfo updateAdmin(Admin admin) {
        Admin admin_db=adminRepository.getOne(admin.getId());
        if (admin_db!=null){
            UpdateUtil.copyNullProperties(admin,admin_db);
        }
        UserInfo userInfo=new UserInfo();
        UserInfo save = this.save(admin_db);
        if (save.getData()!=null){
            userInfo.setCode(200);
            userInfo.setMessage("信息更改成功！");
            userInfo.setData(save.getData());
            return userInfo;
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("信息更改失败！");
            return userInfo;
        }
    }

    /**
     * 得到未处理的申请  申请和未申请的apply
     * @return
     */
    @Override
    public List<College_Comp> getUndoCompApply() {
        return college_compService.getUndoApplyComp();
    }




}
