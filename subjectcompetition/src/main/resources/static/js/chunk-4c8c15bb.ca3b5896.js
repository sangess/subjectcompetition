(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4c8c15bb"],{"4bc0":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"school_apply"}},[a("el-card",[a("div",{attrs:{slot:"header"},slot:"header"}),a("div",[a("el-tabs",{model:{value:e.compOrProject,callback:function(t){e.compOrProject=t},expression:"compOrProject"}},[a("el-tab-pane",{attrs:{label:"竞赛申请列表",name:"comp"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeApplyComp,callback:function(t){e.activeApplyComp=t},expression:"activeApplyComp"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"undo"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.undoApplyCompList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"college.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{label:"目前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:"success"}},[e._v(e._s(1==t.row.applyOrJoin?"已发出申请":"已重新发出申请"))])]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"done"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.doneApplyCompList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"college.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{prop:"applyOrJoin",label:"处理结果"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:3==t.row.applyOrJoin?"success":"danger"}},[a("span",{domProps:{textContent:e._s(3==t.row.applyOrJoin?"同意申请":"申请驳回")}})]),2==t.row.applyOrJoin?a("el-tooltip",{staticClass:"item",staticStyle:{"margin-left":"50px"},attrs:{effect:"dark",content:"重新申请",placement:"right"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-refresh-right",circle:""},on:{click:function(a){return e.reApplyComp(t.row.id)}}})],1):e._e()]}}])})],1)],1)],1)],1),a("el-tab-pane",{attrs:{label:"项目申请列表",name:"project"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeApplyProject,callback:function(t){e.activeApplyProject=t},expression:"activeApplyProject"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"undo"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.undoApplyTeacherList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"teacher.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{label:"目前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:"success"}},[e._v(e._s(1==t.row.applyOrJoin?"发出申请":"重新发出申请"))])]}}])}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"同意申请",placement:"top"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-circle-check",circle:""},on:{click:function(a){return e.applyProject(t.row.id)}}})],1),a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"驳回申请",placement:"top"}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-circle-close",circle:""},on:{click:function(a){return e.refuseProject(t.row.id)}}})],1)]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"done"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.doneApplyTeacherList}},[a("el-table-column",{attrs:{label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{prop:"teacher.nickname",label:"申请人"}}),a("el-table-column",{attrs:{prop:"project.comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{prop:"applyOrJoin",label:"处理情况"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:3==t.row.applyOrJoin?"success":"danger"}},[a("span",{domProps:{textContent:e._s(3==t.row.applyOrJoin?"同意申请":"申请驳回")}})])]}}])})],1)],1)],1)],1),a("el-tab-pane",{attrs:{label:"比赛奖项确认",name:"awards"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeApplyAwards,callback:function(t){e.activeApplyAwards=t},expression:"activeApplyAwards"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"undo"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.undoApplyAwardsList}},[a("el-table-column",{attrs:{align:"center",label:"#",prop:"id",width:"50px"}}),a("el-table-column",{attrs:{align:"center",prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"type",label:"获奖级别",width:"60px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:4==t.row.type?"success":3==t.row.type?"":t.row.type="warning"}},[a("span",{domProps:{textContent:e._s(4==t.row.type?"国家级":3==t.row.type?"省级":t.row.type="市级")}})])]}}])}),a("el-table-column",{attrs:{align:"center",prop:"level",label:"获奖名次"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{domProps:{textContent:e._s(4==t.row.level?"一等奖":3==t.row.level?"二等奖":t.row.level="三等奖")}})]}}])}),a("el-table-column",{attrs:{align:"center",prop:"project.comp.compName",label:"参加竞赛"}}),a("el-table-column",{attrs:{align:"center",prop:"project.projectName",label:"参与项目"}}),a("el-table-column",{attrs:{align:"center",label:"奖状"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-image",{staticStyle:{width:"100px",height:"100px"},attrs:{src:e.urlImg+t.row.imageUrl,"preview-src-list":e.srcUnList}})]}}])}),a("el-table-column",{attrs:{align:"center",prop:"state",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"同意奖项",placement:"top"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-circle-check",circle:""},on:{click:function(a){return e.applyAwards(t.row.id)}}})],1),a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"驳回奖项",placement:"top"}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-circle-close",circle:""},on:{click:function(a){return e.invokeAwards(t.row.id)}}})],1)]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"done"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.doneApplyAwardsList}},[a("el-table-column",{attrs:{align:"center",label:"#",prop:"id",width:"50px"}}),a("el-table-column",{attrs:{align:"center",prop:"applyDate",label:"申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.applyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.applyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"updateApplyDate",label:"最新更改日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"type",label:"获奖级别",width:"60px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:4==t.row.type?"success":3==t.row.type?"":t.row.type="warning"}},[a("span",{domProps:{textContent:e._s(4==t.row.type?"国家级":3==t.row.type?"省级":t.row.type="市级")}})])]}}])}),a("el-table-column",{attrs:{align:"center",prop:"level",label:"获奖名次"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{domProps:{textContent:e._s(4==t.row.level?"一等奖":3==t.row.level?"二等奖":t.row.level="三等奖")}})]}}])}),a("el-table-column",{attrs:{align:"center",prop:"project.comp.compName",label:"参加竞赛"}}),a("el-table-column",{attrs:{align:"center",prop:"project.projectName",label:"参与项目"}}),a("el-table-column",{attrs:{align:"center",label:"奖状"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-image",{staticStyle:{width:"100px",height:"100px"},attrs:{src:e.urlImg+t.row.imageUrl,"preview-src-list":e.srcDoList}})]}}])}),a("el-table-column",{attrs:{align:"center",prop:"state",label:"目前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:2==t.row.state?"success":"danger"}},[a("span",{domProps:{textContent:e._s(2==t.row.state?"审核通过":"审核不通过")}})])]}}])})],1)],1)],1)],1),a("el-tab-pane",{attrs:{label:"竞赛添加申请列表",name:"addComp"}},[a("el-tabs",{attrs:{"tab-position":e.tabPosition},model:{value:e.activeApplyAddComp,callback:function(t){e.activeApplyAddComp=t},expression:"activeApplyAddComp"}},[a("el-tab-pane",{attrs:{label:"未处理",name:"undo"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.undoAddList}},[a("el-table-column",{attrs:{label:"#",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{align:"center",prop:"updateApplyDate",label:"最新申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"college.nickname",label:"申请人"}}),a("el-table-column",{attrs:{align:"center",prop:"comp.compName",label:"申请添加竞赛"}}),a("el-table-column",{attrs:{align:"center",label:"查看详情"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-link",{attrs:{type:"primary",underline:!1},on:{click:function(a){return e.checkComp(t.row.comp.id)}}},[a("i",{staticClass:"el-icon-view el-icon--right"}),e._v(" 竞赛详情")])]}}])}),a("el-table-column",{attrs:{align:"center",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:0==t.row.applyOrJoin?"info":"danger"}},[a("span",{domProps:{textContent:e._s(0==t.row.applyOrJoin?"等待审核":"状态异常")}})])]}}])})],1)],1),a("el-tab-pane",{attrs:{label:"已处理",name:"done"}},[a("el-table",{staticStyle:{width:"100%","align-items":"center"},attrs:{border:"",stripe:"",data:e.doneAddList}},[a("el-table-column",{attrs:{align:"center",label:"#"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.$index+1))]}}])}),a("el-table-column",{attrs:{align:"center",prop:"updateApplyDate",label:"最新申请日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.updateApplyDate?"":"el-icon-time"}),e._v("\n                      "+e._s(e._f("dataTime")(t.row.updateApplyDate))+"\n                    ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"college.nickname",label:"申请人"}}),a("el-table-column",{attrs:{align:"center",prop:"comp.compName",label:"申请竞赛"}}),a("el-table-column",{attrs:{align:"center",label:"查看详情"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-link",{attrs:{type:"primary",underline:!1},on:{click:function(a){return e.checkComp(t.row.comp.id)}}},[a("i",{staticClass:"el-icon-view el-icon--right"}),e._v(" 竞赛详情")])]}}])}),a("el-table-column",{attrs:{align:"center",prop:"applyOrJoin",label:"处理情况"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:5==t.row.applyOrJoin?"success":"danger"}},[a("span",{domProps:{textContent:e._s(5==t.row.applyOrJoin?"申请同意":"申请驳回")}})])]}}])}),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[5==t.row.applyOrJoin?a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"已同意",placement:"top"}},[a("el-tag",{attrs:{type:"success"}},[a("i",{staticClass:"el-icon-check"},[e._v("已同意")])])],1):e._e(),6==t.row.applyOrJoin?a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"重新申请",placement:"top"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-refresh-left",circle:""},on:{click:function(a){return e.reApplyAddCommp(t.row.id)}}})],1):e._e()]}}])})],1)],1)],1)],1)],1)],1)]),a("el-dialog",{attrs:{title:"竞赛详情列表",width:"70%",visible:e.compInfoVisiable},on:{"update:visible":function(t){e.compInfoVisiable=t}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.compInfo,stripe:"",border:"","max-height":"500"}},[a("el-table-column",{attrs:{align:"center",label:"竞赛名",prop:"compName"}}),a("el-table-column",{attrs:{align:"center",prop:"level",label:"级 别",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[0==t.row.level?a("p",[e._v("国家级")]):e._e(),1==t.row.level?a("p",[e._v("省级")]):e._e(),2==t.row.level?a("p",[e._v("校级")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"可申请时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.canApplyTime?"":"el-icon-time"}),e._v("\n            "+e._s(e._f("dataTime")(t.row.canApplyTime))+"\n          ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"截止申请时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.notApplyTime?"":"el-icon-time"}),e._v("\n            "+e._s(e._f("dataTime")(t.row.notApplyTime))+"\n          ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"比赛时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{class:void 0==t.row.occurrenceTime?"":"el-icon-time"}),e._v("\n            "+e._s(e._f("dataTime")(t.row.occurrenceTime))+"\n          ")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"place",label:"竞赛地点",sortable:""}}),a("el-table-column",{attrs:{align:"center",prop:"organizer",label:"主办方",sortable:""}})],1)],1)],1)},n=[],o=(a("ac6a"),a("56d7")),r={inject:["reload"],name:"college_apply",data:function(){return{compInfo:[],compInfoVisiable:!1,showViewer:!1,urlImg:o["default"],srcUnList:[],srcDoList:[],compOrProject:"comp",activeApplyComp:"done",activeApplyProject:"undo",activeApplyAwards:"undo",activeApplyAddComp:"done",tabPosition:"left",undoApplyCompList:[],doneApplyCompList:[],undoApplyTeacherList:[],doneApplyTeacherList:[],undoApplyAwardsList:[],doneApplyAwardsList:[],doneAddList:[],undoAddList:[],time1:"",time2:"",time3:"",time4:"",time5:"",time6:"",time7:"",time8:""}},mounted:function(){this.getUndoApplyCompList(),this.getDoneApplyCompList(),this.getUndoApplyTeacherList(),this.getDoneApplyTeacherList(),this.getDoneApplyAwardsList(),this.getUndoApplyAwardsList(),this.getDoneAddList(),this.getUndoAddList(),this.time1=setInterval(this.getUndoApplyCompList,1500),this.time2=setInterval(this.getDoneApplyCompList,1500),this.time3=setInterval(this.getUndoApplyTeacherList,1500),this.time4=setInterval(this.getDoneApplyTeacherList,1500),this.time5=setInterval(this.getDoneApplyAwardsList,1500),this.time6=setInterval(this.getUndoApplyAwardsList,1500),this.time7=setInterval(this.getDoneAddList,1500),this.time8=setInterval(this.getUndoAddList,1500)},methods:{getUndoApplyCompList:function(){var e=this,t=e.$store.state.id;e.$axios.post(o["default"]+"/college/getUndoCompApply/"+t).then((function(t){e.undoApplyCompList=t.data}))},getDoneApplyCompList:function(){var e=this,t=e.$store.state.id;e.$axios.post(o["default"]+"/college/getDoneCompApply/"+t).then((function(t){e.doneApplyCompList=t.data}))},reApplyComp:function(e){var t=this;t.$axios.get(o["default"]+"/college/reApplyComp/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"error"}),t.reload()}))},getUndoApplyTeacherList:function(){var e=this,t=e.$store.state.id;e.$axios.get(o["default"]+"/college/getUndoApplyTeacherList/"+t).then((function(t){e.undoApplyTeacherList=t.data}))},getDoneApplyTeacherList:function(){var e=this,t=e.$store.state.id;e.$axios.get(o["default"]+"/college/getDoneApplyTeacherList/"+t).then((function(t){e.doneApplyTeacherList=t.data}))},applyProject:function(e){var t=this;t.$axios.get(o["default"]+"/college/applyTeacherProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"error"})}))},refuseProject:function(e){var t=this;t.$axios.get(o["default"]+"/college/refuseTeacherProject/"+e).then((function(e){t.$message({message:e.data.message,type:200==e.data.code?"success":"error"}),t.reload()}))},getDoneApplyAwardsList:function(){var e=this;e.$axios.get(o["default"]+"/awards/getDoneApplyAwardsList/"+e.$store.state.id).then((function(t){e.doneApplyAwardsList=t.data,e.doneApplyAwardsList.forEach((function(t){e.srcDoList.push(e.urlImg+t.imageUrl)}))}))},getUndoApplyAwardsList:function(){var e=this;e.$axios.get(o["default"]+"/awards/getUndoApplyAwardsList/"+e.$store.state.id).then((function(t){e.undoApplyAwardsList=t.data,e.undoApplyAwardsList.forEach((function(t){e.srcUnList.push(e.urlImg+t.imageUrl)})),console.log(e.undoApplyAwardsList.length)}))},applyAwards:function(e){var t=this;t.$axios.get(o["default"]+"/awards/applyAwards/"+e).then((function(e){t.$message({type:200==e.data.code?"success":"error",message:e.data.message}),t.reload()}))},invokeAwards:function(e){var t=this;t.$axios.get(o["default"]+"/awards/invokeAwards/"+e).then((function(e){t.$message({type:200==e.data.code?"success":"error",message:e.data.message}),t.reload()}))},checkComp:function(e){var t=this;t.compInfoVisiable=!0,t.$axios.get(o["default"]+"/admin/checkComp/"+e).then((function(e){t.compInfo=e.data}))},getDoneAddList:function(){var e=this;e.$axios.get(o["default"]+"/college/getDoneAddList/"+e.$store.state.id).then((function(t){e.doneAddList=t.data}))},getUndoAddList:function(){var e=this;e.$axios.get(o["default"]+"/college/getUndoAddList/"+e.$store.state.id).then((function(t){e.undoAddList=t.data}))},reApplyAddCommp:function(e){var t=this;t.$axios.get(o["default"]+"/college/reApplyAddCommp/"+e).then((function(e){t.$message({type:200==e.data.code?"success":"error",message:e.data.message})}))}},filters:{dataTime:function(e){if(void 0==e)return"-";if(e=new Date(e),e instanceof Date){var t=e,a=t.getFullYear(),l=t.getMonth()+1,n=t.getDate()<10?"0"+t.getDate():t.getDate(),o=a+"年"+l+"月 "+n+"日";return o}return"-"}},beforeDestroy:function(){clearInterval(this.time1),clearInterval(this.time2),clearInterval(this.time3),clearInterval(this.time4),clearInterval(this.time5),clearInterval(this.time6)}},s=r,i=a("2877"),p=Object(i["a"])(s,l,n,!1,null,null,null);t["default"]=p.exports},ac6a:function(e,t,a){for(var l=a("cadf"),n=a("0d58"),o=a("2aba"),r=a("7726"),s=a("32e9"),i=a("84f2"),p=a("2b4c"),c=p("iterator"),d=p("toStringTag"),u=i.Array,m={CSSRuleList:!0,CSSStyleDeclaration:!1,CSSValueList:!1,ClientRectList:!1,DOMRectList:!1,DOMStringList:!1,DOMTokenList:!0,DataTransferItemList:!1,FileList:!1,HTMLAllCollection:!1,HTMLCollection:!1,HTMLFormElement:!1,HTMLSelectElement:!1,MediaList:!0,MimeTypeArray:!1,NamedNodeMap:!1,NodeList:!0,PaintRequestList:!1,Plugin:!1,PluginArray:!1,SVGLengthList:!1,SVGNumberList:!1,SVGPathSegList:!1,SVGPointList:!1,SVGStringList:!1,SVGTransformList:!1,SourceBufferList:!1,StyleSheetList:!0,TextTrackCueList:!1,TextTrackList:!1,TouchList:!1},f=n(m),y=0;y<f.length;y++){var b,g=f[y],A=m[g],w=r[g],_=w&&w.prototype;if(_&&(_[c]||s(_,c,u),_[d]||s(_,d,g),i[g]=u,A))for(b in l)_[b]||o(_,b,l[b],!0)}}}]);
//# sourceMappingURL=chunk-4c8c15bb.ca3b5896.js.map