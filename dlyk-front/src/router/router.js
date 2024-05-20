//从依赖中导入这两个函数
import {createRouter, createWebHistory} from "vue-router";

//定义一个变量存储创建路由
let router = createRouter({
    //路由历史（浏览记录地址）
    history: createWebHistory(),
    //配置路由（vue访问的路径），是一个数组，可配置多个
    routes:[
        {
            //路由的路径
            path:"/",
            //路由路径所对应的界面（登录界面）
            component:()=>import('../view/LoginView.vue'),
        },
        {
            path:"/dashboard",
            component:()=>import('../view/DashboardView.vue'),
            //子路由
            children : [
                {
                    //这个是ai对话
                    path:"aiChat",
                    meta : {
                      name : '智慧对话'
                    },
                    component:()=>import('../view/ChatAIView.vue'),
                },
                {
                    //空路由（什么都没选的情况下）相当于是个斜杠，统计页面
                    path:"",
                    meta : {
                        name : '营销统计'
                    },
                    component:()=>import('../view/StatisticView.vue'),
                },
                {
                    //子路径不能带斜杠
                    path:"user",
                    meta : {
                        name : '用户管理'
                    },
                    component:()=>import('../view/UserView.vue'),
                },
                {
                    //id是动态变量，使用:拼接（动态路由）
                    path:"user/:id",
                    meta : {
                        name : '用户详情'
                    },
                    component:()=>import('../view/UserDetailView.vue'),
                },
                {
                    path:"activity",
                    meta : {
                        name : '市场活动管理'
                    },
                    component:()=>import('../view/ActivityView.vue'),
                },
                {
                    path:"activity/add",
                    meta : {
                        name : '新增市场活动'
                    },
                    component:()=>import('../view/ActivityRecordView.vue'),
                },
                {
                    path:"activity/edit/:id",
                    meta : {
                        name : '编辑市场活动'
                    },
                    component:()=>import('../view/ActivityRecordView.vue'),
                },
                {
                    path:"activity/:id",
                    meta : {
                        name : '市场活动详情'
                    },
                    component:()=>import('../view/ActivityDetailView.vue'),
                },
                {
                    path:"clue",
                    meta : {
                        name : '线索管理'
                    },
                    component:()=>import('../view/ClueView.vue'),
                },
                {
                    path:"clue/add",
                    meta : {
                        name : '新增线索'
                    },
                    component:()=>import('../view/ClueRecordView.vue'),
                },
                {
                    path:"clue/edit/:id",
                    meta : {
                        name : '编辑线索'
                    },
                    component:()=>import('../view/ClueRecordView.vue'),
                },
                {
                    path:"clue/detail/:id",
                    meta : {
                        name : '线索详情'
                    },
                    component:()=>import('../view/ClueDetailView.vue'),
                },
                {
                    path:"customer",
                    meta : {
                        name : '客户管理'
                    },
                    component:()=>import('../view/CustomerView.vue'),
                },
                {
                    path:"product",
                    meta : {
                        name : '产品管理'
                    },
                    component:()=>import('../view/ProductView.vue'),
                },
                {
                    path:"tran",
                    meta : {
                        name : '交易管理'
                    },
                    component:()=>import('../view/TranView.vue'),
                },
                {
                    path:"dictype",
                    meta : {
                        name : '字典类型管理'
                    },
                    component:()=>import('../view/DicTypeView.vue'),
                },
                {
                    path:"dicvalue",
                    meta : {
                        name : '字典字段管理'
                    },
                    component:()=>import('../view/DicValueView.vue'),
                },
                {
                    path:"system",
                    meta : {
                        name : '系统管理'
                    },
                    component:()=>import('../view/SystemView.vue'),
                }
            ]
        },
    ]
})

router.beforeEach((to, from, next) => {
    //to是路由信息
    //from是开源的路由信息
    //next是帮助我们跳转路由的函数
    console.log(to)
    next()
})

//导出创建好的路由对象（变量）
export default router;