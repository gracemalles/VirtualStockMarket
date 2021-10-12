<template>

<div class = "container">
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <label for="password" class="sr-only">Password</label>
      <div class="flex">
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.container {
    display: flex;
    align-content: center;
    justify-content: center;
    height: 90vh;
  }

  #register {
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
    margin: 1rem;
    font-size: 1rem;
    border-bottom: 0;
  }
  
  a:hover {
    border-bottom: 0;
    text-decoration: underline;
  }

  .flex {
    display: flex;
  }

  #username, #password, #confirmPassword {
    border: none;
    background-color: var(--color-lighter);
    border-bottom: 2px solid var(--background-color);
    transition: 0.4s;
    color: var(--background-color);
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

  input {
    padding: 8px;
    margin: 0;
  }

  .form-register {
    display: flex;
    flex-grow: 0.4;
    flex-direction: column;
  }

  label {
    margin: 0;
    font-size: 1.2rem;
    margin-top: 8px;
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
