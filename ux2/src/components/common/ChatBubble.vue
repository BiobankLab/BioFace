<template>

  <b-row>
    <b-col>
      <div :class="right ? 'speech-bubble-right' : 'speech-bubble-left'">
      <p class="chattext" v-html="text"></p>
      </div>
    </b-col>
  </b-row>

</template>

<script>
import '@/libs/czat.css'
export default {
  name: 'ChatBubble',
  props: {
    info: {
      type: String
    },
    text: {
      type: String
    },
    right: {
      type: Boolean
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
