import { React, useState, useEffect } from 'react';
import { KanbanComponent, ColumnsDirective, ColumnDirective  } from '@syncfusion/ej2-react-kanban';
import { IoTicketOutline } from "react-icons/io5";
import {
    FcDataBackup, FcPlus, FcApproval, FcSynchronize, FcSupport,
    FcHighPriority, FcLowPriority, FcMediumPriority, FcExpired
} from "react-icons/fc";
import { DragDropContext, Draggable, Droppable } from "react-beautiful-dnd";
import { kanbanData, kanbanGrid } from '../data/dummy';
import { Header } from '../components';
import axios from "axios";
import { useStateContext } from '../context/ContextProvider';
import CardItem from "../components/Card";

const Tickets = () => {
    const { details, setState, Board, setBoard, ticketInfo, deleteTick, showDelete, editTick, showEdit } = useStateContext();
    const [newTicket, addTicket] = useState(false);

    const [backlog, setBack] = useState([]);
    const [open, setOpen] = useState([]);
    const [prog, setProg] = useState([]);
    const [done, setDone] = useState([]);

    const [renderBoard, showBoard] = useState(true);
    const [ticket, setDetails] = useState({
        Summary: "", Estimate: "", Priority: "", Status: "",
    })

    const onDragEnd = (re) => {
        console.log(re);
        console.log(re.destination.droppableId);
        if (!re.destination) return;
        const formData = new FormData();
        formData.append('userId', details.loginName);
        formData.append('eventType', "moveCard");
        formData.append('ticketLocation', re.destination.droppableI);
        formData.append('Status', re.destination.droppableId);
        formData.append('Id', Board[parseInt(re.source.droppableId)].items[re.source.index].Id);
        formData.append('Summary', Board[parseInt(re.source.droppableId)].items[re.source.index].Summary);
        formData.append('Estimate', Board[parseInt(re.source.droppableId)].items[re.source.index].Estimate.split(":")[1].trim());
        formData.append('Priority', Board[parseInt(re.source.droppableId)].items[re.source.index].Priority.split(" ")[0].trim());
        const API_URL = "http://localhost:8080/updateTickets";
        const request = axios.post(API_URL, formData).then(res => {
            setState(res.data);
            showBoard(true);
        })

    };

    if (renderBoard) {
        Board[0]["items"] = [];
        Board[1]["items"] = [];
        Board[2]["items"] = [];
        Board[3]["items"] = [];
        setTicket(details.tickets);
    }

    function setTicket(ticketsString) {
        const items = ticketsString.split(">");
        for (let index = 0; index < items.length; index++) {
            if (items[index] != "") {
                const tickets = items[index].split("^");
                let tempDic = {};
                let addLoc = "";
                for (let parse = 0; parse < tickets.length; parse++) {
                    const attribute = tickets[parse].split('-');
                    if (attribute[0] == "Id") {
                        tempDic[attribute[0]] = attribute[1];
                    }
                    else if (attribute[0] == "Priority") {
                        tempDic[attribute[0]] = attribute[1] + " Priority";
                        if (attribute[1] == 'Low') {
                            tempDic["icon"] = <FcMediumPriority className='rounded-full' />;
                            tempDic["ClassName"] = 'bg-gradient-to-r from-yellow-700 to-yellow-400 px-2 py-1 rounded text-white text-xs';
                        }
                        else if (attribute[1] == 'Minor') {
                            tempDic["icon"] = <FcLowPriority className='rounded-full' />;
                            tempDic["ClassName"] = 'bg-gradient-to-r from-green-800 to-green-500 px-2 py-1 rounded text-white text-xs';
                        }
                        else if (attribute[1] == 'High') {
                            tempDic["icon"] = <FcHighPriority className='rounded-full' />;
                            tempDic["ClassName"] = 'bg-gradient-to-r from-orange-700 to-orange-400 px-2 py-1 rounded text-white text-xs';

                        }
                        else if (attribute[1] == 'Critical') {
                            tempDic["icon"] = <FcExpired className='rounded-full' />;
                            tempDic["ClassName"] = 'bg-gradient-to-r from-red-900 to-red-600 px-2 py-1 rounded text-white text-xs';

                        }

                    }
                    else if (attribute[0] == "Estimate") {
                        tempDic[attribute[0]] = "Story points: " + attribute[1];
                    }
                    else if (attribute[0] == "Status") {
                        tempDic[attribute[0]] = attribute[1];
                        if (attribute[1] == 'Backlog') {
                            addLoc = 'Backlog';
                        }
                        else if (attribute[1] == 'Open') {
                            addLoc = 'Open';
                        }
                        else if (attribute[1] == 'InProgress') {
                            addLoc = 'InProgress';
                        }
                        else {
                            addLoc = 'Close';
                        }
                    }
                    else {
                        tempDic[attribute[0]] = attribute[1];
                    }
                }
                if (addLoc == 'Backlog') {
                    Board[0]["items"].push(tempDic);
                    //backlog.push(tempDic);
                }
                else if (addLoc == 'Open') {
                    Board[1]["items"].push(tempDic);
                    //open.push(tempDic);
                }
                else if (addLoc == 'InProgress') {
                    Board[2]["items"].push(tempDic);
                    //prog.push(tempDic);
                }
                else {
                    Board[3]["items"].push(tempDic);
                    //done.push(tempDic);
                }

            }
        }
        showBoard(false);
    }

    const ticketDetails = event => {
        event.preventDefault();
        console.log(ticket);
        if (ticket.Summary == '' || ticket.Estimate == '' || ticket.Priority == '') {
            alert("Error: \nPlease fill in all fields.")
        }
        else {
            const formData = new FormData();
            formData.append('userId', details.loginName);
            formData.append('eventType', "new");
            formData.append('Summary', ticket.Summary);
            formData.append('Estimate', ticket.Estimate);
            formData.append('Priority', ticket.Priority);
            formData.append('Status', 'Backlog');
            const API_URL = "http://localhost:8080/updateTickets";
            const request = axios.post(API_URL, formData).then(res => {
                setState(res.data);
                addTicket(false);
                showBoard(true);
            })
        }
    }

    const updateTicket = event => {
        console.log(ticketInfo);
        event.preventDefault();
        if (ticketInfo[0].eventType == "update") {
            const formData = new FormData();
            formData.append('userId', details.loginName);
            formData.append('eventType', "cardChange")
            if (ticket.Summary == '' && ticket.Estimate == '' && ticket.Priority == '' && ticket.Status == '') {
                showEdit(false);
            }
            else {
                if (ticket.Summary == '') {
                    formData.append('Summary', ticketInfo[0].Summary);
                }
                else {
                    formData.append('Summary', ticket.Summary);
                }
                if (ticket.Estimate == '') {
                    formData.append('Estimate', ticketInfo[0].Estimate);
                }
                else {
                    formData.append('Estimate', ticket.Estimate);
                }
                if (ticket.Priority == '') {
                    formData.append('Priority', ticketInfo[0].Priority);
                }
                else {
                    formData.append('Priority', ticket.Priority);
                }
                if (ticket.Status == '') {
                    formData.append('Status', ticketInfo[0].Status);
                }
                else {
                    formData.append('Status', ticket.Status);
                }
                formData.append('Id', ticketInfo[0].Id);
                const API_URL = "http://localhost:8080/updateTickets";
                const request = axios.post(API_URL, formData).then(res => {
                    setState(res.data);
                    showBoard(true);
                    showEdit(false);
                })
            }
        }
        else if (ticketInfo[0].eventType == "delete") {
            const formData = new FormData();
            formData.append('userId', details.loginName);
            formData.append('eventType', "cardRemove");
            formData.append('Id', ticketInfo[0].Id);
            formData.append('Summary', ticketInfo[0].Summary);
            formData.append('Estimate', ticketInfo[0].Estimate);
            formData.append('Priority', ticketInfo[0].Priority);
            formData.append('Status', ticketInfo[0].Status);
            const API_URL = "http://localhost:8080/updateTickets";
            const request = axios.post(API_URL, formData).then(res => {
                setState(res.data);
                showBoard(true);
                showDelete(false);
            })
        }
        event.preventDefault();
    }


    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3x1 ">
        <Header category="App" title="Tickets" />
            <p className='p-1 text-sm'>
                Hi, <i>{details.name}</i>. These are all your task for the current sprint:
            </p>
            {
                renderBoard ? (<div />) : (
                    <div>
                        <DragDropContext onDragEnd={onDragEnd}>
                            <div className='grid grid-cols-4 gap-5 my-5'>
                                {Board.map((board, bIndex) => {
                                    return (
                                        <div key={board.name}>
                                            <Droppable droppableId={`${bIndex}`}>
                                                {(provided, snapshot) => (
                                                    <div
                                                        {...provided.droppableProps}
                                                        ref={provided.innerRef}
                                                    >
                                                        <div
                                                            className={`bg-gray-100 rounded-md shadow-md
                            flex flex-col relative overflow-hidden
                            ${snapshot.isDraggingOver && "bg-green-100"}`}
                                                        >
                                                            <span
                                                                className="w-full h-1 bg-gradient-to-r from-pink-700 to-red-200
                          absolute inset-x-0 top-0"
                                                            ></span>
                                                            <h4 className=" p-3 flex justify-between items-center mb-2">
                                                                <span className="text-base text-gray-600">
                                                                    {board.name}
                                                                </span>
                                                                {board.icon}
                                                            </h4>

                                                            <div className="overflow-y-auto overflow-x-hidden h-auto"
                                                                style={{ maxHeight: 'calc(100vh - 290px)' }}>
                                                                {board.items.length > 0 &&
                                                                    board.items.map((item, iIndex) => {
                                                                        return (
                                                                            <CardItem
                                                                                key={item.Id}
                                                                                data={item}
                                                                                index={iIndex}
                                                                                className="m-3"
                                                                            />
                                                                        );
                                                                    })}
                                                                {provided.placeholder}
                                                            </div>
                                                        </div>
                                                    </div>
                                                )}
                                            </Droppable>
                                        </div>
                                    );
                                })}
                            </div>

                        </DragDropContext>
                        <button type='button' className='p-1 m-3 flex gap-2 bg-sky-500 hover:bg-sky-700 rounded align-bottom' onClick={() => addTicket(true)}> <IoTicketOutline />Add ticket</button>
                        {
                            newTicket ? ((<div>
                                {/* MODAL FOR ADDING TICKET  */}
                                <div className="   bg-zinc-200 opacity-80 fixed inset-0 z-50   ">

                                    <div className="flex h-screen justify-center items-center ">

                                        <div className="bg-black py-12 px-24 border-4 border-black rounded-xl ">

                                            <div >
                                                <form onSubmit={ticketDetails} className="gap-5" id="newTicket">
                                                    <h4 className='text-lg text-white m-1 justi-left'>Ticket details:</h4>

                                                    <div>
                                                        <label className='text-lg text-white' >Priority: </label>
                                                        <select required className="form-control m-2 font-extrabold bg-black text-white" form="newTicket" onChange={event => setDetails({ ...ticket, Priority: event.target.value })} value={ticket.name}>
                                                            <option name="" selected disabled hidden>Choose here</option>
                                                            <option name="Minor">Minor</option>
                                                            <option name="Low">Low</option>
                                                            <option name="High">High</option>
                                                            <option name="Critical">Critical</option>
                                                        </select>
                                                    </div>
                                                    <div className="form-group">
                                                        <label className='text-lg text-white'>Estimate Points: </label>
                                                        <select required className="form-control m-2 font-extrabold bg-black text-white" form="newTicket" onChange={event => setDetails({ ...ticket, Estimate: event.target.value })} value={ticket.name}>
                                                            <option name="" selected disabled hidden>Choose here</option>
                                                            <option name="1">1</option> <option name="2">2</option> <option name="3">3</option>
                                                            <option name="4">4</option> <option name="5">5</option> <option name="6">6</option>
                                                            <option name="7">7</option> <option name="8">8</option> <option name="9">9</option>
                                                            <option name="10">10</option> <option name="11">11</option> <option name="12">12</option>
                                                            <option name="13">13</option> <option name="14">14</option><option name="15">15</option>
                                                            <option name="16">16</option> <option name="17">17</option> <option name="18">18</option>
                                                            <option name="19">19</option> <option name="20">20</option> <option name="21">21</option>
                                                        </select>
                                                    </div>

                                                    <div className="form-group">
                                                        <label className='text-lg text-white'>Summary: </label><br />
                                                        <textarea form="newTicket" name='Summary' required className="form-control m-2 bg-white font-extrabold" placeholder="Description..." onChange={event => setDetails({ ...ticket, Summary: event.target.value })} value={ticket.surname} />
                                                    </div>
                                                    <div className="flex m-2 gap-10 justify-center">
                                                        <button onClick={ticketDetails} type="submit" className=" rounded px-4 py-2 text-white  bg-green-600 hover:bg-green-700">
                                                            Add
                                                        </button>
                                                        <button onClick={() => addTicket(false)} className=" rounded px-4 py-2 text-white  bg-red-600 hover:bg-red-700">
                                                            Close
                                                        </button>
                                                    </div>
                                                </form>

                                            </div>

                                        </div>
                                    </div>
                                </div>

                                {/* END OF MODAL */}
                            </div>)) : (<div />)
                        }
                        {
                            editTick ? ((<div>
                                {/* MODAL FOR EDITIING TICKET */}
                                <div className="   bg-zinc-200 opacity-80 fixed inset-0 z-50   ">

                                    <div className="flex h-screen justify-center items-center ">

                                        <div className="bg-black py-12 px-24 border-4 border-black rounded-xl ">

                                            <div >
                                                <form onSubmit={updateTicket} className="gap-5" id="editTicket">
                                                    <h4 className='text-lg text-white m-1 justi-left'>Edit ticket:</h4>

                                                    <div>
                                                        <label className='text-lg text-white' >Priority: </label>
                                                        <select className="form-control m-2 font-extrabold bg-black text-white" form="editTicket" onChange={event => setDetails({ ...ticket, Priority: event.target.value })} value={ticket.name}>
                                                            <option name={ticketInfo[0].Priority} selected hidden>{ticketInfo[0].Priority}</option>
                                                            <option name="Minor">Minor</option>
                                                            <option name="Low">Low</option>
                                                            <option name="High">High</option>
                                                            <option name="Critical">Critical</option>
                                                        </select>
                                                    </div>
                                                    <div className="form-group">
                                                        <label className='text-lg text-white'>Estimate Points: </label>
                                                        <select className="form-control m-2 font-extrabold bg-black text-white" form="editTicket" onChange={event => setDetails({ ...ticket, Estimate: event.target.value })} value={ticket.name}>
                                                            <option name={ticketInfo[0].Estimate} selected hidden>{ticketInfo[0].Estimate}</option>
                                                            <option name="1">1</option> <option name="2">2</option> <option name="3">3</option>
                                                            <option name="4">4</option> <option name="5">5</option> <option name="6">6</option>
                                                            <option name="7">7</option> <option name="8">8</option> <option name="9">9</option>
                                                            <option name="10">10</option> <option name="11">11</option> <option name="12">12</option>
                                                            <option name="13">13</option> <option name="14">14</option><option name="15">15</option>
                                                            <option name="16">16</option> <option name="17">17</option> <option name="18">18</option>
                                                            <option name="19">19</option> <option name="20">20</option> <option name="21">21</option>
                                                        </select>
                                                    </div>
                                                    <div className="form-group">
                                                        <label className='text-lg text-white'>Ticket status: </label>
                                                        <select className="form-control m-2 font-extrabold bg-black text-white" form="editTicket" onChange={event => setDetails({ ...ticket, Status: event.target.value })} value={ticket.name}>
                                                            <option name={ticketInfo[0].Status} selected hidden>{ticketInfo[0].Status}</option>
                                                            <option name="Backlog">Backlog</option>
                                                            <option name="Open">Open</option>
                                                            <option name="InProgress">InProgress</option>
                                                            <option name="Closed">Closed</option>
                                                        </select>
                                                    </div>

                                                    <div className="form-group">
                                                        <label className='text-lg text-white'>Summary: </label><br />
                                                        <textarea form="editTicket" name='Summary' className="form-control m-2 bg-white font-extrabold" defaultValue={ticketInfo[0].Summary} onChange={event => setDetails({ ...ticket, Summary: event.target.value })} value={ticket.surname} />
                                                    </div>
                                                    <div className="flex m-2 gap-10 justify-center">
                                                        <button onClick={updateTicket} type="submit" className=" rounded px-4 py-2 text-white  bg-green-600 hover:bg-green-700">
                                                            Save
                                                        </button>
                                                        <button onClick={() => showEdit(false)} className=" rounded px-4 py-2 text-white  bg-red-600 hover:bg-red-700">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </form>

                                            </div>

                                        </div>
                                    </div>
                                </div>

                                {/* END OF MODAL */}
                            </div>)) : (<div />)
                        }
                        {
                            deleteTick ? ((<div>
                                {/* MODAL FOR Deleting TICKET */}
                                <div className="   bg-zinc-200 opacity-80 fixed inset-0 z-50   ">

                                    <div className="flex h-screen justify-center items-center ">

                                        <div className="bg-black py-12 px-24 border-4 border-black rounded-xl ">

                                            <div className='gap-5'>
                                                <h4 className='text-lg text-white m-1 justi-left'>Are you sure you want to delete this ticket?</h4>
                                                <div className='flex gap-20'>
                                                    <button onClick={updateTicket} className=" rounded px-4 py-2 text-white  bg-green-600 hover:bg-green-700">
                                                        Yes
                                                    </button>
                                                    <button onClick={() => showDelete(false)} className=" rounded px-4 py-2 text-white  bg-red-600 hover:bg-red-700">
                                                        Cancel
                                                    </button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                                {/* END OF MODAL */}
                            </div>)) : (<div />)
                        }
                    </div>
                )
            }
            {/* <div>
                <KanbanComponent actionBegin={updateTicket} id="tickets" dataSource={kanbanData} cardSettings={{ contentField: 'Summary', headerField: 'Id', grabberField: "Color" }} keyField="Status">
                    <ColumnsDirective>
                        {kanbanGrid.map((item, index) => <ColumnDirective key={index} {...item} />)}
            </ColumnsDirective>
        </KanbanComponent>

        </div> */}
        </div>

    )
}

export default Tickets