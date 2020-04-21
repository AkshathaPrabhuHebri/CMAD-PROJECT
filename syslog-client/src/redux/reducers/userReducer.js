import { ADD_USER,LOAD_USERS } from "../actions/userActions";

function userReducer(state = { users: [] }, action) {
    switch (action.type) {
        case ADD_USER:
            return Object.assign({}, state, {
                users: [...state.users, action.user],
            });
        case LOAD_USERS:
            return Object.assign({}, state, {
                users: action.payload,
            });
        default:
            return state;
    }
}

export default userReducer;
