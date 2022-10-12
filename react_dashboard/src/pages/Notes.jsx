import React, { useState } from 'react';
import { HtmlEditor, Image, Inject, Link, QuickToolbar, RichTextEditorComponent,Toolbar } from '@syncfusion/ej2-react-richtexteditor';
import { SiMicrosoftonenote } from "react-icons/si";

import { EditorData } from '../data/dummy';
import { Header } from '../components';
import { useStateContext } from '../context/ContextProvider';

const Notes = () => {
    const { notes, setNotes } = useStateContext();
    const [loadNotes, getNotes] = useState(true);
    const [selected, setSelected] = useState({ Content: "" });

    if (loadNotes) {

    }

    const saveNote = (event, editor) => {
        console.log(selected);
        var name = document.getElementById('noteName').value;
        console.log(name);
        var content = document.getElementById('Content').value;
        console.log(editor.getHTML());
    }

    return (
        <div className= "m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <Header category = "App" title = "Editor" />
            <div className='p-2'>
                <input id='noteName' placeholder="Name of note..." type='text' className='border-transparent' />
            </div>
            <RichTextEditorComponent id='Content' onChange={saveNote} value={selected.Content} >
                <Inject services={[HtmlEditor, Toolbar, Link, QuickToolbar]} />
            </RichTextEditorComponent>
            <button onClick={saveNote} className='rounded flex md-1 p-1 gap-2 bg-lime-500 hover:scale-105 hover:bg-green-600'>
                <SiMicrosoftonenote /> Save note
            </button>

        </div>
    )
}

export default Notes