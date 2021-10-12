<template>
<div class="main">
    <div class="wrapper">
        <h3>Make a Trade</h3>
        <div class="scroll">
        <router-link v-for="stock in topStocks" v-bind:key="stock.id" v-bind:to="{name: 'stock-info', params: {ticker: stock.stockSymbol}}">
            <div class="row">
                <img :src="stock.logoURL">
                <p> {{stock.stockSymbol}} </p>
                <p> {{stock.companyName}} </p>
                <p> ${{stock.currentPrice.toFixed(2)}} </p>
            </div>
        </router-link>
        </div>
        <stock-search />


    </div>

</div>
 
</template>

<script>
import tradeTopStocks from '../services/StockService.js'
import StockSearch from '../components/StockSearch.vue'
export default {
    components: {StockSearch},
    data(){
        return {
            topStocks: []
    }
},

created(){
    tradeTopStocks.getTopStocks().then(
        (response) => {
            this.topStocks = response.data;
            //console.log(this.topStocks);
        }
    ).catch(
        (error) => console.log(error)
    );

}
};
</script>

<style scoped>
    img {
        max-height: 3rem;
        max-width: 3rem;
    }

    .main {
        padding: 1rem 0;
        display: flex;
        justify-content: center;
        flex-grow: 1;
    }

    .scroll {
        max-height: 50vh;
        overflow-y: scroll;
        width: 100%;
    }

    .row {
        border-collapse: collapse;
        width: 100%;
        margin: 0.5rem 0;
        border-radius: var(--border-radius);
    }

    .row {
        height: 4rem;
        padding: 10px;
        align-items: center;
        max-height: 4rem;
        background-color: var(--background-color);
        color: var(--color-primary);
        display: flex;
        align-content: center;
        justify-content: space-between;
    }

    h3 {
        padding: 1rem 0;
        text-align: center;
    }

    p {
        padding: 0;
    }

    .row:hover {
        background-color: white;
    }
</style>