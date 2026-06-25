package com.example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.tts.in.model.FyersClass;
import com.tts.in.websocket.FyersSocket;
import com.tts.in.websocket.FyersSocketDelegate;

import in.tts.hsjavalib.ChannelModes;

public class App implements FyersSocketDelegate {

    public static void main(String[] args) {
        String clientID = "";
        String LiveToken = "";

        FyersClass fyersClass = FyersClass.getInstance();
        fyersClass.clientId = clientID;
        fyersClass.accessToken = LiveToken;
        App app = new App();
        app.WebSocket();
    }

    public void WebSocket() {
        List<String> scripList = new ArrayList<>();
        scripList.add("NSE:SBIN-EQ");
        FyersSocket fyersSocket = new FyersSocket(3);
        fyersSocket.webSocketDelegate = this;
        fyersSocket.ConnectHSM(ChannelModes.LITE);
        fyersSocket.SubscribeData(scripList);
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
        System.out.println("On Error: " + error);
    }

    @Override
    public void OnMessage(JSONObject message) {
        System.out.println("OnMessage: " + message);
    }

}