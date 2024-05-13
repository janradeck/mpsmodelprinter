package de.janradeck.mps.modelprinter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.janradeck.mps.modelreader.Model;

/**
 * Repository for storing models.<br>
 */
public class ModelRepo {
	private enum ModelType {
		PROJECT_MODEL, MPS_MODEL
	}

	private Map<String, ModelEntry> repo = new HashMap<>();

	/**
	 * Recursively collect all models from the MPS source code in the given directory.
	 * @param directory
	 */
	public void collectMpsModels(String directory) {
		handleDirectory(new File(directory), ModelType.MPS_MODEL);
	}

	/**
	 * Recursively collect all project models in the given directory.
	 * @param directory
	 */
	public void collectProjectModels(String directory) {
		handleDirectory(new File(directory), ModelType.PROJECT_MODEL);
	}
	
    public void handleDirectory(File directory, ModelType type) {
        for (File fileEntry : directory.listFiles()) {
            if (fileEntry.isDirectory()) {
                handleDirectory(fileEntry, type);
            } else {
                if (fileEntry.getName().endsWith(".mps")) {
                	System.out.println(String.format("Found file: %s\\%s", directory.getPath(), fileEntry.getName()));
                	addModel(fileEntry, type);
                }
            }
        }
    }
	
	public void addModel(File xmlFile, ModelType type) {
		// Use JAXB to parse an XML file with the name "filename" and store the parsed model in the repo.
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Model.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Model model = (Model) jaxbUnmarshaller.unmarshal(xmlFile);
			String modelName = MpsModelUtils.refToModelName(model.getRef());
			
			if (repo.containsKey(modelName)) {
				System.err.println("Model " + modelName + " already exists in the repo.");
				return;
			}
			repo.put(modelName, new ModelEntry(model, modelName, type));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a model from the repo by its name.
	 * @param modelName
	 * @return
	 */
	public ModelEntry getModelByName(String modelName) {
		return repo.get(modelName);
	}
	
	public static class ModelEntry {
		
		private ModelType type;
		private String name;
		private Model model;
		
		public ModelEntry(Model model, String name, ModelType type) {
			this.model = model;
			this.name = name;
			this.type = type;
		}
		
		public boolean isProjectModel() {
			return type.equals(ModelType.PROJECT_MODEL);
		}
		
		public Model getModel() {
			return model;
		}
		
		public String getName() {
			return name;
		}
	}
		
}
