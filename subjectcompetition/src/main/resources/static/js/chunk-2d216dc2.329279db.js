(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d216dc2"],{c3ea:function(t,e,r){"use strict";r.r(e);var a=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("el-card",[r("el-tabs",{attrs:{"tab-position":t.tabPosition},model:{value:t.unOrEd,callback:function(e){t.unOrEd=e},expression:"unOrEd"}},[r("el-tab-pane",{attrs:{label:"未评分的作品",name:"un"}},[r("el-table",{staticStyle:{width:"100%"},attrs:{data:t.unMarkProjects}},[r("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("el-form",{staticStyle:{"background-color":"rgba(146,255,152,0.58)","border-radius":"20px"},attrs:{"label-position":"right"}},t._l(e.row.workFiles,(function(e,a){return r("el-form-item",{key:e.id,attrs:{inline:""}},[r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:2}},[r("span",{staticStyle:{color:"#1402bf",float:"right"}},[t._v(t._s(t.fileTip+String(a+1)))])]),r("el-col",{attrs:{span:10}},[r("span",[t._v(t._s(e.fileName))])]),r("el-col",{attrs:{span:3}},[r("a",{attrs:{href:t.api+"/file/downloadFile/"+e.id}},[r("el-link",{attrs:{icon:"el-icon-download",underline:!1}},[t._v("下载附件")])],1)])],1)],1)})),1)]}}])}),r("el-table-column",{attrs:{label:"竞赛 ID",prop:"project.comp.id"}}),r("el-table-column",{attrs:{label:"竞赛名",prop:"project.comp.compName"}}),r("el-table-column",{attrs:{label:"项目名",prop:"project.projectName"}}),r("el-table-column",{attrs:{label:"作品名",prop:"workName"}}),r("el-table-column",{attrs:{label:"评分"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("el-input",{staticStyle:{width:"50px","margin-right":"20px"},attrs:{size:"mini",id:t.work+e.row.id},model:{value:t.input,callback:function(e){t.input=e},expression:"input"}}),r("el-popconfirm",{attrs:{title:"确认评分吗？"},on:{confirm:function(r){return t.markWork(e.row.id)}}},[r("el-button",{attrs:{slot:"reference",type:"success",size:"mini"},slot:"reference"},[t._v("保存")])],1)]}}])})],1)],1),r("el-tab-pane",{attrs:{label:"已评分的比赛",name:"ed"}},[r("el-table",{staticStyle:{width:"100%"},attrs:{data:t.markWorkProjects}},[r("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("el-form",{staticStyle:{"background-color":"rgba(146,255,152,0.58)","border-radius":"20px"},attrs:{"label-position":"right"}},t._l(e.row.projectList,(function(e,a){return r("el-form-item",{key:e.id,attrs:{inline:""}},[r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:8}},[r("span",{staticStyle:{color:"#1402bf",float:"right"}},[t._v("名次："+t._s(String(a+1)))])]),r("el-col",{attrs:{span:8}},[r("span",[t._v("作品名："+t._s(e.workName))])]),r("el-col",{attrs:{span:8}},[r("span",[t._v("院系评分:"+t._s(e.score))])])],1)],1)})),1)]}}])}),r("el-table-column",{attrs:{label:"竞赛 ID",prop:"compId"}}),r("el-table-column",{attrs:{label:"竞赛名",prop:"compName"}}),r("el-table-column",{attrs:{label:"截至报名日期",prop:"notApplyTime"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n            "+t._s(t._f("dataTime")(e.row.notApplyTime))+"\n          ")]}}])}),r("el-table-column",{attrs:{label:"比赛时间",prop:"occurrenceTime"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n            "+t._s(t._f("dataTime")(e.row.occurrenceTime))+"\n          ")]}}])})],1)],1)],1)],1)},o=[],l=r("56d7"),n={name:"college_work",inject:["reload"],data:function(){return{work:"work",input:0,fileTip:"附件",api:l["default"],tabPosition:"left",unOrEd:"un",unMarkProjects:[],markWorkProjects:[]}},mounted:function(){this.getUnMarkProjects(),this.getMarkWorkProjects()},methods:{getMarkWorkProjects:function(){var t=this;t.$axios.get(l["default"]+"/college/getMarkWorkProjects/"+t.$store.state.id).then((function(e){t.markWorkProjects=e.data,console.log(t.markWorkProjects)}))},getUnMarkProjects:function(){var t=this;t.$axios.get(l["default"]+"/college/getUnMarkProjects/"+t.$store.state.id).then((function(e){t.unMarkProjects=e.data}))},markWork:function(t){var e=this,r=this.work+t,a=document.getElementById(r).value;a<=0?e.$message.error(a+"分实在太低了！请重新打分"):a>=100?e.$message.error("请在1-99分中评分！"):e.$axios.get(l["default"]+"/college/markWork/"+a+"/"+t).then((function(t){e.$message({type:t.data.code="success",message:t.data.message}),e.reload()}))}},filters:{dataTime:function(t){if(void 0==t)return"-";if(t=new Date(t),t instanceof Date){var e=t,r=e.getFullYear(),a=e.getMonth()+1,o=e.getDate()<10?"0"+e.getDate():e.getDate(),l=r+"年"+a+"月 "+o+"日";return l}return"-"}}},s=n,i=r("2877"),c=Object(i["a"])(s,a,o,!1,null,"3afc5206",null);e["default"]=c.exports}}]);
//# sourceMappingURL=chunk-2d216dc2.329279db.js.map