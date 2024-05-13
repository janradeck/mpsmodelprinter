package de.janradeck.mps.modelprinter;

/**
 * An id for a node that is the target of a reference. 
 */
public class NodeReferenceId {
	private String modelName;
	private String id;
	private boolean isValid = false;
	
	public NodeReferenceId(String modelName, String id) {
		this.modelName = modelName;
		this.id = id;
		this.isValid = true;
	}
	
	public NodeReferenceId() {
		this.isValid = false;
	}
	
	String getModule() {
		return modelName;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public String getId(String modelName) {
		if (!isValid) {
			return "";
		}
		if (this.modelName.equals(modelName)) {
			return "(" + id + ")";
		} else {
			return "("+ this.modelName + "." + this.id + ")";
		}
		
	}
	
	public String getIdAsTarget(String modelName) {
		String result = getId(modelName);
		if (!result.equals("")) {
			return "[" + result + "]";
		}
		return "";
	}
	
}
