import axios from 'axios';

// need path names  
export default {
    getAllUsers() {
        return axios.get('/users');
    },
    getUserById(id) {
        return axios.get(`/users/${id}`);
    }
}
    
