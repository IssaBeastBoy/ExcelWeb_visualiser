import React from 'react';
import { KanbanComponent, ColumnsDirective, ColumnDirective  } from '@syncfusion/ej2-react-kanban';

import { kanbanData, kanbanGrid } from '../data/dummy';
import { Header } from '../components';
import { useStateContext } from '../context/ContextProvider';

const Tickets = () => {
    const { details } = useStateContext();
    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3x1 ">
        <Header category="App" title="Tickets" />
            <p className='p-2 text-sm'>
                Hi, <i>{details.name}</i>. These are all your task for the current sprint.
            </p>
        <KanbanComponent id="tickets" dataSource={kanbanData} cardSettings={{ contentField: 'Summary', headerField: 'Id'}} keyField="Status">
            <ColumnsDirective>
                {kanbanGrid.map((item, index) => <ColumnDirective key={index} {...item}/> )}
            </ColumnsDirective>
        </KanbanComponent>
        </div>
    )
}

export default Tickets