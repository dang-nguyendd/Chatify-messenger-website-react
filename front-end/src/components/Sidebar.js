import React, { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";
import {
  LogoutOutlined,
  HomeFilled,
  MessageFilled,
  SettingFilled,
} from "@ant-design/icons";
import { Avatar } from "antd";

const Sidebar = () => {
  const { user, setUser } = useContext(AuthContext);

  return (
    <div style={{ textAlign: "center" }}>
      <div className="ce-sidebar-menu">
        <HomeFilled className="ce-sidebar-icon" />
        <MessageFilled className="ce-sidebar-icon ce-sidebar-icon-active" />
        <SettingFilled className="ce-sidebar-icon" />
      </div>
      <Avatar
        className="sidebar-avatar"
        src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
      />
      {/* <Avatar
        className="sidebar-avatar"
        avatarUrl={typeof user?.avatar === "string" ? user.avatar : undefined}
        username={user?.username}
        isOnline={true}
      /> */}

      <LogoutOutlined
        // onClick={() => setUser(undefined)}
        className="signout-icon"
      />
    </div>
  );
};

export default Sidebar;
