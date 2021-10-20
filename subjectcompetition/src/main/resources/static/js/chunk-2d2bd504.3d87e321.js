(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d2bd504"],{4134:function(t,e,a){},dff9:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"admin_gonggao"}},[a("el-card",[a("div",[a("el-tabs",{on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"公告信息列表",name:"公告信息列表"}},[a("el-button",{staticStyle:{float:"right"},attrs:{type:"text"},on:{click:function(e){t.点击导出表格按钮("gonggaoTable","公告信息列表")}}},[t._v("导出到Ecxel")]),t.showTable?a("el-table",{staticStyle:{width:"100%"},attrs:{id:"gonggaoTable",data:t.gonggaoTable,border:"",stripe:"","max-height":"650"}},[a("el-table-column",{attrs:{prop:"id",label:"#",width:"60"}}),a("el-table-column",{attrs:{prop:"title",label:"标题",width:"270"}}),a("el-table-column",{attrs:{prop:"content",label:"内容"}}),a("el-table-column",{attrs:{prop:"type",label:"公告类型",width:"120"}}),a("el-table-column",{attrs:{prop:"time",label:"发布日期",width:"140"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                "+t._s(t._f("dataTime")(e.row.time))+"\n              ")]}}],null,!1,3571372020)}),a("el-table-column",{attrs:{prop:"author.nickname",label:"作者",width:"105"}}),a("el-table-column",{attrs:{prop:"level",label:"公告级别",width:"80"}}),a("el-table-column",{attrs:{align:"center",label:"操作",width:"130"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"编辑公告",placement:"top"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-edit",circle:""},on:{click:function(a){t.点击编辑公告按钮(e.$index)}}})],1),a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"删除公告",placement:"top"}},[a("el-button",{attrs:{type:"danger",icon:"el-icon-delete",circle:""},on:{click:function(a){t.点击删除公告按钮(e.row.id)}}})],1)]}}],null,!1,2410786965)})],1):t._e(),t.showTable?t._e():a("div",[a("div",{staticStyle:{"text-align":"center"}},[a("h1",{staticStyle:{"font-size":"28px"}},[t._v("编辑修改公告")])]),a("el-row",{staticClass:"gonggao"},[a("el-col",{attrs:{span:8,offset:8}},[a("el-card",{staticStyle:{"border-radius":"4px","padding-buttom":"50px"}},[a("div",{attrs:{slot:"header"},slot:"header"},[a("el-input",{attrs:{placeholder:"请添加标题"},model:{value:t.tableRowData.title,callback:function(e){t.$set(t.tableRowData,"title",e)},expression:"tableRowData.title"}})],1),a("div",[a("el-input",{attrs:{type:"textarea",placeholder:"请填写内容"},model:{value:t.tableRowData.content,callback:function(e){t.$set(t.tableRowData,"content",e)},expression:"tableRowData.content"}})],1)]),a("div",{staticStyle:{padding:"10px"}},[a("span",[t._v("设置公告级别")]),a("el-slider",{attrs:{step:10,"show-stops":"","show-input":""},model:{value:t.tableRowData.level,callback:function(e){t.$set(t.tableRowData,"level",e)},expression:"tableRowData.level"}})],1),a("div",{staticStyle:{padding:"10px"}},[a("span",{staticStyle:{"padding-right":"20px"}},[t._v("设置公告类型")]),a("el-select",{attrs:{placeholder:"请选择公告类型"},model:{value:t.tableRowData.type,callback:function(e){t.$set(t.tableRowData,"type",e)},expression:"tableRowData.type"}},[a("el-option",{attrs:{label:"系统公告",value:"系统公告"}}),a("el-option",{attrs:{label:"荣誉墙",value:"荣誉墙"}}),a("el-option",{attrs:{label:"竞赛信息",value:"竞赛信息"}})],1)],1),a("div",{staticStyle:{"text-align":"center",margin:"30px"}},[a("el-button",{attrs:{type:"success",plain:"",icon:"el-icon-message"},on:{click:function(e){t.确认编辑公告修改()}}},[t._v("确认修改")]),a("el-button",{attrs:{type:"warning",plain:"",icon:"el-icon-message"},on:{click:function(e){t.取消编辑公告修改()}}},[t._v("取消修改")])],1)],1)],1)],1)],1),a("el-tab-pane",{staticStyle:{"text-algin":"center"},attrs:{label:"发布新公告",name:"发布新公告"}},[a("div",{staticStyle:{"text-align":"center"}},[a("h1",{staticStyle:{"font-size":"28px"}},[t._v("发布新公告")])]),a("el-row",{staticClass:"gonggao"},[a("el-col",{attrs:{span:8,offset:8}},[a("el-card",{staticStyle:{"border-radius":"4px","padding-buttom":"50px"}},[a("div",{attrs:{slot:"header"},slot:"header"},[a("el-input",{attrs:{placeholder:"请添加标题"},model:{value:t.newGonggao.title,callback:function(e){t.$set(t.newGonggao,"title",e)},expression:"newGonggao.title"}})],1),a("div",[a("el-input",{attrs:{type:"textarea",placeholder:"请填写内容"},model:{value:t.newGonggao.content,callback:function(e){t.$set(t.newGonggao,"content",e)},expression:"newGonggao.content"}})],1)]),a("div",{staticStyle:{padding:"10px"}},[a("span",[t._v("设置公告级别")]),a("el-slider",{attrs:{step:10,"show-stops":"","show-input":""},model:{value:t.newGonggao.level,callback:function(e){t.$set(t.newGonggao,"level",e)},expression:"newGonggao.level"}})],1),a("div",{staticStyle:{padding:"10px"}},[a("span",{staticStyle:{"padding-right":"20px"}},[t._v("设置公告类型")]),a("el-select",{attrs:{placeholder:"请选择公告类型"},model:{value:t.newGonggao.type,callback:function(e){t.$set(t.newGonggao,"type",e)},expression:"newGonggao.type"}},[a("el-option",{attrs:{label:"系统公告",value:"系统公告"}}),a("el-option",{attrs:{label:"荣誉墙",value:"荣誉墙"}}),a("el-option",{attrs:{label:"竞赛信息",value:"竞赛信息"}})],1)],1),a("div",{staticStyle:{"text-align":"center",margin:"30px"}},[a("el-button",{attrs:{type:"success",plain:"",icon:"el-icon-message"},on:{click:function(e){t.发布公告()}}},[t._v("发布公告")])],1)],1)],1)],1)],1)],1)])],1)},o=[],l=a("2f14"),i=a("56d7"),s={name:"admin_gonggao",data:function(){return{tabPosition:"left",showTable:!0,activeName:"公告信息列表",activeXinxiName:"消息列表",tableRowData:{},gonggaoTable:[],xinxiTable:[],newGonggao:{title:"",content:"",level:50,author:"",type:""}}},mounted:function(){this.请求公告信息列表数据()},methods:{handleClick:function(t,e){},"点击编辑公告按钮":function(t){this.showTable=!1,this.tableRowData=this.gonggaoTable[t]},"点击删除公告按钮":function(t){var e=this,a=this;this.$confirm("此操作将永久删除该公告, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){e.$axios.get(i["default"]+"/notice/deleteNoticeById?id=".concat(t)).then((function(t){a.$message({message:t.data,type:"删除成功！"==t.data?"success":"error"}),"删除成功！"==t.data&&a.请求公告信息列表数据()})).catch((function(t){a.$message.error("服务器错误，操作失败！"),console.error(t)}))})).catch((function(){e.$message({type:"info",message:"已取消删除"})}))},"取消编辑公告修改":function(){var t=this;this.$confirm("此操作将放弃对公告的修改, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.tableRowData={},t.showTable=!0})).catch((function(){}))},"确认编辑公告修改":function(){if(""!=this.tableRowData.title&&""!=this.tableRowData.content){var t=this,e=new FormData;e.append("id",t.tableRowData.id),e.append("title",t.tableRowData.title),e.append("content",t.tableRowData.content),e.append("level",t.tableRowData.level),e.append("author.id",t.$store.state.id),e.append("type",t.tableRowData.type),this.$axios.post(i["default"]+"/notice/updateNotice",e).then((function(e){t.$message({message:"发布成功！"==e.data?"修改成功":e.data,type:"发布成功！"==e.data?"success":"error"}),t.showTable=!0})).catch((function(e){t.$message.error("服务器错误，操作失败！"),console.error(e)}))}else this.$message.error("标题和内容不能为空！")},"发布公告":function(){if(""!=this.newGonggao.title&&""!=this.newGonggao.content){var t=this,e=(new Date,new FormData);e.append("title",t.newGonggao.title),e.append("content",t.newGonggao.content),e.append("level",t.newGonggao.level),e.append("author.id",t.$store.state.id),e.append("type",t.newGonggao.type),this.$axios.post(i["default"]+"/notice/addNotice",e).then((function(e){t.$message({message:e.data,type:"发布成功！"==e.data?"success":"error"}),t.newGonggao.title="",t.newGonggao.content="",t.activeName="公告信息列表"})).catch((function(e){t.$message.error("服务器错误，操作失败！"),console.error(e)}))}else this.$message.error("标题和内容不能为空！")},"请求公告信息列表数据":function(){var t=this;this.$axios.get(i["default"]+"/notice/findAllNotice").then((function(e){t.gonggaoTable=e.data})).catch((function(e){t.$message.error("服务器错误，操作失败！"),console.error(e)}))},"点击导出表格按钮":function(t,e){Object(l["a"])(t,e)}},watch:{activeName:function(t,e){var a=this;a.tableRowData={},a.showTable=!0,"公告信息列表"==a.activeName?a.请求公告信息列表数据():"信息管理"==a.activeName&&("消息列表"!=a.activeXinxiName?a.activeXinxiName="消息列表":a.获取未读消息列表())},activeXinxiName:function(t,e){var a=this;"消息列表"==a.activeXinxiName?a.获取未读消息列表():"已读"==a.activeXinxiName&&a.获取已读消息列表()}},filters:{dataTime:function(t){if(void 0==t)return"-";if(t=new Date(t),t instanceof Date){var e=t,a=e.getFullYear(),n=e.getMonth()+1,o=e.getDate()<10?"0"+e.getDate():e.getDate(),l=a+"年"+n+"月 "+o+"日";return l}return"-"}}},c=s,r=(a("f857"),a("2877")),p=Object(r["a"])(c,n,o,!1,null,null,null);e["default"]=p.exports},f857:function(t,e,a){"use strict";a("4134")}}]);
//# sourceMappingURL=chunk-2d2bd504.3d87e321.js.map