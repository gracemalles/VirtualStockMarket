<template>
<div id="leader">
  <div class="title">
    <h3>Leaderboard</h3>
    <div class="toggle">
              <input type="radio" id="rankings" value="rankings" v-model="leaderView">
              <label for="rankings">Rankings</label>
              <input type="radio" id="history" value="history" v-model="leaderView">
              <label for="history">History</label>
    </div>
  </div>
  <leader-board v-bind:gameId="gameId" v-if="leaderView=='rankings'" class="rankings"/>
  <div v-else>
    <leader-chart class="leaderChart" v-bind:gameId="gameId"/>
  </div> 
  </div>
</template>

<script>
import LeaderBoard from '../components/LeaderBoard.vue'
import LeaderChart from '../components/LeaderChart.vue'
export default {
  components: { LeaderBoard, 
  LeaderChart
  },
  props: ["gameId"],
  data() {
    return {
      leaderView: 'rankings',
    }
  }

}
</script>

<style scoped>
  #leader {
    display: flex;
    flex-direction: column;
    /* margin: 0; */
  }

  .title {
    margin: 1rem;
  }

  .title h3 {
    margin: 1rem 0 0 0;
  }

   input[type=radio] {
        opacity: 0;
        position: fixed;
        width: 0;
    }

    .toggle {
        display: flex;
        justify-content: space-evenly;
        padding-top: 1rem;
    }

    .toggle label {
        display: inline-block;
        padding: 0.1rem 0.5rem;
        width: 6rem;
        text-align: center;
        font-family: 'Poppins', sans-serif;
        font-size: 0.8rem;
        border: 2px solid var(--background-color);
        border-radius: 8rem;
        cursor: pointer;
        transition: 0.4s;
    }

    .toggle label:hover {
        background-color: var(--background-color);
        color: var(--color-lighter);
    }

    .toggle input[type="radio"]:checked + label {
        background-color: var(--background-color);
        color: var(--color-lighter);
        font-weight: bold;
    }

    .rankings {
      margin: 1.5rem;
    }
</style>