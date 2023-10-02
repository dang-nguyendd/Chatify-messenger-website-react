// import {
//   ChatCard,
//   ChatCardProps,
//   ChatObject,
// } from "react-chat-engine-advanced";
import React from "react";
// import { getOtherUser } from "../functions/getOtherUser";
import { Avatar, Card } from "antd";
const { Meta } = Card;
const ChatCard = (props) => {
  //   if (!props.chat) return <div />;

  // const otherMember = getOtherUser(props.chat, props.username);
  const firstName = "123";
  const lastName = "123";
  const username = "123";
  const messageText = "123";
  //   const hasNotification =
  //     props.chat.last_message.sender_username !== props.username;

  return (
    <div
      style={{
        width: "18vw",
        top: "0px",
        left: "10vw",
        height: "10%", // Fill parent height
      }}
    >
      <Card
        style={{
          borderRadius: "8px 8px 8px 8px",
          backgroundColor: "white",
          color: "white",
          height: "10vh",
          width: "calc(100% - 12px - 12px)",
          margin: "0px 12px",
          paddingTop: "10px !important",
          paddingBottom: "50px !important",
        }}
      >
        <Meta
          style={{
            lineHeight: "1",
            color: "white",
          }}
          avatar={
            <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
          }
          title={`${firstName} ${lastName}`}
          description={
            messageText === null || messageText.length === 0
              ? "Say hello!"
              : messageText
          }
          //   isActive={props.isActive}
          // onClick={() => props.chat && props.onChatCardClick(props.chat.id)}
        />
      </Card>
    </div>
  );
};

export default ChatCard;
