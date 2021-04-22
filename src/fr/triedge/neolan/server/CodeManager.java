package fr.triedge.neolan.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodeManager {

	private static final String CODE_PATH = "code";
	
	public static String setCode(String _code) throws IOException{
		if (_code == null)
			return "NOK";
		FileWriter w = new FileWriter(new File(CODE_PATH));
		w.write(_code);
		w.flush();
		w.close();
		return "OK";
	}
	
	public static String getCode() throws IOException{
		File f = new File(CODE_PATH);
		if (!f.exists())
			return "NO_FILE";
		
		FileReader r = new FileReader(f);
		BufferedReader br = new BufferedReader(r);
		String _res = br.readLine();
		br.close();
		return _res;
	}
}
