<style>
</style>

<template>

<div>
    <router-view></router-view>
    <user-message/>
</div>

</template>

<script>
import message from '@/components/common/message'
export default {
    name: 'app',
    components: {
      'user-message': message
    }
}

</script>
