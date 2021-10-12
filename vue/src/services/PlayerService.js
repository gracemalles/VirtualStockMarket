import axios from 'axios';

// need path names  
// using console.log as placeholder to avoid compiler errors
export default {
    getAllPlayers(){
        return axios.get('/players');
    },

    getPlayerByGame(game_id) {
      return axios.get(`/game/${game_id}/player`);
    },

    getPlayersByGame(game_id) {
      return axios.get(`players/game/${game_id}`);
    },
    
    create(player) {
      return axios.post('/players', player);
    },
  
      update(player) {
        return axios.put(`/players/${player.id}/`, player);
      },
    
    delete(id) {
      return axios.delete(`/players/${id}`);
    }
  }
    
