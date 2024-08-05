import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import "@mantine/core/styles.css";
import "@mantine/notifications/styles.css";
import "@mantine/dropzone/styles.css";
import { HelmetProvider } from 'react-helmet-async'
import AppProvider from './contexts/AppProvider.tsx'
import { AuthProvider } from './contexts/AuthProvider.tsx'
import { Notifications } from '@mantine/notifications'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <HelmetProvider>
      <AuthProvider>
        <AppProvider>
          <Notifications position="top-right" autoClose={5000} />

          <App />
        </AppProvider>

      </AuthProvider>

    </HelmetProvider>
  </React.StrictMode>,
)
