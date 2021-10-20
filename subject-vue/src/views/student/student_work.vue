<template>
  <el-card>
    <el-tabs  v-model="workOrUpload">
      <el-tab-pane label="作品提交" name="upload">
        <el-form ref="form" style="width: 520px"  :model="form" label-width="120px" :rules="rules">
      <el-form-item  label="作品名" prop="workName">
        <el-input v-model="form.workName" name="workName"></el-input>
      </el-form-item>
      <el-form-item  label="上交的项目" prop="projectId">
        <el-select v-model="form.projectId" name="projectId" placeholder="请选择需要上交的项目">
          <el-option v-for="project in myProjects"
                      :key="project.id"
                      :label="project.projectName"
                      :value="project.id"
                      :disabled="project.comp.compState>2">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="上传文件">
        <el-upload
                name="files"
                ref="upload"
                action="/url"
                :limit="5"
                multiple
                :file-list="fileList"
                :with-credentials="true"
                :on-change="uploadChange"
                :on-remove="handleRemove"
                :auto-upload="false">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">最多上传5个文件，单个文件大小不得超过100MB,一共大小不得超过200MB</div>
        </el-upload>
        <!--
                :data="formData"
                :on-success="upFile"
                :on-remove="handleRemove"
                :on-exceed="handleExceed"-->
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitUpload('form')">确认上交</el-button>
      </el-form-item>
    </el-form>
      </el-tab-pane>
      <el-tab-pane label="作品管理" name="work">
        <el-tabs  v-model="unOrEd" :tab-position="tabPosition">
          <el-tab-pane label="已提交" name="Ed">
            <el-table :data="handOutWorks" style="width: 100%">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-form label-position="right" style="background-color: rgba(146,255,152,0.58);border-radius: 20px" >
                    <el-form-item inline  v-for="(workFile,index) in props.row.workFiles" :key ="workFile.id" >
                      <el-row :gutter="40">
                        <el-col :span="2"> <span style="color: #1402bf;float: right">{{ fileTip+String((index+1))}}</span></el-col>
                        <el-col :span="10"> <span>{{ workFile.fileName}}</span></el-col>
                        <el-col :span="3"><a :href='api+"/file/downloadFile/"+workFile.id'><el-link icon="el-icon-download" :underline="false">下载附件</el-link></a></el-col>
                        <el-col :span="3"><el-link icon="el-icon-edit" @click="editId=workFile.id,dialogTableVisible=true" :underline="false">修改</el-link></el-col>
                        <el-col :span="3">
                          <el-popconfirm title="删除该文件？操作不可逆！" @confirm="deleteWorkFile(workFile.id)">
                            <el-link icon="el-icon-delete"  slot="reference" :underline="false">删除</el-link>
                          </el-popconfirm>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column label="竞赛 ID" prop="project.comp.id"></el-table-column>
              <el-table-column label="竞赛名" prop="project.comp.compName"></el-table-column>
              <el-table-column label="项目名" prop="project.projectName"></el-table-column>
              <el-table-column label="作品名" prop="workName"></el-table-column>
              <el-table-column label="作品得分" prop="score">
                <template slot-scope="scope">
                  {{scope.row.score}}{{scope.row.ifMark==false?'(未打分)':''}}
                </template>
              </el-table-column>
        </el-table>
          </el-tab-pane>
          <el-tab-pane label="待提交" name="un">
            <el-table :data="unHandOutWorks" style="width: 100%">
              <el-table-column label="竞赛 ID" prop="comp.id"></el-table-column>
              <el-table-column label="竞赛名" prop="comp.compName"></el-table-column>
              <el-table-column label="项目名" prop="projectName"></el-table-column>
              <el-table-column label="截至报名日期" prop="comp.notApplyTime">
                <template slot-scope="scope">
                  {{scope.row.comp.notApplyTime|dataTime}}
                </template>
              </el-table-column>
              <el-table-column label="比赛时间" prop="comp.occurrenceTime">
                <template slot-scope="scope">
                  {{scope.row.comp.occurrenceTime|dataTime}}
                </template>
              </el-table-column>
              <el-table-column label="状态" prop="projectName">
                <el-button type="warning" plain size="mini" @click="workOrUpload='upload'">尚未提交作品</el-button>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已评分" name="mark">
            <el-table :data="markWorks" style="width: 100%">
              <el-table-column label="竞赛 ID" prop="project.comp.id"></el-table-column>
              <el-table-column label="竞赛名" prop="project.comp.compName"></el-table-column>
              <el-table-column label="项目名" prop="project.projectName"></el-table-column>
              <el-table-column label="作品名" prop="workName"></el-table-column>
              <el-table-column label="作品得分" prop="score">
                <template slot-scope="scope">
                  {{scope.row.score}}{{scope.row.score==0?'(未打分)':''}}
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-tab-pane>
    </el-tabs>
    <el-dialog  title="修改文件" :visible.sync="dialogTableVisible">
      <el-upload
              name="edit"
              action="/url"
              :limit="1"
              :file="file"
              :with-credentials="true"
              :on-change="editChange"
              :on-remove="handleRemove"
              :auto-upload="false">
        <el-button size="small" type="primary">选择文件</el-button>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible=false">取消修改</el-button>
        <el-button type="primary" @click="editWorkFile()">确认修改</el-button>
      </div>
    </el-dialog>
  </el-card>

</template>

<script>

  import api from "../../main";
  export default {
    inject:['reload'],
    name: "student_work",
    data() {
      return {
        markWorks:[],
        editId:"",
        file:"",
        dialogTableVisible:false,
        api:api,
        fileTip:"附件",
        handOutWorks:[],
        unHandOutWorks:[],
        workOrUpload:"upload",
        tabPosition:'left',
        unOrEd:'Ed',
        form:{
          workName:"",
          projectId:"",
        },
        fileList:[],
        myProjects:[],
        rules:{
          workName:[
              { required: true, message: "请填写作品名称", trigger: "blur" }
          ],
          projectId:[
            { required: true, message: "请选择提交的项目", trigger: "blur" }
          ]
        },
      }
    },
    mounted(){
      this.showMyComps();
      this.getHandOutWorks();
      this.getUnHandOutWorks();
      this.getWorksMark();
    },
    methods:{
      /**
       * 显示学生所有的项目
       */
      showMyComps(){
        let that=this;
        let studentId=that.$store.state.id;
        that.$axios
        .get(api+`/project/getStudentProjects/`+studentId)
        .then(function (response) {
          that.myProjects=response.data;
        })
      },
      submitUpload(form){
        let that =this;
        let dataForm=new FormData();
        that.$refs[form].validate(valid=>{
          if(valid){
            dataForm.append("workName",that.form.workName);
            dataForm.append("projectId",that.form.projectId);
            for(let i=0;i<that.fileList.length;i++){
              dataForm.append("uploadFile",that.fileList[i].raw);
            }
            that.$axios({
              url:api+`/student/workUpload`,
              method:"post",
              data:dataForm,
              headers: {
                "Content-Type":"multipart/form-data"
              }
            })
            .then(function (response) {
              that.$message({
                type:response.data.code==200?'success':'error',
                message:response.data.message
              });
              that.reload();
              that.workOrUpload="work";
            })
          }else{
            that.$message.error("请正确填写作品信息！");
            return false;
          }
        })
      },
      uploadChange(file, filelist){
        this.fileList=filelist;
      },
      editChange(file, filelist){
        let that=this;
        that.file=filelist;
      },
      // 文件移除时的钩子
      handleRemove(file, fileList) {
        this.filelist = fileList
      },
      /**
       * 得到交过作品的列表
       */
      getHandOutWorks(){
        let that =this;
        that.$axios
          .get(api+'/student/getHandOutWorks/'+that.$store.state.id)
          .then(function (response) {
            that.handOutWorks=response.data;
          })
      },
      /**
       * 得到待提交作品的列表
       */
      getUnHandOutWorks(){
        let that =this;
        that.$axios
        .get(api+'/student/getUnHandOutWorks/'+that.$store.state.id)
        .then(function (response) {
          that.unHandOutWorks=response.data;
          console.log(that.unHandOutWorks);
        })
      },
      editWorkFile(workFileId){
        let that =this;
        console.log(that.file[0].raw);
        console.log(that.editId);
        let data=new FormData();
        data.append("workFileId",that.editId);
        data.append("workFile",that.file[0].raw);
        that.$axios({
          url:api+`/student/editWork`,
          method:"post",
          data:data,
          headers: {
            "Content-Type":"multipart/form-data"
          }
        })
          .then(function (response) {
            that.$message({
              type:response.data.code==200?'success':'error',
              message:response.data.message
            });
            that.reload();
            that.workOrUpload="work";
          })
      },
      deleteWorkFile(workFileId){
        let that=this;
        that.$axios
          .get(api+"/student/deleteWorkFile/"+workFileId)
          .then(function (response) {
            that.$message({
              type:response.data.code==200?'success':'error',
              message:response.data.message
            });
            that.reload();
            that.workOrUpload="work";
          })
      },
      getWorksMark(){
        let that =this;
        that.$axios
        .get(api+'/student/getWorksMark/'+that.$store.state.id)
        .then(function (response) {
          that.markWorks=response.data;
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
    }
  }
</script>

<style>
</style>