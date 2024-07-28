import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import reportWebVitals from './reportWebVitals';
import App from './components/app/App';

import { PrimeReactProvider } from "primereact/api";
import "primereact/resources/themes/lara-light-indigo/theme.css";
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';  
import 'primereact/resources/primereact.css';



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <PrimeReactProvider value={{ ripple: true, appendTo: 'self', }}>
        <App />
      </PrimeReactProvider>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
