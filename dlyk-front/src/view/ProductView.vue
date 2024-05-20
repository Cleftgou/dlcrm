<template>
  <el-button type="primary" @click=add() v-if="canAddProduct">录入产品信息</el-button>
  <el-button type="danger" @click=batchDel() v-if="canDeleteProduct">批量删除</el-button>

  <!--  列表自带循环功能-->
  <el-table
      :data="productList"
      stripe style="width: 100%"
      @selection-change="handleSelectionChange">

    <el-table-column type="selection" width="55" />

    <el-table-column type="index" label="序号" width="60" />

    <el-table-column property="name" label="产品名称" />

    <el-table-column property="guidePriceS" label="官方指导起始价" show-overflow-tooltip />

    <el-table-column property="guidePriceE" label="官方指导最高价" show-overflow-tooltip />

    <el-table-column property="quotation" label="经销商报价" show-overflow-tooltip />

    <el-table-column property="state" label="是否在售" show-overflow-tooltip >
      <!--作用域插槽-->
      <template #default="scope">
        <span style="background: #89e121" v-if="scope.row.state === 0">销售中</span>
        <span style="background: #e28181" v-else>停止销售</span>
      </template>
    </el-table-column>

    <el-table-column property="createTime" label="创建时间" show-overflow-tooltip />

    <el-table-column label="操作" show-overflow-tooltip >

      <template #default="scope">
        <!--作用域插槽，获取到这一行内容的数据（这里scope代表了userList，row代表行，即user对象）-->
        <el-button type="success" :icon="Search" circle @click="view(scope.row.id)" v-if="canViewProduct" />
        <el-button type="primary" :icon="Edit" circle @click="edit(scope.row.id)" v-if="canEditProduct" />
        <el-button type="danger" :icon="Delete" circle @click="del(scope.row.id)" v-if="canDeleteProduct" />
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
  name: "ProductView",
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
    canAddProduct() {
      return permissionUtils.default.canAddProduct(this.nowUser)
    },
    // 使用计算属性检查是否具有查看线索权限
    canViewProduct() {
      return permissionUtils.default.canViewProduct(this.nowUser);
    },
    // 使用计算属性检查是否具有删除线索权限
    canDeleteProduct() {
      return permissionUtils.default.canDeleteProduct(this.nowUser);
    },
    //检查是否有修改权限
    canEditProduct() {
      return permissionUtils.default.canEditProduct(this.nowUser);
    }
  },

  inject: ["reload"],

  data() {
    return {
      productList: [],
      //分页相关
      pageSize: 0,
      total: 0,
      //活动id的数组(用来记住多选)
      productIdArray: [],
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
      this.productIdArray = [];
      selectionDataArray.forEach(data => {
        let productId = data.id;
        this.productIdArray.push(productId);
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
      axios.get("/api/products", {
        params : {
          current: this.currentPage //当前页，前面是参数名，后面是参数值
        }
      }).then(resp => {
        if (resp.data.code === 200) {
          this.productList = resp.data.data.list;
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