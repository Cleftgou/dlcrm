<template>
  <el-container>
    <!--左侧-->
    <!--true表示折叠，为true时调整宽度为64px，false为200px-->
    <el-aside :width="isCollapse ? '64px' : '200px'">
      <router-link to="/dashboard" style="text-decoration: none; color: inherit;">
        <div class="demo-type">
          <div>
            <el-avatar
                src="http://picture.gptkong.com/images/2e44f441f50c4896a91d19fbe927f96c.png"
            />
            <span v-if="!isCollapse">@动力CRM</span>
          </div>
        </div>
      </router-link>


    <!--在这里设置了默认打开已当前已选择的界面:default-active="currentRouterPath"-->
      <el-menu
          active-text-color="#ffd04b"
          background-color="#2f4056"
          class="el-menu-vertical-demo"
          :default-active="currentRouterPath"
          text-color="#fff"
          style="border-right: 0 solid"
          :collapse-transition="false"
          :collapse="isCollapse"
          :router="true"
          :unique-opened="true">

        <!--市场活动-->
        <!--主菜单-->
        <el-sub-menu :index="String(index)" v-for="(menuPermission, index) in user.menuPermissionList" :key="menuPermission.id">
          <template #title>
            <!--复制官方的图标-->
            <el-icon><component :is="menuPermission.icon"></component></el-icon>
            <!--外层主菜单名-->
            <span>{{ menuPermission.name }}</span>
          </template>

          <!--子菜单-->
          <!--上面配置了，所以可以直接使用路由跳转，注意这里已经是内层for循环了-->
          <el-menu-item v-for="subPermission in menuPermission.subPermissionList" :key="subPermission.id" :index="subPermission.url">
            <!--<el-icon><School /></el-icon>-->
            <!--注意这里的标签写法，以及取数据属性名是java对象的属性名，不是数据库查询名-->
            <el-icon><component :is="subPermission.icon"></component></el-icon>
            <!--子菜单名-->
            {{ subPermission.name }}
          </el-menu-item>
        </el-sub-menu>

      </el-menu>

    </el-aside>

    <!--右侧-->
    <el-container>
      <!--上-->
      <el-header>

        <el-icon class="show" @click="showMenu" style="font-size: 26px; color: white"><Fold /></el-icon>
        <el-breadcrumb :separator-icon="ArrowRight" style="margin-left: 10px; font-size: 16px;" >
          <el-breadcrumb-item :to="{ path: '/dashboard' }"><span style="color: white">系统主页</span></el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">
            <span style="color: white">{{ $route.meta.name }}</span>
          </el-breadcrumb-item>
        </el-breadcrumb>

        <div style="flex: 1; width: 0; display: flex; align-items: center; justify-content: flex-end">
          <el-dropdown :hide-on-click="false">
            <div class="el-dropdown-link" style="display: flex; align-items: center;cursor: default;outline: none">
              <el-avatar
                  src="http://picture.gptkong.com/images/2e44f441f50c4896a91d19fbe927f96c.png"
              />
              <span style="color: white;margin-left: 10px ">{{ user.name }}</span><el-icon class="el-icon--right"><arrow-down /></el-icon>
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="myProfile()">我的资料</el-dropdown-item>
                <el-dropdown-item @click="changePassword()">修改密码</el-dropdown-item>
                <el-dropdown-item divided @click="logout()">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>

      </el-header>


      <!--中-->
      <el-main>
        <router-view v-if="isRouterAlive" :nowUser="this.user"></router-view>
      </el-main>

      <!--下-->
      <el-footer>@版权所有 2024-2099 动力CRM</el-footer>
    </el-container>
  </el-container>

  <!--个人详细信息弹窗-->
  <el-dialog v-model="myDetailDialogVisible" title="个人资料" width="900px" center draggable>
    <el-descriptions
        class="margin-top"
        title="你好，我的朋友！"
        :column="2"
        :model="user"
        border
    >

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon>
              <user />
            </el-icon>
            &nbsp;用户编号
          </div>
        </template>
        {{ user.id }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon><UserFilled /></el-icon>
            &nbsp;账户
          </div>
        </template>
        {{ user.loginAct }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon><Crop /></el-icon>
            &nbsp;姓名
          </div>
        </template>
        {{ user.name }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon><Iphone /></el-icon>
            &nbsp;电话
          </div>
        </template>
        {{ user.phone === null ? "未绑定" : user.phone }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon><ChatDotRound /></el-icon>
            &nbsp;邮箱
          </div>
        </template>
        {{ user.email === null ? "未绑定" : user.email }}
      </el-descriptions-item>

    </el-descriptions>


  </el-dialog>

  <!--个人修改密码弹窗-->
  <el-dialog v-model="changePasswordDialogVisible" title="修改密码" width="900px" center draggable>

    <div class="alert-container">
      <el-alert title="修改密码会自动退出当前登录账号，请牢记您的新密码！如无法修改，请联系管理员开通权限！" type="warning" show-icon :closable="false" />
    </div>

    <el-form ref="userChangePasswordForm" :inline="true" :model="user" class="demo-form-inline" :rules="passwordRules">

      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" v-model="user.newPassword" />
      </el-form-item>

      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="user.confirmPassword" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button type="primary" plain @click="this.changePasswordDialogVisible = false">取消</el-button>
      </el-form-item>
    </el-form>

  </el-dialog>

</template>

<script>
//一定要导入这个js文件，否则js文件配置的axios拦截器无效！
import "../http/httpRequest.js";
import axios from "axios";
import {messageConfirm, messageTip, removeToken} from "../util/util.js";
import {ArrowRight} from "@element-plus/icons-vue";
//定义后端接口地址的前缀
axios.defaults.baseURL = "http://localhost:8088";

export default{
  //定义组件名
  name: "DashboardView",
  computed: {
    ArrowRight() {
      return ArrowRight
    }
  },

  data() {
    return {
      //控制左侧菜单左右的展开和折叠，true是折叠，false是展开
      isCollapse : false,
      //登录用户对象，初始值是空
      user : {
        menuPermissionList : [],
        //在这里定义一下按钮权限，不然传给子组件会报错，虽然不影响使用
        permissionList : []
      },
      //是否显示右中侧内容
      isRouterAlive : true,
      //当前访问的路由路径
      currentRouterPath : '',
      myDetailDialogVisible : false,
      changePasswordDialogVisible : false,

      //修改密码
      passwordRules : {
        //表单的验证规则
        newPassword : [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 16, message: '密码长度应在6-16位之间', trigger: 'blur' },
        ],
        confirmPassword : [
          { required: true, message: '请再次输入新密码', trigger: 'blur' },
        ]
      },

    }
  },

  //提供者（可以提供很多东西，在子路由中都能直接拿来用）
  provide() {
    return {
      //提供一个函数
      reload : (reload) => {
        this.isRouterAlive = false;//先右侧内容隐藏
        this.$nextTick(function (){//这个函数是将回调函数延迟在下一次dom更新数据后调用（即数据更新后，在dom中渲染后，自动执行该函数）
          this.isRouterAlive = true;//显示右侧内容，相当于刷新了
        })
      },

      //提供一个字符串
      content : "你好",
    }
  },

  //vue的生命周期中的一个函数钩子，该钩子是在页面渲染后执行
  mounted() {
    //加载当前登录用户
    this.loadLoginUser();
    //加载当前路由路径
    this.loadCurrentRouterPath();
  },

  methods : {
    //修改密码
    changePassword() {
      this.changePasswordDialogVisible = true
      // 重置表单验证状态，判断是否为空，为空就不刷新了
      if (this.$refs['userChangePasswordForm']) {
        this.$refs['userChangePasswordForm'].resetFields();
      } else {
        console.log("表单不需要刷新.");
      }
    },

    //提交密码修改请求
    submitForm(){
      this.$refs['userChangePasswordForm'].validate((valid) => {
        if (valid) {
          // 密码验证通过，执行修改密码的逻辑
          if (this.user.newPassword === this.user.confirmPassword) {
            this.user.loginPwd = this.user.newPassword
            // 从user对象中删除confirmPassword和newPassword字段
            delete this.user.confirmPassword;
            delete this.user.newPassword;
            let formData = new FormData();
            //拿数据赋值
            for (let field in this.user) {
              //字段名，字段值
              formData.append(field, this.user[field]);
            }
            // 执行修改密码的操作
            axios.put("/api/user", formData).then(response => {
              console.log(response.data)
              if (response.data.code === 200) {
                messageTip("修改密码成功", "success")
                this.$router.go(0)
              } else {
                messageTip("修改密码失败", "error")
              }
            })
            // 修改密码成功后，强制退出账号
          } else {
            messageTip("两次输入的密码不一致", "error")
          }
        } else {
          //验证规则未通过
          messageTip("请把表单填写完整", "error")
          return false;
        }
      });
    },

    //加载当前登录的个人信息
    myProfile() {
      this.myDetailDialogVisible = true
    },

    //左侧菜单左右展开和折叠
    showMenu() {
      this.isCollapse = !this.isCollapse;
    },

    //加载当前登录用户
    loadLoginUser() {
      axios.get("/api/login/info").then( (resp) => {
        //用户姓名
        //console.log(resp.data.data.name)
        //用户对象的详细信息，能对这个人的身份进行判断，如果是管理员就跳子路由1，否则跳子路由2...
        console.log(resp.data.data)
        this.user = resp.data.data;
      })
    },

    //退出当前登录用户
    logout(){
      axios.get("/api/logout").then( (resp) => {
        if (resp.data.code === 200) {
          //退出成功，删除token
          removeToken()
          messageTip("退出成功!", "success")
          window.location.href = "/"
        } else {
          messageConfirm("系统退出发生错误，是否强制退出？").then(() => {
            removeToken();
            window.location.href = "/";
          }).catch(() => {
            messageTip("取消强制退出", "warning");
          })
        }
      })
    },

    loadCurrentRouterPath(){
      let path = this.$route.path;// /dashboard/activity/add
      //把这个路径分开
      let arr = path.split("/"); // [ ,dashboard,activity,add] 4个值
      if (arr.length > 3) {
        this.currentRouterPath = "/" + arr[1] + "/" + arr[2];
      } else {
        this.currentRouterPath = path;
      }
    }
  }
}
</script>

<style scoped>
.el-aside{
  background: #192431;
}
.el-header{
  background: #1aa094;
  height: 50px;
  line-height: 50px;
  display: flex;
  align-items: center;
}
.el-footer{
  background: #d1ecea;
  height: 20px;
  text-align: center;
  line-height: 20px;
}
.el-container{
  /*calc()函数用于计算一个长度值，这里的100vh表示视口高度的百分比值，即浏览器窗口的高度*/
  height: calc(100vh);
}
.Avatar{
  color: white;
  height: 35px;
  text-align: center;
  line-height: 35px;
}
.show{
  /*鼠标放上去变成手*/
  cursor: pointer;
}
.el-dropdown{
  line-height: 50px;
  float: right;
}

.demo-type {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}
.demo-type > div {
  display: flex;
  align-items: center; /* 垂直居中 */
  text-align: center;
  padding: 5px; /* 添加内边距 */
}
.demo-type > div:not(:last-child) {
  border-right: 1px solid var(--el-border-color);
}
.demo-type > div > span {
  color: white;
  margin-left: 5px; /* 适当的间距 */
}
.alert-container {
  margin-top: 10px;
  margin-bottom: 10px;
}
.el-aside {
  transition: width .3s;
}
</style>