import React, { useState } from 'react';
import {
    AxisModel, ColumnSeries, Legend, BarSeries, Category, ChartComponent, Inject, SeriesCollectionDirective, SeriesDirective, Tooltip
} from '@syncfusion/ej2-react-charts';
import axios from "axios";
import { useStateContext } from '../../context/ContextProvider';
import { BsBarChartSteps } from 'react-icons/bs';

const BarGraph = () => {
    const { selectedCol, setRenderBar, renderBar } = useStateContext();
    const [render, setRender] = useState(true)
    const [data, ChartData] = useState([]);
    const [show, setShow] = useState(false);
    const [primaryXAxis, setPrimary] = useState({});

    if (renderBar) {
        const formData = new FormData();
        formData.append("colName", selectedCol);
        const API_URL = "http://localhost:8080/BarGraphView";
        const response = axios.post(API_URL, formData).then(res => {
            res = res.data;
            const results = [];
            for (let parse = 0; parse < res.length; parse++) {
                let tempValue = res[parse];
                let tempDic = {};
                tempDic["x"] = tempValue[0];
                tempDic["y"] = parseFloat(tempValue[1]);
                results.push(tempDic);
            }
            ChartData(results);
            setShow(true);
            const barPrimaryXAxis = {
                valueType: 'Category',
                majorGridLines: { width: 0 },
            };
            setPrimary(barPrimaryXAxis);
            setRenderBar(false);
        })
    }
    return (

        <div className="bg-white dark:bg-secondary-dark-bg rounded-3xl">
            {
                show ? (<div className='py-3 px-2' >
                    <p className='italic text-xs font-semibold'> Bar Graph View</p>
                    <ChartComponent id='charts' primaryXAxis={primaryXAxis}
                        chartArea={{ border: { width: 0 } }}
                        tooltip={{ enable: true }}
                        width='auto'>
                        <Inject services={[ColumnSeries, Legend, Tooltip, BarSeries, Category]} />
                        <SeriesCollectionDirective>
                            <SeriesDirective dataSource={data} xName='x' yName='y' type='Bar' />
                        </SeriesCollectionDirective>
                    </ChartComponent>
                </div >) : (<div className=' p-5 gap-20 items-center'>
                    <BsBarChartSteps className='hover:scale-150  scale-125' />
                    <span className='font-extrabold'>Rendering chart data ...</span>
                </div>)
            }
        </div>

    )
}

export default BarGraph