import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import { MdOutlineCancel } from 'react-icons/md';
import { TooltipComponent } from '@syncfusion/ej2-react-popups';
import axios from "axios";

import { links } from '../data/dummy';
import { useStateContext } from '../context/ContextProvider';

const SideBar = () => {
    const { activeMenu, setActiveMenu, screenSize, details, fileInfo } = useStateContext();


    const handleCloseSideBar = () => {
        if (activeMenu !== undefined && screenSize <= 900) {
            setActiveMenu(false);
        }
    };
    const activeLink = 'flex items-center gap-5 pl-4 pt-3 pb-2.5 rounded-1g text-white text-md m-2';
    const normalLink = 'flex items-center gap-5 pl-4 pt-3 pb-2.5 rounded-1g text-md text-gray-700 dark:text-gray-200 dark:hover:text-black hover:bg-light-gray m-2';
    return (
        <div className='ml-3 h-screen md:overflow-hidden overflow-auto md:hover:overflow-auto pb-10'>
            {activeMenu && (<>
                <div className='flex justify-between items-center'>
                    <Link to='/Welcome' onClick={() => setActiveMenu(false)} className='items-center gap-3 ml-3 mt-4 flex text-xl 
                    font-extrabold tracking-tight dark:text-white text-slate-900'>
                        {handleCloseSideBar}
                        <img src={require("../data/LOGO.png")} className="rounded-full scale-50 w-36 h-36" alt={"Logo"} /> <span>EXCEL VS</span>
                    </Link>
                    <TooltipComponent content="Menu" position="BottomCenter">
                        <button type='button' onClick={() => setActiveMenu((prevActiveMenu => !prevActiveMenu))} className="text-x1 rounded-full mp-3
                        hover:bg-light-gray mt-4 block md:hidden">
                            <MdOutlineCancel />
                        </button>
                    </TooltipComponent>
                </div>
                <div className='mt-10'>
                    {links.map((item) => (
                        <div key={item.title} className='text-grey-400 m-3 mt-4 uppercase'>
                            <p className='text-gray-400 m-3 mt-4 uppercase'>
                                {item.title}
                            </p>
                            {item.links.map((link) => (
                                <NavLink to={`/${link.name}`} key={link.name} onClick={() => handleCloseSideBar}
                                    className={({ isActive }) => isActive ? activeLink : normalLink}>
                                    {link.icon}
                                    <span className='capitalize'> {link.name}
                                    </span>

                                </NavLink>
                            ))}
                        </div>
                    ))}
                </div>
            </>)
            }
        </div>
    )
}

export default SideBar