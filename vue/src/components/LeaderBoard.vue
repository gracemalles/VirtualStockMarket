<template>
<div class = "leader-board">

  <div class="container">
    <table>
      <tr v-for="player in leaderBoardInfo" v-bind:key="player.id">
        <td class="rank">{{player.rank}}</td>
        <td>{{player.username}}</td>
        <td>${{player.portfolioValue}}</td>
      </tr>
    </table>
    <!-- <p>{{player.rank}} - {{player.username}} : ${{player.portfolioValue}}</p> -->

  </div>
</div>
</template>

<script>
import playerService from '../services/PlayerService.js'
//import userService from '../services/UserService.js'
import stockService from '../services/StockService.js'

export default {
    name: 'leader-board',
    components: {
     // LeaderChart,
    },
    data(){
      return {
        playerList: [],
        portfolioValue: [],
        sorted : [],
        stocks : [],
        counter: 0,
        activePlayer: {}
      }
    },
    created(){
      // first thing that happens when component loads 
      // taking data returned from getAllPlayers() and saving into our playerList
    
      playerService.getPlayersByGame(this.gameId).then(
          (response) => {
            this.playerList = response.data;
            this.playerlist = this.playerList.sort((a,b) => {
              return b.availableFunds - a.availableFunds
            })
        }).then(() => {
          for(let i = 0; i < this.playerList.length; i++) {
            stockService.getPlayerStocks(this.playerList[i].id)
            .then(response => {
              let playerStocks = response.data.filter(stock => {
                return stock.total_shares > 0;
              }); 
              for (let j = 0; j < playerStocks.length; j++) {
                this.stocks.push(playerStocks[j]);
              }
            }).then(() => {
              this.getPortfolioValue(i);
            });
          }
        })
      .catch(
        (error) => {console.log(error)
        });
    },
    props:  ["gameId"],

    computed: {
      leaderBoardInfo() {
        let leaderBoard = [];


        for (let i = 0; i < this.playerList.length; i++) {
          for (let j = 0; j < this.portfolioValue.length; j++) {
            if (this.portfolioValue[j].id == this.playerList[i].id && this.playerList[i].game_status != "Rejected") {
              leaderBoard.push({
                username: this.playerList[i].username,
                portfolioValue: this.portfolioValue[j].value.toFixed(2),
              });
            }
          }
        }
        leaderBoard.sort((a, b) => {
          return b.portfolioValue - a.portfolioValue;
        })
        let counter = 1;
        for (let i = 0; i < leaderBoard.length; i++) {
            leaderBoard[i].rank = counter;
            counter++;
        }
        return leaderBoard;
      }
  },
  methods: {
    getPortfolioValue(index) {let timer = setInterval(() => {
      for (let i = 0; i < this.portfolioValue.length; i++) {
        if (this.portfolioValue[i].id == this.playerList[index].id) return;
      }
      this.portfolioValue.push({
        value: this.playerList[index].availableFunds,
        id: this.playerList[index].id});
      if(this.portfolioValue[index]) clearInterval(timer);
      for(let i = 0; i <  this.stocks.length; i++) {
        if (this.playerList[index].id == this.stocks[i].player_id) {
          let shares = this.stocks[i].total_shares;
          let stock_ticker = this.stocks[i].stock_ticker;
          stockService.getStockInfo(stock_ticker)
          .then(response => {
            let currentValue = response.data.currentPrice;
            for (let j = 0; j < this.portfolioValue.length; j++) {
              if (this.portfolioValue[j].id == this.playerList[index].id){
                this.portfolioValue[j].value += (currentValue * shares);
              }
            }
          });
        }
      }
    }, 300)}
  }
}

</script>

<style>
  .leader-board{
    padding-bottom: 1rem;
  }

  tr {
    width: 100%;
  }

  table {
    width: 100%;
    padding: 0 2rem;
  }

  td{
    width: 50%;
    text-align: left;
  }

  .rank {
    width: 25%;
  }
</style>