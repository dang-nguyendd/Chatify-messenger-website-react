import "./App.css";
import { Routes, Route, Navigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "./contexts/AuthContext";
import SignUpForm from "./pages/SignUpForm";
import LogInForm from "./pages/LogInForm";
import ChatsPage from "./pages/ChatsPage";
import ChatCard from "./components/ChatCard";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
// app.js

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/login" element={<LogInForm />} />
      <Route path="/signup" element={<SignUpForm />} />
    </Routes>
  );
};

export default App;
