import { useEffect } from "react";
import * as React from "react";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Popover from "@mui/material/Popover";
import PopupState, { bindTrigger, bindPopover } from "material-ui-popup-state";
import { Image, Divider, Skeleton } from "antd";
import deved from "../assets/dev-ed-wave.png";
import avatar from "../assets/avatar.png";
import code from "../assets/code.png";
import design from "../assets/design.png";
import consulting from "../assets/consulting.png";
import CusCarousel from "../components/CusCarousel";
import web1 from "../assets/web1.png";
import web2 from "../assets/web2.png";
import web3 from "../assets/web3.png";
import web4 from "../assets/web4.png";
import web5 from "../assets/web5.png";
import web6 from "../assets/web6.png";

export default function PopoverPopupState(props) {
  return (
    <PopupState variant="popover" popupId="demo-popup-popover">
      {(popupState) => (
        <div>
          {/* <Button variant="contained" {...bindTrigger(popupState)}>
            Open Popover
          </Button> */}
          <span
            className="text-teal-500"
            style={styles}
            {...bindTrigger(popupState)}
          >
            See more...
          </span>
          <Popover
            {...bindPopover(popupState)}
            anchorOrigin={{
              vertical: "top",
              horizontal: "left",
            }}
            // transformOrigin={{
            //   vertical: "top",
            //   horizontal: "center",
            // }}
          >
            <CusCarousel props={props.type} />
            {/* <div className="lg:flex gap-20">
              <div className="text-center shadow-lg p-10 rounded-xl my-10  dark:bg-white flex-1">
                <Image src={avatar} width={100} height={100} />
                <h3 className="text-lg font-medium pt-8 pb-2  ">Flea</h3>
                <p className="text-teal-500 py-1">View Profile</p>
              </div>
              <div className="text-center shadow-lg p-10 rounded-xl my-10 dark:bg-white flex-1">
                <Image src={avatar} width={100} height={100} />
                <h3 className="text-lg font-medium pt-8 pb-2  ">Josh</h3>
                <p className="text-teal-500 py-1">View Profile</p>
              </div>
              <div className="text-center shadow-lg p-10 rounded-xl my-10 dark:bg-white flex-1">
                <Image src={design} width={100} height={100} />
                <h3 className="text-lg font-medium pt-8 pb-2  ">Chad</h3>
                <p className="text-teal-500 py-1">View Profile</p>
              </div>
              <div className="text-center shadow-lg p-10 rounded-xl my-10 dark:bg-white flex-1">
                <Image src={design} width={100} height={100} />
                <h3 className="text-lg font-medium pt-8 pb-2  ">Chad</h3>
                <p className="text-teal-500 py-1">View Profile</p>
              </div>
              <div className="text-center shadow-lg p-10 rounded-xl my-10 dark:bg-white flex-1">
                <Image src={design} width={100} height={100} />
                <h3 className="text-lg font-medium pt-8 pb-2  ">Chad</h3>
                <p className="text-teal-500 py-1">View Profile</p>
              </div>
            </div> */}
          </Popover>
        </div>
      )}
    </PopupState>
  );
}

const styles = {
  cursor: "pointer",
};
