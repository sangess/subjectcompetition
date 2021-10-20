<template>
  <div id="teacher_apply">
    <el-card>
      <div slot="header"></div>
      <div>
        <el-tabs  v-model="collegeOrStudentOrTeacher">
          <el-tab-pane label="学院申请" name="college">
            <el-tabs v-model="activeApply" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoCollgeApplyList" style="width: 100%;align-items: center">
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
                      <el-tag type="success" >{{scope.row.applyOrJoin==1?'已发出申请':'已重新发出申请'}}</el-tag>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneCollgeApplyList" style="width: 100%;align-items: center">
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
                        <span v-text="scope.row.applyOrJoin==3?'申请已通过':'申请被驳回'"></span>
                      </el-tag>
                      <el-tooltip v-if="scope.row.applyOrJoin==2" style="margin-left: 50px" class="item" effect="dark" content="重新申请" placement="right">
                        <el-button type="primary" icon="el-icon-refresh-right"  circle @click="applyProjectFormVisible=true,compId=scope.row.project.comp.id"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane label="学生申请" name="student">
            <el-tabs v-model="activeStudentApply" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoApplyProjectStudentList" style="width: 100%;align-items: center">
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
                  <el-table-column label="操作" >
                    <template slot-scope="scope">
                      <el-tooltip class="item" effect="dark" content="同意申请" placement="top">
                        <el-button type="primary" icon="el-icon-circle-check"  circle @click="applyStudentJoinProject(scope.row.id)"></el-button>
                      </el-tooltip>
                      <el-tooltip class="item" effect="dark" content="驳回申请" placement="top">
                        <el-button type="warning" icon="el-icon-circle-close"  circle @click="refuseStudentJoinProject(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneApplyProjectStudentList" style="width: 100%;align-items: center">
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
                  <el-table-column  label="处理人">
                    <template slot-scope="scope">
                      <span>{{scope.row.project.createByTeacher.nickname}}老师</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="project.comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column prop="applyOrJoin" label="处理情况" >
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.applyOrJoin==3?'success':'danger'">
                        <span v-text="scope.row.applyOrJoin==3?'申请已通过':'申请被驳回'"></span>
                      </el-tag>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane label="老师申请" name="teacher">
            <el-tabs v-model="activeTeacherApply" :tab-position="tabPosition">
              <el-tab-pane label="未处理" name="undo">
                <el-table border stripe  :data="undoApplyProjectTeacherList" style="width: 100%;align-items: center">
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
                  <el-table-column  label="申请人">
                    <template slot-scope="scope">
                      <span>{{scope.row.teacher.nickname}}老师</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="project.comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column label="目前状态" >
                    <template slot-scope="scope">
                      <el-tag type="success" >{{scope.row.applyOrJoin==1?'已发出申请':'已重新发出申请'}}</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column label="操作" >
                    <template slot-scope="scope">
                      <el-tooltip v-if="scope.row.project.createByTeacher.id==teacherId" class="item" effect="dark" content="同意申请" placement="top">
                        <el-button type="primary" icon="el-icon-circle-check"  circle @click="applyTeacherJoinProject(scope.row.id)"></el-button>
                      </el-tooltip>
                      <el-tooltip v-if="scope.row.project.createByTeacher.id==teacherId" class="item" effect="dark" content="驳回申请" placement="top">
                        <el-button type="warning" icon="el-icon-circle-close"  circle @click="refuseTeacherJoinProject(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="已处理" name="done">
                <el-table border stripe  :data="doneApplyProjectTeacherList" style="width: 100%;align-items: center">
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
                  <el-table-column  label="处理人">
                    <template slot-scope="scope">
                      <span>{{scope.row.project.createByTeacher.nickname}}老师</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="project.comp.compName" label="申请竞赛" ></el-table-column>
                  <el-table-column prop="applyOrJoin" label="处理情况" >
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.applyOrJoin==3?'success':'danger'">
                        <span v-text="scope.row.applyOrJoin==3?'申请已通过':'申请驳回'"></span>
                      </el-tag>
                      <el-tooltip v-if="scope.row.project.createByTeacher.id!=teacherId&scope.row.applyOrJoin==2" style="margin-left: 50px" class="item" effect="dark" content="重新申请" placement="right">
                        <el-button type="primary" icon="el-icon-refresh-right"  circle @click="teacherReApplyJoinProject(scope.row.id)"></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
        </el-tabs>
        <!---------------------------------项目申请填写的弹出框------------------------------------>
        <el-dialog title="项目重新申报"  :visible.sync="applyProjectFormVisible">
          <el-form :model="applyProjectForm" ref="applyProjectForm" style="width: 300px":label-width="formLabelWidth" label-position="left" :rules="rules">
            <el-form-item label="申请老师" >
              <el-input :value="this.$store.state.nickname" disabled autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="项目名称" prop="projectName">
              <el-input v-model="applyProjectForm.projectName"  autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="项目预支" prop="pay">
              <el-input type="age" v-model.number="applyProjectForm.pay"  autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="applyProjectFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="applyProject('applyProjectForm')">确认申报</el-button>
          </div>
        </el-dialog>
        <!--项目申请填写内容-->
<!--
        <el-dialog title="申请发送内容" :visible.sync="dialogFormVisible">
          <el-form :model="contentForm">
            <el-form-item label="内容" :label-width="formLabelWidth">
              <el-input :value="contentForm.content" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
          </div>
        </el-dialog>-->
      </div>
    </el-card>
  </div>
</template>
<script>
import { formatDate} from "../../assets/js/utils.js";
import api from '../../main.js'
export default {
  inject:['reload'],
  name:"teacher_apply",
  data(){
    return {
      applyProjectFormVisible:false,
      formLabelWidth:"80px",
      activeStudentApply:"undo",
      activeTeacherApply:"undo",
      applyProjectForm:{},
      contentForm:{},
      teacherId:"",
      collegeOrStudentOrTeacher:"college",
      activeApply:"done",
      tabPosition:"left",
      undoCollgeApplyList:[],//未处理的学院和老师的申请
      doneCollgeApplyList:[],//处理过的学院和老师的申请
      undoApplyProjectStudentList:[],//未处理的学生和老师的申请
      doneApplyProjectStudentList:[],//处理过的学生和老师的申请
      undoApplyProjectTeacherList:[],//未处理的学生和老师的申请
      doneApplyProjectTeacherList:[],//处理过的学生和老师的申请
      rules:{
        projectName:[
          { required: true, message: "请输入项目名称", trigger: "blur" }
        ],
        pay:[
          { required: true, message: '项目预支不能为空',trigger: "blur"},
          { type: 'number', message: '项目预支必须为数字值',trigger: "blur"}
        ]
      },
      time1:"",
      time2:"",
      time3:"",
      time4:"",
      time5:"",
      time6:"",
    }
  },
  mounted() {
    this.teacherId=this.$store.state.id;
    this.getUndoCollgeApplyList();
    this.getDoneCollgeApplyList();
    this.getUndoApplyProjectStudentList();
    this.getDoneApplyProjectStudentList();
    this.getUndoApplyProjectTeacherList();
    this.getDoneApplyProjectTeacherList();
    this.time1=setInterval(this.getUndoCollgeApplyList,1500);
    this.time2=setInterval(this.getDoneCollgeApplyList,1500);
    this.time3=setInterval(this.getUndoApplyProjectStudentList,1500);
    this.time4=setInterval(this.getDoneApplyProjectStudentList,1500);
    this.time5=setInterval(this.getUndoApplyProjectTeacherList,1500);
    this.time6=setInterval(this.getDoneApplyProjectTeacherList,1500);
  },
  methods:{
    /**
     * 得到未处理的学院和教师之间的申请
     */
    getUndoCollgeApplyList(){
      let that=this;
      let teacherId=that.$store.state.id;
      that.$axios
        .get(api+`/teacher/getUndoCollgeApplyList/`+teacherId)
        .then(function (response) {

          that.undoCollgeApplyList=response.data;
        })
    },

    /**
     * 得到未处理的学院和教师之间的申请
     */
    getDoneCollgeApplyList(){
      let that=this;
      let teacherId=that.$store.state.id;
      that.$axios
      .get(api+`/teacher/getDoneCollgeApplyList/`+teacherId)
      .then(function (response) {

        that.doneCollgeApplyList=response.data;
      })
    },

    /**
     * 重新申请
     * @param collegeCompId
     */
    applyProject(formName){
      let that=this;
      var teacherId=that.$store.state.id;//教师的id
      let projectForm=new FormData();

      projectForm.append("projectName",that.applyProjectForm.projectName);
      projectForm.append("pay",that.applyProjectForm.pay);
      projectForm.append("compId",that.compId);
      projectForm.append("teacherId",that.$store.state.id);
      that.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios
          .post(api+`/teacher/applyJoinComp/`,projectForm)
          .then(function (response) {

            var state=response.data.code;
            that.$message({
              message:response.data.message,
              type:state==200?'success':'warning'
            });
            that.reload();
          })
        }else {
          that.$message.error("请正确填写表格！");
          return false;
        }
      });
    },

    /**
     * 得到未处理的学生申请项目之间的申请
     */
    getUndoApplyProjectStudentList(){
      let that=this;
      let teacherId=that.$store.state.id;
      that.$axios
        .get(api+`/teacher/getUndoApplyProjectStudentList/`+teacherId)
        .then(function (response) {
          that.undoApplyProjectStudentList=response.data;
        })
    },

    /**
     * 同意学生加入项目*/
    applyStudentJoinProject(student_projectId){
      let that =this;
      that.$axios
      .get(api+`/teacher/applyStudentJoinProject/`+student_projectId)
      .then(function (response) {
        that.$message({
          message:response.data.message,
          type:response.data.code==200?'success':'warning'
        })
        that.reload();
      })
    },

    /**
     * 拒绝学生加入项目*/
    refuseStudentJoinProject(student_projectId){
      let that =this;
      that.$axios
      .get(api+`/teacher/refuseStudentJoinProject/`+student_projectId)
      .then(function (response) {
        that.$message({
          message:response.data.message,
          type:response.data.code==200?'success':'warning'
        })
        that.reload();
      })
    },

    /**
     * 得到处理过的学生申请项目之间的申请
     */
    getDoneApplyProjectStudentList(){
      let that=this;
      let teacherId=that.$store.state.id;
      that.$axios
      .get(api+`/teacher/getDoneApplyProjectStudentList/`+teacherId)
      .then(function (response) {
        that.doneApplyProjectStudentList=response.data;
      })
    },

    /**
     * 得到未处理的老师和申请项目之间的申请
     */
    getUndoApplyProjectTeacherList(){
      let that=this;
      let teacherId=that.$store.state.id;
      that.$axios
      .get(api+`/teacher/getUndoApplyProjectTeacherList/`+teacherId)
      .then(function (response) {
        that.undoApplyProjectTeacherList=response.data;
      })
    },

    /**
     * 得到处理过的老师和申请项目之间的申请
     */
    getDoneApplyProjectTeacherList(){
      let that=this;
      let teacherId=that.$store.state.id;
      that.$axios
      .get(api+`/teacher/getDoneApplyProjectTeacherList/`+teacherId)
      .then(function (response) {
        that.doneApplyProjectTeacherList=response.data;
      })
    },

    /**
     * 老师重新申请加入项目*/
    teacherReApplyJoinProject(Teacher_projectId){
      let that =this;
      that.$axios
      .get(api+`/teacher/teacherReApplyJoinProject/`+Teacher_projectId)
      .then(function (response) {
        that.$message({
          message:response.data.message,
          type:response.data.code==200?'success':'warning'
        })
        that.reload();
      })
    },

    /**
     * 同意老师加入项目*/
    applyTeacherJoinProject(Teacher_projectId){
      let that =this;
      that.$axios
      .get(api+`/teacher/applyTeacherJoinProject/`+Teacher_projectId)
      .then(function (response) {
        that.$message({
          message:response.data.message,
          type:response.data.code==200?'success':'warning'
        })
        that.reload();
      })
    },

    /**
     * 拒绝学生加入项目*/
    refuseTeacherJoinProject(Teacher_projectId){
      let that =this;
      that.$axios
      .get(api+`/teacher/refuseTeacherJoinProject/`+Teacher_projectId)
      .then(function (response) {
        that.$message({
          message:response.data.message,
          type:response.data.code==200?'success':'warning'
        })
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
    clearInterval(this.time3);
    clearInterval(this.time4);
    clearInterval(this.time5);
    clearInterval(this.time6);
  }
}
</script>