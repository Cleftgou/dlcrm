<template>
  <el-descriptions
      class="margin-top"
      title="市场活动详细信息"
      :column="2"
      :model="activityDetail"
      border
  >
    <template #extra>
      <el-button type="primary" @click="goBack()">返回</el-button>
    </template>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <user />
          </el-icon>
          &nbsp;活动编号
        </div>
      </template>
      {{ activityDetail.id }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><UserFilled /></el-icon>
          &nbsp;负责人
        </div>
      </template>
      {{ activityDetail.ownerDO.name }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Hide /></el-icon>
          &nbsp;活动名称
        </div>
      </template>
      {{ activityDetail.name }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Crop /></el-icon>
          &nbsp;开始时间
        </div>
      </template>
      {{ activityDetail.startTime }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Iphone /></el-icon>
          &nbsp;结束时间
        </div>
      </template>
      {{ activityDetail.endTime }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><ChatDotRound /></el-icon>
          &nbsp;活动预算
        </div>
      </template>
      {{ activityDetail.cost }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;创建时间
        </div>
      </template>
      {{ activityDetail.createTime || "未指定创建时间" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;创建人
        </div>
      </template>
      {{ activityDetail.createByDO ? activityDetail.createByDO.name : "未指定创建人" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;编辑时间
        </div>
      </template>
      {{ activityDetail.editTime || "未指定编辑时间" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><EditPen /></el-icon>
          &nbsp;编辑人
        </div>
      </template>
      {{ activityDetail.editByDO ? activityDetail.editByDO.name : "未指定编辑人" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;活动描述
        </div>
      </template>
      {{ activityDetail.description ? activityDetail.description : "未指定活动描述" }}
    </el-descriptions-item>

  </el-descriptions>

  <hr>

  <el-form ref="activityRemarkRefForm" :model="activityRemarkQuery" size="default" label-width="100px"
           label-position="left" :rules="activityRemarkRules">
    <el-form-item label="填写备注" prop="noteContent">
      <el-input
          v-model="activityRemarkQuery.noteContent"
          style="width: 240px"
          :rows="2"
          type="textarea"
          placeholder="请简短输入此活动备注"
          :style="{width: '90%'}"
      />
      <el-button type="primary" @click="activityRemarkSubmit"
                 style="position: absolute; bottom: 0; right: 0; margin-bottom: 0; margin-right: 40px;">提交</el-button>
    </el-form-item>
  </el-form>

  <hr>

  <el-table
      :data="activityRemarkList"
      stripe style="width: 100%">

    <el-table-column type="index" label="序号" width="60" />

    <el-table-column property="noteContent" label="备注内容" />

    <el-table-column property="createTime" label="备注时间" />

    <el-table-column property="createByDO.name" label="备注人" />

    <el-table-column property="editTime" label="编辑时间" />

    <el-table-column property="editByDO.name" label="编辑人" />

    <el-table-column label="操作" show-overflow-tooltip >
      <template #default="scope">
        <!--作用域插槽，获取到这一行内容的数据（这里scope代表了userList，row代表行，即user对象）-->
        <el-button type="primary" :icon="Edit" circle @click="edit(scope.row.id)" />
        <el-button type="danger" :icon="Delete" circle @click="del(scope.row.id)" />
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

  <!--活动备注记录的弹窗-->
  <el-dialog v-model="activityRemarkDialogVisible" title="编辑市场活动备注" width="900px" center draggable>

    <el-form ref="editActivityRemarkRefForm" :model="editActivityRemarkQuery" size="default" label-width="100px"
             label-position="left" :rules="editActivityRemarkRules">
      <el-form-item label="活动备注" prop="noteContent">
        <el-input
            v-model="editActivityRemarkQuery.noteContent"
            style="width: 240px"
            :rows="6"
            type="textarea"
            placeholder="请输入活动备注"
            :style="{width: '100%'}"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="activityRemarkDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="editActivityRemarkSubmit()">提交</el-button>
      </div>
    </template>

  </el-dialog>

</template>

<script>
import "../http/httpRequest.js";
import router from "../router/router.js";
import axios from "axios";
import {messageConfirm, messageTip} from "../util/util.js";
import {Delete, Edit} from "@element-plus/icons-vue";
export default {
  name: "ActivityDetailView",
  computed: {
    Delete() {
      return Delete
    },
    Edit() {
      return Edit
    }
  },

  inject: ['reload'],

  data() {
    return {
      //活动详情对象
      activityDetail : {
        ownerDO : {},
        createByDO : {},
      },
      activityRemarkRules : {
        noteContent: [
          { required: true, message: '请输入活动备注', trigger: 'blur'},
          { min: 5, max: 255, message: '活动备注长度为5-255个字符', trigger: 'blur' }
        ]
      },
      //备注信息（提交用）
      activityRemarkQuery : {},
      //编辑用
      editActivityRemarkQuery : {},
      //编辑用验证规则
      editActivityRemarkRules : {
        noteContent: [
          { required: true, message: '请输入活动备注', trigger: 'blur'},
          { min: 5, max: 255, message: '活动备注长度为5-255个字符', trigger: 'blur' }
        ]
      },
      //备注信息列表（显示用）
      activityRemarkList : [
        {
          createByDO: {},
          editByDO: {},
        }
      ],
      //分页
      pageSize : 0,
      total: 0,
      currentPage: 0,
      //弹窗
      activityRemarkDialogVisible: false
    }
  },

  mounted() {
    this.loadActivityDetail();
    this.loadActivityRemarkList();
  },

  methods:{
    //加载市场活动详情
    loadActivityDetail(){
      //拿id
      let id = this.$route.params.id;
      //这里共用接口了
      axios.get("/api/activity/" + id).then(response => {
        if (response.data.code === 200) {
          this.activityDetail = response.data.data;
          console.log(this.activityDetail.createByDO);
        }
      })
    },

    //返回上一级
    goBack(){
      this.$router.go(-1)
    },

    //备注提交
    activityRemarkSubmit(){
      this.$refs.activityRemarkRefForm.validate(isValid => {
        console.log(this.activityRemarkQuery.noteContent);
        if (isValid) {

          axios.post("/api/activity/remark", {
            activityId: this.activityDetail.id,
            noteContent: this.activityRemarkQuery.noteContent,
          }).then(response => {
            if (response.data.code === 200) {
              messageTip("提交成功", "success");
              this.reload();
            }else {
              messageTip("提交失败", "error")
            }
          })

        }
      })
    },

    //查询某个活动的全部活动备注activityRemarkList
    loadActivityRemarkList(){
      axios.get("/api/activity/remark", {
        params: {
          current: this.currentPage, //假设 this.currentPage 是当前页码的变量（初始是0）
          activityId : this.$route.params.id,//查当前活动的id
        }
      }).then(response => {
        if (response.data.code === 200) {
          this.activityRemarkList = response.data.data.list;
          this.pageSize = response.data.data.pageSize;
          this.total = response.data.data.total;
        }
      })
    },

    //更新要变化后所在页码的页数
    toPage(current){
      //饿了么组件会自动传递一个变化后的页码，这里才是真的修改页码
      this.currentPage = current
      //当前页码参数currentPage改动后，再去服务器获取请求
      this.loadActivityRemarkList()
    },

    //备注列表的编辑按钮弹窗，就是先单查
    edit(id){
      this.activityRemarkDialogVisible = true;
      //清除表单验证状态
      if (this.$refs['editActivityRemarkRefForm']) {
        this.$refs['editActivityRemarkRefForm'].resetFields();
      } else {
        console.log("表单不需要刷新.");
      }
      //先去把数据加载出来
      axios.get("/api/activity/remark/" + id).then(response => {
        if (response.data.code === 200) {
          this.editActivityRemarkQuery = response.data.data;
        }
      })
    },

    //真正的编辑操作
    editActivityRemarkSubmit(){
      this.$refs.editActivityRemarkRefForm.validate(isValid => {
        console.log(this.activityRemarkQuery.noteContent);
        if (isValid) {

          axios.put("/api/activity/remark", {
            id: this.editActivityRemarkQuery.id,
            noteContent: this.editActivityRemarkQuery.noteContent,
          }).then(response => {
            if (response.data.code === 200) {
              messageTip("编辑成功", "success");
              this.reload();
            }else {
              messageTip("编辑失败", "error")
            }
          })

        }
      })
    },

    //删除操作
    del(id){
      messageConfirm("您确定要删除该备注吗？").then(res => {
        //用户点击确定，就执行then
        axios.delete("/api/activity/remark/" + id).then(response => {
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
    }
  }

}
</script>

<style scoped>
.margin-top {
  margin-top: 10px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.el-form{
  margin-top: 20px;
  margin-bottom: 20px;
}
.el-descriptions{
  margin-bottom: 20px;
}
.el-pagination{
  margin-top: 12px;
}
</style>