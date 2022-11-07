import React, { useCallback, useEffect, useState } from 'react';
import axios from "axios";
import { useDropzone } from 'react-dropzone';

import { GrEdit } from 'react-icons/gr';
import { BiLogOutCircle, BiDownArrowCircle } from "react-icons/bi";
import { FcCancel, FcPlus } from "react-icons/fc";

import { useStateContext } from '../context/ContextProvider';

const UserProfile = () => {
    const { details, userProfile, setProfile, setState, setStatus } = useStateContext();

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

    const [file, setFile] = useState("");

    const onDrop = useCallback((acceptedFiles) => {
        const files = acceptedFiles[0];
        setFile(files);
        //const previewUrl = URL.createObjectURL(files);
    });

    const [setter, Set] = useState(true);

    const { getRootProps, getInputProps, isDragActive } = useDropzone({
        multiple: false,
        onDrop,
    }
    );

    const [picture, setPicture] = useState(false);
    const [proPic, setImg] = useState(require("../data/default_IMG.png"));

    const updatePicture = async (event) => {
        event.preventDefault()
        setPicture(() => !picture)
    }

    const uploadImg = async (event) => {
        event.preventDefault();
        const formData = new FormData();
        if (file == "") {
            alert("No image uploaded")
        }
        else {
            formData.append('userName', details["loginName"]);
            formData.append('imgDir', details["img"]);
            formData.append('imgFile', file);
            const API_URL = "http://localhost:8080/updatePicture";
            const response = axios.post(API_URL, formData).then(async (res) => {
                if (res.data.err) {
                    setPicture(false);
                    alert(res.data.message);
                    setProfile({
                        imgLoc: "default_IMG",
                    });
                }
                else {
                    setPicture(false);
                    alert(res.data.message);
                    setProfile(res.data);
                }
                setStatus(true);
            });
        }
        setStatus(true);
    }

    const updatePass = async (event) => {
        event.preventDefault();
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
        event.preventDefault();
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
        <div className="absolute p-3 right-0 z-10 w-auto origin-top-right rounded-md bg-light-gray shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none" role="menu" aria-orientation="vertical" aria-labelledby="menu-button" tabindex="-1" >
            <img src={require(`../data/${userProfile["imgLoc"]}.png`)} alt={"Profile Picture"} className="rounded-full w-52 h-52 self-center" />
            <GrEdit className='absolute item-center w-5 h-5 hover:scale-110 top-5 right-20' title="New Picture" onClick={updatePicture} />
            {
                picture ? (<div>
                    {/* MODAL FOR Deleting TICKET */}
                    <div className="   bg-zinc-200 opacity-80 fixed inset-0 z-50   ">

                        <div className="flex h-screen justify-center items-center ">

                            <div className="bg-black py-12 px-24 border-4 border-black rounded-xl ">

                                <div className='gap-5'>
                                    <div {...getRootProps()} className=' w-auto h-auto gap-10'>
                                        Upload Image
                                        <div className='items-center p-10 border-green-100 text-gray-200 outline-dashed h-1/4 text-center relative justify-between gap-4' >
                                            <input {...getInputProps()} />
                                            {
                                                isDragActive ?
                                                    <p>Drop the Image .PNG here ...</p> :
                                                    <p>Drag 'n' drop image here, or click to select files</p>
                                            }

                                        </div>

                                    </div>
                                    <div className='flex gap-20'>
                                        <button className=" rounded px-4 py-2 text-white  bg-green-600 hover:bg-green-700" onClick={uploadImg}>
                                            Update
                                        </button>
                                        <button onClick={updatePicture} className=" rounded px-4 py-2 text-white  bg-red-600 hover:bg-red-700">
                                            Cancel
                                        </button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    {/* END OF MODAL */}
                </div>) : (<div />)
            }
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