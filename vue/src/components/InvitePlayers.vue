<template>
<div class="container">
  <div class="invite">
    <h1>Invite Users</h1>
    <div class="flex">
      <table>
        <!-- <th>
          <td class="title">ID</td>
          <td class="title">Username</td>
          <td class="button"></td>
        </th> -->
        <p v-if="users.length == 0">There are no other players you can invite.</p>
        <tr v-else v-for="user in users" v-bind:key="user.id">
          <td>{{ user.id }}</td>
          <td> {{ user.username }}</td>
          <button v-if="!user.invited" v-on:click="inviteUser(user)">Invite</button>
          <button v-else v-on:click="cancelInvite(user)" id="cancel">Cancel</button>
        </tr>
      </table>
      <div class="invited">
        <h3>Invited Players:</h3>
        <p v-if="invitedUsers.length == 0">You have not invited any players yet.</p>
        <div v-else>
          <p v-for="user in invitedUsers" v-bind:key="user.id"> {{ user }}</p>
        </div>
      </div>
      <div class="flex">
        <router-link v-bind:to="{ name: 'game', params: {id: $route.params.id}}" id="finishedButton" tag="button">Finished Inviting Players</router-link>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import userService from '../services/UserService';
import playerService from '../services/PlayerService';
export default {
  data() {
    return {
      users: [],
      invitedUsers: [],
    }
  },
  created() {
    userService.getAllUsers().then( response => {
      response.data.forEach(user => {
        if (user.id != this.$store.state.user.id) {
          this.users.push( { id: user.id, username: user.username, invited: false });
        }
      })
    }).then(() => {
      // check if user has already been invited to game
      playerService.getPlayersByGame(this.$route.params.id).then(result => {
        console.log("users"  + this.users);
        result.data.forEach(player => {
          for(let user of this.users) {
            if(player.user_id == user.id) {
              user.invited = true;
              this.invitedUsers.push(user.username);
              let index = this.users.indexOf(user);
              this.users.splice(index, 1);
            }
          }
        })
      })
    })
  },
  methods: {
    inviteUser(user) {
      playerService.create({
        user_id: user.id,
        game_id: this.$route.params.id,
        game_status: 'Pending'
      })
      .then((response) => {
        if(response.status === 200) {
          user.invited = true;
          user.player_id = response.data;
          this.invitedUsers.push(user.username);
        }
      })
    },
    cancelInvite(user) {
      playerService.delete(user.player_id)
        .then(response => {
          if(response.status === 200) {
            let position = this.invitedUsers.indexOf(user.username);
            this.invitedUsers.splice(position, 1);
            user.invited = false;
          }
        })
    }
  }

}
</script>

<style scoped>

  h1 {
    margin-bottom: 2rem;
  }

  .container {
    padding: var(--padding);
    display: flex;
    align-content: center;
    justify-content: center;
    /* height: 90vh; */
  }

  .invite {
    padding: var(--padding);
    margin: auto;
    border-radius: var(--border-radius);
    color: var(--background-color);
    background-color: var(--color-lighter);
    width: 50vw;
  }

  .invited {
    margin: 2rem 0;
  }

  h3 {
    text-align: left;
  }

  table {
    display: flex;
    flex-direction: column;
    border-collapse: collapse;
    border-spacing: 2px;
    text-align: center;
    padding: 0;
  }

  td {
    font-size: 1.2rem;
    text-align: center;
    padding: 6px;
    flex-basis: 1;
    flex-grow: 1;
    width: 33.3%;
    text-align: left;
  }

  tr, th {
    display: flex;
    flex-direction: row;
  }

  h1, h2 {
    text-align: center;
  }

  ul li {
    font-size: 1.2rem;
    margin: 0.5rem;
  }

  button{
    font-size: 1.2rem;
    padding: 0.4rem 2rem;
    margin: 1rem;
    color: var(--background-color);
    border: 2px solid var(--background-color);
    background-color: transparent;
    border-radius: 4rem;
    cursor: pointer;
    transition: 0.4s;
    width: 40%; 
    border-color: var(--background-color);
    white-space:pre;
    margin-top: 0rem;
  }

  #cancel:hover {
    border: 2px solid var(--color-red);
    background-color: var(--color-red);
  }

  button:hover {
    border: 2px solid var(--color-green);
    background-color: var(--color-green);
  }

  #finishedButton {
    align-self: center;
    width: 100%;
    justify-self: center;
  }
</style>