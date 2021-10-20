<template>
  <div id="admin_comp_add" >
    <el-card style="width: 100%; height:1200px">
      <div slot="header">
        <el-button
          @click="$router.back(-1)"
          type="default"
          style="float:left"
          icon="el-icon-arrow-left"
        >返回</el-button>
      </div>
      <div style="text-align:center;">
        <div >
          <h1 style="font-size: 28px;">申请添加新竞赛</h1>
        </div>
        <el-col :span="10" :offset="6">
          <div class="body_form" >
            <el-form label-position="right" label-width="150px" :model="formData" ref="formData" :rules="rules" >

              <el-form-item label="竞赛名称" prop="compName">
                <el-input v-model="formData.compName" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="主办单位" prop="organizer">
                <el-input v-model="formData.organizer" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="竞赛地点" prop="place">
                <el-input v-model="formData.place" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="所属学科" prop="subjectType">
                <el-input v-model="formData.subjectType" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="最多项目组" prop="maxCountProject">
                <el-input type="age" v-model.number="formData.maxCountProject" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="项目中老师最多人数" prop="maxCountTeacher">
                <el-input type="age" v-model.number="formData.maxCountTeacher" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="项目中学生最多人数" prop="maxCountStudent">
                <el-input type="age" v-model.number="formData.maxCountStudent" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="竞赛时间" prop="occurrenceTime">
                <el-date-picker
                  v-model="formData.occurrenceTime"
                  type="date"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd"
                  format="yyyy 年 MM 月 dd 日"
                  :picker-options="occurrenceTimeRule"
                ></el-date-picker>
              </el-form-item>

              <el-form-item label="可申请时间" prop="canApplyTime">
                <el-date-picker
                  v-model="formData.canApplyTime"
                  type="date"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd"
                  format="yyyy 年 MM 月 dd 日"
                  :picker-options="canApplyTimeRule"
                ></el-date-picker>
              </el-form-item>

              <el-form-item label="截止申请时间" prop="notApplyTime">
                <el-date-picker
                  v-model="formData.notApplyTime"
                  type="date"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd"
                  format="yyyy 年 MM 月 dd 日"
                  :picker-options="notApplyTimeRule"
                ></el-date-picker>
              </el-form-item>

              <el-form-item label="竞赛级别" prop="level">
                <el-select v-model="formData.level" placeholder>
                  <el-option label="国家级" value="0"></el-option>
                  <el-option label="省级" value="1"></el-option>
                  <el-option label="校级" value="2"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item style="margin-top: 30px;margin-bottom: 30px;">
                <el-button style="float:left;width: 250px" type="primary" @click="applyCreateComp('formData')">申请添加比赛</el-button>
                <el-button style="float:right" @click="重置('formData')">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </div>
    </el-card>
  </div>
</template>

<script>
  import api from '../../main.js'
export default {
  name: "admin_comp_add",
  data() {
    return {
      formData: {},
      occurrenceTimeRule:{
        disabledDate:time =>{
          return time.getTime()<Date.now();
        }
      },
      canApplyTimeRule:{
        disabledDate:time =>{
          if(this.formData.occurrenceTime){
            return (new Date(this.formData.occurrenceTime).getTime()-24 * 3600 * 1000 < time.getTime() | time.getTime()<Date.now());
          }
        }
      },
      notApplyTimeRule:{
        disabledDate:time =>{
          if(this.formData.occurrenceTime){
            return (new Date(this.formData.occurrenceTime).getTime()-24 * 3600 * 1000<time.getTime() | time.getTime()<Date.now());
          }
        }
      },
      rules: {
        compName: [
          { required: true, message: "请输入竞赛名称", trigger: "blur" }
          //   { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
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
        canApplyTime: [
          { required: true, message: "请输选择可申请时间", trigger: "blur" }
        ],
        notApplyTime: [
          { required: true, message: "请输选择截止申请时间", trigger: "blur" }
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
  methods: {
    重置(formName) {
      this.$refs[formName].resetFields();
    },
    applyCreateComp(formName) {

      var that = this;
      let comp = new FormData();

      comp.append("compName", that.formData.compName);
      comp.append("organizer", that.formData.organizer);
      comp.append("level", that.formData.level);
      comp.append("subjectType", that.formData.subjectType);
      comp.append("occurrenceTime", that.formData.occurrenceTime);
      comp.append("canApplyTime", that.formData.canApplyTime);
      comp.append("notApplyTime", that.formData.notApplyTime);
      comp.append("place", that.formData.place);
      comp.append("maxCountProject", that.formData.maxCountProject);
      comp.append("maxCountTeacher", that.formData.maxCountTeacher);
      comp.append("maxCountStudent", that.formData.maxCountStudent);
      comp.append("compState", 0);
      comp.append("countProject", 0);
      comp.append("collegeId",that.$store.state.id);

      that.$refs[formName].validate(valid => {
        if (valid) {
          that.$axios
            .post(api+"/college/applyCreateComp", comp)
            .then(function(response) {
              //提示信息
              that.$message({
                message: response.data.message,
                type: response.data.code==200?"success" : "error"
              });
              that.$router.back(-1);
            })
            .catch(function(error) {
              that.$message.error(error);
            });
        } else {
          that.$message.error("请正确填写表格！");
          return false;
        }
      });
    }
  }
};
</script>