import React, { useContext, useState, useEffect } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { useAuthState } from "react-firebase-hooks/auth";
import { useNavigate } from "react-router-dom";
import TextInput from "../components/TextInput";
import PhotoInput from "../components/PhotoInput";
import Button from "../components/Button";
import Link from "../components/Link";
import valley from "../assets/valley.jpeg";
import { auth, registerWithEmailAndPassword } from "../firebase";
import axios from "axios";

const SignUpForm = () => {
  // State
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [avatar, setAvatar] = useState("");
  const [user, loading, error] = useAuthState(auth);
  // Hooks

  const navigate = useNavigate();

  const backgroundImage = {
    backgroundImage: `url(${valley})`, // Here due to variable
  };

  const register = (event) => {
    event.preventDefault();
    if (!username || avatar === undefined) alert("Please fill in all fields");
    else {
      registerWithEmailAndPassword(`${username}`, email, password);
      createUser();
    }
    // navigate("/login");
  };

  useEffect(() => {
    if (loading) return;
    // if (user) navigate("/login");
  }, [user, loading]);

  const createUser = () => {
    // POST request using axios with error handling
    const newUser = {
      username: username,
      email: email,
      password: password,
      avatar: "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
      status: "insert status",
      isOnline: false,
    };
    const headers = {};
    // headers.append("Access-Control-Allow-Origin", "http://54.251.83.192:3000");
    // headers.append("Access-Control-Allow-Credentials", "true");
    axios
      .post("http://54.251.83.192:9001/users/create", newUser)
      .then((response) => console.log(response))
      .catch((error) => {
        this.setState({ errorMessage: error.message });
        console.error("Cannot create new user!", error);
      });
  };

  return (
    <div className='background-image' style={backgroundImage}>
      <div className='background-gradient-dark'>
        <div style={styles.formContainerStyle}>
          <div style={styles.titleStyle}>Chatify</div>
          <div className='form-title'>Create an account</div>

          <div className='form-subtitle'>
            Already a member?{" "}
            <Link
              onClick={() => {
                navigate("/login");
              }}
            >
              Log in
            </Link>
          </div>

          <form onSubmit={register}>
            <TextInput
              label='Username'
              name='username'
              placeholder='Username'
              style={{ width: "calc(50% - 6px)" }}
              onChange={(e) => setUsername(e.target.value)}
            />

            <TextInput
              label='Email'
              name='email'
              placeholder='Billie@rmit.edu.vn'
              style={{ width: "calc(50% - 6px)", float: "right" }}
              onChange={(e) => setEmail(e.target.value)}
            />

            <TextInput
              label='Password'
              name='password'
              placeholder='********'
              type='password'
              style={{
                width: "calc(50% - 6px)",
                float: "right",
              }}
              onChange={(e) => setPassword(e.target.value)}
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

            <Button
              type='submit'
              style={{
                width: "100%",
              }}
            >
              Sign Up
            </Button>
          </form>
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
};

export default SignUpForm;
