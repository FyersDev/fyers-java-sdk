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
        String clientID = "M0R4WW1PYU-100";
        String LiveToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhcGkuZnllcnMuaW4iLCJpYXQiOjE3Mjc3NTUwMzEsImV4cCI6MTcyNzgyOTAxMSwibmJmIjoxNzI3NzU1MDMxLCJhdWQiOlsieDowIiwieDoxIiwieDoyIiwiZDoxIiwiZDoyIiwieDoxIiwieDowIl0sInN1YiI6ImFjY2Vzc190b2tlbiIsImF0X2hhc2giOiJnQUFBQUFCbS0zTVh0bzRjY2dSTmxhS3NLUHZ5NDZYN1RsN09laW5oLVVfWjJOMkNGNEVyQUxqMFc0UG0zbTFXSVF5U1R4SmR2b28wWGEyNWpOZFhkd0JqcVBiekZMZWdfaGNqcUFQcGtJdGZPVlREdkNfX2xCZz0iLCJkaXNwbGF5X25hbWUiOiJLVU1BUiBLSVNIT1JFIEtVTUFSIiwib21zIjoiSzEiLCJoc21fa2V5IjoiMjQ5MmUyMGE1YjRkMDBkZmIxODQ4ZDgyNzExZGZmMmM4MWYzNTc5MmVmNDNjMmI4YTE0NWQyZmMiLCJmeV9pZCI6IllLMDQzOTEiLCJhcHBUeXBlIjoxMDAsInBvYV9mbGFnIjoiTiJ9.srlRU8-d55f3xzdTN5R3g4kr9tUFjboW_87ZIDU1Yqw";

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