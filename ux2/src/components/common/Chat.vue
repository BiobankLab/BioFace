<style scoped>
.modal-content {
  border: 0;
}

</style>

<template>
<b-modal class="contentmain" ref="chatModal" hide-header hide-footer>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <!-- row -->
        <div class="row">
          <!-- contentLeft -->
          <div class="col-4 col-sm-3">
            <div class="contentLeft">
              <b-link v-for="channel in messageChannels" @click="onSelectChannel(channel)" :key="channel.id">
                <button class="btn channelListElement" style="border: 0.5px solid black;" variant="info" v-bind:class="{ 'active-czat': channel.active }">
                    {{channel.biobank ? channel.biobank : $t('projects.chat.channel.all')}}
                    <b-badge variant="light">
                      {{channel.unreadedMessageCount}}
                    </b-badge>
                </button>
              </b-link>
            </div>
          </div>

          <!-- content RIGHT -->
          <div class="col-8 col-sm-9" style="padding-left:0">
            <!-- header -->
            <div class="contenttop">
              <div class="headerdivcell">
                <p class="headertext bolded">{{getMessageChannelTitle()}}</p>
              </div>
            </div>

            <!-- contentchat -->
            <div class="contentchat" :style="chatStyleHeight" ref="contentchat" @scroll="handleChatScroll">
              <div v-if="selectedMessageChannel !== null">
                <div v-for="(message, index) in messageList">
                  <div v-if="isDateChange(index)" style="text-align: center;" :key="index">
                    <p style="color: black; font-size: small">{{ formatDate(message.date) }}</p>
                  </div>
                  <div class="chattext" :style="isMine(message) ? 'width: 100%; text-align:right;' : 'width: 100%; text-align: left;'" :key="'#' + index">
                    <chat-bubble :info="message.author + '  ' + formatTime(message.date)" :text="message.content" :right="isMine(message)" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- end row -->
        <!-- row -->
        <div class="row">
          <div class="offset-lg-3 col-lg-9">
            <div class="contentbottom">
              <div class="row">
                <div class="col-md-9">
                  <textarea class="form-control" style="resize: none;width:100%" v-model="messageText" v-on:keydown="handleCmdEnter" :placeholder="this.$t('projects.chat.placeholders.message')" rows="2"></textarea>
                </div>
                <div class="col-md-3">
                  <b-button style="background-color: #24d5d8;" class="btn-mini-czat" v-on:click="send()">{{$t('projects.chat.button.send')}}</b-button>
                  <b-button class="btn-warning btn-mini-czat" data-dismiss="modal" aria-label="Close" v-on:click="close()">{{$t('projects.chat.button.close')}}</b-button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- end row  -->
      </div>
    </div>
  </div>
</b-modal>
</template>

<script>
import ChatBubble from './ChatBubble'
import axios from 'axios'
import moment from 'moment'
import '@/libs/czat.css'
export default {
  name: 'Chat',
  data: function() {
    return {
      timer: '',
      interval: 5000,
      chatStyleHeight: 'height: ' + (window.innerHeight * 0.8 - 150) + 'px',
      messageText: '',
      messageList: [],
      selectedMessageChannel: null,
      messages: [],
      oneMessageScrollHeight: 0,
      getMessagesRequest: {
        messageChannelId: null,
        lastestMessageDate: null,
        earliestMessageDate: null
      }
    }
  },
  created() {
    window.addEventListener('resize', this.resize)
  },
  methods: {
    open() {
      this.$refs.chatModal.show()
    },
    getMessageChannelTitle() {
      let title = this.$t('project') + ': ' + this.project.name
      if (this.selectedMessageChannel) {
        title += '  ' + this.$t('messageChannel') + ': '
        title += this.selectedMessageChannel.biobank ? this.selectedMessageChannel.biobank : this.$t('projects.chat.channel.all')
      }
      return title
    },
    onSelectChannel(channel) {
      if (!channel.active) {
        this.messageList = []
        this.getMessagesRequest.lastestMessageDate = null
        this.getMessagesRequest.earliestMessageDate = null
        this.messageChannels.forEach(function(ch) {
          ch.active = false
        })
        channel.active = true
        channel.unreadedMessageCount = 0
        this.selectedMessageChannel = channel
        this.readPreviousMessages(true)
      }
    },
    handleChatScroll: function() {
      let contentchat = this.$refs.contentchat
      if (contentchat.scrollTop === 0 && this.messageList.length >= 20) {
        this.readPreviousMessages(false)
      }
    },
    formatDate(date) {
      return moment(date).format('YYYY-MM-DD')
    },
    formatTime(messageTime) {
      return moment(messageTime).format('HH:mm')
    },
    resize() {
      this.chatStyleHeight = 'height: ' + (window.innerHeight * 0.8 - 150) + 'px'
    },
    close() {
      this.$emit('on-close-chat')
      if (this.timer !== '') {
        clearInterval(this.timer)
        this.timer = ''
      }
      window.removeEventListener('resize', this.resize)
      this.messageList = []
      this.messageText = ''
    },
    isMine(message) {
      if (message.author === localStorage.username) {
        return true
      }
      return false
    },
    isDateChange(index) {
      if (index === 0) {
        return true
      } else {
        let thisMessageDate = moment(this.messageList[index].date).format('yyyy-MM-dd')
        let previousMessageDate = moment(this.messageList[index - 1].date).format('yyyy-MM-dd')
        return thisMessageDate !== previousMessageDate
      }
    },
    send() {
      if (this.selectedMessageChannel === null || !this.messageText || this.messageText.replace(/\s/g, '').length === 0) {
        return
      }
      let self = this
      axios.post('chat/message', {
        messageChannelId: this.selectedMessageChannel.id,
        content: this.messageText
      }).then(function(response) {
        self.readNextMessages()
        self.messageText = ''
      }).catch(function(error) {
        console.log('error', error)
      })
    },
    handleCmdEnter(e) {
      if ((e.metaKey || e.ctrlKey) && e.keyCode === 13) {
        this.send()
      }
    },
    readPreviousMessages(scrollDown) {
      this.getMessagesRequest.messageChannelId = this.selectedMessageChannel.id
      if (this.messageList.length > 0) {
        console.log('new')
        this.getMessagesRequest.lastestMessageDate = null
        this.getMessagesRequest.earliestMessageDate = this.messageList[0].date
      }
      let self = this
      axios.post('chat/message/read', this.getMessagesRequest).then(function(response) {
        if (response.data) {
          response.data.forEach(function(message) {
            self.messageList.unshift(message)
          })
        }

        if (scrollDown) {
          self.$nextTick(() => {
            let contentchat = self.$refs.contentchat
            contentchat.scrollTop = contentchat.scrollHeight
            if (self.messageList.length > 0) {
              self.oneMessageScrollHeight = contentchat.scrollHeight / self.messageList.length
            }
          })
        } else {
          self.$nextTick(() => {
            let contentchat = self.$refs.contentchat
            contentchat.scrollTop = self.oneMessageScrollHeight * response.data.length
          })
        }
      }).catch(function(error) {
        console.log('error', error)
      })

      if (self.timer === '') {
        self.timer = setInterval(self.readNextMessages, 5000)
      }
    },
    readNextMessages() {
      this.getMessagesRequest.messageChannelId = this.selectedMessageChannel.id
      if (this.messageList.length > 0) {
        this.getMessagesRequest.lastestMessageDate = this.messageList[this.messageList.length - 1].date
        this.getMessagesRequest.earliestMessageDate = null
      }
      let self = this
      axios.post('chat/message/read', this.getMessagesRequest).then(function(response) {
        if (response.data) {
          response.data.forEach(function(message) {
            self.messageList.push(message)
          })
        }
        if (response.data.length > 0) {
          self.$nextTick(() => {
            let contentchat = self.$refs.contentchat
            contentchat.scrollTop = contentchat.scrollHeight
          })
        }
      }).catch(function(error) {
        console.log('error', error)
      })

      if (self.timer === '') {
        self.timer = setInterval(self.readNextMessages, 5000)
      }
    }
  },
  components: {
    'chat-bubble': ChatBubble
  },
  props: ['messageChannel', 'messageChannels', 'project']
}
</script>
