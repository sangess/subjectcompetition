(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-441d1f5d"],{"0064":function(e,t,a){"use strict";a("41b5")},"0400":function(e,t,a){},"0b0b":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"teacher_home"}},[a("el-card",[a("div",[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"我的比赛项目",name:"myComps"}},[a("el-button",{staticStyle:{float:"right"},attrs:{type:"text"},on:{click:function(t){e.导出为Excel("我的全部竞赛项目")}}},[e._v("导出为Excel")]),a("el-table",{attrs:{id:"我的全部竞赛项目",stripe:"",border:"",data:e.myComps.slice((e.currentPage-1)*e.pagesize,e.currentPage*e.pagesize),"default-sort":{prop:"level",order:"descending"}}},[a("el-table-column",{attrs:{align:"center",prop:"compId",label:"竞赛编号",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(t.row.comp.id)+"\n              ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"compName",label:"竞赛名称",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(t.row.comp.compName)+"\n              ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"level",label:"竞赛等级",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[0==t.row.comp.level?a("p",[e._v("国家级")]):e._e(),1==t.row.comp.level?a("p",[e._v("省级")]):e._e(),2==t.row.comp.level?a("p",[e._v("校级")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",prop:"compTime",label:"竞赛时间",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(e._f("dataTime")(t.row.comp.occurrenceTime))+"\n              ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"compState",label:"竞赛状态",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{effect:"dark",type:0===t.row.comp.compState?"info":1===t.row.comp.compState?"primary":2===t.row.comp.compState?"success":3===t.row.comp.compState?"warning":"danger","disable-transitions":""}},[0==t.row.comp.compState?a("span",[e._v("规划中")]):e._e(),1==t.row.comp.compState?a("span",[e._v("报名中")]):e._e(),2==t.row.comp.compState?a("span",[e._v("备赛中")]):e._e(),3==t.row.comp.compState?a("span",[e._v("比赛中")]):e._e(),4==t.row.comp.compState?a("span",[e._v("已结束")]):e._e()])]}}])}),a("el-table-column",{attrs:{align:"center",label:"我的团队"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-link",{attrs:{underline:!1},on:{click:function(a){return e.checkMyTeam(t.row)}}},[a("i",{staticClass:"el-icon-view el-icon--right"},[e._v("查看")])])]}}])}),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popconfirm",{attrs:{title:"解散项目组？"},on:{confirm:function(a){return e.invokeProject(t.row.id)}}},[t.row.createByTeacher.id==e.teacherId?a("el-button",{attrs:{slot:"reference",type:"danger",size:"small"},slot:"reference"},[e._v("解散项目组")]):e._e()],1),a("el-popconfirm",{attrs:{title:"退出项目组？"},on:{confirm:function(a){return e.exitProject(t.row.id)}}},[t.row.createByTeacher.id!=e.teacherId?a("el-button",{attrs:{slot:"reference",type:"danger",size:"small"},slot:"reference"},[e._v("退出项目组")]):e._e()],1)]}}])})],1),a("div",{attrs:{id:"fenye"}},[a("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:e.myComps.length,"page-size":8,"current-page":e.currentPage},on:{"current-change":e.current_change,"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t}}})],1)],1),a("el-tab-pane",{attrs:{label:"教师信息管理",name:"userInfo"}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[e._v("教师："+e._s(this.$store.state.nickname))]),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:function(t){e.dialogFormVisible=!0}}},[e._v("修改资料")])],1),a("div",{staticClass:"text item"},[a("p",[e._v("账号名："+e._s(this.$store.state.username))]),a("p",[e._v("姓名："+e._s(this.$store.state.nickname))]),a("p",[e._v("账户类型："+e._s("admin"==this.$store.state.token?"学校管理员":"college"==this.$store.state.token?"学院":"teacher"==this.$store.state.token?"老师":"学生"))])])]),a("el-dialog",{attrs:{title:"院系信息修改",visible:e.dialogFormVisible,"before-close":e.handleClose,width:e.dialogWidth},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"checkUser",attrs:{model:e.checkUser,"status-icon":"",rules:e.rules,"label-position":e.labelPosition,"label-width":e.labelWidth}},[a("el-form-item",{attrs:{label:"昵称（姓名）",prop:"nickname"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.checkUser.nickname,callback:function(t){e.$set(e.checkUser,"nickname",t)},expression:"checkUser.nickname"}})],1),a("el-form-item",{attrs:{label:"密码",prop:"password"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.checkUser.password,callback:function(t){e.$set(e.checkUser,"password",t)},expression:"checkUser.password"}})],1),a("el-form-item",{attrs:{label:"重新输入密码",prop:"rePassword"}},[a("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.checkUser.rePassword,callback:function(t){e.$set(e.checkUser,"rePassword",t)},expression:"checkUser.rePassword"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitChange("checkUser")}}},[e._v("确定修改")])],1)],1)],1),a("el-dialog",{attrs:{title:"项目成员",visible:e.projectPersonInfo,width:"70%"},on:{"update:visible":function(t){e.projectPersonInfo=t}}},[a("el-table",{attrs:{data:e.projectJoinTeacher}},[a("el-table-column",{attrs:{property:"nickname",label:"教师姓名"}}),a("el-table-column",{attrs:{property:"username",label:"用户名"}}),a("el-table-column",{attrs:{property:"college.nickname",label:"所属院系"}}),a("el-table-column",{attrs:{label:"身份"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.id==e.createProjectTeacherId?a("span",[a("el-tag",{attrs:{type:"success"}},[e._v("带队老师")])],1):e._e(),t.row.id!=e.createProjectTeacherId?a("span",[a("el-tag",{attrs:{type:"Primary"}},[e._v("指导老师")])],1):e._e()]}}])}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popconfirm",{attrs:{title:"退出项目组？"},on:{confirm:function(t){return e.exitProject(e.projectId)}}},[t.row.id==e.teacherId&&t.row.id!=e.createProjectTeacherId?a("el-button",{attrs:{slot:"reference",type:"danger",size:"small"},slot:"reference"},[e._v("退出项目组")]):e._e()],1),a("el-popconfirm",{attrs:{title:"解散项目组？"},on:{confirm:function(t){return e.invokeProject(e.projectId)}}},[t.row.id==e.teacherId&&t.row.id==e.createProjectTeacherId?a("el-button",{attrs:{slot:"reference",type:"danger",size:"small"},slot:"reference"},[e._v("解散项目组")]):e._e()],1)]}}])})],1),a("el-table",{attrs:{data:e.projectJoinStudent}},[a("el-table-column",{attrs:{property:"nickname",label:"学生姓名"}}),a("el-table-column",{attrs:{property:"username",label:"学号"}}),a("el-table-column",{attrs:{property:"college.nickname",label:"所属院系"}}),a("el-table-column",{attrs:{label:"身份"}},[a("el-tag",{attrs:{type:"Primary"}},[e._v("参赛学员")])],1),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popconfirm",{attrs:{title:"是否将学生移出项目组？"},on:{confirm:function(a){return e.exitStudent(t.row.id)}}},[e.teacherId==e.createProjectTeacherId?a("el-button",{attrs:{slot:"reference",type:"danger",size:"small"},slot:"reference"},[e._v("移出")]):e._e()],1)]}}])})],1)],1)],1)],1)])],1)},o=[],n=a("56d7"),s=a("2f14"),c={inject:["reload"],name:"teacher_home",data:function(){var e=this,t=function(t,a,r){""===a?r(new Error("请输入密码")):(""!==e.checkUser.rePassword&&e.$refs.checkUser.validateField("rePassword"),r())},a=function(t,a,r){""===a?r(new Error("请再次输入密码")):a!==e.checkUser.password?r(new Error("两次输入密码不一致!")):r()};return{teacherId:"",projectId:"",projectJoinTeacher:[],projectJoinStudent:[],projectPersonInfo:!1,pagesize:8,currentPage:1,userData:{},myComps:[],createProjectTeacherId:"",myJoinComps:[],checkUser:{nickname:"",password:"",rePassword:""},dialogFormVisible:!1,activeName:"myComps",labelPosition:"right",dialogWidth:"700px",labelWidth:"120px",rules:{password:[{validator:t,trigger:"blur"}],rePassword:[{validator:a,trigger:"blur"}],nickname:[{required:!0,message:"昵称（姓名）不可为空",trigger:"blur"}]}}},mounted:function(){this.teacherId=this.$store.state.id,this.showMyComps(),this.getUserData()},methods:{handleClick:function(e,t){},checkMyTeam:function(e){var t=this,a=e.id;t.projectId=a,this.createProjectTeacherId=e.createByTeacher.id,this.projectPersonInfo=!0,this.getProjectJoinTeacher(a),this.getProjectJoinStudent(a)},showMyComps:function(){var e=this,t=e.$store.state.id;e.$axios.get(n["default"]+"/project/getTeacherJoinProjects/"+t).then((function(t){e.myComps=t.data}))},current_change:function(e){this.currentPage=e},getProjectJoinTeacher:function(e){var t=this;t.$axios.get(n["default"]+"/project/getProjectJoinTeacher/"+e).then((function(e){t.projectJoinTeacher=e.data}))},getProjectJoinStudent:function(e){var t=this;t.$axios.get(n["default"]+"/project/getProjectJoinStudent/"+e).then((function(e){t.projectJoinStudent=e.data}))},invokeProject:function(e){var t=this;t.$axios.get(n["default"]+"/project/invokeProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"error"}),t.reload()}))},getUserData:function(){var e=this,t=e.$store.state.id;this.$axios.get(n["default"]+"/teacher/getUserTeacherById/"+t).then((function(t){e.userData=t.data,e.$set(e.userData,"token",e.$store.state.token),e.$set(e.userData,"password",""),e.checkUser.nickname=e.userData.nickname})).catch((function(t){e.$message.error("服务器错误，操作失败！")}))},handleClose:function(e){this.$confirm("确认关闭？").then((function(t){e()})).catch((function(e){}))},submitUserInto:function(){var e=this,t=new FormData;t.append("nickname",e.checkUser.nickname),t.append("id",e.userData.id),t.append("password",e.checkUser.password),e.$axios.post(n["default"]+"/teacher/updateTeacher",t).then((function(t){e.dialogFormVisible=!1,e.checkUser.password="",e.checkUser.rePassword="",e.$store.commit("setNickname",t.data.data.nickname),e.userData.nickname=t.data.data.nickname,e.$message({message:t.data.message,type:200==t.data.code?"success":"warning"})}))},submitChange:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return t.$message({message:"提交失败！",type:"warning"}),!1;t.submitUserInto(),t.reload()}))},resetForm:function(e){this.$refs[e].resetFields()},exitTeacher:function(e){var t=this;t.$axios.get(n["default"]+"/teacher/exitTeacher/"+e+"/"+t.projectId).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"error"}),t.reload()}))},exitStudent:function(e){var t=this;t.$axios.get(n["default"]+"/teacher/exitStudent/"+e+"/"+t.projectId).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"error"}),t.reload()}))},exitProject:function(e){var t=this;t.$axios.get(n["default"]+"/teacher/exitProject/"+t.teacherId+"/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"error"}),t.reload()}))},"导出为Excel":function(e){var t=e;Object(s["a"])(e,t)}},filters:{dataTime:function(e){if(void 0==e)return"-";if(e=new Date(e),e instanceof Date){var t=e,a=t.getFullYear(),r=t.getMonth()+1,o=t.getDate()<10?"0"+t.getDate():t.getDate(),n=a+"年"+r+"月 "+o+"日";return n}return"-"}}},i=c,l=(a("0064"),a("1a96"),a("2877")),u=Object(l["a"])(i,r,o,!1,null,null,null);t["default"]=u.exports},"1a96":function(e,t,a){"use strict";a("0400")},"41b5":function(e,t,a){}}]);
//# sourceMappingURL=chunk-441d1f5d.c2bedca0.js.map