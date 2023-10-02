import React, { useState } from "react";
import {
  PhoneFilled,
  DeleteFilled,
  PaperClipOutlined,
  LoadingOutlined,
} from "@ant-design/icons";

import { Avatar } from "antd";

const ChatHeader = (props) => {
  // State
  const [isFilePickerLoading, setFilePickerLoading] = useState(false);
  const [isDeleteLoading, setDeleteLoading] = useState(false);
  // Hooks

  return (
    <div className="ce-custom-chat-header">
      {true && (
        <div>
          <Avatar
            className="ce-custom-header-avatar"
            src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
          />
          <div className="ce-custom-header-text">
            <div className="ce-custom-header-title">
              {"otherMember.first_name"} {"otherMember.last_name"}
            </div>
            <div className="ce-custom-header-subtitle">Online</div>
          </div>

          <div className="ce-custom-header-icon-wrapper">
            <form style={{ display: "inline-block" }}>
              <label htmlFor="ce-files-picker">
                {isFilePickerLoading ? (
                  <LoadingOutlined className="ce-custom-header-icon" />
                ) : (
                  <PaperClipOutlined className="ce-custom-header-icon" />
                )}
              </label>
              <input
                multiple
                id="ce-files-picker"
                style={{ visibility: "hidden", height: "0px", width: "0px" }}
                type="file"
              />
            </form>

            <PhoneFilled className="ce-custom-header-icon" />

            {isDeleteLoading ? (
              <LoadingOutlined className="ce-custom-header-icon" />
            ) : (
              <DeleteFilled className="ce-custom-header-icon" />
            )}
          </div>
        </div>
      )}

      <style>{`
      .ce-custom-header-avatar { display: inline-block; position: relative; top: 28px; margin-left: ${"48px"}; border: 1px solid ${"rgb(24, 144, 255)"}; box-shadow: ${"rgb(24 144 255 / 35%)"} 0px 2px 7px; width: 38px !important; height: 38px !important; font-size: 14px !important; transition: all 0.66s ease; }
      `}</style>
    </div>
  );
};

export default ChatHeader;
