import React, { useCallback, useState } from 'react';
import { FiDownload } from 'react-icons/fi';
import { useStateContext } from '../context/ContextProvider';
import { useDropzone } from 'react-dropzone';
import axios from "axios";
import { BsTrash, BsHandThumbsDown, BsHandThumbsUp, BsExclamationTriangle } from "react-icons/bs";
import { ImFilesEmpty } from "react-icons/im";
import { AiOutlineFileDone } from "react-icons/ai";


const Uploaded_Files = () => {
    const { handleUpload, Upload, setActiveMenu, details } = useStateContext();

    const [dropDown, setDropDown] = useState(false);

    const [fileInfo, getFiles] = useState([]);

    const [removeFile, setDelete] = useState(false);

    const [fileName, setFileName] = useState("");

    const deleteFile = async () => {
        const formData = new FormData();
        formData.append("fileLoc", details.fileStorageDir + fileName);
        const API_URL = "http://localhost:8080/delUpload";
        const response = axios.post(API_URL, formData).then(res => {
            res = res.data;
            if (res) {
                alert("File " + fileName + " successfully deleted")
            }
            else {
                alert("File deletion failed. Please try again!")
            }
        })
        setDelete(false);
    }

    const delFile = (event) => {
        let name = event.target.id.split("-");
        name = name[0];
        setFileName(name.trim());
        setDelete(true);
        let id = details.fileStorageDir + event.target.id;
    }

    const getUploads = async () => {
        const formData = new FormData();
        formData.append("fileLoc", details.fileStorageDir);
        const API_URL = "http://localhost:8080/Getuploads";
        const response = axios.post(API_URL, formData).then(res => {
            res = res.data.filesName;
            getFiles(res);
        })
        setDropDown(!dropDown)
    }

    const closeDelete = event => {
        setDelete(false);
    }

    const [file, setFile] = useState();

    const onDrop = useCallback((acceptedFiles) => {
        const files = acceptedFiles[0];
        setFile(files);
        //const previewUrl = URL.createObjectURL(files);
    });

    const { getRootProps, getInputProps, isDragActive } = useDropzone({
        multiple: false,
        onDrop,
    }
    );
    const [loading, setLoading] = useState(false);
    const [Success, setSuccess] = useState(false);

    const config = {
        headers: {
            "Access-Control-Allow-Origin": "/**",
            "Access-Control-Allow-Credentials": true
        }
    };


    const uploadFile = async () => {
        try {
            setSuccess(false);
            setLoading(true);
            const formData = new FormData();
            formData.append('file', file);
            formData.append('userDetails', details.fileStorageDir);
            const API_URL = "http://localhost:8080/file";
            const response = await axios.put(API_URL, formData).then(res => {
                setFileName(res.data.fileName);
                setSuccess(true);
                setLoading(false);
            })
            //{ headers: "Access-Control-Allow-Origin": "*" })

            // setSuccess(true);
            // setLoading(true);
        } catch (err) {
            alert(err.message)
        }
    }
    return (
        <div className='p-5 gap-10'>
            <div {...getRootProps()} className=' w-full h-full gap-10'>
                Upload excel spread sheets
                <div className='items-center p-10 outline-dashed h-1/4 text-center relative justify-between gap-4' >                    
                    <input {...getInputProps()} />
                    {
                        isDragActive ?
                            <p>Drop the files here ...</p> :
                            <p>Drag 'n' drop excel files here, or click to select files</p>
                    }

                </div>

            </div>
            <div className='m-5'>
                <button type="button" className=" dropdown-toggle inline-block px-6 py-2.5 bg-green-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-green-700 hover:shadow-lg focus:bg-green-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-green-800 active:shadow-lg transition duration-150 ease-in-out flex items-center whitespace-nowrap
              " onClick={uploadFile}>
                    <FiDownload className='relative items-center' />
                    Upload</button>
            </div>
            {
                    Success ? (
                    <div className='flex gap-10'>
                        <p> <AiOutlineFileDone /> </p>
                        <p> {fileName} was successfully uploaded!</p>
                        </div>
                    ) : (
                    <div />)
            }
            <div >
                <button type="button" onClick={getUploads} className="flex dropdown-toggle px-6 py-2.5 text-white font-medium text-xs leading-tight uppercase rounded bg-blue-600 hover:bg-blue-800">
                    <ImFilesEmpty />  View Uploads
                </button>
            </div>
            {
                dropDown ? (
                    <div className="rounded-lg py-2 mt-2">
                        {
                            fileInfo.map((item) => (
                                <div className="flex gap-2 py-2 px-2">
                                    < BsTrash onClick={delFile} className="hover:scale-125" title="Delete file" id={item} /> {item}
                                </div>
                            )

                            )
                        }
                    </div>
                ) : (<div />)
            }
            {
                removeFile ? ((<div>
                    {/* MODAL FOR SUCCESSFUL Login */}
                    <div className="   bg-zinc-400 opacity-80 fixed inset-0 z-50   ">

                        <div className="flex h-screen justify-center items-center ">

                            <div className=" justify-center  bg-gray-100 py-12 px-24 border-4 border-black rounded-xl ">

                                <div className=" text-lg  text-black" >
                                    <p className='flex gap-5'> <BsExclamationTriangle /> {fileName} will be deleted</p><br />
                                    <p className='text-center'>Continue?</p>

                                </div>
                                <div className="flex gap-10 justify-center">
                                    <button onClick={deleteFile} className=" rounded px-4 py-2 text-white  bg-green-600 hover:bg-green-700">
                                        <BsHandThumbsDown />  Yes
                                    </button>
                                    <button onClick={closeDelete} className=" rounded px-4 py-2 text-white  bg-red-600 hover:bg-red-700">
                                        <BsHandThumbsUp /> No
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>

                    {/* END OF MODAL */}
                </div>)) : (<div />)
            }
        </div >
    )
}

export default Uploaded_Files