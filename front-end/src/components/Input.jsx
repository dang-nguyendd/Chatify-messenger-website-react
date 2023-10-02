import React, { useContext, useState, useEffect } from "react";
import Img from "../assets/img.png";
import Attach from "../assets/attach.png";
import { AuthContext } from "../contexts/AuthContext";
import { ChatContext } from "../contexts/ChatContext";
import { v4 as uuid } from "uuid";
import "../chatStyle.css";
// import { Client } from '@stomp/stompjs';
import Stomp from "stompjs";
import axios from "axios";

const SOCKET_URL = "ws://54.251.83.192:9001/ws-message";

const Input = () => {
  const [text, setText] = useState("");
  const [img, setImg] = useState(null);
  const { conversation } = useContext(ChatContext);

  // const { currentUser } = useContext(AuthContext);
  const { user } = useContext(AuthContext);
  const { addMessage } = useContext(ChatContext);

  const handleSend = (event) => {
    if (text !== "" && conversation !== null) {
      const message = {
        content: text,
        isSeen: false,
        mediaUrl: img,
        senderId: parseInt(localStorage.getItem("id")),
        conversationId: conversation,
      };
      console.log(message);
      addMessage(message);
      setText("");
      sendMessage(message);
    } else {
      alert("Please select a conversation");
    }
  };

  //Send message to a stomp server

  const sendMessage = (message) => {
    axios.post("http://54.251.83.192:9001/messages/chat", message).then((res) => {
      console.log(res);
    });
  };

  return (
    <div className='input'>
      <input
        type='text'
        placeholder='Type something...'
        onChange={(e) => setText(e.target.value)}
        value={text}
      />
      <div className='send'>
        <img src={Attach} alt='' />
        <input
          type='file'
          style={{ display: "none" }}
          id='file'
          onChange={(e) => setImg(e.target.files[0])}
        />
        <label htmlFor='file'>
          <img src={Img} alt='' />
        </label>
        <button onClick={handleSend}>Send</button>
      </div>
    </div>
  );
};

export default Input;
