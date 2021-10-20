<template>
  <div id="school_apply">
    <el-card>
      <div slot="header"></div>
      <div>
        <el-tabs  v-model="compOrProject">
          <el-tab-pane label="竞赛申请列表" name="comp">
            <el-tabs v-model="activeApplyComp" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoApplyCompList" style="width: 100%;align-items: center">
                  <el-table-column label="#" >
                    <template slot-scope="scope">{{scope.$index + 1}}</template>
                  </el-table-column>
                  <el-table-column prop="applyDate" label="申请日期" >
                    <template slot-scope="scope">
                      <i :class="scope.row.applyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.applyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="updateApplyDate" label="最新更改日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="college.nickname" label="申请人"></el-table-column>
                  <el-table-column prop="comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column label="目前状态" >
                    <template slot-scope="scope">
                      <el-tag type="success" >{{scope.row.applyOrJoin==1?'已发出申请':'已重新发出申请'}}</el-tag>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneApplyCompList" style="width: 100%;align-items: center">
                  <el-table-column label="#" >
                    <template slot-scope="scope">{{scope.$index + 1}}</template>
                  </el-table-column>
                  <el-table-column prop="applyDate" label="申请日期" >
                    <template slot-scope="scope">
                      <i :class="scope.row.applyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.applyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="updateApplyDate" label="最新更改日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="college.nickname" label="申请人"></el-table-column>
                  <el-table-column prop="comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column prop="applyOrJoin" label="处理结果" >
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.applyOrJoin==3?'success':'danger'">
                        <span v-text="scope.row.applyOrJoin==3?'同意申请':'申请驳回'"></span>
                      </el-tag>
                      <el-tooltip v-if="scope.row.applyOrJoin==2" style="margin-left: 50px" class="item" effect="dark" content="重新申请" placement="right">
                        <el-button type="primary" icon="el-icon-refresh-right"  circle @click="reApplyComp(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane label="项目申请列表" name="project">
            <el-tabs v-model="activeApplyProject" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoApplyTeacherList" style="width: 100%;align-items: center">
                  <el-table-column label="#" >
                    <template slot-scope="scope">{{scope.$index + 1}}</template>
                  </el-table-column>
                  <el-table-column prop="applyDate" label="申请日期" >
                    <template slot-scope="scope">
                      <i :class="scope.row.applyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.applyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="updateApplyDate" label="最新更改日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="teacher.nickname" label="申请人"></el-table-column>
                  <el-table-column prop="project.comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column label="目前状态" >
                    <template slot-scope="scope">
                      <el-tag type="success" >{{scope.row.applyOrJoin==1?'发出申请':'重新发出申请'}}</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column label="操作" >
                    <template slot-scope="scope">
                      <el-tooltip class="item" effect="dark" content="同意申请" placement="top">
                        <el-button type="primary" icon="el-icon-circle-check"  circle @click="applyProject(scope.row.id)"></el-button>
                      </el-tooltip>
                      <el-tooltip class="item" effect="dark" content="驳回申请" placement="top">
                        <el-button type="warning" icon="el-icon-circle-close"  circle @click="refuseProject(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneApplyTeacherList" style="width: 100%;align-items: center">
                  <el-table-column label="#" >
                    <template slot-scope="scope">{{scope.$index + 1}}</template>
                  </el-table-column>
                  <el-table-column prop="applyDate" label="申请日期" >
                    <template slot-scope="scope">
                      <i :class="scope.row.applyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.applyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="updateApplyDate" label="最新更改日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="teacher.nickname" label="申请人"></el-table-column>
                  <el-table-column prop="project.comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column prop="applyOrJoin" label="处理情况" >
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.applyOrJoin==3?'success':'danger'">
                        <span v-text="scope.row.applyOrJoin==3?'同意申请':'申请驳回'"></span>
                      </el-tag>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane label="比赛奖项确认" name="awards">
            <el-tabs v-model="activeApplyAwards" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoApplyAwardsList" style="width: 100%;align-items: center">
                  <el-table-column align="center"label="#" prop="id" width="50px"></el-table-column>
                  <el-table-column align="center" prop="applyDate" label="申请日期" >
                    <template slot-scope="scope">
                      <i :class="scope.row.applyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.applyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="updateApplyDate" label="最新更改日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="type" label="获奖级别"width="60px">
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.type==4?'success':(scope.row.type==3?'':(scope.row.type=2?'warning':'danger'))">
                        <span v-text="scope.row.type==4?'国家级':(scope.row.type==3?'省级':(scope.row.type=2?'市级':'校级'))"></span>
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="level" label="获奖名次">
                    <template slot-scope="scope">
                      <span v-text="scope.row.level==4?'一等奖':(scope.row.level==3?'二等奖':(scope.row.level=2?'三等奖':scope.row.content))"></span>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="project.comp.compName" label="参加竞赛" ></el-table-column>
                  <el-table-column align="center" prop="project.projectName" label="参与项目" ></el-table-column>
                  <el-table-column align="center" label="奖状" >
                    <template slot-scope="scope">
                      <el-image style="width: 100px; height: 100px" :src="urlImg+scope.row.imageUrl" :preview-src-list="srcUnList" >
                      </el-image>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="state" label="操作" >
                    <template slot-scope="scope">
                      <el-tooltip class="item" effect="dark" content="同意奖项" placement="top">
                        <el-button type="primary" icon="el-icon-circle-check"  circle @click="applyAwards(scope.row.id)"></el-button>
                      </el-tooltip>
                      <el-tooltip class="item" effect="dark" content="驳回奖项" placement="top">
                        <el-button type="warning" icon="el-icon-circle-close"  circle @click="invokeAwards(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneApplyAwardsList" style="width: 100%;align-items: center">
                  <el-table-column align="center" label="#" prop="id" width="50px">
                  </el-table-column>
                  <el-table-column align="center" prop="applyDate" label="申请日期" >
                    <template slot-scope="scope">
                      <i :class="scope.row.applyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.applyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="updateApplyDate" label="最新更改日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="type" label="获奖级别" width="60px">
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.type==4?'success':(scope.row.type==3?'':(scope.row.type=2?'warning':'danger'))">
                        <span v-text="scope.row.type==4?'国家级':(scope.row.type==3?'省级':(scope.row.type=2?'市级':'校级'))"></span>
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="level" label="获奖名次">
                    <template slot-scope="scope">
                        <span v-text="scope.row.level==4?'一等奖':(scope.row.level==3?'二等奖':(scope.row.level=2?'三等奖':scope.row.content))"></span>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="project.comp.compName" label="参加竞赛" ></el-table-column>
                  <el-table-column align="center" prop="project.projectName" label="参与项目" ></el-table-column>
                  <el-table-column align="center" label="奖状" >
                    <template slot-scope="scope">
                      <el-image style="width: 100px; height: 100px" :src="urlImg+scope.row.imageUrl" :preview-src-list="srcDoList" >
                      </el-image>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="state" label="目前状态" >
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.state==2?'success':'danger'">
                        <span v-text="scope.row.state==2?'审核通过':'审核不通过'"></span>
                      </el-tag>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane label="竞赛添加申请列表" name="addComp">
            <el-tabs v-model="activeApplyAddComp" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoAddList" style="width: 100%;align-items: center">
                  <el-table-column label="#" align="center" >
                    <template slot-scope="scope">{{scope.$index + 1}}</template>
                  </el-table-column>
                  <el-table-column align="center" prop="updateApplyDate" label="最新申请日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="college.nickname" label="申请人"></el-table-column>
                  <el-table-column align="center" prop="comp.compName" label="申请添加竞赛" ></el-table-column>
                  <el-table-column align="center" label="查看详情">
                    <template slot-scope="scope">
                      <el-link type="primary" :underline="false" @click="checkComp(scope.row.comp.id)"><i class="el-icon-view el-icon--right"></i> 竞赛详情</el-link>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="状态">
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.applyOrJoin==0?'info':'danger'">
                        <span v-text="scope.row.applyOrJoin==0?'等待审核':'状态异常'"></span>
                      </el-tag>
                    </template>
                  </el-table-column>
<!--                  <el-table-column align="center" label="操作" >
                    <template slot-scope="scope">
                      <el-tooltip class="item" effect="dark" content="同意申请" placement="top">
                        <el-button type="primary" icon="el-icon-circle-check"  circle @click="applyAddCommp(scope.row.id)"></el-button>
                      </el-tooltip>
                      <el-tooltip class="item" effect="dark" content="驳回申请" placement="top">
                        <el-button type="warning" icon="el-icon-circle-close"  circle @click="refuseAddComp(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>-->
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneAddList" style="width: 100%;align-items: center">
                  <el-table-column align="center" label="#" >
                    <template slot-scope="scope">{{scope.$index + 1}}</template>
                  </el-table-column>
                  <el-table-column align="center" prop="updateApplyDate" label="最新申请日期">
                    <template slot-scope="scope">
                      <i :class="scope.row.updateApplyDate==undefined?'':'el-icon-time'"></i>
                      {{scope.row.updateApplyDate|dataTime }}
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="college.nickname" label="申请人"></el-table-column>
                  <el-table-column align="center" prop="comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column align="center" label="查看详情">
                    <template slot-scope="scope">
                      <el-link type="primary" :underline="false" @click="checkComp(scope.row.comp.id)"><i class="el-icon-view el-icon--right"></i> 竞赛详情</el-link>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="applyOrJoin" label="处理情况" >
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.applyOrJoin==5?'success':'danger'">
                        <span v-text="scope.row.applyOrJoin==5?'申请同意':'申请驳回'"></span>
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="操作">
                    <template slot-scope="scope">
                      <el-tooltip class="item"  v-if="scope.row.applyOrJoin==5" effect="dark" content="已同意" placement="top">
                        <el-tag type="success">
                          <i class="el-icon-check"  >已同意</i>
                        </el-tag>
                      </el-tooltip>
                      <el-tooltip class="item"  v-if="scope.row.applyOrJoin==6" effect="dark" content="重新申请" placement="top">
                        <el-button type="primary" icon="el-icon-refresh-left"  circle @click="reApplyAddCommp(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-card>
    <!-- 竞赛项目展示模态框 -->
    <el-dialog title="竞赛详情列表" width="70%" :visible.sync="compInfoVisiable">
      <el-table :data="compInfo" stripe border style="width: 100%" max-height="500">
        <el-table-column align="center" label="竞赛名" prop="compName"></el-table-column>
        <el-table-column align="center" prop="level" label="级 别" sortable>
          <template slot-scope="scope">
            <p v-if="scope.row.level==0">国家级</p>
            <p v-if="scope.row.level==1">省级</p>
            <p v-if="scope.row.level==2">校级</p>
          </template>
        </el-table-column>
        <el-table-column align="center" label="可申请时间">
          <template slot-scope="scope">
            <i :class="scope.row.canApplyTime==undefined?'':'el-icon-time'"></i>
            {{scope.row.canApplyTime|dataTime }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="截止申请时间">
          <template slot-scope="scope">
            <i :class="scope.row.notApplyTime==undefined?'':'el-icon-time'"></i>
            {{scope.row.notApplyTime|dataTime }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="比赛时间">
          <template slot-scope="scope">
            <i :class="scope.row.occurrenceTime==undefined?'':'el-icon-time'"></i>
            {{scope.row.occurrenceTime|dataTime }}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="place" label="竞赛地点" sortable></el-table-column>
        <el-table-column align="center" prop="organizer" label="主办方" sortable></el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>
<script>
import api from '../../main.js'
export default {
  inject:['reload'],
  name:"college_apply",
  data(){
    return {
      compInfo:[],
      compInfoVisiable:false,
      showViewer: false, // 显示查看器
      urlImg:api,
      srcUnList:[],
      srcDoList:[],
      compOrProject:"comp",
      activeApplyComp:"done",
      activeApplyProject:"undo",
      activeApplyAwards:"undo",
      activeApplyAddComp:"done",
      tabPosition:"left",
      undoApplyCompList:[],
      doneApplyCompList:[],
      undoApplyTeacherList:[],
      doneApplyTeacherList:[],
      undoApplyAwardsList:[],
      doneApplyAwardsList:[],
      doneAddList:[],
      undoAddList:[],
      time1:"",
      time2:"",
      time3:"",
      time4:"",
      time5:"",
      time6:"",
      time7:"",
      time8:"",
    }
  },
  mounted() {
    this.getUndoApplyCompList();
    this.getDoneApplyCompList();
    this.getUndoApplyTeacherList();
    this.getDoneApplyTeacherList();
    this.getDoneApplyAwardsList();
    this.getUndoApplyAwardsList();
    this.getDoneAddList();
    this.getUndoAddList();
    this.time1=setInterval(this.getUndoApplyCompList,1500);
    this.time2=setInterval(this.getDoneApplyCompList,1500);
    this.time3=setInterval(this.getUndoApplyTeacherList,1500);
    this.time4=setInterval(this.getDoneApplyTeacherList,1500);
    this.time5=setInterval(this.getDoneApplyAwardsList,1500);
    this.time6=setInterval(this.getUndoApplyAwardsList,1500);
    this.time7=setInterval(this.getDoneAddList,1500);
    this.time8=setInterval(this.getUndoAddList,1500);

  },
  methods:{
    /**
     * 得到未处理的比赛申请
     */
    getUndoApplyCompList(){
      let that=this;
      let collegeId=that.$store.state.id;
      that.$axios
        .post(api+`/college/getUndoCompApply/`+collegeId)
        .then(function (response) {
          that.undoApplyCompList=response.data;
        })
    },
    /**
     * 得到处理过的比赛申请
     */
    getDoneApplyCompList(){
      let that=this;
      let collegeId=that.$store.state.id;
      that.$axios
      .post(api+`/college/getDoneCompApply/`+collegeId)
      .then(function (response) {
        that.doneApplyCompList=response.data;
      })
    },
    /**
     * 重新申请
     * @param collegeCompId
     */
    reApplyComp(collegeCompId){
      let that=this;
      that.$axios
        .get(api+`/college/reApplyComp/`+collegeCompId)
        .then(function (response) {
          that.$message({
            message:response.data.message,
            type:response.data.code==200?'success':'error'
          });
          that.reload()
        })
    },
    /**
     * 得到未处理的项目申请
     */
    getUndoApplyTeacherList(){
      let that=this;
      let collegeId=that.$store.state.id;
      that.$axios
      .get(api+`/college/getUndoApplyTeacherList/`+collegeId)
      .then(function (response) {
        that.undoApplyTeacherList=response.data;
      })
    },
    /**
     * 得到处理过的项目申请
     */
    getDoneApplyTeacherList(){
      let that=this;
      let collegeId=that.$store.state.id;
      that.$axios
      .get(api+`/college/getDoneApplyTeacherList/`+collegeId)
      .then(function (response) {
        that.doneApplyTeacherList=response.data;
      })
    },
    /**
     * 同意项目申请请求
     */
    applyProject(teacher_projectId){
      let that=this;
      that.$axios
      .get(api+`/college/applyTeacherProject/`+teacher_projectId)
      .then(function (response) {
        that.$message({
          message:response.data.message,
          type:response.data.code==200?'success':'error'
        })
      })
    },
    /**
     * 拒绝项目申请请求
     */
    refuseProject(teacher_projectId){
      let that=this;
      that.$axios
      .get(api+`/college/refuseTeacherProject/`+teacher_projectId)
      .then(function (response) {
        that.$message({
          message:response.data.message,
          type:response.data.code==200?'success':'error'
        })
        that.reload()
      })
    },
    /**
     * 得到处理过的奖项申请
     */
    getDoneApplyAwardsList(){
      let that=this;
      that.$axios
        .get(api+`/awards/getDoneApplyAwardsList/`+that.$store.state.id)
        .then(function (response) {
          that.doneApplyAwardsList=response.data;
          that.doneApplyAwardsList.forEach(function (item){
            that.srcDoList.push(that.urlImg+item.imageUrl);
          });
        })
    },
    /**
     * 得到未处理的奖项申请
     */
    getUndoApplyAwardsList(){
      let that=this;
      that.$axios
      .get(api+`/awards/getUndoApplyAwardsList/`+that.$store.state.id)
      .then(function (response) {
        that.undoApplyAwardsList=response.data;
        that.undoApplyAwardsList.forEach(function (item){
          that.srcUnList.push(that.urlImg+item.imageUrl);
        });
        console.log(that.undoApplyAwardsList.length);
      })
    },
    applyAwards(awardsId){
      let that=this;
      that.$axios
        .get(api+`/awards/applyAwards/`+awardsId)
        .then(function (response) {
          that.$message({
            type:response.data.code==200?'success':'error',
            message:response.data.message
          });
          that.reload();
        })
    },
    invokeAwards(awardsId){
      let that=this;
      that.$axios
      .get(api+`/awards/invokeAwards/`+awardsId)
      .then(function (response) {
        that.$message({
          type:response.data.code==200?'success':'error',
          message:response.data.message
        });
        that.reload();
      })
    },
    /**
     * 查看竞赛信息
     * @param compId
     */
    checkComp(compId){
      let that =this;
      that.compInfoVisiable=true;
      that.$axios
      .get(api+`/admin/checkComp/`+compId)
      .then(function (response) {
        that.compInfo=response.data;
      })
    },
    getDoneAddList(){
      let that=this;
      that.$axios
        .get(api+`/college/getDoneAddList/`+that.$store.state.id)
        .then(function (response) {
          that.doneAddList=response.data;
        })
    },
    getUndoAddList(){
      let that=this;
      that.$axios
      .get(api+`/college/getUndoAddList/`+that.$store.state.id)
      .then(function (response) {
        that.undoAddList=response.data;
      })
    },

    reApplyAddCommp(comp_collegeId){
      let that=this;
      that.$axios
        .get(api+`/college/reApplyAddCommp/`+comp_collegeId)
        .then(function (response) {
          that.$message({
            type:response.data.code==200?"success":"error",
            message:response.data.message
          })
        })
    }
  },
  filters:{
    dataTime: function(value) {
      if (value==undefined)
        return '-';
      value = new Date(value)
      if (value instanceof Date) {
        var d = value;
        var year=d.getFullYear();
        var m = d.getMonth() + 1;
        var day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate();
        var myDate = year + '年'+m + '月 ' + day +'日';
        return myDate;
      } else {
        return "-";
      }
    }
  },
  beforeDestroy() {
    clearInterval(this.time1);
    clearInterval(this.time2);
    clearInterval(this.time3);
    clearInterval(this.time4);
    clearInterval(this.time5);
    clearInterval(this.time6);
  }
}
</script>