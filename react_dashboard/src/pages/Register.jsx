import React, { Component, useState } from 'react';
import axios from "axios";
import { BsCloudArrowDown } from "react-icons/bs";
import { useStateContext } from '../context/ContextProvider';

const Register = () => {
    //const refresh = window.location.reload(false);
    const { setStatus, setState } = useStateContext();

    const login = () => {
        setStatus(true);
    }
    const [homepage, setHomePage] = useState(false);

    const [user, setUser] = useState({
        name: "", surname: "",
        email: "",
        contact: "",
        password: "",
        confirm: "",
    })

    const [lasNumber, setLasNumber] = useState("");
    const [registered, setRegistion] = useState(false);
    const [error, setError] = useState("");
    const [dupContact, setDupCon] = useState(true);
    const [dupEmail, setDupEm] = useState(true);

    const userDetails = event => {
        event.preventDefault()        
        setHomePage(false);
        if (user.name === "" || user.surname === "" || user.email === "" || user.contact === ""
            || user.password === "" || user.confirm === "") {
            alert("File in all fields");
        }
        else {
            setRegistion(true);
        }           
    }
    const registerUser = event => {
        event.preventDefault() 
        if (user.name === "" || user.surname === "" || user.email === "" || user.contact === ""
            || user.password === "" || user.confirm === "") {
            alert("File in all fields");
        }
        else if (user.password !== user.confirm) {
            alert("Passwords do not match. Try again.")
        }
        else {
            const API_URL = "http://localhost:8080/Register/all";
            var uniqueID = 1;
            const response = axios.get(API_URL).then(res => {
                const users = res.data;
                if (users === null) {
                    alert("User file storage directory could not be created");
                }
                else {
                    for (let index = 0; index < users.length; index++) {
                        let details = users[index];
                        console.log("checking1");
                        if (details.email === user.email) {
                            alert("Email address already exists");
                            setRegistion(false);
                            setDupEm(false);
                            setDupCon(false);
                            setHomePage(false);
                            break;
                        }
                        if (details.contact === user.contact) {
                            alert("Contact number already exists");
                            setRegistion(false);
                            setDupEm(false);
                            setHomePage(false);
                            setDupCon(false);
                            break;
                        }
                    }
                    if (dupEmail || dupContact) {
                        const formData = new FormData();
                        formData.append('lasNumbr', (Number(users.length) + 1));
                        formData.append('name', user.name);
                        formData.append('surname', user.surname);
                        formData.append('email', user.email);
                        formData.append('contact', user.contact);
                        formData.append('password', user.password);
                        const API_URL = "http://localhost:8080/Register/user";
                        const request = axios.post(API_URL, formData).then(res => {
                            if (res.data.userAdded) {
                                setLasNumber(res.data.lasNumber);
                                setState(res.data.userAdd);
                                setHomePage(true);
                            }
                        });
                    }
                    else {
                        setRegistion(false);
                        setDupEm(true);
                        setDupCon(true);
                        setHomePage(false);
                    }
                }
            });

        }
    }


    return (
        <div className='w-fit p-2 h-fit items-center'>
            <form className=' p-5 gap-5 bg-white' onSubmit={userDetails} >
                <h3>Register</h3>

                <div className="form-group">
                    <label>First name</label><br />
                    <input type="text" name='name' required className="form-control" placeholder="First name" onChange={event => setUser({ ...user, name: event.target.value })} value={user.name} />
                </div>

                <div className="form-group">
                    <label>Last name</label><br />
                    <input type="text" name='surname' required className="form-control" placeholder="Last name" onChange={event => setUser({ ...user, surname: event.target.value })} value={user.surname} />
                </div>

                <div className="form-group">
                    <label>Email</label><br />
                    <input type="email" name='email' required className="form-control" placeholder="Enter email" onChange={event => setUser({ ...user, email: event.target.value })} value={user.email} />
                </div>

                <div className="form-group">
                    <label>Phone number</label><br />
                    <input type="text" name='phone' required className="form-control" placeholder="0 ..." onChange={event => setUser({ ...user, contact: event.target.value })} value={user.contact} />
                </div>

                <div className="form-group">
                    <label>Password</label><br />
                    <input type="password" name='password' required className="form-control" placeholder="Enter password" onChange={event => setUser({ ...user, password: event.target.value })} value={user.password} />
                </div>

                <div className="form-group">
                    <label>Confirm Password</label><br />
                    <input type="password" name='confirm' required className="form-control" placeholder="Confirm password" onChange={event => setUser({ ...user, confirm: event.target.value })} value={user.confirm} />
                </div> 
                <button type='submit' className='w-0' />
            </form>
            <button onClick={registerUser} />
                {
                    registered ? (<div className="">
                        {
                            homepage ? (<div>
                                {/* MODAL FOR SUCCESSFUL Login */}
                                <div className="   bg-zinc-200 opacity-80 fixed inset-0 z-50   ">

                                    <div className="flex h-screen justify-center items-center ">

                                        <div className="flex-col justify-center  bg-white py-12 px-24 border-4 border-sky-500 rounded-xl ">

                                            <div className=" text-lg  text-zinc-600   mb-10" >
                                                <p> Please keep the below LAS number safe:</p><br />
                                                <p className="font-black"> {lasNumber}</p> <br />
                                                <p>NOTE: Use above LAS number for further login</p>

                                            </div>
                                            <div className="flex">
                                                <a href='/'>
                                                    <button onClick={login} className=" rounded px-4 py-2 text-white  bg-green-400 ">Understood!</button>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                                {/* END OF MODAL */}
                        </div>) : (<div> <button type="button" onClick={registerUser} className="flex px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">
                                Register <BsCloudArrowDown className='text-centre w-8' />
                            </button> </div>

                            )
                        }

                    </div>) : (<div className=" text-gray-500 text-right">
                        <p> Press Enter once complete </p>
                    </div>
                    )
            }

            <p className=" text-gray-500 text-right">
                Already registered <a href="/">log in?</a>
            </p>


        </div>
    )
}

export default Register