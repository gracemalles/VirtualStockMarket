<template>
<div class="big-container">

  <div class="container">
      <div class="info">
        <img :src="imageUrl">
        <div class="company">
            <h2>{{stockTicker}}</h2>
            <h3>{{ companyName }}</h3>
        </div>
      </div>
        <div class="flex">
            <h3>Your Position: </h3>  
            <p> {{ stock.total_shares }} Share{{ stock.total_shares != 1 ? "s" : ""}}</p>
            <p>Market Value: ${{marketValue}} </p>
        </div>
        
      <div class="chartContainer">
        <stock-data-graph v-bind:ticker="stockTicker"></stock-data-graph>
      </div>
      <div class="value">
            <div class="flex">
                <p>Currrent Price: ${{currentPrice}}</p>
                <p>Day Change: <span v-bind:class="{ 'red' : percentChange < 0, 'green' : percentChange >= 0 }"> {{percentChange}}% </span></p>
            </div>
        </div>
        <div class="largeButton"> 
            <button v-on:click="toggleTrade = !toggleTrade">{{ !toggleTrade ? "Trade" : "Cancel"}}</button>
        </div>
        <div v-if="toggleTrade" class="flex tradeToggle">
            <div class="tradeDetails">
                <div>
                    <p>Buying Power: ${{ buyingPower.toFixed(2) }}</p>
                </div>
                <div>
                    <label for="amount">Amount:</label>
                    <input type="number" name="amount" id="amount" v-model="amount">
                    <select name="entryType" id="entryType" v-model="entryType">
                        <option value="Shares">Shares</option>
                        <option value="Dollars">Dollars</option>
                    </select>
                </div>

                <div v-if="entryType === 'Shares'">
                    <p>Estimated Value: ${{ estimatedCost }}</p>
                </div>
                <div v-else>
                    <p>Number of Stocks: {{ estimatedNumberOfStocks }}</p>
                </div>
            </div>

            <div class="buttons">
                <button v-on:click="buyStocks()" v-bind:class="{ 'invalidTransaction' : !validBuyTransaction }">Buy</button>
                <button v-on:click="sellStocks()" v-bind:class="{ 'invalidTransaction' : !validSellTransaction }" v-if="stock.total_shares> 0">Sell</button>
            </div>
        </div>
        <div class="back">
            <router-link v-bind:to="{ name: 'game', params: {id: $store.state.activeGameId}}">Back to Game Board</router-link>
        </div>
  </div>
</div>
</template>

<script>
import stockService from '../services/StockService'
import tradeService from '../services/TradeService'
import StockDataGraph from '../components/StockDataGraph';

export default {
    components: {
        StockDataGraph
    },
    data(){
        return {
            stockTicker: this.$route.params.ticker,
            currentPrice: 0,
            imageUrl: "",
            companyName: "",
            percentChange: 0,
            stock: {
                id: "",
                player_id: "",
                stock_name: "",
                stock_ticker: "",
                total_shares: 0
            },
            toggleTrade: false,
            entryType: "Shares",
            amount: "",
        }
    },
    created(){
        stockService.getStockByPlayerAndTicker(this.$store.state.currentPlayerId, this.stockTicker)
            .then(result => {
                if(result.data != "") {
                    this.stock.id = result.data.id;
                    this.stock.player_id = result.data.player_id;
                    this.stock.stock_name = result.data.stock_name;
                    this.stock.stock_ticker = result.data.stock_ticker;
                    this.stock.total_shares = result.data.total_shares;
                }
            });

        stockService.getStockInfo(this.stockTicker)
            .then(
                (response) => {
                    this.currentPrice = response.data.currentPrice;
                    this.imageUrl = response.data.logoURL;
                    this.percentChange = response.data.percentageChange;
                    this.companyName = response.data.companyName;
                }
            ).catch(
                (error) => console.log(error)
            );
    },
    computed: {
        buyingPower() {
            return Number((this.getCurrentPlayer().availableFunds).toFixed(2));
        },
        marketValue() {
            return Number((this.stock.total_shares * this.currentPrice).toFixed(2));
        },
        estimatedCost() {
            return Number((this.amount * this.currentPrice).toFixed(2));
        },
        estimatedNumberOfStocks() {
            return Number(this.amount / this.currentPrice).toFixed(6);
        },
        validSellTransaction() {
            if(this.entryType=== "Shares" && this.stock.total_shares >= this.amount && this.amount > 0){
                      return true;
            }  if(this.entryType=== "Dollars" && this.marketValue >= this.amount && this.amount > 0){
                      return true;
            }
            return false; 
          
        },
        validBuyTransaction() {
            let player = this.getCurrentPlayer();
            if(this.entryType=== "Shares" && player.availableFunds >= (this.amount * this.currentPrice) && this.amount > 0){
                      return true;
            }  if(this.entryType=== "Dollars" && player.availableFunds >= this.amount && this.amount > 0){
                      return true;
            }
            return false; 
        }
    },
    methods: {
        getCurrentPlayer(){
           return this.$store.state.players.find(player => {
               return player.id === this.$store.state.currentPlayerId
            })
        },
        buyStocks() {
            if(this.validBuyTransaction) {
                if(this.stock.total_shares === 0) {
                    this.createStock().then(() => {
                        stockService.getStockByPlayerAndTicker(this.$store.state.currentPlayerId, this.stockTicker)
                            .then(result => {
                                if(result.data != "") {
                                    this.stock.id = result.data.id;
                                    this.stock.player_id = result.data.player_id;
                                    this.stock.stock_name = result.data.stock_name;
                                    this.stock.stock_ticker = result.data.stock_ticker;
                                    this.stock.total_shares = result.data.total_shares;
                                }

                                this.updateStock("Buy");
                                this.updatePlayer("Buy");
                                this.createTradeObject("Buy");
                                this.amount = "";
                            })
                    })
                    .catch(error => {
                            console.log(error);
                        });
                   
                } else {
                    this.updateStock("Buy");
                    this.updatePlayer("Buy");
                    this.createTradeObject("Buy");
                    this.amount = "";
                }
            }
        },
        sellStocks() {
            if(this.validSellTransaction) {
                if(this.stock.total_shares > 0) {
                    this.updateStock("Sell");
                    this.updatePlayer("Sell");
                    this.createTradeObject("Sell");
                    this.amount = "";
                }
            }
        },

        updateStock(buyOrSell) {
            let stock = this.stock;
            let currentShares = parseInt(stock.total_shares);
            let amount = parseInt(this.amount);

            let shareChange = this.entryType === "Shares" ? amount : amount / this.currentPrice;
            
            let newShares = buyOrSell == "Buy" ? currentShares + shareChange : currentShares - shareChange;
            stock.total_shares = newShares;
        },
        createTradeObject(buyOrSell) {
            let shares = this.entryType === "Shares" ? this.amount : this.amount / this.currentPrice;
            let price = this.entryType === "Dollars" ? this.amount : this.amount * this.currentPrice;
            let dateTime = new Date()
            let date = dateTime.toISOString().substring(0, 10);
            let time = dateTime.toString().substring(16, 24);

            let trade = {
                stock_id: this.stock.id,
                shares_traded: shares,
                buy_or_sell: buyOrSell,
                price: price,
                entered_in: this.entryType,
                date: date,
                time: time
            }
            tradeService.createTrade(trade);
        },
        updatePlayer(buyOrSell) {
            let player = this.getCurrentPlayer();
            let initialBalance = player.availableFunds;
            let amount = parseFloat(this.amount)
            let balanceChange = this.entryType === "Dollars" ? amount : amount * this.currentPrice;

            let newBalance = buyOrSell === "Buy" ? initialBalance - balanceChange : initialBalance + balanceChange;

            player.availableFunds = newBalance;
        },
        createStock() {
            let player_id = this.getCurrentPlayer().id;
            let stock_name = this.companyName;
            let stock_ticker = this.stockTicker;
            let total_shares = 0;

            let stock = {
                player_id: player_id,
                stock_name: stock_name,
                stock_ticker: stock_ticker,
                total_shares: total_shares
            };

            return stockService.createStock(stock);
        }
    }

}
</script>

<style scoped>
    .big-container {
        width: 50vw;
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin: auto;
    }

    .container {
        display: flex;
        flex-direction: column;
        padding: 4rem;
        padding-bottom: 1rem;
        background-color: var(--color-lighter);
        border-radius: var(--border-radius);
        color: var(--background-color);
        width: 50vw;
        margin: var(--padding) auto 0 auto;
        justify-content: center;
    }

    .info {
        display: flex;
        align-items: center;
        justify-content: flex-start;
        width: 500px;
        margin-bottom: 2rem;
    }

    .info img {
        max-height: 6rem;
        margin-right: 1rem;
    }

    h2{
        text-transform: none;
        font-size: 1.2rem;
    }

    .company h3 {
        font-size: 2.6rem;
    }

    .flex {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .flex h3 {
        text-align: left;
    }

    .largeButton {
        display: flex;
        align-items: flex-end;
        justify-content: flex-end;
    }

    select {
        background-color: var(--color-lighter);
        color: var(--background-color);
        font-size: 1rem;
        border: none;
        cursor: pointer;
    }

    button {
        padding: 0.4rem 2rem;
        color: var(--background-color);
        border: 2px solid var(--background-color);
        background-color: transparent;
        border-radius: 4rem;
        cursor: pointer;
        transition: 0.4s;
        text-transform: uppercase;
        width: 100%;
        margin: 1rem 0;
    }

    button:hover {
        border: 2px solid var(--color-green);
        background-color: var(--color-green);
    }

    .invalidTransaction {
        background-color: var(--color-red);
        border: 2px solid var(--color-red);
        pointer-events: none;
        opacity: 0.75;
    }

    .invalidTransaction:hover {
        background-color: var(--color-red);
        border: 2px solid var(--color-red);
        pointer-events: none;
    }

    .red {
        color: var(--color-red);
    }

    .green {
        color: var(--color-green);
    }
    
    input {
        border: none;
        background-color: transparent;
        border-bottom: 2px solid var(--background-color);
        margin-right: 1rem;
        transition: 0.4s;
        color: var(--background-color);
        font-size: 1rem;
        width: 4rem;
        text-align: center;
    }

    input:focus, input:hover {
        outline: none;
        color: var(--background-color);
        font-size: 1rem;
    }

    /* Chrome, Safari, Edge, Opera */
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    /* Firefox */
    input[type=number] {
        -moz-appearance: textfield;
    }

    .tradeToggle {
        margin-bottom: 2rem;
    }

    .tradeDetails div {
        margin: 1rem 0 0 0;
        height: 2rem;
    }

    label {
        padding-right: 1rem;
    }

    .buttons {
        display: flex;
        flex-direction: column;
        align-self: flex-end;
        flex-grow: 1;
        margin-left: 5rem;
    }

    .buttons button {
        margin: 0.3rem auto;
        width: 100%;
    }

    .back {
        display: flex;
        justify-content: flex-start;
    }

    a {
        color: var(--background-color);
        text-align: center;
        padding: 0.2rem;
        color: var(--background-color);
        background-color: transparent;
        cursor: pointer;
        border-bottom: 2px solid transparent;
        transition: 0.4s;
        margin: 0;
        margin-top: 1rem;
        font-size: 0.8rem;
    }

    a:hover {
        border-bottom: 2px solid var(--background-color);
    }

    /* a:hover {
        border-bottom: 0;
        color: var(--color-green);
    } */
</style>