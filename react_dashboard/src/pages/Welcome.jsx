import React from 'react';
import { useStateContext } from '../context/ContextProvider';
import { slider } from '../data/dummy';
import { AiFillRightCircle, AiFillLeftCircle } from 'react-icons/ai';

const Welcome = () => {
    const { nextSlide, preSlide, currentSilde } = useStateContext();
    return (
        <div>
            <div className='justify-between'>
                <p >
                    Welcome to the FNB Lead Analysis System: <br />
                    Defination of the System...
                </p>
            </div>
            <div>
                <section className='flex relative h-1/4 justify-center items-center gap-10  max-h-fit '>
                    <AiFillLeftCircle onClick={preSlide} title='previous arrow' className='hover:scale-125 relative top-2/4 left-8 text-4xl cursor-pointer
                     select-none text-orange-500 ' />
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
                    <AiFillRightCircle onClick={nextSlide} title='next slide' className=' hover:scale-125 relative top-2/4 right-8 text-4xl cursor-pointer
                     select-none text-orange-500' />

                </section>
                <iframe width="956" height="538" src="https://www.youtube.com/embed/QgNbFxe0UAE" title="FNB inContact." frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </div>
        </div>
    )
}

export default Welcome