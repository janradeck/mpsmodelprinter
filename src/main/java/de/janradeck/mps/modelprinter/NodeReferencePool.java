package de.janradeck.mps.modelprinter;

import java.util.HashMap;
import java.util.Map;

/**
 * A pool of node references.<br>
 * Each node that is referenced is assigned a unique id<br>
 * In the output of the application we only want to show this id, if there is a reference to this node.<br>
 */
public class NodeReferencePool {
	private Map<String,NodeReferenceId> idMap = new HashMap<>();
	private Integer idCounter = 1;
	
	/**
	 * If the id of the node is not already in the map, add it with the current idCounter value.<br>
	 * Increment the idCounter.
	 * @param nodeId : The id of the node.
	 */
	public void markAsReferenced(String model, String nodeId) {
		if (! idMap.containsKey(nodeId)) {
			idMap.put(nodeId, new NodeReferenceId(model, idCounter.toString()));
			idCounter++;
		}
	}
	
	/**
	 * If the nodeId is in the map, return the value of the map.<br>
	 * Otherwise return an empty instance.
	 * @param nodeId
	 * @return
	 */
	public NodeReferenceId getReferencedId(String nodeId) {
		if (idMap.containsKey(nodeId)) {
			return idMap.get(nodeId);
		} 
		return new NodeReferenceId();
	}

}
