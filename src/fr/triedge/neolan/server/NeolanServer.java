package fr.triedge.neolan.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class NeolanServer {

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 2021), 0);
		server.createContext("/neolan", new  NeolanHttpHandler());
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		server.setExecutor(threadPoolExecutor);
		server.start();
		System.out.println("Server started");
		
	}

}
