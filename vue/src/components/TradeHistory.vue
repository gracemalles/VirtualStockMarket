<template>
<div class="historyContainer">
  <p class="empty" v-if="tradeHistory.length == 0">You have no trade history to display.</p>
  <div v-else class= "history" v-for="trade in tradeHistory" v-bind:key="trade.id">
    <div>
      <h3>{{trade.stock_name }} {{ trade.buy_or_sell }}</h3>
      <p>{{trade.date.toString().substring(5)}}-{{trade.date.toString().substring(0, 4)}}</p>
    </div>
    <div>
      <p class="shares">{{ trade.buy_or_sell == 'Buy' ? "+" : "-"}}{{trade.shares_traded }} Share{{ trade.shares_traded != 1 ? "s" : ""}}</p>
      <p class="price">{{ trade.buy_or_sell == 'Buy' ? "-" : "+"}}${{trade.price.toFixed(2) }} </p>
    </div>
   </div>  
  </div>
</template>

<script>
import TradeService from "../services/TradeService"
export default {
    data() {
        return {
            tradeHistory: [],
        }
    },

    created(){
        TradeService.getTradeHistory(this.$store.state.currentPlayerId)
        .then((response) => {
            console.log(response);
            this.tradeHistory = response.data;
        })
    }
}
</script>

<style scoped>

    .historyContainer{
        max-height: 40vh;
        overflow-y: scroll;
        width: 100%;
    }

    .history {
        background-color: var(--background-color);
        color: var(--color-primary);
        border-radius: var(--border-radius);
        margin-bottom: 0.5rem;
        margin-top: 0.5rem;
        justify-content: space-between;
        padding: 1rem;
        align-items: center;
        transition: 0.4s;
        margin-bottom: 0.4rem;
        display: flex;
    }


    h3 {
        padding: 0.2rem 0 0 0;
        font-size: 1rem;
    }

    p {
        font-size: 0.9rem;
        padding: 0;
    }

    .shares {
        font-weight: bold;
        text-align: right;
    }
    .price {
        text-align: right;
    }
  
   .empty {
       text-align: center;
   }

</style>