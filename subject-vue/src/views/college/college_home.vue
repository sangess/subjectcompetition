<template>
  <div id="college_home">
    <el-card>
      <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <!-- ============================个人信息管理====================================== -->
          <el-tab-pane label="学院信息管理" name="collegeInfo">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>院系：{{this.$store.state.nickname}}</span>
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
          <!-- ============================教师账号注册====================================== -->
          <el-tab-pane label="教师账号注册" name="registerTeacher">
            <el-card style="width: 60%; height: 1400px">
              <div>
                <div style="text-align:center;">
                  <h1 style="font-size: 28px;">教师账号注册</h1>
                </div>
                <el-col :span="12" :offset="6">
                  <div class="body_form">
                    <el-form label-position="right" :model="teacherForm" ref="teacherForm" :rules="rules" >
                      <el-form-item label="教师名称" prop="teacherNickname">
                        <el-input v-model="teacherForm.teacherNickname" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="登录密码" prop="teacherPassword">
                        <el-input type="password" v-model="teacherForm.teacherPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="重新输入密码" prop="teacherRePassword">
                        <el-input type="password" v-model="teacherForm.teacherRePassword" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                      <el-button type="primary" @click="submitTeacher('teacherForm')">添加教师账号</el-button>
                      <el-tooltip class="item" effect="dark" style="margin-left: 50px" content="重置信息" placement="right">
                        <el-button type="warning" icon="el-icon-refresh-left"  circle @click="resetForm('teacherForm')"></el-button>
                      </el-tooltip>
                    </div>
                  </div>
                </el-col>
              </div>
            </el-card>
          </el-tab-pane>
          <!-- ============================学生账号注册====================================== -->
          <el-tab-pane label="学生账号注册" name="registerStudent">
            <el-card style="width: 60%; height: 1400px">
              <div>
                <div style="text-align:center;">
                  <h1 style="font-size: 28px;">学生账号注册</h1>
                </div>
                <el-col :span="12" :offset="6">
                  <div class="body_form">
                    <el-form label-position="right" :model="studentForm" ref="studentForm" :rules="rules" >
                      <el-form-item label="学生名称" prop="studentNickname">
                        <el-input v-model="studentForm.studentNickname" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="登录密码" prop="studentPassword">
                        <el-input type="password" v-model="studentForm.studentPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="重新输入密码" prop="studentRePassword">
                        <el-input type="password" v-model="studentForm.studentRePassword" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                      <el-button type="primary" @click="submitStudent('studentForm')">添加学生账号</el-button>
                      <el-tooltip class="item" effect="dark" style="margin-left: 50px" content="重置信息" placement="right">
                        <el-button type="warning" icon="el-icon-refresh-left"  circle @click="resetForm('studentForm')"></el-button>
                      </el-tooltip>
                    </div>
                  </div>
                </el-col>
              </div>
            </el-card>
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
    name: "college_home",
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
      var validateTeacherPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.teacherForm.teacherRePassword !== '') {
            this.$refs.teacherForm.validateField('teacherRePassword');
          }
          callback();
        }
      };
      var validateTeacherPass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.teacherForm.teacherPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      var validateStudentPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.studentForm.studentRePassword !== '') {
            this.$refs.teacherForm.validateField('studentRePassword');
          }
          callback();
        }
      };
      var validateStudentPass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.studentForm.studentPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        userData:{},
        teacherForm:{
          teacherNickname:'',
          teacherPassword:'',
          teacherRePassword:''
        },
        studentForm:{
          studentNickname:'',
          studentPassword:'',
          studentRePassword:''
        },
        checkUser:{
          nickname:"",
          password:'',
          rePassword:''
        },
        showTable: true,
        dialogFormVisible:false,
        activeName: "collegeInfo",
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
          ],
          teacherNickname:[
            { required: true, message: '教师名称不可为空', trigger: 'blur' }
          ],
          teacherPassword:[
            { validator: validateTeacherPass, trigger: 'blur' }
          ],
          teacherRePassword:[
            { validator: validateTeacherPass2, trigger: 'blur' }
          ],
          studentNickname:[
            { required: true, message: '教师名称不可为空', trigger: 'blur' }
          ],
          studentPassword:[
            { validator: validateStudentPass, trigger: 'blur' }
          ],
          studentRePassword:[
            { validator: validateStudentPass2, trigger: 'blur' }
          ]
        }
      };

    },
    mounted() {
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
        var collegeId= that.$store.state.id;
        this.$axios
        .post(api+`/college/getUserCollegeById/`+collegeId)
        .then(function(response) {
          that.userData = response.data;
          //将token身份加入到userData中
          that.$set(that.userData,"token",that.$store.state.token);
          that.$set(that.userData,"password","");
          that.checkUser.nickname = that.userData.nickname;
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
          console.error(error);
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
        .post(api+"/college/updateCollege",user)
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
       * 创建教师账号
       */
      createTeacher(){
        let that = this;
        var teacher=new FormData();
        teacher.append("nickname",that.teacherForm.teacherNickname);
        teacher.append("password",that.teacherForm.teacherPassword);
        teacher.append("collegeId",that.$store.state.id);
        that.$axios
        .post(api+`/college/createTeacher`,teacher)
        .then(function (response) {
          that.$message({
            message:response.data.code==200?'注册教师账号成功！账号为：'+response.data.data.username:response.data.message,
            type:response.data.code==200?'success':'error'
          })
          that.reload();
        })
      },
      /**
       * 先验证触发添加教师账号的按钮事件
       */
      submitTeacher(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.createTeacher();
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
       * 创建教师账号
       */
      createStudent(){
        let that = this;
        var student=new FormData();
        student.append("nickname",that.studentForm.studentNickname);
        student.append("password",that.studentForm.studentPassword);
        student.append("collegeId",that.$store.state.id);
        that.$axios
        .post(api+`/college/createStudent`,student)
        .then(function (response) {
          that.$message({
            message:response.data.code==200?'注册学生账号成功！账号为：'+response.data.data.username:response.data.message,
            type:response.data.code==200?'success':'error'
          })
          that.reload();
        })
      },
      /**
       * 先验证触发添加学生账号的按钮事件
       */
      submitStudent(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.createStudent();
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