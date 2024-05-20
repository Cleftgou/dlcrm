<template>
  <el-form :inline="true" :model="activityQuery" class="demo-form-inline" :rules="activityRules">

    <el-form-item label="负责人">
      <el-select
          v-model="activityQuery.ownerId"
          placeholder="请选择负责人"
          @click="loadOwner()"
          clearable
      >
        <el-option
            v-for="item in ownerOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="活动名称">
      <el-input v-model="activityQuery.name" placeholder="请输入活动名称" clearable />
    </el-form-item>

    <el-form-item label="活动时间">
      <el-date-picker
          v-model="activityQuery.activityTime"
          type="datetimerange"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          value-format="YYYY-MM-DD HH:mm:ss"/>
    </el-form-item>

    <el-form-item label="活动预算" prop="cost">
      <el-input v-model="activityQuery.cost" placeholder="请输入最低活动预算" clearable />
    </el-form-item>

    <el-form-item label="创建时间">
      <el-date-picker
          v-model="activityQuery.createTime"
          type="datetime"
          placeholder="请选择最早创建时间"
          value-format="YYYY-MM-DD HH:mm:ss"/>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSearch" v-if="canSearchActivity">搜索</el-button>
      <el-button type="primary" plain @click="onReset">重置</el-button>
    </el-form-item>
  </el-form>

  <el-button type="primary" @click=add() v-if="canAddActivity">录入市场活动</el-button>
  <el-button type="danger" @click=batchDel() v-if="canDeleteActivity">批量删除</el-button>

  <!--  列表自带循环功能-->
  <el-table
      :data="activityList"
      stripe style="width: 100%"
      @selection-change="handleSelectionChange">

    <el-table-column type="selection" width="55" />

    <el-table-column type="index" label="序号" width="60" />

    <el-table-column property="ownerDO.name" label="负责人" />

    <el-table-column property="name" label="活动名称" />

    <el-table-column property="startTime" label="开始时间" show-overflow-tooltip />

    <el-table-column property="endTime" label="结束时间" show-overflow-tooltip />

    <el-table-column property="cost" label="活动预算" show-overflow-tooltip />

    <!--show-overflow-tooltip宽度不够自动隐藏，不加的话就是换行了-->
    <el-table-column property="createTime" label="创建时间" show-overflow-tooltip />

    <el-table-column label="操作" show-overflow-tooltip >

      <template #default="scope">
        <!--作用域插槽，获取到这一行内容的数据（这里scope代表了userList，row代表行，即user对象）-->
        <el-button type="success" :icon="Search" circle @click="view(scope.row.id)" v-if="canViewActivity" />
        <el-button type="primary" :icon="Edit" circle @click="edit(scope.row.id)" v-if="canEditActivity" />
        <el-button type="danger" :icon="Delete" circle @click="del(scope.row.id)" v-if="canDeleteActivity" />
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
import "../http/httpRequest.js";
import {defineComponent} from "vue";
import {Delete, Edit, Search} from "@element-plus/icons-vue";
import axios from "axios";
import {messageConfirm, messageTip} from "../util/util.js";
import * as permissionUtils from "../util/permissionUtils.js";

export default defineComponent({
  props: ['nowUser'],
  name: "ActivityView",
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
    canAddActivity() {
      return permissionUtils.default.canAddActivity(this.nowUser)
    },
    //搜索权限
    canSearchActivity() {
      return permissionUtils.default.canSearchActivity(this.nowUser)
    },
    // 使用计算属性检查是否具有查看线索权限
    canViewActivity() {
      return permissionUtils.default.canViewActivity(this.nowUser);
    },
    // 使用计算属性检查是否具有删除线索权限
    canDeleteActivity() {
      return permissionUtils.default.canDeleteActivity(this.nowUser);
    },
    //检查是否有修改权限
    canEditActivity() {
      return permissionUtils.default.canEditActivity(this.nowUser);
    }
  },

  inject: ["reload"],

  data(){
    return{
      //市场活动搜索表单对象，初始是空
      activityQuery: {},
      //市场活动列表对象
      activityList: [
        {}
      ],
      //分页相关
      pageSize: 0,
      total: 0,
      //负责人的下拉列表数据
      ownerOptions: [{}],
      //搜索表单验证规则
      activityRules : {
        cost : [
          {pattern: /^[0-9]+(\.[0-9]{2})?$/, message: '活动预算必须是整数或两位小数', trigger: "blur"}
        ]
      },
      //活动id的数组(用来记住多选)
      activityIdArray: [],
    }
  },

  mounted() {
    //直接去查询活动列表
    this.getData(this.currentPage);
  },

  methods: {
    //查询某页用户的全部数据userList
    getData(){
      let startTime = '';
      let endTime = '';
      for (let key in this.activityQuery.activityTime) {
        if (key === '0'){
          //console.log(this.activityQuery.activityTime[key])
          startTime = this.activityQuery.activityTime[key];
        }
        if (key === '1'){
          endTime = this.activityQuery.activityTime[key];
        }
      }

      //查询活动列表数据
      axios.get("/api/activitys", {
        params: {

          //查询条件字段（一共6个）
          ownerId: this.activityQuery.ownerId,
          name: this.activityQuery.name,

          //活动时间，这个日期参数封装的是一个obj对象，上面使用for in可以取出两个日期
          //activityTime: this.activityQuery.activityTime,
          //开始时间与结束时间
          startTime: startTime,
          endTime: endTime,

          //预算
          cost: this.activityQuery.cost,
          //创建时间
          createTime: this.activityQuery.createTime,

          current: this.currentPage //假设 this.currentPage 是当前页码的变量（初始是0）
        }
      }).then(response => {
        if (response.data.code === 200) {
          console.log(response.data.data.list)
          this.activityList = response.data.data.list;
          this.pageSize = response.data.data.pageSize;
          this.total = response.data.data.total;
        }
      })
    },

    //更新要变化后所在页码的页数
    toPage(current){
      //饿了么组件会自动传递一个变化后的页码
      this.currentPage = current
      //当前页码参数currentPage改动后，再去服务器获取请求
      this.getData()
    },

    //加载负责人下拉菜单数据
    loadOwner(){
      axios.get("/api/owner").then(response => {
        if (response.data.code === 200) {
          //实际上，后端不作数据类型的转换，这里也能取到所需要的数据
          //console.log(response.data.data[0]);
          //this.ownerOptions = response.data.data[0];
          console.log(response.data.data)
          this.ownerOptions = response.data.data;
        }
      })
    },

    //搜索（调用分页查询功能）
    onSearch(){
      this.getData();
    },

    //录入市场活动，跳转路由
    add(){
      this.$router.push('/dashboard/activity/add')
    },

    //搜索条件重置
    onReset(){
      this.activityQuery = {}
    },

    //编辑
    edit(id){
      this.$router.push("/dashboard/activity/edit/" + id)
    },

    //查看详情
    view(id){
      this.$router.push("/dashboard/activity/" + id)
    },

    //删除
    del(id){
      messageConfirm("您确定要删除该市场活动信息吗？").then(res => {
        //用户点击确定，就执行then
        axios.delete("/api/activity/" + id).then(response => {
          if (response.data.code === 200){
            messageTip("删除成功", "success")
            //刷新一下右侧页面
            this.reload()
          }else {
            messageTip("删除失败，原因：" + response.data.msg, "error")
          }
        })
      }).catch(() => {
        //取消触发catch
        messageTip("取消删除", "warning")
      })
    },

    //勾选复选框和取消复选框都会触发的方法
    handleSelectionChange(selectionDataArray){
      //先清空，以最后一次勾选状态为准
      this.activityIdArray = [];
      //饿了么组件会自动传递复选框选的数据（10个活动对象）（就像spring的依赖注入IOC）
      console.log(selectionDataArray);
      selectionDataArray.forEach(data => {
        let activityId = data.id;
        this.activityIdArray.push(activityId);
      })
      console.log(this.activityIdArray);
    },

    //批量删除
    batchDel() {
      if (this.activityIdArray.length <= 0) {
        //提示
        messageTip("请选择要删除的数据", "info")
      } else {
        let ids = this.activityIdArray.join(",")
        messageConfirm("您确定要删除这些数据吗？").then(() => {
          axios.delete("/api/activities", {
            params: {
              ids: ids
            }
          }).then(response => {
            if (response.data.code === 200) {
              messageTip("批量删除成功", "success")
              //刷新一下右侧页面
              this.reload()
            } else {
              console.log(response.data)
              messageTip("批量删除失败，原因：" + response.data.msg, "error")
            }
          })
        }).catch(() => {
          messageTip("取消批量删除", "warning")
        })
      }
    }

  }
})

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
