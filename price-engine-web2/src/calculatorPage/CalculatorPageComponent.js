import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Select from 'react-select';

import TableComponent from '../shared/table/TableComponent';
import * as Constants from '../shared/utils/Constants';

const CalculatorPageComponent = () => {

    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState({});
    const [quantity, setQuantity] = useState(1);
    const [quantityType, setQuantityType] = useState(Constants.QUANTITY_TYPE_UNITS);

    const [headers, setHeaders] = useState([]);
    const [rows, setRows] = useState([]);

    const [cartTotal, setCartTotal] = useState(0);

    useEffect(() => {
        axios.get(`${Constants.API_URL}/v1/products`).then(res => {
            var data = res.data

            if (data._embedded.products.length > 0) {
                const products = data._embedded.products.map((product) => {
                    return {
                        value: product.id,
                        label: product.name
                    }
                });

                setProducts(products);
                setSelectedProduct(products[0]);
            }
        });
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();

        let params = {
            productId: selectedProduct.value
        }

        if (quantityType === Constants.QUANTITY_TYPE_UNITS) {
            params.numberOfSingleUnits = quantity;
        } else {
            params.numberOfCartons = quantity;
        }

        axios.get(`${Constants.API_URL}/v1/prices/calculate`, {
            params: params
        }).then(res => {
            var data = res.data

            setHeaders(["Product", "Number Of Cartons", "Number Of SingleUnits", "Discount", "Total Price"]);

            let newRows = [];

            rows.forEach(function (row) {
                if (row[0] !== data.productName) {
                    newRows.push(row);
                }
            });

            newRows.unshift([data.productName, data.numberOfCartons, data.numberOfSingleUnits, parseFloat(data.discount).toFixed(3), parseFloat(data.price).toFixed(3)]);

            let total = 0.00;
            newRows.forEach(row => {
                total+= parseFloat(row[4]);
            });

            setCartTotal(total);
            setRows(newRows);

            // setRows((arr) => {
            //     return [...arr, [data.productName, data.numberOfCartons, data.numberOfSingleUnits, parseFloat(data.discount).toFixed(3), parseFloat(data.price).toFixed(3)]]
            // });

            // setSelectedProduct(products[0]);
            // setQuantity(1);
            // setQuantityType(Constants.QUANTITY_TYPE_UNITS);
        });
    }

    const clear = () => {
        setHeaders([]);
        setRows([]);
        setSelectedProduct(products[0]);
        setQuantity(1);
        setQuantityType(Constants.QUANTITY_TYPE_UNITS);
    }

    return (
        <div className="card">
            <div className="card-body">
                <h5 className="card-title">Price calculator</h5>
                <h6 className="card-subtitle mb-2 text-muted">Get price by selecting the product and quantity.</h6>
                <form onSubmit={handleSubmit}>
                    <div className="row">
                        <div className="col-md-4 form-group" style={{ alignSelf: "center" }}>
                            <Select options={products} value={selectedProduct} onChange={event => setSelectedProduct(event)} />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-2 form-group">
                            <label>Quantity</label>
                            <input className="form-control" type="number" min="1" value={quantity} onChange={event => setQuantity(event.target.value)} />
                        </div>

                        <div className="form-check form-check-inline">
                            <input className="form-check-input" type="radio" name="quentityType" id="inlineRadio1" value={Constants.QUANTITY_TYPE_UNITS} checked={quantityType === Constants.QUANTITY_TYPE_UNITS} onChange={event => setQuantityType(event.target.value)} />
                            <label className="form-check-label" htmlFor="inlineRadio1">Units</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" type="radio" name="quentityType" id="inlineRadio2" value={Constants.QUANTITY_TYPE_CARTONS} checked={quantityType === Constants.QUANTITY_TYPE_CARTONS} onChange={event => setQuantityType(event.target.value)} />
                            <label className="form-check-label" htmlFor="inlineRadio2">Cartons</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <button type="submit" className="btn btn-primary">Add to cart</button>
                        </div>
                    </div>
                </form>
                <div className="row mt-4">
                    <div className="col-md-12">
                        <button className="btn-sm btn-danger" style={{ float: "right", display: rows.length === 0 ? "none" : "block" }} onClick={clear}>Clear</button>
                        <TableComponent headers={headers} rows={rows} />
                        <label style={{ display: rows.length === 0 ? "none" : "block" }}>Cart Total = {parseFloat(cartTotal).toFixed(3)}</label>
                    </div>
                </div>
            </div>

        </div >
    );
}

export default CalculatorPageComponent;