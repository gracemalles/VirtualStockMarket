import axios from 'axios';

export default{
    createTrade(trade) {
        return axios.post("/trade", trade);
    },

    getTradeHistory(playerId) {
        return axios.get('/tradehistory/' + playerId);
    }


}