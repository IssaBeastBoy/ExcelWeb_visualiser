import React, { useState } from 'react';
import { useStateContext } from '../../context/ContextProvider';

import { Header } from '../../components';
import axios from "axios";
import { RiFileExcel2Fill } from "react-icons/ri";
import { HiOutlineVariable } from "react-icons/hi";
import { GrHostMaintenance } from "react-icons/gr";
import { BiFilterAlt } from "react-icons/bi";
import { FcProcess } from "react-icons/fc";
import { MdDownloading } from "react-icons/md";


const Table = () => {    
    const { details } = useStateContext();

    const [fileInfo, getFiles] = useState([]);
    const [dropDown, setDropDown] = useState(false);
    const [selectedExcel, setSelected] = useState("");


    const [columnNames, setColumns] = useState([]);
    const [uploads, setUploads] = useState(false);
    const [varDropDown, setVdropDown] = useState(false);
    const [viewDetail, setDetails] = useState({
        Main: "", filterOne: "", filterTwo: "", mainCount: "2",
        filterOneSelect: "", filterTwoSelect: "",

    })


    const [initail, setInitail] = useState(0);


    const [tableView, setTableView] = useState(false);
    const [tableItems, setItems] = useState([])
    const [tableResult, setResult] = useState(false);



    const updateTable = async () => {
        setResult(false);
        setTableView(true);

        const formData = new FormData();
        formData.append("Main", viewDetail.Main);
        formData.append("filterOne", viewDetail.filterOne);
        formData.append("filterTwo", viewDetail.filterTwo);
        formData.append("filterOneSelect", viewDetail.filterOneSelect);
        formData.append("filterTwoSelect", viewDetail.filterTwoSelect);
        formData.append("mainCount", viewDetail.mainCount);
        formData.append("excelLoc", details.fileStorageDir + selectedExcel);
        const API_URL = "http://localhost:8080/Table";
        var storeResponse = {};
        const response = axios.post(API_URL, formData).then(res => {
            if (res.data.err) {
                alert(res.data.errMessage)
            }
            else {
                storeResponse["mainCount"] = viewDetail.mainCount;
                storeResponse["fileName"] = selectedExcel;
                storeResponse["Main"] = viewDetail.Main;
                storeResponse["filterApp"] = res.data.filter;
                storeResponse["filterOne"] = viewDetail.filterOneSelect;
                storeResponse["filterOneOptions"] = res.data.filterOneOptions;
                storeResponse["filterTwo"] = viewDetail.filterTwoSelect;
                storeResponse["filterTwoOptions"] = res.data.filterTwoOptions;
                storeResponse["itemDisplay"] = res.data.itemDisplay;
                if (tableItems.length == 0) {
                    tableItems.push(storeResponse);
                }
                else if (tableItems.length == 1) {
                    tableItems.push(storeResponse);
                }
                else if (tableItems.length == 2) {
                    tableItems[1] = tableItems[0];
                    tableItems[0] = storeResponse;
                }
                setResult(true);
            }
        })

    }


    const plotTables = async () => {
        setVdropDown(false);
        setDropDown(false);
        setResult(false);
        setTableView(true);

        viewDetail.filterOne = "";
        viewDetail.filterTwo = "";
        viewDetail.mainCount = "2";

        const formData = new FormData();
        formData.append("Main", viewDetail.Main);
        formData.append("filterOne", viewDetail.filterOne);
        formData.append("filterTwo", viewDetail.filterTwo);
        formData.append("filterOneSelect", viewDetail.filterOneSelect);
        formData.append("filterTwoSelect", viewDetail.filterTwoSelect);
        formData.append("mainCount", viewDetail.mainCount);
        formData.append("excelLoc", details.fileStorageDir + selectedExcel);
        const API_URL = "http://localhost:8080/Table";
        var storeResponse = {};
        const response = axios.post(API_URL, formData).then(res => {
            if (res.data.err) {
                alert(res.data.errMessage)
            }
            else {
                storeResponse["mainCount"] = viewDetail.mainCount;
                storeResponse["fileName"] = selectedExcel;
                storeResponse["Main"] = viewDetail.Main;
                storeResponse["filterApp"] = res.data.filter;
                storeResponse["filterOne"] = viewDetail.filterOneSelect;
                storeResponse["filterOneOptions"] = res.data.filterOneOptions;
                storeResponse["filterTwo"] = viewDetail.filterTwoSelect;
                storeResponse["filterTwoOptions"] = res.data.filterTwoOptions;
                storeResponse["itemDisplay"] = res.data.itemDisplay;
                viewDetail.filterOne = res.data.filterOneOptions[0];
                viewDetail.filterTwo = res.data.filterTwoOptions[0];

                console.log(viewDetail);

                if (tableItems.length == 0) {
                    tableItems.push(storeResponse);
                }
                else if (tableItems.length == 1) {
                    tableItems.push(storeResponse);
                }
                else if (tableItems.length == 2) {
                    tableItems[1] = tableItems[0];
                    tableItems[0] = storeResponse;
                }
                setResult(true);
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
            const response = axios.post(API_URL, formData).then(res => {
                if (res.data.err) {
                    alert(res.data.errMessage);
                }
                else {
                    setColumns(res.data.columnNames);
                    setVdropDown(true);
                    setTableView(false)
                }
            })
        }
    }

    const getFileNames = async () => {
        const formData = new FormData();
        formData.append("fileLoc", details.fileStorageDir);
        const API_URL = "http://localhost:8080/getUploadFiles";
        const response = axios.post(API_URL, formData).then(res => {
            getFiles(res.data.filesName);
            setDropDown(true);
            if (res.data.filesName.length > 0) {
                setUploads(true);
            }
            else {
                setUploads(false);
                alert("Please upload excel sheet.");
            }
        })
    }

    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <Header category="Analyse Columns" title="Table View" />
            <div className='gap-3 p-2'>
                <button type="button" className="rounded gap-2 hover:bg-green-100 flex items-center p-2 font-semibold" onClick={getFileNames}>
                    <RiFileExcel2Fill className='relative items-center' />
                    Select Excel file:</button>
                {
                    dropDown ? (
                        <div>
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
                            <button className='bg-red-200 rounded text-xs p-1 hover:bg-red-400 hover:text-sm' onClick={() => setDropDown(false)}> Close select </button>
                        </div>
                    ) : (<div />)
                }
            </div>
            {
                uploads ? (
                    <div>
                        <button type="button" className="rounded gap-2 hover:bg-blue-200 flex items-center p-2 font-semibold" onClick={getColNames}>
                            <HiOutlineVariable className='relative items-center scale-105' />
                            Select Variables:</button>
                        {
                            varDropDown ? (
                                <div>
                                    <div className="columns-3">
                                        <div className='gap-2 items-center text-sm'>
                                            <label className='flex gap-2'> <GrHostMaintenance className='scale-95' />  Main variable: </label>
                                            {
                                                columnNames.map((item) => (
                                                    <div className='space-y-2 m-4'>
                                                        <input type="radio" name="main" onClick={() => setDetails({ ...viewDetail, Main: item })} value={item} />
                                                        <label> {item}</label>
                                                    </div>
                                                )

                                                )
                                            }
                                        </div>

                                        <div className='gap-2 items-center text-sm'>
                                            <label className='flex gap-2'> <BiFilterAlt className='scale-95' /> Filter by: </label>
                                            {
                                                columnNames.map((item) => (
                                                    <div className='space-y-2 m-4'>
                                                        <input type="radio" name="filterOneSelect" onClick={() => setDetails({ ...viewDetail, filterOneSelect: item })} value={item} />
                                                        <label> {item}</label>
                                                    </div>
                                                )

                                                )
                                            }
                                        </div>

                                        <div className='gap-2 items-center text-sm'>
                                            <label className='flex gap-2'> <BiFilterAlt className='scale-95' /> Filter by: </label>
                                            {
                                                columnNames.map((item) => (
                                                    <div className='space-y-2 m-4'>
                                                        <input type="radio" name="filterTwo" onClick={() => setDetails({ ...viewDetail, filterTwoSelect: item })} value={item} />
                                                        <label> {item}</label>
                                                    </div>
                                                )

                                                )
                                            }
                                        </div>
                                    </div>
                                    <button className='bg-red-200 rounded text-xs p-1 hover:bg-red-400 hover:text-sm' onClick={() => setVdropDown(false)}> Close variables </button>
                                    <br /> <br />
                                    <button className='bg-green-300 rounded text-sm p-1 hover:bg-green-500 hover:text-base flex gap-1' onClick={plotTables}><FcProcess />Plot</button>
                                </div>

                            ) : (<div />)
                        }
                    </div>
                ) : (<div />)
            }

            {
                tableView ? (
                    <div className='m-1'>

                        {
                            tableResult ? (
                                <div>
                                    <div>
                                        <button className='bg-green-300 rounded text-sm p-1 hover:bg-green-500 hover:text-base flex gap-1' onClick={updateTable}><FcProcess />Update</button>
                                    </div>
                                    <div className='columns-2 overflow-scroll'>
                                        {
                                            tableItems.map((variable) => (
                                                <div className='overflow-scroll'>
                                                    <table className="border">
                                                        <tr className="border-b bg-cyan-400 text-black">
                                                            <th colspan="2" > {variable.fileName} <br />
                                                                <label className='text-xs'>
                                                                    Remember to press the Update button above if any changes are made.
                                                                </label>
                                                            </th>
                                                        </tr>

                                                        <tr className="border-b text-sm">
                                                            <td className='font-semibold'>
                                                                Showing top
                                                            </td>
                                                            <td>
                                                                <select name="filterApp" id="filterApp" onChange={event => setDetails({ ...viewDetail, mainCount: event.target.value })}> {/* setDetails({ ...viewDetail, mainCount: event.target.value })} value={viewDetail.mainCount}> */}
                                                                    {
                                                                        variable.filterApp.map((option) => (
                                                                            <option name={option} value={option}>{option}</option>
                                                                        )
                                                                        )
                                                                    }
                                                                </select>
                                                            </td>
                                                        </tr>

                                                        <tr className="border-b text-sm">
                                                            <td className='font-semibold'>
                                                                {variable.filterOne}
                                                            </td>
                                                            <td>
                                                                <select name="filterOne" id="filterOne" onChange={event => setDetails({ ...viewDetail, filterOne: event.target.value })}>
                                                                    {
                                                                        variable.filterOneOptions.map((option) => (
                                                                            <option name={option} value={option}>{option}</option>
                                                                        )
                                                                        )
                                                                    }
                                                                </select>
                                                            </td>
                                                        </tr>

                                                        <tr className="border-b text-sm">
                                                            <td className='font-semibold'>
                                                                {variable.filterTwo}
                                                            </td>
                                                            <td>
                                                                <select name="filterTwo" id="filterTwo" onChange={event => setDetails({ ...viewDetail, filterTwo: event.target.value })}>
                                                                    {
                                                                        variable.filterTwoOptions.map((option) => (
                                                                            <option name={option} value={option}>{option}</option>
                                                                        )
                                                                        )
                                                                    }
                                                                </select>
                                                            </td>
                                                        </tr>

                                                        <tr className="border-b text-sm">
                                                            <td className='font-semibold'>
                                                                {variable.Main}
                                                            </td>
                                                            <td className='font-semibold'>
                                                                Total Customers
                                                            </td>
                                                        </tr>
                                                        {
                                                            variable.itemDisplay.map((value) =>
                                                            (
                                                                <tr className="border-b text-sm">
                                                                    <td>
                                                                        {value[0]}
                                                                    </td>
                                                                    <td>
                                                                        {value[1]}
                                                                    </td>

                                                                </tr>
                                                            )
                                                            )
                                                        }

                                                    </table>
                                                </div>
                                            )
                                            )
                                        }

                                    </div>
                                </div>
                            ) : (<div className='text-xl font-black'>
                                <MdDownloading /> Loading table...
                            </div>)
                        }
                    </div>
                ) : (<div />)
            }
        </div >
    )
}

export default Table