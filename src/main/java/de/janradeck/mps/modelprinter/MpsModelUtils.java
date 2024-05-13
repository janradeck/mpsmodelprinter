package de.janradeck.mps.modelprinter;

import de.janradeck.mps.modelreader.Import;
import de.janradeck.mps.modelreader.Model;

/**
 * Helper methods for MPS models 
 */
public class MpsModelUtils {
	
	public static String component(String dottedString) {
		int lastIndex = dottedString.lastIndexOf(".");
		if (lastIndex != -1 && lastIndex < dottedString.length() - 1) {
		    return dottedString.substring(lastIndex + 1);
		} else {
		    return dottedString;
		}
	}
	
	public static String pkgName(String dottedString) {
		int lastIndex = dottedString.lastIndexOf(".");
		if (lastIndex != -1 && lastIndex < dottedString.length() - 1) {
		    return dottedString.substring(0, lastIndex);
		} else {
		    return dottedString;
		}
	}

	// the toAttribute is of the form "modelName:conceptInstanceId"
	public static String toModelKey(String toAttribute) {
		String[] parts = toAttribute.split(":");
		String modelName = parts[0];
		return modelName;
	}
	
	// the toAttribute is of the form "modelName:conceptInstanceId"
	public static String toConceptInstanceId(String toAttribute) {
		String[] parts = toAttribute.split(":");
		String conceptInstanceId = parts[1];
		return conceptInstanceId;
	}
	
	// Use a regular expression to extract the string between the brackets as "modelName"  "r:00000000-0000-4000-0000-011c89590299(jetbrains.mps.lang.editor.editor)"
	// and return it.
	public static String refToModelName(String refString) {
		return refString.replaceAll(".*\\((.*)\\)", "$1");
	}

	
	// Determine the model name by looking up the library name in the import section of the Model object
	public static String modelKeyToModelName(Model model, String modelKey) {
		for (Import imp : model.getImports().getImport()) {
			if (imp.getIndex().equals(modelKey)) {
				return MpsModelUtils.refToModelName(imp.getRef());				
			}
		}
		return null;
	}
	
}
