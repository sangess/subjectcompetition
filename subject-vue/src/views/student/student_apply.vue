<template>
  <div id="teacher_apply">
    <el-card>
      <div slot="header"></div>
      <div>
        <el-tabs  v-model="collegeOrStudent">
          <el-tab-pane label="比赛项目申请" name="teacher">
            <el-tabs v-model="activeApply" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoApplyProjectList" style="width: 100%;align-items: center">
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
                  <el-table-column prop="student.nickname" label="申请人"></el-table-column>
                  <el-table-column prop="project.comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column label="目前状态" >
                    <template slot-scope="scope">
                      <el-tag type="success" >{{scope.row.applyOrJoin==1?'已发出申请':'已重新发出申请'}}</el-tag>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneApplyProjectList" style="width: 100%;align-items: center">
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
                  <el-table-column prop="student.nickname" label="申请人"></el-table-column>
                  <el-table-column prop="project.comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column prop="applyOrJoin" label="处理情况" >
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.applyOrJoin==3?'success':'danger'">
                        <span v-text="scope.row.applyOrJoin==3?'申请已同意':'申请驳回'"></span>
                      </el-tag>
                      <el-tooltip v-if="scope.row.applyOrJoin==2" style="margin-left: 50px" class="item" effect="dark" content="重新申请" placement="right">
                        <el-button type="primary" icon="el-icon-refresh-right"  circle @click="reApplyJoinProject(scope.row.id)"></el-button>
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
  </div>
</template>
<script>
import api from '../../main.js'
export default {
  inject:['reload'],
  name:"teacher_apply",
  data(){
    return {
      applyProjectFormVisible:false,
      formLabelWidth:"80px",
      applyProjectForm:{},
      compId:"",
      collegeOrStudent:"teacher",
      activeApply:"undo",
      tabPosition:"left",
      undoApplyProjectList:[],
      doneApplyProjectList:[],
      time1:"",
      time2:"",
    }
  },
  mounted() {
    this.getUndoApplyProjectList();
    this.getDoneApplyProjectList();
    this.time1=setInterval(this.getUndoApplyProjectList,1500);
    this.time2=setInterval(this.getDoneApplyProjectList,1500);
  },
  methods:{
    /**
     * 根据学生的id返回学生的未处理的请求
     * @param studentId
     * @return
     */
    getUndoApplyProjectList(){
      let that=this;
      let studentId=that.$store.state.id;
      that.$axios
        .get(api+`/student/getUndoApplyProjectList/`+studentId)
        .then(function (response) {
          that.undoApplyProjectList=response.data;
        })
    },
    /**
     * 根据学生的id返回学生的处理过的请求
     * @param studentId
     * @return
     */
    getDoneApplyProjectList(){
      let that=this;
      let studentId=that.$store.state.id;
      that.$axios
      .get(api+`/student/getDoneApplyProjectList/`+studentId)
      .then(function (response) {
        that.doneApplyProjectList=response.data;
      })
    },
    /**
     * 重新申请
     * @param collegeCompId
     */
    reApplyJoinProject(student_projectId){
      let that=this;
      let studentId=that.$store.state.id;
      that.$axios
        .get(api+`/student/reApplyJoinProject/`+student_projectId)
        .then(function (response) {
          that.$message({
            type:response.data.code==200?'success':'warning',
            message:response.data.message
          });
          that.reload();
        })
    },
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
  }
}
</script>