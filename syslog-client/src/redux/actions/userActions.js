export const ADD_USER = 'ADD_USER' // action types
export const LOAD_USERS = 'LOAD_USERS' // action types
import API from "../../utils/api"

export function addUser(user) {
  return
     {     
        type: ADD_USER,
        user     // action payload
     }
}

export function loadUsers() {
    return (dispatch, getState) => {
        API.getUsers().then(resp =>{
            dispatch({
                type : LOAD_USERS,
                payload : resp.data
            })
        })
    };
}