import {ElMessage, ElMessageBox} from "element-plus";

// 弹窗（各种小弹窗）
export function messageTip(msg, type){
    ElMessage({
        showClose: true,//消息提示是否可以关闭
        message: msg,//内容
        center: true,//文字居中
        duration: 3000,//显示多久消失
        type: type,//消息类型
    })
}

//拿到token名字
export function getTokenName(){
    return "dlyk_token"
}

//删除旧token
export function removeToken(){
    window.sessionStorage.removeItem(getTokenName());
    window.localStorage.removeItem(getTokenName());
}

// 弹窗（确认与取消）
export function messageConfirm(msg){
    return ElMessageBox.confirm(
        msg,
        '警告',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
}

//获取token
export function getToken() {
    let token = window.sessionStorage.getItem(getTokenName());
    if (!token) {
        token = window.localStorage.getItem(getTokenName());
    }
    if (!token) {//两个地方都没拿到，说明在浏览器客户端没有登录
        messageConfirm("Token不存在, 是否重新去登录？").then(() => { //当点击“确定”按钮就执行该then函数
            //去重新登录，把浏览器的token清理一下
            removeToken();
            //跳到登录页
            window.location.href = "/";
        }).catch(() => { //当点击“取消”按钮就执行该catch函数
            messageTip("取消去登录", "warning");
        })
    } else {
        return token;
    }
}