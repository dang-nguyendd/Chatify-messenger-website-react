import React, { useState, useContext, useEffect } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";
import { NavContext } from "../contexts/NavContext";
import {
  LogoutOutlined,
  HomeFilled,
  MessageFilled,
  SettingFilled,
  UserOutlined,
  CloseOutlined,
} from "@ant-design/icons";
import { Avatar, message, Dropdown, Popover } from "antd";
import { auth, logout } from "../firebase";
import { useAuthState } from "react-firebase-hooks/auth";

const MainSidebar = () => {
  // const { user, setUser } = useContext(AuthContext);
  const { sndUser, getSndUser } = useContext(AuthContext);
  const [user, loading, error] = useAuthState(auth);
  const { active, setActive, changeActive } = useContext(NavContext);
  const navigate = useNavigate();
  const [open, setOpen] = useState(false);
  useEffect(() => {
    if (loading) return;
    if (!user) navigate("/login");
  }, [user, loading]);

  const handleMenuClick = (e) => {
    //Clear data in local storage
    localStorage.clear();
    logout();
    message.info("You've just logged out");
    console.log("click sidebar", e);
  };

  const hide = () => {
    setOpen(false);
  };
  const handleOpenChange = (newOpen) => {
    setOpen(newOpen);
  };

  // const items = [
  //   {
  //     label: "Log out",
  //     key: "1",
  //     icon: <UserOutlined />,
  //   },
  //   {
  //     label: "Cancel",
  //     key: "2",
  //     icon: <CloseOutlined />,
  //   },
  // ];

  // const menuProps = {
  //   items,
  //   onClick: handleMenuClick,
  // };

  return (
    <div style={{ textAlign: "center" }}>
      <div className="ce-sidebar-menu">
        {active === 0 ? (
          <MessageFilled
            onClick={() => changeActive(0)}
            className="ce-sidebar-icon ce-sidebar-icon-active"
          />
        ) : (
          <MessageFilled
            onClick={() => changeActive(0)}
            className="ce-sidebar-icon"
          />
        )}

        {active === 1 && sndUser ? (
          <UserOutlined
            onClick={() => changeActive(1)}
            className="ce-sidebar-icon ce-sidebar-icon-active"
          />
        ) : (
          <UserOutlined
            onClick={() => {
              if (sndUser) changeActive(1);
            }}
            className="ce-sidebar-icon"
          />
        )}

        {active === 2 ? (
          <SettingFilled
            onClick
            className="ce-sidebar-icon ce-sidebar-icon-active"
          />
        ) : (
          <SettingFilled onClick className="ce-sidebar-icon" />
        )}
      </div>

      {/* <Avatar
        className="sidebar-avatar"
        avatarUrl={typeof user?.avatar === "string" ? user.avatar : undefined}
        username={user?.username}
        isOnline={true}
      /> */}
      {active === 3 ? (
        <Avatar
          onClick={() => changeActive(3)}
          className="sidebar-avatar sidebar-avatar-active"
          src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
        />
      ) : (
        <Avatar
          onClick={() => changeActive(3)}
          className="sidebar-avatar"
          src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
        />
      )}

      <Popover
        content={<a onClick={handleMenuClick}>Sign Out</a>}
        title="Are you sure?"
        trigger="click"
        open={open}
        onOpenChange={handleOpenChange}
      >
        <LogoutOutlined className="signout-icon signout-icon-active" />
      </Popover>
    </div>
  );
};

export default MainSidebar;
