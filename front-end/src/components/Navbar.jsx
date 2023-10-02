import React, { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";
import "../chatStyle.css";

const Navbar = () => {
  const { currentUser } = useContext(AuthContext);

  return (
    <div className="navbar">
      <span className="logo">Chatify</span>
    </div>
  );
};

export default Navbar;
