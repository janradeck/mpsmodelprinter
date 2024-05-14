package de.janradeck.mps.modelprinter;

import java.io.File;

import de.janradeck.mps.modelprinter.ModelRepo.ModelEntry;
import de.janradeck.mps.modelreader.Child;
import de.janradeck.mps.modelreader.Concept;
import de.janradeck.mps.modelreader.Language;
import de.janradeck.mps.modelreader.MPSNode;
import de.janradeck.mps.modelreader.Model;
import de.janradeck.mps.modelreader.Property;
import de.janradeck.mps.modelreader.Ref;
import de.janradeck.mps.modelreader.Reference;

/**
 * Prints a model to a file.<br>
 * Recursively prints all concepts and their properties and references.<br> 
 *  
 */
public class ModelPrinter {
	
	private Model model;
	private IndentPrinter indenter;
	private ModelRepo repo;
	private NodeReferencePool pool;
	private String modelName;
	
	/**
	 * @param model The model to be printed
	 * @param globalRepo The repo with all models
	 * @param pool The pool with all node references
	 */
	public ModelPrinter(Model model, ModelRepo globalRepo, NodeReferencePool pool) {
		this.model = model;
		modelName = MpsModelUtils.refToModelName(model.getRef());
		repo = globalRepo;
        this.pool = pool;
	}
	
	/**
	 * Print one model to a file
	 * @param aspect The name of the aspect, eg. "behavior"
	 * @param outputFile The file to print to
	 */
	public void printModel(String aspect, File outputFile) {
		indenter = new IndentPrinter(outputFile);
		indenter.printf("%s model: %s", aspect, outputFile.getName());
		indenter.printf("");
		for (MPSNode mpsNode : model.getNode()) {
			printConcept(mpsNode, null);
		}		

		indenter.close();
	}
		
	private  void printConcept(MPSNode curNode, Concept parentConcept) {
		String conceptIndex = curNode.getConcept();
		Concept concept = getConceptByIndex(conceptIndex);
		
		if (null == concept) {
			indenter.printf("Concept %s not found", conceptIndex);
			return;
		}
		
		NodeReferenceId conceptRefId = pool.getReferencedId(curNode.getId());
		String refId = conceptRefId.getIdAsTarget(modelName);
		
		if (null == parentConcept) {
			indenter.printf("%s%s from %s", refId, MpsModelUtils.component(concept.getName()),  MpsModelUtils.pkgName(concept.getName()));
		} else {
			String role = curNode.getRole();
			Child child = getChildByIndex(role);
			if (null == child) {
				indenter.printf("Child %s not found", role);
			} else {
				indenter.printf("%s%s = %s from %s", refId , child.getName(), MpsModelUtils.component(concept.getName()), MpsModelUtils.pkgName(concept.getName()));
			}
		}

		indenter.incr();

		for (Object object: curNode.getContent()) {
			if (object instanceof Property) {
				printProperty((Property) object);
			} else if (object instanceof Ref) {
				printRef((Ref) object);
			} else if (object instanceof MPSNode) {
				printConcept((MPSNode) object, concept);
			}
		}
		indenter.decr();
		
		if (null == parentConcept) {
			indenter.printf("");
		}
	}

	/**
	 * Depending on the target of the reference:
	 * <ul> 
	 * <li>a node in this model: Print the reference id</li>
	 * <li>a node in an internal model: Print the reference id and the concept name</li>
	 * <li>a node in an external model: Print the reference id, the concept name and the model name</li>
	 * </ul>
	 * @param ref
	 */
	private void printRef(Ref ref) {
		String srcIndex = ref.getRole();
		Reference reference = getReferenceByIndex(srcIndex);

		if (null == reference) {
			indenter.printf("Reference %s not found", srcIndex);

		// A node in this model: The "node" attribute is set
		} else if (null != ref.getNode()) {
			String refId = pool.getReferencedId( ref.getNode()).getId();
			indenter.printf("%s -> %s [target id %s]", reference.getName(), ref.getResolve(), refId);
		// A node in another model: The "to" attribute is set
        } else if (null != ref.getTo()) {
            String modelKey = MpsModelUtils.toModelKey(ref.getTo());
            String referencedModelName = MpsModelUtils.modelKeyToModelName(model, modelKey);
            ModelEntry toModel = repo.getModelByName(referencedModelName); 
            String conceptInstanceId = MpsModelUtils.toConceptInstanceId(ref.getTo());
            Concept concept = getConceptByIndex(conceptInstanceId);
            String conceptName = "";
            if (null != concept) {
            	conceptName = concept.getName() + " from ";
            }
            if (null == toModel) {
                indenter.printf("%s -> %s (%s%s)", reference.getName(), ref.getResolve(), conceptName, referencedModelName);            	
            } else if (toModel.isProjectModel()) {
                String toModelName = toModel.getName();
                String refId = pool.getReferencedId(conceptInstanceId).getId();
                indenter.printf("%s -> %s [target id %s %s]", reference.getName(), ref.getResolve(), refId, toModelName);
            } else {
                String toModelName = toModel.getName();
                indenter.printf("%s -> %s (%s%s)", reference.getName(), ref.getResolve(), conceptName, toModelName);
            }
        }
		
	}

	private void printProperty(Property srcProperty) {
		String srcIndex = srcProperty.getRole();
		Property dstProperty = getPropertyByIndex(srcIndex);
		if (null == dstProperty) {
			indenter.printf("Property %s not found", srcIndex);
		} else {
			indenter.printf("%s = %s", dstProperty.getName(), srcProperty.getValueAttribute());
		}
	}

	private Concept getConceptByIndex(String conceptIndex) {
		for (Language language: model.getRegistry().getLanguage()) {
			for (Concept concept: language.getConcept()) {
				if (concept.getIndex().equals(conceptIndex)) {
					return concept;
				}
			}
		}
		
		return null;
	}
	
	private Property getPropertyByIndex(String propertyIndex) {
		for (Language language: model.getRegistry().getLanguage()) {
			for (Concept concept: language.getConcept()) {
				for (Object child: concept.getContent()) {
					if (child instanceof Property) {
						Property property = (Property) child;
						if (property.getIndex().equals(propertyIndex)) {
							return property;
						}
					}
				}
			}
		}
		return null;
	}
	
	private Reference getReferenceByIndex(String refIndex) {
		for (Language language: model.getRegistry().getLanguage()) {
			for (Concept concept: language.getConcept()) {
				for (Object child: concept.getContent()) {
					if (child instanceof Reference) {
						Reference reference = (Reference) child;
						if (reference.getIndex().equals(refIndex)) {
							return reference;
						}
					}
				}
			}
		}		
		return null;
	}
	
	private Child getChildByIndex(String childIndex) {
		for (Language language: model.getRegistry().getLanguage()) {
			for (Concept concept: language.getConcept()) {
				for (Object child: concept.getContent()) {
					if (child instanceof Child) {
						Child curChild = (Child) child;
						if (curChild.getIndex().equals(childIndex)) {
							return curChild;
						}
					}
				}
			}
		}		
		
		return null;
	}
		
}
