package com.baraka.candlebar.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class CandleClientWebSocketConfig {

	@Bean

	public WebSocketConnectionManager webSocketConnectionManager() {
		WebSocketConnectionManager manager = new WebSocketConnectionManager(webSocketClient(), webSocketHandler(),
				"ws://b-mocks.dev.app.getbaraka.com:9989");
		manager.setAutoStartup(true);
		return manager;

	}

	@Bean
	public WebSocketClient webSocketClient() {
		return new StandardWebSocketClient();
	}

	@Bean
	public WebSocketHandler webSocketHandler() {
		return new CandleWebSocketHandler();
	}

}
