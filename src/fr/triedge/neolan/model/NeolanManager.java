package fr.triedge.neolan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class NeolanManager {

	private ArrayList<NeolanGame> games = new ArrayList<>();
	
	public void createGame(String id, String ip){
		if (getGameByIP(ip) != null)
			return;
		NeolanGame g = new NeolanGame();
		g.creationDate = new Date();
		g.id = id;
		g.hostIp = ip;
		g.currentPlayers = 1;
		games.add(g);
	}
	
	public void removeGame(String id){
		Iterator<NeolanGame> it = games.iterator();
		while(it.hasNext()){
			NeolanGame g = it.next();
			if (g.id.equals(id))
				it.remove();
			
		}				
	}
	
	public void updateNumberOfPlayers(String id, int number){
		Iterator<NeolanGame> it = games.iterator();
		while(it.hasNext()){
			NeolanGame g = it.next();
			if (g.id.equals(id)){
				g.currentPlayers = number;
			}
			
		}
	}
	
	public ArrayList<NeolanGame> getGames(){
		return games;
	}
	
	public NeolanGame getGameByIP(String ip){
		for (NeolanGame game : games)
			if (ip.equals(game.hostIp))
				return game;
		return null;
	}
}
