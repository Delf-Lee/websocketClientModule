package com.za.tutorial.websocket;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
	public void destroy() {}
	public void init(EndpointConfig arg0) {}
	public ChatMessage decode(String message) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		chatMessage.setName(jsonObject.getString("name"));
		chatMessage.setMessage(jsonObject.getString("message"));
		return chatMessage;
	}
	public boolean willDecode(String message) {
		boolean flag = true;
		try {Json.createReader(new StringReader(message)).readObject();} 
		catch (Exception e) {flag = false;}
		return flag;
	}
}
