package de.janradeck.mps.modelprinter;

/**
 * An id for a node that is the target of a reference. 
 */
public class NodeReferenceId {
	private String modelName;
	private String id;
	private boolean isValid = false;
	
	public NodeReferenceId(String modelName) {
		this.modelName = modelName;
		this.id = "NO_ID";
		this.isValid = true;
	}
	
	public NodeReferenceId() {
		this.isValid = false;
	}
	
	String getModel() {
		return modelName;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public String getId() {
		if (!isValid) {
			return "";
		}
		return "(" + id + ")";		
	}
	
	public String getIdAsTarget(String modelName) {
		String result = getId();
		if (!result.equals("")) {
			return "[" + result + "]";
		}
		return "";
	}

	public void setId(String id) {
		this.id = id;		
	}
	
}
