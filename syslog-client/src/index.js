import React from 'react';
import ReactDOM from 'react-dom';

import App from './App';

import './assets/styles/style.sass';
import './assets/styles/style.css';
import { Provider } from "react-redux";
import store from "./redux/store";

ReactDOM.render(
  <Provider store={store}>
          <App />
       </Provider>,
  document.getElementById('root'),
);

// Check if hot reloading is enable. If it is, changes won't reload the page.
// This is related to webpack-dev-server and works on development only.
if (module.hot) {
  module.hot.accept();
}
