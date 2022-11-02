import React, { useCallback, createContext, useContext, useState } from 'react';
import { slider, vidoes } from '../data/dummy';
import {
    FcDataBackup, FcApproval, FcSynchronize, FcSupport,
} from "react-icons/fc";

const StateContext = createContext();

const initialState = {
    Chats: false,
    userProfile: false,
    Notifications: false,
}

const uploadProcess = {
    Success: false,
    Loading: false,
}

export const ContextProvider = ({ children }) => {

    const [Upload, setUpload] = useState(uploadProcess)

    const [Board, setBoard] = useState([
        {
            "name": "Backlog",
            "items": [],
            "icon": <FcDataBackup className='w-5 h-5 text-gray-500' />,
        },
        {
            "name": "Open",
            "items": [],
            "icon": <FcSynchronize className='w-5 h-5' />,
        },
        {
            "name": "In Progress",
            "items": [],
            "icon": <FcSupport className='w-5 h-5' />,
        },
        {
            "name": "Complete",
            "items": [],
            "icon": <FcApproval className='w-5 h-5' />,
        },
    ])

    const [editTick, showEdit] = useState(false);

    const [deleteTick, showDelete] = useState(false);

    const [ticketInfo, changeTicket] = useState({
        Id: "",
        Status: "",
        Priority: "",
        Estimate: "",
        Summary: "",
        eventType: "",
    });

    const [notes, setNotes] = useState([]);

    const [renderCalender, setCalender] = useState(true);

    const [renderBar, setRenderBar] = useState(false);
    const [renderPie, setRenderPie] = useState(false);
    const [renderTable, setRenderTable] = useState(false);
    const [size, reSetSize] = useState(false);
    const [range, setRange] = useState([0, 10]);

    const handleUpload = (clicked) => {
        setUpload({ ...uploadProcess, [clicked]: true })
    }

    const [selectedCol, setSelectCol] = useState("");

    const [details, setState] = useState([]);

    const [lineChart, showLineChart] = useState(false);

    const [login, setStatus] = useState(false);
    const [userProfile, setProfile] = useState({});

    const [activeMenu, setActiveMenu] = useState
        (true);
    const [isClicked, setIsClicked] = useState(initialState)
    const handleClick = (clicked) => {
        setIsClicked({ ...initialState, [clicked]: true })
    }
    const [screenSize, setScreenSize] = useState(undefined);

    const [renderData, setRender] = useState(false);

    const [currentSilde, setCurrentSlide] = useState(0);

    const slideCount = slider.length;
    const nextSlide = () => {
        setCurrentSlide(currentSilde == slideCount - 1 ? 0 : currentSilde + 1);
    }

    const preSlide = () => {
        setCurrentSlide(currentSilde == 0 ? slideCount - 1 : currentSilde - 1);
    }

    const [currentVideo, setCurrentVideo] = useState(0);
    const videoCount = vidoes.length;
    const nextVideo = () => {
        setCurrentVideo(currentVideo == videoCount - 1 ? 0 : currentVideo + 1);
    }

    if (!Array.isArray(slider) || slideCount <= 0) {
        return null;
    }

    return (
        <StateContext.Provider
            value={{
                userProfile, setProfile, lineChart, showLineChart, notes, setNotes, ticketInfo, changeTicket, deleteTick, showDelete, editTick, showEdit, Board, setBoard, renderCalender, setCalender, renderData, setRender, size, reSetSize, range, setRange, renderTable, setRenderTable, renderPie, setRenderPie, renderBar, setRenderBar, selectedCol, setSelectCol, details, setState, login, setStatus, activeMenu, handleUpload, Upload, setActiveMenu, isClicked, setIsClicked, handleClick, screenSize,
                setScreenSize, currentSilde, setCurrentSlide, nextSlide, preSlide, nextVideo, currentVideo
            }
            }>
            {children}

        </StateContext.Provider >

    )
}

export const useStateContext = () => useContext(StateContext);