import React, { createContext, useContext, useState } from 'react';
import { slider } from '../data/dummy';

const StateContext = createContext();

const initialState = {
    Chats: false,
    userProfile: false,
    Notifications: false,
}

export const ContextProvider = ({ children }) => {
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

    if (!Array.isArray(slider) || slideCount <= 0) {
        return null;
    }

    return (
        <StateContext.Provider
            value={{ activeMenu, setActiveMenu, isClicked, setIsClicked, handleClick, screenSize, setScreenSize, currentSilde, setCurrentSlide, nextSlide, preSlide }
            }>
            {children}

        </StateContext.Provider >

    )
}

export const useStateContext = () => useContext(StateContext);