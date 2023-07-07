import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './components/App';
import {Provider} from 'react-redux';
import {store} from './redux';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import './style.scss';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(<React.StrictMode>
    <Provider store={store}>
        <App />
    </Provider>
</React.StrictMode>);
