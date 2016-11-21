package de.formularmanager.storage;

import java.util.HashMap;
import java.util.Map;

public class FromsStatisticsStorage {

	private int formId;
	
	private String jsonForm;

	private Map<Integer, Map<String, String>> statisticsValue;

	private Map<Integer, Map<String, String>> statisticsValueHelper = new HashMap<Integer, Map<String, String>>();

	public Map<Integer, Map<String, String>> getStatisticsValue() {
		return statisticsValue;
	}

	public void setStatisticsValue(String value, String createdAt, int id, int userId) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("value", value);
		map.put("createdAt", createdAt);
		map.put("userId", Integer.toString(userId));	
		
		statisticsValueHelper.put(id, map);
		this.statisticsValue = statisticsValueHelper;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int i) {
		this.formId = i;
	}
	
	public String getJsonForm() {
		return jsonForm;
	}

	public void setJsonForm(String jsonForm) {
		this.jsonForm = jsonForm;
	}
	
}
