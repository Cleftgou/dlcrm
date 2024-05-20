<template>

  <div style="display: flex;margin: 15px 0">
    <el-card style="width: 50%; margin-right: 10px" >
      <div style="margin-bottom: 15px;font-size: 20px;font-weight: bold">公司所有产品</div>
      <el-timeline style="padding: 0">
        <el-timeline-item placement="top" v-for="item in notices" :key="item.id" :timestamp="item.createTime">
          <el-card>
            <template #header>
              <div class="card-header">
                <span>{{ item.name }}</span>
              </div>
            </template>
            <p>项目描述：{{ item.description }}</p>
            <p>分部公司地址：{{ item.address }}</p>
            <p>体验版网站地址：{{ item.closemsg }}</p>
          </el-card>
        </el-timeline-item>

      </el-timeline>
    </el-card>

    <el-card style="width: 50%;" >
      <div style="margin-bottom: 15px;font-size: 20px;font-weight: bold">动力CRM版本更新日志</div>
        <el-collapse accordion>
          <el-collapse-item :title="log.version" :name="String(index)" v-for="(log,index) in UpdateLog" :key="log.id">
            <div>
              版本更新时间：{{ log.upgradeTime }}
            </div>
            <div>
              版本日志：{{ log.description }}
            </div>
          </el-collapse-item>
        </el-collapse>
    </el-card>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "systemView",
  data(){
    return{
      //系统公告栏
      notices: [{}],
      //版本日志
      UpdateLog: [{}],
    }
  },
  mounted() {
    //加载公司产品信息
    this.loadNotice();
    this.loadUpdateLog();
  },
  methods: {
    //加载公司产品信息
    loadNotice(){
      axios.get("/api/notices").then(response => {
        if (response.data.code === 200) {
          console.log(response.data.data)
          this.notices = response.data.data;
        }
      })
    },

    loadUpdateLog(){
      axios.get("/api/updateLogs").then(response => {
        if (response.data.code === 200) {
          console.log(response.data.data)
          this.UpdateLog = response.data.data;
        }
      })
    }
  }
}
</script>

<style scoped>

</style>