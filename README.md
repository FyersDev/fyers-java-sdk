<a href="https://fyers.in/"><img src="https://assets.fyers.in/images/logo.svg" align="right" /></a>
# Fyers Java SDK : fyers-api-v3 - v2.1.0
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

 #### Transaction Info
 
 * Order History
 * Trade History

 #### Order Placement
 
 * Place Order
 * Place MultiLeg Order

 #### GTT Orders
 
 * GTT Single
 * GTT OCO
 * GTT Modify Order
 * GTT Cancel Order
 * GTT Order Book

 #### Smart Orders

 * Smart Limit
 * Smart Trail
 * Smart Step
 * Smart SIP
 * Modify Smart Order
 * Cancel Smart Order
 * Pause Smart Order
 * Resume Smart Order
 * Smart Order Book

 #### Smart Orders

 * Create Smart Exit
 * Fetch Smart Exit
 * Modify / Activate / Deactivate Smart Exit

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

 #### Expired Historical Data

 * Futures Chain
 * Expiry Dates
 * Underlying Symbols
 * FNO Historical Data

 #### Screeners

 * Config
 * Query
 * Candlestick
 * Technical

 #### Price Alert

 * Create Price Alert
 * Get Price Alerts
 * Modify Price Alert
 * Delete Price Alert
 * Enable/Disable Price Alert

 ### Expired Historical Data Usage

 | SDK Method | Description | Request Model / Params |
 |---|---|---|
 | `GetFuturesChain(model)` | Fetch futures chain for an index/symbol | `FuturesChainModel` |
 | `GetHistoryExpiryDates(underlyingSymbol, rangeFrom, rangeTo, dateFormat)` | Fetch expiry dates for an underlying | — |
 | `GetHistoryUnderlyingSymbols(underlyingSymbol, expiryDate)` | Fetch underlying symbols for an expiry | — |
 | `GetHistoryFNOExpired(model)` | Fetch expired FNO historical candles | `HistoryFNOExpiredModel` |

 ```java
 FyersClass fyers = FyersClass.getInstance();
 fyers.clientId = "YOUR_APP_ID";
 fyers.accessToken = "YOUR_ACCESS_TOKEN";

 FuturesChainModel futuresModel = new FuturesChainModel();
 futuresModel.Symbol = "NSE:NIFTY50-INDEX";
 fyers.GetFuturesChain(futuresModel);

 fyers.GetHistoryExpiryDates("NSE:NIFTY50-INDEX", "2024-01-01", "2024-12-31", 1);

 fyers.GetHistoryUnderlyingSymbols("NSE:NIFTY50-INDEX", "2024-11-28");

 HistoryFNOExpiredModel historyModel = new HistoryFNOExpiredModel();
 historyModel.Symbol = "NSE:NIFTY24NOV22500CE";
 historyModel.Resolution = "5";
 historyModel.DateFormat = "1";
 historyModel.RangeFrom = "2024-11-01";
 historyModel.RangeTo = "2024-11-28";
 historyModel.Greeks = 1;
 fyers.GetHistoryFNOExpired(historyModel);
 ```

 ### Screeners Usage

 | SDK Method | Description | Request Model |
 |---|---|---|
 | `GetScreenersConfig()` | Fetch screener configuration | — |
 | `GetScreenersQuery(model)` | Run a query-based screener | `ScreenersQueryModel` |
 | `GetScreenersCandlestick(model)` | Run a candlestick pattern screener | `ScreenersCandlestickModel` |
 | `GetScreenersTechnical(model)` | Run a technical indicator screener | `ScreenersTechnicalModel` |

 ```java
 FyersClass fyers = FyersClass.getInstance();
 fyers.clientId = "YOUR_APP_ID";
 fyers.accessToken = "YOUR_ACCESS_TOKEN";

 fyers.GetScreenersConfig();

 ScreenersQueryModel queryModel = new ScreenersQueryModel();
 queryModel.Screener = "top_gainers";
 queryModel.Universe = "iw001";
 queryModel.Fields = "market_cap";
 queryModel.OrderBy = "t0_per_change";
 queryModel.Order = "desc";
 fyers.GetScreenersQuery(queryModel);

 ScreenersCandlestickModel candlestickModel = new ScreenersCandlestickModel();
 candlestickModel.Screener = "dragonfly_doji";
 fyers.GetScreenersCandlestick(candlestickModel);

 ScreenersTechnicalModel technicalModel = new ScreenersTechnicalModel();
 technicalModel.Screener = "macd_crossed_above_signal_line";
 fyers.GetScreenersTechnical(technicalModel);
 ```

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
 - Added `GetFuturesChain(FuturesChainModel)` — fetch futures chain for an index/symbol
 - Added `GetHistoryExpiryDates(underlyingSymbol, rangeFrom, rangeTo, dateFormat)` — fetch expiry dates for an underlying
 - Added `GetHistoryUnderlyingSymbols(underlyingSymbol, expiryDate)` — fetch contracts for an expiry date
 - Added `GetHistoryFNOExpired(HistoryFNOExpiredModel)` — fetch expired FNO historical candles with `greeks` support
 - Request models: `FuturesChainModel`, `HistoryFNOExpiredModel`

