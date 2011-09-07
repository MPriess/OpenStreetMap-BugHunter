package com.mpriess.osm.bug;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.mpriess.osm.bug.model.Bugreport;

public class OpeningHoursHandler implements ContentHandler {

	Bugreport bugreport;

	String[] valid = { "mo", "tu", "we", "th", "fr", "sa", "su", "24/7", "jan",
			"feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
			"nov", "dec", "sh", "ph", "sunrise", "sunset" };

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (attributes.getValue("id") != null) {
			long id = Long.valueOf(attributes.getValue("id"));
			bugreport = new Bugreport(id);
		}

		if (attributes.getValue("user") != null) {
			bugreport.setUser(attributes.getValue("user"));
		}

		if (attributes.getValue("lon") != null) {
			double lon = Double.parseDouble(attributes.getValue("lon"));
			bugreport.setLon(lon);
		}

		if (attributes.getValue("lat") != null) {
			double lat = Double.parseDouble(attributes.getValue("lat"));
			bugreport.setLat(lat);
		}

		if (attributes.getValue("timestamp") != null) {
			bugreport.setTimestamp(attributes.getValue("timestamp"));
		}

		if (attributes.getValue(0).equals("opening_hours")) {
			String[] openingHours = attributes.getValue(1).split(";");
			if (checkDate(openingHours)) {
				bugreport.setBugDescription(attributes.getValue(1));
			}
		}

	}

	/**
	 * Check if opening_hours contain a weekday, month or sunset.
	 * @param String[] s example: "Mo-Fr 09:00-16:00"  
	 * @return boolean
	 */
	public boolean checkDate(String[] s) {
		String[] openingHours = s;
		for (String h : openingHours) {
			String[] ddMM24 = h.split(" ");
			String[] firstValue = ddMM24[0].replace(",", "-").split("-");
			for (String value : firstValue) {
				for (String validValue : valid) {
					if (validValue.contains(value.toLowerCase())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("node")) {
			if (bugreport.getBugDescription() != null) {
				System.out.println(bugreport);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}
}
