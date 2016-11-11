package de.formularmanager.storage;

public class FormsListStorage {
	
	public FormsListStorage(String id, String type, String created_at, String modified_at, String form_title) {
		this.setId(id);
		this.setType(type);
		this.setCreatedAt(created_at);
		this.setModifiedAt(modified_at);
		this.setFormTitle(form_title);
	}
	
	private String id;
	
	private String type;
	
	private String createdAt;

	private String modifiedAt;
	
	private String formTitle;
	
	public String getFormTitle() {
		return formTitle;
	}

	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
