<template ::key="key">
  <div id="teacher_comp">
    <el-card style="    margin-bottom: 20px; height: 100%">
      <div slot="header">

        <el-row :gutter="10">
          <el-col :span="8">
            <span>竞赛列表</span>
          </el-col>
          <el-col :span="16">
            <el-button type="text" style="float:right;" @click="导出为Excel('全部竞赛')">导出为Excel</el-button>
          </el-col>
        </el-row>
      </div>
      <div>
        <el-row :gutter="10">
          <el-form ref="searchFormData" :model="searchFormData" label-width="80px">
            <el-col :span="4">
              <el-form-item label="竞赛名称" prop="compName">
                <el-input v-model="searchFormData.compName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="主办单位" prop="organizer">
                <el-input v-model="searchFormData.organizer"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="竞赛级别" prop="level">
                <el-select v-model="searchFormData.level" placeholder="请选择竞赛级别">
                  <el-option label="国家级" value="0"></el-option>
                  <el-option label="省级" value="1"></el-option>
                  <el-option label="校级" value="2"></el-option>
                  <el-option label="全部" value></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="所属学科" prop="subjectType">
                <el-input v-model="searchFormData.subjectType"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="竞赛地点" prop="place">
                <el-input v-model="searchFormData.place"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="竞赛状态" prop="compState">
                <el-select v-model="searchFormData.compState" placeholder="请选择竞赛状态">
                  <el-option label="规划中" value=0></el-option>
                  <el-option label="报名中" value=1></el-option>
                  <el-option label="备赛中" value=2></el-option>
                  <el-option label="进行中" value=3></el-option>
                  <el-option label="已结束" value=4></el-option>
                  <el-option label="全部" value></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
        <el-row>
          <el-col :span="3" :offset="21">
            <el-button
              type="primary"
              size="medium"
              icon="el-icon-search"
              style="    float: right;"
              @click="moreSearch()"
            >筛选 / 查询</el-button>
          </el-col>
        </el-row>
      </div>
      <el-table
              id="全部竞赛"
        v-loading="loading"
        :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        stripe
        border
        height="400px"
        style="width: 100%;"
        class="elTable"
        @row-click="getDetails"
        :default-sort = "{prop: 'date', order: 'descending'}"
      >
        <el-table-column align="center" prop="id" label="#" width="50px" sortable></el-table-column>
        <el-table-column align="center" prop="compName" label="竞赛名称" sortable></el-table-column>
        <el-table-column align="center" prop="organizer" label="主办单位" sortable></el-table-column>
        <el-table-column align="center" prop="level" label="级 别" sortable>
          <template slot-scope="scope">
            <p v-if="scope.row.level==0">国家级</p>
            <p v-if="scope.row.level==1">省级</p>
            <p v-if="scope.row.level==2">校级</p>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="subjectType" label="所属学科" sortable></el-table-column>
        <el-table-column align="center" prop="occurrenceTime" label="竞赛时间" sortable>
          <template slot-scope="scope">
            {{scope.row.occurrenceTime|dataTime}}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="place" label="竞赛地点" sortable></el-table-column>
        <el-table-column align="center" prop="竞赛项目" label="竞赛项目" >
          <template slot-scope="scope">
            <el-link type="primary" :underline="false" @click="checkProjects(scope.row.id)"><i class="el-icon-view el-icon--right"></i> 参赛项目列表</el-link>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="compState" label="状态" sortable>
          <template slot-scope="scope">
            <el-tag  effect="dark"
                     :type="scope.row.compState === 0 ? 'info' :       /*规划中*/
              (scope.row.compState === 1 ? 'primary':       /*可报名*/
              (scope.row.compState === 2 ? 'success':       /*备赛中*/
              (scope.row.compState === 3 ? 'warning':       /*比赛中*/
              'danger')))"
                     disable-transitions
            >
              <span v-if="scope.row.compState==0">规划中</span>
              <span v-if="scope.row.compState==1">报名中</span>
              <span v-if="scope.row.compState==2">备赛中</span>
              <span v-if="scope.row.compState==3">比赛中</span>
              <span v-if="scope.row.compState==4">已结束</span>
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="申请状态" width="80px" >
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" :content="scope.row.applyState==3?'申请项目':(scope.row.applyState==1||scope.row.applyState==4?'学院正在申请，请耐心等待':'学院尚未申请竞赛，无法申请项目')" placement="top">
              <el-button :icon="scope.row.applyState==3?'el-icon-folder-add':'el-icon-folder-delete'" :type="scope.row.applyState==3?'success':'info'" :disabled="scope.row.applyState!=3" circle @click="applyProjectFormVisible = true,compId=scope.row.id"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <div id="fenye">
        <!-- 设置layout，表示需要显示的内容，用逗号分隔，布局元素会依次显示。
          prev表示上一页，next为下一页，pager表示页码列表，
          除此以外还提供了jumper和total，size和特殊的布局符号->，->后的元素会靠右显示，
          jumper表示跳页元素，
          total表示总条目数，
          page-size  每页多少条数据
          @current-change="current_change" //currentPage改变时会触发
        size用于设置每页显示的页码数量。-->
        <el-pagination
          background
          layout="prev, pager, next"
          :total="tableData.length"
          :page-size="12"
          @current-change="current_change"
          :current-page.sync="currentPage"
        ></el-pagination>
      </div>
    </el-card>
    <!---------------------------------项目申请填写的弹出框------------------------------------>
    <el-dialog title="项目申报"  :visible.sync="applyProjectFormVisible">
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
    <!-- 竞赛项目展示模态框 -->
    <el-dialog title="竞赛项目列表" :visible.sync="projectVisible">
      <el-table :data="projectData" stripe border style="width: 100%" max-height="500">
        <el-table-column align="center" prop="id" label="项目编号" width="80px"></el-table-column>
        <el-table-column align="center" prop="projectName" label="项目名称"></el-table-column>
        <el-table-column align="center" prop="createByTeacher.college.nickname" label="所属学院"></el-table-column>
        <el-table-column align="center" prop="createByTeacher.nickname" label="指导老师" width="120px"></el-table-column>
        <el-table-column align="center" prop="pay" label="预算"></el-table-column>
        <el-table-column align="center" prop="applyState" label="状态">
          <template slot-scope="scope">
            <el-tag
                    :type="scope.row.applyState === 1 ? 'success' : 'warning'"
                    disable-transitions
            >{{scope.row.applyState==1?'已审核':'未审核'}}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>
<style>
.clearfix {
  font-size: 26px;
}
#fenye {
  text-align: center;
  margin-top: 20px;
}
</style>
<script>
  import api from '../../main.js'
  import {exportExcel} from "../../assets/js/utils";
export default {
  inject:['reload'],
  name: "teacher_comp",
  data() {
    return {
      compId:"",
      formLabelWidth:"80px",
      applyProjectForm:{},
      loading: true,
      pagesize: 12,
      currentPage: 1,
      applyProjectFormVisible: false,
      projectVisible: false,
      projectData: [],
      rowIndex: -1,
      rowData: {
        id: 0,
        compName: "",
        organizer: "",
        level: "",
        subjectType: "",
        occurrenceTime: "",
        place: "",
        compState: ""
      },
      tableData: [],
      searchFormData: {
        compName: "",
        organizer: "",
        level: "",
        subjectType: "",
        place: "",
        compState: ""
      },
      college_comp:[],
      rules:{
        projectName:[
          { required: true, message: "请输入项目名称", trigger: "blur" }
        ],
        pay:[
          { required: true, message: '项目预支不能为空',trigger: "blur"},
          { type: 'number', message: '项目预支必须为数字值',trigger: "blur"}
        ]
      },
      time:"",
    };
  },

  mounted() {
    this.getAllComps();
    this.time=setInterval(this.getAllComps,1500);
  },
  methods: {
    /**
     * 模糊查询
     */
    moreSearch() {
      let that = this;
      let searchData = new FormData();
      searchData.append("compName", that.searchFormData.compName);
      searchData.append("organizer", that.searchFormData.organizer);
      searchData.append("level", that.searchFormData.level);
      searchData.append("subjectType", that.searchFormData.subjectType);
      searchData.append("place", that.searchFormData.place);
      searchData.append("compState", that.searchFormData.compState);
      this.$axios
        .post(api+"/comp/moreSearch", searchData)
        .then(function(response) {
          that.tableData = response.data;
        })
        .catch(function(error) {
          that.$message.error("系统错误，操作失败，请稍后再试!");
        });
    },
    /**
     * 申请项目
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
    getDetails(row) {
      // this.rowData = row;
      this.rowData = JSON.parse(JSON.stringify(row)); // 先转换为字符串，然后再转换
    },
    checkProjects(compId) {
      let that = this;
      that.projectVisible = true;
      this.$axios
      .get(
          api+`/comp/getProjectLists/`+compId
      )
      .then(function(response) {
        that.projectData = response.data;
      })
      .catch(function(error) {
      });
    },
    current_change(currentPage) {
      //改变当前页
      this.currentPage = currentPage;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    /**
     * 得到所有的竞赛信息
     */
    getAllComps(){
      let that = this;
      that.loading = true;
      let teacherId=that.$store.state.id;
      this.$axios
      .get(api+"/teacher/getCollegeCompListByTeacherId/"+teacherId)
      .then(function(response) {
        that.tableData = response.data;
        that.loading = false;
      })
      .catch(function(error) {
        that.loading = false;
      });
    },
    导出为Excel(domname) {
      let excelname =  domname;
      exportExcel(domname, excelname);
    },
  },
  computed: {},
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
    clearInterval(this.time);
  }
};
</script>