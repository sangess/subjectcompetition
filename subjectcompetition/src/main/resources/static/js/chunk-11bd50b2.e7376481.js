(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-11bd50b2"],{4186:function(e,t,a){"use strict";a("b3c5")},b03d:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"teacher_comp"}},[a("el-card",{staticStyle:{"margin-bottom":"20px",height:"100%"}},[a("div",{attrs:{slot:"header"},slot:"header"},[a("el-row",{attrs:{gutter:10}},[a("el-col",{attrs:{span:8}},[a("span",[e._v("竞赛列表")])]),a("el-col",{attrs:{span:16}},[a("el-button",{staticStyle:{float:"right"},attrs:{type:"text"},on:{click:function(t){e.导出为Excel("全部竞赛")}}},[e._v("导出为Excel")])],1)],1)],1),a("div",[a("el-row",{attrs:{gutter:10}},[a("el-form",{ref:"searchFormData",attrs:{model:e.searchFormData,"label-width":"80px"}},[a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"竞赛名称",prop:"compName"}},[a("el-input",{model:{value:e.searchFormData.compName,callback:function(t){e.$set(e.searchFormData,"compName",t)},expression:"searchFormData.compName"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"主办单位",prop:"organizer"}},[a("el-input",{model:{value:e.searchFormData.organizer,callback:function(t){e.$set(e.searchFormData,"organizer",t)},expression:"searchFormData.organizer"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"竞赛级别",prop:"level"}},[a("el-select",{attrs:{placeholder:"请选择竞赛级别"},model:{value:e.searchFormData.level,callback:function(t){e.$set(e.searchFormData,"level",t)},expression:"searchFormData.level"}},[a("el-option",{attrs:{label:"国家级",value:"0"}}),a("el-option",{attrs:{label:"省级",value:"1"}}),a("el-option",{attrs:{label:"校级",value:"2"}}),a("el-option",{attrs:{label:"全部",value:""}})],1)],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"所属学科",prop:"subjectType"}},[a("el-input",{model:{value:e.searchFormData.subjectType,callback:function(t){e.$set(e.searchFormData,"subjectType",t)},expression:"searchFormData.subjectType"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"竞赛地点",prop:"place"}},[a("el-input",{model:{value:e.searchFormData.place,callback:function(t){e.$set(e.searchFormData,"place",t)},expression:"searchFormData.place"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"竞赛状态",prop:"compState"}},[a("el-select",{attrs:{placeholder:"请选择竞赛状态"},model:{value:e.searchFormData.compState,callback:function(t){e.$set(e.searchFormData,"compState",t)},expression:"searchFormData.compState"}},[a("el-option",{attrs:{label:"规划中",value:"0"}}),a("el-option",{attrs:{label:"报名中",value:"1"}}),a("el-option",{attrs:{label:"备赛中",value:"2"}}),a("el-option",{attrs:{label:"进行中",value:"3"}}),a("el-option",{attrs:{label:"已结束",value:"4"}}),a("el-option",{attrs:{label:"全部",value:""}})],1)],1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:3,offset:21}},[a("el-button",{staticStyle:{float:"right"},attrs:{type:"primary",size:"medium",icon:"el-icon-search"},on:{click:function(t){return e.moreSearch()}}},[e._v("筛选 / 查询")])],1)],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"elTable",staticStyle:{width:"100%"},attrs:{id:"全部竞赛",data:e.tableData.slice((e.currentPage-1)*e.pagesize,e.currentPage*e.pagesize),stripe:"",border:"",height:"400px","default-sort":{prop:"date",order:"descending"}},on:{"row-click":e.getDetails}},[a("el-table-column",{attrs:{align:"center",prop:"id",label:"#",width:"50px",sortable:""}}),a("el-table-column",{attrs:{align:"center",prop:"compName",label:"竞赛名称",sortable:""}}),a("el-table-column",{attrs:{align:"center",prop:"organizer",label:"主办单位",sortable:""}}),a("el-table-column",{attrs:{align:"center",prop:"level",label:"级 别",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[0==t.row.level?a("p",[e._v("国家级")]):e._e(),1==t.row.level?a("p",[e._v("省级")]):e._e(),2==t.row.level?a("p",[e._v("校级")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",prop:"subjectType",label:"所属学科",sortable:""}}),a("el-table-column",{attrs:{align:"center",prop:"occurrenceTime",label:"竞赛时间",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s(e._f("dataTime")(t.row.occurrenceTime))+"\n        ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"place",label:"竞赛地点",sortable:""}}),a("el-table-column",{attrs:{align:"center",prop:"竞赛项目",label:"竞赛项目"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-link",{attrs:{type:"primary",underline:!1},on:{click:function(a){return e.checkProjects(t.row.id)}}},[a("i",{staticClass:"el-icon-view el-icon--right"}),e._v(" 参赛项目列表")])]}}])}),a("el-table-column",{attrs:{align:"center",prop:"compState",label:"状态",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{effect:"dark",type:0===t.row.compState?"info":1===t.row.compState?"primary":2===t.row.compState?"success":3===t.row.compState?"warning":"danger","disable-transitions":""}},[0==t.row.compState?a("span",[e._v("规划中")]):e._e(),1==t.row.compState?a("span",[e._v("报名中")]):e._e(),2==t.row.compState?a("span",[e._v("备赛中")]):e._e(),3==t.row.compState?a("span",[e._v("比赛中")]):e._e(),4==t.row.compState?a("span",[e._v("已结束")]):e._e()])]}}])}),a("el-table-column",{attrs:{align:"center",label:"申请状态",width:"80px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:3==t.row.applyState?"申请项目":1==t.row.applyState||4==t.row.applyState?"学院正在申请，请耐心等待":"学院尚未申请竞赛，无法申请项目",placement:"top"}},[a("el-button",{attrs:{icon:3==t.row.applyState?"el-icon-folder-add":"el-icon-folder-delete",type:3==t.row.applyState?"success":"info",disabled:3!=t.row.applyState,circle:""},on:{click:function(a){e.applyProjectFormVisible=!0,e.compId=t.row.id}}})],1)]}}])})],1),a("div",{attrs:{id:"fenye"}},[a("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:e.tableData.length,"page-size":12,"current-page":e.currentPage},on:{"current-change":e.current_change,"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t}}})],1)],1),a("el-dialog",{attrs:{title:"项目申报",visible:e.applyProjectFormVisible},on:{"update:visible":function(t){e.applyProjectFormVisible=t}}},[a("el-form",{ref:"applyProjectForm",staticStyle:{width:"300px"},attrs:{model:e.applyProjectForm,"label-width":e.formLabelWidth,"label-position":"left",rules:e.rules}},[a("el-form-item",{attrs:{label:"申请老师"}},[a("el-input",{attrs:{value:this.$store.state.nickname,disabled:"",autocomplete:"off"}})],1),a("el-form-item",{attrs:{label:"项目名称",prop:"projectName"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.applyProjectForm.projectName,callback:function(t){e.$set(e.applyProjectForm,"projectName",t)},expression:"applyProjectForm.projectName"}})],1),a("el-form-item",{attrs:{label:"项目预支",prop:"pay"}},[a("el-input",{attrs:{type:"age",autocomplete:"off"},model:{value:e.applyProjectForm.pay,callback:function(t){e.$set(e.applyProjectForm,"pay",e._n(t))},expression:"applyProjectForm.pay"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.applyProjectFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.applyProject("applyProjectForm")}}},[e._v("确认申报")])],1)],1),a("el-dialog",{attrs:{title:"竞赛项目列表",visible:e.projectVisible},on:{"update:visible":function(t){e.projectVisible=t}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.projectData,stripe:"",border:"","max-height":"500"}},[a("el-table-column",{attrs:{align:"center",prop:"id",label:"项目编号",width:"80px"}}),a("el-table-column",{attrs:{align:"center",prop:"projectName",label:"项目名称"}}),a("el-table-column",{attrs:{align:"center",prop:"createByTeacher.college.nickname",label:"所属学院"}}),a("el-table-column",{attrs:{align:"center",prop:"createByTeacher.nickname",label:"指导老师",width:"120px"}}),a("el-table-column",{attrs:{align:"center",prop:"pay",label:"预算"}}),a("el-table-column",{attrs:{align:"center",prop:"applyState",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:1===t.row.applyState?"success":"warning","disable-transitions":""}},[e._v(e._s(1==t.row.applyState?"已审核":"未审核"))])]}}])})],1)],1)],1)},l=[],o=a("56d7"),c=a("2f14"),n={inject:["reload"],name:"teacher_comp",data:function(){return{compId:"",formLabelWidth:"80px",applyProjectForm:{},loading:!0,pagesize:12,currentPage:1,applyProjectFormVisible:!1,projectVisible:!1,projectData:[],rowIndex:-1,rowData:{id:0,compName:"",organizer:"",level:"",subjectType:"",occurrenceTime:"",place:"",compState:""},tableData:[],searchFormData:{compName:"",organizer:"",level:"",subjectType:"",place:"",compState:""},college_comp:[],rules:{projectName:[{required:!0,message:"请输入项目名称",trigger:"blur"}],pay:[{required:!0,message:"项目预支不能为空",trigger:"blur"},{type:"number",message:"项目预支必须为数字值",trigger:"blur"}]},time:""}},mounted:function(){this.getAllComps(),this.time=setInterval(this.getAllComps,1500)},methods:{moreSearch:function(){var e=this,t=new FormData;t.append("compName",e.searchFormData.compName),t.append("organizer",e.searchFormData.organizer),t.append("level",e.searchFormData.level),t.append("subjectType",e.searchFormData.subjectType),t.append("place",e.searchFormData.place),t.append("compState",e.searchFormData.compState),this.$axios.post(o["default"]+"/comp/moreSearch",t).then((function(t){e.tableData=t.data})).catch((function(t){e.$message.error("系统错误，操作失败，请稍后再试!")}))},applyProject:function(e){var t=this,a=this,r=(a.$store.state.id,new FormData);r.append("projectName",a.applyProjectForm.projectName),r.append("pay",a.applyProjectForm.pay),r.append("compId",a.compId),r.append("teacherId",a.$store.state.id),a.$refs[e].validate((function(e){if(!e)return a.$message.error("请正确填写表格！"),!1;t.$axios.post(o["default"]+"/teacher/applyJoinComp/",r).then((function(e){var t=e.data.code;a.$message({message:e.data.message,type:200==t?"success":"warning"}),a.reload()}))}))},getDetails:function(e){this.rowData=JSON.parse(JSON.stringify(e))},checkProjects:function(e){var t=this;t.projectVisible=!0,this.$axios.get(o["default"]+"/comp/getProjectLists/"+e).then((function(e){t.projectData=e.data})).catch((function(e){}))},current_change:function(e){this.currentPage=e},handleClose:function(e){this.$confirm("确认关闭？").then((function(t){e()})).catch((function(e){}))},getAllComps:function(){var e=this;e.loading=!0;var t=e.$store.state.id;this.$axios.get(o["default"]+"/teacher/getCollegeCompListByTeacherId/"+t).then((function(t){e.tableData=t.data,e.loading=!1})).catch((function(t){e.loading=!1}))},"导出为Excel":function(e){var t=e;Object(c["a"])(e,t)}},computed:{},filters:{dataTime:function(e){if(void 0==e)return"-";if(e=new Date(e),e instanceof Date){var t=e,a=t.getFullYear(),r=t.getMonth()+1,l=t.getDate()<10?"0"+t.getDate():t.getDate(),o=a+"年"+r+"月 "+l+"日";return o}return"-"}},beforeDestroy:function(){clearInterval(this.time)}},s=n,p=(a("4186"),a("2877")),i=Object(p["a"])(s,r,l,!1,null,null,null);t["default"]=i.exports},b3c5:function(e,t,a){}}]);
//# sourceMappingURL=chunk-11bd50b2.e7376481.js.map