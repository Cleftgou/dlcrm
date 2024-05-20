<template>
  <el-button type="primary" @click=add()>添加用户</el-button>
  <el-button type="danger" @click=batchDel()>批量删除</el-button>

<!--  列表自带循环功能-->
  <el-table
      :data="userList"
      stripe style="width: 100%"
      @selection-change="handleSelectionChange">

    <el-table-column type="selection" width="55" />

    <el-table-column type="index" label="序号" width="60" />

    <el-table-column property="loginAct" label="账号" />

    <el-table-column property="name" label="姓名" show-overflow-tooltip />

    <el-table-column property="phone" label="手机" show-overflow-tooltip />

    <el-table-column property="email" label="邮箱" show-overflow-tooltip />

    <el-table-column property="createTime" label="创建时间" show-overflow-tooltip />

    <el-table-column label="操作" show-overflow-tooltip >

      <template #default="scope">
        <!--作用域插槽，获取到这一行内容的数据（这里scope代表了userList，row代表行，即user对象）-->
        <el-button type="success" :icon="Search" circle @click="view(scope.row.id)" />
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

  <el-dialog v-model="userDialogVisible" :title="userQuery.id ? '编辑用户' : ' 新增用户'" width="900px" center draggable>

    <el-form ref="userRefForm" :model="userQuery" size="default" label-width="100px"
             label-position="left" :rules="userRules">

      <el-form-item label="账号" prop="loginAct">
        <el-input v-model="userQuery.loginAct" placeholder="请输入账号" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>

      <el-form-item label="密码" v-if="userQuery.id>0"><!--编辑模式，密码不设置规则限制，注意v-if与v-show的区别，if是真的不渲染，源代码也没有了-->
        <el-input v-model="userQuery.loginPwd" placeholder="不修改密码则无需填写" clearable show-password
                  :style="{width: '100%'}"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="loginPwd" v-else><!--新增模式-->
        <el-input v-model="userQuery.loginPwd" placeholder="请输入密码" clearable show-password
                  :style="{width: '100%'}"></el-input>
      </el-form-item>

      <el-form-item label="姓名" prop="name">
        <el-input v-model="userQuery.name" placeholder="请输入姓名" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>

      <el-form-item label="手机号" prop="phone">
        <el-input v-model="userQuery.phone" placeholder="请输入手机号" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>

      <el-form-item label="邮箱" prop="email">
        <el-input v-model="userQuery.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>

      <el-form-item label="账号可用吗" prop="accountNoExpired">
        <el-select v-model="userQuery.accountNoExpired" placeholder="请选择账号是否可用" clearable
                   :style="{width: '100%'}">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />

        </el-select>
      </el-form-item>

      <el-form-item label="密码可用吗" prop="credentialsNoExpired">
        <el-select v-model="userQuery.credentialsNoExpired" placeholder="请选择密码是否可用" clearable
                   :style="{width: '100%'}">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="账号正常吗" prop="accountNoLocked">
        <el-select v-model="userQuery.accountNoLocked" placeholder="请选择账号是否正常" clearable
                   :style="{width: '100%'}">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="账号启用吗" prop="accountEnabled">
        <el-select v-model="userQuery.accountEnabled" placeholder="请选择账号是否启用" clearable :style="{width: '100%'}">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>

    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="userDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="userSubmit()">提交</el-button>
      </div>
    </template>

  </el-dialog>

</template>

<script>
import "../http/httpRequest.js";
import {Delete, Edit, Search} from "@element-plus/icons-vue";
import axios from "axios";
import {messageConfirm, messageTip} from "../util/util.js";

export default {
  name: "UserView",

  //从子路由中通过inject可以拿到（注入父级页面提供的属性，函数等等）
  inject: ['content', 'reload'],

  computed: {
    Search() {
      return Search
    },
    Delete() {
      return Delete
    },
    Edit() {
      return Edit
    }
  },

  data() {
    return {
      userList : [{}],
      pageSize : 0,
      total : 0,
      currentPage: 0,
      userDialogVisible: false,
      //封装用户查询详情的结果对象
      userQuery: {},
      userRules: {
        loginAct: [
          {
            required: true,
            message: "请输入账号",
            trigger: "blur"
          },
          {
            min: 4,
            max: 16,
            message: "账号长度为4-16位",
            trigger: "blur"
          }
        ],
        loginPwd: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur"
          },
          {
            min: 6,
            max: 16,
            message: "密码长度为6-16位",
            trigger: "blur"
          }
        ],
        name: [
          {
            required: true,
            message: "请输入姓名",
            trigger: "blur"
          },
          {
            //正则验证必须为中文
            pattern: /^[\u4e00-\u9fa5]{1,5}$/,
            message: "请使用中文字符",
            trigger: "blur"
          }
        ],
        phone: [
          {
            required: true,
            message: "请输入手机号",
            trigger: "blur"
          },
          {
            //手机号正则验证
            pattern: /^1[3456789]\d{9}$/,
            message: "手机号格式不正确",
            trigger: "blur"
          }
        ],
        email: [
          {
            required: true,
            message: "请输入邮箱",
            trigger: "blur"
          },
          {
            //正则验证email格式
            pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message: "邮箱格式不正确",
            trigger: "blur"
          }
        ],
        accountNoExpired: [
          {
            required: true,
            message: "请作出选择",
            trigger: "change"
          }
        ],
        credentialsNoExpired: [
          {
            required: true,
            message: "请作出选择",
            trigger: "change"
          }
        ],
        accountNoLocked: [
          {
            required: true,
            message: "请作出选择",
            trigger: "change"
          }
        ],
        accountEnabled: [
          {
            required: true,
            message: "请作出选择",
            trigger: "change"
          }
        ]
      },
      //选项数组
      options: [
        {label: '是', value: 1},
        {label: '否', value: 0},
      ],
      //用户id的数组(用来记住多选)
      userIdArray: [],
    }
  },
  mounted() {
    //直接去查询用户列表，这里的这个参数并没有用，因为后端current == null || current < 1，自动置为1，后面toPage页码变化才会真的变化
    this.getData(this.currentPage);
  },
  methods : {
    //勾选复选框和取消复选框都会触发的方法
    handleSelectionChange(selectionDataArray){
      //先清空，以最后一次勾选状态为准
      this.userIdArray = [];
      //饿了么组件会自动传递复选框选的数据（10个user对象）（就像spring的依赖注入IOC）
      console.log(selectionDataArray);
      selectionDataArray.forEach(data => {
        let userId = data.id;
        this.userIdArray.push(userId);
      })
      console.log(this.userIdArray);
    },

    //查询某页用户的全部数据userList
    getData(){
      //查询用户列表数据
      axios.get("/api/users", {
          params: {
            current: this.currentPage //假设 this.currentPage 是当前页码的变量（初始是0，到后端就是1了）
          }
        }).then(response => {
          if (response.data.code === 200) {
            console.log(response.data.data.list)
            this.userList = response.data.data.list;
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

    //跳转到某个用户的详情组件界面
    view(id){
      console.log(id);
      //跳转路由($router用来执行路由方法)(动态路由)
      this.$router.push("/dashboard/user/" + id);
    },

    //打开新增用户弹窗
    add(){
      //让新增用户时没有显示之前编辑用户的数据
      this.userQuery = {};
      this.userDialogVisible = true;
      // 重置表单验证状态，判断是否为空，为空就不刷新了
      if (this.$refs['userRefForm']) {
        this.$refs['userRefForm'].resetFields();
      } else {
        console.log("表单不需要刷新.");
      }
    },


    //提交用户添加请求
    userSubmit(){
      //进行表单验证
      this.$refs.userRefForm.validate(valid => {

        //验证提高后再提交表单
        if (valid) {
          let formData = new FormData();
          //拿数据赋值
          for (let field in this.userQuery) {
            //字段名，字段值
            formData.append(field, this.userQuery[field]);
          }

          if (this.userQuery.id > 0){

            //编辑状态
            axios.put("/api/user",formData).then(response => {
              console.log(response.data)
              if (response.data.code === 200) {
                messageTip("编辑用户成功", "success")
                this.reload()
              } else {
                messageTip("编辑用户失败", "error")
              }
            })

          } else {
            //新增用户状态
            axios.post("/api/user",formData).then(response => {
              console.log(response.data)
              if (response.data.code === 200) {
                messageTip("添加用户成功", "success")

                //重新获取一下用户列表，这种不够好用
                //this.getData(this.currentPage);

                //调用一下父级传给的函数，实现上面的功能，猜测底层还是调用mounted钩子，只不过这种可以把添加弹窗也刷新掉
                this.reload()

                //父级传来的数据console.log(this.content);
              } else {
                messageTip("添加用户失败", "error")
              }
            })
          }

        }
      })
    },

    //编辑
    edit(id){
      this.userDialogVisible = true;
      this.loadUser(id)
    },

    //单查
    loadUser(id){
      axios.get("/api/user/" + id).then(response => {
        if (response.data.code === 200){
          this.userQuery = response.data.data;
          //不在前端显示密码
          this.userQuery.loginPwd = "";
          // 重置表单验证状态
          this.$refs['userRefForm'].resetFields();
        }else {
          //后期替换成错误弹窗
          console.log(response.data.code)
        }
      })
    },

    //删除
    del(id){
      messageConfirm("您确定要删除该用户吗？").then(res => {
        //用户点击确定，就执行then
        axios.delete("/api/user/" + id).then(response => {
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

    //批量删除
    batchDel(){
      if (this.userIdArray.length <= 0){
        //提示
        messageTip("请选择要删除的数据", "info")
      }else {

        let ids = this.userIdArray.join(",")

        messageConfirm("您确定要删除这些数据吗？").then(() => {
          axios.delete("/api/users", {
            params: {
              ids: ids
            }
          }).then(response => {
            if (response.data.code === 200){
              messageTip("批量删除成功", "success")
              //刷新一下右侧页面
              this.reload()
            }else {
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
}
</script>

<style scoped>
.el-pagination{
  margin-top: 12px;
}
.el-table{
  margin-top: 12px;
}
</style>