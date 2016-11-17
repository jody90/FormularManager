package de.formularmanager.global;

import java.util.HashMap;
import java.util.Map;

public class ConfigMaps {

	public Map<String, String> getCountries() {
		Map<String, String> countries = new HashMap<String, String>();
		countries.put("DE", "Deutschland");
		countries.put("EN", "England");
		countries.put("AT", "Östereich");
		countries.put("CH", "Schweiz");
		countries.put("FR", "Frankreich");
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
