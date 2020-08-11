<template>
  <div id="login">
    <section style="margin-top: 100px" class="content-app">
      <b-row>
        <b-col class="col-md-4"> </b-col>
        <b-col class="col-md-4">
          <b-card
            :header="$t('loginPage.login')"
            header-bg-variant="primary"
            header-text-variant="white"
          >
            <b-form-group
              class="label"
              horizontal
              label-
              :label-cols="3"
              label-size="md"
              label-for="username"
              :label="$t('loginPage.username')"
            >
              <b-form-input
                v-on:keyup.enter="login"
                type="text"
                id="username"
                v-model="input.username"
                :placeholder="$t('loginPage.username')"
                required
              />
            </b-form-group>
            <b-form-group
              class="label"
              horizontal
              :label-cols="3"
              label-size="md"
              label-for="password"
              :label="$t('loginPage.password')"
            >
              <b-form-input
                v-on:keyup.enter="login"
                type="password"
                id="password"
                v-model="input.password"
                :placeholder="$t('loginPage.password')"
                required
              />
            </b-form-group>
            <b-row style="padding-right:25px; justify-content: flex-end">
              <b-btn
                type="submit"
                style="margin-left:10px;"
                @click="login"
                variant="success"
                >{{ $t("loginPage.login") }}</b-btn
              >
            </b-row>
          </b-card>
        </b-col>
      </b-row>
    </section>
  </div>
</template>

<script>
import LoginService from "@/service/LoginService.js";

export default {
  name: "LoginPage",
  data() {
    return {
      input: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    login() {
      if (this.input.username !== "" && this.input.password !== "") {
        LoginService.login(this.input).then(response => {
          if (response.data === "") {
            this.snackbarText = this.$t("loginPage.wrongUsernameOrPassword");
            this.$root.$emit("open-snackbar", this.snackbarText);
          } else {
            localStorage.setItem('token', response.data);
            this.$store.state.logged = true;
            this.$router.push("/biobanksPermissions");
          }
        });
      } else {
        this.snackbarText = this.$t("loginPage.empytUsernameOrPassword");
        this.$root.$emit("open-snackbar", this.snackbarText);
      }
    }
  }
};
</script>

<style scoped>
.label {
  align-items: center !important;
  text-align: center !important;
}
</style>
