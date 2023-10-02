import React, { useContext, useEffect, useState } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { ChatsContext } from "../contexts/ChatsContext";
import "../chatStyle.css";
import { ChatContext } from "../contexts/ChatContext";

const Chats = () => {
  const { convo } = useContext(ChatsContext);
  const { getSecondUser, mainUser } = useContext(AuthContext);
  const {conversation, setConversation} = useContext(ChatContext);
  // useEffect(() => {
  //   const getChats = () => {
  //     const unsub = onSnapshot(doc(db, "userChats", currentUser.uid), (doc) => {
  //       setChats(doc.data());
  //     });

  //     return () => {
  //       unsub();
  //     };
  //   };

  //   currentUser.uid && getChats();
  // }, [currentUser.uid]);

  const handleSelect = (id) => {
    console.log(id);
    setConversation(id);
  };

  return (
    <div className="chats">
      {convo
        ? Object.entries(convo)
            ?.sort((a, b) => b[1].date - a[1].date)
            .map((chat) => (
              <div
                className="userChat"
                key={chat[0]}
                onClick={() => handleSelect(chat[1].id)}
              >
                <img src={chat[1].conversationPhoto} alt="" />
                <div className="userChatInfo">
                  <span>
                    {
                      chat[1].conversationName
                        .split("-")
                        .filter((user) => user !== mainUser.username)[0]
                    }
                  </span>
                  {/* <p>
                    {chat[1].messages
                      ? chat[1].messages[messages.length]
                      : "Say Hello to your friend"}
                  </p> */}
                </div>
              </div>
            ))
        : {}}
    </div>
  );
};

export default Chats;
