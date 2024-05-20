// 添加请求拦截器（就像spring的面向切面编程aop）
import axios from "axios";
import {getTokenName, messageConfirm, messageTip, removeToken} from "../util/util.js";
import {ElMessageBox} from "element-plus";

axios.defaults.baseURL = "http://localhost:8088/";

axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么，在请求头中放一个token（jwt），传给后端接口
    let token = sessionStorage.getItem(getTokenName());
    if (!token){
        //sessionStorage中不存在
        token = localStorage.getItem(getTokenName());
        //加上一个记住我的标志，后面token刷新要用
        config.headers['rememberMe'] = true;
    }
    if (token){
        //有值，命名随意
        config.headers['Authorization'] = token;
    }
    return config;
}, function (error) {
    // 对请求错误做些什么（拒绝访问）
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    //拦截返回的token验证结果，进行响应的提示和页面调转
    if (response.data.code > 900) {
        //错误代码验证为通过都是900以上
        //给用户提示并跳转界面
        messageConfirm(response.data.msg + ",是否重新去登录？").then(() => {//用户点击确定，去登录
            //清理掉前端的token，反正验证token已经未通过了
            removeToken();
            window.location.href = "/";
        }).catch(() => {//用户点击取消
            messageTip("取消登录", "warning");
        })
        //防止弹窗两次问题（这种只有极端情况，redis数据库出问题了才会有，并且两个弹窗确实也说明了问题，所以不处理了）
        //return ;
    }

    return response;
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
});

export function hi(){
    alert("你好！");
}