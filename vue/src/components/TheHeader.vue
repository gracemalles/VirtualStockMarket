<template>
  <div id="header">
      <router-link v-bind:to="{name: 'home'}" class="title">
          <h1>Gold Digger</h1>
        </router-link>
      <div class="links">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-if="$store.state.user.username" id="logoutLink" to="/logout">Log Out</router-link>
        <router-link v-else id="loginLink" to="/login">Log In</router-link>
      </div>
  </div>
</template>

<script>
export default {
    created() {
    }
}
</script>

<style scoped>
    * {
        background-color: var(--color-lighter);
        color: var(--background-color);
    }

    #header {
        max-height: 6vh;
        height: 6vh;
        width: 100vw;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 1.4rem var(--padding);
    }

    h1 {
        font-size: 1.4rem;
        font-weight: 100;
        text-transform: uppercase;
        flex-grow: 1;
    }

    a {
        text-decoration: none;
    }

    .links {
        display: flex;
        justify-content: space-around;
        flex-grow: 0.5;
    }

    .title:hover {
        border-bottom: 2px solid transparent;
    }
</style>