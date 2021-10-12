<template>
  <div class="stock-scroll">
      <p v-if="stocks.length == 0">You have no stocks in your portfolio.</p>
      <stock-card v-else v-for="stock in stocks" v-bind:stock="stock" v-bind:key="stock.id"></stock-card>
  </div>
</template>

<script>
import StockCard from './StockCard.vue';
export default {
  components: { StockCard },
  props: ['stocks'],
  data() {
    return {
    }
  },
};
</script>

<style scoped>
  .stock-scroll{
    max-height: 40vh;
    overflow-y: scroll;
  }

  p {
    text-align: center;
  }
</style>