<template>
  <div class="chat-container">
    <div class="chat-window">
      <div class="chat-history">
        <div
            v-for="(message, index) in messages"
            :key="index"
            class="message"
            :class="{'message-self': message.from === 'self', 'message-other': message.from === 'other'}"
        >
          <div class="message-content">
            {{ message.content }}
          </div>
        </div>
      </div>
      <div class="input-area">
        <el-input
            v-model="inputMessage"
            placeholder="请输入消息..."
            @keyup.enter.native="sendMessage"
        ></el-input>
        <el-button type="primary" @click="sendMessage">发送</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import {messageTip} from "../util/util.js";

export default {
  name: "ChatDemo",
  setup() {
    const messages = ref([]);
    const inputMessage = ref('');

    const sendMessage = async () => {
      if (!inputMessage.value) return;

      try {
        // 发送 POST 请求到 /api/chat，并带上消息内容
        const response = await axios.post('/api/chat', {
          message: inputMessage.value, // 根据你的后端接口要求调整请求体结构
        });

        //假设服务器返回了聊天响应消息
        const serverResponse = response.data; // 根据你的后端返回的数据结构来访问

        //添加用户发送的消息到聊天记录
        messages.value.push({
          from: 'self',
          content: inputMessage.value,
        });

        //添加服务器的响应消息到聊天记录（如果有的话）
        if (serverResponse) {
          console.log(serverResponse);
          messages.value.push({
            from: 'other',
            content: serverResponse,
          });
        }

        //清空输入框
        inputMessage.value = '';
      } catch (error) {
        console.error('Error sending message:', error);
        // 在这里处理错误，比如显示错误消息给用户
        messageTip("发送消息失败", "error")
      }
    };

    return {
      messages,
      inputMessage,
      sendMessage,
    };
  },
};
</script>

<style scoped>
.chat-container {
  width: 100vw; /* 使用整个视口的宽度 */
  max-width: 600px; /* 保持最大宽度不变 */
  margin: 0 auto;
}


.chat-window {
  border: 1px solid #ccc; /* 添加边框 */
  border-radius: 10px;
  overflow: hidden;
}

.chat-history {
  height: 500px; /* 高度设为视口高度 */
  overflow-y: auto;
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

.message {
  clear: both;
  margin-bottom: 10px;
  position: relative;
  padding-left: 30px; /* 为左侧的消息添加内边距 */
}

.message-content {
  padding: 10px;
  border-radius: 10px;
  max-width: 80%;
  word-break: break-all;
}

.message-self {
  text-align: right;
  padding-left: 0; /* 右侧消息不需要内边距 */
  padding-right: 30px; /* 为右侧的消息添加内边距 */
}

.message-self .message-content {
  background-color: #b3e5fc;
  color: #000;
  float: right;
}

.message-other .message-content {
  background-color: #e0e0e0;
  color: #000;
  float: left;
}

.message-other::before {
  content: "";
  position: absolute;
  top: 10px; /* 调整箭头的位置 */
  left: 0;
  width: 0;
  height: 0;
  border-right-color: #e0e0e0;
  border-width: 10px 10px 10px 0;
  border-radius: 5px 0 0 5px;
  margin-top: 5px;
  margin-left: -20px;
}

.input-area {
  padding: 10px;
}

.el-button{
  margin-top: 10px;
  margin-bottom: 10px;
  float: right;
}
</style>