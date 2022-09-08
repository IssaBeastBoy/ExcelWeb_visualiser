import React from 'react'

const header = ({ category, title }) => {
    return (
        <div className='mb-10'>
            <p className='text-gray-400 '>
                {category}
            </p>
            <p>
                {title}
            </p>
        </div>
    )
}

export default header