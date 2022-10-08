import { React } from 'react';
import { FcPlus } from "react-icons/fc";
import { AiOutlineMinusCircle } from "react-icons/ai";
import { Draggable } from "react-beautiful-dnd";
import { useStateContext } from '../context/ContextProvider';

const Card = ({ data, index }) => {
    const { ticketInfo, changeTicket, deleteTick, showDelete, editTick, showEdit } = useStateContext();
    const edit = (event) => {
        changeTicket([{
            Id: data.Id,
            Status: data.Status,
            Priority: data.Priority.split(" ")[0],
            Estimate: data.Estimate.split(":")[1],
            Summary: data.Summary,
            eventType: "update",
        }]
        )
        showEdit(true);
    }

    const del = (event) => {
        changeTicket(
            [{
                Id: data.Id,
                Status: data.Status,
                Priority: data.Priority.split(" ")[0],
                Estimate: data.Estimate.split(":")[1],
                Summary: data.Summary,
                eventType: "delete",
            }]
        )
        showDelete(true);
    }
    return (
        <Draggable index={index} draggableId={`${data.Id}`}>
            {(provided) => (
                <div
                    ref={provided.innerRef}
                    {...provided.draggableProps}
                    {...provided.dragHandleProps}
                    className='bg-white rounded-md p-3 mt-3 text-xs'>
                    <label className={data.ClassName}>{data.Priority}</label> <br />  <br />
                    <label className="font-black">{data.Estimate}</label>
                    <h4>{data.Summary} </h4> <br />
                    <ul className='flex gap-10'>
                        <li className='scale-150'>
                            {data.icon}
                        </li>
                        <li className='flex gap-2'>
                            <button type="button" className='border border-dashed flex items-center
                                    rounded-full w-5 h-5' onClick={edit}>
                                <FcPlus className="w-5 h-5 text-gray-500 hover:scale-110" />
                            </button>
                            <button type="button" className='border border-dashed flex items-center
                                    rounded-full w-5 h-5' onClick={del}>
                                <AiOutlineMinusCircle className="w-5 h-5 text-gray-500 hover:scale-110" />
                            </button>
                        </li>
                    </ul>
                </div>

            )}
        </Draggable>
    );
}

export default Card;