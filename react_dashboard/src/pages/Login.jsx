import React, { Component, useState } from 'react';
import avatar from '../data/default_IMG.png';
import loginImage from '../data/login_I.png';
import axios from "axios";
import { useStateContext } from '../context/ContextProvider';
import { RangeSelector } from "../components";
const Login = () => {
    const { setStatus, setState, login } = useStateContext();

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

    const loginButton = event => {
        if (user.name === "" || user.password === "") {
            alert("Ensure both fields are filled in.");
        }
        else {
            const getUniqueID = user.name.split('');
            let uniqueID = "";
            let leadZeros = true;
            let digits = 0;
            for (let index = 3; index < getUniqueID.length; index++) {
                if (getUniqueID[index] !== "0" && leadZeros) {
                    leadZeros = false;
                }
                if (!leadZeros) {
                    uniqueID = uniqueID + getUniqueID[index];
                }
                digits++;
            }
            //console.log(uniqueID);
            const API_URL = "http://localhost:8080/login/" + uniqueID;
            const response = axios.get(API_URL).then(res => {
                const users = res.data;
                setState(users);
                //console.log(digits);
                // console.log("uniqueID");
                // console.log(uniqueID);
                // console.log("details.loginName");
                // console.log(`${details.loginName}`);
                // console.log("user.password");
                // console.log(user.password);
                // console.log("details.password");
                // console.log(details.password);
                // console.log(details);
                if (users.loginName === undefined || digits !== 5) {
                    alert("User does not exist.");
                }
                else if (uniqueID === `${users.loginName}` && user.password === users.password) {
                    setStatus(true);
                }
                else {
                    alert("Incorrect password or username");
                }
            });
        }
    }

    return (
        < div className='w-fit h-fit gap-5 p-5' >
            <form className='bg-white' onSubmit={submit}>

                <div>
                    <img src={avatar} className=" rounded-full w-1/2 h-1/2" />
                </div>

                <div className="form-group">
                    <label>LAS number</label> <br />
                    <input type="text" className="form-control" required placeholder="Enter LAS number" onChange={event => setLogin({ ...user, name: event.target.value })} value={user.name} />
                </div>

                <div className="form-group">
                    <label>Password</label> <br />
                    <input type="password" className="form-control" required placeholder="Enter password" onChange={event => setLogin({ ...user, password: event.target.value })} value={user.password} />
                </div>

                <button type="submit" className='btn flex w-fit justify-left' alt="Sign In">
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