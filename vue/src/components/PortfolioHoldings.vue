<template>
  <div class="holdings">
    <div>
      <h3 class= "holdingsTitle">Your Position</h3>
      <div class="portfolioValue">
        <h4>Total Portfolio Value: </h4>
        <p> ${{ decimalPortfolioValue }} </p>
      </div>
      <div class="buyingPower">
        <h4>Buying Power: </h4>
        <p> ${{ availableFunds}} </p>
      </div>
      <div class="toggle">
          <input type="radio" id="portfolio-holdings" value="portfolio-holdings" v-model="toggle">
          <label for="portfolio-holdings">Stocks</label>
          <input type="radio" id="trade-history" value="trade-history" v-model="toggle">
          <label for="trade-history">History</label>
      </div>
      <div v-if="toggle=='trade-history'" class="flex">
        <div class="tradeHistory">
          <trade-history></trade-history>
        </div>
      </div>
      <div v-else>
        <stock-list v-bind:stocks="stocks"></stock-list>
      </div>
 
      </div>
      </div>
</template>
<script>
import playerService from "../services/PlayerService";
import stockService from "../services/StockService";
import StockList from "../components/StockList";
import TradeHistory from './TradeHistory.vue';
export default {
  components: {
    StockList,
    TradeHistory,
  },
  props: ['gameId'],
  data() {
    return {
      player: {},
      stocks: [],
      portfolioValue: 0,
      toggle: 'portfolio-holdings'
    }
  },
  computed: {
    availableFunds() {
      return Number(this.player.availableFunds).toFixed(2);
    },
    decimalPortfolioValue() {
      return Number(this.portfolioValue).toFixed(2);
    }
  },
  created() {
    playerService.getPlayerByGame(this.gameId) 
      .then(response => {
        this.player = response.data;
        stockService.getPlayerStocks(this.player.id)
          .then(response => {
            this.stocks = response.data.filter(stock => {
              return stock.total_shares > 0;
            })
          }).then(() => {
            this.getPortfolioValue();
          });
      })
  },
  methods: {
    getPortfolioValue() {
      this.portfolioValue = this.player.availableFunds;
      for(let i = 0; i <  this.stocks.length; i++) {
        let shares = this.stocks[i].total_shares;
        let stock_ticker = this.stocks[i].stock_ticker;
        stockService.getStockInfo(stock_ticker)
          .then(response => {
            let currentValue = response.data.currentPrice;
            this.portfolioValue = this.portfolioValue + (currentValue * shares);
          })
      }
    }
  }
}
</script>
<style scoped >
  .holdingsTitle{
    margin: 1rem;
  }
  .holdings{
    border-style: solid;
    padding: 1rem;
    width: 30%;
  }
  .portfolioValue, .buyingPower {
    margin: 0.4rem 1rem;
  }

  input[type=radio] {
    opacity: 0;
    position: fixed;
    width: 0;
  }

  .toggle {
    display: flex;
    justify-content: space-evenly;
    margin: 1rem;
    margin-bottom: 2rem;
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


</style>

