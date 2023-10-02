import { useState, useContext } from "react";
import * as React from "react";
import TextInput from "../components/TextInput";
import PhotoInput from "../components/PhotoInput";
import Button from "../components/Button";
import { EditOutlined } from "@ant-design/icons";
import Popover from "@mui/material/Popover";
import PopupState, { bindTrigger, bindPopover } from "material-ui-popup-state";
import axios from "axios";
import AuthContext from "../contexts/AuthContext";

export default function PopoverPopupState() {
  const [bio, setBio] = useState("");
  const [username, setUsername] = useState("");
  const [status, setStatus] = useState("");
  const [avatar, setAvatar] = useState(undefined);

  // const { mainUser } = useContext(AuthContext);

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .put("http://54.251.83.192:9001/users/5", {
        username: username,
        avatar:
          "http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcQgklhgD0zVQoATeorXmrSJ1JBDH9YmkG9OLdgmJ04C5uUNIADhmQLPIUFw98w0y-QSXVLSRM3suAiJhzxSjqabwT5FahrMOsKFMLAJodBf",
        status: status,
      })
      .then((response) => {});
    axios
      .put("http://54.251.83.192:9001/api/profile/update/5", {
        images: [],
        bio: bio,
      })
      .then((response) => {
        window.location.reload(false);
      });
  };

  return (
    <PopupState variant='popover' popupId='demo-popup-popover'>
      {(popupState) => (
        <div>
          <span className='text-teal-500' style={styles} {...bindTrigger(popupState)}>
            <EditOutlined className=' cursor-pointer text-2xl' />
          </span>
          <Popover
            {...bindPopover(popupState)}
            anchorOrigin={{
              vertical: "bottom",
              horizontal: "left",
            }}
            // transformOrigin={{
            //   vertical: "bottom",
            //   horizontal: "left",
            // }}
          >
            <div style={styles.formContainerStyle}>
              <form onSubmit={handleSubmit}>
                <TextInput
                  label='Username'
                  name='email'
                  placeholder='Edit username'
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
                <TextInput
                  label='Status'
                  name='status'
                  placeholder='Edit status'
                  value={status}
                  onChange={(e) => setStatus(e.target.value)}
                />
                <TextInput
                  label='Bio'
                  name='bio'
                  placeholder='Edit bio'
                  value={bio}
                  onChange={(e) => setBio(e.target.value)}
                />
                <PhotoInput
                  label='Profile picture'
                  name='avatar'
                  id='avatar-picker'
                  style={{ width: "calc(50% - 6px)" }}
                  onChange={(e) => {
                    if (e.target.files !== null) {
                      setAvatar(e.target.files[0]);
                    }
                  }}
                />
                <Button type='submit'>Submit</Button>
              </form>
            </div>
          </Popover>
        </div>
      )}
    </PopupState>
  );
}

const styles = {
  formContainerStyle: {
    width: "100%",
    maxWidth: "650px",
    padding: "36px 72px",
  },
  titleStyle: {
    fontSize: "24px",
    fontFamily: "VisbyRoundCF-Heavy",
    letterSpacing: "0.5px",
    color: "white",
    paddingBottom: "11vw",
  },
  icon: {
    cursor: "pointer",
  },
};
