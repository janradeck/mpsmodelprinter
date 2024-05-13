package de.janradeck.mps.modelprinter;

import de.janradeck.mps.modelprinter.ModelRepo;

public class ModelRepoTest {
	private static final String mpsBaseDirectory = "C:\\temp\\MPS-master\\languages";

	public static void main(String[] args) {
		ModelRepo repo = new ModelRepo();
		repo.collectMpsModels(mpsBaseDirectory);
	}

}
