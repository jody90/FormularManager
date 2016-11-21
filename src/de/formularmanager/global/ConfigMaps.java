package de.formularmanager.global;

import java.util.HashMap;
import java.util.Map;

public class ConfigMaps {

	public Map<String, String> getcountrys() {
		Map<String, String> countries = new HashMap<String, String>();
		countries.put("DE", "Deutschland");
		countries.put("AT", "England");
		countries.put("FR", "Frankreich");
		countries.put("CH", "Schweiz");
		return countries;
	}
	
	public Map<String, String> getTypes() {
		Map<String, String> types = new HashMap<String, String>();
		types.put("antrag", "Antrag");
		types.put("umfrage", "Umfrage");
		types.put("wasweisich", "Was weis ich");
		return types;
	}
	
}
