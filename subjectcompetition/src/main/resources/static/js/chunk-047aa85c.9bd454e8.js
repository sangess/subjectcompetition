(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-047aa85c"],{"2d53":function(e,t,a){"use strict";a("36a4")},"36a4":function(e,t,a){},"67b4":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"college_home"}},[a("el-card",[a("div",[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"学院信息管理",name:"collegeInfo"}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[e._v("院系："+e._s(this.$store.state.nickname))]),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:function(t){e.dialogFormVisible=!0}}},[e._v("修改资料")])],1),a("div",{staticClass:"text item"},[a("p",[e._v("账号名："+e._s(this.$store.state.username))]),a("p",[e._v("姓名："+e._s(this.$store.state.nickname))]),a("p",[e._v("账户类型："+e._s("admin"==this.$store.state.token?"学校管理员":"college"==this.$store.state.token?"学院":"teacher"==this.$store.state.token?"老师":"学生"))])])]),a("el-dialog",{attrs:{title:"院系信息修改",visible:e.dialogFormVisible,"before-close":e.handleClose,width:e.dialogWidth},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"checkUser",attrs:{model:e.checkUser,"status-icon":"",rules:e.rules,"label-position":e.labelPosition,"label-width":e.labelWidth}},[a("el-form-item",{attrs:{label:"昵称（姓名）",prop:"nickname"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.checkUser.nickname,callback:function(t){e.$set(e.checkUser,"nickname",t)},expression:"checkUser.nickname"}})],1),a("el-form-item",{attrs:{label:"密码",prop:"password"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.checkUser.password,callback:function(t){e.$set(e.checkUser,"password",t)},expression:"checkUser.password"}})],1),a("el-form-item",{attrs:{label:"重新输入密码",prop:"rePassword"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.checkUser.rePassword,callback:function(t){e.$set(e.checkUser,"rePassword",t)},expression:"checkUser.rePassword"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitChange("checkUser")}}},[e._v("确定修改")])],1)],1)],1),a("el-tab-pane",{attrs:{label:"教师账号注册",name:"registerTeacher"}},[a("el-card",{staticStyle:{width:"60%",height:"1400px"}},[a("div",[a("div",{staticStyle:{"text-align":"center"}},[a("h1",{staticStyle:{"font-size":"28px"}},[e._v("教师账号注册")])]),a("el-col",{attrs:{span:12,offset:6}},[a("div",{staticClass:"body_form"},[a("el-form",{ref:"teacherForm",attrs:{"label-position":"right",model:e.teacherForm,rules:e.rules}},[a("el-form-item",{attrs:{label:"教师名称",prop:"teacherNickname"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.teacherForm.teacherNickname,callback:function(t){e.$set(e.teacherForm,"teacherNickname",t)},expression:"teacherForm.teacherNickname"}})],1),a("el-form-item",{attrs:{label:"登录密码",prop:"teacherPassword"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.teacherForm.teacherPassword,callback:function(t){e.$set(e.teacherForm,"teacherPassword",t)},expression:"teacherForm.teacherPassword"}})],1),a("el-form-item",{attrs:{label:"重新输入密码",prop:"teacherRePassword"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.teacherForm.teacherRePassword,callback:function(t){e.$set(e.teacherForm,"teacherRePassword",t)},expression:"teacherForm.teacherRePassword"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitTeacher("teacherForm")}}},[e._v("添加教师账号")]),a("el-tooltip",{staticClass:"item",staticStyle:{"margin-left":"50px"},attrs:{effect:"dark",content:"重置信息",placement:"right"}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-refresh-left",circle:""},on:{click:function(t){return e.resetForm("teacherForm")}}})],1)],1)],1)])],1)])],1),a("el-tab-pane",{attrs:{label:"学生账号注册",name:"registerStudent"}},[a("el-card",{staticStyle:{width:"60%",height:"1400px"}},[a("div",[a("div",{staticStyle:{"text-align":"center"}},[a("h1",{staticStyle:{"font-size":"28px"}},[e._v("学生账号注册")])]),a("el-col",{attrs:{span:12,offset:6}},[a("div",{staticClass:"body_form"},[a("el-form",{ref:"studentForm",attrs:{"label-position":"right",model:e.studentForm,rules:e.rules}},[a("el-form-item",{attrs:{label:"学生名称",prop:"studentNickname"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.studentForm.studentNickname,callback:function(t){e.$set(e.studentForm,"studentNickname",t)},expression:"studentForm.studentNickname"}})],1),a("el-form-item",{attrs:{label:"登录密码",prop:"studentPassword"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.studentForm.studentPassword,callback:function(t){e.$set(e.studentForm,"studentPassword",t)},expression:"studentForm.studentPassword"}})],1),a("el-form-item",{attrs:{label:"重新输入密码",prop:"studentRePassword"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.studentForm.studentRePassword,callback:function(t){e.$set(e.studentForm,"studentRePassword",t)},expression:"studentForm.studentRePassword"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitStudent("studentForm")}}},[e._v("添加学生账号")]),a("el-tooltip",{staticClass:"item",staticStyle:{"margin-left":"50px"},attrs:{effect:"dark",content:"重置信息",placement:"right"}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-refresh-left",circle:""},on:{click:function(t){return e.resetForm("studentForm")}}})],1)],1)],1)])],1)])],1)],1)],1)])],1)},s=[],o=(a("2f14"),a("56d7")),n={inject:["reload"],name:"college_home",data:function(){var e=this,t=function(t,a,r){""===a?r(new Error("请输入密码")):(""!==e.checkUser.rePassword&&e.$refs.checkUser.validateField("rePassword"),r())},a=function(t,a,r){""===a?r(new Error("请再次输入密码")):a!==e.checkUser.password?r(new Error("两次输入密码不一致!")):r()},r=function(t,a,r){""===a?r(new Error("请输入密码")):(""!==e.teacherForm.teacherRePassword&&e.$refs.teacherForm.validateField("teacherRePassword"),r())},s=function(t,a,r){""===a?r(new Error("请再次输入密码")):a!==e.teacherForm.teacherPassword?r(new Error("两次输入密码不一致!")):r()},o=function(t,a,r){""===a?r(new Error("请输入密码")):(""!==e.studentForm.studentRePassword&&e.$refs.teacherForm.validateField("studentRePassword"),r())},n=function(t,a,r){""===a?r(new Error("请再次输入密码")):a!==e.studentForm.studentPassword?r(new Error("两次输入密码不一致!")):r()};return{userData:{},teacherForm:{teacherNickname:"",teacherPassword:"",teacherRePassword:""},studentForm:{studentNickname:"",studentPassword:"",studentRePassword:""},checkUser:{nickname:"",password:"",rePassword:""},showTable:!0,dialogFormVisible:!1,activeName:"collegeInfo",labelPosition:"right",dialogWidth:"700px",labelWidth:"120px",rules:{password:[{validator:t,trigger:"blur"}],rePassword:[{validator:a,trigger:"blur"}],nickname:[{required:!0,message:"昵称（姓名）不可为空",trigger:"blur"}],teacherNickname:[{required:!0,message:"教师名称不可为空",trigger:"blur"}],teacherPassword:[{validator:r,trigger:"blur"}],teacherRePassword:[{validator:s,trigger:"blur"}],studentNickname:[{required:!0,message:"教师名称不可为空",trigger:"blur"}],studentPassword:[{validator:o,trigger:"blur"}],studentRePassword:[{validator:n,trigger:"blur"}]}}},mounted:function(){this.getUserData()},methods:{handleClick:function(e,t){},getUserData:function(){var e=this,t=e.$store.state.id;this.$axios.post(o["default"]+"/college/getUserCollegeById/"+t).then((function(t){e.userData=t.data,e.$set(e.userData,"token",e.$store.state.token),e.$set(e.userData,"password",""),e.checkUser.nickname=e.userData.nickname})).catch((function(t){e.$message.error("服务器错误，操作失败！"),console.error(t)}))},handleClose:function(e){this.$confirm("确认关闭？").then((function(t){e()})).catch((function(e){}))},submitUserInto:function(){var e=this,t=new FormData;t.append("nickname",e.checkUser.nickname),t.append("id",e.userData.id),t.append("password",e.checkUser.password),e.$axios.post(o["default"]+"/college/updateCollege",t).then((function(t){console.log(t.data),e.dialogFormVisible=!1,e.checkUser.password="",e.checkUser.rePassword="",e.$store.commit("setNickname",t.data.data.nickname),e.userData.nickname=t.data.data.nickname,e.$message({message:t.data.message,type:200==t.data.code?"success":"warning"})}))},submitChange:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return t.$message({message:"提交失败！",type:"warning"}),!1;t.submitUserInto(),t.reload()}))},createTeacher:function(){var e=this,t=new FormData;t.append("nickname",e.teacherForm.teacherNickname),t.append("password",e.teacherForm.teacherPassword),t.append("collegeId",e.$store.state.id),e.$axios.post(o["default"]+"/college/createTeacher",t).then((function(t){e.$message({message:200==t.data.code?"注册教师账号成功！账号为："+t.data.data.username:t.data.message,type:200==t.data.code?"success":"error"}),e.reload()}))},submitTeacher:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return t.$message({message:"提交失败！",type:"warning"}),!1;t.createTeacher(),t.reload()}))},createStudent:function(){var e=this,t=new FormData;t.append("nickname",e.studentForm.studentNickname),t.append("password",e.studentForm.studentPassword),t.append("collegeId",e.$store.state.id),e.$axios.post(o["default"]+"/college/createStudent",t).then((function(t){e.$message({message:200==t.data.code?"注册学生账号成功！账号为："+t.data.data.username:t.data.message,type:200==t.data.code?"success":"error"}),e.reload()}))},submitStudent:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return t.$message({message:"提交失败！",type:"warning"}),!1;t.createStudent(),t.reload()}))},resetForm:function(e){this.$refs[e].resetFields()}}},i=n,c=(a("2d53"),a("2877")),l=Object(c["a"])(i,r,s,!1,null,null,null);t["default"]=l.exports}}]);
//# sourceMappingURL=chunk-047aa85c.9bd454e8.js.map