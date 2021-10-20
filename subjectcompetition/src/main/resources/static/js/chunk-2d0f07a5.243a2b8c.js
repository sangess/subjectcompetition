(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0f07a5"],{"9d15":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-card",[a("el-tabs",{model:{value:e.workOrUpload,callback:function(t){e.workOrUpload=t},expression:"workOrUpload"}},[a("el-tab-pane",{attrs:{label:"作品提交",name:"upload"}},[a("el-form",{ref:"form",staticStyle:{width:"520px"},attrs:{model:e.form,"label-width":"120px",rules:e.rules}},[a("el-form-item",{attrs:{label:"作品名",prop:"workName"}},[a("el-input",{attrs:{name:"workName"},model:{value:e.form.workName,callback:function(t){e.$set(e.form,"workName",t)},expression:"form.workName"}})],1),a("el-form-item",{attrs:{label:"上交的项目",prop:"projectId"}},[a("el-select",{attrs:{name:"projectId",placeholder:"请选择需要上交的项目"},model:{value:e.form.projectId,callback:function(t){e.$set(e.form,"projectId",t)},expression:"form.projectId"}},e._l(e.myProjects,(function(e){return a("el-option",{key:e.id,attrs:{label:e.projectName,value:e.id,disabled:e.comp.compState>2}})})),1)],1),a("el-form-item",{attrs:{label:"上传文件"}},[a("el-upload",{ref:"upload",attrs:{name:"files",action:"/url",limit:5,multiple:"","file-list":e.fileList,"with-credentials":!0,"on-change":e.uploadChange,"on-remove":e.handleRemove,"auto-upload":!1}},[a("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")]),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("最多上传5个文件，单个文件大小不得超过100MB,一共大小不得超过200MB")])],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitUpload("form")}}},[e._v("确认上交")])],1)],1)],1),a("el-tab-pane",{attrs:{label:"作品管理",name:"work"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.unOrEd,callback:function(t){e.unOrEd=t},expression:"unOrEd"}},[a("el-tab-pane",{attrs:{label:"已提交",name:"Ed"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.handOutWorks}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-form",{staticStyle:{"background-color":"rgba(146,255,152,0.58)","border-radius":"20px"},attrs:{"label-position":"right"}},e._l(t.row.workFiles,(function(t,o){return a("el-form-item",{key:t.id,attrs:{inline:""}},[a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:2}},[a("span",{staticStyle:{color:"#1402bf",float:"right"}},[e._v(e._s(e.fileTip+String(o+1)))])]),a("el-col",{attrs:{span:10}},[a("span",[e._v(e._s(t.fileName))])]),a("el-col",{attrs:{span:3}},[a("a",{attrs:{href:e.api+"/file/downloadFile/"+t.id}},[a("el-link",{attrs:{icon:"el-icon-download",underline:!1}},[e._v("下载附件")])],1)]),a("el-col",{attrs:{span:3}},[a("el-link",{attrs:{icon:"el-icon-edit",underline:!1},on:{click:function(a){e.editId=t.id,e.dialogTableVisible=!0}}},[e._v("修改")])],1),a("el-col",{attrs:{span:3}},[a("el-popconfirm",{attrs:{title:"删除该文件？操作不可逆！"},on:{confirm:function(a){return e.deleteWorkFile(t.id)}}},[a("el-link",{attrs:{slot:"reference",icon:"el-icon-delete",underline:!1},slot:"reference"},[e._v("删除")])],1)],1)],1)],1)})),1)]}}])}),a("el-table-column",{attrs:{label:"竞赛 ID",prop:"project.comp.id"}}),a("el-table-column",{attrs:{label:"竞赛名",prop:"project.comp.compName"}}),a("el-table-column",{attrs:{label:"项目名",prop:"project.projectName"}}),a("el-table-column",{attrs:{label:"作品名",prop:"workName"}}),a("el-table-column",{attrs:{label:"作品得分",prop:"score"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(t.row.score)+e._s(0==t.row.ifMark?"(未打分)":"")+"\n              ")]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"待提交",name:"un"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.unHandOutWorks}},[a("el-table-column",{attrs:{label:"竞赛 ID",prop:"comp.id"}}),a("el-table-column",{attrs:{label:"竞赛名",prop:"comp.compName"}}),a("el-table-column",{attrs:{label:"项目名",prop:"projectName"}}),a("el-table-column",{attrs:{label:"截至报名日期",prop:"comp.notApplyTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(e._f("dataTime")(t.row.comp.notApplyTime))+"\n              ")]}}])}),a("el-table-column",{attrs:{label:"比赛时间",prop:"comp.occurrenceTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(e._f("dataTime")(t.row.comp.occurrenceTime))+"\n              ")]}}])}),a("el-table-column",{attrs:{label:"状态",prop:"projectName"}},[a("el-button",{attrs:{type:"warning",plain:"",size:"mini"},on:{click:function(t){e.workOrUpload="upload"}}},[e._v("尚未提交作品")])],1)],1)],1),a("el-tab-pane",{attrs:{label:"已评分",name:"mark"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.markWorks}},[a("el-table-column",{attrs:{label:"竞赛 ID",prop:"project.comp.id"}}),a("el-table-column",{attrs:{label:"竞赛名",prop:"project.comp.compName"}}),a("el-table-column",{attrs:{label:"项目名",prop:"project.projectName"}}),a("el-table-column",{attrs:{label:"作品名",prop:"workName"}}),a("el-table-column",{attrs:{label:"作品得分",prop:"score"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(t.row.score)+e._s(0==t.row.score?"(未打分)":"")+"\n              ")]}}])})],1)],1)],1)],1)],1),a("el-dialog",{attrs:{title:"修改文件",visible:e.dialogTableVisible},on:{"update:visible":function(t){e.dialogTableVisible=t}}},[a("el-upload",{attrs:{name:"edit",action:"/url",limit:1,file:e.file,"with-credentials":!0,"on-change":e.editChange,"on-remove":e.handleRemove,"auto-upload":!1}},[a("el-button",{attrs:{size:"small",type:"primary"}},[e._v("选择文件")])],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogTableVisible=!1}}},[e._v("取消修改")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.editWorkFile()}}},[e._v("确认修改")])],1)],1)],1)},l=[],r=a("56d7"),n={inject:["reload"],name:"student_work",data:function(){return{markWorks:[],editId:"",file:"",dialogTableVisible:!1,api:r["default"],fileTip:"附件",handOutWorks:[],unHandOutWorks:[],workOrUpload:"upload",tabPosition:"left",unOrEd:"Ed",form:{workName:"",projectId:""},fileList:[],myProjects:[],rules:{workName:[{required:!0,message:"请填写作品名称",trigger:"blur"}],projectId:[{required:!0,message:"请选择提交的项目",trigger:"blur"}]}}},mounted:function(){this.showMyComps(),this.getHandOutWorks(),this.getUnHandOutWorks(),this.getWorksMark()},methods:{showMyComps:function(){var e=this,t=e.$store.state.id;e.$axios.get(r["default"]+"/project/getStudentProjects/"+t).then((function(t){e.myProjects=t.data}))},submitUpload:function(e){var t=this,a=new FormData;t.$refs[e].validate((function(e){if(!e)return t.$message.error("请正确填写作品信息！"),!1;a.append("workName",t.form.workName),a.append("projectId",t.form.projectId);for(var o=0;o<t.fileList.length;o++)a.append("uploadFile",t.fileList[o].raw);t.$axios({url:r["default"]+"/student/workUpload",method:"post",data:a,headers:{"Content-Type":"multipart/form-data"}}).then((function(e){t.$message({type:200==e.data.code?"success":"error",message:e.data.message}),t.reload(),t.workOrUpload="work"}))}))},uploadChange:function(e,t){this.fileList=t},editChange:function(e,t){var a=this;a.file=t},handleRemove:function(e,t){this.filelist=t},getHandOutWorks:function(){var e=this;e.$axios.get(r["default"]+"/student/getHandOutWorks/"+e.$store.state.id).then((function(t){e.handOutWorks=t.data}))},getUnHandOutWorks:function(){var e=this;e.$axios.get(r["default"]+"/student/getUnHandOutWorks/"+e.$store.state.id).then((function(t){e.unHandOutWorks=t.data,console.log(e.unHandOutWorks)}))},editWorkFile:function(e){var t=this;console.log(t.file[0].raw),console.log(t.editId);var a=new FormData;a.append("workFileId",t.editId),a.append("workFile",t.file[0].raw),t.$axios({url:r["default"]+"/student/editWork",method:"post",data:a,headers:{"Content-Type":"multipart/form-data"}}).then((function(e){t.$message({type:200==e.data.code?"success":"error",message:e.data.message}),t.reload(),t.workOrUpload="work"}))},deleteWorkFile:function(e){var t=this;t.$axios.get(r["default"]+"/student/deleteWorkFile/"+e).then((function(e){t.$message({type:200==e.data.code?"success":"error",message:e.data.message}),t.reload(),t.workOrUpload="work"}))},getWorksMark:function(){var e=this;e.$axios.get(r["default"]+"/student/getWorksMark/"+e.$store.state.id).then((function(t){e.markWorks=t.data}))}},filters:{dataTime:function(e){if(void 0==e)return"-";if(e=new Date(e),e instanceof Date){var t=e,a=t.getFullYear(),o=t.getMonth()+1,l=t.getDate()<10?"0"+t.getDate():t.getDate(),r=a+"年"+o+"月 "+l+"日";return r}return"-"}}},s=n,i=a("2877"),d=Object(i["a"])(s,o,l,!1,null,null,null);t["default"]=d.exports}}]);
//# sourceMappingURL=chunk-2d0f07a5.243a2b8c.js.map