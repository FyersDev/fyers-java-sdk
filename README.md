<a href="https://fyers.in/"><img src="https://assets.fyers.in/images/logo.svg" align="right" /></a>
# Fyers Java SDK : fyers-api-v3 - v1.3.1

⚠️ **TESTING RELEASE** - Use this build only for testing and evaluating WebSocket reconnection.

## Quick Start

The Fyers Java SDK includes built-in logging to help you monitor connections and debug issues.

### Where to Find Logs

- **Log files**: As configured in log4j.properties (check the property 'log4j.appender.FILE.File' for the exact path)
- **Console**: Also shows in your terminal

### Basic Usage

Add logging to your application:

```java
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Test {
    private static Logger log = LogManager.getLogger(Test.class.getName());
    
    public static void main(String[] args) {
        log.info("Application started");
        
        // Your Fyers code here
        FyersSocket fyersSocket = new FyersSocket();
        
        log.info("Trading session started");
    }
}
```

### Configure Logging Level

Edit `src/main/resources/log4j.properties`:

```properties
# For your application class
log4j.logger.com.yourpackage.Test=INFO

# For FyersSocket (connection logs)
log4j.logger.com.tts.in.websocket.FyersSocket=DEBUG
```

### Log Levels
- **DEBUG**: Shows DEBUG + INFO + WARN + ERROR (most verbose)
- **INFO**: Shows INFO + WARN + ERROR
- **WARN**: Shows WARN + ERROR
- **ERROR**: Shows ERROR only (least verbose)

### Example Output

```
2025-10-09 10:20:02.463 INFO  [main] FyersSocket:228 - connect:socket.fyers.in/hsm/v1-5/prod
2025-10-09 10:20:03.072 INFO  [WebSocketConnectReadThread-14] Test:233 - OnMessage: {"code":200,"type":"cn","message":"Authentication done"}
2025-10-09 10:20:03.073 INFO  [WebSocketConnectReadThread-14] Test:208 - On open: HSM Connected
2025-10-09 10:20:03.588 INFO  [WebSocketConnectReadThread-14] Test:233 - OnMessage: {"code":200,"subscribed_symbols":["NSE:BANKNIFTY25OCT46000PE"],"type":"sub","message":"Successfully subscribed"}
2025-10-09 10:21:14.148 INFO  [WebSocketConnectReadThread-14] FyersSocket:953 - Reconnecting to socket, please wait.... Attempt: 1
```