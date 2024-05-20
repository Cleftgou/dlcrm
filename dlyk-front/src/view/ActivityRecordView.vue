<template>

  <el-form ref="activityRefForm" :model="activityQuery" size="default" label-width="100px"
           label-position="left" :rules="activityRules">

    <el-form-item label="负责人" prop="ownerId">
      <el-select
          v-model="activityQuery.ownerId"
          placeholder="请选择负责人"
          clearable
      >
        <el-option
            v-for="item in ownerOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="活动名称" prop="name">
      <el-input v-model="activityQuery.name" placeholder="请输入活动名称" clearable :style="{width: '100%'}"></el-input>
    </el-form-item>

    <el-form-item label="开始时间" prop="startTime">
      <el-date-picker
          v-model="activityQuery.startTime"
          type="datetime"
          placeholder="请选择开始时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          :style="{width: '100%'}"/>
    </el-form-item>

    <el-form-item label="结束时间" prop="endTime">
      <el-date-picker
          v-model="activityQuery.endTime"
          type="datetime"
          placeholder="请选择结束时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          :style="{width: '100%'}"/>
    </el-form-item>

    <el-form-item label="活动预算" prop="cost">
      <el-input v-model="activityQuery.cost" placeholder="请输入活动预算" clearable :style="{width: '100%'}"></el-input>
    </el-form-item>

    <el-form-item label="活动描述" prop="description">
      <el-input
          v-model="activityQuery.description"
          style="width: 240px"
          :rows="6"
          type="textarea"
          placeholder="请输入活动描述"
          :style="{width: '100%'}"
      />
    </el-form-item>

    <div style="float:right;">
      <el-button type="primary" @click="activitySubmit()">提交</el-button>
      <el-button type="danger" @click="goBack()">返回</el-button>
    </div>

  </el-form>

</template>

<script>
import "../http/httpRequest.js";
import axios from "axios";
import {messageTip} from "../util/util.js";
export default {
  name: "ActivityRecordView",
  //注入
  inject: ['reload'],

  data() {
    return {
      //市场活动对象，初始是空
      activityQuery: {},
      //表单验证规则
      activityRules: {
        ownerId: [
          {
            required: true,
            message: "请选择负责人",
            trigger: "change"
          },
        ],
        name: [
          {
            required: true,
            message: "请输入活动名称",
            trigger: "blur"
          }
        ],
        startTime: [
          {
            required: true,
            message: "请选择开始时间",
            trigger: "blur"
          }
        ],
        endTime: [
          {
            required: true,
            message: "请选择结束时间",
            trigger: "blur"
          }
        ],
        cost: [
          {
            required: true,
            message: "请输入活动预算",
            trigger: "blur"
          },
          {
            pattern: /^[0-9]+(\.[0-9]{2})?$/,
            message: '活动预算必须是整数或两位小数',
            trigger: "blur"
          }
        ],
        description: [
          {
            required: true,
            message: "请输入活动描述",
            trigger: "blur"
          },
          {
            min: 5,
            max: 255,
            message: '活动描述长度应在5-255字符之间',
            trigger: 'blur'
          },
        ]
      },
      //负责人的下拉选项
      ownerOptions: [{}],
    }
  },

  mounted() {
    //与之前用户点击下拉框去加载负责人列表不同，这里当页面加载时就去加载列表
    this.loadOwner();
    //加载要编辑的数据，由于公用一个界面所以进行判断
    this.loadActivity();
  },

  methods: {
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

    //返回
    goBack(){
      this.$router.go(-1);
    },

    //市场活动提交
    activitySubmit()  {
      let formData = new FormData();
      for (let field in this.activityQuery) {
        console.log(field  + " -- " + this.activityQuery[field])
        if (this.activityQuery[field])  { //this.activityQuery[field]有值，this.activityQuery[field]不是空，this.activityQuery[field]存在
          //也就是说空段不再传递
          formData.append(field, this.activityQuery[field]);
        }
      }
      this.$refs.activityRefForm.validate( (isValid) => {
        if (isValid) {
          if (this.activityQuery.id > 0) { /*编辑*/
            axios.put("/api/activity", formData).then(resp => {
              if (resp.data.code === 200) {
                messageTip("编辑市场活动成功", "success");
                //跳转到市场活动列表页
                this.goBack()
              } else {
                messageTip("编辑市场活动失败", "error");
              }
            })
          } else  {
            axios.post("/api/activity", formData).then(resp => {/*新增*/
              if (resp.data.code === 200) {
                messageTip("新增市场活动成功", "success");
                //跳转到市场活动列表页
                this.goBack()
              } else {
                messageTip("新增市场活动失败", "error");
              }
            })
          }
        }
      })
    },

    //加载要编辑的市场活动
    loadActivity(){
      //取路由地址里的数据
      let id = this.$route.params.id;
      if (id){
        //id存在，说明是编辑状态
        //查数据
        axios.get("/api/activity/" + id).then(response => {
          if (response.data.code === 200) {
            this.activityQuery = response.data.data;
          }
        })
      }
    }

  }
}
</script>

<style scoped>

</style>