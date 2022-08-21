import React, { Component, useState } from 'react';


const Register = () => {
    //const refresh = window.location.reload(false);

    const [user, setUser] = useState({
        name: "", surname: "",
        email: "",
        phone: "",
        password: "",
        confirm: ""
    })
    const [error, setError] = useState("");
    const newUser = event => {
        event.preventDefault()
        if (user.confirm == user.password) {

        }
        else {
            alert("Passwords do not match. Please try again.");

        }
    }
    return (
        <div className='w-fit h-fit items-center'>
            <form className=' p-5 gap-5 bg-white' onSubmit={newUser} >
                <h3>Register</h3>

                <div className="form-group">
                    <label>First name</label>
                    <input type="text" name='name' className="form-control" placeholder="First name" onChange={event => setUser({ ...user, name: event.target.value })} value={user.name} />
                </div>

                <div className="form-group">
                    <label>Last name</label>
                    <input type="text" name='surname' className="form-control" placeholder="Last name" onChange={event => setUser({ ...user, surname: event.target.value })} value={user.surname} />
                </div>

                <div className="form-group">
                    <label>Email</label>
                    <input type="email" name='email' className="form-control" placeholder="Enter email" onChange={event => setUser({ ...user, email: event.target.value })} value={user.email} />
                </div>

                <div className="form-group">
                    <label>Phone number</label>
                    <input type="text" name='phone' className="form-control" placeholder="+27 ..." onChange={event => setUser({ ...user, phone: event.target.value })} value={user.phone} />
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" name='password' className="form-control" placeholder="Enter password" onChange={event => setUser({ ...user, password: event.target.value })} value={user.password} />
                </div>

                <div className="form-group">
                    <label>Confirm Password</label>
                    <input type="password" name='confirm' className="form-control" placeholder="Confirm password" onChange={event => setUser({ ...user, confirm: event.target.value })} value={user.confirm} />
                </div>

                <button type="submit" className="btn btn-dark btn-xl text-justify btn-block">Register</button>
                <p className=" text-gray-500 pt-2.5 text-right">
                    Already registered <a href="/">log in?</a>
                </p>
            </form>
        </div>
    )
}

export default Register