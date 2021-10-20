<template>
  <div id="admin_tongji" >
    <el-row :gutter="10" >
      <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <div  class="grid-content bg-purple" id="countCollegeJoinComp" style=" height: 130px; background-color: #fff; padding: 10px; ">
            <div class="title" >
              <span>当前所有参赛学生人数（比赛未结束）</span>
            </div>
            <div class="nextLine">
              <div class="count">
                <span class="title_count" :class="[studentJoinCompInfo.nowCount==0?'nochange':studentJoinCompInfo.nowCount>20?'changeAlot':'change']">{{studentJoinCompInfo.nowCount}}</span>
              </div>
              <div class="title_change">
                <div><p>昨日<i class="el-icon-caret-top" :class="[studentJoinCompInfo.yesterday==0?'nochange':studentJoinCompInfo.yesterday>5?'changeAlot':'change']">{{studentJoinCompInfo.yesterday}}</i></p></div>
                <div><p>一周<i class="el-icon-caret-top" :class="[studentJoinCompInfo.week==0?'nochange':studentJoinCompInfo.week>10?'changeAlot':'change']">{{studentJoinCompInfo.week}}</i></p></div>
              </div>
            </div>
          </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
        <div>
          <div  class="grid-content bg-purple" id="countCollegeApplyProject" style=" height: 130px; background-color: #fff; padding: 10px; ">
          <div class="title" >
            <span>当前所有学院参加竞赛数量（比赛未结束）</span>
          </div>
          <div class="nextLine">
            <div class="count">
              <span class="title_count" :class="[collegesJoinCompInfo.nowCount==0?'nochange':collegesJoinCompInfo.nowCount>10?'changeAlot':'change']">{{collegesJoinCompInfo.nowCount}}</span>
            </div>
            <div class="title_change">
              <div><p>昨日<i class="el-icon-caret-top" :class="[collegesJoinCompInfo.yesterday==0?'nochange':collegesJoinCompInfo.yesterday>5?'changeAlot':'change']">{{collegesJoinCompInfo.yesterday}}</i></p></div>
              <div><p>一周<i class="el-icon-caret-top" :class="[collegesJoinCompInfo.week==0?'nochange':collegesJoinCompInfo.week>15?'changeAlot':'change']">{{collegesJoinCompInfo.week}}</i></p></div>
            </div>
          </div>
        </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
        <div>
          <div  class="grid-content bg-purple" id="countStudentJoinProject" style=" height: 130px; background-color: #fff; padding: 10px; ">
          <div class="title" >
            <span>当前所有学院项目数量（比赛未结束）</span>
          </div>
          <div class="nextLine">
            <div class="count">
              <span class="title_count" :class="[collegeApplyCompInfo.nowCount==0?'nochange':collegeApplyCompInfo.nowCount>10?'changeAlot':'change']">{{collegeApplyCompInfo.nowCount}}</span>
            </div>
            <div class="title_change">
              <div><p>昨日<i class="el-icon-caret-top" :class="[collegeApplyCompInfo.yesterday==0?'nochange':collegeApplyCompInfo.yesterday>5?'changeAlot':'change']">{{collegeApplyCompInfo.yesterday}}</i></p></div>
              <div><p>一周<i class="el-icon-caret-top" :class="[collegeApplyCompInfo.week==0?'nochange':collegeApplyCompInfo.week>15?'changeAlot':'change']">{{collegeApplyCompInfo.week}}</i></p></div>
            </div>
          </div>
        </div>
        </div>
      </el-col>
    </el-row>
    <!--各学院参加比赛学生人次-->
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :xs="24" :sm="24" :md="13" :lg="13" :xl="13"><div><div  class="grid-content bg-purple" id="allStudentJoinProjectBydate" style=" height: 410px; background-color: #fff; padding: 20px; "></div></div></el-col>
      <el-col :xs="24" :sm="24" :md="11" :lg="11" :xl="11"><div><div  class="grid-content bg-purple" id="allStudentJoinProject" style=" height: 410px; background-color: #fff; padding: 20px; "></div></div></el-col>
    </el-row>
    <!--各学院参加竞赛-->
    <el-row :gutter="20" style="margin-top: 20px">
        <el-col :xs="24" :sm="24" :md="13" :lg="13" :xl="13"><div> <div class="grid-content bg-purple"  id="allCollegeJoinCompBydate" style=" height: 410px; background-color: #fff; padding: 20px; "></div></div></el-col>
        <el-col :xs="24" :sm="24" :md="11" :lg="11" :xl="11"><div><div  class="grid-content bg-purple" id="allCollegeJoinComp" style=" height: 410px; background-color: #fff; padding: 20px; "></div></div></el-col>
    </el-row>
    <!--各学院成立项目-->
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :xs="24" :sm="24" :md="13" :lg="13" :xl="13"><div> <div class="grid-content bg-purple"  id="allCollegeApplyProjectBydate" style=" height: 410px; background-color: #fff; padding: 20px; "></div></div></el-col>
      <el-col :xs="24" :sm="24" :md="11" :lg="11" :xl="11"><div> <div class="grid-content bg-purple"  id="allCollegeApplyProject" style=" height: 410px; background-color: #fff; padding: 20px; "></div></div></el-col>
    </el-row>
  </div>
</template>
<script>
  import api from '../../main.js'
  import * as echarts from 'echarts'
  export default {
    name: "admin_tongji",
    data() {
      return {
        tongji: {},
        proNum: 0,
        charts: '',
        studentJoinCompInfo:{},
        collegesJoinCompInfo:{},
        collegeApplyCompInfo:{},
        collegeJoinCompInfo:{},
        pip_allCollegeJoinComp:{},
        pip_allCollegeJoinCompByDate:{},
        pip_allStudentJoinProject:{},
        pip_allStudentJoinProjectByDate:{},
        pip_allCollegeApplyProject:{},
        pip_allCollegeApplyProjectByDate:{},
        time1:"",
        time2:"",
        time3:"",
        time4:"",
        time5:"",
        time6:"",
        time7:"",
        time8:"",
        time9:"",
      };
    },
    mounted() {

      /**
       * 根据时间日期获得学院参加竞赛评比信息
       */
      this.getAllCollegeJoinCompInfoByDate();

      /**
       * 学院参加竞赛评比
       */
      this.getAllCollegeJoinCompInfo();

      /**
       * 各学院参加比赛学生人次
       */
      this.getAllStudentJoinProject();

      /**
       * 各学院参加比赛学生人次根据日期
       */
      this.getAllStudentJoinProjectByDate();

      /**
       * 各学院成立竞赛项目数
       */
      this.getAllCollegeApplyProject();

      /**
       * 各学院参加比赛项目数量根据日期
       */
      this.getAllCollegeApplyProjectByDate();

      this.getStudentJoinCompInfo();

      this.getCollegesJoinCompInfo();

      this.getCollegeApplyCompInfo();

      this.time1=setInterval(this.getCollegeApplyCompInfo,1500);
      this.time2=setInterval(this.getCollegesJoinCompInfo,1500);
      this.time3=setInterval(this.getStudentJoinCompInfo,1500);
      this.time7=setInterval(this.getAllCollegeApplyProject,1500);
      this.time8=setInterval(this.getAllStudentJoinProject,1500);
      this.time9=setInterval(this.getAllCollegeJoinCompInfo,1500);
      this.time4=setInterval(this.getAllCollegeApplyProjectByDate,1000*60*60);
      this.time5=setInterval(this.getAllStudentJoinProjectByDate,1000*60*60);
      this.time6=setInterval(this.getAllCollegeJoinCompInfoByDate,1000*60*60);
    },
    methods:{

      /**
       * 学院参加竞赛评比
       */
      getAllCollegeJoinCompInfo(){
        let that =this;
        that.$axios
          .get(api+`/data/getAllCollegeJoinCompInfo`)
          .then(function (response) {
            that.pip_allCollegeJoinComp=response.data;
            //学院申请竞赛比较
            let allCollegeJoinComp = echarts.init(document.getElementById("allCollegeJoinComp"))
            allCollegeJoinComp.setOption({
              title: {
                text: '各学院参加竞赛次数(总数：'+ that.pip_allCollegeJoinComp.sum+')',
                x: 'center',//中间字的位置
                y: 'bottom',//
                orient: 'horizontal',
                textStyle: {
                  fontSize: 16,
                  fontWeight: 'bolder',
                  color: '#363636'
                },
              },
              tooltip: {
                trigger: 'item'
              },
              legend: {
                orient: 'vertical',
                icon: 'circle',
                x: 'left',//标签的位置
                y:'top'//图的位置
              },
              series: [
                {
                  name: '各学院参加竞赛次数',
                  type: 'pie',
                  radius: '50%',
                  center: ['60%', '50%'],  // 设置饼状图位置，第一个百分数调水平位置，第二个百分数调垂直位置
                  label: {
                    show: true,
                    padding: [0, -120],
                    overflow: 'none',
                    fontSize: '12',
                    fontWeight: 'bold',
                    position: 'outer',
                    formatter: '{b}\n\n{c}'
                  },

                  labelLine: {
                    show: true,
                    length: 30,
                    length2: 100
                  },
                  itemStyle: {
                    emphasis: {
                      shadowBlur: 30,
                      shadowOffsetX: 20,
                      shadowColor: 'rgba(30, 144, 255，0.5)'
                    }
                  },
                  data: that.pip_allCollegeJoinComp.collegeInfo
                }
              ]
            })

          })
      },

      /**
       * 根据时间日期获得学院参加竞赛评比信息
       */
      getAllCollegeJoinCompInfoByDate(){
        let that =this;
        that.$axios
        .get(api+`/data/getAllCollegeJoinCompInfoByDate`)
        .then(function (response) {
          that.pip_allCollegeJoinCompByDate=response.data;
          // 按月份统计学院申请竞赛数量
          let allCollegeJoinCompBydate = echarts.init(document.getElementById("allCollegeJoinCompBydate"))
          allCollegeJoinCompBydate.setOption({
            title: {
              text: '各院系过去一年参加比赛'
            },
            // 提示框
            tooltip: {
              trigger: 'axis'
            },
            // 图例
            legend: {
              icon: 'circle',
              orient: 'vertical',
              left: 'left',
              top: 'center',
              data: that.pip_allCollegeJoinCompByDate.collegeNames
            },
            grid: {
              left: '20%',
              right: '3%',
              bottom: '3%',
              containLabel: true
            },
            // 工具栏
            toolbox: {
              feature: {
                saveAsImage: {
                  type: 'png'
                },
                magicType: {
                  type: ['line', 'bar', 'stack']
                }
              }
            },
            xAxis: {
              type: 'category',
              boundaryGap: false,
              data: that.pip_allCollegeJoinCompByDate.date
            },
            yAxis: {
              type: 'value'
            },
            series:that.pip_allCollegeJoinCompByDate.collegeInfo
          })
        })
      },

      /**
       * 各学院参加比赛学生人次
       */
      getAllStudentJoinProject(){
        let that =this;
        that.$axios
        .get(api+`/data/getAllStudentJoinProjectInfo`)
        .then(function (response) {
          that.pip_allStudentJoinProject=response.data;
          let allStudentJoinProject = echarts.init(document.getElementById("allStudentJoinProject"))
          allStudentJoinProject.setOption({
            title: {
              text: '各学院参加竞赛学生人次(总人次：'+ that.pip_allStudentJoinProject.sum+')',
              x: 'center',//中间字的位置
              y: 'bottom',//
              orient: 'horizontal',
              textStyle: {
                fontSize: 16,
                fontWeight: 'bolder',
                color: '#363636'
              },
            },
            tooltip: {
              trigger: 'item'
            },
            legend: {
              orient: 'vertical',
              icon: 'circle',
              x: 'left',//标签的位置
              y:'top'//图的位置
            },
            series: [
              {
                name: '院系竞赛学生人次',
                type: 'pie',
                radius: '50%',
                center: ['60%', '50%'],  // 设置饼状图位置，第一个百分数调水平位置，第二个百分数调垂直位置
                label: {
                  show: true,
                  padding: [0, -120],
                  overflow: 'none',
                  fontSize: '12',
                  fontWeight: 'bold',
                  position: 'outer',
                  formatter: '{b}\n\n{c}'
                },

                labelLine: {
                  show: true,
                  length: 30,
                  length2: 100
                },
                itemStyle: {
                  emphasis: {
                    shadowBlur: 30,
                    shadowOffsetX: 20,
                    shadowColor: 'rgba(30, 144, 255，0.5)'
                  }
                },
                data: that.pip_allStudentJoinProject.collegeInfo
              }
            ]
          })

        })
      },

      /**
       * 各学院参加比赛学生人次根据日期
       */
      getAllStudentJoinProjectByDate(){
        let that =this;
        that.$axios
        .get(api+`/data/getAllStudentJoinProjectByDate`)
        .then(function (response) {
          that.pip_allStudentJoinProjectByDate=response.data;
          // 按月份统计学院申请竞赛数量
          let allStudentJoinProjectBydate = echarts.init(document.getElementById("allStudentJoinProjectBydate"))
          allStudentJoinProjectBydate.setOption({
            title: {
              text: '各院系学生过去一年参加比赛情况'
            },
            // 提示框
            tooltip: {
              trigger: 'axis'
            },
            // 图例
            legend: {
              icon: 'circle',
              orient: 'vertical',
              left: 'left',
              top: 'center',
              data: that.pip_allStudentJoinProjectByDate.collegeNames
            },
            grid: {
              left: '20%',
              right: '3%',
              bottom: '3%',
              containLabel: true
            },
            // 工具栏
            toolbox: {
              feature: {
                saveAsImage: {
                  type: 'png'
                },
                magicType: {
                  type: ['line', 'bar', 'stack']
                }
              }
            },
            xAxis: {
              type: 'category',
              boundaryGap: false,
              data: that.pip_allStudentJoinProjectByDate.date
            },
            yAxis: {
              type: 'value'
            },
            series:that.pip_allStudentJoinProjectByDate.collegeInfo
          })
        })
      },

      /**
       * 各学院成立竞赛项目数
       */
      getAllCollegeApplyProject(){
        let that =this;
        that.$axios
        .get(api+`/data/getAllCollegeApplyProjectInfo`)
        .then(function (response) {
          that.pip_allCollegeApplyProject=response.data;
          //学院申请竞赛比较
          let allCollegeApplyProject = echarts.init(document.getElementById("allCollegeApplyProject"))
          allCollegeApplyProject.setOption(
              {
                title: {
                  text: '各学院成立竞赛项目次数(总項目数：'+ that.pip_allCollegeApplyProject.sum+')',
                  x: 'center',//中间字的位置
                  y: 'bottom',//
                  orient: 'horizontal',
                  textStyle: {
                    fontSize: 16,
                    fontWeight: 'bolder',
                    color: '#363636'
                  },
                },
                tooltip: {
                  trigger: 'item'
                },
                legend: {
                  orient: 'vertical',
                  icon: 'circle',
                  x: 'left',//标签的位置
                  y:'top'//图的位置
                },
                series: [
                  {
                    name: '院系内成立项目次数',
                    type: 'pie',
                    radius: '50%',
                    center: ['60%', '50%'],  // 设置饼状图位置，第一个百分数调水平位置，第二个百分数调垂直位置
                    label: {
                      show: true,
                      padding: [0, -120],
                      overflow: 'none',
                      fontSize: '12',
                      fontWeight: 'bold',
                      position: 'outer',
                      formatter: '{b}\n\n{c}'
                    },

                    labelLine: {
                      show: true,
                      length: 30,
                      length2: 100
                    },
                    itemStyle: {
                      emphasis: {
                        shadowBlur: 30,
                        shadowOffsetX: 20,
                        shadowColor: 'rgba(30, 144, 255，0.5)'
                      }
                    },
                    data: that.pip_allCollegeApplyProject.collegeInfo
                  }
                ]
              }
              )
        })
      },

      /**
       * 各学院参加比赛项目数量根据日期
       */
      getAllCollegeApplyProjectByDate(){
        let that =this;
        that.$axios
        .get(api+`/data/getAllCollegeApplyProjectByDate`)
        .then(function (response) {
          that.pip_allCollegeApplyProjectByDate=response.data;
          // 按月份统计学院申请竞赛数量
          let allCollegeApplyProjectBydate = echarts.init(document.getElementById("allCollegeApplyProjectBydate"))
          allCollegeApplyProjectBydate.setOption({
            title: {
              text: '各院系过去一年成立项目情况'
            },
            // 提示框
            tooltip: {
              trigger: 'axis'
            },
            // 图例
            legend: {
              icon: 'circle',
              orient: 'vertical',
              left: 'left',
              top: 'center',
              data: that.pip_allCollegeApplyProjectByDate.collegeNames
            },
            grid: {
              left: '20%',
              right: '3%',
              bottom: '3%',
              containLabel: true
            },
            // 工具栏
            toolbox: {
              feature: {
                saveAsImage: {
                  type: 'png'
                },
                magicType: {
                  type: ['line', 'bar', 'stack']
                }
              }
            },
            xAxis: {
              type: 'category',
              boundaryGap: false,
              data: that.pip_allCollegeApplyProjectByDate.date
            },
            yAxis: {
              type: 'value'
            },
            series:that.pip_allCollegeApplyProjectByDate.collegeInfo
          })
        })
      },

      getStudentJoinCompInfo(){
        let that=this;
        that.$axios
         .get(api+`/data/getStudentJoinCompInfo`)
         .then(function (response) {
           that.studentJoinCompInfo=response.data;
         })
      },

      getCollegesJoinCompInfo(){
        let that=this;
        that.$axios
        .get(api+`/data/getCollegesJoinCompInfo`)
        .then(function (response) {
          that.collegesJoinCompInfo=response.data;
        })
      },

      getCollegeApplyCompInfo(){
        let that=this;
        that.$axios
        .get(api+`/data/getCollegeApplyCompInfo`)
        .then(function (response) {
          that.collegeApplyCompInfo=response.data;
        })
      },
    },
    beforeDestroy() {
      clearInterval(this.time1);
      clearInterval(this.time2);
      clearInterval(this.time3);
      clearInterval(this.time4);
      clearInterval(this.time5);
      clearInterval(this.time6);
      clearInterval(this.time7);
      clearInterval(this.time8);
      clearInterval(this.time9);
    }
  };
</script>
<style>
 .title{
   margin: 0 auto;
   text-align: center;
   font-size: 18px;
 }
  .title_count{
    margin-top: 20px;
    position: absolute;
    font-size: 30px;
  }
  .nextLine{
    padding: 0;
    margin-top: 10px;
  }
  .count{
    margin-left: 50%;
    display: inline;
  }
  .title_change{
    display: inline;
    float: right;
    font-size: 13px;
  }
  .noChange{
    color: #a1a3a3;
  }
  .change{
    color: #50a3a2;
  }
  .changeAlot{
    color: #a32d34;
  }
</style>

