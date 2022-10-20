import React, { useState } from 'react';
import { useStateContext } from '../../context/ContextProvider';

import { Header } from '../../components';
import axios from "axios";
import { HiOutlineVariable } from "react-icons/hi";

const PivotTable = () => {
    const { details } = useStateContext();

    const [startView, setStartUp] = useState(true);
    const [columnNames, setColumns] = useState([]);
    const [fileInfo, getFiles] = useState([]);
    const [selectedExcel, setSelected] = useState("");
    const [varDropDown, setVdropDown] = useState(false);

    const [viewDetail, setDetails] = useState({
        filterOneSelect: "", filterTwoSelect: "",

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
                }
                else {
                    setColumns(res.data.columnNames);
                    setVdropDown(true);
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
                                            <input type="radio" name="filterOneSelect" onClick={() => setDetails({ ...viewDetail, filterOneSelect: item })} value={item} />
                                            <label> {item}</label>
                                        </td>
                                        <td>
                                            <input type="radio" name="filterTwoSelect" onClick={() => setDetails({ ...viewDetail, filterTwoSelect: item })} value={item} />
                                            <label> {item}</label>
                                        </td>
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