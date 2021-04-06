package fr.triedge.neolan.server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fr.triedge.neolan.model.NeolanGame;
import fr.triedge.neolan.model.NeolanManager;

public class NeolanHttpHandler implements HttpHandler {

	private NeolanManager manager = new NeolanManager();
	
	@Override
	public void handle(HttpExchange http) throws IOException {
		
		if("GET".equals(http.getRequestMethod())) {
			HashMap<String, String> params = getParams(http);
			String action = params.get("action");
			switch(action){
			case "create":
				createGame(params);
				sendResponse(http, "OK");
				break;
			case "delete":
				deleteGame(params);
				sendResponse(http, "OK");
				break;
			case "update":
				updateGame(params);
				sendResponse(http, "OK");
				break;
			case "get":
				sendResponse(http, getGames());
				break;
				
			}
		}

	}
	
	private void updateGame(HashMap<String, String> params){
		manager.updateNumberOfPlayers(params.get("id"), Integer.parseInt(params.get("players")));
	}
	
	private void sendResponse(HttpExchange http, String body) throws IOException{
		OutputStream out = http.getResponseBody();
		http.sendResponseHeaders(200, body.length());
		out.write(body.getBytes());
		out.flush();
		out.close();
	}
	
	private String getGames(){
		StringBuilder tmp = new StringBuilder();
		for (NeolanGame game : manager.getGames()){
			tmp.append(game.toString()+"\r\n");
		}
		return tmp.toString();
	}
	
	private void deleteGame(HashMap<String, String> params){
		manager.removeGame(params.get("id"));
	}
	
	private void createGame(HashMap<String, String> params){
		manager.createGame(
				params.get("id"), 
				params.get("ip"));
		System.out.println("Added: "+params.get("id"));
	}
	
	private HashMap<String, String> getParams(HttpExchange http){
		HashMap<String, String> map = new HashMap<>();
		String fullUrl = http.getRequestURI().toString();
		System.out.println("FullURL: "+fullUrl);
		String subUrl = fullUrl.substring(fullUrl.indexOf("?")+1);
		System.out.println("SubURL: "+subUrl);
		String[] params = subUrl.split("&");
		System.out.println("Found params: "+params.length);
		for(String p : params){
			String[] duo = p.split("=");
			map.put(duo[0], duo[1]);
		}
				
		return map;
	}

}
