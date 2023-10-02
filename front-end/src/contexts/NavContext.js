import React, { createContext, useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";

export const NavContext = createContext();

const NavContextProvider = ({ children }) => {
  // State
  const [active, setActive] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    if (active === 0) navigate("/");
    else if (active === 1) navigate("/profile");
    else if (active === 3) navigate("/profile");
  }, [active]);

  const changeActive = (state) => {
    setActive(state);
  };

  // context data
  const navContextData = {
    active,
    setActive,
    changeActive,
  };

  // return
  return (
    <NavContext.Provider value={navContextData}>{children}</NavContext.Provider>
  );
};

export default NavContextProvider;
