import { createContext, useState, useEffect } from "react";
import axios from "axios";

export const ChatsContext = createContext();

const ChatsContextProvider = ({ children }) => {
  // State
  const [convo, setConvo] = useState([]);

  useEffect(() => {
    axios
      .get(
        `http://54.251.83.192:9001/conversations/getConversationByParticipantId/${localStorage.getItem(
          "id"
        )}?page=0&size=10`
      )
      .then((response) => {
        console.log(response.data.data.content);
        setConvo(response.data.data.content);
      });
  }, []);

  const changeConvo = (data) => {
    setConvo(data);
  };

  // context data
  const chatsContextData = {
    changeConvo,
    convo,
  };

  return <ChatsContext.Provider value={chatsContextData}>{children}</ChatsContext.Provider>;
};

export default ChatsContextProvider;
