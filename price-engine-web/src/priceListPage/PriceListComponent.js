import React, { useState, useEffect } from 'react';
import axios from 'axios';

import TableComponent from '../shared/table/TableComponent';
import * as Constants from '../shared/utils/Constants';

const PriceListComponent = () => {

    const [headers, setHeaders] = useState([]);
    const [rows, setRows] = useState([]);

    useEffect(() => {
        axios.get(`${Constants.API_URL}/v1/prices/list`, {
            params: {
                from: 1,
                to: 50
            }
        }).then(res => {
            var data = res.data

            if (data.length > 0) {
                const priceList = data;

                var headers = priceList[0].productPrices.map((prices) => {
                    return prices.productName;
                });

                headers.unshift("No of Units");

                var rows = priceList.map((price) => {
                    let row = [price.numberOfUnits];

                    price.productPrices.forEach((product) =>{
                        row.push(parseFloat(product.price).toFixed(3));
                    })

                    return row;
                });

                setHeaders(headers);
                setRows(rows);
            }
        });
    }, []);

    return (
        <div className="card">
            <div className="card-body">
                <h5 className="card-title">Price list</h5>
                <h6 className="card-subtitle mb-2 text-muted">Showing prices for 1-50 units for all products.</h6>
                <div className="col-md-12">
                    <TableComponent headers={headers} rows={rows} />
                </div>
            </div>
        </div>
    );
}

export default PriceListComponent;