package com.baraka.candlebar.client;

import com.baraka.candlebar.dto.CandleClient;
import com.baraka.candlebar.service.CandleBarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class CandleWebSocketHandler extends TextWebSocketHandler {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CandleWebSocketHandler.class);
    @Autowired
    CandleBarService candleBarService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Client connection opened");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Client connection closed: {}", status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {

        String messageInfo = message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();

        CandleClient data = objectMapper.readValue(messageInfo, CandleClient.class);

        candleBarService.saveCandleBarClient(data.getData());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Client transport error: {}", exception.getMessage());
    }


}
