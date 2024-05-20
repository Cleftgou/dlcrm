<template>

  <el-descriptions
      class="margin-top"
      title="用户详细信息"
      :column="2"
      :model="userDetail"
      border
  >
    <template #extra>
      <el-button type="primary" @click="back()">返回</el-button>
    </template>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <user />
          </el-icon>
          &nbsp;用户编号
        </div>
      </template>
      {{ userDetail.id }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><UserFilled /></el-icon>
          &nbsp;账户
        </div>
      </template>
      {{ userDetail.loginAct }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Hide /></el-icon>
          &nbsp;密码
        </div>
      </template>
      都是密文，拿到也不能反向解密
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Crop /></el-icon>
          &nbsp;姓名
        </div>
      </template>
      {{ userDetail.name }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Iphone /></el-icon>
          &nbsp;电话
        </div>
      </template>
      {{ userDetail.phone === null ? "未绑定" : userDetail.phone }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><ChatDotRound /></el-icon>
          &nbsp;邮箱
        </div>
      </template>
      {{ userDetail.email === null ? "未绑定" : userDetail.email }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;账户是否已过期
        </div>
      </template>
      {{ userDetail.accountNoExpired === 1 ? "否" : "是"}}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;密码是否已过期
        </div>
      </template>
      {{ userDetail.credentialsNoExpired === 1 ? "否" : "是"}}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;账户是否已锁定
        </div>
      </template>
      {{ userDetail.accountNoLocked === 1 ? "否" : "是"}}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;账户是否已启用
        </div>
      </template>
      {{ userDetail.accountEnabled === 1 ? "否" : "是"}}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><EditPen /></el-icon>
          &nbsp;创建时间
        </div>
      </template>
      {{ userDetail.createTime === null ? "未知" : userDetail.createTime }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Edit /></el-icon>
          &nbsp;创建人
        </div>
      </template>
      {{ userDetail.createByDO === null ? "未知" : userDetail.createByDO.name }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><EditPen /></el-icon>
          &nbsp;编辑时间
        </div>
      </template>
      {{ userDetail.editTime === null ? "未知" : userDetail.editTime }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Edit /></el-icon>
          &nbsp;编辑人
        </div>
      </template>
      {{ userDetail.editByDO === null ? "未知" : userDetail.editByDO.name }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Position /></el-icon>
          &nbsp;最后登录时间
        </div>
      </template>
      {{ userDetail.lastLoginTime === null ? "未知" : userDetail.lastLoginTime }}
    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
import "../http/httpRequest.js";
import axios from "axios";
import router from "../router/router.js";
import {Iphone, Location, OfficeBuilding, Setting, Tickets, User} from "@element-plus/icons-vue";

export default {
  name: "UserDetailView",
  components: {Setting, OfficeBuilding, Tickets, Location, Iphone, User},

  data(){
    return{
      //用户详情对象
      userDetail : {
        createByDO : {
        },
        editByDO : {
        }
      },
    }
  },
  mounted() {
    this.loadUserDetail()
  },
  methods: {
    //加载用户详情
    loadUserDetail(){
      //拿路由属性信息（$route），要和动态路由中配置的id名一致path:"user/:id",
      let id = this.$route.params.id;
      axios.get("/api/user/" + id).then(response=>{
        if (response.data.code === 200) {
          // console.log(response);
          this.userDetail = response.data.data;
          console.log(response.data.data);
        }
      })
    },

    //返回上一个路由
    back(){
      this.$router.go(-1);
    }
  }
}
</script>

<style scoped>
.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}
</style>