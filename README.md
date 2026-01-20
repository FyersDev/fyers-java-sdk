<a href="https://fyers.in/"><img src="https://assets.fyers.in/images/logo.svg" align="right" /></a>
# Fyers Java SDK : fyers-api-v3 - v1.4.0
The official Fyers Java SDK for API-V3 Users [FYERS API](https://fyers.in/products/api/).

Fyers API is a set of REST-like APIs that provide integration with our in-house trading platform with which you can build your own customized trading applications.

## Documentation
- [Fyers API documentation](https://myapi.fyers.in/docsv3)

## Usage
- Create a Maven Project, Use VS Code (or any preferred IDE) to create a Maven project.

- Inside the project’s root folder, create a new folder named ```repo```. Kindly refer to ```fyers-javasdk```folder for project’s structure.

- Download the [fyersjavasdk.jar](https://github.com/FyersDev/fyers-java-sdk/tree/master/dist/) file and place it in the project's root folder.

- Open Git Bash and navigate to the folder containing the JAR file. Run the following Maven command to deploy the JAR to your local repository:
```bash
mvn deploy:deploy-file -Durl="file:repo" -Dfile=fyersjavasdk.jar -DgroupId=com.tts.in -DartifactId=fyersjavasdk -Dpackaging=jar -Dversion=1.0
```

- Open the project in VS Code and navigate to the pom.xml file. Add the following dependencies inside the <dependencies> tag:
```xml
    <dependencies>
        <dependency>
            <groupId>com.tts.in</groupId>
            <artifactId>fyersjavasdk</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20211205</version>
        </dependency>
    </dependencies>
```

- Add the following inside the <repositories> tag:
```xml
    <repositories>
        <repository>
        <id>project.local</id>
        <name>project</name>
        <url>file:${project.basedir}/repo</url>
        </repository>
    </repositories>
```
Please review the pom.xml file in ```fyers-javasdk``` folder for better clarity.
Note: If the previous fyersjavasdk package is already in use, clear the Maven cache to ensure that Maven fetches fresh dependencies and avoids conflicts with outdated packages.

- In the terminal, run the following command to clean and package the project:```mvn clean package```

- After the build is successful, the SDK is ready to use. Open the App.java file located in ```src/main``` and try running the sample code from the [Fyers API documentation.](https://myapi.fyers.in/docsv3)


 ## APIs Supported by Fyers Java SDK
 
 #### User
 
 * Profile
 * Funds
 * Holdings

 #### Transaction Info
 
 * Orders
 * Positions
 * Trades

 #### Order Placement
 
 * Place Order
 * Place MultiLeg Order

 #### GTT Orders
 
 * GTT Single
 * GTT OCO
 * GTT Modify Order
 * GTT Cancel Order
 * GTT Order Book

 #### Other Transactions
 
 * Modify Orders
 * Cancel Order
 * Exit Position
 * Convert Position

 #### Broker Config
 
 * Market Status

 #### Data Api
 
 * History
 * Quotes
 * Market Depth
 * Option Chain

 #### Price Alert

 * Create Price Alert
 * Get Price Alerts
 * Modify Price Alert
 * Delete Price Alert
 * Enable/Disable Price Alert

 ## Web Socket

 #### General Socket

 * General Socket (orders)
 * General Socket (trades)
 * General Socket (positions)
 * General Socket (general)

 #### Market Data

 * Market Data Symbol Update
 * Market Data Indices Update
 * Market Data Depth Update
 * Market Data Lite-Mode

 ## Release Notes

 * Added Price Alert APIs: Create, Get, Update, Delete, and Toggle price alerts
 * Enhanced Order Socket with id_fyers field for better order tracking and identification

