(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0c8a27"],{"565e":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"college_xinxi"}},[a("el-card",[a("div",{attrs:{slot:"header"},slot:"header"}),a("div",[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeXinxiName,callback:function(t){e.activeXinxiName=t},expression:"activeXinxiName"}},[a("el-tab-pane",{attrs:{label:"消息列表",name:"消息列表"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{border:"",stripe:"",data:e.xinxiTable}},[a("el-table-column",{attrs:{label:"#",width:"60"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"title",label:"标题"}}),a("el-table-column",{attrs:{prop:"content",label:"内容"}}),a("el-table-column",{attrs:{prop:"sendName",label:"发送者"}}),a("el-table-column",{attrs:{prop:"sendUsername",label:"发送者用戶名"}}),a("el-table-column",{attrs:{label:"时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{staticClass:"el-icon-time"}),a("span",{staticStyle:{"margin-left":"10px"}},[e._v(e._s(e._f("dataTime")(t.row.date)))])]}}])}),a("el-table-column",{attrs:{label:"操作",width:"125"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",icon:"el-icon-s-flag",plain:"",size:"small"},on:{click:function(a){return e.remarkMsg(t.row.id)}}},[e._v("标记已读")])]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已读",name:"已读"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{border:"",stripe:"",data:e.xinxiTable}},[a("el-table-column",{attrs:{label:"#",width:"60"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"title",label:"标题"}}),a("el-table-column",{attrs:{prop:"content",label:"内容"}}),a("el-table-column",{attrs:{prop:"sendName",label:"发送者"}}),a("el-table-column",{attrs:{prop:"sendUsername",label:"发送者用戶名"}}),a("el-table-column",{attrs:{label:"时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{staticClass:"el-icon-time"}),a("span",{staticStyle:{"margin-left":"10px"}},[e._v(e._s(e._f("dataTime")(t.row.date)))])]}}])}),a("el-table-column",{attrs:{label:"操作",width:"125"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"danger",icon:"el-icon-delete",plain:"",size:"small"},on:{click:function(a){return e.deleteMessage(t.row.id)}}},[e._v("删除信息")])]}}])})],1)],1)],1)],1)])],1)},l=[],i=a("56d7"),s={inject:["reload"],name:"college_xinxi",data:function(){return{tabPosition:"left",activeXinxiName:"",xinxiTable:[],receiver:this.$store.state.receiver}},mounted:function(){this.activeXinxiName="消息列表"},methods:{getUnReadList:function(){var e=this;e.$axios.get(i["default"]+"/msg/getUnReadList/"+e.receiver).then((function(t){e.xinxiTable=t.data})).catch((function(t){e.$message.error("服务器错误，操作失败！")}))},getReadList:function(){var e=this;e.$axios.get(i["default"]+"/msg/getReadList/"+e.receiver).then((function(t){e.xinxiTable=t.data})).catch((function(t){e.$message.error("服务器错误，操作失败！")}))},remarkMsg:function(e){var t=this;t.$axios.get(i["default"]+"/msg/markMessage/"+e).then((function(e){t.$message({type:200==e.data.code?"success":"error",message:e.data.message}),t.getUnReadList()})).catch((function(e){t.$message.error("服务器错误，操作失败！")}))},deleteMessage:function(e){var t=this;this.$confirm("此操作将永久删除该信息, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){var a=t;a.$axios.get(i["default"]+"/msg/deleteMessage/"+e).then((function(e){a.$message({type:200==e.data.code?"success":"error",message:e.data.message}),a.getReadList()})).catch((function(e){a.$message.error("服务器错误，操作失败！")}))}))}},watch:{activeXinxiName:function(e,t){var a=this;"消息列表"==a.activeXinxiName?a.getUnReadList():"已读"==a.activeXinxiName&&a.getReadList()}},filters:{dataTime:function(e){if(void 0==e)return"-";if(e=new Date(e),e instanceof Date){var t=e,a=t.getFullYear(),n=t.getMonth()+1,l=t.getDate()<10?"0"+t.getDate():t.getDate(),i=a+"年"+n+"月 "+l+"日";return i}return"-"}}},r=s,o=a("2877"),c=Object(o["a"])(r,n,l,!1,null,null,null);t["default"]=c.exports}}]);
//# sourceMappingURL=chunk-2d0c8a27.7ddcf3c2.js.map