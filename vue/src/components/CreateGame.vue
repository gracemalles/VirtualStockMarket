<template>
  <div class="container">
    <div id="createGame">
      <h2>Create Game</h2>
      <form @submit.prevent="createGame">
        <div>
          <label>Name:</label>
          <input type="text" id="gameName" v-model="game.name" required/>
        </div>
        <div>
        <label>End Date:</label>
        <input type="date" min="currentDate" id="endDate" v-model="game.end_date" required/>
        </div>
        <div>
        <label>End Time (UTC):</label>
          <input type="time" name="endTime" id="endTime" v-model="game.end_time" required/> 
        </div>
        <div class="button-container">
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import gamesService from '../services/GamesService.js'
import playerService from '../services/PlayerService.js'
export default {
data() {
    return {
      game: {
        organizer_id: this.$store.state.user.id,
        name: "",
        end_date: '',
        end_time: ''
      },
      gameId: -1
    }
  },
// create date/ time object then convert and move back to seperate objs
  methods: {
    convertTimetoUTC(){
       /* let moment = (this.game.end_date + 'T' + this.game.end_time + '.000Z');
        let utc_offset = moment.getTimezoneOffset();
        moment.setMinutes(moment.getMinutes() + utc_offset)
        console.log(this.game)*/
      
    },

    createGame() {
      // call converision method
    // this.convertTimetoUTC()
      console.log(this.game.end_date);
      console.log(this.game.end_time);
      gamesService.createGame(this.game)
      .then(response => {
        this.gameId = response.data;
        console.log(this.$store.state.user);
        this.inviteUser(this.$store.state.user);
        this.$router.push({ name: 'add-players', params: { id: this.gameId } });

      })
    },
    inviteUser(user) {
      playerService.create({
        user_id: user.id,
        game_id: this.gameId,
        game_status: 'Accepted'
      })
      .then((response) => {
        if(response.status === 200) {
          user.invited = true;
        }
      })
    },

  }
  
}
   
   
</script>

<style scoped>
  .container {
    display: flex;
    align-content: center;
    justify-content: center;
  } 

  #createGame {
    background-color: var(--color-lighter);
    border-radius: var(--border-radius);
    margin: var(--padding) auto;
    padding: var(--padding);
    color: var(--background-color);
  }

  h2 {
    margin: 0.5rem;
    text-align: center;
  }

  label {
    box-sizing: border-box;
    width: 150px;
    display: inline-block;
    margin: 0.5rem;    
  }

  .button-container{
    display: flex;
  }

  button{
    font-size: 1.2rem;
    padding: 0.4rem 2rem;
    border: 2px solid var(--background-color);
    background-color: transparent;
    border-radius: 4rem;
    cursor: pointer;
    transition: 0.4s;
    width: auto; 
    margin: 2rem auto 0 auto;
    color: var(--background-color);
    width: 100%;
  }

  button:hover {
    border: 2px solid var(--color-green);
    background-color: var(--color-green);
  }

  input {
      border: none;
      background-color: transparent;
      border-bottom: 2px solid var(--background-color);
      transition: 0.4s;
      color: var(--background-color);
      font-size: 1rem;
      text-align: center;
      margin: 0.5rem;
  }

  input:focus, input:hover {
      outline: none;
      color: var(--background-color);
      font-size: 1rem;
  }

  ::-webkit-calendar-picker-indicator {
    background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="16" height="15" viewBox="0 0 24 24"><path fill="%23bbbbbb" d="M20 3h-1V1h-2v2H7V1H5v2H4c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 18H4V8h16v13z"/></svg>');
  }

</style>