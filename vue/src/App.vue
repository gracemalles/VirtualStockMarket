<template>

  <div id="app">
    <head>
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    </head>
      <the-header id="header"/>
      <!-- <loading-page></loading-page> -->
    <router-view />
  </div>
</template>


<script>

import TheHeader from './components/TheHeader.vue'
// import LoadingPage from './components/LoadingPage.vue'
export default {
  components: { TheHeader },
  
}
</script>

<style>
  :root {
    --color-red: #ff4f79;
    --color-green: #9bc53d;
    --background-color: #eeeeee;
    --color-primary: #211a1e;
    --color-lighter: #454545;
    --padding: 4rem;
    --border-radius: 5px;
  }

  *,
  *::before,
  *::after {
    box-sizing: border-box;
    padding: 0;
    margin: 0;
  }

  * {
    font-family: 'Poppins', 'Helvetica', sans-serif; 
  }

  body {
    font-size: 62.5%;
    background-color: var(--background-color);
  }

  h1 {
    font-size: 2rem;
  }

  h2 {
    font-size: 1.8rem;
  }

  h3 {
    font-size: 1.4rem;
    text-align: center;
  }

  h4 {
    font-size: 1rem;
  }
  
  p, a, td, tr, label, button {
    font-size: 1rem;
  }

  a {
    transition: 0.2s;
    border-bottom: 2px solid transparent;
    text-decoration: none;
    border-bottom: 2px solid var(--color-lighter);
    color: var(--color-primary);
  }

  a:hover {
    border-bottom: 2px solid var(--color-green);
  }

  ::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

  #header {
    z-index: 800;
  }
/* Track */
::-webkit-scrollbar-track {
  background: var(--color-lighter);
  border-radius: var(--border-radius);
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: #888;
  border-radius: var(--border-radius);
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #555;
}


</style>
