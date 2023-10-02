import React, { useContext } from "react";

import valley from "../assets/valley.jpeg";
import "../theme.css";
// import {
//   MultiChatWindow,
//   MultiChatSocket,
//   useMultiChatLogic,
//   MessageFormProps,
//   ChatCardProps,
//   ChatHeaderProps,
// } from "react-chat-engine-advanced";

import Sidebar from "../components/Sidebar";
import MessageForm from "../components/MessageForm";
import UserSearch from "../components/UserSearch";
import ChatCard from "../components/ChatCard";
import ChatHeader from "../components/ChatHeader";
import CardList from "../components/CardList";

const ChatsPage = () => {
  // const username = user ? user.username : "";
  // const secret = user && user.secret !== null ? user.secret : "";

  const backgroundImage = {
    backgroundImage: `url(${valley})`, // Here due to variable
  };

  return (
    <div className="background-image" style={backgroundImage}>
      <div className="background-gradient-light">
        <div
          style={{
            position: "relative",
            top: "10vh",
            left: "calc(50vw - 3vw - 1.5vw - 40vw)",
            height: "80vh",
            width: "calc(100vw - 5.5vw - 10.5vw)",
            backgroundColor: "black",
          }}
        >
          <div
            style={{
              width: "6vw",
              height: "100%",
              position: "absolute",
              top: "0px",
              left: "0px",
              backgroundColor: "rgb(40,43,54)",
            }}
          >
            <Sidebar />
          </div>

          <div
            style={{
              width: "calc(100% - 15%)",
              position: "absolute",
              display: "flex",
              flexDirection: "row",
              top: "0px",
              left: "6vw",
              height: "100%", // Fill parent height
            }}
          >
            <div
              style={{
                width: "20vw",
                top: "0px",
                left: "10vw",
                height: "10%", // Fill parent height
              }}
            >
              <UserSearch
                username={"chatProps.username"}
                secret={"chatProps.secret"}
              />
              <div
                style={{
                  width: "20vw",
                  height: "65vh", // Fill parent height
                  top: "0px",
                  left: "10vw",
                }}
              >
                <CardList />
              </div>
            </div>
            <div
              style={{
                width: "100%",
                height: "100%", // Fill parent height
                display: "flex",
                flexDirection: "column",
              }}
            >
              <div
                style={{
                  width: "100%",
                  top: "0px",
                  left: "10vw",
                  height: "15%", // Fill parent height
                }}
              >
                <ChatHeader
                  chat={"chatProps.chat"}
                  username={"chatProps.username"}
                  secret={"chatProps.secret"}
                />
              </div>
              <div
                className="ce-message-list"
                // style={{
                //   width: "100%",
                //   top: "0px",
                //   left: "10vw",
                //   height: "70%", // Fill parent height
                //   backgroundColor: "rgb(40,43,54)",
                // }}
              ></div>

              <MessageForm />
            </div>
            {/* <ChatCard
                {...props}
                username={chatProps.username}
                onChatCardClick={chatProps.onChatCardClick}
                isActive={
                  props.chat !== undefined &&
                  chatProps.activeChatId === props.chat.id
                }
                chat={props.chat}
              /> */}
          </div>
        </div>
      </div>
    </div>
  );
};

const styles = {
  ListContainerStyle: {
    width: "100%",
    maxWidth: "650px",
    padding: "36px 72px",
  },
  ChatContainerStyle: {
    width: "100%",
    maxWidth: "650px",
    padding: "36px 72px",
  },
};

export default ChatsPage;
