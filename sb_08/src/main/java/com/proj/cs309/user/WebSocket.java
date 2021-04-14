package com.proj.cs309.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ServerEndpoint(value = "/chat/{username}")
public class WebSocket {

	private static ChatRepo chatRepo;

	@Autowired
	public void setMessageRepository(ChatRepo repo) {
		chatRepo = repo; 
	}

	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(WebSocket.class);
	
	@OnOpen
	public void onOpen(Session session,@PathParam("username") String username) throws IOException {

		logger.info(username + " entered into the chat");

		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		sendMessageToParticularUser(username, "Connecting to the group chat of users");
		sendMessageToParticularUser(username, getChatHistory(username));

		String message = username + " has joined the chat";
		broadcast(message);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		logger.info("Message sent and received:" + message);
		String username = sessionUsernameMap.get(session);

		if (message.startsWith("@")) {
			String destUsername = message.split(" ")[0].substring(1);

			sendMessageToParticularUser(destUsername, "Direct Message from " + username + ": " + message);
			sendMessageToParticularUser(username, "Direct Message from you to " + destUsername + ": " + message);

		} else { 
			broadcast(username + ": " + message);
		}

		chatRepo.save(new Chat(username, message));
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Closing chat");
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

		String message = username + " has disconnected";
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
	
		logger.info("Error occurred");
		throwable.printStackTrace();
	}

	private void sendMessageToParticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});

	}

	private String getChatHistory(String NewUser) {
		List<Chat> messages = chatRepo.findAll();

		StringBuilder sb = new StringBuilder();
		if (messages != null && messages.size() != 0) {
			for (Chat message : messages) {
				
				if (!message.getUserName().equals(NewUser) && message.getContent().contains("@")
						&& !message.getContent().contains("@" + NewUser)) {}
				else {
					if (message.getContent().contains("@")) {
						if(message.getUserName().equals(NewUser)) {
							sb.append("Direct Message from you: " + message.getContent() + "\n");
						}
						else {
							sb.append("Direct Message from " + message.getUserName() + ": " + message.getContent() + "\n");
						}
						
					}
					else {
						sb.append(message.getUserName() + ": " + message.getContent() + "\n");
					}
				}
			}
		}
		return sb.toString();
	}

}
