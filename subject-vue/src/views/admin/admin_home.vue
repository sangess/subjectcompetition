<template>
  <div id="admin_home">
    <el-card>
      <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <!-- ============================个人信息管理====================================== -->
          <el-tab-pane label="个人信息管理" name="个人信息管理">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>管理员：{{this.$store.state.nickname}}</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="dialogFormVisible = true">修改资料</el-button>
              </div>
              <div class="text item">
                <p>账号名：{{this.$store.state.username}}</p>
                <p>姓名：{{this.$store.state.nickname}}</p>
                <p>账户类型：{{userData.token=='admin'?'学校管理员':userData.token=='college'?'学院':userData.token=='teacher'?'老师':'学生'}}</p>
              </div>
            </el-card>
            <!-- 修改信息模态框 -->
            <el-dialog title="用户信息修改"  :visible.sync="dialogFormVisible" :before-close="handleClose" :width="dialogWidth">
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
          <!-- ============================学院账号注册====================================== -->
          <el-tab-pane label="学院账号注册" name="学院账号注册">
            <el-card style="width: 60%; height: 1400px">
              <div>
                <div style="text-align:center;">
                  <h1 style="font-size: 28px;">学院账号注册</h1>
                </div>
                <el-col :span="12" :offset="6">
                  <div class="body_form">
                    <el-form label-position="right" :model="collegeForm" ref="collegeForm" :rules="rules" >
                      <el-form-item label="院系名称" prop="collegeNickname">
                        <el-input v-model="collegeForm.collegeNickname" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="登录密码" prop="collegePassword">
                        <el-input type="password" v-model="collegeForm.collegePassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="重新输入密码" prop="collegeRePassword">
                        <el-input type="password" v-model="collegeForm.collegeRePassword" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                      <el-button type="primary" @click="submitCollege('collegeForm')" round>创建学院</el-button>
                      <el-tooltip class="item" effect="dark" style="margin-left: 50px" content="重置信息" placement="right">
                        <el-button type="warning" icon="el-icon-refresh-left"  circle @click="resetForm('collegeForm')"></el-button>
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
  name: "admin_home",
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
    var validateCollegePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.collegeForm.collegeRePassword !== '') {
          this.$refs.collegeForm.validateField('collegeRePassword');
        }
        callback();
      }
    };
    var validateCollegePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.collegeForm.collegePassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      userData:{},
      collegeForm:{
        collegeNickname:'',
        collegePassword:'',
        collegeRePassword:''
      },
      checkUser:{
        nickname:"",
        password:'',
        rePassword:''
      },
      showTable: true,
      dialogFormVisible:false,
      activeName: "个人信息管理",
      labelPosition: 'right',
      dialogWidth: '700px',
      labelWidth:'120px',
      rules:{
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        rePassword: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        nickname:[
          { required: true, message: '昵称（姓名）不可为空', trigger: 'blur' }
        ],
        collegeNickname:[
          { required: true, message: '学院名称不可为空', trigger: 'blur' }
        ],
        collegePassword:[
          { validator: validateCollegePass, trigger: 'blur' }
        ],
        collegeRePassword:[
          { validator: validateCollegePass2, trigger: 'blur' }
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
      var id= that.$store.state.id;
      this.$axios
        .get(api+`/admin/getUserAdminById/?id=`+id)
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
     * 提交表单修改信息
     */
    submitUserInto(){
      let that = this;
      let user = new FormData();
      user.append("nickname",that.checkUser.nickname);
      user.append("id",that.userData.id);
      user.append("password",that.checkUser.password);
      user.append("username",that.$store.state.username);
          that.$axios
          .post(api+"/admin/updateAdmin",user)
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
    createCollege(){
      let that = this;
      var college=new FormData();
      college.append("nickname",that.collegeForm.collegeNickname);
      college.append("password",that.collegeForm.collegePassword);
      that.$axios
        .post(api+`/admin/createCollege`,college)
        .then(function (response) {
          that.$message({
            message:response.data.code==200?'注册成功！账号为：'+response.data.data.username:response.data.message,
            type:response.data.code==200?'success':'error'
          })
          this.reload();
        })
    },
    submitCollege(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.createCollege();
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