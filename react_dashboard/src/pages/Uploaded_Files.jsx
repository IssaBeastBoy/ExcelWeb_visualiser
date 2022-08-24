import React, { useCallback, createContext, useContext, useState } from 'react';
import { FiDownload } from 'react-icons/fi';
import { useStateContext } from '../context/ContextProvider';
import { useDropzone } from 'react-dropzone';
import axios from "axios";

const Uploaded_Files = () => {
    const { handleUpload, Upload, setActiveMenu, details } = useStateContext();

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
    const [downloadUri, setDownloadUri] = useState();


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
            formData.append('userDetails', details);
            console.log(formData);
            const API_URL = "http://localhost:8080/file";
            const response = await axios.put(API_URL, formData, config).then(res => {
                setDownloadUri(res.data.fileDownloadUri);
            setSuccess(true);
            setLoading(false);
                console.log(res.data.userDetails);
            })
            //{ headers: "Access-Control-Allow-Origin": "*" })

            // setSuccess(true);
            // setLoading(true);
        } catch (err) {
            alert(err.message)
        }
    }
    return (
        <div className='p-5'>
            <div {...getRootProps()} className=' w-full h-full'>
                Upload excel spread sheets
                <div className='items-center h-5/6 outline-dashed text-center relative justify-between gap-4' >
                    <FiDownload className='text-5xl relative items-center' />
                    <input {...getInputProps()} />
                    {
                        isDragActive ?
                            <p>Drop the files here ...</p> :
                            <p>Drag 'n' drop excel files here, or click to select files</p>
                    }

                </div>
                {
                    Success ? (
                        <div>
                            <a href={downloadUri}>
                                <p>{file.name}</p>
                            </a>
                        </div>
                    ) : (
                        <div>
                            <p>Error</p>
                        </div>
                    )
                }
            </div>
            <div>
                <button type="button" onClick={uploadFile}> Upload</button>
            </div>
        </div >
    )
}

export default Uploaded_Files