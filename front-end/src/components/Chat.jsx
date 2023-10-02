import React, { useContext, useEffect, useState } from "react";
import Cam from "../assets/cam.png";
import Add from "../assets/add.png";
import More from "../assets/more.png";
import Messages from "./Messages";
import Input from "./Input";
import { ChatContext } from "../contexts/ChatContext";
import { AuthContext } from "../contexts/AuthContext";
import { NavContext } from "../contexts/NavContext";
import "../chatStyle.css";

const Chat = () => {
  const { currentUser, conversation } = useContext(ChatContext);
  const { sndUser } = useContext(AuthContext);
  const { changeActive } = useContext(NavContext);
  const [message, setMessage] = useState({});

  return (
    <div className="chat">
      <div className="chatInfo">
        <span onClick={() => changeActive(1)}>{sndUser?.username}</span>
        <div className="chatIcons">
          <img src={Cam} alt="" />
          <img src={Add} alt="" />
          <img src={More} alt="" />
        </div>
      </div>
      <Messages />
      <Input />
    </div>
  );
};

export default Chat;
