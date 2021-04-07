package fr.triedge.neolan.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NeolanGame {

	public static final String sep = "::";
	
	public Date creationDate;
	public String id;
	public int currentPlayers;
	public String hostIp;
	
	public String toString(){
		StringBuilder tmp = new StringBuilder();
		tmp.append(id);
		tmp.append(sep);
		tmp.append(hostIp);
		tmp.append(sep);
		tmp.append(currentPlayers);
		tmp.append(sep);
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd-HHmmss");
		tmp.append(format.format(creationDate));
		return tmp.toString();
	}
}
