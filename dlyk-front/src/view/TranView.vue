<template>
  <el-button type="primary" @click=add() v-if="canAddTran">录入交易信息</el-button>
  <el-button type="danger" @click=batchDel() v-if="canDeleteTran">批量删除</el-button>

  <!--  列表自带循环功能-->
  <el-table
      :data="tranList"
      stripe style="width: 100%"
      @selection-change="handleSelectionChange">

    <el-table-column type="selection" width="55" />

    <el-table-column type="index" label="序号" width="60" />

    <el-table-column property="tranNo" label="交易流水号" show-overflow-tooltip />

    <el-table-column property="customerDO.fullName" label="所属客户" show-overflow-tooltip />

    <el-table-column property="money" label="交易金额" show-overflow-tooltip />

    <el-table-column property="expectedDate" label="预计成交时间" show-overflow-tooltip />

    <el-table-column property="stateValueDO.typeValue" label="交易所处阶段" show-overflow-tooltip />

    <el-table-column label="操作" show-overflow-tooltip >

      <template #default="scope">
        <!--作用域插槽，获取到这一行内容的数据（这里scope代表了userList，row代表行，即user对象）-->
        <el-button type="success" :icon="Search" circle @click="view(scope.row.id)" v-if="canViewTran" />
        <el-button type="primary" :icon="Edit" circle @click="edit(scope.row.id)" v-if="canEditTran" />
        <el-button type="danger" :icon="Delete" circle @click="del(scope.row.id)" v-if="canDeleteTran" />
      </template>
    </el-table-column>

  </el-table>

  <el-pagination
      background
      layout="prev, pager, next"
      :page-size="pageSize"
      :total="total"
      @prev-click="toPage($event)"
      @next-click="toPage"
      @current-change="toPage($event)"
  />
</template>

<script>
import {Delete, Edit, Search} from "@element-plus/icons-vue";
import * as permissionUtils from "../util/permissionUtils.js";
import axios from "axios";
export default {
  props: ['nowUser'],
  name: "TranView",
  computed: {
    Delete() {
      return Delete
    },
    Edit() {
      return Edit
    },
    Search() {
      return Search
    },
    //增加权限
    canAddTran() {
      return permissionUtils.default.canAddTran(this.nowUser)
    },
    // 使用计算属性检查是否具有查看线索权限
    canViewTran() {
      return permissionUtils.default.canViewTran(this.nowUser);
    },
    // 使用计算属性检查是否具有删除线索权限
    canDeleteTran() {
      return permissionUtils.default.canDeleteTran(this.nowUser);
    },
    //检查是否有修改权限
    canEditTran() {
      return permissionUtils.default.canEditTran(this.nowUser);
    }
  },

  inject: ["reload"],

  data() {
    return {
      tranList: [],
      //分页相关
      pageSize: 0,
      total: 0,
      //活动id的数组(用来记住多选)
      tranIdArray: [],
      currentPage: 0
    }
  },

  mounted() {
    //直接去查询活动列表
    this.getData(this.currentPage);
  },

  methods: {
    //勾选复选框和取消复选框都会触发的方法
    handleSelectionChange(selectionDataArray){
      this.tranIdArray = [];
      selectionDataArray.forEach(data => {
        let tranId = data.id;
        this.tranIdArray.push(tranId);
      })
    },

    //更新要变化后所在页码的页数
    toPage(current){
      //饿了么组件会自动传递一个变化后的页码
      this.currentPage = current
      //当前页码参数currentPage改动后，再去服务器获取请求
      this.getData()
    },

    getData(){
      axios.get("/api/trans", {
        params : {
          current: this.currentPage //当前页，前面是参数名，后面是参数值
        }
      }).then(resp => {
        if (resp.data.code === 200) {
          console.log(resp.data.data)
          this.tranList = resp.data.data.list;
          this.pageSize = resp.data.data.pageSize;
          this.total = resp.data.data.total;
        }
      })
    }
  }
}
</script>

<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
.el-pagination{
  margin-top: 12px;
}
.el-table{
  margin-top: 12px;
}
</style>