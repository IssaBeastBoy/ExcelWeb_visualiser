import React, { useState } from 'react';
import { ScheduleComponent, Day, Week, WorkWeek, Month, Agenda, Inject, Resize, DragAndDrop } from '@syncfusion/ej2-react-schedule';

import { scheduleData } from '../data/dummy';
import { Header } from '../components' ;
import { useStateContext } from '../context/ContextProvider';
import axios from "axios";
import { BsCalendarWeek } from 'react-icons/bs';

const Calender = () => {
    const { details, setState } = useStateContext();

    const [renderCalender, setCalender] = useState(true);
    const [renderSchedule, setShow] = useState(true);

    const [greet, setGreet] = useState();

    const [schedule, setSchedule] = useState({});

    const setClick = (event) => {
        console.log(event);
        if (event.requestType == "eventChange") {
            let itemChange = event.data;
            if (itemChange.occurrence != undefined) {
                itemChange = itemChange.occurrence;
                const formData = new FormData();
                formData.append('userId', details.loginName);
                formData.append('eventType', "new")
                formData.append('Id', itemChange.Id);
                formData.append('Subject', itemChange.Subject);
                formData.append('StartTime', itemChange.StartTime);
                formData.append('IsAllDay', `${itemChange.IsAllDay}`);
                formData.append('EndTime', itemChange.EndTime);
                formData.append('cancel', `${itemChange.cancel}`);
                formData.append('Location', `${itemChange.Location}`);
                formData.append('Description', `${itemChange.Description}`);
                formData.append('RecurrenceRule', `${itemChange.RecurrenceRule}`);
                formData.append('EndTimezone', itemChange.EndTimezone);
                formData.append('StartTimezone', itemChange.StartTimezone);
                formData.append('RecurrenceID', `${itemChange.RecurrenceID}`);
                formData.append('RecurrenceException', itemChange.RecurrenceException);
                const API_URL = "http://localhost:8080/updateCalendar";
                const request = axios.post(API_URL, formData).then(res => {
                    setState(res.data);
                    setCalender(true);
                })

            }
            else if (itemChange.Id != undefined) {
                const formData = new FormData();
                formData.append('userId', details.loginName);
                formData.append('eventType', "update")
                formData.append('Id', itemChange.Id);
                formData.append('Subject', itemChange.Subject);
                formData.append('StartTime', itemChange.StartTime);
                formData.append('IsAllDay', `${itemChange.IsAllDay}`);
                formData.append('EndTime', itemChange.EndTime);
                formData.append('cancel', `${itemChange.cancel}`);
                formData.append('Location', `${itemChange.Location}`);
                formData.append('Description', itemChange.Description);
                formData.append('RecurrenceRule', `${itemChange.RecurrenceRule}`);
                formData.append('EndTimezone', itemChange.EndTimezone);
                formData.append('StartTimezone', itemChange.StartTimezone);
                formData.append('RecurrenceID', `${itemChange.RecurrenceID}`);
                formData.append('RecurrenceException', itemChange.RecurrenceException);
                const API_URL = "http://localhost:8080/updateCalendar";
                const request = axios.post(API_URL, formData).then(res => {
                    setState(res.data);
                    setCalender(true);
                })
            }
        }
        else if (event.requestType == "eventCreate") {
            let itemChange = event.data[0];
            const formData = new FormData();
            formData.append('userId', details.loginName);
            formData.append('eventType', "new")
            formData.append('Id', itemChange.Id);
            formData.append('Subject', itemChange.Subject);
            formData.append('StartTime', itemChange.StartTime);
            formData.append('IsAllDay', `${itemChange.IsAllDay}`);
            formData.append('EndTime', itemChange.EndTime);
            formData.append('cancel', `${itemChange.cancel}`);
            formData.append('Location', `${itemChange.Location}`);
            formData.append('Description', `${itemChange.Description}`);
            formData.append('RecurrenceRule', `${itemChange.RecurrenceRule}`);
            formData.append('EndTimezone', itemChange.EndTimezone);
            formData.append('StartTimezone', itemChange.StartTimezone);
            const API_URL = "http://localhost:8080/updateCalendar";
            const request = axios.post(API_URL, formData).then(res => {
                setState(res.data);
                setCalender(true);
            })

            console.log(event.data[0]);
        }
        else if (event.requestType == "eventRemove") {
            let itemChange = event.data[0];
            const formData = new FormData();
            formData.append('userId', details.loginName);
            formData.append('eventType', "delete")
            formData.append('Id', itemChange.Id);
            formData.append('Subject', itemChange.Subject);
            formData.append('StartTime', itemChange.StartTime);
            formData.append('IsAllDay', `${itemChange.IsAllDay}`);
            formData.append('EndTime', itemChange.EndTime);
            formData.append('cancel', `${itemChange.cancel}`);
            formData.append('Location', `${itemChange.Location}`);
            formData.append('Description', `${itemChange.Description}`);
            formData.append('RecurrenceRule', `${itemChange.RecurrenceRule}`);
            formData.append('EndTimezone', itemChange.EndTimezone);
            formData.append('StartTimezone', itemChange.StartTimezone);
            const API_URL = "http://localhost:8080/updateCalendar";
            const request = axios.post(API_URL, formData).then(res => {
                setState(res.data);
                setCalender(true);
            })
            console.log(event.data[0]);
        }
    }

    if (renderCalender) {
        const currTime = new Date().toLocaleString();
        const dateTime = currTime.split(",");
        const hours = dateTime[1].split(":");
        const day = dateTime[1].split(' ');
        const period = day[day.length - 1];
        const hour = Number(hours[0].trim());
        if (period == "PM") {
            if (hour == 12 || (hour >= 1 && hour <= 5)) {
                setGreet("Good Afternoon");
            }
            else if (hour >= 6 && hour <= 7) {
                setGreet("Good Evening");
            }
            else {
                setGreet("Good Night")
            }
        }
        else {
            setGreet("Good Morning")
        }
        setShow(true);
        const calenderItems = details.calendar.split(">");
        const calendarSchedule = [];
        if (calenderItems[0] != "") {
            for (let parse = 0; parse < calenderItems.length; parse++) {
                const tempcalendarDic = {};
                let calenderAttri = calenderItems[parse].split('^');
                if (calenderAttri[0] != "") {
                    for (let item = 0; item < calenderAttri.length; item++) {
                        let calendarItem = calenderAttri[item].split('-');
                        if (calendarItem[0] == "Id") {
                            tempcalendarDic[calendarItem[0]] = Number(calendarItem[1]);
                        }
                        else if (calendarItem[0] == "cancel" || calendarItem[0] == "IsAllDay") {
                            if (calendarItem[1] == "false") {
                                tempcalendarDic[calendarItem[0]] = false;
                            }
                            else {
                                tempcalendarDic[calendarItem[0]] = true;
                            }
                        }
                        else if (calendarItem[0] == "EndTime" || calendarItem[0] == "StartTime") {
                            const temp = new Date(Number(calendarItem[1])).toISOString();
                            tempcalendarDic[calendarItem[0]] = temp;
                        }
                        else {
                            tempcalendarDic[calendarItem[0]] = calendarItem[1];
                        }
                    }
                    tempcalendarDic["CategoryColor"] = '#1aaa55';
                    if (tempcalendarDic != {}) {
                        calendarSchedule.push(tempcalendarDic);
                    }
                }

            }
            setSchedule(calendarSchedule);
            console.log(calendarSchedule);
        }
        setShow(false);
        setCalender(false);
    }

    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3x1 ">
            <Header category="App" title="Calendar" />
            <p className='p-2 text-sm'>
                {greet}, <i>{details.name} {details.surname}</i>. These are all the items in your calender:
            </p>

            {
                renderSchedule ? (<p className='flex items-center relative gap-2 justify-center text-xl font-black text-center p-2'><BsCalendarWeek /> Loading calendar...</p>) : (
                    <ScheduleComponent actionBegin={setClick} height="650px" eventSettings={{ dataSource: schedule }} selectedDate={new Date(2021, 0, 10)}>

                <Inject services={[Day, Week, WorkWeek, Month, Agenda, Resize, DragAndDrop]}/>

            </ScheduleComponent>
                )
            }

        </div>
    )
}

export default Calender