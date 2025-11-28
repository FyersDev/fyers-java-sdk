package com.tts.in;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.tts.in.model.FyersClass;
import com.tts.in.websocket.FyersSocket;
import com.tts.in.websocket.FyersSocketDelegate;

import in.tts.hsjavalib.ChannelModes;

public class App3 implements FyersSocketDelegate {
	private static Logger log6 = LogManager.getLogger(App3.class.getName());
	public FyersSocket fyersSocket;
	public FyersClass fyersClass = FyersClass.getInstance();
	public int loopCounter = 0;

	public static void main(String[] args) {
		String clientID = "";

		//ajay r
		String LiveToken = ""; 
		App3 app = new App3();
		app.fyersClass.clientId = clientID;
		app.fyersClass.accessToken = LiveToken.split(":")[1];
		app.fyersSocket = new FyersSocket(3);
		app.WebSocket();
	}

	public void WebSocket() {
		fyersSocket.webSocketDelegate = this;
		fyersSocket.ConnectHSM(ChannelModes.FULL);
		fyersSocket.setSymbolsInResponse(true);

		List<String> symList = Arrays.asList("NSE:AMBUJACEM-EQ", "NSE:BERGEPAINT-EQ", "NSE:COLPAL-EQ", "NSE:DABUR-EQ",
				"NSE:GRASIM-EQ", "NSE:HINDPETRO-EQ", "NSE:JSWSTEEL-EQ", "NSE:NTPC-EQ", "NSE:ONGC-EQ",
				"NSE:SBICARD-EQ");

		// List<String> symList = Arrays.asList("MCX:SILVERMIC26FEBFUT", "MCX:SILVER25DECFUT", "MCX:NATURALGAS25DECFUT", "MCX:NATGASMINI25DECFUT",
 		// "MCX:CRUDEOIL25DECFUT", "MCX:GOLDPETAL25NOVFUT");
		int subscriptionIndex = 0;
		int unsubscribeIndex = -1;
		int lotSize = 4;
		while (true) {
			List<String> scripList = new ArrayList<>();
			for (int i = subscriptionIndex; i < subscriptionIndex + lotSize; i++)
				scripList.add(symList.get(i % symList.size()));

			log6.info("Loop " + loopCounter + ": Subscribing " + scripList.toString());
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

				log6.info("Loop " + loopCounter + ": Unsubscribing " + scripList.toString());
				fyersSocket.UnSubscribeData(scripList);
			}
			unsubscribeIndex = subscriptionIndex;

			subscriptionIndex = (subscriptionIndex + lotSize) % symList.size();
//			log6.info("Loop " + loopCounter + ": Subscribe Index " + subscriptionIndex + ", unsubscribe Index "
//					+ unsubscribeIndex);

			try {
				log6.info("Loop " + loopCounter + ": Sleep for 5 minutes. ");
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
		log6.info("Loop " + loopCounter + ": On Index: " + index);
	}

	@Override
	public void OnScrips(JSONObject scrips) {
		System.out.println("Loop " + String.format("%4d", loopCounter) + ": @" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS")) + ", quote : " + scrips);
	}

	@Override
	public void OnDepth(JSONObject depths) {
		log6.info("Loop " + loopCounter + ": On Depth: " + depths);
	}

	@Override
	public void OnOrder(JSONObject orders) {
		log6.info("Loop " + loopCounter + ": On Orders: " + orders);
	}

	@Override
	public void OnTrade(JSONObject trades) {
		log6.info("Loop " + loopCounter + ": On Trades: " + trades);
	}

	@Override
	public void OnPosition(JSONObject positions) {
		log6.info("Loop " + loopCounter + ": On Positions: " + positions);
	}

	@Override
	public void OnOpen(String status) {
		log6.info("Loop " + loopCounter + ": On open: " + status);
	}

	@Override
	public void OnClose(String status) {
		log6.info("Loop " + loopCounter + ": *** On Close: " + status);
	}

	@Override
	public void OnError(JSONObject error) {
		// TODO Auto-generated method stub
		log6.info("Loop " + loopCounter + ": *** On Error: " + error);
		System.out.println("Loop " + loopCounter + ": *** On Error: " + error);
	}

	public void OnReconnect(JSONObject message) {
		// TODO Auto-generated method stub
		log6.info("Loop " + loopCounter + ": *** On Reconnect: " + message);
		System.out.println("Loop " + loopCounter + ": *** On Reconnect: " + message);
	}

	@Override
	public void OnMessage(JSONObject message) {
		log6.info("Loop " + loopCounter + ": On Message: " + message);
	}
}