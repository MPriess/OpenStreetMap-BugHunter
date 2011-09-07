package com.mpriess.osm.bug;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class App {

	public static void main(String[] args) throws Exception {
		if(args.length>=1){
			parse(args[0]);
		} else {
			System.out.println("First argument map.osm");
		}
	}

	private static void parse(String uri) {
		XMLReader xmlReader = null;
		try {
			xmlReader = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		FileReader reader = null;
		try {
			reader = new FileReader(uri);
		} catch (FileNotFoundException e) {
			System.out.print("Usage: java -jar bughunter.jar /home/user/berlin.osm");
		}
		InputSource inputSource = new InputSource(reader);

		xmlReader.setContentHandler(new OpeningHoursHandler());

		try {
			xmlReader.parse(inputSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
