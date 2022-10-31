import React, { useState, useEffect, forceUpdate } from 'react';
import { useStateContext } from '../../context/ContextProvider';
import { ChartComponent, SeriesCollectionDirective, SeriesDirective, Inject, LineSeries, Legend, Category, Tooltip, Highlight } from '@syncfusion/ej2-react-charts';

import { Header } from '../../components';
import { lineCustomSeries, LinePrimaryXAxis, LinePrimaryYAxis } from '../../data/dummy';
import axios from "axios";
import { HiOutlineVariable } from "react-icons/hi";
import { FcProcess } from "react-icons/fc";
import { itemMove } from '@syncfusion/ej2/treemap';

const PivotTable = () => {
    const { details, lineChart, showLineChart } = useStateContext();

    const [startView, setStartUp] = useState(true);
    const [columnNames, setColumns] = useState([]);
    const [fileInfo, getFiles] = useState([]);
    const [selectedExcel, setSelected] = useState("");
    const [varDropDown, setVdropDown] = useState(false);
    const [tableView, showTable] = useState(false);
    const [pivotTable, setPivotTable] = useState();
    const [tableBody, setBody] = useState();

    const [viewDetail, setDetails] = useState({
        verticalSelect: "", horizontalSelect: "", totalCustomerCount: 0,

    })


    const [chartSeries, setSeries] = useState([]);


    const plotLine = async (event) => {
        let xVariable = event.target.id;
        // showLineChart(lineChart => !lineChart);
        if (lineChart) {
            showLineChart(false);
        }
        else {
            showLineChart(false);
        }
        if (event.target.checked) {     
            let xAxis = pivotTable[xVariable];
            let dataSource = [];
            let tempVariablesDictionary = {
                dataSource: [],
                xName: 'x',
                yName: 'y',
                name: xVariable,
                width: '2',
                marker: { visible: true, width: 10, height: 10 },
                type: 'Line'

            }
            for (let parse = 0; parse < xAxis.length; parse++) {
                let tempDictionary = {};
                tempDictionary['x'] = pivotTable.colNames[parse];
                tempDictionary['y'] = Number(xAxis[parse]);
                dataSource.push(tempDictionary);
            }
            tempVariablesDictionary.dataSource = dataSource;
            chartSeries.push(tempVariablesDictionary);
            setSeries(chartSeries);
            //console.log(lineChart);
        }
        else {    
            let newSeries = [];
            for (let parse = 0; parse < chartSeries.length; parse++) {
                if (chartSeries[parse].name != xVariable) {
                    newSeries.push(chartSeries[parse]);
                }
            }
            setSeries(newSeries); 
        }
        if (lineChart) {
            //DO NOTHING
            showLineChart(true);
        }
        else {
            showLineChart(true);
        }
        // showLineChart(() => true);
    }  


    if (startView) {
        const formData = new FormData();
        formData.append("fileLoc", details.fileStorageDir);
        const API_URL = "http://localhost:8080/getUploadFiles";
        const response = axios.post(API_URL, formData).then(async (res) => {
            getFiles(res.data.filesName);
            if (res.data.filesName.length == 0) {
                alert("Please upload excel sheet.");
            }
        })
        setStartUp(false);
    }

    const plotChart = async (event) => {
        const formData = new FormData();
        formData.append("verticalSelect", viewDetail.verticalSelect);
        formData.append("horizontalSelect", viewDetail.horizontalSelect);
        formData.append("excelLoc", details.fileStorageDir + selectedExcel);
        formData.append("displayType", event.target.value)
        const API_URL = "http://localhost:8080/pivotTable";
        const response = axios.post(API_URL, formData).then(async (res) => {
            if (res.data.err) {
                alert(res.data.errMessage);
                setVdropDown(true);
                showTable(false);
            }
            else {
                setPivotTable(res.data.values);
                setBody(res.data.pivotTable);
                viewDetail.totalCustomerCount = res.data.totalCustonerCount;
                setVdropDown(false);
                showTable(true);
            }
        })
    }

    const getColNames = async () => {
        if (selectedExcel == "") {
            alert("Please select excel file first.");
        }
        else {
            const formData = new FormData();
            formData.append("fileLoc", details.fileStorageDir + selectedExcel);
            const API_URL = "http://localhost:8080/getColumnName";
            const response = axios.post(API_URL, formData).then(async (res) => {
                if (res.data.err) {
                    alert(res.data.errMessage);
                    setVdropDown(false);
                    showTable(false);
                }
                else {
                    setColumns(res.data.columnNames);
                    setVdropDown(true);
                    showTable(false);
                }
            })
        }
    }



    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <Header category="Comparative Analysis" title="Pivat Table View" />
            <div>
                <label>
                    Please select Excel sheet to use:
                </label>
                <div className='gap-2 '>
                    {
                        fileInfo.map((item, index) => (
                            <div className='m-1'>
                                <input type="radio" name="files_Select" onClick={() => setSelected(item)} value={item} />
                                <label> {item}</label>
                            </div>
                        )

                        )
                    }

                </div>
                <button type="button" className="rounded gap-2 hover:bg-blue-200 flex items-center p-2 font-semibold" onClick={getColNames}>
                    <HiOutlineVariable className='relative items-center scale-105' />
                    Select Variables:</button>
            </div>

            {
                varDropDown ? (
                    <div>
                        <label className='text-xs'>
                            Please select the variables you want to use:
                        </label>
                        <table className='w-full'>
                            <tr className='font-semibold'>
                                <td>
                                    Vertical variable
                                </td>
                                <td>
                                    Horizontal variable
                                </td>

                            </tr>
                            {
                                columnNames.map((item) => (
                                    <tr>
                                        <td>
                                            <input type="radio" name="verticalSelect" onClick={() => setDetails({ ...viewDetail, verticalSelect: item })} value={item} />
                                            <label> {item}</label>
                                        </td>
                                        <td>
                                            <input type="radio" name="horizontalSelect" onClick={() => setDetails({ ...viewDetail, horizontalSelect: item })} value={item} />
                                            <label> {item}</label>
                                        </td>
                                    </tr>
                                )

                                )
                            }

                        </table>
                        <button className='bg-red-200 rounded text-xs p-1 hover:bg-red-400 hover:text-sm' onClick={() => setVdropDown(false)}> Close variables </button>
                        <br /> <br />
                        <button className='bg-green-300 rounded text-sm p-1 hover:bg-green-500 hover:text-base flex gap-1' value="number" onClick={plotChart}><FcProcess />Plot</button>
                    </div>
                ) : (<div />)
            }
            {
                tableView ? (
                    <div>
                        <div className='text-sm'>
                            <span>
                                <input type="radio" name="type" value="number" onClick={plotChart} /> <label>by customer sum</label>
                            </span>
                            <span className='p-2'>
                                <input type="radio" name="type" value="percent" onClick={plotChart} /> <label>by customer percent</label>
                            </span>
                        </div>
                        <table className='border-2 text-sm items-center'>
                            <tr className="border-b md-1 bg-cyan-400 text-black">
                                {
                                    pivotTable.Header.map((item) => (
                                        <td>
                                            {item}
                                        </td>
                                    )
                                    )
                                }
                            </tr>
                            {
                                tableBody.Body.map((items) => (
                                    < tr className="border-b md-1 p-2">
                                        {
                                            items.map((item) => (
                                                <td> {item}</td>
                                            )
                                            )
                                        }
                                    </tr>
                                )
                                )
                            }
                        </table> <br />
                        <label className='font-semibold'>
                            Select which row to plot the line graph:
                        </label> <br />
                        {
                            pivotTable.rowNames.map((item) => (
                                <div className='inline text-sm gap-2 space-x-2'>
                                    <span className='p-2'>
                                        <input type="checkbox" onClick={plotLine} name={item} id={item} />
                                        <label> {item}</label>
                                    </span>
                                </div>
                            )
                            )
                        }
                        {
                            lineChart ? (
                                <ChartComponent
                                    primaryXAxis={{
                                        valueType: 'Category', edgeLabelPlacement: 'Shift', majorGridLines: { width: 0 }, title: viewDetail.verticalSelect,
                                    }}
                                    primaryYAxis={{
                                        title: 'Amount of Customers'
                                    }}
                                    title={pivotTable.Header[0]}
                                    tooltip={{ enable: true }}>
                                    <Inject services={[LineSeries, Category, Legend, Tooltip, Highlight]} />
                                    <SeriesCollectionDirective>
                                        {
                                            chartSeries.map((item, index) => (
                                                <SeriesDirective key={index} {...item} />
                                            ))
                                        }
                                    </SeriesCollectionDirective>
                                </ChartComponent>) : (<div />)
                        }
                    </div>
                ) : (<div />)
            }

        </div>
    )
}

export default PivotTable