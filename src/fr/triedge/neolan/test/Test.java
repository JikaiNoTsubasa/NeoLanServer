package fr.triedge.neolan.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd-HHmmss");
		System.out.println(format.format(d));
	}

}
