import React, { createContext, useEffect, useState } from "react";
import axios from "axios";
import { useQuery } from "react-query";
export const AuthContext = createContext();

const AuthContextProvider = ({ children }) => {
  // State
  const [mainUser, setMainUser] = useState();
  const [sndUser, setSndUser] = useState();
  const [friends, setFriends] = useState([]);
  const [isFriend, setIsFriend] = useState(false);
  const [pairId, setPairId] = useState();

  // const getUserFriends = (id) => {
  //   axios
  //     .get(`http://54.251.83.192:9001/friends/getFriendOfUser/${id}?page=0&size=4`)
  //     .then((res) => {
  //       setFriends(res.data.data.content);
  //     });
  // };

  const getMainUser = () => {
    axios
      .get(
        `http://54.251.83.192:9001/friends/search?email=${localStorage.getItem(
          "user"
        )}&page=0&size=1`
      )
      .then((response) => {
        console.log(response.data.data);
        setMainUser(response.data.data[0]);
      });
  };

  const { data, error, isLoading } = useQuery("getMainUser", getMainUser);
  // Error and Loading states
  if (error) return <div>Request Failed</div>;
  if (isLoading) return <div>Loading...</div>;

  // useEffect(() => {

  // }, [mainUser]);

  const checkIsFriend = (user) => {
    friends.map((pair) =>
      pair.users
        .filter((data) => data.id !== mainUser.id)
        .map((data) => {
          if (data.id === user.id) {
            setIsFriend(true);
            setPairId(pair.id);
            return;
          } else if (friends[friends.length - 1] === pair) {
            setIsFriend(false);
          }
        })
    );
  };

  const getSecondUser = (username) => {
    axios
      .get(`http://54.251.83.192:9001/friends/search/name?name=${username}&page=0&size=1`)
      .then((response) => {
        setSndUser(response.data.data[0]);
        checkIsFriend(response.data.data[0]);
      });
  };

  // context data
  const authContextData = {
    mainUser,
    setMainUser,
    setSndUser,
    sndUser,
    getSecondUser,
    isFriend,
    setIsFriend,
    friends,
    setFriends,
    pairId,
    setPairId,
    checkIsFriend,
  };

  // return
  return <AuthContext.Provider value={authContextData}>{children}</AuthContext.Provider>;
};

export default AuthContextProvider;
