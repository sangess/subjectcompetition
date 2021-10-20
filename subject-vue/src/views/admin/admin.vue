<template>
  <div id="admin" >
    <el-container >
      <el-header height="50px" style="padding: 0">
        <el-menu
                :default-active="this.$router.path"
                router
                class="el-menu-demo"
                mode="horizontal"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b" >
          <el-menu-item index="1">
            <div style="height:100%;width:239px;font-size: 17px">
              <img style="height: 30px;" src="../../assets/img/6310.png" /> 学科竞赛管理系统
            </div>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">我的管理</template>
            <el-menu-item index="/admin/comp">竞赛管理</el-menu-item>
            <el-menu-item index="/admin/project">项目管理</el-menu-item>
            <el-menu-item index="/admin/gonggao">公告管理</el-menu-item>
          </el-submenu>
          <el-menu-item index="/admin/tongji">信息统计</el-menu-item>
          <el-menu-item type="success" size="small" round icon="el-icon-user-solid"  style="float: right;">
          <span type="danger" size="small" round @click="注销登录()">注销登录<i class="el-icon-switch-button"></i></span>
          </el-menu-item>
          <el-menu-item type="success" size="small" round icon="el-icon-user-solid"  style="float: right">
                  {{this.$store.state.username}} 管理员<i class="el-icon-user-solid"></i>
          </el-menu-item>
        </el-menu>
      </el-header>
      <el-container  >
        <el-aside width="180px" style=" height: 100%; background: linear-gradient(to bottom right,#65FDF0,#1D6FA3)">
          <el-menu
                  :default-active="this.$router.path"
                  router
                  text-color="1D6FA3"
                  active-text-color="black"
                  style="border-right: 2px;
                   background: linear-gradient(to bottom right,rgba(101,253,240,0),rgba(29,111,163,0));    "
                  class="el-menu-vertical-demo"
          >
            <el-menu-item index="/admin/home">
              <i class="el-icon-user-solid"></i>
              <span slot="title">个人中心</span>
            </el-menu-item>
            <el-menu-item index="/admin/index">
              <i class="el-icon-menu"></i>
              <span slot="title">首页</span>
            </el-menu-item>
            <el-menu-item index="/admin/comp">
              <i class="el-icon-wallet"></i>
              <span slot="title">竞赛管理</span>
            </el-menu-item>

            <el-menu-item index="/admin/project">
              <i class="el-icon-reading"></i>
              <span slot="title">项目查看</span>
            </el-menu-item>

            <el-menu-item index="/admin/apply">
              <i class="el-icon-s-check"></i>
              <span slot="title">竞赛申请管理</span>
            </el-menu-item>

            <el-menu-item index="/admin/gonggao">
              <i class="el-icon-document-copy"></i>
              <span slot="title">公告管理</span>
            </el-menu-item>
            <el-menu-item index="/admin/xinxi">
              <i class="el-icon-postcard"></i>
              <span slot="title">通知管理</span>
            </el-menu-item>
            <el-menu-item index="/admin/tongji">
              <i class="el-icon-s-data"></i>
              <span slot="title">信息统计</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main style="background-color: rgba(165,165,165,0.43)">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import api from '../../main.js'
  export default {
    name: "admin",
    data() {
      return {
        username: "",
        receiver:this.$store.state.receiver,
        xinxiList:[],
        time1:"",
      };
    },
    mounted() {
      this.getMessage();
      /*this.time1=setInterval(this.getMessage,2000);*/

    },
    methods: {
      getMessage() {
        let that = this;
        that.$axios
        .get(api+`/msg/getUnReadListPopup/`+that.receiver)
        .then(function(response) {
          that.xinxiList = response.data;
          that.showMessage();
        })
        .catch(function(error) {
        });
      },
      showMessage: async function() {
        let that = this;
        /**创建一个节点元素 */
        const h = that.$createElement;
        for (let i = 0; i < that.xinxiList.length; i++) {
          await that.$notify({
            title: that.xinxiList[i].title,
            message: that.xinxiList[i].content,
            type: that.xinxiList[i].type==2?'success':'warning',
            duration: 7000,
            offset: 55
          });
        }
      },
      注销登录() {
        this.$confirm("此操作将退出登录, 是否继续?", "注意", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
        .then(() => {
          //清除store.state.userinfo 和 store.state.islogin
          this.$store.commit("logout",false);
          //从window.sessionStorage 中删除相应内容
          window.sessionStorage.setItem("username", "");

          window.sessionStorage.setItem("id", "");

          window.sessionStorage.setItem("nickname", "");

          window.sessionStorage.setItem("token", "");

          window.sessionStorage.setItem("receiver", "");

          window.sessionStorage.setItem("islogin", false);

          this.$message({
            type: "success",
            message: "已注销登录!"
          });
          //跳转登陆页面
          this.$router.push({ path: "/" });
        })
        .catch(() => {
          // this.$message({
          //   type: "waring",
          //   message: "未知错误！"
          // });
        });
      }
    },
    beforeDestroy() {
      clearInterval(this.time1);
    }
  };
</script>


<style>
  body {
    margin: 0px;
  }
  #admin {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    height: 100vh;
    color: #2c3e50;
  }
  .el-container.is-vertical {
    height: 100vh;
  }
  .el-container {
    height: 100vh;
  }

  .el-header {
    background-color: white;
    height: 55px;
    z-index: 99;
    border-bottom: 1px solid rgb(238, 238, 238);
  }

  .el-aside {
    background-color: white;
    color: #333;
    height: 100vh;
    width: 260px;
    border-right: 1px solid #eee;
    box-shadow: 6px 1px 20px rgba(69, 65, 78, 0.1);
    z-index: 1;
  }

  .el-main {
    background-color: #f2f3f8;
    color: #333;
  }
  .el-card {
    border-radius: 25px;
  }
  #user {
    width: 100%;
    text-align: center;
    padding: 24px;
  }

  .elTable td {
    padding: 5px 0 !important;
  }
</style>
