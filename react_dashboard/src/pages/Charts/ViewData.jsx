import React, { useState } from 'react';
import {
    GridComponent, ColumnDirective, ColumnsDirective,
    Resize, Page, Sort, ExcelExport, PdfExport, Edit,
    Inject,
    ContextMenu,
    Filter,
    Search, Toolbar
} from '@syncfusion/ej2-react-grids';
import { ChartComponent, SeriesCollectionDirective, SeriesDirective, DateTime, Legend, Tooltip } from '@syncfusion/ej2-react-charts';
import axios from "axios";
import { fileGrid } from "../../data/dummy";
import { useStateContext } from '../../context/ContextProvider';
import { FiPlayCircle } from "react-icons/fi";
import { RiFolderChartLine } from 'react-icons/ri';
import { BarGraph, PieChart, Table, RangeSelector } from "../../components";
import { Header } from '../../components';

const ViewData = () => {
    const { details, renderData, setRender, setSelectCol, selectedCol, setRenderBar, setRenderTable, setRenderPie, reSetSize } = useStateContext();
    const [table, setTable] = useState([]);
    const [pingFile, findFiles] = useState(true);

    const [loading, setLoading] = useState(false);
    const [showCol, setShow] = useState(false);
    const [colNames, setColNames] = useState([]);
    const [tableData, setTableData] = useState([]);
    const [viewData, setViewData] = useState(false);
    const [fetchFiles, setFetchFiles] = useState(false);
    const [selected, setSelected] = useState("");


    if (pingFile) {
        const formData = new FormData();
        formData.append("fileLoc", details.fileStorageDir);
        const API_URL = "http://localhost:8080/Getuploads";
        var storeAttributes = [];
        const response = axios.post(API_URL, formData).then(res => {
            for (let index = 0; index < res.data.filesName.length; index++) {
                let tempAttributes = res.data.filesName[index].split("-");
                var filesAtributes = {};
                filesAtributes["Excel Sheet"] = tempAttributes[0].trim();
                filesAtributes["Last Modified"] = tempAttributes[1].trim();
                storeAttributes.push(filesAtributes);
            }
            setTable(storeAttributes);            
            setRender(false);   
        })
        findFiles(false);

    }

    const display = async (event) => {
        const fileName = event.target.innerText;
        setLoading(false);
        setShow(true);
        const formData = new FormData();
        formData.append("sheetLoc", details.fileStorageDir + fileName);
        const API_URL = "http://localhost:8080/Excel_sheet";
        const response = axios.post(API_URL, formData).then(res => {
            setColNames(res.data);            
            setRender(false);
            reSetSize(false);
            setRenderBar(false);
            setRenderTable(false);
            setRender(false);   
            setLoading(true);       
        })
    }

    const showData = async (event) => {
        setSelectCol(event.target.innerText);
        reSetSize(true);
        setRenderBar(true);        
        setRenderTable(true);
        setRenderPie(true);
        setRender(true);
    }

    return (
        <div>
            <div className='m-2 gap-3 md:m-10 p-2 md:p-10 bg-white round-3x1 '>
                <Header category="Analyse excel" title="View Uploaded Data:" />
                <div className='gap-10'>
                    <p className='italic text-xs'> Click on excel sheet name to ingest.</p>
                    <GridComponent
                        id='gridcomp'
                        dataSource={table}
                        allowPaging
                        width='auto'
                        onClick={display}>
                        <Inject services={[Resize, Sort, ContextMenu, Filter, Page, ExcelExport, Edit, PdfExport]} />
                    </GridComponent>
                </div>
                {
                    showCol ? (
                        <div>
                            {
                                loading ? (<div className='p-2'>
                                    <p className='font-extrabold'>Excel sheet column names: </p>
                                    <p className='italic text-xs'> Click on the column name to view column attributes.</p>
                                    <div className='grid grid-cols-3'>
                                        <div className='col-span-1'>
                                            {
                                                colNames.map((item, index) => (
                                                    <button value={item} className=" text-xs flex gap-2 py-2 px-2 hover:scale-110 hover:bg-green-600 rounded" onClick={showData}>
                                                        < FiPlayCircle title="Display values" />
                                                        {item}
                                                    </button>
                                                )

                                                )
                                            }
                                        </div>
                                        {
                                            renderData ? (<div className='p-3 gap-10 col-span-2'>
                                                <p className='font-extrabold item-center text-center'>{selectedCol} </p>                                                
                                                <Table />
                                                <RangeSelector />
                                                <BarGraph />
                                                <PieChart />
                                            </div>) : (<div />)
                                        }
                                    </div>
                                </div>) : (
                                    <div className=' p-5 gap-20 fixed items-center'>
                                        <img className='text-xl items-center' src={require("../../data/FNB_LOGO.png")} alt={"Logo"} />
                                        <span className='font-extrabold'>Loading excel sheet ...</span>
                                    </div>
                                )
                            }
                        </div>
                    ) : (<div />)
                }

            </div>

        </div>
    )
}

export default ViewData