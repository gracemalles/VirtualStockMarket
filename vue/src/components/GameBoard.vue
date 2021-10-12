<template>
<div>
  <div id="main" v-if="isLoading == false">
    <div>
    <div id = "game" v-if="gameFinished == false">
      <div class="flex header"> 
        <h1 class = "boardTitle">{{ game.name }} Dashboard</h1>
          <router-link class="button" tag="button" v-bind:to="{name: 'add-players', params: {id: gameId}}">
            <span class="icon">+</span>
          <span class="text">Invite Players</span>
        </router-link>
      </div>
        <div class="flex">
            <portfolio-holdings v-bind:gameId="gameId" class="portfolio container"></portfolio-holdings>
            <trade-stocks class="trade container"></trade-stocks>
          <div>
            <countdown-timer v-bind:gameId="gameId" class="container"></countdown-timer>
            <leader-board v-bind:gameId="gameId" class="container"></leader-board>
          </div>
        </div>
        <router-link class="back" v-bind:to="{ name: 'home' }">Back to all games</router-link>
    </div>
    <div v-if="gameFinished">
      <game-over v-bind:gameId="gameId"/>
    </div>
    
    </div>
  </div>
  <div v-else>
    <loading-page class="loading-page"/>
  </div>
</div>
</template>

<script>
import LeaderBoard from '../views/Leader'
import PortfolioHoldings from './PortfolioHoldings.vue'
import TradeStocks from './TradeStocks.vue'
import CountdownTimer from '../components/CountdownTimer'
import GameService from '../services/GamesService'
import GameOver from './GameOver.vue'
import PlayerService from '../services/PlayerService'
import LoadingPage from './LoadingPage.vue'

export default {
  components: { LeaderBoard, PortfolioHoldings, TradeStocks, CountdownTimer, GameOver, LoadingPage},
  data() {
    return {
      gameId: this.$route.params.id,
      game: {
        id: "",
        name: "",
        organizer_id:"",
        end_date: "",
        end_time: ""
      },
      gameFinished: false,
      isLoading: true
    }
  },
  created() {
    GameService.getGameById(this.gameId)
    .then(result => {
      if(result.data != ""){
        this.game.id = result.data.id;
        this.game.name = result.data.name;
        this.game.organizer_id = result.data.organizer_id;
        this.game.end_date = result.data.end_date;
        this.game.end_time = result.data.end_time;
      }
    }).then(() => {
      PlayerService.getPlayerByGame(this.game.id).then(result => {
        if (result.data.game_status == 'Finished') {
          this.gameFinished = true;
        }
      })
    }).then(() => {
      if (this.gameFinished == false) {
      const timer = setInterval(() => {
        const now = new Date();
        const end = new Date(this.game.end_date + 'T' + this.game.end_time + '.000Z'); 
        const distance = end.getTime() - now.getTime();
        if (distance < 0) {
          clearInterval(timer);
          this.gameFinished = true;
          return;
        }
      }, 100);
      }
    })   
  },
  mounted() {
    setTimeout(() => {
    this.isLoading = false;
  }, 1200)}

}
</script>

<style scoped>
  * {
    color: var(--background-color);
  }

  #game {
    margin: 2rem var(--padding);
  }

  .flex {
    display: flex;
    flex-direction: row;
  }

  .portfolio, .trade {
    flex-grow: 1;
  }

  h3{
    padding: 0.5rem;
  }

  .boardTitle {
    margin-bottom: 1rem;
    text-align: center;
    color: var(--color-primary);
  }

  .container {
    border: none;
    background-color: var(--color-lighter);
    margin: 0.3rem;
    border-radius: var(--border-radius);
    color: var(--background-color);
  }

  a {
    color: var(--color-lighter);
  }

  .header {
    justify-content: space-between;
    padding: 1rem;
  }

   button {
    display: block;
    background-color:var(--color-green);
    border: none;
    border-radius: var(--border-radius);
    height: 3rem;
    width: 17rem;
    padding: 1.4rem 2.2rem;
    cursor: pointer;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    overflow: visible;
  }

  span {
    height: auto;
    /* width: 10rem; */
  }

  .icon, .text {
    color: var(--background-color);
    position: relative;
    left: 4.5rem;
    transition: 0.2s;
  }

  .icon {
    font-size: 2.2rem;
    /* flex-grow: 1; */
    text-align: center;
  }

  .text {
    font-size: 1.4rem;
    opacity: 0;
    min-width: 8rem;
  }

  button:hover .text, button:hover .icon {
    transform: translateX(0rem);
    opacity: 1;
    left: 0;
    /* width: 100%; */
  }

  button:hover .icon {
    font-size: 2rem;
  }

  .back {
    margin: 1rem;
  }

  .loading-page {
    margin: 0;
    padding: 0;
  }
</style>