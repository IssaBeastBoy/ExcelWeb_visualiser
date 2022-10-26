import React, { useState } from 'react';
import { useStateContext } from '../../context/ContextProvider';

import { Header } from '../../components';
import axios from "axios";
import { HiOutlineVariable } from "react-icons/hi";
import { FcProcess } from "react-icons/fc";

const PivotTable = () => {
    const { details } = useStateContext();

    const [startView, setStartUp] = useState(true);
    const [columnNames, setColumns] = useState([]);
    const [fileInfo, getFiles] = useState([]);
    const [selectedExcel, setSelected] = useState("");
    const [varDropDown, setVdropDown] = useState(false);
    const [tableView, showTable] = useState(false);
    const [pivotTable, setPivotTable] = useState();
    const [tableBody, setBody] = useState();

    const [viewDetail, setDetails] = useState({
        verticalSelect: "", horizontalSelect: "",

    })

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

    const plotChart = async () => {
        const formData = new FormData();
        formData.append("verticalSelect", viewDetail.verticalSelect);
        formData.append("horizontalSelect", viewDetail.horizontalSelect);
        formData.append("excelLoc", details.fileStorageDir + selectedExcel);
        const API_URL = "http://localhost:8080/pivotTable";
        const response = axios.post(API_URL, formData).then(async (res) => {
            console.log(res.data);
            if (res.data.err) {
                alert(res.data.errMessage);
                setVdropDown(true);
                showTable(false);
            }
            else {
                setPivotTable(res.data.values);
                setBody(res.data.pivotTable);
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
                        <button className='bg-green-300 rounded text-sm p-1 hover:bg-green-500 hover:text-base flex gap-1' onClick={plotChart}><FcProcess />Plot</button>
                    </div>
                ) : (<div />)
            }
            {
                tableView ? (
                    <div>
                        <table border="1">
                            <tr>
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
                                    < tr >
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
                        </table>
                    </div>
                ) : (<div />)
            }

        </div>
    )
}

export default PivotTable