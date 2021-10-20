<template>
  <div id="admin_index">
    <el-card class="gonggaolan">
      <div slot="header">
        <span style="font-size:26px;">学科竞赛管理系统公告栏</span>
      </div>
      <div class="gonggaolan_body">
        <el-row :gutter="10">
          <el-col :span="8">
            <el-card shadow="hover">
              <div slot="header">系统公告</div>
              <div style="overflow: auto;">
                <el-collapse accordion>
                  <el-collapse-item v-for="(gonggao,key) in xitonggonggao" :key="key">
                    <template slot="title">
                      <span
                        class="el-icon-paperclip"
                        style="font-size: 22px;color: crimson;margin-right: 5px;"
                      ></span>
                      {{gonggao.title}}
                    </template>
                    <div>
                      {{gonggao.content}}
                      <br />
                      <span style="float:right;">—— 作者：{{gonggao.author.nickname}}</span>
                      <br />
                      <span style="float:right;">{{gonggao.time|dataTime}}</span>
                    </div>
                  </el-collapse-item>
                </el-collapse>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">
              <div slot="header">荣誉墙</div>
              <div style="overflow: auto;">
                <el-collapse accordion>
                  <el-collapse-item v-for="(gonggao,key) in rongyuqiang" :key="key">
                    <template slot="title">
                      <span
                        class="el-icon-trophy"
                        style="font-size: 22px;color: #d8c324;margin-right: 5px;"
                      ></span>
                      {{gonggao.title}}
                    </template>
                    <div>
                      {{gonggao.content}}
                      <br />
                      <span style="float:right;">—— 作者：{{gonggao.author.nickname}}</span>
                      <br />
                      <span style="float:right;">{{gonggao.time|dataTime}}</span>
                    </div>
                  </el-collapse-item>
                </el-collapse>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">
              <div slot="header">竞赛信息</div>
              <div style="overflow: auto;">
                <el-collapse accordion>
                  <el-collapse-item v-for="(gonggao,key) in jingsaixinxi" :key="key">
                    <template slot="title">
                      <span
                        class="el-icon-document"
                        style="font-size: 22px;color: #9C27B0;margin-right: 5px;"
                      ></span>
                      {{gonggao.title}}
                    </template>
                    <div>
                      {{gonggao.content}}
                      <br />
                      <span style="float:right;">—— 作者：{{gonggao.author.nickname}}</span>
                      <br />
                      <span style="float:right;">{{gonggao.time|dataTime}}</span>
                    </div>
                  </el-collapse-item>
                </el-collapse>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import gonggao from "./admin_gonggao";
import { formatDate} from "../../assets/js/utils.js";
import api from '../../main'
export default {
  name: "admin_index",
  data() {
    return {
      xitonggonggao: [],
      rongyuqiang: [],
      jingsaixinxi: []
    };
  },
  mounted() {
    this.请求公告信息列表数据();
  },
  methods: {
    请求公告信息列表数据() {
      let that = this;
      this.$axios
        .get(api+`/notice/selectAllByTypeOrderBYLevelDesc`)
        .then(function(response) {
          that.xitonggonggao = response.data.xitonggonggao;
          that.rongyuqiang = response.data.rongyuqiang;
          that.jingsaixinxi = response.data.jingsaixinxi;
        })
        .catch(function(error) {
          console.error(error);
        });
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
.gonggaolan_body .el-card__header {
  background-color: aliceblue;
}
.gonggaolan_body .el-collapse-item__header {
  font-weight: 700;
  font-size: 16px;
  color: #303133;
  line-height: inherit;
}
.gonggaolan_body .el-collapse-item__wrap {
  padding-left: 30px;
  padding-right: 40px;
}
</style>