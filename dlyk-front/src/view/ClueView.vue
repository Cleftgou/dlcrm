<template>
  <el-button type="primary" @click=addClue() v-if="canAddClue">录入线索</el-button>
  <el-button type="success" @click=importExcel() v-if="canImportClue">导入线索</el-button>
  <el-button type="danger" @click=batchDelClue() v-if="canDeleteClue">批量删除</el-button>

  <el-table
      :data="clueList"
      stripe style="width: 100%"
      @selection-change="handleSelectionChange">
    <el-table-column type="selection"/>
    <el-table-column type="index" label="序号" width="55px"/>
    <el-table-column property="ownerDO.name" label="负责人"/>
    <el-table-column property="activityDO.name" label="所属活动" width="120px"/>

    <el-table-column label="姓名">
      <template #default="scope">
        <a href="javascript:" @click="view(scope.row.id)">{{ scope.row.fullName }}</a>
      </template>
    </el-table-column>

    <el-table-column property="appellationDO.typeValue" label="称呼"/>
    <el-table-column property="phone" label="手机" width="120px"/>
    <el-table-column property="weixin" label="微信" width="120px"/>
    <el-table-column property="needLoanDO.typeValue" label="是否贷款"/>
    <el-table-column property="intentionStateDO.typeValue" label="意向状态"/>
    <el-table-column property="intentionProductDO.name" label="意向产品"/>

    <el-table-column label="线索状态">
      <template #default="scope">
        <span style="background: #89e121" v-if="scope.row.state === -1">{{ scope.row.stateDO.typeValue }}</span>
        <span v-else>{{ scope.row.stateDO.typeValue }}</span>
      </template>
    </el-table-column>

    <el-table-column property="sourceDO.typeValue" label="线索来源"/>
    <el-table-column property="nextContactTime" label="下次联系时间" width="150px"/>
    <el-table-column label="操作" width="150px" >
      <template #default="scope">
        <el-button type="success" :icon="Search" circle @click="view(scope.row.id)" v-if="canViewClue" />
        <el-button type="primary" :icon="Edit" circle @click="edit(scope.row.id)" v-if="canEditClue" />
        <el-button type="danger" :icon="Delete" circle @click="del(scope.row.id)" v-if="canDeleteClue" />
      </template>
    </el-table-column>
  </el-table>

  <el-pagination
      background
      layout="prev, pager, next"
      :page-size="pageSize"
      :total="total"
      @prev-click="toPage"
      @next-click="toPage"
      @current-change="toPage"/>

  <el-dialog v-model="importExcelDialogVisible" title="导入线索Excel" width="900px" center draggable>

    <el-upload
        ref="uploadRef"
        :http-request="uploadFile"
        :auto-upload="false"
        accept=".xls,.xlsx">

      <template #trigger>
        <el-button type="primary">选择文件Excel文件</el-button>
      </template>

      <span>&nbsp;&nbsp;仅支持后缀为.xls或.xlsx的文件</span>

      <template #tip>
        <div class="fileTip">
          温馨提示：
          <ul>
            <li>1.给定Excel文件的第一行将视为字段名</li>
            <li>2.文件大小不超过50MB</li>
            <li>3.日期值以文本形式保存，必须符合格式：yyyy-MM-dd</li>
            <li>4.日期时间以文本形式保存，必须符合格式: yyyy-MM-dd HH:mm:ss</li>
          </ul>
        </div>
      </template>
    </el-upload>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="importExcelDialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="submitExcel">导入</el-button>
      </div>
    </template>

  </el-dialog>

</template>

<script>
import * as permissionUtils from '../util/permissionUtils';
import "../http/httpRequest.js";
import axios from "axios";
import {Delete, Edit, Search} from "@element-plus/icons-vue";
import {messageConfirm, messageTip} from "../util/util.js";

export default {

  props: ['nowUser'],

  name: "ClueView",
  inject: ["reload"],
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
    canAddClue() {
      return permissionUtils.default.canAddClue(this.nowUser)
    },
    //导入权限
    canImportClue() {
      return permissionUtils.default.canImportClue(this.nowUser)
    },
    // 使用计算属性检查是否具有查看线索权限
    canViewClue() {
      return permissionUtils.default.canViewClue(this.nowUser);
    },
    // 使用计算属性检查是否具有删除线索权限
    canDeleteClue() {
      return permissionUtils.default.canDeleteClue(this.nowUser);
    },
    //检查是否有修改权限
    canEditClue() {
      return permissionUtils.default.canEditClue(this.nowUser);
    }
  },

  data(){
    return{
      //线索列表对象，初始值是空
      clueList : [{
        ownerDO : {},
        activityDO : {},
        appellationDO : {},
        needLoanDO : {},
        intentionStateDO : {},
        intentionProductDO : {},
        stateDO : {},
        sourceDO : {}
      }],
      pageSize : 0,
      total : 0,
      //excel的弹窗状态，弹/不弹
      importExcelDialogVisible : false,
      clueIdArray : [],
    }
  },

  mounted() {
    this.getData();
  },

  methods : {


    //勾选复选框和取消复选框都会触发的方法
    handleSelectionChange(selectionDataArray){
      //先清空，以最后一次勾选状态为准
      this.clueIdArray = [];
      //饿了么组件会自动传递复选框选的数据（10个user对象）（就像spring的依赖注入IOC）
      console.log(selectionDataArray);
      selectionDataArray.forEach(data => {
        let clueId = data.id;
        this.clueIdArray.push(clueId);
      })
      console.log(this.clueIdArray);
    },

    //批量删除
    batchDelClue() {
      if (this.clueIdArray.length <= 0) {
        //提示
        messageTip("请选择要删除的数据", "info")
      } else {
        let ids = this.clueIdArray.join(",")
        messageConfirm("您确定要删除这些数据吗？").then(() => {
          axios.delete("/api/clues", {
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
    },

    //获取线索数据
    getData(){
      //查询线索列表数据
      axios.get("/api/clues", {
        params: {
          current: this.currentPage //假设 this.currentPage 是当前页码的变量（初始是0，到后端就是1了）
        }
      }).then(response => {
        if (response.data.code === 200) {
          console.log(response.data.data.list)
          this.clueList = response.data.data.list;
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

    //导入excel的弹窗
    importExcel(){
      this.importExcelDialogVisible = true
    },

    //提交excel文件
    submitExcel(){
      this.$refs.uploadRef.submit();
    },

    //上传时会自动触发这个函数（饿了么允许手动上传文件）（记得前面属性名前加:）
    uploadFile(param){
      //param是饿了么组件自动给的
      console.log(param);
      let fileObj = param.file;//取得上传的文件信息（相当于html中的input）
      let formData = new FormData();
      formData.append('file', fileObj);//表单数据的装填
      axios.post('/api/importExcel',formData).then(response => {
        if (response.data.code === 200) {
          messageTip("Excel导入成功", "success");
          //清理一下上传的文件
          this.$refs.uploadRef.clearFiles();
          //页面刷新
          this.reload();
        }else {
          messageTip("Excel导入失败", "error")
        }
      })
    },

    addClue(){
      this.$router.push("/dashboard/clue/add")
    },

    edit(id){
      this.$router.push("/dashboard/clue/edit/" + id)
    },

    view(id){
      this.$router.push("/dashboard/clue/detail/" + id)
    },

    del(id){
      messageConfirm("您确定要删除该线索信息吗？").then(res => {
        //用户点击确定，就执行then
        axios.delete("/api/clue/" + id).then(response => {
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