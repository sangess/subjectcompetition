(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0da982"],{"6bee":function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"admin_apply"}},[a("el-card",[a("el-tabs",{attrs:{"tab-position":t.tabPosition},model:{value:t.activeApply,callback:function(e){t.activeApply=e},expression:"activeApply"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"未处理"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:t.undoApplyList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("i",{class:void 0==e.row.applyDate?"":"el-icon-time"}),t._v("\n              "+t._s(t._f("dataTime")(e.row.applyDate))+"\n            ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("i",{class:void 0==e.row.updateApplyDate?"":"el-icon-time"}),t._v("\n              "+t._s(t._f("dataTime")(e.row.updateApplyDate))+"\n            ")]}}])}),a("el-table-column",{attrs:{prop:"college.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"同意申请",placement:"top"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-circle-check",circle:""},on:{click:function(a){return t.applyComp(e.row.id)}}})],1),a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"驳回申请",placement:"top"}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-circle-close",circle:""},on:{click:function(a){return t.refuseComp(e.row.id)}}})],1)]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"已处理"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:t.doneApplyList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("i",{class:void 0==e.row.applyDate?"":"el-icon-time"}),t._v("\n              "+t._s(t._f("dataTime")(e.row.applyDate))+"\n            ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("i",{class:void 0==e.row.updateApplyDate?"":"el-icon-time"}),t._v("\n              "+t._s(t._f("dataTime")(e.row.updateApplyDate))+"\n            ")]}}])}),a("el-table-column",{attrs:{prop:"college.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{prop:"applyOrJoin",label:"处理情况"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-tag",{attrs:{type:2==e.row.applyOrJoin?"danger":"success"}},[a("span",{domProps:{textContent:t._s(3==e.row.applyOrJoin?"同意申请":"申请驳回")}})])]}}])})],1)],1)],1)],1)],1)},n=[],o=a("56d7"),p={inject:["reload"],name:"admin_apply",data:function(){return{tabPosition:"left",activeApply:"未处理",undoApplyList:[],doneApplyList:[],time1:"",time2:""}},mounted:function(){this.displayUndoApplyList(),this.displayDoneApplyList(),this.time1=setInterval(this.displayUndoApplyList,1500),this.time2=setInterval(this.displayDoneApplyList,1500)},methods:{displayUndoApplyList:function(){var t=this;t.$axios.post(o["default"]+"/admin/getUndoApplyCompList").then((function(e){t.undoApplyList=e.data}))},displayDoneApplyList:function(){var t=this;t.$axios.post(o["default"]+"/admin/getDoneApplyCompList").then((function(e){t.doneApplyList=e.data}))},applyComp:function(t){var e=this;e.$axios.get(o["default"]+"/admin/applyComp/"+t).then((function(t){e.$message({message:t.data.message,type:200==t.data.code?"success":"error"}),e.displayUndoApplyList()}))},refuseComp:function(t){var e=this;e.$axios.get(o["default"]+"/admin/refuseComp/"+t).then((function(t){e.$message({message:t.data.message,type:200==t.data.code?"success":"error"}),e.displayDoneApplyList()}))}},filters:{dataTime:function(t){if(void 0==t)return"-";if(t=new Date(t),t instanceof Date){var e=t,a=e.getFullYear(),l=e.getMonth()+1,n=e.getDate()<10?"0"+e.getDate():e.getDate(),o=a+"年"+l+"月 "+n+"日";return o}return"-"}},beforeDestroy:function(){clearInterval(this.time1),clearInterval(this.time2)}},i=p,s=a("2877"),r=Object(s["a"])(i,l,n,!1,null,"5b2eadd6",null);e["default"]=r.exports}}]);
//# sourceMappingURL=chunk-2d0da982.55eb2bd9.js.map