<a href="https://fyers.in/"><img src="https://assets.fyers.in/images/logo.svg" align="right" /></a>
# Fyers Java SDK : fyers-api-v3 - v2.2.0
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
 * GTT Market Orders (leg `Type`: 1 = limit, 2 = market)
 * GTT Order Tag

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
 * Quotes V2 (`GetStockQuotesV2`, POST — symbols in the body, so many more per request)
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

 #### Kill Switch

 * Get Kill Switch Status
 * Activate Kill Switch

 ### GTT Orders Usage

 | SDK Method | Description | Request Model |
 |---|---|---|
 | `PlaceGTTOrder(list)` | Place GTT Single / OCO orders | `List<GTTModel>` |
 | `ModifyGTTOrder(list)` | Modify GTT orders | `List<GTTModel>` |
 | `CancelGTTOrder(id)` | Cancel a GTT order | — |
 | `GetGTTOrderBook()` | Fetch the GTT order book | — |

 Each leg is a `GTTLeg(type, price, triggerPrice, qty)`:
 * `type`: `1` = limit, `2` = market (for market, `price` must be `0`).
 * `price` and `triggerPrice` accept decimal values.
 * `OrderTag` (optional, place only): stored as `"1:GTT" + tag`. When not set, the API uses `"2:GTTUntagged"`.

 ```java
 FyersClass fyers = FyersClass.getInstance();
 fyers.clientId = "YOUR_APP_ID";
 fyers.accessToken = "YOUR_ACCESS_TOKEN";

 GTTModel model = new GTTModel();
 model.Side = 1;
 model.Symbol = "NSE:IDEA-EQ";
 model.productType = "CNC";
 model.OrderTag = "mytag";
 model.addGTTLeg("leg1", new GTTLeg(1, 10.5, 10.4, 1));    // limit
 // model.addGTTLeg("leg1", new GTTLeg(2, 0, 10.4, 1));   // market

 List<GTTModel> orders = new ArrayList<>();
 orders.add(model);
 fyers.PlaceGTTOrder(orders);
 ```

 **Migrating from v2.1.1:** the `GTTLeg(price, triggerPrice, qty)` constructor is replaced by
 `GTTLeg(type, price, triggerPrice, qty)`, and `Price` / `TriggerPrice` are now `double`.
 Change `new GTTLeg(price, triggerPrice, qty)` to `new GTTLeg(1, price, triggerPrice, qty)` and recompile.
 Code built against v2.1.1 must be recompiled before using this version.

 ### Kill Switch Usage

 | SDK Method | Description |
 |---|---|
 | `GetKillSwitchStatus()` | Fetch the current kill switch status |
 | `ActivateKillSwitch()` | Activate the kill switch |

 > **Warning:** `ActivateKillSwitch()` blocks trading on the account. Use it with care.

 ```java
 FyersClass fyers = FyersClass.getInstance();
 fyers.clientId = "YOUR_APP_ID";
 fyers.accessToken = "YOUR_ACCESS_TOKEN";

 fyers.GetKillSwitchStatus();
 // fyers.ActivateKillSwitch();
 ```

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
 | `GetScreenersConfig()` | Fetch screener configuration (screener ids, universes, timeframes, expiries) | — |
 | `GetScreenersStandard(model)` | Run a standard screener | `ScreenersStandardModel` |
 | `GetScreenersCandlestick(model)` | Run a candlestick pattern screener | `ScreenersCandlestickModel` |
 | `GetScreenersTechnical(model)` | Run a technical indicator screener | `ScreenersTechnicalModel` |

 The screener is selected with `ScreenerId`, an id taken from the config response.
 Only the params a screener lists as supported are accepted; anything else is
 rejected by the API, so leave the rest unset.

 ```java
 FyersClass fyers = FyersClass.getInstance();
 fyers.clientId = "YOUR_APP_ID";
 fyers.accessToken = "YOUR_ACCESS_TOKEN";

 fyers.GetScreenersConfig();

 ScreenersStandardModel standardModel = new ScreenersStandardModel();
 standardModel.ScreenerId = "top_gainers";
 standardModel.Universe = "iw010";
 standardModel.Fields = "t0_close_ltp,t0_volume";
 standardModel.OrderBy = "t0_change_per";
 standardModel.Order = "desc";
 standardModel.Timeframe = "t0_5min";
 fyers.GetScreenersStandard(standardModel);

 ScreenersCandlestickModel candlestickModel = new ScreenersCandlestickModel();
 candlestickModel.ScreenerId = "dragonfly_doji";
 fyers.GetScreenersCandlestick(candlestickModel);

 ScreenersTechnicalModel technicalModel = new ScreenersTechnicalModel();
 technicalModel.ScreenerId = "macd_crossed_above_signal_line";
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

