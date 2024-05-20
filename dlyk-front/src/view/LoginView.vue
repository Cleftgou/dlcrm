<template>

  <div class="a" style="height: 100vh; display: flex; align-items: center; justify-content: center; background-color: #1aa094">

    <div style="display: flex; background-color: white; width: 60%; height: 60%;border-radius: 5px; overflow: hidden">
      <div style="flex: 1; display: flex;align-items: center;justify-content: center;">
        <img src="../assets/login.png" alt="一张图片" style="width: 90%">
      </div>

      <div style="flex: 1; display: flex;align-items: center;justify-content: center">
        <el-form ref="loginReForm" :model="user" label-width="auto" style="width: 80%" :rules="loginRules">
          <div style="font-size: 20px; font-weight: bold;text-align: center;margin-bottom: 20px">欢迎登录动力CRM管理平台</div>
          <el-form-item prop="loginAct">
            <el-input v-model="user.loginAct" placeholder="请输入账号" />
          </el-form-item>

          <el-form-item prop="loginPwd">
            <el-input type="password" v-model="user.loginPwd" placeholder="请输入密码" />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="login()" style="width: 100%">登 录</el-button>
          </el-form-item>

          <div style="display: flex">
            <el-form-item style="flex: 1">
              <el-checkbox label="记住我" v-model="user.rememberMe"/>
            </el-form-item>
            <div style="flex: 1;text-align: right">
              <span style="color: #1b84f1; cursor: pointer" @click="userManual">《系统使用手册》</span>
            </div>
          </div>


        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {getTokenName, messageTip, removeToken} from "../util/util.js";
//让过滤器起作用!
import "../http/httpRequest.js";
import {hi} from "../http/httpRequest.js";
//定义后端接口的端口前缀
axios.defaults.baseURL = "http://localhost:8088"

export default {
  name: "LoginView",
  data(){
    return{
      //对象定义
      user: {
        loginAct: '',
        loginPwd: '',
        rememberMe: '',
      },
      //字符串变量定义
      name: '',
      //数字变量定义
      age: 0,
      //数组变量定义
      arr: [],
      //list集合对象定义
      userList: [{}],
      loginRules : {
        //表单的验证规则
        loginAct : [
          { required: true, message: '请输入登录账号', trigger: 'blur' },
          {
            min: 4,
            max: 16,
            message: "账号长度为4-16位",
            trigger: "blur"
          }
        ],
        loginPwd : [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 16, message: '密码长度应在6-16位之间', trigger: 'blur' },
        ]
      }
    }
  },
  mounted() {
    //实现免登录，从localstorage尝试拿token
    this.freeLogin();
  },
  methods: {

    userManual(){
      messageTip("根本没什么使用手册，快去登录吧！", "success")
    },

    //登录函数
    login(){
      //验证输入框合法性（执行表单验证规则loginAct和loginPwd）
      //ref 加在普通的元素上，用this.$refs.（ref值） 获取到的是dom元素
      //ref 加在子组件上，用this.$refs.（ref值） 获取到的是组件实例，可以使用组件的所有方法。在使用方法的时候直接this.$refs.（ref值）.方法（） 就可以使用了。
      //将 ref 直接添加到原生 HTML 元素（如 div, input, button 等）上时，可以通过 $refs 来访问这些 DOM 元素。
      //而将 ref 添加到自定义组件标签上时，则是为了访问整个组件实例，可以通过 $refs 访问组件实例以调用组件内部的方法或访问数据。
      //console.log(this.$refs.loginReForm)
      this.$refs.loginReForm.validate(isValid => {
        //isValid是验证后的结果true和false
        if (isValid){
          //alert(this.user.rememberMe)
          //alert("提交用户信息中！")
          //可以提交，不满足会有红色提示

          //定义要提交的内容json格式
          // const postData = {
          //   key1: 'value1',
          //   key2: 'value2'
          // };

          //FormData代表了一个表单数据的集合，不同于json，但能直接作为请求体发送到服务器，
          //json还需要先进行序列化（JSON.stringify）然后再发送到服务器，此外Axios会在发送请求时自动处理这个过程
          let formDate = new FormData();
          formDate.append("loginAct", this.user.loginAct)
          formDate.append("loginPwd", this.user.loginPwd)
          formDate.append("rememberMe", this.user.rememberMe)

          // 发送 POST 请求
          axios.post('/api/login', formDate)
          .then(response => {
            console.log('服务器响应：', response.data);
            if (response.data.code === 200) {
              //登录成功，弹窗提示
              messageTip("登陆成功", "success");
              //删除旧token
              removeToken();

              //前端存储jwt
              //如果选了记住我，要放到localStorage里，防止浏览器关闭丢失，否则放到sessionStorage（在当前标签页有效）
              if (this.user.rememberMe === true){
                //R对象（response.data），里的data属性就是jwt数据
                localStorage.setItem(getTokenName(), response.data.data);
              } else {
                sessionStorage.setItem(getTokenName(), response.data.data);
              }

              //跳转到系统主页面
              window.location.href = "/dashboard";//仪表盘页面
            }else {
              messageTip("登录失败", "error");
              //console.log("当前用户的身份:" + response.data);
            }
          })
          .catch(error => {
            console.error('请求失败：', error);
          });

        }
      })
    },

    //免登录（自动登录）
    freeLogin(){
      let token = localStorage.getItem(getTokenName())
      if (token){
        //token不是空的，发送get请求会走前端配置好的过滤器，会拉出这个token去验证，验证通过才会真的去访问controller
        axios.get("/api/login/free").then(response => {
          if (response.data.code === 200) {
            //验证通过了，直接跳转到登录界面
            window.location.href = "/dashboard";
            //验证未通过的话，后前端过滤器会给出响应的提示
          }
        })
      }
    }
  }
}
</script>

<style scoped>
.a{
  background-image: url("../assets/background.jpg");
  background-size: cover;
  background-position: center;
}
</style>
