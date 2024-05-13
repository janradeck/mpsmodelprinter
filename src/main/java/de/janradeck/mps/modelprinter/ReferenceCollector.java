package de.janradeck.mps.modelprinter;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.janradeck.mps.modelprinter.ModelRepo.ModelEntry;
import de.janradeck.mps.modelreader.MPSNode;
import de.janradeck.mps.modelreader.Model;
import de.janradeck.mps.modelreader.Ref;

/**
 * Traverse the model and mark all references.
 */
public class ReferenceCollector {

	private NodeReferencePool pool;
	private Model model;
	private String modelName;
	private ModelRepo repo;
	
	/**
	 * Parse the xmlFile into an instance of Model
	 * @param xmlFile
	 * @param repo Repository, to look up referenced models
	 * @param pool Where all references to nodes will be stored
	 */
	public ReferenceCollector(File xmlFile, ModelRepo repo, NodeReferencePool pool) {
		this.repo = repo;
		this.pool = pool;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			model = (Model) jaxbUnmarshaller.unmarshal(xmlFile);

			modelName = MpsModelUtils.refToModelName(model.getRef());
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Collect all references in the model. 
	 */
	public void collectReferencesInModel() {		
		for (MPSNode mpsNode : model.getNode()) {
			collectReferencesInNode(mpsNode);
		}
	}
	
	/**
	 * Print the model to a file, resolving all references.
	 * @param aspect
	 * @param outputFile
	 */
	public void printModel(String aspect, File outputFile) {
		ModelPrinter printer = new ModelPrinter(model, repo, pool);
		printer.printModel(aspect, outputFile);
	}
	
	private void collectReferencesInNode(MPSNode curNode) {
		for (Object object: curNode.getContent()) {
			if (object instanceof Ref) {
				markAsReferenced((Ref) object);
			} else if (object instanceof MPSNode) {
				collectReferencesInNode( (MPSNode) object);
			}
		}
	}

	private void markAsReferenced(Ref refNode) {
		if (refNode.getNode() != null) {
			// This is a reference to a node in the current model
			pool.markAsReferenced(modelName, refNode.getNode());
		}
		// This is a reference to a node in another model
		if(refNode.getTo()!=null) {
			String modelKey = MpsModelUtils.toModelKey(refNode.getTo());
			String targetModelName = MpsModelUtils.modelKeyToModelName(model, modelKey);
			ModelEntry toModel = repo.getModelByName(targetModelName);
			// Only mark the reference as used if the model is internal
			if (null != toModel && toModel.isProjectModel()) {
				String toModelRef = toModel.getModel().getRef();
				String toModelName = MpsModelUtils.refToModelName(toModelRef);
				pool.markAsReferenced(toModelName, MpsModelUtils.toConceptInstanceId(refNode.getTo()));
			}
		}
	}
	
		
}
