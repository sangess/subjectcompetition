<template>
  <div id="college_xinxi">
    <el-card>
      <div slot="header"></div>
      <div>
        <el-tabs v-model="activeXinxiName" :tab-position="tabPosition">
          <el-tab-pane label="消息列表" name="消息列表">
            <el-table border stripe :data="xinxiTable" style="width: 100%">
              <el-table-column label="#" width="60">
                <template slot-scope="scope">{{scope.$index + 1}}</template>
              </el-table-column>
              <el-table-column prop="title" label="标题"></el-table-column>
              <el-table-column prop="content" label="内容"></el-table-column>
              <el-table-column prop="sendName" label="发送者" ></el-table-column>
              <el-table-column prop="sendUsername" label="发送者用戶名" ></el-table-column>
              <el-table-column label="时间" >
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px">{{ scope.row.date |dataTime}}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="125">
                <template slot-scope="scope">
                  <el-button
                          type="primary"
                          icon="el-icon-s-flag"
                          plain
                          size="small"
                          @click="remarkMsg(scope.row.id)"
                  >标记已读</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已读" name="已读">
            <el-table border stripe :data="xinxiTable" style="width: 100%">
              <el-table-column label="#" width="60">
                <template slot-scope="scope">{{scope.$index + 1}}</template>
              </el-table-column>
              <el-table-column prop="title" label="标题"></el-table-column>
              <el-table-column prop="content" label="内容"></el-table-column>
              <el-table-column prop="sendName" label="发送者" ></el-table-column>
              <el-table-column prop="sendUsername" label="发送者用戶名" ></el-table-column>
              <el-table-column label="时间" >
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px">{{ scope.row.date |dataTime}}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="125">
                <template slot-scope="scope">
                  <el-button
                          type="danger"
                          icon="el-icon-delete"
                          plain
                          size="small"
                          @click="deleteMessage(scope.row.id)"
                  >删除信息</el-button>
                </template>
              </el-table-column>
            </el-table>
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
    name: "college_xinxi",
    data() {
      return {
        tabPosition: "left",
        activeXinxiName: "",
        xinxiTable: [],
        receiver:this.$store.state.receiver
      };
    },
    mounted() {
      this.activeXinxiName = "消息列表";
    },
    methods: {
      getUnReadList() {
        let that = this;
        that.$axios
        .get(
            api+`/msg/getUnReadList/`+that.receiver
        )
        .then(function(response) {
          that.xinxiTable = response.data;
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
        });
      },
      getReadList() {
        let that = this;
        that.$axios
        .get(
            api+`/msg/getReadList/`+that.receiver
        )
        .then(function(response) {
          that.xinxiTable = response.data;
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
        });
      },
      remarkMsg(messageId) {
        let that = this;
        that.$axios
        .get(api+`/msg/markMessage/`+messageId)
        .then(function(response) {
          that.$message({
            type: response.data.code==200?'success':'error',
            message: response.data.message
          });
          that.getUnReadList();
        })
        .catch(function(error) {
          that.$message.error("服务器错误，操作失败！");
        });
      },
      deleteMessage(messageId) {
        this.$confirm("此操作将永久删除该信息, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
        .then(() => {
          let that = this;
          that.$axios
          .get(api+`/msg/deleteMessage/`+messageId)
          .then(function(response) {
            that.$message({
              type: response.data.code==200?'success':'error',
              message: response.data.message
            });
            that.getReadList();
          })
          .catch(function(error) {
            that.$message.error("服务器错误，操作失败！");
          });
        })
      }
    },
    watch: {
      activeXinxiName(val, oldVal) {
        let that = this;
        if (that.activeXinxiName == "消息列表") {
          that.getUnReadList();
        } else if (that.activeXinxiName == "已读") {
          that.getReadList();
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