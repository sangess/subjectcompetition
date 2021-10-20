package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Admin;
import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Comp;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;

import java.util.List;

public interface AdminService {

    UserInfo save(Admin admin);

    void deleteById(Integer id);

    Admin getAdminById(Integer id);

    //业务逻辑--------------------------------------------------------

    /**
     * 得到全部学院信息
     * @return
     */
    List<College> getAllColleges();

    /**
     * 创建新的院系
     * @param college
     * @return
     */
    UserInfo createCollege(College college);

    /**
     * 信息修改
     */
    UserInfo updateAdmin(Admin admin);

    /**
     * 得到未处理的申请  申请和重新申请
     * @return
     */
    List<College_Comp> getUndoCompApply();

}
