import React from 'react';

const TableComponent = ({ headers, rows }) => {

    const buildHeader = (headers) => {
        return (
            <thead>
                <tr key="header">
                    {headers.map((row, index) =>
                        <th key={`th-${index}`}>{row}</th>
                    )}
                </tr>
            </thead>
        );
    };

    const buildBody = (rows) => {
        return (
            <tbody>
                {rows.map((price, rowIndex) =>
                    <tr key={`row-${rowIndex}`}>
                        {price.map((product, priceIndex) =>
                            <td key={`item-${rowIndex}-${priceIndex}`}>{product}</td>
                        )}
                    </tr>
                )}
            </tbody>
        );
    };

    return (
        <div>
            <table className="table table-striped">
                {buildHeader(headers)}
                {buildBody(rows)}
            </table>
        </div>
    );
}

export default TableComponent;