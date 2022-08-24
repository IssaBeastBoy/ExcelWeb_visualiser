import React, { useCallback, createContext, useContext, useState } from 'react';
import { slider, vidoes } from '../data/dummy';

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
    const handleUpload = (clicked) => {
        setUpload({ ...uploadProcess, [clicked]: true })
    }

    const [details, setState] = useState([])

    const [login, setStatus] = useState(false);

    const [activeMenu, setActiveMenu] = useState
        (true);
    const [isClicked, setIsClicked] = useState(initialState)
    const handleClick = (clicked) => {
        setIsClicked({ ...initialState, [clicked]: true })
    }
    const [screenSize, setScreenSize] = useState(undefined);

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
                details, setState, login, setStatus, activeMenu, handleUpload, Upload, setActiveMenu, isClicked, setIsClicked, handleClick, screenSize,
                setScreenSize, currentSilde, setCurrentSlide, nextSlide, preSlide, nextVideo, currentVideo
            }
            }>
            {children}

        </StateContext.Provider >

    )
}

export const useStateContext = () => useContext(StateContext);