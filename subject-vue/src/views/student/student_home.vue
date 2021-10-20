<template>
  <div id="teacher_home">
    <el-card>
      <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <!-- ============================个人信息管理====================================== -->
          <el-tab-pane label="我的竞赛" name="myComp">
            <!--我的竞赛模块
              前端：参加竞赛数目
                    竞赛列表（table）
                    显示项目组成员（模块框）
                    退出项目按钮
              后端：根据studentId在student_project表中查询个数
                    *bug:一个学生只能参加一个竞赛
                    List<Project> myComp的数据结构
                    *根据竞赛id和学生id查看project
            -->
            <el-table :data="myProjects">
              <el-table-column align="center" label="竞赛编号" >
                <template slot-scope="scope">
                  {{scope.row.comp.id }}
                </template>
              </el-table-column>
              <el-table-column align="center" label="竞赛名称">
                <template slot-scope="scope">
                  {{scope.row.comp.compName}}
                </template>
              </el-table-column>
              <el-table-column align="center" label="竞赛等级">
                <template slot-scope="scope">
                  <p v-if="scope.row.comp.level==0">国家级</p>
                  <p v-if="scope.row.comp.level==1">省级</p>
                  <p v-if="scope.row.comp.level==2">校级</p>
                </template>
              </el-table-column>
              <el-table-column align="center" label="竞赛时间">
                <template slot-scope="scope">
                  {{scope.row.comp.occurrenceTime|dataTime}}
                </template>
              </el-table-column>
              <el-table-column align="center" label="带队老师" prop="createByTeacher.nickname" ></el-table-column>
              <el-table-column align="center" label="竞赛状态">
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
              <el-table-column align="center" label="项目信息">
                <template slot-scope="scope">
                  <el-link :underline="false" @click="checkMyTeam(scope.row)"><i class="el-icon-view el-icon--right">查看</i> </el-link>
                </template>
              </el-table-column>
              <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                  <el-popconfirm title="确定退出项目？" @confirm="exitProject(scope.row.id)">
                    <el-button  slot="reference" type="danger" size="small">退出项目组</el-button>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>

            <!--参赛人员列表-->
            <el-dialog title="项目成员" :visible.sync="projectPersonInfo">
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
                      <el-tag type="Primary ">指导老师</el-tag>
                    </span>
                  </template>
                </el-table-column>
              </el-table>
              <el-table :data="projectJoinStudent">
                <el-table-column property="nickname" label="学生姓名"></el-table-column>
                <el-table-column property="username" label="学号"></el-table-column>
                <el-table-column property="college.nickname" label="所属院系"></el-table-column>
                <el-table-column label="身份"><el-tag type="Primary ">参赛学员</el-tag></el-table-column>
              </el-table>
            </el-dialog>
          </el-tab-pane>
          <el-tab-pane label="个人信息管理" name="userInfo">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>姓名：{{this.$store.state.nickname}}</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="dialogFormVisible = true">修改资料</el-button>
              </div>
              <div class="text item">
                <p>账号名：{{this.$store.state.username}}</p>
                <p>姓名：{{this.$store.state.nickname}}</p>
                <p>账户类型：{{this.$store.state.token=='admin'?'学校管理员':this.$store.state.token=='college'?'学院':this.$store.state.token=='teacher'?'老师':'学生'}}</p>
              </div>
            </el-card>
            <!-- 修改信息模态框 -->
            <el-dialog title="个人信息修改"  :visible.sync="dialogFormVisible" :before-close="handleClose" :width="dialogWidth">
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
        </el-tabs>
      </div>
    </el-card>
  </div>
</template>
<script>
  import { dateFormat, exportExcel } from "../../assets/js/utils.js";
  import api from '../../main.js'
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
        userData:{},
        checkUser:{
          nickname:"",
          password:'',
          rePassword:''
        },
        myProjects:[],
        showTable: true,
        createProjectTeacherId:"",
        dialogFormVisible:false,
        projectPersonInfo:false,
        projectJoinTeacher:[],
        projectJoinStudent:[],
        activeName: "myComp",
        labelPosition: 'right',
        dialogWidth: '700px',
        labelWidth:'120px',
        rules: {
          /*修改学院的密码验证*/
          password: [
            {validator: validatePass, trigger: 'blur'}
          ],
          /*修改学院的密码验证*/
          rePassword: [
            {validator: validatePass2, trigger: 'blur'}
          ],
          /*修改学院的名称验证*/
          nickname: [
            {required: true, message: '昵称（姓名）不可为空', trigger: 'blur'}
          ],
        }
      };

    },
    mounted() {
      this.showMyComps();
      this.getUserData();
    },
    methods: {
      handleClick(tab, event) {
      },
      /**
       * 得到用户信息
       * @param done
       */
      getUserData() {
        let that = this;
        var studentId= that.$store.state.id;
        this.$axios
        .get(api+`/student/getStudentInfoById/`+studentId)
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
       * 提交表单修改学生信息
       */
      submitUserInto(){
        let that = this;
        let user = new FormData();
        user.append("nickname",that.checkUser.nickname);
        user.append("id",that.userData.id);
        user.append("password",that.checkUser.password);
        that.$axios
        .post(api+"/student/updateStudent",user)
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
          that.reload();
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
      /**
       * 查看团队成员
       * @param projectId
       */
      checkMyTeam(project){
        let projectId=project.id;
        this.createProjectTeacherId=project.createByTeacher.id;
        this.projectPersonInfo=true;
        this.getProjectJoinTeacher(projectId);
        this.getProjectJoinStudent(projectId);
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
       * 显示学生所有的项目
       */
      showMyComps(){
        let that=this;
        let studentId=that.$store.state.id;
        that.$axios
          .get(api+`/project/getStudentProjects/`+studentId)
          .then(function (response) {
            that.myProjects=response.data;
          })
      },
      exitProject(projectId){
        let that=this;
        let studentId=that.$store.state.id;
        that.$axios
          .get(api+`/student/exitProject/`+studentId+`/`+projectId)
          .then(function (response) {
            that.$message({
              message:response.data.message,
              type:response.data.code==200?'success':'error'
            });
            that.reload();
          })
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