package fr.triedge.neolan.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class NeolanServer {

	public static void main(String[] args) throws IOException {
		int port = 50000;
		String ip = "localhost";
		String context = "neolan";
		if (args.length >= 2){
			ip = args[0];
			port = Integer.parseInt(args[1]);
		}
		if (args.length >= 3){
			context = args[2];
		}
		
		HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
		server.createContext("/"+context, new  NeolanHttpHandler());
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		server.setExecutor(threadPoolExecutor);
		server.start();
		System.out.println("Server started on "+ip+":"+port);
		
	}

}
