(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9c1c3d46"],{"12c6":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"admin_project"}},[a("el-card",{staticStyle:{height:"690px"}},[a("div",[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"竞赛项目列表",name:"竞赛项目列表"}},[a("div",[a("el-row",{attrs:{gutter:10}},[a("el-form",{ref:"searchProjects",attrs:{model:e.searchProjects,"label-width":"80px"}},[a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"项目名称",prop:"name"}},[a("el-input",{model:{value:e.searchProjects.projectName,callback:function(t){e.$set(e.searchProjects,"projectName",t)},expression:"searchProjects.projectName"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"所属竞赛",prop:"suoshujingsai"}},[a("el-select",{attrs:{placeholder:"所属竞赛"},model:{value:e.searchProjects.compId,callback:function(t){e.$set(e.searchProjects,"compId",t)},expression:"searchProjects.compId"}},[e._l(e.allComp,(function(e){return a("el-option",{key:e.id,attrs:{label:e.compName,value:e.id}})})),a("el-option",{attrs:{label:"",value:""}})],2)],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"审核状态",prop:"zhuangtai"}},[a("el-select",{attrs:{placeholder:"请选择项目状态"},model:{value:e.searchProjects.applyState,callback:function(t){e.$set(e.searchProjects,"applyState",t)},expression:"searchProjects.applyState"}},[a("el-option",{attrs:{label:"未审核",value:"0"}}),a("el-option",{attrs:{label:"审核通过",value:"1"}}),a("el-option",{attrs:{label:"审核驳回",value:"2"}}),a("el-option",{attrs:{label:"",value:""}})],1)],1)],1),a("el-col",{attrs:{span:3}},[a("el-button",{staticStyle:{float:"right"},attrs:{type:"primary",size:"medium",icon:"el-icon-search"},on:{click:function(t){return e.getMoreSearchAllProjects()}}},[e._v("筛选 / 查询")])],1),a("el-col",{attrs:{span:4}},[a("el-button",{staticStyle:{float:"right"},attrs:{type:"primary",size:"medium",icon:"el-icon-search"},on:{click:function(t){return e.getAllProjects()}}},[e._v("查看全部")])],1)],1)],1)],1),a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.tableData.slice((e.currentPage-1)*e.pagesize,e.currentPage*e.pagesize),height:"480px",stripe:"",border:"","default-sort":{prop:"date",order:"descending"}}},[a("el-table-column",{attrs:{align:"center",label:"项目编号",width:"80px",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                  "+e._s(t.$index+1)+"\n                ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"项目名称",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"点击查看项目信息",placement:"top-end"}},[a("el-button",{attrs:{type:"text"},on:{click:function(a){return e.showProjectInfo(t.row.id)}}},[e._v(e._s(t.row.projectName))])],1)]}}])}),a("el-table-column",{attrs:{align:"center",prop:"comp.compName",label:"所属竞赛",sortable:""}}),a("el-table-column",{attrs:{align:"center",label:"参赛人员"},scopedSlots:e._u([{key:"default",fn:function(t){return[1==t.row.applyState?a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.getProjectJoinPersons(t.row)}}},[e._v("人员查看")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"指导老师",width:"120px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.createByTeacher.nickname))])]}}])}),a("el-table-column",{attrs:{align:"center",prop:"pay",label:"预算",sortable:""}}),a("el-table-column",{attrs:{align:"center",prop:"applyState",label:"状态",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[0==t.row.applyState?a("el-tag",{attrs:{type:"danger","disable-transitions":""}},[e._v("审核未通过")]):e._e(),1==t.row.applyState?a("el-tag",{attrs:{type:1===t.row.applyState?"success":"warning","disable-transitions":""}},[e._v("已审核")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",prop:"comp.compState",label:"所属竞赛状态",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{effect:"dark",type:0===t.row.comp.compState?"info":1===t.row.comp.compState?"primary":2===t.row.comp.compState?"success":3===t.row.comp.compState?"warning":"danger","disable-transitions":""}},[0==t.row.comp.compState?a("span",[e._v("规划中")]):e._e(),1==t.row.comp.compState?a("span",[e._v("报名中")]):e._e(),2==t.row.comp.compState?a("span",[e._v("备赛中")]):e._e(),3==t.row.comp.compState?a("span",[e._v("比赛中")]):e._e(),4==t.row.comp.compState?a("span",[e._v("已结束")]):e._e()])]}}])})],1),a("div",{attrs:{id:"fenye"}},[a("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:e.tableData.length,"page-size":15,"current-page":e.currentPage},on:{"current-change":e.current_change,"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t}}})],1)],1)])],1)],1)]),a("el-dialog",{attrs:{title:"项目成员",visible:e.projectPersonInfo},on:{"update:visible":function(t){e.projectPersonInfo=t}}},[a("el-table",{attrs:{data:e.projectJoinTeacher}},[a("el-table-column",{attrs:{property:"nickname",label:"教师姓名"}}),a("el-table-column",{attrs:{property:"username",label:"用户名"}}),a("el-table-column",{attrs:{property:"college.nickname",label:"所属院系"}}),a("el-table-column",{attrs:{label:"身份"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.id==e.createProjectTeacherId?a("span",[a("el-tag",{attrs:{type:"success"}},[e._v("带队老师")])],1):e._e(),t.row.id!=e.createProjectTeacherId?a("span",[e._v("\n            指导老师\n          ")]):e._e()]}}])})],1),a("el-table",{attrs:{data:e.projectJoinStudent}},[a("el-table-column",{attrs:{property:"nickname",label:"学生姓名"}}),a("el-table-column",{attrs:{property:"username",label:"学号"}}),a("el-table-column",{attrs:{property:"college.nickname",label:"所属院系"}}),a("el-table-column",{attrs:{label:"身份"}},[e._v("参赛学员")])],1)],1),a("el-dialog",{attrs:{title:"项目信息",visible:e.projectVisible,width:"70%"},on:{"update:visible":function(t){e.projectVisible=t}}},[a("el-table",{attrs:{stripe:"",border:"",data:e.projectInfo}},[a("el-table-column",{attrs:{align:"center",label:"项目编号",prop:"id"}}),a("el-table-column",{attrs:{align:"center",prop:"projectName",label:"项目名称"}}),a("el-table-column",{attrs:{align:"center",prop:"comp.compName",label:"所属竞赛"}}),a("el-table-column",{attrs:{align:"center",label:"参与学生人数"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.countStudent<t.row.comp.maxCountStudent?a("span",[e._v(e._s(t.row.countStudent)+"人")]):e._e(),t.row.countStudent>=t.row.comp.maxCountStudent?a("span",[e._v(e._s(t.row.countStudent)+"人(满员)")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"参与教师人数"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.countTeacher<t.row.comp.maxCountTeacher?a("span",[e._v(e._s(t.row.countTeacher)+"人")]):e._e(),t.row.countTeacher>=t.row.comp.maxCountTeacher?a("span",[e._v(e._s(t.row.countTeacher)+"人(满员)")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",prop:"createByTeacher.nickname",label:"指导老师"}}),a("el-table-column",{attrs:{align:"center",prop:"createByTeacher.username",label:"指导老师用户名"}}),a("el-table-column",{attrs:{align:"center",label:"预算"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.pay)+"元")])]}}])})],1)],1)],1)},r=[],n=a("56d7"),c={name:"admin_project",data:function(){return{allComp:[],projectVisible:!1,projectInfo:[],activeName:"竞赛项目列表",createProjectTeacherId:"",loading:!0,pagesize:15,currentPage:1,tableData:[],xinxiDialogFormVisible:!1,projectPersonInfo:!1,projectJoinStudent:[],projectJoinTeacher:[],searchProjects:{projectName:"",compId:"",applyState:""}}},mounted:function(){this.getAllComp(),this.getAllProjects()},methods:{getAllComp:function(){var e=this;e.$axios.post(n["default"]+"/comp/getAllComps").then((function(t){e.allComp=t.data}))},getMoreSearchAllProjects:function(){var e=this,t=new FormData;t.append("projectName",e.searchProjects.projectName),t.append("comp.id",e.searchProjects.compId),t.append("applyState",e.searchProjects.applyState),this.$axios.post(n["default"]+"/project/getMoreSearchAllProjects",t).then((function(t){e.collegeProjectData=t.data})).catch((function(t){e.$message.error("系统错误，操作失败，请稍后再试!")}))},current_change:function(e){this.currentPage=e},getProjectJoinPersons:function(e){var t=e.id;this.createProjectTeacherId=e.createByTeacher.id,this.projectPersonInfo=!0,this.getProjectJoinTeacher(t),this.getProjectJoinStudent(t)},getProjectJoinTeacher:function(e){var t=this;t.$axios.get(n["default"]+"/project/getProjectJoinTeacher/"+e).then((function(e){t.projectJoinTeacher=e.data}))},getProjectJoinStudent:function(e){var t=this;t.$axios.get(n["default"]+"/project/getProjectJoinStudent/"+e).then((function(e){t.projectJoinStudent=e.data}))},"筛选学院":function(e){var t=this;this.$axios.get("http://localhost:8088/project/selectProjectBySchoolName/?name=".concat(e)).then((function(e){t.tableData=e.data})).catch((function(e){}))},"筛选状态":function(e){var t=this;this.$axios.get("http://localhost:8088/project/selectProjectByZhuangtai/?zhuangtai=".concat(e)).then((function(e){t.tableData=e.data})).catch((function(e){}))},getAllProjects:function(){var e=this;this.$axios.get(n["default"]+"/admin/getAllProjects").then((function(t){e.tableData=t.data,e.proNum=t.data.length,e.loading=!1})).catch((function(t){e.loading=!1}))},"关联查询筛选状态":function(e){var t=this;this.$axios.get("http://localhost:8088/project/relationProAndCompByZtV/?zhuangtai=".concat(e,"&visible=1")).then((function(e){t.tableData=e.data})).catch((function(e){}))},handleClick:function(e,t){},showProjectInfo:function(e){var t=this;t.projectVisible=!0,t.$axios.get(n["default"]+"/teacher/getProjectById/"+e).then((function(e){var a=[];a.push(e.data),t.projectInfo=a}))}},watch:{activeName:function(e,t){var a=this;switch(a.tableData=[],a.activeName){case"竞赛项目审核":a.关联查询筛选状态("待审核");break;case"竞赛项目列表":a.getAllProjects();break}}}},l=c,s=(a("beb9"),a("2877")),i=Object(s["a"])(l,o,r,!1,null,null,null);t["default"]=i.exports},"7fc6":function(e,t,a){},beb9:function(e,t,a){"use strict";a("7fc6")}}]);
//# sourceMappingURL=chunk-9c1c3d46.ac79fe1f.js.map