<template>
  <el-button type="primary" @click=batchExportExcel()>批量导出为Excel</el-button>
  <el-button type="success" @click=chooseExportExcel()>选择导出为Excel</el-button>

  <el-table
      :data="customerList"
      stripe style="width: 100%"
      @selection-change="handleSelectionChange">
    <el-table-column type="selection"/>
    <el-table-column type="index" label="序号" width="55px"/>
    <el-table-column property="ownerDO.name" label="负责人"/>
    <el-table-column property="activityDO.name" label="所属活动" width="120px"/>

    <el-table-column label="姓名">
      <template #default="scope">
        <a href="javascript:" @click="view(scope.row.id)">{{ scope.row.clueDO.fullName }}</a>
      </template>
    </el-table-column>

    <el-table-column property="appellationDO.typeValue" label="称呼"/>
    <el-table-column property="clueDO.phone" label="手机" width="120"/>
    <el-table-column property="clueDO.weixin" label="微信" width="120"/>
    <el-table-column property="needLoanDO.typeValue" label="是否贷款"/>
    <el-table-column property="intentionStateDO.typeValue" label="意向状态"/>
    <el-table-column property="stateDO.typeValue" label="线索状态"/>
    <el-table-column property="sourceDO.typeValue" label="线索来源"/>
    <el-table-column property="intentionProductDO.name" label="意向产品"/>

    <el-table-column label="操作" width="85">
      <template #default="scope">
        <el-button type="primary" @click="view(scope.row.id)">详情</el-button>
      </template>
    </el-table-column>
  </el-table>
  <p>
    <el-pagination
        background
        layout="prev, pager, next"
        :page-size="pageSize"
        :total="total"
        @prev-click="toPage"
        @next-click="toPage"
        @current-change="toPage"/>
  </p>
</template>

<script>
import "../http/httpRequest.js";
import axios from "axios";
import {getToken, messageTip} from "../util/util.js";
export default {
  name: "CustomerView",

  data() {
    return {
      //客户列表数据对象，初始值是空，关联了九个表
      customerList : [{
        clueDO : {},
        ownerDO : {},
        activityDO : {},
        appellationDO : {},
        needLoanDO : {},
        intentionStateDO : {},
        stateDO : {},
        sourceDO : {},
        intentionProductDO : {},
      }],
      //分页时每页显示多少条数据
      pageSize : 0,
      //总共有多少条
      total : 0,
      //客户id数组
      customerIdArray: []
    }
  },

  mounted() {
    this.getData();
  },

  methods:{
    handleSelectionChange(selectionIdArray) {
      //别忘了先清空数据
      this.customerIdArray = [];
      selectionIdArray.forEach((item) => {
        this.customerIdArray.push(item.id);
      })
    },

    //更新要变化后所在页码的页数
    toPage(current){
      //饿了么组件会自动传递一个变化后的页码
      this.currentPage = current
      //当前页码参数currentPage改动后，再去服务器获取请求
      this.getData()
    },

    //获取线索分页列表数据
    //这个params必须加上，不然后面没办法用@RequestParam(value = "current", required = false)接收到
    getData() {
      axios.get("/api/customers", {
        params : {
          current: this.currentPage //当前页，前面是参数名，后面是参数值
        }
      }).then(resp => {
        if (resp.data.code === 200) {
          this.customerList = resp.data.data.list;
          this.pageSize = resp.data.data.pageSize;
          this.total = resp.data.data.total;
        }
      })
    },

    //导出函数提取
    exportExcel(ids){
      let token = getToken();
      //1、向后端发送一个请求 （我们来实现）
      //这是html标签，用于发送一个请求，并把响应的流显示在一个小窗口里<iframe src="https://www.runoob.com"></iframe>
      let iframe = document.createElement("iframe")
      if (ids){
        iframe.src = axios.defaults.baseURL + "/api/exportExcel?Authorization="+ token + "&ids=" + ids
      }else {
        iframe.src = axios.defaults.baseURL + "/api/exportExcel?Authorization=" + token//这个后端地址前缀在httpRequest里定义
      }
      iframe.style.display = "none";//隐藏一下，别显示
      //把这个创建好的元素（iframe）作为子元素加入到body里
      document.body.appendChild(iframe);
      //2、后端查询数据库的数据，把数据写入Excel，然后把Excel以IO流的方式输出到前端浏览器（我们来实现）
      //3、前端浏览器弹出一个下载框进行文件下载（浏览器本身实现的，不需要我们去实现）
    },


    //批量导出客户的Excel数据
    batchExportExcel() {
      this.exportExcel(null);
    },

    //选择导出
    chooseExportExcel(){
      if (this.customerIdArray.length === 0){
        messageTip("请先选择要导出的客户", "info");
      }else {
        let ids = this.customerIdArray.join(",");
        this.exportExcel(ids)
      }
    }

  }
}
</script>

<style scoped>
.el-pagination{
  margin-top: 12px;
}
.el-table{
  margin-top: 12px;
}
.fileTip{
  padding-top: 15px;
}
</style>