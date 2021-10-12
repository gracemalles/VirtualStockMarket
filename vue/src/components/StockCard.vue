<template>
    <router-link v-bind:to="{name: 'stock-info', params: {ticker: stock.stock_ticker}}">
        <div class="stock">
            <div class="column">
                <h3>{{ stock.stock_ticker }}</h3>
            </div>
            <div class="column column-2">
                <p> {{ stock.total_shares}} Share{{stock.total_shares != 1 ? "s" : ""}} </p>
                <p>Value: ${{ marketValue.toFixed(2) }} </p>
            </div>
        </div>
    </router-link>
</template>

<script>
import stockService from '../services/StockService'
export default {
    props: ['stock'],
    data() {
        return {
            currentValue: 0,
        }
    },
    created() {
        stockService.getStockInfo(this.stock.stock_ticker)
            .then(result => {
                this.currentValue = result.data.currentPrice;
            })
    },
    computed: {
        marketValue() {
            return this.currentValue * this.stock.total_shares;
        }
    }


}
</script>

<style scoped>
    .stock {
        background-color: var(--background-color);
        color: var(--color-primary);
        border-radius: var(--border-radius);
        margin-bottom: 0.5rem;
        display: flex;
        justify-content: space-between;
        padding: 0.3rem 1rem;
        align-items: center;
        transition: 0.4s;
    }

    h3 {
        padding: 0.4rem 0;
        text-align: left;
    }

    .column-2 {
        text-align: right;
    }

    p {
        margin: 0.5rem;
        padding-bottom: 0;
    }

    .stock:hover {
        background-color: white;
    }

</style>