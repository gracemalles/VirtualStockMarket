<template>
<div class="container">

  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <div>
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div>
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <button type="submit">Sign in</button>
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
</div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
  .container {
    display: flex;
    align-content: center;
    justify-content: center;
    height: 90vh;
  }

  #login {
    padding: var(--padding);
    margin: auto;
    border-radius: var(--border-radius);
    color: var(--background-color);
    background-color: var(--color-lighter);
    width: 50vw;
    text-align: center;
    display: flex;
    align-content: center;
    justify-content: center;
  }

  a {
    color: var(--background-color);
    border-bottom: 0;
    font-size: 1rem;
    margin: 1rem;
  }

  a:hover {
    border-bottom: 0;
    text-decoration: underline;
  }

  #username, #password {
    appearance: none !important;
    border: none;
    background-color: var(--color-lighter) !important;
    border-bottom: 2px solid var(--background-color) !important;
    transition: 0.4s;
    color: var(--background-color) !important;
    padding: 0.5rem 1rem;
    font-size: 1rem;
    margin: 0.5rem;
    width: 100%;
  }

  input:focus, input:hover {
      background-color: var(--color-lighter);
      outline: none;
      border-bottom: 2px solid var(--color-green);
      color: var(--background-color);
      font-size: 1rem;
  }

  .form-signin {
    display: flex;
    flex-direction: column;
  }

  label {
    margin: 0;
    padding: 2rem;
    font-size: 1.2rem;
    text-align: left;
  }

  button {
      font-size: 1.2rem;
      padding: 0.4rem 2rem;
      color: var(--background-color);
      border: 2px solid var(--background-color);
      background-color: transparent;
      border-radius: 4rem;
      cursor: pointer;
      transition: 0.4s;
      text-transform: uppercase;
      width: auto;
      margin-top: 1.4rem;
  }

  button:hover {
      border: 2px solid var(--color-green);
      background-color: var(--color-green);
  }
  
</style>
