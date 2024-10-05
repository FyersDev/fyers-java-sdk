package com.tts.in;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.tts.in.model.FyersClass;
import com.tts.in.model.Leg;
import com.tts.in.model.MultiLegModel;
import com.tts.in.model.PlaceOrderModel;
import com.tts.in.model.PositionConversionModel;
import com.tts.in.model.StockHistoryModel;
import com.tts.in.utilities.OrderType;
import com.tts.in.utilities.OrderValidity;
import com.tts.in.utilities.ProductType;
import com.tts.in.utilities.TransactionType;
import com.tts.in.utilities.Tuple;
import com.tts.in.websocket.FyersSocket;
import com.tts.in.websocket.FyersSocketDelegate;


public class App implements FyersSocketDelegate {

    public static void main(String[] args) {
        String clientID = "";
        String secretKey = "";
        String appHashId = getSHA256Hash(clientID, secretKey);

        String redirectURI = "https://trade.fyers.in/api-login/redirect-uri/index.html";
        String code = "";

        String LiveToken = "";

        FyersClass fyersClass = FyersClass.getInstance();
        fyersClass.clientId = clientID;
        fyersClass.accessToken = LiveToken;
        App app = new App();

        // Request Parameters
        // app.GetGenerateCode(redirectURI, fyersClass);
        // app.GetGenerateToken(code, appHashId, fyersClass);

        // User
        // app.GetFunds(fyersClass);
        // app.GetProfile(fyersClass);
        // app.GetHoldings(fyersClass);

        // Transaction Info
        // app.GetOrders(fyersClass, "all");
        // app.GetPositions(fyersClass);
        // app.GetTradeBook(fyersClass);
        // app.GetTradeByTag(fyersClass);

        // Order Placement
        // app.PlaceOrder(fyersClass);   
        // app.PlaceMultipleOrder(fyersClass);
        // app.PlaceMultiLegOrder(fyersClass);

        // Other Transactions
        // app.ModifySingleOrder(fyersClass);
        // app.ModifyMultipleOrder(fyersClass);
        // app.CancelSingleOrder(fyersClass);
        // app.CancelMultipleOrder(fyersClass);
        // app.ExitPosition(fyersClass);
        // app.ExitPositionBySegmentSidePrdType(fyersClass);
        // app.PositionConversion(fyersClass);

        // Broker Config
        // app.GetMarketStatus(fyersClass);

        // Data Api
        // app.GetStockHistory(fyersClass);
        // app.GetStockQuotes(fyersClass);
        // app.GetMarketDepth(fyersClass);
        // app.GetOptionChain(fyersClass);

        // Web Socket
        // app.WebSocket();
    }

    public void GetGenerateCode(String redirectURI, FyersClass fyersClass) {
        fyersClass.GenerateCode(redirectURI);
    }

    public void GetGenerateToken(String code, String appHashId, FyersClass fyersClass) {
        JSONObject jsonObject = fyersClass.GenerateToken(code, appHashId);
        System.out.println(jsonObject);
    }

    public void GetProfile(FyersClass fyersClass) {
        Tuple<JSONObject, JSONObject> ProfileResponseTuple = fyersClass.GetProfile();

        if (ProfileResponseTuple.Item2() == null) {
            System.out.println("Profile: " + ProfileResponseTuple.Item1());
        } else {
            System.out.println("Profile Error: " + ProfileResponseTuple.Item2());
        }
    }

    public void GetFunds(FyersClass fyersClass) {
        Tuple<JSONObject, JSONObject> ResponseTuple = fyersClass.GetFunds();

        if (ResponseTuple.Item2() == null) {
            System.out.println("Fund: " + ResponseTuple.Item1());
        } else {
            System.out.println("Fund Error: " + ResponseTuple.Item2());
        }
    }

    public void GetHoldings(FyersClass fyersClass) {
        Tuple<JSONObject, JSONObject> holdingTuple = fyersClass.GetHoldings();
        if (holdingTuple.Item2() == null) {
            System.out.println("Holdings: " + holdingTuple.Item1());
        } else {
            System.out.println("Holdings Error: " + holdingTuple.Item2());
        }
    }

    public void GetTradeBook(FyersClass trades) {
        Tuple<JSONObject, JSONObject> tradeTuple = trades.GetTradeBook();
        if (tradeTuple.Item2() == null) {
            System.out.println("TradeBook:" + tradeTuple.Item1()); 
        }else {
            System.out.println("TradeBook Error: " + tradeTuple.Item2());
        }
    }

    public void GetTradeByTag(FyersClass trades) {
        String tag = "1:tag1";
        Tuple<JSONObject, JSONObject> tradeTuple = trades.GetTradeByTag(tag);
        if (tradeTuple.Item2() == null) {
            System.out.println("TradeBook By Tag:" + tradeTuple.Item1()); 
        }else {
            System.out.println("TradeBook Error: " + tradeTuple.Item2());
        }
    }

    public void GetStockHistory(FyersClass fyersClass) {
        StockHistoryModel model = new StockHistoryModel();
        model.Symbol = "NSE:SBIN-EQ";
        model.Resolution = "30";
        model.DateFormat = "1";
        model.RangeFrom = "2021-01-01";
        model.RangeTo = "2022-02-03";
        model.ContFlag = 1;

        Tuple<JSONObject, JSONObject> stockTuple = fyersClass.GetStockHistory(model);
        if (stockTuple.Item2() == null) {
            System.out.println("Stock History: " + stockTuple.Item1());
        } else {
            System.out.println("Stock History Error: " + stockTuple.Item2());
        }
    }

    public void GetMarketStatus(FyersClass fyersClass) {
        Tuple<JSONObject, JSONObject> stockTuple = fyersClass.GetMarketStatus();

        if (stockTuple.Item2() == null) {
            System.out.println("Market Status:" + stockTuple.Item1());
        } else {
            System.out.println("Market Status Error:" + stockTuple.Item2());
        }
    }

    public void GetMarketDepth(FyersClass fyersClass) {
        Tuple<JSONObject, JSONObject> ResponseTuple = fyersClass.GetMarketDepth("NSE:TCS-EQ", 0);

        if (ResponseTuple.Item2() == null) {
            System.out.println("Market Depth: " + ResponseTuple.Item1());
        } else {
            System.out.println("Market Depth Error:" + ResponseTuple.Item2());
        }
    }

    public void GetStockQuotes(FyersClass fyersClass) {
        String symbols = "NSE:TCS-EQ";

        Tuple<JSONObject, JSONObject> stockTuple = fyersClass.GetStockQuotes(symbols);

        if (stockTuple.Item2() == null) {
            System.out.println("Stock Quotes:" + stockTuple.Item1());
        } else {
            System.out.println("Error: " + stockTuple.Item2());
        }

    }

    public void PlaceOrder(FyersClass fyersClass) {
        PlaceOrderModel model = new PlaceOrderModel();
        model.Symbol = "NSE:IDEA-EQ";
        model.Qty = 1;
        model.OrderType = OrderType.MarketOrder.getDescription();
        model.Side = TransactionType.Buy.getValue();
        model.ProductType = ProductType.BRACKETORDER;
        model.LimitPrice = 0;
        model.StopPrice = 1000;
        model.OrderValidity = OrderValidity.DAY;
        model.DisclosedQty = 0;
        model.OffLineOrder = false;
        model.StopLoss = 0;
        model.TakeProfit = 0;
        model.OrderTag = "tag1";

        Tuple<JSONObject, JSONObject> ResponseTuple = fyersClass.PlaceOrder(model);
        if (ResponseTuple.Item2() == null) {
            System.out.println("Order ID: " + ResponseTuple.Item1());
        } else {
            System.out.println("Place order Message : " + ResponseTuple.Item2());
        }

    }

    public void PlaceMultipleOrder(FyersClass order) {
        List<PlaceOrderModel> placeOrdersList = new ArrayList<>();

        PlaceOrderModel model = new PlaceOrderModel();
        model.Symbol = "BSE:KRETTOSYS-XT";
        model.Qty = 1;
        model.OrderType = OrderType.MarketOrder.getDescription();
        model.Side = TransactionType.Buy.getValue();
        model.ProductType = ProductType.CNC;
        model.LimitPrice = 0;
        model.StopPrice = 0;
        model.OrderValidity = OrderValidity.DAY;
        model.DisclosedQty = 0;
        model.OffLineOrder = false;
        model.StopLoss = 0;
        model.TakeProfit = 0;
        model.OrderTag = "ManualOrderTag1";

        PlaceOrderModel orderModel2 = new PlaceOrderModel();
        orderModel2.Symbol = "NSE:IDEA-EQ";
        orderModel2.Qty = 1;
        orderModel2.OrderType = OrderType.MarketOrder.getDescription();
        orderModel2.Side = TransactionType.Buy.getValue();
        orderModel2.ProductType = ProductType.CNC;
        orderModel2.LimitPrice = 0;
        orderModel2.StopPrice = 0;
        orderModel2.OrderValidity = OrderValidity.DAY;
        orderModel2.DisclosedQty = 0;
        orderModel2.OffLineOrder = false;
        orderModel2.StopLoss = 0;
        orderModel2.TakeProfit = 0;
        orderModel2.OrderTag = "tag1";

        PlaceOrderModel orderModel3 = new PlaceOrderModel();
        orderModel3.Symbol = "BSE:IFL-T";
        orderModel3.Qty = 1;
        orderModel3.OrderType = OrderType.MarketOrder.getDescription();
        orderModel3.Side = TransactionType.Buy.getValue();
        orderModel3.ProductType = ProductType.CNC;
        orderModel3.LimitPrice = 0;
        orderModel3.StopPrice = 0;
        orderModel3.OrderValidity = OrderValidity.DAY;
        orderModel3.DisclosedQty = 0;
        orderModel3.OffLineOrder = false;
        orderModel3.StopLoss = 0;
        orderModel3.TakeProfit = 0;
        orderModel3.OrderTag = "tag2";

        placeOrdersList.add(model);
        placeOrdersList.add(orderModel2);
        placeOrdersList.add(orderModel3);

        Tuple<JSONObject, JSONObject> ResponseTuple = order.PlaceMultipleOrders(placeOrdersList);
        if (ResponseTuple.Item2() == null) {
            System.out.println("Orders:" + ResponseTuple.Item1());
        } else {
            System.out.println("Place order Message : " + ResponseTuple.Item2());
        }

    }

    public void PlaceMultiLegOrder(FyersClass order) {
        List<MultiLegModel> placeOrdersList = new ArrayList<>();
        MultiLegModel model = new MultiLegModel();
        model.OrderTag = "tag1";
        model.ProductType = ProductType.INTRADAY;
        model.OfflineOrder = false;
        model.OrderType = "3L";
        model.Validity = "IOC";
        model.addLeg("leg1", new Leg("NSE:SBIN24SEPFUT", 750, 1, 1, 800));
        model.addLeg("leg2", new Leg("NSE:SBIN24OCTFUT", 750, 1, 1, 790));
        model.addLeg("leg3", new Leg("NSE:SBIN24SEP900CE", 750, 1, 1, 0.05));

        placeOrdersList.add(model);

        Tuple<JSONObject, JSONObject> ResponseTuple = order.PlaceMultiLegOrder(placeOrdersList);
        if (ResponseTuple.Item2() == null) {
            System.out.println("Orders:" + ResponseTuple.Item1());
        } else {
            System.out.println("Place order Message :" + ResponseTuple.Item2());
        }
    }

    public void ModifySingleOrder(FyersClass order) {
        PlaceOrderModel model = new PlaceOrderModel();
        model.OrderId = "24022300000049";
        model.Qty = 10;
        model.OrderType = OrderType.MarketOrder.getDescription();
        // model.Side = (int)TransactionType.Buy.getValue();
        model.LimitPrice = 0;
        model.StopPrice = 0;
        Tuple<JSONObject, JSONObject> ResponseTuple = order.ModifyOrder(model);
        if (ResponseTuple.Item2() == null) {
            System.out.println("Order ID: " + ResponseTuple.Item1());
        } else {
            System.out.println("Modify order Message : " + ResponseTuple.Item2());
        }
    }

    public void ModifyMultipleOrder(FyersClass order) {
        List<PlaceOrderModel> placeOrdersList = new ArrayList<>();
        PlaceOrderModel model = new PlaceOrderModel();
        model.OrderId = "24022900000007";
        model.Qty = 5;
        model.OrderType = OrderType.LimitOrder.getDescription();
        model.Side = TransactionType.Buy.getValue();
        model.LimitPrice = 0;

        PlaceOrderModel model2 = new PlaceOrderModel();
        model2.OrderId = "24022900000006";
        model2.Qty = 20;
        model2.OrderType = OrderType.LimitOrder.getDescription();
        model2.Side = TransactionType.Buy.getValue();
        model2.LimitPrice = 0;

        placeOrdersList.add(model);
        placeOrdersList.add(model2);

        Tuple<JSONObject, JSONObject> ResponseTuple = order.ModifyMultipleOrders(placeOrdersList);
        if (ResponseTuple.Item2() == null) {
            System.out.println("Orders ID: " + ResponseTuple.Item1());
        } else {
            System.out.println("Place order Message : " + ResponseTuple.Item2());
        }
    }

    public void CancelSingleOrder(FyersClass order) {
        String OrderId = "24091000443820";

        Tuple<JSONObject, JSONObject> ResponseTuple = order.CancelOrder(OrderId);
        if (ResponseTuple.Item2() == null) {
            System.out.println("Orders ID: " + ResponseTuple.Item1());
        } else {
            System.out.println("Place order Message : " + ResponseTuple.Item2());
        }
    }

    public void CancelMultipleOrder(FyersClass order) {
        List<String> orderIdList = new ArrayList<>();
        orderIdList.add("24091000459798");
        orderIdList.add("24091000459804");
        Tuple<JSONObject, JSONObject> ResponseTuple = order.CancelMultipleOrders(orderIdList);
        if (ResponseTuple.Item2() == null) {
            System.out.println("Orders ID: " + ResponseTuple.Item1());
        } else {
            System.out.println("Place order Message : " + ResponseTuple.Item2());
        }
    }

    public void GetOrders(FyersClass order, String ordersBy) {
        if (ordersBy.equalsIgnoreCase("all")) {
            Tuple<JSONObject, JSONObject> orderList = order.GetAllOrders();
            if (orderList.Item2() == null) {
                System.out.println("Orders :" + orderList.Item1());
            } else {
                System.out.println("Orders Error:" + orderList.Item2());
            }
        } else if (ordersBy.equalsIgnoreCase("id")) {
            String orderId = "24091000292032";
            Tuple<JSONObject, JSONObject> orderList = order.GetOrderById(orderId);
            if (orderList.Item2() == null) {
                System.out.println("Order by Id:" + orderList.Item1());
            } else {
                System.out.println("Order by Id Error:" + orderList.Item2());
            }
        } else if (ordersBy.equalsIgnoreCase("tag")) {
            String orderTag = "2:Untagged";
            Tuple<JSONObject, JSONObject> orderList = order.GetOrderByTag(orderTag);
            if (orderList.Item2() == null) {
                System.out.println("Order by Tag:" + orderList.Item1());
            } else {
                System.out.println("Order by Tag Error:" + orderList.Item2());
            }
        }
    }

    public void GetPositions(FyersClass positions) {
        Tuple<JSONObject, JSONObject> positionTuple = positions.GetPositions();
        if (positionTuple.Item2() == null) {
            System.out.println("Positions:" + positionTuple.Item1());
        } else {
            System.out.println("Positions Error: " + positionTuple.Item2());
        }
    }

    public void ExitPosition(FyersClass positions) {
        String positionId = "NSE:IDEA-EQ-CNC";
        String positionId2 = "NSE:INFY-EQ-INTRADAY";

        List<String> positionIDs = new ArrayList<>();
        positionIDs.add(positionId);
        //positionIDs.Add(positionId2);

        Tuple<JSONObject, JSONObject> jObject = positions.ExitPositions(positionIDs);
        if (jObject.Item2() == null) {
            System.out.println("Position Message: " + jObject.Item1());
        } else {
            System.out.println("Position Error: " + jObject.Item2());
        }
    }

    public void ExitPositionBySegmentSidePrdType(FyersClass positions) {
        int[] sides = new int[]{1};
        int[] segments = new int[]{12};
        String[] products = new String[]{ProductType.INTRADAY};

        Tuple<JSONObject, JSONObject> jObject = positions.ExitPositionBySegmentSidePrdType(sides, segments, products);
        if (jObject.Item2() == null) {
            System.out.println("Position Message: " + jObject.Item1());
        } else {
            System.out.println("Position Error: " + jObject.Item2());
        }
    }

    public void PositionConversion(FyersClass positions) {
        PositionConversionModel positionConversionModel = new PositionConversionModel();

        positionConversionModel.Symbol = "NSE:BANKNIFTY24FEBFUT-INTRADAY";
        positionConversionModel.Side = 1;
        positionConversionModel.ConvertQty = 15;
        positionConversionModel.ConvertFrom = "INTRADAY";
        positionConversionModel.ConvertTo = "MARGIN";
        positionConversionModel.Overnight = 0;

        Tuple<JSONObject, JSONObject> jObject = positions.PositionConversion(positionConversionModel);
        if (jObject.Item2() == null) {
            System.out.println("Position Message: " + jObject.Item1());
        } else {
            System.out.println("Position error Message: " + jObject.Item2());
        }
    }

    public void GetOptionChain(FyersClass fyersClass) {
        String symbol = "NSE:TCS-EQ";
        int strikeCount = 1;
        String timestamp = "";

        Tuple<JSONObject, JSONObject> stockTuple = fyersClass.GetOptionChain(symbol, strikeCount, timestamp);

        if (stockTuple.Item2() == null) {
            System.out.println("OptionChain:" + stockTuple.Item1());
        } else {
            System.out.println("OptionChain Error: " + stockTuple.Item2());
        }

    }

    public String getSHA256Hash(String clientId, String secretKey) {
        String input = String.format("%s:%s", clientId, secretKey);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void WebSocket() {
        //Order Socket
        List<String> list = new ArrayList<>();
        list.add("orders");
        // list.add("trades");
        // list.add("positions"); 
        FyersSocket fyersSocket = new FyersSocket(3);
        fyersSocket.webSocketDelegate = this;
        fyersSocket.Connect();
        fyersSocket.Subscribe(list);

        // Data Socket - Symbol Update
        // List<String> scripList = new ArrayList<>();
        // scripList.add("NSE:SBIN-EQ");
        // FyersSocket fyersSocket = new FyersSocket(3);
        // fyersSocket.webSocketDelegate = this; 
        // fyersSocket.ConnectHSM(ChannelModes.LITE);       
        // fyersSocket.SubscribeData(scripList);

        // Data Socket - Symbol Update LITE
        // List<String> scripList = new ArrayList<>();
        // scripList.add("NSE:SBIN-EQ");
        // FyersSocket fyersSocket = new FyersSocket(3);
        // fyersSocket.webSocketDelegate = this; 
        // fyersSocket.ConnectHSM(ChannelModes.LITE);       
        // fyersSocket.SubscribeData(scripList);

        // Data Socket - Index Data
        // FyersSocket fyersSocket = new FyersSocket(3);
        // fyersSocket.webSocketDelegate = this;
        // List<String> scripList = new ArrayList<>();
        // scripList.add("NSE:FINNIFTY-INDEX");
        // fyersSocket.ConnectHSM(ChannelModes.LITE);
        // fyersSocket.SubscribeData(scripList);

        // Data Socket - Market Depth
        // FyersSocket fyersSocket = new FyersSocket(3);
        // fyersSocket.webSocketDelegate = this; 
        // List<String> scripList = new ArrayList<>();
        // scripList.add("NSE:SBIN-EQ");
        // fyersSocket.ConnectHSM(ChannelModes.FULL);
        // fyersSocket.SubscribeDepth(scripList);

        // Market Data Unsubscribe
        // FyersSocket fyersSocket = new FyersSocket(3);
        // fyersSocket.webSocketDelegate = this;
        // List<String> scripList = new ArrayList<>();
        // scripList.add("NSE:SBIN-EQ");
        // fyersSocket.ConnectHSM(ChannelModes.FULL);
        // fyersSocket.SubscribeData(scripList);
        // try 
        // {
        //     TimeUnit.SECONDS.sleep(3);
        //     fyersSocket.UnSubscribeData(scripList);
        //     fyersSocket.CloseHSM();
        // }
        // catch (InterruptedException e) 
        // {            
        //     e.printStackTrace();
        // }
    }

    @Override
    public void OnIndex(JSONObject index) {
        System.out.println("On Index: " + index);
    }

    @Override
    public void OnScrips(JSONObject scrips) {
        System.out.println("On Scrips: " + scrips);
    }

    @Override
    public void OnDepth(JSONObject depths) {
        System.out.println("On Depth: " + depths);
    }

    @Override
    public void OnOrder(JSONObject orders) {
        System.out.println("On Orders: " + orders);
    }

    @Override
    public void OnTrade(JSONObject trades) {
        System.out.println("On Trades: " + trades);
    }

    @Override
    public void OnPosition(JSONObject positions) {
        System.out.println("On Positions: " + positions);
    }

    @Override
    public void OnOpen(String status) {
        System.out.println("On open: " + status);
    }

    @Override
    public void OnClose(String status) {
        System.out.println("On Close: " + status);
    }

    @Override
    public void OnError(JSONObject error) {
        // TODO Auto-generated method stub
        System.out.println("On Error: " + error);
    }

    @Override
    public void OnMessage(JSONObject message) {
        // TODO Auto-generated method stub
        System.out.println("OnMessage: " + message);
    }

}
