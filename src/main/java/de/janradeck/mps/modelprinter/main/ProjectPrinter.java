package de.janradeck.mps.modelprinter.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.janradeck.mps.modelprinter.ModelRepo;
import de.janradeck.mps.modelprinter.ReferenceCollector;
import de.janradeck.mps.modelprinter.NodeReferencePool;

/**
 * Parses the MPS models in a given project directory and prints them in a human-readable format.<br>
 * One file per model is created in the output directory.<br>
 * The references between models are resolved.<br>
 * The main purpose is to be able to reconstruct the models programmatically.<br>
 * This is useful when writing a DSL to create another DSL.<br>
 * 
 * To be able to resolve references within MPS models, the source code of MPS is needed.<br>
 */
public class ProjectPrinter {
	// The path to the MPS source code
	private static final String MPS_SOURCECODE_DIRECTORY = "C:\\temp\\MPS-master";
	// The path to the directory containing the MPS models to be parsed
	private static final String INPUT_DIRNAME = "C:\\Users\\Jan\\MPSProjects\\Boardgame\\languages\\Boardgame\\models";
	private static final String OUTPUT_DIRNAME = ".\\output";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ModelRepo repo = new ModelRepo();
		repo.collectMpsModels(MPS_SOURCECODE_DIRECTORY + "\\languages");
		repo.collectProjectModels(INPUT_DIRNAME);
		
		File outputDir = new File(OUTPUT_DIRNAME);
		File inputDir = new File(INPUT_DIRNAME);
		File[] files = inputDir.listFiles();
		NodeReferencePool pool = new NodeReferencePool();
		List<ReferenceCollector> parsers = new ArrayList<>();
		
		// Create the output directory if it does not exist
		if (!outputDir.exists()) {
			outputDir.mkdir();
		}
		
		// First, collect all references
		for (int i = 0; i < files.length; i++) {
			ReferenceCollector parser = new ReferenceCollector(files[i], repo, pool);
			parser.collectReferencesInModel();
			parsers.add(parser);
		}

		// Second, print all models, resolving references
		for (int i = 0; i < files.length; i ++) {
			File curInputFile = files[i];
			
			String filename = curInputFile.getName().replaceAll(".mps", ".txt");
			String aspect = extractAspect(curInputFile.getName());
			
			File curOutputFile = new File(outputDir, filename);
			parsers.get(i).printModel(aspect, curOutputFile);
		}
				
	}

	// for a string like "boardgame.behavior.mps" extract "behavior"
	private static String extractAspect(String filename) {
        String[] parts = filename.split("\\.");
        return parts[1];
    }
	

}
