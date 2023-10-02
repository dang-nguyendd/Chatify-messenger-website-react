import React, { useState, useContext, useEffect } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";
import TextInput from "../components/TextInput";
import Button from "../components/Button";
import Link from "../components/Link";
import valley from "../assets/valley.jpeg";
import { NavContext } from "../contexts/NavContext";
import { GoogleOutlined, FacebookOutlined } from "@ant-design/icons";
//Authentication
import { auth, logInWithEmailAndPassword, signInWithGoogle, signInWithFacebook } from "../firebase";
import { getAuth } from "firebase/auth";
import { useAuthState } from "react-firebase-hooks/auth";
import "../theme.css";
import axios from "axios";

const LogInForm = () => {
  // State
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  // Hooks
  const [user, loading, error] = useAuthState(auth);
  const navigate = useNavigate();
  const { changeActive } = useContext(NavContext);
  const backgroundImage = {
    backgroundImage: `url(${valley})`, // Here due to variable
  };

  useEffect(() => {
    if (loading) {
      // maybe trigger a loading screen
      return;
    }
    if (user) {
      localStorage.setItem("user", getAuth().currentUser.email);
      axios
        .get(
          `http://54.251.83.192:9001/friends/search?email=${
            getAuth().currentUser.email
          }&page=0&size=10`
        )
        .then((response) => {
          console.log("Data");
          console.log(response.data.data);
          //filter out id
          response.data.data.map((data) => {
            if (data.email === getAuth().currentUser.email) {
              localStorage.setItem("id", data.id);
              localStorage.setItem("username", data.username);
            }
          });
        });
      changeActive(3);
    }
  }, [user, loading]);

  const toggleSignup = () => {
    navigate("/signup");
  };

  const changeEmail = (event) => {
    setEmail(event.target.value);
  };
  const changePassword = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (email !== "" && password !== "") {
      logInWithEmailAndPassword(email, password);
      console.log(email);
      console.log(password);
    }
  };
  const handleClickGoogle = (event) => {
    signInWithGoogle();
    console.log("GreatGG!!");
  };
  const handleClickFacebook = (event) => {
    signInWithFacebook();
    console.log("GreatGB!!");
  };

  return (
    <div className='background-image' style={backgroundImage}>
      <div className='background-gradient-dark'>
        <div style={styles.formContainerStyle}>
          <div style={styles.titleStyle}>Chatify</div>
          <div className='form-title'>Welcome Back</div>
          <div className='form-subtitle'>
            New here? <Link onClick={toggleSignup}>Sign Up</Link>
          </div>
          <form onSubmit={handleSubmit}>
            <TextInput
              label='Email'
              name='email'
              placeholder='email'
              value={email}
              onChange={changeEmail}
            />

            <TextInput
              label='Password'
              name='password'
              placeholder='password'
              type='password'
              value={password}
              onChange={changePassword}
            />

            <Button type='submit'>Log In</Button>
          </form>
          <div className='form-subtitle'>Or log in with</div>
          <ul className='flex items-center gap-2'>
            <li>
              <GoogleOutlined
                style={styles.icon}
                onClick={handleClickGoogle}
                className='text-2xl cursor-pointer '
              />
            </li>
            <li>
              <FacebookOutlined
                style={styles.icon}
                onClick={handleClickFacebook}
                className='text-2xl cursor-pointer '
              />
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
};

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

export default LogInForm;
