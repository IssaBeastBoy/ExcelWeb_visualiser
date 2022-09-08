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
import { MdOutlineTableChart } from 'react-icons/md';

const Table = () => {
    const { selectedCol, setRenderTable, renderTable, range } = useStateContext();
    const [render, setRender] = useState(true);
    const [data, ChartData] = useState([]);
    const [show, setShow] = useState(false);

    if (renderTable) {
        const formData = new FormData();
        formData.append("colName", selectedCol);
        const API_URL = "http://localhost:8080/TableView";
        const response = axios.post(API_URL, formData).then(res => {
            res = res.data;
            const results = [];
            for (let parse = 0; parse < res.length; parse++) {
                let tempValue = res[parse];
                let tempDic = {};
                tempDic["Name"] = tempValue[0];
                tempDic["Customer Count"] = Number(tempValue[1]);
                tempDic["Percentage (%)"] = parseFloat(tempValue[2]);
                results.push(tempDic);
            }
            ChartData(results);
            setRenderTable(false);
            setShow(true);
        })
    }


    return (
        <div className="bg-white dark:bg-secondary-dark-bg rounded-3xl">
            {
                show ? (<div className='py-3 px-2'>
                    <p className='italic text-xs font-semibold'> Table View</p>
                    <GridComponent
                        dataSource={data}
                        allowPaging
                        allowSorting
                        toolbar={["Search"]}
                        width="auto"
                    >
                        <Inject services={[Resize, Sort, Search, ContextMenu, Filter, Toolbar, Page, ExcelExport, Edit, PdfExport]} />
                    </GridComponent>
                </div>) : (<div className=' p-5 gap-20  items-center'>
                    <MdOutlineTableChart className='hover:scale-150  scale-125' />
                    <span className='font-extrabold'>Rendering chart data ...</span>
                </div>)
            }
        </div >
    )
}

export default Table