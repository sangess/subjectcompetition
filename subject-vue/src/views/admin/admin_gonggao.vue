<template>
  <div id="admin_gonggao">
    <el-card >
      <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <!-- ============================公告信息列表====================================== -->
          <el-tab-pane label="公告信息列表" name="公告信息列表" >
            <!-- 公告列表 -->
            <el-button
              type="text"
              @click="点击导出表格按钮('gonggaoTable','公告信息列表')"
              style="float: right;"
            >导出到Ecxel</el-button>
            <el-table
              id="gonggaoTable"
              :data="gonggaoTable"
              border
              stripe
              style="width: 100%"
              max-height="650"
              v-if="showTable"
            >
              <el-table-column prop="id" label="#" width="60"></el-table-column>
              <el-table-column prop="title" label="标题" width="270"></el-table-column>
              <el-table-column prop="content" label="内容"></el-table-column>
              <el-table-column prop="type" label="公告类型" width="120"></el-table-column>
              <el-table-column prop="time" label="发布日期" width="140">
                <template slot-scope="scope">
                  {{scope.row.time|dataTime}}
                </template>
              </el-table-column>
              <el-table-column prop="author.nickname" label="作者" width="105"></el-table-column>
              <el-table-column prop="level" label="公告级别" width="80"></el-table-column>
              <el-table-column align="center" label="操作" width="130">
                <template slot-scope="scope">
                  <el-tooltip class="item" effect="dark" content="编辑公告" placement="top">
                    <el-button
                      type="primary"
                      icon="el-icon-edit"
                      circle
                      @click="点击编辑公告按钮(scope.$index)"
                    ></el-button>
                  </el-tooltip>
                  <el-tooltip class="item" effect="dark" content="删除公告" placement="top">
                    <el-button
                      type="danger"
                      icon="el-icon-delete"
                      circle
                      @click="点击删除公告按钮(scope.row.id)"
                    ></el-button>
                  </el-tooltip>
                </template>
              </el-table-column>
            </el-table>
            <!-- 修改编辑公告模块 -->
            <div v-if="!showTable">
              <div style="text-align:center;">
                <h1 style="font-size: 28px;">编辑修改公告</h1>
              </div>
              <el-row class="gonggao">
                <el-col :span="8" :offset="8">
                  <el-card style="    border-radius: 4px;padding-buttom:50px;">
                    <div slot="header">
                      <el-input v-model="tableRowData.title" placeholder="请添加标题"></el-input>
                    </div>
                    <div>
                      <el-input type="textarea" v-model="tableRowData.content" placeholder="请填写内容"></el-input>
                    </div>
                  </el-card>
                  <div style="padding: 10px;">
                    <span>设置公告级别</span>
                    <el-slider v-model="tableRowData.level" :step="10" show-stops show-input></el-slider>
                  </div>
                  <div style="padding: 10px;">
                    <span style="padding-right: 20px;">设置公告类型</span>
                    <el-select v-model="tableRowData.type" placeholder="请选择公告类型">
                      <el-option label="系统公告" value="系统公告"></el-option>
                      <el-option label="荣誉墙" value="荣誉墙"></el-option>
                      <el-option label="竞赛信息" value="竞赛信息"></el-option>
                    </el-select>
                  </div>
                  <div style="text-align: center;margin: 30px;">
                    <el-button type="success" plain icon="el-icon-message" @click="确认编辑公告修改()">确认修改</el-button>
                    <el-button type="warning" plain icon="el-icon-message" @click="取消编辑公告修改()">取消修改</el-button>
                  </div>
                </el-col>
              </el-row>
            </div>
          </el-tab-pane>
          <!-- ============================发布新公告====================================== -->
          <el-tab-pane label="发布新公告" name="发布新公告" style="text-algin:center;">
            <div style="text-align:center;">
              <h1 style="font-size: 28px;">发布新公告</h1>
            </div>
            <el-row class="gonggao">
              <el-col :span="8" :offset="8">
                <el-card style="    border-radius: 4px;padding-buttom:50px;">
                  <div slot="header">
                    <el-input v-model="newGonggao.title" placeholder="请添加标题"></el-input>
                  </div>
                  <div>
                    <el-input type="textarea" v-model="newGonggao.content" placeholder="请填写内容"></el-input>
                  </div>
                </el-card>
                <div style="padding: 10px;">
                  <span>设置公告级别</span>
                  <el-slider v-model="newGonggao.level" :step="10" show-stops show-input></el-slider>
                </div>
                <div style="padding: 10px;">
                  <span style="padding-right: 20px;">设置公告类型</span>
                  <el-select v-model="newGonggao.type" placeholder="请选择公告类型">
                    <el-option label="系统公告" value="系统公告"></el-option>
                    <el-option label="荣誉墙" value="荣誉墙"></el-option>
                    <el-option label="竞赛信息" value="竞赛信息"></el-option>
                  </el-select>
                </div>

                <div style="text-align: center;margin: 30px;">
                  <el-button type="success" plain icon="el-icon-message" @click="发布公告()">发布公告</el-button>
                </div>
              </el-col>
            </el-row>
          </el-tab-pane>

        </el-tabs>
      </div>
    </el-card>
  </div>
</template>
<script>
import { exportExcel } from "../../assets/js/utils.js";
import api from '../../main.js'
export default {
  name: "admin_gonggao",
  data() {
    return {
      tabPosition: "left",
      showTable: true,
      activeName: "公告信息列表",
      activeXinxiName: "消息列表",
      tableRowData: {},
      gonggaoTable: [],
      xinxiTable: [],
      newGonggao: {
        title: "",
        content: "",
        level: 50,
        author: "",
        type: ""
      }
    };
  },
  mounted() {
    this.请求公告信息列表数据();
  },
  methods: {
    handleClick(tab, event) {
    },
    // getRowData(row){
    //     this.tableRowData = JSON.parse(JSON.stringify(row)); // 先转换为字符串，然后再转换
    // },
    点击编辑公告按钮(index) {
      this.showTable = false;
      this.tableRowData = this.gonggaoTable[index];
    },
    点击删除公告按钮(id) {
      let that = this;
      this.$confirm("此操作将永久删除该公告, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios
            .get(api+`/notice/deleteNoticeById?id=${id}`)
            .then(function(response) {
              that.$message({
                message: response.data,
                type: response.data == "删除成功！" ? "success" : "error"
              });
              if (response.data == "删除成功！") {
                that.请求公告信息列表数据();
              }
            })
            .catch(function(error) {
              that.$message.error("服务器错误，操作失败！");
              console.error(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    取消编辑公告修改() {
      let that = this;
      this.$confirm("此操作将放弃对公告的修改, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          //确定
          that.tableRowData = {};
          that.showTable = true;
        })
        .catch(() => {
          //取消
          return;
        });
    },
    确认编辑公告修改() {
      if (this.tableRowData.title == "" || this.tableRowData.content == "") {
        this.$message.error("标题和内容不能为空！");
        return;
      }
      let that = this;
      //创建表单对象
      let gonggao = new FormData();
      gonggao.append("id", that.tableRowData.id);
      gonggao.append("title", that.tableRowData.title);
      gonggao.append("content", that.tableRowData.content);
      gonggao.append("level", that.tableRowData.level);
      gonggao.append("author.id", that.$store.state.id);
      gonggao.append("type", that.tableRowData.type);

      this.$axios
        .post(api+`/notice/updateNotice`, gonggao)
        .then(function(response) {
          that.$message({
            message: response.data == "发布成功！" ?'修改成功':response.data,
            type: response.data == "发布成功！" ? "success" : "error"
          });
          that.showTable = true;
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
          console.error(error);
        });
    },
    发布公告() {
      if (this.newGonggao.title == "" || this.newGonggao.content == "") {
        this.$message.error("标题和内容不能为空！");
        return;
      }

      let that = this;
      let date = new Date();
      //创建表单对象
      let gonggao = new FormData();
      gonggao.append("title", that.newGonggao.title);
      gonggao.append("content", that.newGonggao.content);
      gonggao.append("level", that.newGonggao.level);
      gonggao.append("author.id", that.$store.state.id);
      gonggao.append("type", that.newGonggao.type);

      this.$axios
        .post(api+`/notice/addNotice`, gonggao)
        .then(function(response) {
          that.$message({
            message: response.data,
            type: response.data == "发布成功！" ? "success" : "error"
          });
          that.newGonggao.title = "";
          that.newGonggao.content = "";
          that.activeName = "公告信息列表";
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
          console.error(error);
        });
    },

    请求公告信息列表数据() {
      let that = this;
      this.$axios
        .get(api+`/notice/findAllNotice`)
        .then(function(response) {
          that.gonggaoTable = response.data;
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
          console.error(error);
        });
    },
    点击导出表格按钮(tableDOM, excelName) {
      exportExcel(tableDOM, excelName);
    },
  },
  watch: {
    activeName(val, oldVal) {
      let that = this;
      that.tableRowData = {};
      that.showTable = true;
      if (that.activeName == "公告信息列表") {
        that.请求公告信息列表数据();
      } else if (that.activeName == "信息管理") {
        if (that.activeXinxiName != "消息列表") {
          that.activeXinxiName = "消息列表";
        } else {
          that.获取未读消息列表();
        }
      }
    },
    activeXinxiName(val, oldVal) {
      let that = this;
      if (that.activeXinxiName == "消息列表") {
        that.获取未读消息列表();
      } else if (that.activeXinxiName == "已读") {
        that.获取已读消息列表();
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
};
</script>
<style>
.gonggao .el-card input.el-input__inner {
  height: 65px;
  font-size: 24px;
}
.gonggao .el-card__header {
  padding: 0;
}
.gonggao .el-card__content {
  padding: 0;
}
.gonggao textarea.el-textarea__inner {
  height: 330px;
}
</style>