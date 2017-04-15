package com.za.tutorial.websocket;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
@ClientEndpoint(decoders = {ChatMessageDecoder.class}, encoders = {ChatMessageEncoder.class})
public class ChatroomClientEndpoint {
	Session session = null;
	TextArea textArea = null;
	public ChatroomClientEndpoint(TextArea textArea) throws DeploymentException, IOException, URISyntaxException {
		URI uRI = new URI("ws://localhost:8080/WebSocketPrj03/chatServerEndpoint");
		ContainerProvider.getWebSocketContainer().connectToServer(this, uRI);	
		this.textArea = textArea;
	}
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}
	@OnMessage
	public void onMessage(ChatMessage chatMessage) {
		Platform.runLater(() -> textArea.appendText(chatMessage.getName()+": "+chatMessage.getMessage()+"\n"));
	}
	public void sendMessage(String message) throws IOException, EncodeException {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setMessage(message);
		session.getBasicRemote().sendObject(chatMessage);
	}
}