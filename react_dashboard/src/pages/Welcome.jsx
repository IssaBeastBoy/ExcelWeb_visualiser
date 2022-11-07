import React from 'react';
import { useStateContext } from '../context/ContextProvider';
import { slider, vidoes } from '../data/dummy';
import { AiFillRightCircle, AiFillLeftCircle } from 'react-icons/ai';
import { TbPlayerTrackNext } from "react-icons/tb";

const Welcome = () => {
    const { nextSlide, preSlide, currentSilde, nextVideo, currentVideo } = useStateContext();
    return (
        <div>
            <div className='p-4 justify-between'>
                <p >
                    Welcome to the Excel Visualiser System: <br />
                    Defination of the System...
                </p>
            </div>
            <div className=' flex relative justify-center items-center gap-14 max-h-fit my-10 h-32 '>
                <AiFillLeftCircle onClick={preSlide} title='previous arrow' className='hover:scale-125 relative  text-4xl cursor-pointer
                     select-none text-orange-500 ' />
                <div className='w-15'>
                    {slider.map((slide, index) => {
                        return (
                            <div className="opacity-100 duration-1500 scale-100" key={index}>
                                {index === currentSilde && (
                                    <a href={slide.url}>
                                        <img className='rounded-lg  hover:scale-125' src={slide.image} alt="What's Happening?" />
                                    </a>
                                )}
                            </div>
                        )
                    })}
                </div>
                <AiFillRightCircle onClick={nextSlide} title='next slide' className=' hover:scale-125 relative text-4xl cursor-pointer
                     select-none text-orange-500' />
            </div>
            <div className='p-8 columns-2 justify-between flex gap-2 col-span-1'>
                <p>
                    Date and time <br />
                    Summary of current tasks from agile <br />
                    Upcoming meetings <br />

                </p>
                <div className='w-7/12'>
                    <TbPlayerTrackNext onClick={nextVideo} title='Play next' className='hover:scale-125 relative right-10 text-xl text-orange-500 ' />
                    {vidoes.map((video, index) => {
                        return (
                            <div key={index}>
                                {index === currentVideo && (
                                    <iframe className='h-96 w-full' src={video.url} title={video.title} frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" ></iframe>
                                )}
                            </div>
                        )
                    })}
                </div>

            </div>
        </div>
    )
}

export default Welcome