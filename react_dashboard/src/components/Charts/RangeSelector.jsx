import React, { useState } from 'react';
import { useStateContext } from '../../context/ContextProvider';
import '../../data/CSS/RangeSelect.css';
import axios from "axios";
import { convertStringToValue } from '@syncfusion/ej2/maps';

const RangeSelector = () => {
    const { setRender, selectedCol, setRenderTable, setRenderPie, setRenderBar, setRange, range, size, reSetSize } = useStateContext();

    const [maxValue, setMax] = useState(10);

    if (size) {
        const formData = new FormData();
        formData.append("colName", selectedCol);
        const API_URL = "http://localhost:8080/Getmax";
        const response = axios.post(API_URL, formData).then(res => {
            setMax(res.data);
            reSetSize(false);
            if (maxValue < range[1]) {
                setRange([0, maxValue - 1]);
            }
        })

    }

    const bounderies = (event) => {
        let sliderName = event.target.id;
        if (sliderName == "one") {
            if (Number(event.target.value) >= range[1]) {
                alert("Not allow: \n Error-Yellow boundery are overlapping or at the same position.")
                event.target.value = range[1] - 1;
                setRange([(range[1] - 1), range[1]]);
            }
            else {
                setRange([Number(event.target.value), range[1]]);
                setRenderBar(true);
                setRenderTable(true);
                setRenderPie(true);
            }
        }
        if (sliderName == "two") {
            if (Number(event.target.value) <= range[0]) {
                alert("Not allow: \n Error-Blue boundery are overlapping or at the same position.")
                event.target.value = range[0] + 1;
                setRange([range[0], range[0] + 1]);
            }
            else {
                setRange([range[0], Number(event.target.value)]);
                setRenderBar(true);
                setRenderTable(true);
                setRenderPie(true);
            }
        }

    }


    return (
        <div className='relative py-1 px-2' >
            <p className='text-white bg-blue-600 text-xs w-fit rounded-md p-1'>
                Range: {(range[0] + 1)} - {(range[1] + 1)}
            </p> <br /> <br /> <br />
            <span>
                <input defaultValue={range[0]} type="range" min="1" max={maxValue - 1} id="one" onInput={bounderies} />
                <input defaultValue={range[1]} type="range" min="2" max={maxValue} id="two" onInput={bounderies} />
            </span>
        </div >
    )
}

export default RangeSelector