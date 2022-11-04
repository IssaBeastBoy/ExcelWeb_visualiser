import React, { useEffect, useState } from 'react';
import { AiOutlineMenu } from 'react-icons/ai';
import { BsChatLeft } from 'react-icons/bs';
import { RiNotification3Line } from 'react-icons/ri';
import { MdKeyboardArrowDown } from 'react-icons/md';
import { TooltipComponent } from '@syncfusion/ej2-react-popups';
import axios from "axios";


// import avatar from '../data/default_IMG.png';
import { Chat, Notification, UserProfile } from '.';
import { useStateContext } from '../context/ContextProvider';

const NavButton = ({ title, customFunc, icon, color, dotColor }) => (
    <TooltipComponent content={title} position="BottomCenter">
        <button
            type="button"
            onClick={() => customFunc()}
            style={{ color }}
            className="relative text-xl rounded-full p-3 hover:bg-light-gray"
        >
            <span
                style={{ background: dotColor }}
                className="absolute inline-flex rounded-full h-2 w-2 right-2 top-2"
            />
            {icon}
        </button>
    </TooltipComponent>
);

const Navbar = () => {
    const { activeMenu, setActiveMenu, details, isClicked, initialState, handleClick, setScreenSize, screenSize, userProfile, setProfile } = useStateContext();
    useEffect(() => {
        const handleResize = () => setScreenSize(() => window.innerWidth);

        window.addEventListener('resize', handleResize);

        handleResize();

        return () => window.removeEventListener('resize', handleResize);
    }, []);

    const [status, setStatus] = useState({
        Online: "#22c55e",
        Offline: "#64748b",
        Away: "#f59e0b"
    })
    // const [stateColor, setColor] = useState("#22c55e");

    useEffect(() => {
        if (screenSize <= 900) {
            setActiveMenu(false);
        } else {
            setActiveMenu(true);
        }
    }, [screenSize]);

    const [setter, Set] = useState(true);
    const [proPic, setImg] = useState(require("../data/default_IMG.png"));

    if (setter) {
        // setColor(status[details.status]);
        const formData = new FormData();
        formData.append("fileLoc", details.img);
        const API_URL = "http://localhost:8080/getProfile";
        const response = axios.post(API_URL, formData).then(async (res) => {
            setProfile(res.data);
            if (res.data.err) {
                setImg(require(`../data/${res.data.imgLoc}.png`));
                userProfile["imgLoc"] = require(`../data/${res.data.imgLoc}.png`);
            }
            else {
                alert(res.data.errMessage);
                userProfile["imgLoc"] = require("../data/default_IMG.png");
            }

        });
        Set(false);
    }

    return (
        <div className='flex justify-between p-2 md:mx-6 relative'>
            {activeMenu ?
                (
                    <div className='items-center gap-1 ml-3 mt-4 flex text-xl font-extrabold tracking-tight dark:text-white text-slate-900'>
                        <NavButton title="Menu" className='h-1' customFunc={() => setActiveMenu((prevActiveMenu) => !prevActiveMenu)} color='blue' icon={<AiOutlineMenu />} />
                    </div>
                ) : (
                    <div className='items-center gap-1 ml-3 mt-4 flex text-xl font-extrabold tracking-tight dark:text-white text-slate-900'>
                        <NavButton title="Menu" className='h-1' customFunc={() => setActiveMenu((prevActiveMenu) => !prevActiveMenu)} color='blue' icon={<AiOutlineMenu />} />
                        <img src={require("../data/FNB_LOGO.png")} alt={"Logo"} />
                        <span>FNB Leads Analysis System</span>
                    </div>
                )}
            <div className='flex items-center gap-3'>
                <NavButton title="Chat" dotColor="#03C9D7" customFunc={() => handleClick('Chats')} color='blue' icon={<BsChatLeft />} />
                {/* <NavButton title="Notification" dotColor="rgb(254, 201, 15)" customFunc={() => handleClick('Notifications')} color='blue' icon={<RiNotification3Line />} /> */}
                <TooltipComponent content="Profile" position="BottomCenter">
                    <div>
                        <div className='flex item-center gap-2 cursor-pointer p-1 hover:bg-gray-200 rounded-lg' onClick={() => handleClick("userProfile")}>
                        <img src={proPic} alt={"Profile Picture"} className="rounded-full w-8 h-8" />
                            <span
                                style={{ background: status[details.status] }}
                                className=" inline-flex rounded-full h-2 w-2 left-2 top-2"
                            />
                        <p>
                            <span className='text-gray-400 text-14'> Hi, {details.name} </span>
                            <span className='text-gray-400 font-bold ml-1 text-14'>Welcome to FNB Leads!</span>
                            <MdKeyboardArrowDown className='text-gray-400 text-14' />
                        </p>
                        </div>
                        {
                            initialState.userProfile ? (<div>{isClicked.userProfile && <UserProfile />} </div>) : (<div />)
                        }

                    </div>
                </TooltipComponent>
                {isClicked.Chats && <Chat />}
                {isClicked.Notifications && <Notification />}

            </div>
        </div>

    )
}

export default Navbar