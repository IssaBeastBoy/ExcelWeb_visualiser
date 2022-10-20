import React, { useState } from 'react';
import axios from "axios";
import { useStateContext } from '../../context/ContextProvider';
import {
    AccumulationChartComponent, AccumulationSeriesCollectionDirective,
    AccumulationSeriesDirective, AccumulationLegend, PieSeries,
    AccumulationDataLabel, Inject, AccumulationTooltip
} from '@syncfusion/ej2-react-charts';
import { AiOutlinePieChart } from 'react-icons/ai';

const PieChart = () => {
    const { selectedCol, setRenderPie, renderPie, range } = useStateContext();
    const [data, ChartData] = useState([]);
    const [show, setShow] = useState(false);
    const [title, setTitle] = useState(selectedCol + " vs Percentage (%)");

    if (renderPie) {
        const formData = new FormData();
        formData.append("colName", selectedCol);
        formData.append("min", range[0]);
        formData.append("max", range[1]);
        const API_URL = "http://localhost:8080/PieChartView";
        const response = axios.post(API_URL, formData).then(async (res) => {
            res = res.data;
            const results = [];
            for (let parse = 0; parse < res.length; parse++) {
                let tempValue = res[parse];
                let tempDic = {};
                tempDic["x"] = tempValue[0];
                tempDic["y"] = Number(tempValue[1]);
                tempDic["text"] = parseFloat(tempValue[2]);
                results.push(tempDic);
            }
            ChartData(results);
            setRenderPie(false);
            setShow(true);
            let name = selectedCol + " vs Percentage (%)";
            setTitle(name);
        })
    }


    return (
        <div className="bg-white dark:bg-secondary-dark-bg rounded-3xl">
            {
                show ? (<div className='py-3 px-2'>
                    <p className='italic text-xs font-semibold'> Pie Chart View</p>
                    <AccumulationChartComponent
                        id='chart-pie'
                        legendSettings={{ visible: 'legendVisiblity', background: 'white' }}
                        tooltip={{ enable: true }}
                        title={title}
                    >
                        <Inject services={[AccumulationLegend, PieSeries, AccumulationDataLabel, AccumulationTooltip]} />
                        <AccumulationSeriesCollectionDirective>
                            <AccumulationSeriesDirective
                                name={selectedCol}
                                dataSource={data}
                                xName="x"
                                yName="y"
                                innerRadius="40%"
                                startAngle={0}
                                endAngle={360}
                                radius="70%"
                                explode
                                explodeOffset="10%"
                                explodeIndex={2}
                                dataLabel={{
                                    visible: true,
                                    name: 'text',
                                    position: 'Inside',
                                    font: {
                                        fontWeight: '600',
                                        color: '#fff',
                                    },
                                }}
                            />
                        </AccumulationSeriesCollectionDirective>
                    </AccumulationChartComponent>
                </div>
                ) : (<div className=' p-5 gap-20  items-center'>
                    <AiOutlinePieChart className='hover:scale-150  scale-125' />
                    <span className='font-extrabold'>Rendering chart  data ...</span>
                </div>)

            }
        </div>
    )
}

export default PieChart