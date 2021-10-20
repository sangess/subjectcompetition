<template>
  <div id="admin_apply">
    <el-card>
      <el-tabs  v-model="applyType">
        <el-tab-pane label="参加竞赛申请" name="collegeJoinComp">
          <el-tabs v-model="activeApply" :tab-position="tabPosition">
            <el-tab-pane label="未处理" name="未处理">
              <el-table border stripe  :data="undoApplyList" style="width: 100%;align-items: center">
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
                <el-table-column label="操作" >
                  <template slot-scope="scope">
                    <el-tooltip class="item" effect="dark" content="同意申请" placement="top">
                      <el-button type="primary" icon="el-icon-circle-check"  circle @click="applyComp(scope.row.id)"></el-button>
                    </el-tooltip>
                    <el-tooltip class="item" effect="dark" content="驳回申请" placement="top">
                      <el-button type="warning" icon="el-icon-circle-close"  circle @click="refuseComp(scope.row.id)"></el-button>
                    </el-tooltip>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="已处理" name="已处理">
              <el-table border stripe  :data="doneApplyList" style="width: 100%;align-items: center">
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
                <el-table-column prop="applyOrJoin" label="处理情况" >
                  <template slot-scope="scope">
                    <el-tag :type="scope.row.applyOrJoin==2?'danger':'success'">
                      <span v-text="scope.row.applyOrJoin==3?'申请同意':'申请驳回'"></span>
                    </el-tag>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
        <el-tab-pane label="添加竞赛申请" name="collegeAddComp">
          <el-tabs v-model="activeApply2" :tab-position="tabPosition">
            <el-tab-pane label="未处理" name="未处理">
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
                <el-table-column align="center" label="操作" >
                  <template slot-scope="scope">
                    <el-tooltip class="item" effect="dark" content="同意申请" placement="top">
                      <el-button type="primary" icon="el-icon-circle-check"  circle @click="applyAddCommp(scope.row.id)"></el-button>
                    </el-tooltip>
                    <el-tooltip class="item" effect="dark" content="驳回申请" placement="top">
                      <el-button type="warning" icon="el-icon-circle-close"  circle @click="refuseAddComp(scope.row.id)"></el-button>
                    </el-tooltip>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="已处理" name="已处理">
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
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
      </el-tabs>
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
    name: "admin_apply",
    data() {
      return {
        compInfo:[],
        compInfoVisiable:false,
        applyType:"collegeJoinComp",
        tabPosition: "left",
        activeApply: "未处理",
        activeApply2: "未处理",
        undoApplyList: [],
        doneApplyList: [],
        undoAddList:[],
        doneAddList:[],
        time1:"",
        time2:"",
        time3:"",
        time4:"",
      }

    },
    mounted() {
      this.displayUndoApplyList();
      this.displayDoneApplyList();
      this.displayUndoAddCompList();
      this.displayDoneAddCompList();
      this.time1=setInterval(this.displayUndoApplyList,1500);
      this.time2=setInterval(this.displayDoneApplyList,1500);
      this.time3=setInterval(this.displayUndoAddCompList,1500);
      this.time4=setInterval(this.displayDoneAddCompList,1500);
    },
    methods: {
      /**
       * 得到未处理的比赛申请集合
       */
      displayUndoApplyList() {
        let that = this;
        that.$axios
        .post(api+`/admin/getUndoApplyCompList`)
        .then(function (response) {
          that.undoApplyList = response.data;
        })
      },

      /**
       * 得到已处理的比赛申请集合
       */
      displayDoneApplyList() {
        let that = this;
        that.$axios
        .post(api+`/admin/getDoneApplyCompList`)
        .then(function (response) {
          that.doneApplyList = response.data;
        })
      },

      /**
       * 同意申请
       */
      applyComp(id){
        let that=this;
        that.$axios
          .get(api+`/admin/applyComp/`+id)
          .then(function (response) {
            that.$message({
              message:response.data.message,
              type:response.data.code==200?'success':'error'
            })
            that.displayUndoApplyList()
          })
      },

      /**
       * 拒绝申请
       */
      refuseComp(id){
        let that=this;
        that.$axios
        .get(api+`/admin/refuseComp/`+id)
        .then(function (response) {
          that.$message({
            message:response.data.message,
            type:response.data.code==200?'success':'error'
          })
          that.displayDoneApplyList()
        })
      },

      /**
       * 获得申请添加竞赛未处理列表
       */
      displayUndoAddCompList(){
        let that=this;
        that.$axios
          .get(api+`/admin/getUndoAddCompList`)
          .then(function (response) {
            that.undoAddList=response.data;
          })
      },

      /**
       * 获得申请添加竞赛处理过的列表
       */
      displayDoneAddCompList(){
        let that=this;
        that.$axios
        .get(api+`/admin/getDoneAddCompList`)
        .then(function (response) {
          that.doneAddList=response.data;
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

      /**
       * 同意院系创建比赛
       * @param college_compId
       */
      applyAddCommp(college_compId){
        let that=this;
        that.$axios
          .get(api+`/admin/applyAddCommp/`+college_compId)
          .then(function (response) {
            that.$message({
              type:response.data.code==200?'success':'error',
              message:response.data.message
            });
          })
      },

      /**
       * 拒绝院系创建比赛
       * @param college_compId
       */
      refuseAddComp(college_compId){
        let that=this;
        that.$axios
        .get(api+`/admin/refuseAddComp/`+college_compId)
        .then(function (response) {
          that.$message({
            type:response.data.code==200?'success':'error',
            message:response.data.message
          });
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
    }
  }
</script>

<style scoped>

</style>