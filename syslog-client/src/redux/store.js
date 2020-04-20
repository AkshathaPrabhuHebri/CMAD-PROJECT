import { createStore,applyMiddleware } from "redux";
import thunk from 'redux-thunk';
import userReducer from "./reducers/userReducer";

export default createStore(userReducer, applyMiddleware(thunk));