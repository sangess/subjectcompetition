<template ::key="key">
  <div id="admin_comp">
    <el-card style="    margin-bottom: 20px; ">
      <div slot="header">
        <span style="font-size: 26px;">综合查询选项</span>
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
                  <el-option label="已结束" value=3></el-option>
                  <el-option label="备赛中" value=4></el-option>
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
    </el-card>
    <el-card class="boxc-ard" style="height: 700px">
      <div slot="header" class="clearfix">
        <span>竞赛列表</span>
        <el-button
          type="primary"
          size="medium"
          icon="el-icon-circle-plus-outline"
          style="float: right;"
          @click="addNewComp()"
          >添加新竞赛
        </el-button>
      </div>

      <el-table
        v-loading="loading"
        :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        stripe
        border
        height="550px"
        style="width: 100%;"
        class="elTable"
        @row-click="getDetails"
        :default-sort = "{prop: 'date', order: 'descending'}"
      >
        <el-table-column align="center" prop="id" label="#" width="50px" ></el-table-column>
        <el-table-column align="center" prop="compName" label="竞赛名称" sortable></el-table-column>
        <el-table-column align="center" prop="organizer" label="主办单位" sortable></el-table-column>
        <el-table-column align="center" prop="level" label="级 别"  sortable>
          <template slot-scope="scope">
            <p v-if="scope.row.level==0">国家级</p>
            <p v-if="scope.row.level==1">省级</p>
            <p v-if="scope.row.level==2">校级</p>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="subjectType" label="所属学科"></el-table-column>
        <el-table-column align="center" prop="occurrenceTime" label="竞赛时间" sortable>
          <template slot-scope="scope">
            {{scope.row.occurrenceTime|dataTime}}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="place" label="竞赛地点"></el-table-column>
        <el-table-column align="center" prop="竞赛项目" label="竞赛项目" >
          <template slot-scope="scope">
            <el-link type="primary" :underline="false" @click="checkProjects(scope.row.id)"><i class="el-icon-view el-icon--right"></i> 参赛项目列表</el-link>
            <!--<el-button size="mini" style="background-color: #009688"  plain @click="checkProjects(scope.row.id)">参赛项目列表</el-button>-->
          </template>
        </el-table-column>
        <el-table-column align="center" prop="compState" label="状态" sortable width="80px">
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
        <el-table-column align="center" label="操作" width="80px">
          <template slot-scope="scope">
              <el-tag type="info">
                <el-tooltip class="item" effect="dark" content="编辑" placement="left">
                <el-link icon="el-icon-edit" @click="dialogFormVisible=true">编辑</el-link>
                </el-tooltip>
              </el-tag>
             <!-- <el-button icon="el-icon-edit" type="info" circle @click="dialogFormVisible=true"></el-button>-->
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
          :page-size="8"
          @current-change="current_change"
          :current-page.sync="currentPage"
        ></el-pagination>
      </div>
    </el-card>
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
    <!-- 修改信息模态框 -->
    <el-dialog title="修改比赛信息" :span="8" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form  label-position="right" label-width="150px" :model="rowData" ref="form" :rules="rules">
        <el-form-item label="竞赛名称" prop="compName">
          <el-input v-model="rowData.compName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="主办单位" prop="organizer">
          <el-input v-model="rowData.organizer" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="竞赛地点" prop="place">
          <el-input v-model="rowData.place" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属学科" prop="subjectType">
          <el-input v-model="rowData.subjectType" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="最多项目参与数" prop="maxCountProject">
          <el-input v-model="rowData.maxCountProject" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="项目最多参与老师数" prop="maxCountTeacher">
          <el-input v-model="rowData.maxCountTeacher" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="项目最多参与学生数" prop="maxCountStudent">
          <el-input v-model="rowData.maxCountStudent" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="竞赛时间" >
          <el-date-picker
            v-model="rowData.occurrenceTime"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
            format="yyyy 年 MM 月 dd 日"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="可申请时间" >
        <el-date-picker
                v-model="rowData.canApplyTime"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"
                format="yyyy 年 MM 月 dd 日"
        ></el-date-picker>
      </el-form-item>
        <el-form-item label="截止参加时间" >
          <el-date-picker
                  v-model="rowData.notApplyTime"
                  type="date"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd"
                  format="yyyy 年 MM 月 dd 日"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="竞赛级别">
          <el-select v-model="rowData.level" placeholder>
            <el-option label="国家级" :value="0" ></el-option>
            <el-option label="省级"   :value="1"></el-option>
            <el-option label="校级"   :value="2" ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="竞赛状态">
          <el-select v-model="rowData.compState" placeholder>
            <el-option label="规划中" :value="0" ></el-option>
            <el-option label="报名中" :value="1" ></el-option>
            <el-option label="备赛中" :value="2" ></el-option>
            <el-option label="比赛中" :value="3" ></el-option>
            <el-option label="已结束" :value="4" ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editComp()">确定修改</el-button>
      </div>
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
export default {
  inject:['reload'],
  name: "admin_comp",
  data() {
    return {
      loading: true,
      pagesize: 8,
      currentPage: 1,
      dialogFormVisible: false,
      projectVisible: false,
      projectData: [],
      rowIndex: -1,
      rowData: {},
      tableData: [],
      searchFormData: {
        compName: "",
        organizer: "",
        level: "",
        subjectType: "",
        place: "",
        compState: ""
      },
      rules: {
        compName: [
          { required: true, message: "请输入竞赛名称", trigger: "blur" }
        ],
        organizer: [
          { required: true, message: "请输入主办单位", trigger: "blur" }
        ],
        place: [
          { required: true, message: "请输入竞赛地点", trigger: "blur" }
        ],
        subjectType: [
          { required: true, message: "请输入所属学科", trigger: "blur" }
        ],
        occurrenceTime: [
          { required: true, message: "请输选择竞赛时间", trigger: "blur" }
        ],
        level: [
          { required: true, message: "请输选择竞赛级别", trigger: "blur" }
        ],
        maxCountProject:[
          { required: true, message: '项目最大数不能为空，未要求可填写成999',trigger: "blur"},
          { type: 'number', message: '项目最大数必须为数字值',trigger: "blur"}
        ],
        maxCountTeacher:[
          { required: true, message: '项目中老师最大数不能为空，未要求可填写成999',trigger: "blur"},
          { type: 'number', message: '项目中老师最大数必须为数字值',trigger: "blur"}
        ],
        maxCountStudent:[
          { required: true, message: '项目中学生最大数不能为空，未要求可填写成999',trigger: "blur"},
          { type: 'number', message: '项目中老师最大数必须为数字值',trigger: "blur"}
        ]
      }
    };
  },
  mounted() {
    var that = this;
    that.loading = true;
    this.$axios
      .post(api+"/comp/getAllComps")
      .then(function(response) {
        that.tableData = response.data;
        that.loading = false;
      })
      .catch(function(error) {
        that.loading = false;
      });
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
     * 查看项目详情
     * @param row
     */
    getDetails(row) {
      // this.rowData = row;
      this.rowData = JSON.parse(JSON.stringify(row)); // 先转换为字符串，然后再转换
    },
    /**
     * 修改比赛信息
     * @param index
     */
    editComp() {
      let that = this;
      let comp = new FormData();
      comp.append("id", that.rowData.id);
      comp.append("compName", that.rowData.compName);
      comp.append("organizer", that.rowData.organizer);
      comp.append("level", that.rowData.level);
      comp.append("subjectType", that.rowData.subjectType);
      comp.append("occurrenceTime", that.rowData.occurrenceTime);
      comp.append("occurrenceTime", that.rowData.canApplyTime);
      comp.append("canApplyTime", that.rowData.occurrenceTime);
      comp.append("notApplyTime", that.rowData.notApplyTime);
      comp.append("maxCountTeacher", that.rowData.maxCountTeacher);
      comp.append("maxCountProject", that.rowData.maxCountProject);
      comp.append("place", that.rowData.place);
      comp.append("compState", that.rowData.compState);
      this.$confirm("确认修改？")
        .then(_ => {
          this.$axios
            .post(api+"/comp/addComp", comp)
            .then(function(response) {
              that.$set(that.tableData, that.rowData.id - 1, that.rowData);

              //提示信息
              that.$message({
                message: "修改成功！",
                type: "success"
              });
              //模态框关闭
              that.dialogFormVisible = false;
              that.reload();
            })
            .catch(function(error) {
              that.$message.error(error);
            });

          done();
        })
        .catch(_ => {});
    },
    /**
     *
     * @param compId
     */
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
    /**
     * 增加新竞赛  跳转页面
     */
    addNewComp() {
      this.$router.push(`/admin/comp/add`);
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
    }
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
  }
};
</script>