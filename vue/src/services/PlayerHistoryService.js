import axios from 'axios'

export default {
    getHistoryByPlayerId(id){
        return axios.get(`/players/${id}/history`)
    }
}