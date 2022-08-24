import React, { useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { FiSettings } from 'react-icons/fi';
import { TooltipComponent } from '@syncfusion/ej2-react-popups';
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';


import { Navbar, Footer, SideBar, ThemeSetting } from './components';
import { Calendar, Chat, Emails, Login, Notes, Profile, Tickets, Uploaded_Files, Welcome, PivotTable, Table, ViewData, Register } from './pages';
import { useStateContext } from './context/ContextProvider';
import './App.css'

const App = () => {
    const { activeMenu, login } = useStateContext();
    return (
        <div>
            <BrowserRouter>
                <div className='flex relative dark:bg-main-dark-bg'>
                    <div className='fixed right-4 bottom-4' style={{ zIndex: '1000' }}>
                        <TooltipComponent content="Settings" position="Top">
                            <button type='button' className='text-3x1 p-3 hover:bg-light-gray text-white' style={{ background: '#008080', borderRadius: '50%' }}>
                                <FiSettings />
                            </button>
                        </TooltipComponent>
                    </div>
                    {!login ?
                        (<div className={`${login ? 'w-0' : "w-fit"}`}>
                            <Routes>
                                <Route path='/' element={<Login />} />
                                <Route path='/Register' element={<Register />} />
                            </Routes>
                        </div>) :
                        (
                            <div className='w-auto'>
                                {activeMenu ?
                                    (
                                        <div className='w-72 sidebar fixed dark:bg-secondary-dark-bg bg-white'>
                                            <SideBar />
                                        </div>
                                    ) : (
                                        <div className='w-0 dark:bg-secondary-dark-bg'>
                                            <SideBar />
                                        </div>
                                    )}
                                <div className={
                                    `dark:bg-main-bg bg-main-bg min-h-screen w-full ${activeMenu ? 'md:ml-72' : 'flex-2'}`

                                }>
                                    <div className='fixed md:static bg-main-bg dark:bg-main-dark-bg navbar w-full'>
                                        <Navbar />
                                    </div>

                                    <div>
                                        <Routes>
                                            {/* Files */}
                                            <Route path='/Upload' element={<Uploaded_Files />} />

                                            {/* User Profile */}
                                            <Route path='/Profile' element={<Profile />} />

                                            {/* App */}
                                            <Route path='/Tickets' element={<Tickets />} />
                                            <Route path='/Calender' element={<Calendar />} />
                                            <Route path='/Emails' element={<Emails />} />
                                            <Route path='/Notes' element={<Notes />} />
                                            <Route path='/Chat' element={<Chat />} />

                                            {/* Home page */}
                                            <Route path='/Welcome' element={<Welcome />} />
                                            <Route path='/Register' element={<Welcome />} />
                                            <Route path='/' element={<Welcome />} />



                                            {/* Charts */}
                                            <Route path='/ViewData' element={<ViewData />} />
                                            <Route path='/Table' element={<Table />} />
                                            <Route path='/PivotTable' element={<PivotTable />} />
                                        </Routes>
                                    </div>
                                </div>
                            </div>
                        )
                    }
                </div>
            </BrowserRouter>
        </div>
    )
}

export default App
