<template>
  <el-form
      ref="clueRefForm"
      :model="clueQuery"
      :rules="clueRules"
      label-width="100px"
      style="max-width: 95%;">

    <el-form-item label="负责人">
      <!--选项的 item.id 值将成为 clueQuery.ownerId 的值-->
      <el-select
          v-model="clueQuery.ownerId"
          placeholder="请选择负责人"
          style="width: 100%"
          clearable
          disabled>
        <el-option
            v-for="item in ownerOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="所属活动">
      <el-select
          v-model="clueQuery.activityId"
          placeholder="请选择所属活动"
          style="width: 100%"
          clearable>
        <el-option
            v-for="item in activityOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="姓名" prop="fullName">
      <el-input v-model="clueQuery.fullName"/>
    </el-form-item>

    <el-form-item label="称呼">
      <el-select
          v-model="clueQuery.appellation"
          placeholder="请选择称呼"
          style="width: 100%"
          clearable>
        <el-option
            v-for="item in appellationOptions"
            :key="item.id"
            :label="item.typeValue"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="手机" v-if="clueQuery.id > 0"><!--此时是编辑-->
      <el-input v-model="clueQuery.phone" disabled/>
    </el-form-item>

    <el-form-item label="手机" prop="phone" v-else><!--此时是录入-->
      <el-input v-model="clueQuery.phone"/>
    </el-form-item>

    <el-form-item label="微信">
      <el-input v-model="clueQuery.weixin"/>
    </el-form-item>

    <el-form-item label="QQ" prop="qq">
      <el-input v-model="clueQuery.qq"/>
    </el-form-item>

    <el-form-item label="邮箱" prop="email">
      <el-input v-model="clueQuery.email"/>
    </el-form-item>

    <el-form-item label="年龄" prop="age">
      <el-input v-model="clueQuery.age"/>
    </el-form-item>

    <el-form-item label="职业">
      <el-input v-model="clueQuery.job"/>
    </el-form-item>

    <el-form-item label="年收入" prop="yearIncome">
      <el-input v-model="clueQuery.yearIncome"/>
    </el-form-item>

    <el-form-item label="住址">
      <el-input v-model="clueQuery.address"/>
    </el-form-item>

    <el-form-item label="贷款">
      <el-select
          v-model="clueQuery.needLoan"
          placeholder="请选择是否需要贷款"
          style="width: 100%"
          clearable>
        <el-option
            v-for="item in needLoanOptions"
            :key="item.id"
            :label="item.typeValue"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="意向状态">
      <el-select
          v-model="clueQuery.intentionState"
          placeholder="请选择意向状态"
          style="width: 100%"
          clearable>
        <el-option
            v-for="item in intentionStateOptions"
            :key="item.id"
            :label="item.typeValue"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="意向产品">
      <el-select
          v-model="clueQuery.intentionProduct"
          placeholder="请选择意向产品"
          style="width: 100%"
          clearable>
        <el-option
            v-for="item in productOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="线索状态">
      <el-select
          v-model="clueQuery.state"
          placeholder="请选择线索状态"
          style="width: 100%"
          clearable>
        <el-option
            v-for="item in clueStateOptions"
            :key="item.id"
            :label="item.typeValue"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="线索来源">
      <el-select
          v-model="clueQuery.source"
          placeholder="请选择线索来源"
          style="width: 100%"
          clearable>
        <el-option
            v-for="item in sourceOptions"
            :key="item.id"
            :label="item.typeValue"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="线索描述" prop="description">
      <el-input
          v-model="clueQuery.description"
          :rows="5"
          type="textarea"
          placeholder="请输入线索描述"/>
    </el-form-item>

    <el-form-item label="下次联系时间">
      <el-date-picker
          v-model="clueQuery.nextContactTime"
          type="datetime"
          style="width: 100%;"
          value-format="YYYY-MM-DD HH:mm:ss"
          placeholder="请选择下次联系时间"/>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="addClueSubmit">提 交</el-button>
      <el-button @click="goBack">返 回</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import "../http/httpRequest.js";
import axios from "axios";
import {messageTip} from "../util/util.js";

export default {
  name: "ClueRecordView",

  data() {
    return {
      //线索表单对象，初始值是空
      clueQuery: {},
      //负责人的下拉选项
      ownerOptions: [{}],
      //市场活动的下拉选项数据，初始值是空
      activityOptions: [{}],
      //意向产品的下拉选项
      productOptions: [{}],
      appellationOptions: [{}],
      needLoanOptions: [{}],
      intentionStateOptions: [{}],
      clueStateOptions: [{}],
      sourceOptions: [{}],
      //录入线索验证规则
      clueRules : {
        phone : [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { pattern : /^1[3-9]\d{9}$/, message: '手机号码格式有误', trigger: 'blur'},
          //方法验证，去数据库查看手机号是不是已经登记过了
          { validator : this.checkPhone, trigger: 'blur'}
        ],
        fullName : [
          { min: 2, message: '姓名至少2个汉字', trigger: 'blur'},
          { pattern : /^[\u4e00-\u9fa5]{0,}$/, message: '姓名必须为中文汉字', trigger: 'blur'},
        ],
        qq : [
          { min: 5, message: 'QQ号至少为5位', trigger: 'blur'},
          { pattern : /^\d+$/, message: 'QQ号码必须为数字', trigger: 'blur'},
        ],
        email: [
          { pattern : /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, message: '邮箱格式有误', trigger: 'blur'},
        ],
        age: [
          { pattern : /^(?:[1-9][0-9]?|0)$/, message: '年龄必须为0到99的数字', trigger: 'blur'},
        ],
        yearIncome: [
          { pattern : /^[0-9]+(\.[0-9]{2})?$/, message: '年收入必须是整数或者两位小数', trigger: 'blur'}
        ],
        description: [
          { min: 5, max: 255, message: '线索描述长度为5-255个字符', trigger: 'blur'},
        ],
      }
    }
  },

  mounted() {
    //需要调用五次
    this.loadDicValue('appellation');
    this.loadDicValue('needLoan');
    this.loadDicValue('intentionState');
    this.loadDicValue('clueState');
    this.loadDicValue('source');
    this.loadDicValue('activity');
    this.loadDicValue('product');
    this.loadOwner();
    this.loadLoginUser();
    //新增和编辑共用一个界面，需要进行判断
    this.loadClue();
  },

  methods : {

    //加载字典数据
    loadDicValue(typeCode) {
      axios.get("/api/dicValue/" + typeCode).then( resp => {
        //console.log(resp)
        if (resp.data.code === 200) {
          if (typeCode === 'appellation') {
            this.appellationOptions = resp.data.data;
          } else if (typeCode === 'needLoan') {
            this.needLoanOptions = resp.data.data;
          } else if (typeCode === 'intentionState') {
            this.intentionStateOptions = resp.data.data;
          } else if (typeCode === 'clueState') {
            this.clueStateOptions = resp.data.data;
          } else if (typeCode === 'source') {
            this.sourceOptions = resp.data.data;
          } else if (typeCode === 'activity') {
            this.activityOptions = resp.data.data;
          } else if (typeCode === 'product') {
            this.productOptions = resp.data.data;
          }
        }
      })
    },

    //加载当前登录用户（让这个下拉列表选中id是xx的这个人）
    loadLoginUser() {
      axios.get("/api/login/info").then( (resp) => {
        //只给clueQuery这个用户对象一个id，因为现在是增加模式
        this.clueQuery.ownerId = resp.data.data.id;
      })
    },

    //加载负责人下拉菜单数据（这个地方是加载全部的负责人列表）
    loadOwner(){
      axios.get("/api/owner").then(response => {
        if (response.data.code === 200) {
          //console.log(response.data.data)
          this.ownerOptions = response.data.data;
        }
      })
    },

    //验证手机号是否已经录入（饿了么自动注入的）
    checkPhone(rule, value, callback){
      //console.log(rule)//规则
      //console.log(value)//就是输入的手机号
      //console.log(callback)//回调函数
      let phone = value
      if (phone){
        axios.get("/api/clue/" + phone).then( (resp) => {
          if (resp.data.code === 500) {
            return callback(new Error("这手机号录入过了，不能再录入"));
          } else {
            return callback();//验证通过
          }
        })
      }
    },

    //提交表单
    addClueSubmit(){
      this.$refs.clueRefForm.validate((isValid) => {
        if (isValid){
          let formData = new FormData();
          for (let field in this.clueQuery) {
            //还是那句话，空的内容不要传，不然后端拿clueQuery对象接收时null转类型会出问题
            if  (this.clueQuery[field]) { //this.clueQuery[field]有值，this.clueQuery[field]存在，this.clueQuery[field]不为空
              formData.append(field, this.clueQuery[field]);
            }
          }
          if (this.clueQuery.id > 0){
            //编辑
            axios.put("/api/clue", formData).then( (resp) => {
              if (resp.data.code === 200) {
                messageTip("编辑成功", "success");
                this.$router.push("/dashboard/clue");
              } else {
                messageTip("编辑失败", "error");
              }
            })
          } else {
            axios.post("/api/clue", formData).then( (resp) => {
              if (resp.data.code === 200) {
                messageTip("录入线索成功", "success");
                this.$router.push("/dashboard/clue");
              } else {
                messageTip("录入线索失败", "error");
              }
            })
          }
        }
      })
    },

    //返回
    goBack(){
      this.$router.go(-1);
    },

    //加载要编辑的数据
    loadClue(){
      let id = this.$route.params.id;
      console.log(id)
      if (id){
        axios.get("/api/clueDetail/" + id).then( (resp) => {
          if (resp.data.code === 200) {
            this.clueQuery = resp.data.data;
          }
        })
      }
    },

  }

}
</script>


<style scoped>

</style>