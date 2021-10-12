import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    games: [],
    players: [],
    loaded: false,
    currentPlayerId: -1,
    activeGameId: -1,
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_GAME(state, game) {
      game.isFinished = false;
      state.games.push(game);
    },
    ADD_PLAYER(state, player) {
        state.players.push(player);
    },
    UPDATE_PLAYER_STATUS(state, player) {
      for (let i = 0; i < state.players.length; i++) {
        if (player.id == state.players[i].id) {
          state.players[i].game_status = player.game_status;
        }
      }
    },
    SET_CURRENT_PLAYER(state, player_id) {
      state.currentPlayerId = player_id;
    },
    SET_ACTIVE_GAME(state, game_id) {
      state.activeGameId = game_id;
    },
    UPDATE_GAME(state, game) {
      for (let i = 0; i < state.games.length; i++) {
        if (game.id == state.games[i].id) {
          Vue.set (state.games, i, game);
        }
      }
    } 
  }
})
