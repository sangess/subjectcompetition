<template>
  <el-card>
    <el-tabs  v-model="unOrEd" :tab-position="tabPosition">
      <el-tab-pane label="未评分的作品" name="un">
        <el-table :data="unMarkProjects" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="right" style="background-color: rgba(146,255,152,0.58);border-radius: 20px" >
                <el-form-item inline  v-for="(workFile,index) in props.row.workFiles" :key ="workFile.id" >
                  <el-row :gutter="40">
                    <el-col :span="2"> <span style="color: #1402bf;float: right">{{ fileTip+String((index+1))}}</span></el-col>
                    <el-col :span="10"> <span>{{ workFile.fileName}}</span></el-col>
                    <el-col :span="3"><a :href='api+"/file/downloadFile/"+workFile.id'><el-link icon="el-icon-download" :underline="false">下载附件</el-link></a></el-col>
                  </el-row>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="竞赛 ID" prop="project.comp.id"></el-table-column>
          <el-table-column label="竞赛名" prop="project.comp.compName"></el-table-column>
          <el-table-column label="项目名" prop="project.projectName"></el-table-column>
          <el-table-column label="作品名" prop="workName"></el-table-column>
          <el-table-column label="评分">
            <template slot-scope="scope">
              <el-input v-model="input" size="mini" style="width: 50px;margin-right: 20px" :id=work+scope.row.id></el-input>
              <el-popconfirm title="确认评分吗？" @confirm="markWork(scope.row.id)">
                <el-button type="success" size="mini"  slot="reference" >保存</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="已评分的比赛" name="ed">
        <el-table :data="markWorkProjects" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="right" style="background-color: rgba(146,255,152,0.58);border-radius: 20px" >
                <el-form-item inline  v-for="(work,index) in props.row.projectList" :key ="work.id" >
                  <el-row :gutter="40" >
                    <el-col :span="8"> <span style="color: #1402bf;float: right">名次：{{String((index+1))}}</span></el-col>
                    <el-col :span="8"> <span>作品名：{{work.workName}}</span></el-col>
                    <el-col :span="8"> <span>院系评分:{{work.score}}</span></el-col>
                  </el-row>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="竞赛 ID" prop="compId"></el-table-column>
          <el-table-column label="竞赛名" prop="compName"></el-table-column>
          <el-table-column label="截至报名日期" prop="notApplyTime">
            <template slot-scope="scope">
              {{scope.row.notApplyTime|dataTime}}
            </template>
          </el-table-column>
          <el-table-column label="比赛时间" prop="occurrenceTime">
            <template slot-scope="scope">
              {{scope.row.occurrenceTime|dataTime}}
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script>
  import api from '../../main'
  export default {
    name: "college_work",
    inject:['reload'],
    data(){
      return{
        work:"work",
        input:0,
        fileTip:"附件",
        api:api,
        tabPosition:"left",
        unOrEd:"un",
        unMarkProjects:[],
        markWorkProjects:[],
      }
    },

    mounted() {
      this.getUnMarkProjects();
      this.getMarkWorkProjects();
    },
    methods:{
      getMarkWorkProjects(){
        let that=this;
        that.$axios
          .get(api+'/college/getMarkWorkProjects/'+that.$store.state.id)
          .then(function (response) {
            that.markWorkProjects=response.data;
            console.log(that.markWorkProjects);
          });

      },

      getUnMarkProjects(){
        let that=this;
        that.$axios
        .get(api+'/college/getUnMarkProjects/'+that.$store.state.id)
        .then(function (response) {
          that.unMarkProjects=response.data;
        })
      },

      markWork(workId){
        let that=this;
        let id=this.work+workId;
        let score=document.getElementById(id).value;
        if(score<=0){
          that.$message.error(score+"分实在太低了！请重新打分");
        }else if(score>=100){
          that.$message.error("请在1-99分中评分！");
        }else{
          that.$axios
            .get(api+"/college/markWork/"+score+"/"+workId)
            .then(function (response) {
              that.$message({
                type:response.data.code=200?"success":"error",
                message:response.data.message
              });
              that.reload();
            })
        }
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
    }
  }
</script>

<style scoped>

</style>



