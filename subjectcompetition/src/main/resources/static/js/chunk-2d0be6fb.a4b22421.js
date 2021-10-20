(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0be6fb"],{"2ff7":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"teacher_apply"}},[a("el-card",[a("div",{attrs:{slot:"header"},slot:"header"}),a("div",[a("el-tabs",{model:{value:e.collegeOrStudentOrTeacher,callback:function(t){e.collegeOrStudentOrTeacher=t},expression:"collegeOrStudentOrTeacher"}},[a("el-tab-pane",{attrs:{label:"学院申请",name:"college"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeApply,callback:function(t){e.activeApply=t},expression:"activeApply"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"undo"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.undoCollgeApplyList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"teacher.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{label:"目前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:"success"}},[e._v(e._s(1==t.row.applyOrJoin?"已发出申请":"已重新发出申请"))])]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"done"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.doneCollgeApplyList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"teacher.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{prop:"applyOrJoin",label:"处理情况"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:3==t.row.applyOrJoin?"success":"danger"}},[a("span",{domProps:{textContent:e._s(3==t.row.applyOrJoin?"申请已通过":"申请被驳回")}})]),2==t.row.applyOrJoin?a("el-tooltip",{staticClass:"item",staticStyle:{"margin-left":"50px"},attrs:{effect:"dark",content:"重新申请",placement:"right"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-refresh-right",circle:""},on:{click:function(a){e.applyProjectFormVisible=!0,e.compId=t.row.project.comp.id}}})],1):e._e()]}}])})],1)],1)],1)],1),a("el-tab-pane",{attrs:{label:"学生申请",name:"student"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeStudentApply,callback:function(t){e.activeStudentApply=t},expression:"activeStudentApply"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"undo"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.undoApplyProjectStudentList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"student.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{label:"目前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:"success"}},[e._v(e._s(1==t.row.applyOrJoin?"已发出申请":"已重新发出申请"))])]}}])}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"同意申请",placement:"top"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-circle-check",circle:""},on:{click:function(a){return e.applyStudentJoinProject(t.row.id)}}})],1),a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"驳回申请",placement:"top"}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-circle-close",circle:""},on:{click:function(a){return e.refuseStudentJoinProject(t.row.id)}}})],1)]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"done"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.doneApplyProjectStudentList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{label:"处理人"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.project.createByTeacher.nickname)+"老师")])]}}])}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{prop:"applyOrJoin",label:"处理情况"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:3==t.row.applyOrJoin?"success":"danger"}},[a("span",{domProps:{textContent:e._s(3==t.row.applyOrJoin?"申请已通过":"申请被驳回")}})])]}}])})],1)],1)],1)],1),a("el-tab-pane",{attrs:{label:"老师申请",name:"teacher"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeTeacherApply,callback:function(t){e.activeTeacherApply=t},expression:"activeTeacherApply"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"undo"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.undoApplyProjectTeacherList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{label:"申请人"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.teacher.nickname)+"老师")])]}}])}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{label:"目前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:"success"}},[e._v(e._s(1==t.row.applyOrJoin?"已发出申请":"已重新发出申请"))])]}}])}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.project.createByTeacher.id==e.teacherId?a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"同意申请",placement:"top"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-circle-check",circle:""},on:{click:function(a){return e.applyTeacherJoinProject(t.row.id)}}})],1):e._e(),t.row.project.createByTeacher.id==e.teacherId?a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"驳回申请",placement:"top"}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-circle-close",circle:""},on:{click:function(a){return e.refuseTeacherJoinProject(t.row.id)}}})],1):e._e()]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"done"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.doneApplyProjectTeacherList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{label:"处理人"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.project.createByTeacher.nickname)+"老师")])]}}])}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{prop:"applyOrJoin",label:"处理情况"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:3==t.row.applyOrJoin?"success":"danger"}},[a("span",{domProps:{textContent:e._s(3==t.row.applyOrJoin?"申请已通过":"申请驳回")}})]),t.row.project.createByTeacher.id!=e.teacherId&2==t.row.applyOrJoin?a("el-tooltip",{staticClass:"item",staticStyle:{"margin-left":"50px"},attrs:{effect:"dark",content:"重新申请",placement:"right"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-refresh-right",circle:""},on:{click:function(a){return e.teacherReApplyJoinProject(t.row.id)}}})],1):e._e()]}}])})],1)],1)],1)],1)],1),a("el-dialog",{attrs:{title:"项目重新申报",visible:e.applyProjectFormVisible},on:{"update:visible":function(t){e.applyProjectFormVisible=t}}},[a("el-form",{ref:"applyProjectForm",staticStyle:{width:"300px"},attrs:{model:e.applyProjectForm,"label-width":e.formLabelWidth,"label-position":"left",rules:e.rules}},[a("el-form-item",{attrs:{label:"申请老师"}},[a("el-input",{attrs:{value:this.$store.state.nickname,disabled:"",autocomplete:"off"}})],1),a("el-form-item",{attrs:{label:"项目名称",prop:"projectName"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.applyProjectForm.projectName,callback:function(t){e.$set(e.applyProjectForm,"projectName",t)},expression:"applyProjectForm.projectName"}})],1),a("el-form-item",{attrs:{label:"项目预支",prop:"pay"}},[a("el-input",{attrs:{type:"age",autocomplete:"off"},model:{value:e.applyProjectForm.pay,callback:function(t){e.$set(e.applyProjectForm,"pay",e._n(t))},expression:"applyProjectForm.pay"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.applyProjectFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.applyProject("applyProjectForm")}}},[e._v("确认申报")])],1)],1)],1)])],1)},o=[],r=(a("2f14"),a("56d7")),n={inject:["reload"],name:"teacher_apply",data:function(){return{applyProjectFormVisible:!1,formLabelWidth:"80px",activeStudentApply:"undo",activeTeacherApply:"undo",applyProjectForm:{},contentForm:{},teacherId:"",collegeOrStudentOrTeacher:"college",activeApply:"done",tabPosition:"left",undoCollgeApplyList:[],doneCollgeApplyList:[],undoApplyProjectStudentList:[],doneApplyProjectStudentList:[],undoApplyProjectTeacherList:[],doneApplyProjectTeacherList:[],rules:{projectName:[{required:!0,message:"请输入项目名称",trigger:"blur"}],pay:[{required:!0,message:"项目预支不能为空",trigger:"blur"},{type:"number",message:"项目预支必须为数字值",trigger:"blur"}]},time1:"",time2:"",time3:"",time4:"",time5:"",time6:""}},mounted:function(){this.teacherId=this.$store.state.id,this.getUndoCollgeApplyList(),this.getDoneCollgeApplyList(),this.getUndoApplyProjectStudentList(),this.getDoneApplyProjectStudentList(),this.getUndoApplyProjectTeacherList(),this.getDoneApplyProjectTeacherList(),this.time1=setInterval(this.getUndoCollgeApplyList,1500),this.time2=setInterval(this.getDoneCollgeApplyList,1500),this.time3=setInterval(this.getUndoApplyProjectStudentList,1500),this.time4=setInterval(this.getDoneApplyProjectStudentList,1500),this.time5=setInterval(this.getUndoApplyProjectTeacherList,1500),this.time6=setInterval(this.getDoneApplyProjectTeacherList,1500)},methods:{getUndoCollgeApplyList:function(){var e=this,t=e.$store.state.id;e.$axios.get(r["default"]+"/teacher/getUndoCollgeApplyList/"+t).then((function(t){e.undoCollgeApplyList=t.data}))},getDoneCollgeApplyList:function(){var e=this,t=e.$store.state.id;e.$axios.get(r["default"]+"/teacher/getDoneCollgeApplyList/"+t).then((function(t){e.doneCollgeApplyList=t.data}))},applyProject:function(e){var t=this,a=this,l=(a.$store.state.id,new FormData);l.append("projectName",a.applyProjectForm.projectName),l.append("pay",a.applyProjectForm.pay),l.append("compId",a.compId),l.append("teacherId",a.$store.state.id),a.$refs[e].validate((function(e){if(!e)return a.$message.error("请正确填写表格！"),!1;t.$axios.post(r["default"]+"/teacher/applyJoinComp/",l).then((function(e){var t=e.data.code;a.$message({message:e.data.message,type:200==t?"success":"warning"}),a.reload()}))}))},getUndoApplyProjectStudentList:function(){var e=this,t=e.$store.state.id;e.$axios.get(r["default"]+"/teacher/getUndoApplyProjectStudentList/"+t).then((function(t){e.undoApplyProjectStudentList=t.data}))},applyStudentJoinProject:function(e){var t=this;t.$axios.get(r["default"]+"/teacher/applyStudentJoinProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"warning"}),t.reload()}))},refuseStudentJoinProject:function(e){var t=this;t.$axios.get(r["default"]+"/teacher/refuseStudentJoinProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"warning"}),t.reload()}))},getDoneApplyProjectStudentList:function(){var e=this,t=e.$store.state.id;e.$axios.get(r["default"]+"/teacher/getDoneApplyProjectStudentList/"+t).then((function(t){e.doneApplyProjectStudentList=t.data}))},getUndoApplyProjectTeacherList:function(){var e=this,t=e.$store.state.id;e.$axios.get(r["default"]+"/teacher/getUndoApplyProjectTeacherList/"+t).then((function(t){e.undoApplyProjectTeacherList=t.data}))},getDoneApplyProjectTeacherList:function(){var e=this,t=e.$store.state.id;e.$axios.get(r["default"]+"/teacher/getDoneApplyProjectTeacherList/"+t).then((function(t){e.doneApplyProjectTeacherList=t.data}))},teacherReApplyJoinProject:function(e){var t=this;t.$axios.get(r["default"]+"/teacher/teacherReApplyJoinProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"warning"}),t.reload()}))},applyTeacherJoinProject:function(e){var t=this;t.$axios.get(r["default"]+"/teacher/applyTeacherJoinProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"warning"}),t.reload()}))},refuseTeacherJoinProject:function(e){var t=this;t.$axios.get(r["default"]+"/teacher/refuseTeacherJoinProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"warning"}),t.reload()}))}},filters:{dataTime:function(e){if(void 0==e)return"-";if(e=new Date(e),e instanceof Date){var t=e,a=t.getFullYear(),l=t.getMonth()+1,o=t.getDate()<10?"0"+t.getDate():t.getDate(),r=a+"年"+l+"月 "+o+"日";return r}return"-"}},beforeDestroy:function(){clearInterval(this.time1),clearInterval(this.time2),clearInterval(this.time3),clearInterval(this.time4),clearInterval(this.time5),clearInterval(this.time6)}},p=n,s=a("2877"),c=Object(s["a"])(p,l,o,!1,null,null,null);t["default"]=c.exports}}]);
//# sourceMappingURL=chunk-2d0be6fb.a4b22421.js.map