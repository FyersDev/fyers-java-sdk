package com.tts.in;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.tts.in.token.token;
import com.tts.in.model.FyersClass;
import com.tts.in.websocket.FyersSocket;
import com.tts.in.websocket.FyersSocketDelegate;

import in.tts.hsjavalib.ChannelModes;

public class App3 implements FyersSocketDelegate {
	private static Logger log3 = LogManager.getLogger(App3.class.getName());
	public FyersSocket fyersSocket;
	public FyersClass fyersClass = FyersClass.getInstance();
	public int loopCounter = 0;

	public static void main(String[] args) {
		String clientID = "";

		String LiveToken = token.LIVE_TOKEN_1;
		App3 App3 = new App3();
		App3.fyersClass.clientId = LiveToken.split(":")[0];
		App3.fyersClass.accessToken = LiveToken.split(":")[1];
		App3.fyersSocket = new FyersSocket(3);
		App3.WebSocket();
	}

	public void WebSocket() {
		fyersSocket.webSocketDelegate = this;
		fyersSocket.ConnectHSM(ChannelModes.FULL);
		fyersSocket.setSymbolsInResponse(true);

		// List<String> symList = Arrays.asList("MCX:SILVER26SEPFUT", "MCX:NICKEL25DECFUT", "MCX:CRUDEOIL25DECFUT", "MCX:MCXMETLDEX25DECFUT",
		// 		"MCX:NATGASMINI25DECFUT", "MCX:NATURALGAS25DECFUT", "MCX:ELECDMBL25DECFUT", "MCX:GOLDPETAL25DECFUT", "MCX:GOLDTEN25DECFUT",
		// 		"MCX:GOLDGUINEA25DECFUT");

		List<String> symList = Arrays.asList("NSE:AMBUJACEM-EQ", "NSE:BERGEPAINT-EQ", "NSE:COLPAL-EQ", "NSE:DABUR-EQ",
				"NSE:GRASIM-EQ", "NSE:HINDPETRO-EQ", "NSE:JSWSTEEL-EQ", "NSE:NTPC-EQ", "NSE:ONGC-EQ",
				"NSE:SBICARD-EQ");
				
		int subscriptionIndex = 0;
		int unsubscribeIndex = -1;
		int lotSize = 4;
		log3.info("Starting WebSocket for {}"+ App3.class.getName());
		while (true) {
			List<String> scripList = new ArrayList<>();
			for (int i = subscriptionIndex; i < subscriptionIndex + lotSize; i++)
				scripList.add(symList.get(i % symList.size()));

			log3.info(App3.class.getName()+" Loop " + loopCounter + ": Subscribing " + scripList.toString());
			fyersSocket.SubscribeData(scripList);
			try {
				// sleep for 5 seconds.
				Thread.sleep(1000 * 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (unsubscribeIndex != -1) {
				scripList.clear();
				for (int i = unsubscribeIndex; i < unsubscribeIndex + lotSize; i++)
					scripList.add(symList.get(i % symList.size()));

				log3.info(App3.class.getName()+" Loop " + loopCounter + ": Unsubscribing " + scripList.toString());
				fyersSocket.UnSubscribeData(scripList);
			}
			unsubscribeIndex = subscriptionIndex;

			subscriptionIndex = (subscriptionIndex + lotSize) % symList.size();
//			log3.info(App3.class.getName()+" Loop " + loopCounter + ": Subscribe Index " + subscriptionIndex + ", unsubscribe Index "
//					+ unsubscribeIndex);

			try {
				log3.info(App3.class.getName()+" Loop " + loopCounter + ": Sleep for 5 minutes. ");
				// sleep for 5 minutes.
				Thread.sleep(1000 * 60); // * 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			loopCounter++;
		}
	}

	@Override
	public void OnIndex(JSONObject index) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": On Index: " + index);
	}

	@Override
	public void OnScrips(JSONObject scrips) {
		System.out.println(App3.class.getName()+" Loop " + String.format("%4d", loopCounter) + ": @" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS")) + ", quote3 : " + scrips);
	}

	@Override
	public void OnDepth(JSONObject depths) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": On Depth: " + depths);
	}

	@Override
	public void OnOrder(JSONObject orders) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": On Orders: " + orders);
	}

	@Override
	public void OnTrade(JSONObject trades) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": On Trades: " + trades);
	}

	@Override
	public void OnPosition(JSONObject positions) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": On Positions: " + positions);
	}

	@Override
	public void OnOpen(String status) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": On open: " + status);
	}

	@Override
	public void OnClose(String status) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": *** On Close: " + status);
	}

	@Override
	public void OnError(JSONObject error) {
		// TODO Auto-generated method stub
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": *** On Error: " + error);
		System.out.println(App3.class.getName()+" Loop " + loopCounter + ": *** On Error: " + error);
	}

	public void OnReconnect(JSONObject message) {
		// TODO Auto-generated method stub
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": *** On Reconnect: " + message);
		System.out.println(App3.class.getName()+" Loop " + loopCounter + ": *** On Reconnect: " + message);
	}

	@Override
	public void OnMessage(JSONObject message) {
		log3.info(App3.class.getName()+" Loop " + loopCounter + ": On Message: " + message);
	}
}