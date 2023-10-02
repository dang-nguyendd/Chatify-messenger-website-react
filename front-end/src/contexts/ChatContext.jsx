import { createContext, useState } from "react";
import { AuthContext } from "./AuthContext";
//Import sockjs in reactstomp
import SockJsClient from "react-stomp";

// import { io } from "socket.io-client";
//Import socket io

export const ChatContext = createContext();

// export const SERVER_URL = 'http://54.251.83.192:9001/ws';

const ChatContextProvider = ({ children }) => {
  // Join socketio endpoint
  // Can set convertsation here
  const [conversation, setConversation] = useState(null);

  //Connect to the server

  const [page, setPage] = useState(0);
  //Use socketio
  // const socket = io(
  //   `ws://localhost:9000/?${conversation}&page=${page}&size=10`
  // );
  // socket.on("get_message", (data) => {
  //   console.log("Connected to socketio");
  // });

  // State
  const [currentUser, setCurrentUser] = useState({
    username: "Billie",
    photo:
      "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Billie_Eilish_2019_by_Glenn_Francis_%28cropped%29_2.jpg/1200px-Billie_Eilish_2019_by_Glenn_Francis_%28cropped%29_2.jpg",
  });
  const [messages, setMessages] = useState([
    {
      id: 0,
      content: "Hello",
      sender: "Billie",
      img: "https://images.unsplash.com/photo-1484591974057-265bb767ef71?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
      timestamp: new Date().toLocaleString(),
    },
    {
      id: 1,
      content: "Hi",
      sender: "John",
      img: "",
      timestamp: new Date().toLocaleString(),
    },
    {
      id: 2,
      content: "Bonjour",
      sender: "Billie",
      img: "",
      timestamp: new Date().toLocaleString(),
    },
  ]);

  const addMessage = (message) => {
    setMessages([...messages, message]);
  };

  // context data
  const chatContextData = {
    conversation,
    setConversation,
    currentUser,
    messages,
    addMessage,
    setMessages,
  };

  return <ChatContext.Provider value={chatContextData}>{children}</ChatContext.Provider>;
};

export default ChatContextProvider;
