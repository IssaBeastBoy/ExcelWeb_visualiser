import React, { Component, useState } from 'react';
import avatar from '../data/default_IMG.png';
import loginImage from '../data/login_I.png';
import axios from "axios";
import { useStateContext } from '../context/ContextProvider';
import { RangeSelector } from "../components";
const Login = () => {
    const { setStatus, setState, login, setProfile } = useStateContext();

    const [user, setLogin] = useState({
        name: "",
        password: "",
    })

    const submit = event => {
        event.preventDefault()
        if (user.name === "" || user.password === "") {
            alert("Ensure both fields are filled in.");
        }
    }

    const loginButton = async (event) => {
        if (user.name === "" || user.password === "") {
            alert("Ensure both fields are filled in.");
        }
        else {
            let userName = user.name.toLowerCase();
            const getUniqueID = userName.split('r');
            let uniqueID = getUniqueID[1];
            if (getUniqueID[0] == "vsuse") {
                const API_URL = "http://localhost:8080/login/" + getUniqueID[1];
            const response = axios.get(API_URL).then(async (response) => {
                const users = response.data;
                setState(users);
                if (users.loginName === undefined || uniqueID == "") {
                    alert("Error: Wrong login details. \n Enter correct details.");
                }
                else if (uniqueID === `${users.loginName}` && user.password === users.password) {
                    setStatus(true);                    
                }
                else {
                    alert("Incorrect password or username");
                }
            });
            }
            else {
                alert("Wrong user name or password!")
            }
        }
    }

    return (
        < div className='w-fit h-fit gap-5 p-5' >
            <form className='bg-white' onSubmit={submit}>

                <div>
                    <img src={avatar} className=" rounded-full w-1/2 h-1/2" />
                </div>

                <div className="form-group">
                    <label>User name</label> <br />
                    <input type="text" className="form-control" required placeholder="Enter VSUser..." onChange={event => setLogin({ ...user, name: event.target.value })} value={user.name} />
                </div>

                <div className="form-group">
                    <label>Password</label> <br />
                    <input type="password" className="form-control" required placeholder="Enter password" onChange={event => setLogin({ ...user, password: event.target.value })} value={user.password} />
                </div>

                <button type="submit" className='btn m-2 flex w-fit justify-left hover:scale-125' alt="Sign In">
                    <img src={loginImage} className='w-8 h-8' onClick={loginButton} />
                </button>
                <div className='flex gap-5'>
                    <p className="forgot-password text-left pt-2.5">
                        <a href="/Register">SignUp</a>
                    </p>
                    <p className="text-right text-gray-500 pt-2.5 ">
                        Forgot <a href="#">password?</a>
                    </p>
                </div>

            </form>
        </div >

    )
}

export default Login