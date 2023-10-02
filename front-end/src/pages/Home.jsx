import React, { useState, useContext, useEffect } from "react";
import { NavContext } from "../contexts/NavContext";
import Sidebar from "../components/Sidebars.jsx";
import Chat from "../components/Chat";
import MainSidebar from "../components/MainSidebar";
import "../chatStyle.css";
import valley from "../assets/valley.jpeg";
import {
  auth,
  logInWithEmailAndPassword,
  signInWithGoogle,
  signInWithFacebook,
} from "../firebase";
import { useAuthState } from "react-firebase-hooks/auth";

const backgroundImage = {
  backgroundImage: `url(${valley})`, // Here due to variable
};

const Home = () => {
  return (
    // <div className="background-image" style={backgroundImage}>
    //   <div className="background-gradient-dark">
    //     <div style={styles.titleStyle}>Chatify</div>
    <div className="home">
      <div className="container">
        <div
          style={{
            width: "6vw",
            height: "100%",
            position: "fixed",
            top: "0px",
            left: "0px",
            backgroundColor: "rgb(40,43,54)",
          }}
        >
          <MainSidebar />
        </div>
        <Sidebar />
        <Chat />
      </div>
    </div>
    //   </div>
    // </div>
  );
};

export default Home;
