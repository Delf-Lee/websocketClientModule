package com.za.tutorial.websocket;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Client extends Application {
	public static void main(String[] args) throws Exception {
		launch(args);
	}
	public void start(Stage primaryStage) throws DeploymentException, IOException, URISyntaxException {
		primaryStage.setTitle("WebSocket Tutorial 09");
		final TextField textField = new TextField();
		Button sendButton = new Button();
		sendButton.setText("Send");
		HBox hBox = new HBox();
		hBox.getChildren().addAll(textField, sendButton);	
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setFocusTraversable(false);
		final ChatroomClientEndpoint chatroomClientEndPoint = new ChatroomClientEndpoint(textArea);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(textArea, hBox);
		StackPane stackPane = new StackPane();
		sendButton.setOnAction(event -> {
				try {
					chatroomClientEndPoint.sendMessage(textField.getText());
					textField.clear();
				} catch (IOException | EncodeException e) {
					e.printStackTrace();
				}
		});
		stackPane.getChildren().add(vBox);
		primaryStage.setScene(new Scene(stackPane, 250, 200));
		primaryStage.show();
	}
}