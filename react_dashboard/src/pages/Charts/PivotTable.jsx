import React, { useState } from 'react';
import { useStateContext } from '../../context/ContextProvider';

import { Header } from '../../components';

const PivotTable = () => {
    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <Header category="Comparative Analysis" title="Pivat Table View" />


        </div>
    )
}

export default PivotTable