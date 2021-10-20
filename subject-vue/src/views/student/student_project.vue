<template>
  <div id="college_project">
    <el-card style="    margin-bottom: 20px;">
      <div slot="header">
        <el-row :gutter="10">
          <el-col :span="8">
            <span style="font-size: 26px;">项目列表</span>
          </el-col>
          <el-col :span="16">
            <el-button type="text" style="float:right;" @click="导出为Excel()">导出为Excel</el-button>
          </el-col>
        </el-row>
      </div>
      <div>
        <el-row :gutter="10">
          <el-form ref="searchProjects" :model="searchProjects" label-width="80px">
            <el-col :span="4">
              <el-form-item label="项目名称" prop="name">
                <el-input v-model="searchProjects.projectName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="所属竞赛" prop="suoshujingsai">
                <el-select v-model="searchProjects.compId" placeholder="所属竞赛">
                  <el-option v-for="item in allComp"
                             :key="item.id"
                             :label="item.compName"
                             :value="item.id">
                  </el-option>
                  <el-option label="全部" value></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="审核状态" prop="zhuangtai">
                <el-select v-model="searchProjects.applyState" placeholder="请选择项目状态">
                  <el-option label="未审核" value=0></el-option>
                  <el-option label="审核通过" value=1></el-option>
                  <el-option label="审核驳回" value=2></el-option>
                  <el-option label="全部" value></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <el-button
                      type="primary"
                      size="medium"
                      icon="el-icon-search"
                      style="float: right;"
                      @click="findProjectInCollege()"
              >筛选 / 查询</el-button>
            </el-col>
            <el-col :span="4">
              <el-button
                      type="primary"
                      size="medium"
                      icon="el-icon-search"
                      style="float: right;"
                      @click="checkAll()"
              >查看全部</el-button>
            </el-col>
          </el-form>
        </el-row>
      </div>
      <div>
        <el-table
                id="竞赛项目列表"
                class="elTable"
                :data="projectData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                stripe
                border
                height="560px"
                style="width: 100%"
                :default-sort = "{prop: 'date', order: 'descending'}"
        >
          <el-table-column align="center"  label="项目编号" width="80px" sortable>
            <template slot-scope="scope">
              {{scope.$index + 1}}
            </template>
          </el-table-column>
          <el-table-column align="center"  label="项目名称">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" size="mini" content="点击查看项目信息" placement="top-end">
                <el-button type="text" @click="showProjectInfo(scope.row.id)">{{scope.row.projectName}}</el-button>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="comp.compName" label="所属竞赛" sortable ></el-table-column>
          <el-table-column align="center" prop="comp.compState" label="所属竞赛状态" sortable>
            <template slot-scope="scope">
              <el-tag  effect="dark"
                       :type="scope.row.comp.compState === 0 ? 'info' :       /*规划中*/
              (scope.row.comp.compState === 1 ? 'primary':       /*可报名*/
              (scope.row.comp.compState === 2 ? 'success':       /*备赛中*/
              (scope.row.comp.compState === 3 ? 'warning':       /*比赛中*/
              'danger')))"
                       disable-transitions
              >
                <span v-if="scope.row.comp.compState==0">规划中</span>
                <span v-if="scope.row.comp.compState==1">报名中</span>
                <span v-if="scope.row.comp.compState==2">备赛中</span>
                <span v-if="scope.row.comp.compState==3">比赛中</span>
                <span v-if="scope.row.comp.compState==4">已结束</span>
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column align="center"  label="指导老师" width="120px" sortable><!--prop="createByTeacher.nickName"-->
            <template slot-scope="scope">
              <span>{{scope.row.createByTeacher.nickname}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="pay" label="预算" sortable></el-table-column>
          <el-table-column align="center" prop="applyState" label="项目申请状态" sortable>
            <template slot-scope="scope">
              <el-tag v-if="scope.row.applyState == 0" type="danger" disable-transitions>审核未通过</el-tag>
              <el-tag v-if="scope.row.applyState == 1" :type="scope.row.applyState === 1 ? 'success' : 'warning'" disable-transitions>院系已通过审核</el-tag>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作" width="115px">
            <template slot-scope="scope">
              <el-button
                      v-if="scope.row.applyState == 1"
                      size="mini"
                      type="primary"
                      @click="applyJoinProject(scope.row.id)"
              >申请加入</el-button>
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
                  :total="projectData.length"
                  :page-size="12"
                  @current-change="current_change"
                  :current-page.sync="currentPage"
          ></el-pagination>
        </div>
      </div>
    </el-card>
    <!-- 学生列表模态框 -->
    <el-dialog title="项目成员" :visible.sync="dialogTableVisible">
      <el-table :data="studentData">
        <el-table-column property="id" label="#" width="50"></el-table-column>
        <el-table-column property="studentNumber" label="学号"></el-table-column>
        <el-table-column property="name" label="姓名"></el-table-column>
        <el-table-column property="sex" label="性别"></el-table-column>
        <el-table-column property="age" label="年龄"></el-table-column>
        <el-table-column property="zhuanye" label="所学专业"></el-table-column>
      </el-table>
    </el-dialog>
    <!-- 竞赛项目展示模态框 -->
    <el-dialog title="项目信息" :visible.sync="projectVisible" width="70%">
      <el-table  stripe border :data="projectInfo" >
        <el-table-column align="center" label="项目编号" prop="id" ></el-table-column>
        <el-table-column align="center" prop="projectName" label="项目名称"></el-table-column>
        <el-table-column align="center" prop="comp.compName" label="所属竞赛"></el-table-column>
        <el-table-column align="center" label="参与学生人数" >
          <template slot-scope="scope">
            <span v-if="scope.row.countStudent<scope.row.comp.maxCountStudent">{{scope.row.countStudent}}人</span>
            <span v-if="scope.row.countStudent>=scope.row.comp.maxCountStudent">{{scope.row.countStudent}}人(满员)</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="参与教师人数" >
          <template slot-scope="scope">
            <span v-if="scope.row.countTeacher<scope.row.comp.maxCountTeacher">{{scope.row.countTeacher}}人</span>
            <span v-if="scope.row.countTeacher>=scope.row.comp.maxCountTeacher">{{scope.row.countTeacher}}人(满员)</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="createByTeacher.nickname" label="指导老师" ></el-table-column>
        <el-table-column align="center" prop="createByTeacher.username" label="指导老师用户名" ></el-table-column>
        <el-table-column align="center" label="预算">
          <template slot-scope="scope">
            <span>{{scope.row.pay}}元</span>
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
  import { exportExcel } from "../../assets/js/utils.js";
  import api from '../../main.js'
  export default {
    inject:['reload'],
    name: "college_project",
    data() {
      return {
        studentId:"",
        allComp:[],
        pagesize: 12,
        currentPage: 1,
        projectVisible:false,
        dialogTableVisible: false,
        projectInfo:[],
        studentData: [],
        projectData: [],
        searchProjects: {
          projectName:"",
          compId:"",
          applyState:"",
        }
      };
    },
    mounted() {
      this.studentId=this.$store.state.id;
      this.getAllComp();
      this.getProjectData();
    },
    methods: {
      getAllComp(){
        let that=this;
        that.$axios
        .post(api+`/comp/getAllComps`)
        .then(function (response) {
          that.allComp=response.data;
        })
      },
      /**
       * 得到院系所有的项目
       */
      getProjectData() {
        let that = this;
        let studentId=that.$store.state.id;
        this.$axios
        .get(api+`/student/getProjectByCollegeWithStudent/`+studentId)
        .then(function(response) {
          that.projectData = response.data;
        })
        .catch(function(error) {
          console.error(error);
        });
      },
      checkAll(){
        let that=this;
        that.reload();
      },
      findProjectInCollege() {
        let that = this;
        let searchForm=new FormData();
        searchForm.append("projectName",that.searchProjects.projectName);
        searchForm.append("comp.id",that.searchProjects.compId);
        searchForm.append("applyState",that.searchProjects.applyState);
        searchForm.append("studentId",that.studentId);
        this.$axios
        .post(api+`/project/getMoreSearchProjectByStudent`,searchForm)
        .then(function(response) {
          that.projectData = response.data;
        })
        .catch(function(error) {
          that.$message.error("系统错误，操作失败，请稍后再试!");
        });
      },
      导出为Excel() {
        let excelname = this.$store.state.nickname + "竞赛项目";
        exportExcel("竞赛项目列表", excelname);
      },
      showProjectInfo(projectId){
        let that=this;
        that.projectVisible=true;
        that.$axios
        .get(api+`/teacher/getProjectById/`+projectId)
        .then(function (response) {
          let newArray=[];
          newArray.push(response.data);
          that.projectInfo=newArray;
        })

      },
      /*申请加入项目*/
      applyJoinProject(projectId){
        let that=this;
        let studentId=that.$store.state.id;
        that.$axios
          .get(api+`/student/applyJoinProject/`+projectId+`/`+studentId)
          .then(function (response) {
              that.$message({
                type:response.data.code==200?'success':'warning',
                message:response.data.message
              })
          })
      },
      current_change(currentPage) {
        this.currentPage = currentPage;
      },
    }
  };
</script>