<template>
  <div id="teacher_home">
    <el-card>
      <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <!--=============================管理的比赛项目======================================-->
          <el-tab-pane label="我的比赛项目" name="myComps">
            <el-button type="text" style="float:right;" @click="导出为Excel('我的全部竞赛项目')">导出为Excel</el-button>
            <el-table id="我的全部竞赛项目"
                      stripe
                      border
                      :data="myComps.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                      :default-sort = "{prop: 'level', order: 'descending'}"
            >
              <el-table-column align="center" prop="compId" label="竞赛编号" sortable >
                <template slot-scope="scope">
                  {{scope.row.comp.id }}
                </template>
              </el-table-column>
              <el-table-column align="center" prop="compName" label="竞赛名称" sortable>
                <template slot-scope="scope">
                  {{scope.row.comp.compName}}
                </template>
              </el-table-column>
              <el-table-column align="center"  prop="level" label="竞赛等级" sortable>
                <template slot-scope="scope">
                  <p v-if="scope.row.comp.level==0">国家级</p>
                  <p v-if="scope.row.comp.level==1">省级</p>
                  <p v-if="scope.row.comp.level==2">校级</p>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="compTime" label="竞赛时间" sortable>
                <template slot-scope="scope">
                  {{scope.row.comp.occurrenceTime|dataTime}}
                </template>
              </el-table-column>
              <el-table-column align="center" prop="compState" label="竞赛状态" sortable>
                <template slot-scope="scope">
                  <el-tag  effect="dark"
                           :type="scope.row.comp.compState === 0 ? 'info' :       /*规划中*/
                                (scope.row.comp.compState === 1 ? 'primary':       /*可报名*/
                                (scope.row.comp.compState === 2 ? 'success':       /*备赛中*/
                                (scope.row.comp.compState === 3 ? 'warning':       /*比赛中*/
              'danger')))"
                           disable-transitions
                  >
                    <span v-if="scope.row.comp.compState==0">规划中</span>
                    <span v-if="scope.row.comp.compState==1">报名中</span>
                    <span v-if="scope.row.comp.compState==2">备赛中</span>
                    <span v-if="scope.row.comp.compState==3">比赛中</span>
                    <span v-if="scope.row.comp.compState==4">已结束</span>
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column align="center" label="我的团队">
                <template slot-scope="scope">
                  <el-link :underline="false" @click="checkMyTeam(scope.row)"><i class="el-icon-view el-icon--right">查看</i> </el-link>
                </template>
              </el-table-column>
              <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                  <el-popconfirm title="解散项目组？" @confirm="invokeProject(scope.row.id)">
                    <el-button  v-if="scope.row.createByTeacher.id==teacherId" slot="reference" type="danger" size="small">解散项目组</el-button>
                  </el-popconfirm>
                  <el-popconfirm title="退出项目组？" @confirm="exitProject(scope.row.id)">
                    <el-button  v-if="scope.row.createByTeacher.id!=teacherId" slot="reference" type="danger" size="small">退出项目组</el-button>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>
            <div id="fenye">
              <!-- 设置layout，表示需要显示的内容，用逗号分隔，布局元素会依次显示。
                prev表示上一页，next为下一页，pager表示页码列表，
                除此以外还提供了jumper和total，size和特殊的布局符号->，->后的元素会靠右显示，
                jumper表示跳页元素，
                total表示总条目数，
                page-size  每页多少条数据
                @current-change="current_change" //currentPage改变时会触发
              size用于设置每页显示的页码数量。-->
              <el-pagination
                      background
                      layout="prev, pager, next"
                      :total="myComps.length"
                      :page-size="8"
                      @current-change="current_change"
                      :current-page.sync="currentPage"
              ></el-pagination>
            </div>
          </el-tab-pane>
          <!-- ============================个人信息管理====================================== -->
          <el-tab-pane label="教师信息管理" name="userInfo">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>教师：{{this.$store.state.nickname}}</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="dialogFormVisible = true">修改资料</el-button>
              </div>
              <div class="text item">
                <p>账号名：{{this.$store.state.username}}</p>
                <p>姓名：{{this.$store.state.nickname}}</p>
                <p>账户类型：{{this.$store.state.token=='admin'?'学校管理员':this.$store.state.token=='college'?'学院':this.$store.state.token=='teacher'?'老师':'学生'}}</p>
              </div>
            </el-card>
            <!-- 修改信息模态框 -->
            <el-dialog title="院系信息修改"  :visible.sync="dialogFormVisible" :before-close="handleClose" :width="dialogWidth">
              <el-form :model="checkUser"  status-icon ref="checkUser" :rules="rules" :label-position="labelPosition" :label-width="labelWidth">
                <el-form-item label="昵称（姓名）" prop="nickname">
                  <el-input  v-model="checkUser.nickname" autocomplete="off" ></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                  <el-input type="password" v-model="checkUser.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="重新输入密码" prop="rePassword">
                  <el-input type="password" v-model="checkUser.rePassword" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitChange('checkUser')">确定修改</el-button>
              </div>
            </el-dialog>
          </el-tab-pane>
          <!--参赛人员列表-->
          <el-dialog title="项目成员" :visible.sync="projectPersonInfo" width="70%">
            <el-table :data="projectJoinTeacher">
              <el-table-column property="nickname" label="教师姓名"></el-table-column>
              <el-table-column property="username" label="用户名"></el-table-column>
              <el-table-column property="college.nickname" label="所属院系"></el-table-column>
              <el-table-column label="身份">
                <template slot-scope="scope">
                    <span v-if="scope.row.id==createProjectTeacherId">
                      <el-tag type="success">带队老师</el-tag>
                    </span>
                  <span v-if="scope.row.id!=createProjectTeacherId">
                    <el-tag type="Primary">指导老师</el-tag>
                  </span>
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope" >
                  <el-popconfirm title="退出项目组？" @confirm="exitProject(projectId)">
                    <el-button  v-if="scope.row.id==teacherId && scope.row.id!=createProjectTeacherId" slot="reference" type="danger" size="small">退出项目组</el-button>
                  </el-popconfirm>
                  <el-popconfirm title="解散项目组？" @confirm="invokeProject(projectId)">
                    <el-button  v-if="scope.row.id==teacherId && scope.row.id==createProjectTeacherId" slot="reference" type="danger" size="small">解散项目组</el-button>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>
            <el-table :data="projectJoinStudent">
              <el-table-column property="nickname" label="学生姓名"></el-table-column>
              <el-table-column property="username" label="学号"></el-table-column>
              <el-table-column property="college.nickname" label="所属院系"></el-table-column>
              <el-table-column label="身份">
                <el-tag type="Primary">参赛学员</el-tag>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-popconfirm title="是否将学生移出项目组？" @confirm="exitStudent(scope.row.id)">
                    <el-button  v-if="teacherId==createProjectTeacherId" slot="reference" type="danger" size="small">移出</el-button>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>
          </el-dialog>
        </el-tabs>
      </div>
    </el-card>
  </div>
</template>
<style>
  .clearfix {
    font-size: 26px;
  }
  #fenye {
    text-align: center;
    margin-top: 20px;
  }
</style>
<script>
  import api from '../../main.js'
  import { dateFormat, exportExcel } from "../../assets/js/utils.js";
  export default {
    inject:['reload'],
    name: "teacher_home",
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.checkUser.rePassword !== '') {
            this.$refs.checkUser.validateField('rePassword');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.checkUser.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        teacherId:"",
        projectId:"",
        projectJoinTeacher:[],
        projectJoinStudent:[],
        projectPersonInfo:false,
        pagesize: 8,
        currentPage: 1,
        userData:{},
        myComps:[],
        createProjectTeacherId:"",
        myJoinComps:[],
        checkUser:{
          nickname:"",
          password:'',
          rePassword:''
        },
        dialogFormVisible:false,
        activeName: "myComps",
        labelPosition: 'right',
        dialogWidth: '700px',
        labelWidth:'120px',
        rules:{
          /*修改学院的密码验证*/
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          /*修改学院的密码验证*/
          rePassword: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          /*修改学院的名称验证*/
          nickname:[
            { required: true, message: '昵称（姓名）不可为空', trigger: 'blur' }
          ]
        }
      };

    },
    mounted() {
      this.teacherId=this.$store.state.id;
      this.showMyComps();
      this.getUserData();
    },
    methods: {
      handleClick(tab, event) {
      },
      checkMyTeam(project){
        let that=this;
        let projectId=project.id;
        that.projectId=projectId;
        this.createProjectTeacherId=project.createByTeacher.id;
        this.projectPersonInfo=true;
        this.getProjectJoinTeacher(projectId);
        this.getProjectJoinStudent(projectId);
      },
      /**
       * 显示学生所有的项目
       */
      showMyComps(){
        let that=this;
        let teacherId=that.$store.state.id;
        that.$axios
        .get(api+`/project/getTeacherJoinProjects/`+teacherId)
        .then(function (response) {
          that.myComps=response.data;
        })
      },
      current_change(currentPage) {
        //改变当前页
        this.currentPage = currentPage;
      },
      /**
       * 得到参赛的老师
       * @param projectId
       */
      getProjectJoinTeacher(projectId){
        let that=this;
        that.$axios
        .get(api+`/project/getProjectJoinTeacher/`+projectId)
        .then(function (response) {
          that.projectJoinTeacher=response.data;
        })
      },
      /**
       * 得到参赛的学生
       * @param projectId
       */
      getProjectJoinStudent(projectId){
        let that=this;
        that.$axios
        .get(api+`/project/getProjectJoinStudent/`+projectId)
        .then(function (response) {
          that.projectJoinStudent=response.data;
        })
      },
      /**
       * 解散项目组
       */
      invokeProject(projectId){
        let that=this;
        that.$axios
          .get(api+`/project/invokeProject/`+projectId)
          .then(function (response) {
            that.$message({
              message:response.data.message,
              type:response.data.code==200?'success':'error'
            });
            that.reload();
          })
      },
      /**
       * 得到用户信息
       * @param done
       */
      getUserData() {
        let that = this;
        var teacherId= that.$store.state.id;
        this.$axios
        .get(api+`/teacher/getUserTeacherById/`+teacherId)
        .then(function(response) {
          that.userData = response.data;
          //将token身份加入到userData中
          that.$set(that.userData,"token",that.$store.state.token);
          that.$set(that.userData,"password","");
          that.checkUser.nickname = that.userData.nickname;
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
        });
      },
      /**
       * 关闭弹出框
       * @param done
       */
      handleClose(done) {
        this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
      },
      /**
       * 提交表单修改学院信息
       */
      submitUserInto(){
        let that = this;
        let user = new FormData();
        user.append("nickname",that.checkUser.nickname);
        user.append("id",that.userData.id);
        user.append("password",that.checkUser.password);
        that.$axios
        .post(api+"/teacher/updateTeacher",user)
        .then(function (response) {
          that.dialogFormVisible=false;
          that.checkUser.password="";
          that.checkUser.rePassword="";
          that.$store.commit("setNickname", response.data.data.nickname);
          that.userData.nickname=response.data.data.nickname;

          that.$message({
            message: response.data.message,
            type:response.data.code==200?'success':'warning'
          });
        })
      },
      /**
       * 先验证触发修改学院信息的按钮事件
       */
      submitChange(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.submitUserInto();
            this.reload();
          } else {
            this.$message({
              message: '提交失败！',
              type: 'warning'
            });
            return false;
          }
        });
      },
      /**
       * 重置表单
       * @param formName
       */
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      exitTeacher(teacherId){
        let that=this;
        that.$axios
          .get(api+`/teacher/exitTeacher/`+teacherId+`/`+that.projectId)
        .then(function (response) {
          that.$message({
            message:response.data.message,
            type:response.data.code==200?'success':'error'
          });
          that.reload();
        })
      },
      exitStudent(studentId){
        let that=this;
        that.$axios
        .get(api+`/teacher/exitStudent/`+studentId+`/`+that.projectId)
        .then(function (response) {
          that.$message({
            message:response.data.message,
            type:response.data.code==200?'success':'error'
          });
          that.reload();
        })
      },
      /**
       * 退出项目组
       * @param projectId
       */
      exitProject(projectId){
        let that=this;
        that.$axios
        .get(api+`/teacher/exitProject/`+that.teacherId+`/`+projectId)
        .then(function (response) {
          that.$message({
            message:response.data.message,
            type:response.data.code==200?'success':'error'
          });
          that.reload();
        })
      },
      导出为Excel(domname) {
        let excelname =  domname;
        exportExcel(domname, excelname);
      }
    },
    filters:{
      dataTime: function(value) {
        if (value==undefined)
          return '-';
        value = new Date(value)
        if (value instanceof Date) {
          var d = value;
          var year=d.getFullYear();
          var m = d.getMonth() + 1;
          var day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate();
          var myDate = year + '年'+m + '月 ' + day +'日';
          return myDate;
        } else {
          return "-";
        }
      }
    }
  };
</script>
<style>
  .text {
    font-size: 20px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>