import React, { useContext, useEffect, useRef } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { ChatContext } from "../contexts/ChatContext";
import { Image } from "antd";
import "../chatStyle.css";
import moment from "moment/moment";
const Message = ({ message }) => {
  // const { currentUser } = useContext(AuthContext);
  const { currentUser } = useContext(ChatContext);

  const ref = useRef();

  useEffect(() => {
    console.log("Message");
    console.log(message);
    ref.current?.scrollIntoView({ behavior: "smooth" });
  }, [message]);

  return (
    <div
      ref={ref}
      className={`message ${message.sender.username === localStorage.getItem('username') && "owner"}`}
    >
      <div className="messageInfo">
        {/* <img
          src={
            message.sender === "John"
              ? "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
              : currentUser.photo
          }
          alt="Error"
        /> */}
        {message.sender.username === localStorage.getItem('username') ? (
          <></>
        ) : (
          <img src={currentUser.photo} alt="Error" />
        )}
      </div>
      <div className="messageContent">
        {/* Convert timestamp to date and second
         */}
        <span>{moment(message.timeStamp).format('ddd MMM D YYYY HH:mm:ss')}</span>
        <p>{message.content}</p>
        {message.img && (
          <div className="img">
            <Image src={message.Url} alt="Can't load img" />
          </div>
        )}
      </div>
    </div>
    // <></>
  );
};

export default Message;
