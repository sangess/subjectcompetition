import Vue from 'vue'
import Vuex from 'vuex'
import da from "element-ui/src/locale/lang/da";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    islogin: false,
    id:"",
    username: "",
    nickname:"",
    receiver:"",
    token: "",
  },
  mutations: {
    loginTure(state, data) {
      state.islogin = data;
    },
    setId(state,data){
      state.id=data;
    },
    setUsername(state,data){
      state.username=data;
    },
    setNickname(state,data){
      state.nickname=data;
    },
    setToken(state,data){
      state.token=data;
    },
    setReceiver(state,data){
      state.receiver=data;
    },
    logout(state) {
      state.userinfo = {};
      state.islogin = false;
    },
  },
  actions: {

  }
})