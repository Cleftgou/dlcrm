<template>
  <el-descriptions
      class="margin-top"
      title="线索详细信息"
      :column="2"
      :model="clueDetail"
      border
  >
    <template #extra>
      <el-button type="primary" @click="goBack()">返回</el-button>
    </template>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><UserFilled /></el-icon>
          &nbsp;线索编号
        </div>
      </template>
      {{ clueDetail.id || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><UserFilled /></el-icon>
          &nbsp;负责人
        </div>
      </template>
      {{ clueDetail.ownerDO ? clueDetail.ownerDO.name : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <user />
          </el-icon>
          &nbsp;所属活动
        </div>
      </template>
      {{ clueDetail.activityDO.name ? clueDetail.activityDO.name : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Hide /></el-icon>
          &nbsp;姓名
        </div>
      </template>
      {{ clueDetail.fullName || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Crop /></el-icon>
          &nbsp;称呼
        </div>
      </template>
      {{ clueDetail.appellationDO.typeValue ? clueDetail.appellationDO.typeValue : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Iphone /></el-icon>
          &nbsp;手机
        </div>
      </template>
      {{ clueDetail.phone || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><ChatDotRound /></el-icon>
          &nbsp;微信
        </div>
      </template>
      {{ clueDetail.weixin || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;QQ
        </div>
      </template>
      {{ clueDetail.qq || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;邮箱
        </div>
      </template>
      {{ clueDetail.email || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;年龄
        </div>
      </template>
      {{ clueDetail.age || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><EditPen /></el-icon>
          &nbsp;职业
        </div>
      </template>
      {{ clueDetail.job || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;年收入
        </div>
      </template>
      {{ clueDetail.yearIncome || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;住址
        </div>
      </template>
      {{ clueDetail.address || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;是否需要贷款
        </div>
      </template>
      {{ clueDetail.needLoanDO.typeValue ? clueDetail.needLoanDO.typeValue : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;意向状态
        </div>
      </template>
      {{ clueDetail.intentionStateDO.typeValue ? clueDetail.intentionStateDO.typeValue : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;意向产品
        </div>
      </template>
      {{ clueDetail.intentionProductDO.name ? clueDetail.intentionProductDO.name : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;线索状态
        </div>
      </template>
      {{ clueDetail.stateDO.typeValue ? clueDetail.stateDO.typeValue : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;线索描述
        </div>
      </template>
      {{ clueDetail.description || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;线索来源
        </div>
      </template>
      {{ clueDetail.sourceDO.typeValue ? clueDetail.sourceDO.typeValue : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;下次联系时间
        </div>
      </template>
      {{ clueDetail.nextContactTime || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;创建时间
        </div>
      </template>
      {{ clueDetail.createTime || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;创建人
        </div>
      </template>
      {{ clueDetail.createDO.name ? clueDetail.createDO.name : "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;编辑时间
        </div>
      </template>
      {{ clueDetail.editTime || "未指定" }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Open /></el-icon>
          &nbsp;编辑人
        </div>
      </template>
      {{ clueDetail.editDO.name ? clueDetail.editDO.name : "未指定" }}
    </el-descriptions-item>

  </el-descriptions>

  <hr>

  <el-form ref="activityRemarkRefForm" :model="clueRemark" size="default" label-width="100px"
           label-position="left" :rules="clueRemarkRules">

    <el-form-item label="填写跟踪记录" prop="noteContent">
      <el-input
          v-model="clueRemark.noteContent"
          :rows="4"
          type="textarea"/>
    </el-form-item>

    <el-form-item label="跟踪方式" prop="noteWay">
      <el-select
          v-model="clueRemark.noteWay"
          placeholder="请选择跟踪方式"
          style="width: 100%"
          clearable>
        <!--选项的内容来自于 noteWayOptions 数组中每个对象的 typeValue 属性的值-->
        <!--在 Vue.js 中，el-option 组件用于创建下拉选择框的选项。每个选项都有一个 value 属性，
        当用户选择该选项时，将会返回这个值。在提供的代码中，value 属性被设置为 item.id，
        这意味着每个选项的值将与 noteWayOptions 数组中对应对象的 id 属性的值相对应。-->
        <el-option
            v-for="item in noteWayOptions"
            :key="item.id"
            :label="item.typeValue"
            :value="item.id"/>
      </el-select>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="clueRemarkSubmit">提 交</el-button>
      <el-button type="success" @click="convertCustomer" v-if="clueDetail.state !== -1">转换客户</el-button>
    </el-form-item>

  </el-form>

  <hr>

  <el-table
      :data="clueRemarkList"
      stripe style="width: 100%">

    <el-table-column type="index" label="序号" width="60" />

    <el-table-column property="noteWayDO.typeValue" label="跟踪方式" />

    <el-table-column property="noteContent" label="跟踪内容" />

    <el-table-column property="createTime" label="跟踪时间" />

    <el-table-column property="createByDO.name" label="跟踪人" />

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

  <!--线索备注记录的弹窗-->
  <el-dialog v-model="clueRemarkDialogVisible" title="编辑线索备注" width="900px" center draggable>

    <el-form ref="editActivityRemarkRefForm" :model="editClueRemarkQuery" size="default" label-width="100px"
             label-position="left">
      <el-form-item label="填写跟踪记录" prop="noteContent">
        <el-input
            v-model="editClueRemarkQuery.noteContent"
            :rows="4"
            type="textarea"/>
      </el-form-item>

      <el-form-item label="跟踪方式" prop="noteWay">
        <el-select
            v-model="editClueRemarkQuery.noteWay"
            placeholder="请选择跟踪方式"
            style="width: 100%"
            clearable>
          <el-option
              v-for="item in noteWayOptions"
              :key="item.id"
              :label="item.typeValue"
              :value="item.id"/>
        </el-select>
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="clueRemarkDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="editClueRemarkSubmit()">提交</el-button>
      </div>
    </template>

  </el-dialog>


  <!--线索转换为客户的弹窗（对话框）-->
  <el-dialog v-model="convertCustomerDialogVisible" title="线索转换客户" width="55%" center>
    <el-form ref="convertCustomerRefForm" :model="customerQuery" label-width="110px" :rules="convertCustomerRules">
      <el-form-item label="意向产品" prop="product">
        <el-select v-model="customerQuery.product" placeholder="请选择" style="width: 100%;" @click="loadDicValue('product')">
          <el-option
              v-for="item in productOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="客户描述" prop="description">
        <el-input
            v-model="customerQuery.description"
            :rows="8"
            type="textarea"
            placeholder="请输入客户描述"/>
      </el-form-item>
      <el-form-item label="下次跟踪时间" prop="nextContactTime">
        <el-date-picker
            v-model="customerQuery.nextContactTime"
            type="datetime"
            style="width: 100%;"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择下次跟踪时间"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="convertCustomerDialogVisible = false">关 闭</el-button>
        <el-button type="primary" @click="convertCustomerSubmit">转 换</el-button>
      </span>
    </template>
  </el-dialog>


</template>

<script>
import "../http/httpRequest.js";
import axios from "axios";
import {messageConfirm, messageTip} from "../util/util.js";
import {Delete, Edit} from "@element-plus/icons-vue";
export default {
  name: "ClueDetailView",
  computed: {
    Delete() {
      return Delete
    },
    Edit() {
      return Edit
    }
  },

  inject:['reload'],

  data() {
    return{
      //线索详情对象
      clueDetail:{
        ownerDO : {},
        activityDO : {},
        appellationDO : {},
        needLoanDO : {},
        intentionStateDO : {},
        intentionProductDO : {},
        stateDO : {},
        sourceDO  : {},
        editDO : {},
        createDO : {},
      },
      //线索跟踪记录
      clueRemark:{},
      //跟踪方式的下拉选项
      noteWayOptions:[{}],
      //线索跟踪列表
      clueRemarkList: [{
        createByDO : {},
        editByDO : {},
        noteWayDO : {}
      }],
      //分页
      pageSize : 0,
      total: 0,
      currentPage: 0,
      //跟踪信息编辑用
      editClueRemarkQuery : {},
      //弹窗
      clueRemarkDialogVisible: false,
      //规则
      clueRemarkRules: {},

      //转客户弹窗
      convertCustomerDialogVisible: false,
      //线索转换为客户的form表单对象，初始值是空
      customerQuery : {},
      //定义线索转换为客户的验证规则
      convertCustomerRules : {
        product : [
          { required: true, message: '请选择意向产品', trigger: ['blur', 'change'] }
        ],
        description : [
          { required: true, message: '客户描述不能为空', trigger: 'blur' },
          { min: 5, max: 255, message: '客户描述长度为5-255个字符', trigger: 'blur' }
        ],
        nextContactTime : [
          { required: true, message: '请选择下次联系时间', trigger: 'blur' }
        ]
      },
      //意向产品的下拉选项，初始值是空
      productOptions : [{}]
    }
  },

  mounted() {
    this.loadClueDetail();
    this.loadClueRemarkList();
    this.loadDicValue('noteWay')
  },

  methods: {

    //加载字典数据
    loadDicValue(typeCode) {
      axios.get("/api/dicValue/" + typeCode).then( resp => {
        //console.log(resp)
        if (resp.data.code === 200) {
          if (typeCode === 'noteWay') {
            this.noteWayOptions = resp.data.data;
          }else if(typeCode === 'product') {
            this.productOptions = resp.data.data;
          }
        }
      })
    },

    //转换客户
    convertCustomer() {
      this.convertCustomerDialogVisible = true;
    },

    //查询详情
    loadClueDetail(){
      let id = this.$route.params.id;
      axios.get("/api/clueDetail/" + id).then(response => {
        console.log(response.data.data);
        if (response.data.code === 200) {
          this.clueDetail = response.data.data;
        }
      })
    },

    //返回
    goBack(){
      this.$router.go(-1)
    },

    //提交跟踪记录
    clueRemarkSubmit() {
      axios.post("/api/clue/remark", {
        clueId: this.clueDetail.id,
        noteContent: this.clueRemark.noteContent,
        noteWay: this.clueRemark.noteWay
      }).then(response => {
        if (response.data.code === 200) {
          messageTip("提交成功", "success");
          this.reload();
        }else {
          messageTip("提交失败", "error")
        }
      })
    },

    //查询某个线索的全部线索备注clueRemarkList
    loadClueRemarkList(){
      axios.get("/api/clue/remark", {
        params: {
          current: this.currentPage, //假设 this.currentPage 是当前页码的变量（初始是0）
          clueId : this.$route.params.id,//查当前线索的id
        }
      }).then(response => {
        if (response.data.code === 200) {
          this.clueRemarkList = response.data.data.list;
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
      this.loadClueRemarkList()
    },

    edit(id){
      //显示弹窗
      this.clueRemarkDialogVisible = true;
      //先去把数据加载出来，这个对象有两个值
      axios.get("/api/clue/remark/" + id).then(response => {
        if (response.data.code === 200) {
          this.editClueRemarkQuery = response.data.data;
          //需要再去加载一下字典值，避免用户点击下拉菜单才更新选项，干脆直接页码打开就加载
        }
      })
    },

    del(id){
      messageConfirm("您确定要删除该备注吗？").then(res => {
        //用户点击确定，就执行then
        axios.delete("/api/clue/remark/" + id).then(response => {
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

    //提交更新好的备注信息
    editClueRemarkSubmit(){
      axios.put("/api/clue/remark", {
        id: this.editClueRemarkQuery.id,
        noteContent: this.editClueRemarkQuery.noteContent,
        noteWay: this.editClueRemarkQuery.noteWay
      }).then(response => {
        if (response.data.code === 200) {
          messageTip("编辑成功", "success");
          this.reload();
        }else {
          messageTip("编辑失败", "error")
        }
      })
    },

    //线索转换客户
    convertCustomerSubmit() {
      this.$refs.convertCustomerRefForm.validate( (isValid) => {
        if (isValid) {
          axios.post("/api/clue/customer", {
            clueId : this.clueDetail.id,
            product : this.customerQuery.product,
            description : this.customerQuery.description,
            nextContactTime : this.customerQuery.nextContactTime
          }).then(resp => {
            if (resp.data.code === 200) {
              messageTip("转换成功", "success");
              //刷新
              this.reload();
            } else {
              messageTip("转换失败", "error");
            }
          })
        }
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