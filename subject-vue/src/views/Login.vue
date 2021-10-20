<template>
  <div class="home">
      <div v-bind:class="[{aactive:iisActive},'errorClass']"></div>

      <div @mouseover="addActive" @mouseleave="removeActive" >
        <div @mouseover="adActive" @mouseleave="emoveActive" >

          <el-card id="form" style="padding: 0">
            <div v-bind:class="[{aa:aisActive},'errorClass']">
              <div slot="header" style="padding:-1px">
                <img src="../assets/img/logo1.png" alt />
              </div>
              <div style="padding: 0px 0px 0px 0px;">
                <el-form ref="form" :model="form" label-width="100px" :rules="rules" style="color: #080808">
                  <el-form-item label="用户名" prop="username" style="margin: 5px 70px 40px 15px">
                    <el-input  v-model="form.username"></el-input>
                  </el-form-item>
                  <el-form-item label="密码" prop="password" style="margin: 5px 70px 40px 15px">
                    <el-input type="password" v-model="form.password"></el-input>
                  </el-form-item>
                  <el-form-item label="登录身份" prop="token" style="margin:0 0 15px 15px">
                    <el-radio-group v-model="form.token" class="right" >
                      <el-radio class="radio_labels" label="student" value="student" >学生</el-radio>
                      <el-radio class="radio_labels" label="teacher" value="teacher">教师</el-radio>
                      <el-radio class="radio_labels" label="college" value="college">学院管理员</el-radio>
                      <el-radio class="radio_labels" label="admin" value="admin">校级管理员</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-button
                          style="width: 80%;margin-top: 10px;"
                          type="primary"
                          round
                          @click="submitForm('form')"
                  >登 录</el-button>
                </el-form>

              </div>
            </div>
          </el-card>

        </div>

      </div>




    <div class="dong">
      <ul>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
      </ul>
    </div>


  </div>
</template>
<style scoped>
  .el-card /deep/ .el-card__body{
    padding: 0;
  }
</style>
<script>
  import api from '../main.js'
  export default {
    name: "home",
    data() {
      return {
        iisActive:false,
        aisActive:false,
        form: {
          username: "",
          password: "",
          token:"",
        },
        rules: {
          username: [
            { required: true, message: "请输入用户名", trigger: "blur" }
          ],
          password: [
            { required: true, message: "请输入密码", trigger: "change" }
          ],
          token: [
            { required: true, message: "请选择登陆身份", trigger: "change" }
          ]
        }
      };
    },
    mounted() {
    },
    methods: {
      addActive(){
        this.iisActive=true;
      },
      removeActive(){
        this.iisActive=false;
      },
      adActive(){
        this.aisActive=true;
      },
      emoveActive(){
        this.aisActive=false;
      },

      loginUser() {
        var that = this;
        this.$axios
                .post(api+"/login", that.form)
                .then(function(response) {
                  switch (response.data.code) {
                    case 2001:
                      //登陆失败
                      that.$message({
                        type: "error",
                        message: response.data.message
                      });
                      break;
                    case 2003:
                      //登陆成功
                      //更改store 登陆状态
                      that.$store.commit("loginTure", true);
                      that.$store.commit("setUsername",response.data.user.username);
                      that.$store.commit("setNickname",response.data.user.nickname);
                      that.$store.commit("setToken",response.data.user.token);
                      that.$store.commit("setReceiver",response.data.user.targetId);
                      that.$store.commit("setId",response.data.user.id);
                      console.log(response.data.user);
                      //将username存入本地
                      window.sessionStorage.setItem("username", response.data.user.username);
                      //将token存入本地
                      window.sessionStorage.setItem("token", response.data.user.token);
                      //将nickname存入本地
                      window.sessionStorage.setItem("nickname", response.data.user.nickname);
                      //将targetId存入本地
                      window.sessionStorage.setItem("receiver", response.data.user.targetId);
                      //将id存入本地
                      window.sessionStorage.setItem("id", response.data.user.id);
                      //将islogin存入本地
                      window.sessionStorage.setItem("islogin", true);
                      that.$message({
                        message: response.data.message,
                        type: "success"
                      });

                      //跳转页面
                      switch (response.data.user.token) {
                        case "admin":
                          that.$router.push({ path: "/admin" });
                          break;
                        case "college":
                          that.$router.push({ path: "/college" });
                          break;
                        case "student":
                          that.$router.push({ path: "/student" });
                          break;
                        case "teacher":
                          that.$router.push({ path: "/teacher" });
                          break;
                      }
                      break;
                    default:
                      //提示信息
                      that.$message({
                        message: response.data.message,
                        type: "error"
                      });
                      break;
                  }
                })
                .catch(function(error) {
                  console.log("error");
                  console.log(error);
                });
      },
      submitForm(formName) {
        var that = this;
        this.$refs[formName].validate(valid => {
          if (valid) {
            that.loginUser();
          } else {
            return false;
          }
        });
      }
    },



  };
</script>

<style>
  * {
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;

  }




  body {
    margin: 0 auto;
    padding: 0;
    height: 100%;
    width: 100%;
    font: 14px Helvetica Neue,Helvetica,PingFang SC,微软雅黑,Tahoma,Arial,sans-serif;
    color: #fff;
    over-overflow:hidden;

  }
  /* 选中后的字体颜色 */
  .el-radio__input.is-checked + .el-radio__label {
    color: #000000 !important;
  }
  /* 选中后小圆点的颜色 */
  .el-radio__input.is-checked .el-radio__inner {
    background: rgb(0, 3, 4) !important;
    border-color: #000000 !important;
  }
  #form {
    text-align: center;
    right: 0;
    width: 550px;
    height: 450px;
    position: absolute;
    z-index: 1500;
    left: 50%;
    margin-left: -300px;
    top: 50%;
    margin-top: -250px;
    padding: 0;
    box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
    opacity: 1;
    background: linear-gradient(
            230deg,
            rgba(53, 57, 74, 0) 100%,
            rgb(0, 0, 0) 100%)
  }
  .aa{
    position: absolute;
    width: 548px;
    height: 448px;
    background: linear-gradient(to bottom right, rgba(46, 238, 233, 0.46), rgba(113, 191, 76, 0.64));
    background: -webkit-linear-gradient(to bottom right,rgba(46, 238, 233, 0.46),rgba(113, 191, 76, 0.64));
  }

  .home {
    width: 100%;
    height: 100%;
    position: fixed;
    background: linear-gradient(to bottom right, rgba(46, 238, 233, 0.46),#a3a043);
    background: -webkit-linear-gradient(to bottom right,rgba(46, 238, 233, 0.46),#a3a043);
  }
  .dong ul {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -10;
  }
  .dong ul li {
    list-style-type: none;
    display: block;
    position: absolute;
    bottom: -120px;
    border-radius: 20px;
    width: 15px;
    height: 15px;
    z-index: -8;
    background-color: rgba(77, 237, 236, 0.35);
    animotion: square 15s infinite;
    -webkit-animation: square 15s infinite;
  }
  .dong ul li:nth-child(1) {
    left: 0;
    animation-duration: 10s;
    -moz-animation-duration: 10s;
    -o-animation-duration: 10s;
    -webkit-animation-duration: 10s;
  }
  .dong ul li:nth-child(2) {
    width: 40px;
    height: 40px;
    left: 10%;
    animation-duration: 15s;
    -moz-animation-duration: 15s;
    -o-animation-duration: 15s;
    -webkit-animation-duration: 15s;
  }
  .dong ul li:nth-child(3) {
    left: 20%;
    width: 25px;
    height: 25px;
    animation-duration: 12s;
    -moz-animation-duration: 12s;
    -o-animation-duration: 12s;
    -webkit-animation-duration: 12s;
  }
  .dong ul li:nth-child(4) {
    width: 50px;
    height: 50px;
    left: 30%;
    -webkit-animation-delay: 3s;
    -moz-animation-delay: 3s;
    -o-animation-delay: 3s;
    animation-delay: 3s;
    animation-duration: 12s;
    -moz-animation-duration: 12s;
    -o-animation-duration: 12s;
    -webkit-animation-duration: 12s;
  }
  .dong ul li:nth-child(5) {
    width: 60px;
    height: 60px;
    left: 40%;
    animation-duration: 10s;
    -moz-animation-duration: 10s;
    -o-animation-duration: 10s;
    -webkit-animation-duration: 10s;
  }
  .dong ul li:nth-child(6) {
    width: 75px;
    height: 75px;
    left: 50%;
    -webkit-animation-delay: 7s;
    -moz-animation-delay: 7s;
    -o-animation-delay: 7s;
    animation-delay: 7s;
  }
  .dong ul li:nth-child(7) {
    left: 60%;
    animation-duration: 8s;
    -moz-animation-duration: 8s;
    -o-animation-duration: 8s;
    -webkit-animation-duration: 8s;
  }
  .dong ul li:nth-child(8) {
    width: 90px;
    height: 90px;
    left: 70%;
    -webkit-animation-delay: 4s;
    -moz-animation-delay: 4s;
    -o-animation-delay: 4s;
    animation-delay: 4s;
  }
  .dong ul li:nth-child(9) {
    width: 100px;
    height: 100px;
    left: 80%;
    animation-duration: 20s;
    -moz-animation-duration: 20s;
    -o-animation-duration: 20s;
    -webkit-animation-duration: 20s;
  }
  .dong ul li:nth-child(10) {
    width: 120px;
    height: 120px;
    left: 90%;
    -webkit-animation-delay: 6s;
    -moz-animation-delay: 6s;
    -o-animation-delay: 6s;
    animation-delay: 6s;
    animation-duration: 30s;
    -moz-animation-duration: 30s;
    -o-animation-duration: 30s;
    -webkit-animation-duration: 30s;
  }
  label{
    color: #000000;
    font-weight: bold;
  }

  @keyframes square {
    0%  {
      -webkit-transform: translateY(0);
      transform: translateY(0)
    }
    100% {
      bottom: 400px;
      transform: rotate(600deg);
      -webit-transform: rotate(600deg);
      -webkit-transform: translateY(-800);
      transform: translateY(-500)
    }
  }
  @-webkit-keyframes square {
    0%  {
      -webkit-transform: translateY(0);
      transform: translateY(0)
    }
    100% {
      bottom: 400px;
      transform: rotate(600deg);
      -webit-transform: rotate(600deg);
      -webkit-transform: translateY(-500);
      transform: translateY(-500)
    }
  }

  .aactive {
    width:100%;
    height:100%;
    background-color:#000;
    filter:alpha(opacity=30);
    -moz-opacity:0.3;
    opacity:0.3;
    position:absolute;
    left:0px;
    top:0px;
    z-index:1000;
  }



</style>