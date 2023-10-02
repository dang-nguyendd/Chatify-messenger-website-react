import React from "react";
import { Avatar, Card } from "antd";
const { Meta } = Card;
const MyCard = () => (
  <Card
    style={{
      width: 300,
    }}
  >
    <Meta
      avatar={
        <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
      }
      title="Adam Wilder"
      description="Say something"
    />
  </Card>
);
export default MyCard;
