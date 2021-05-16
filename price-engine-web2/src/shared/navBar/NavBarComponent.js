import React from 'react';
import { NavLink } from 'react-router-dom';

const NavBarComponent = () => {

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <NavLink to="/" className="navbar-brand">Price Engine</NavLink>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <NavLink to="/price-list" className="nav-link" activeClassName="active">Price List</NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink to="/calculator" className="nav-link" activeClassName="active">Calculator</NavLink>
                    </li>
                </ul>
            </div>
        </nav>
    );
}

export default NavBarComponent;