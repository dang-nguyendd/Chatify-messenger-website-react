import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import AuthContextProvider from "./contexts/AuthContext";

import ChatContextProvider from "./contexts/ChatContext";
import ChatsContextProvider from "./contexts/ChatsContext";
import NavContextProvider from "./contexts/NavContext";

import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "react-query";
// Initialze the client

const queryClient = new QueryClient();

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter>
    <QueryClientProvider client={queryClient}>
      <AuthContextProvider>
        <NavContextProvider>
          <ChatsContextProvider>
            <ChatContextProvider>
              <App />
            </ChatContextProvider>
          </ChatsContextProvider>
        </NavContextProvider>
      </AuthContextProvider>
    </QueryClientProvider>
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
