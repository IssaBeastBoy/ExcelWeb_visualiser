import React, { useEffect, useState } from 'react';
import axios from "axios";

import { GrEdit } from 'react-icons/gr';
import { BiLogOutCircle, BiDownArrowCircle } from "react-icons/bi";
import { FcCancel, FcPlus } from "react-icons/fc";

import { useStateContext } from '../context/ContextProvider';

const UserProfile = () => {
    const { details, userProfile, setProfile, setState } = useStateContext();

    const [fields, setFields] = useState(
        {
            status: '',
            email: '',
            contact: '',

        }
    );

    const [pass, setReset] = useState(
        {
            old: '',
            new: '',

        }
    );

    const [newPass, setPass] = useState(false);

    const updatePass = async (event) => {
        if (details.password == pass.old) {
            const formData = new FormData();
            formData.append('userName', details["loginName"]);
            formData.append('newPassword', pass.new);
            const API_URL = "http://localhost:8080/updatePassword";
            const response = axios.post(API_URL, formData).then(async (res) => {
                setState(res.data);
                setPass(false);
                alert("Password successfully updated");
            })
        }
        else {
            alert("Password do not match. Please try again.")
        }
    };

    const saveChanges = async (event) => {
        if (fields.contact == "" && fields.email == "" && fields.status == "") {
            alert("No updated performed")
        }
        else {
            const formData = new FormData();
            formData.append('userName', details["loginName"]);
            formData.append('email', pass.new);
            formData.append('contact', pass.new);
            formData.append('status', pass.new);
            const API_URL = "http://localhost:8080/updateProfile";
            const response = axios.post(API_URL, formData).then(async (res) => {
                setState(res.data);
                alert("Profile updated. ");
            })
        }

    };

    const logout = async (event) => {
        const formData = new FormData();
        formData.append('userName', details["loginName"]);
        formData.append('status', 'Offline');
        const API_URL = "http://localhost:8080/logout";
        const response = axios.post(API_URL, formData).then(async (res) => {
            window.location = ('http://localhost:3000/')
        })
    }

    return (
        <div className="absolute  p-3 right-0 z-10 w-auto origin-top-right rounded-md bg-light-gray shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none" role="menu" aria-orientation="vertical" aria-labelledby="menu-button" tabindex="-1" >
            <img src={require(`../data/${userProfile["imgLoc"]}.png`)} alt={"Profile Picture"} className="rounded-full w-52 h-52 self-center" />
            <h3 className='font-semibold text-lg p-2 text-center'> {details.name} {details.surname}</h3>
            <div className='md-2 gap-2 text-sm'>
                <span className='flex'>
                    <label>Status: </label>
                    <select className='bg-light-gray' onChange={event => setFields({ ...fields, status: event.target.value })}>
                        <option name="" selected disabled hidden>{details.status}</option>
                        <option name="Away">Away</option>
                        <option name="Online">Online</option>
                        <option name="Offline">Offline</option>
                    </select>
                </span>
                <label> User name: {details.loginName}</label> <br />
                <span className='flex gap-1'>
                    <label> Email: </label>
                    <input type="email" className='bg-light-gray w-auto' onChange={event => setFields({ ...fields, email: event.target.value })} value={details.email} />
                </span>
                <span className='flex gap-1'>
                    <label> Contact: </label>
                    <input type="text" className='bg-light-gray w-auto' onChange={event => setFields({ ...fields, contact: event.target.value })} value={details.contact} />
                </span>
                <span >
                    {
                        newPass ? (<div>
                            <FcCancel className='scale-50 hover:scale-75' title="Close" onClick={() => setPass(!newPass)} />
                            <span className='text-base p-2'>
                                <label> Old Password: </label> <br />
                                <input type="password" className='bg-light-gray w-auto border' onChange={event => setReset({ ...pass, old: event.target.value })} value={pass.old} /> <br />
                                <label className='p-2'> New Password: </label> <br />
                                <span className='flex gap-5'>
                                    <input type="password" className='bg-light-gray w-auto border md-2' onChange={event => setReset({ ...pass, new: event.target.value })} value={pass.new} />
                                    <FcPlus className='scale-105 hover:scale-110' title="Update" onClick={updatePass} />
                                </span>
                            </span>
                        </div>) : (<div className='flex gap-1'>
                            <label> Password: </label>
                            <input type="password" className='bg-light-gray w-auto' value={details.password} readonly />
                            <GrEdit className='w-3 h-3 hover:scale-110' title="New Password" onClick={() => setPass(!newPass)} />
                        </div>)
                    }

                </span>
                <button className='flex gap-1 hover:bg-green-200 hover:scale-110 rounded'>
                    <BiDownArrowCircle className='scale-105' onClick={saveChanges} /> Save Changes
                </button>
                <button className='flex gap-1 hover:bg-red-200 hover:scale-110 rounded'>
                    <BiLogOutCircle className='scale-105' onClick={logout} /> Logout
                </button>
            </div>

        </div>
    )
}

export default UserProfile