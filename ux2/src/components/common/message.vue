<template>
<div style="z-index: 2000 !important" v-if="showMessage" class="content" v-on:click="close">
  <div class="message" :class="messageType">
    <div class="messageHeader">
      <p v-if="messageType=='ok'">{{$t('ok')}}</p>
      <p v-if="messageType=='error'">{{$t('error')}}</p>
      <p v-if="messageType=='warning'">{{$t('warning')}}</p>
      <p v-if="messageType=='info'">{{$t('info')}}</p>
    </div>
    <div class="messageBody">
      <p>{{messageContent}}</p>
    </div>
  </div>
</div>
</template>

<script>

export default {
  name: 'WaitingImageMsg',
  data: function() {
    return {
      messageClass: ''
    }
  },
  methods: {
    close() {
      this.$store.state.showMessage = false
    }
  },
  computed: {
    showMessage: function() {
      return this.$store.state.showMessage
    },
    messageContent: function() {
      return this.$store.state.messageContent
    },
    messageType: function() {
      return this.$store.state.messageType
    }
  }
}
</script>

<style scoped>
.content {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  overflow-y: auto;
  overflow-x: auto;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 999;
  background: rgba(0, 0, 0, 0.5);
}

.message {
  border-width: thin;
  border-style: solid;
  border-color: #c8c8c8;
  border-radius: 3px;
  display: inline-block;
  width: 35%;
  text-align: center;
}

.messageBody>p {
  margin: 0;
  padding: 20px 0;
  padding-bottom: 40px;
}

.messageHeader {
  height: 35px;
  padding-top: 7px;
  padding-bottom: 35px;
}

.messageHeader>p {
  font-weight: 900;
  color: white;
}

.ok>.messageHeader {
  background-color: #5cb85c;
}

.info>.messageHeader {
  background-color: #439bf9;
}

.error>.messageHeader {
  background-color: #d9534f;
}

.warning>.messageHeader {
  background-color: #ffc107;
}

.messageBody {
  background-color: #fff;
  height: 100%;
  min-height: 60px;
}


</style>
