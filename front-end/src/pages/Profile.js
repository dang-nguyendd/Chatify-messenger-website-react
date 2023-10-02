import {
  EditOutlined,
  MessageFilled,
  UserAddOutlined,
  UserDeleteOutlined,
} from "@ant-design/icons";
import { useQuery } from "react-query";

import { Input, Button, Select, Space, message } from "antd";

import { Image } from "antd";
import { useState, useEffect, useContext } from "react";
import deved from "../assets/dev-ed-wave.png";
import avatar from "../assets/avatar.png";
import code from "../assets/code.png";
import design from "../assets/design.png";
import consulting from "../assets/consulting.png";

import web1 from "../assets/web1.png";
import web2 from "../assets/web2.png";
import web3 from "../assets/web3.png";
import web4 from "../assets/web4.png";
import web5 from "../assets/web5.png";
import web6 from "../assets/web6.png";
import MainSidebar from "../components/MainSidebar";
import Popup from "../components/Popup";
import EditPopup from "../components/EditPopup";
import axios from "axios";
import { AuthContext } from "../contexts/AuthContext";
import { NavContext } from "../contexts/NavContext";

export default function Home() {
  const { Option } = Select;
  const [darkMode, setDarkMode] = useState(false);
  const [findUser, setFindUser] = useState("");
  const { active, changeActive } = useContext(NavContext);
  const {
    sndUser,
    mainUser,
    getSecondUser,
    isFriend,
    setIsFriend,
    friends,
    setFriends,
    pairId,
    setPairId,
    checkIsFriend,
  } = useContext(AuthContext);

  const getUserFriends = () => {
    axios
      .get(
        `http://54.251.83.192:9001/friends/getFriendOfUser/${localStorage.getItem(
          "id"
        )}?page=0&size=4`
      )
      .then((res) => {
        setFriends(res.data.data.content);
      });
  };

  const searchSubmit = () => {
    axios
      .get(`http://54.251.83.192:9001/friends/search/name?name=${findUser}&page=0&size=1`)
      .then((res) => {
        console.log(res.data);
        if (res.data.data) handleSelect(findUser);
        else message.info("Can't find user " + findUser);
      })
      .catch((error) => console.log(error));
  };

  const { data, error, isLoading } = useQuery("getUserFriends", getUserFriends);
  // Error and Loading states
  if (error) return <div>Request Failed</div>;
  if (isLoading) return <div>Loading...</div>;

  const handleSelect = (username) => {
    getSecondUser(username);
    changeActive(1);
    checkIsFriend(username);
  };

  const deleteFriend = () => {
    axios
      .delete(`http://54.251.83.192:9001/friends/removeFriend/${pairId}`)
      .then((res) => {
        console.log(res.data);
        setIsFriend(false);
      })
      .catch((error) => console.log(error));
  };

  const addFriend = () => {
    axios
      .post(`http://54.251.83.192:9001/friends/addFriend`, {
        id1: mainUser.id,
        id2: sndUser.id,
        isBlocked: false,
      })
      .then((res) => {
        setIsFriend(true);
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className={darkMode ? "dark" : ""}>
      <div
        style={{
          width: "6vw",
          height: "100%",
          position: "fixed",
          top: "0px",
          left: "0px",
          backgroundColor: "rgb(40,43,54)",
        }}
      >
        <MainSidebar />
      </div>
      <main className=' bg-white ml-12 px-10 dark:bg-gray-900 md:px-20 lg:px-40'>
        <section className='min-h-screen'>
          <nav className='py-10 mb-12 flex justify-between dark:text-white'>
            <h1 className='font-burtons text-xl' style={styles}>
              Chatify
            </h1>
            <ul className='flex items-center gap-6'>
              {active === 3 ? (
                <li>
                  <EditPopup />
                </li>
              ) : isFriend ? (
                <li>
                  <UserDeleteOutlined onClick={deleteFriend} className=' cursor-pointer text-2xl' />
                </li>
              ) : (
                <li>
                  <UserAddOutlined onClick={addFriend} className=' cursor-pointer text-2xl' />
                </li>
              )}
            </ul>
          </nav>
          <div className='text-center p-10 py-10'>
            <h2 className='text-5xl py-2 text-teal-600 font-medium dark:text-teal-400 md:text-6xl'>
              {active === 3
                ? mainUser
                  ? mainUser.username
                  : "Loading"
                : sndUser
                ? sndUser.username
                : "Loading"}
            </h2>
            <h3 className='text-2xl py-2 dark:text-white md:text-3xl'>
              {active === 3
                ? mainUser
                  ? mainUser.status
                  : "Loading"
                : sndUser
                ? sndUser.status
                : "Loading"}
            </h3>
            <div className='text-5xl flex justify-center gap-16 py-3 text-gray-600 dark:text-gray-400'></div>
            <div className='mx-auto bg-gradient-to-b from-teal-500 rounded-full w-80 h-80 relative overflow-hidden mt-20 md:h-96 md:w-96'>
              <Image
                src={
                  active === 3
                    ? mainUser
                      ? mainUser.avatar
                      : deved
                    : sndUser
                    ? sndUser.avatar
                    : deved
                }
                layout='fill'
                objectFit='cover'
              />
            </div>
          </div>
        </section>
        <section>
          <div>
            <h3 className='text-3xl py-1 dark:text-white '>My Bio</h3>
            <p className='text-md py-2 leading-8 text-gray-800 dark:text-gray-200'>
              {active === 3
                ? mainUser
                  ? mainUser.profile.bio
                  : "Empty"
                : sndUser
                ? sndUser.profile.bio
                : "Empty"}
            </p>
            {active === 3 ? (
              <div>
                <h3 className='text-3xl py-1 dark:text-white '>Search Users</h3>
                <br />
                <Space.Compact block size='large'>
                  <Input
                    style={{
                      width: "calc(100%)",
                    }}
                    value={findUser}
                    onChange={(e) => setFindUser(e.target.value)}
                    defaultValue=''
                  />
                  <Button onClick={searchSubmit} type='secondary'>
                    Search
                  </Button>
                </Space.Compact>
                <br />
              </div>
            ) : (
              ""
            )}
          </div>
          {active === 3 ? (
            <div>
              <h3 className='text-3xl py-1 dark:text-white '>My Friends</h3>

              <Popup type={"friends"} />

              <div className='lg:flex gap-10'>
                {friends.map((data) =>
                  data.users
                    .filter((user) => user.id !== mainUser.id)
                    .map((datum) => (
                      <div className='text-center shadow-lg p-10 rounded-xl my-10  dark:bg-white flex-1'>
                        <Image
                          className='mx-auto bg-gradient-to-b from-teal-500 rounded-full w-80 h-80 md:h-96 md:w-96'
                          src={datum.avatar}
                          width={100}
                          height={100}
                          layout='fill'
                          objectFit='cover'
                        />
                        <h3 className='text-lg font-medium pt-8 pb-2  '>{datum.username}</h3>
                        <p
                          className='text-teal-500 py-1'
                          style={styles}
                          onClick={() => {
                            handleSelect(datum.username);
                          }}
                        >
                          View Profile
                        </p>
                      </div>
                    ))
                )}
              </div>
            </div>
          ) : (
            ""
          )}
        </section>
        <section className='py-10'>
          <div>
            <h3 className='text-3xl py-1 dark:text-white '>My Gallery</h3>
            <Popup />
          </div>
          <div className='flex flex-col gap-10 py-10 lg:flex-row lg:flex-wrap'>
            <div className='basis-1/3 flex-1 '>
              <Image
                className='rounded-lg object-cover'
                width={"100%"}
                height={"100%"}
                layout='responsive'
                src={web1}
              />
            </div>
            <div className='basis-1/3 flex-1'>
              <Image
                className='rounded-lg object-cover'
                width={"100%"}
                height={"100%"}
                layout='responsive'
                src={web2}
              />
            </div>
            <div className='basis-1/3 flex-1'>
              <Image
                className='rounded-lg object-cover'
                width={"100%"}
                height={"100%"}
                layout='responsive'
                src={web3}
              />
            </div>
            <div className='basis-1/3 flex-1'>
              <Image
                className='rounded-lg object-cover'
                width={"100%"}
                height={"100%"}
                layout='responsive'
                src={web4}
              />
            </div>
            <div className='basis-1/3 flex-1'>
              <Image
                className='rounded-lg object-cover'
                width={"100%"}
                height={"100%"}
                layout='responsive'
                src={web5}
              />
            </div>
            <div className='basis-1/3 flex-1'>
              <Image
                className='rounded-lg object-cover'
                width={"100%"}
                height={"100%"}
                layout='responsive'
                src={web6}
              />
            </div>
          </div>
        </section>
      </main>
    </div>
  );
}

const styles = {
  cursor: "pointer",
};
