import React, { useContext, useEffect, useState } from "react";
import { ChatContext } from "../contexts/ChatContext";
import Message from "./Message";
import "../chatStyle.css";
import SockJsClient from "react-stomp";
import { Socket } from "socket.io-client";
import Stomp from "stompjs";
import axios from "axios";

const Messages = () => {
  const { conversation, messages, setMessages } = useContext(ChatContext);

  //Infinite scroll, load cái cuối cùng
  // Message
  return (
    <div className='messages'>
      {conversation === null ? (
        <span className='noConversation'>Open a conversation to start a chat.</span>
      ) : (
        <>
          <SockJsClient
            url={`http://54.251.83.192:9001/ws-messages/`}
            topics={[`/topic/messages/${conversation}`]}
            onConnect={() => {
              console.log("Connected");
              axios
                .get(`http://54.251.83.192:9001/messages/joinRoom/${conversation}`)
                .then((res) => {
                  console.log(res.data);
                  setMessages(res.data);
                });
              //Join a room with sockjs
            }}
            onDisconnect={() => {
              console.log("Disconnected");
            }}
            onMessage={(msg) => {
              console.log(msg);
              setMessages([...messages, msg]);
            }}
          />
          {messages.map((m) => (
            <Message message={m} key={m.id} />
          ))}
        </>
      )}
    </div>
  );
};

export default Messages;
