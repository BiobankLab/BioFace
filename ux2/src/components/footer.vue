<style scoped>

</style>

<template>
<!-- FOOTER SECTION -->
<footer>
  <div class="container py-5">
    <div class="row">
      <div class="col-sm-4">
        <h5>{{$t('footer.license.header')}}</h5>
        <p>{{$t('footer.license.details')}}</p>
      </div>

      <div class="col-sm-4">
        <h5>{{$t('footer.contact.header')}}</h5>
        <p>
          <strong>{{$t('footer.contact.address')}}:</strong> {{$t('footer.contact.content.address')}}<br />
          <strong>{{$t('footer.contact.mobile')}}:</strong> {{$t('footer.contact.content.mobile')}}<br />
          <strong>{{$t('footer.contact.email')}}:</strong> {{$t('footer.contact.content.email')}}<br />
        </p>
      </div>

      <div class="col-sm-4">
        <h5>{{$t('footer.socialMedia.header')}}</h5>
        <ul class="list-unstyled socialmedia">
          <li><a href="#"><icon name="facebook"></icon></a></li>
          <li><a href="#"><icon name="google-plus"></icon></a></li>
          <li><a href="#"><icon name="instagram"></icon></a></li>
          <li><a href="#"><icon name="twitter"></icon></a></li>
        </ul>
      </div>
    </div>
  </div>
  <!-- COPYRIGHT -->

  <div class="container">
    <hr>
    <small class="d-block mb-3 ">&copy; {{$t('footer.rightsReserved')}}</small>
  </div>
  <!-- COPYRIGHT END-->
</footer>
</template>

<script>
export default {
  name: 'uxFooter',
  data() {
    return {
      msg: ''
    }
  }
}
</script>
