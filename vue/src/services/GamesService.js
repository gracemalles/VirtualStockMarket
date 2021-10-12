import axios from 'axios';

export default {
    getAllGames() {
        return axios.get('/games');
    },
    getGamesByOrganizerId(organizerId){
        return axios.get('/games/organizer/' + organizerId);
    },
    getGameByUserId(userId){
        return axios.get('/games/' + userId);
    },
    getGameById(id){
        return axios.get('/games/' + id);
    },
    createGame(game) {
        return axios.post('/games', game);
    },
    deleteGame(id){
        return axios.delete('/games/' + id);
    },
    
}