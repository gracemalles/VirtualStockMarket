import axios from 'axios';

export default{
    getTopStocks(){
        return axios.get(`/stockinfo/topten`);
    },
    getStockInfo(ticker){
        return axios.get(`/stockinfo/${ticker}`);
    },
    getSearchInfo(searchTerm){
        console.log("Calling getSearchInfo");
        const value = axios(`stockinfo/${searchTerm}`, {
            method: 'GET',
            mode: 'no-cors',
            headers: {
              'Access-Control-Allow-Origin': '*',
              'Content-Type': 'application/json',
            },
           credentials: 'same-origin',
          });
          return value;
        //return axios.get(`/stockinfo/search/${searchTerm}`);
    },
    getPlayerStocks(playerId) {
        return axios.get(`/player/${playerId}/stocks`);
    },
    getStockByPlayerAndTicker(playerId, ticker) {
        return axios.get(`/player/${playerId}/stocks/${ticker}`);
    },
    createStock(stock) {
        return axios.post(`/stocks`, stock);
    },
    getHistoricalStockData(symbol, resolution, from, to) {
        return axios.get(`/candle/${symbol}/${resolution}/${from}/${to}`);
    }

}