import React from 'react';

const HomePageComponent = () => {

    return (
        <div className="card">
            <div className="card-body">
                <h5 className="card-title">Technical assessment - Development</h5>
                {/* <h6 className="card-subtitle mb-2 text-muted">Showing prices for 1-50 units for all products.</h6> */}
                <div className="col-md-12">
                    <p>Create a web application using Spring boot as back-end and Vue.js front-end (React.js or Angular is also permitted)
                        <br /><a href="https://spring.io/guides/gs/spring-boot/" target="_blank" rel="noopener noreferrer">https://spring.io/guides/gs/spring-boot/</a></p>
                    <p>Using gradle as buildtool is preferred but not required</p>

                    <p>Your methodology and process is as important as the actual solution, be prepared to explain your thought process in the interview</p>

                    <h5>The price structures are as follows:</h5>
                    <ol>
                        <li>The rare product "Penguin-ears" is 20 units per carton. A carton is 175,</li>
                        <li>The product "Horseshoe" is 5 units per carton, a carton is 825</li>
                        <li>If you purchase single units, the price is acquired using the carton price multiplied by an increase of 30% (1.3). (to compensate for manual labor)</li>
                        <li>If you purchase 3 cartons or more, you will receive a 10% discount off the carton price</li>
                    </ol>


                    <h5>### 1</h5>
                    <ol>
                        <li>You are to create a price engine and a small calculation feature</li>
                        <li>The calculation will determine the price of two products for a store</li>
                        <li>The price engine is to optimize the price, meaning if you order 25 units and you have 20 units per carton, the price will be set at 1 carton and 5 single units</li>
                        <li>One GUI is to list the prices in a table, listing the actual prices for a product from 1-50 units</li>
                        <li>Another GUI is to present a simple calculator which lets the user choose product and quantity of either single units or cartons (Like a shopping cart in a web store)</li>
                        <li>The price is to be calculated server side (not in Javascript)</li>
                        <li>The service is to be created using test-driven-development (JUnit or Spock)</li>
                        <li>Use a database (e.g. PostgreSQL) to store the initial prices and products, (in the event of time shortage, this can be omitted in favor of hard coding/simple file)</li>
                        <li>The calculation logic is to be developed in Java</li>
                    </ol>

                    <h5>### 2</h5>
                    <ul>
                        <li>Send the solution as a zip-file by the evening before the interview</li>
                    </ul>

                    <h5>### 3</h5>
                    <ul>
                        <li>Bring a computer and prepare to demonstrate the application, the frameworks being used (why and how) and how you chose to structure the code.</li>
                    </ul>

                    <h5>Tips:</h5>
                    <ul>
                        <li>You will need a trial for the Ultimate Edition of IntelliJ</li>
                        <li>We accept running the application from the IDE or the command line</li>
                        <li>In the event of time shortage, prioritize a full vertical implementation (business logic, view logic and the front end implementation) and tests for the business logic. Storage implementation can be simplified in favor of these goals.</li>
                    </ul>
                </div>
            </div>
        </div >
    );
}

export default HomePageComponent;