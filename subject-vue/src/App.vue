
<template>
  <div id="app">
    <router-view v-if="isRouterAlive"></router-view>

  </div>
</template>
<script>
export default {
  name: "app",
  provide(){
    return{
      reload:this.reload
    }
  },
  data() {
    return {
      isRouterAlive:true
    };
  },

  mounted() {
    var token = window.sessionStorage.getItem("token");
    this.$store.commit("setToken", token);
    var islogin = window.sessionStorage.getItem("islogin");
    this.$store.commit("loginTure", islogin);
    var username = window.sessionStorage.getItem("username");
    this.$store.commit("setUsername", username);
    var id = window.sessionStorage.getItem("id");
    this.$store.commit("setId", id);
    var receiver = window.sessionStorage.getItem("receiver");
    this.$store.commit("setReceiver", receiver);
    var nickname = window.sessionStorage.getItem("nickname");
    this.$store.commit("setNickname", nickname);
  },
  methods: {
    reload(){
      this.isRouterAlive=false;
      this.$nextTick(function () {
        this.isRouterAlive=true;
      })
    }
  }
};
</script>

