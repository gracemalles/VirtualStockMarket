<template>
  <div id="stockSearch">
      <div class="input">
        <input type="text" id="searchText" v-model="searchTerm" v-on:keyup.enter="retrieveSearch(searchTerm)"/>
        <button v-on:click="retrieveSearch(searchTerm)">Search</button>
      </div>
        <p id="errorText" v-if="!(searchResult.companyName) && searched">
            Could not find that company.  Please enter a valid stock ticker symbol.
        </p>
      <div v-if="searchResult.companyName" class="results">
        <router-link class="resultsList" :to="{name: 'stock-info', params: {ticker: searchResult.stockSymbol}}">
            {{searchResult.companyName}} (${{searchResult.currentPrice}})
        </router-link>
        <button v-on:click="closeResults" class="closeButton">x</button>
      </div>
  </div>
</template>

<script>
import stockService from '../services/StockService.js';
export default {
    data() {
        return {
            searchTerm: "",
            searchResult: {},
            searched: false
        }
    },
    methods: {
        retrieveSearch(searchTerm) {
            this.searchResult = {};
            stockService.getSearchInfo(searchTerm.toUpperCase()).then(response => {
                if(response.status >=200 && response.status < 300) {
                    this.searchResult = response.data;
                    this.searchTerm = "";
                }
                this.searched = true;
            }).catch(error => {
                this.searched = true;
                console.log(error.data);

            });
        },

        closeResults() {
            this.searched = false;
            this.searchResult = {};
        }
    }

}
</script>

<style scoped>
    .resultsList{
        display: flex;
        flex-direction:row;
        text-decoration: none;
        color: var(--background-color);
        margin: 2rem 0;
    }

    #stockSearch {
        padding: 1rem;
        text-align: center;
        width: auto;
    }

    .input {
        display: flex;
    }


    input {
        border: none;
        background-color: transparent;
        border-bottom: 2px solid var(--background-color);
        margin-right: 1rem;
        transition: 0.4s;
        color: var(--background-color);
        font-size: 1rem;
        padding: 0.5rem;
    }

    input:focus, input:hover {
        outline: none;
        color: var(--background-color);
        font-size: 1rem;
    }

    button {
        padding: 0.2rem 2rem;
        color: var(--background-color);
        border: 2px solid var(--background-color);
        background-color: transparent;
        border-radius: 4rem;
        cursor: pointer;
        transition: 0.4s;
        text-transform: uppercase;
    }

    button:hover {
        border: 2px solid var(--color-green);
        background-color: var(--color-green);
    }

    #errorText {
        color: var(--color-red); 
        margin: 2rem 0;
        display: flex;
        width: 22rem;
        text-align: left;
    }

    .closeButton {
        width: 2rem;
        height: 2rem;
        padding: 0;
    }

    .closeButton:hover {
        border: 2px solid var(--color-red);
        color: var(--color-red);
        background-color: transparent;
    }

    .results {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>