<template>
  <div class="container">
      <h3>Time Remaining</h3>
    <div class="countdownContainer">
        <div class="time">
            <h4 id= "days">{{displayDays}}</h4>
            <p>Days</p>
        </div>
        <div class="time">
            <h4 id= "hours">{{displayHours}}</h4>
            <p>Hours</p>
        </div>
        <div class="time">
            <h4 id= "minutes">{{displayMinutes}}</h4>
            <p>Minutes</p>
        </div>
        <div class="time seconds">
            <h4 id= "seconds">{{displaySeconds}}</h4>
            <p>Seconds</p>
        </div>
    </div>
  </div>
</template>

<script>
import GameService from '../services/GamesService'
export default {
    props:  ["gameId"],
    data: () => ({
        displayDays: 0,
        displayHours: 0,
        displayMinutes: 0,
        displaySeconds: 0,
        game: {
        id: "",
        name: "",
        organizer_id:"",
        end_date: "",
        end_time: "",
        isFinished: false
      }
    }),
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
       this.showRemaining();
        })
        
        
   },
    computed: {
        // values in milisec
        _seconds: () => 1000,
        _minutes() {
            return this._seconds * 60;
        },
        _hours(){
            return this._minutes * 60;
        },
        days(){
            return this._hours * 24;
        }
    },
    mounted() {
       
       //this.showRemaining();
      
    },

    methods: {
        showRemaining(){
            const timer = setInterval(() => {
                const now = new Date();
                const end = new Date(this.game.end_date + 'T' + this.game.end_time + '.000Z'); 
                const distance = end.getTime() - now.getTime();
               
                // when time is up
                if(distance < 0){
                   //stop timer
                   clearInterval(timer);
                   return;

                }
                // get total seconds between the times
                var delta = Math.abs(end -now) / 1000;

                // calculate (and subtract) whole days
                var days = Math.floor(delta / 86400);
                delta -= days * 86400;

                // calculate (and subtract) whole hours
                var hours = Math.floor(delta / 3600) % 24;
                delta -= hours * 3600;

                // calculate (and subtract) whole minutes
                var minutes = Math.floor(delta / 60) % 60;
                delta -= minutes * 60;

                // what's left is seconds
                var seconds = Math.floor(delta % 60);  

                this.displayMinutes = minutes < 10 ? "0" + minutes : minutes;
                this.displaySeconds = seconds < 10 ? "0" + seconds : seconds;
                this. displayHours = hours <  10 ? "0" + hours : hours;
                this.displayDays = days <  10 ? "0" + days : days;
            }, 1000);
        }
    }
};
 
</script>

<style scoped>
/* .countdownContainer {
    text-align: center;
    background: #ddd;
    border: 1px, solid #999;
    padding: 10px;
    box-shadow: 0 0 5px 3px #ccc; 
} */

    .container {
        padding: 1rem;
    }

    .countdownContainer {
        padding: 0.5rem 0rem;
        display: flex;
        align-content: center;
        justify-content: space-evenly;
        margin-bottom: 1rem;
    }

    tr {
        height: auto;
    }

    .countdownContainer h4 {
        font-size: 1.4rem;
    }

    .time {
        flex-grow: 1;
        flex-basis: 1;
        flex-shrink: 0;
        width: 5rem;
        text-align: center;
    }

    h3 {
        margin: 1rem;
    }
</style>