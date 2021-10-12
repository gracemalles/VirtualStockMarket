<template>
  <div class="home">
    <p v-if="!$store.state.user.username">You must be authenticated to see this</p>
    <div v-else>
      <div class="block">
        <h1>Welcome back, {{ $store.state.user.username }}!</h1>
        <router-link class="button" id="createGame" tag="button" v-bind:to="{name: 'new-game'}">
            <span class="icon">+</span>
          <span class="text">New Game</span>
        </router-link>
      </div>
      
      <games-list />
    </div>
  </div>
</template>

<script>
import GamesList from '../components/GamesList.vue';
import gamesService from '../services/GamesService.js';
import playerService from '../services/PlayerService.js';

export default {
  components: {GamesList},
  name: "home",

    mounted() {
    gamesService.getAllGames().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        let game = response.data[i];
        game.isFinshed = false;
        let isInList = false;
        for (let j = 0; j < this.$store.state.games.length; j++) {    
          if (this.$store.state.games[j].id == game.id) isInList = true;
        }
        if (!isInList) {
          
          this.$store.commit("ADD_GAME", game);
          playerService.getPlayersByGame(game.id)
          .then(playerResponse => {
            for (let j = 0; j < playerResponse.data.length; j++) {
              let player = playerResponse.data[j];
              isInList = false;
              for (let k = 0; k < this.$store.state.players.length; k++) {
                if (this.$store.state.players[k].id == player.id) isInList = true; 
              }
              if (!isInList) {
                this.$store.commit("ADD_PLAYER", player);
              }
            }
          });   
        }
      }
    });
  }
}
</script>

<style scoped>
  button {
    display: block;
    background-color:var(--color-green);
    border: none;
    border-radius: var(--border-radius);
    height: 3rem;
    width: 15rem;
    padding: 1.4rem 2.2rem;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: visible;
  }

  span {
    height: auto;
  }

  .icon, .text {
    color: var(--background-color);
    position: relative;
    left: 4rem;
    transition: 0.2s;
  }

  .icon {
    font-size: 2.2rem;
    flex-grow: 1;
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
    width: 100%;
  }

  button:hover .icon {
    font-size: 2rem;
  }

  .block {
    padding: 2rem var(--padding);
    display: flex;
    justify-content: space-between;
  }

</style>
