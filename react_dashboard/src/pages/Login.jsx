import React, { Component } from 'react';
import avatar from '../data/default_IMG.png';
import login from '../data/login_I.png';


const Login = () => {
    return (
        <div className='w-fit h-fit gap-5 p-5'>
            <form className='bg-white'>

                <div>
                    <img src={avatar} className=" rounded-full w-1/2 h-1/2" />
                </div>

                <div className="form-group">
                    <label>LAS number</label> <br />
                    <input type="text" className="form-control" placeholder="Enter LAS number" />
                </div>

                <div className="form-group">
                    <label>Password</label> <br />
                    <input type="password" className="form-control" placeholder="Enter password" />
                </div>

                <button type="submit" className='btn flex w-fit justify-left' alt="Sign In">
                    <img src={login} className='w-8 h-8' />

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
        </div>
    )
}

export default Login