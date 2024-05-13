/**
 * 
 */
package de.janradeck.mps.modelprinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes an output file with indentation.
 */
public class IndentPrinter {
	// Indentation size in spaces.
	private static final Integer INDENT_SIZE = 2;
	
	private int indent = 0;
	private BufferedWriter writer;
	
	/**
	 * Constructor
	 * @param outputFile The output file
	 */
	public IndentPrinter(File outputFile) {
		try {
			this.writer = new BufferedWriter(new FileWriter(outputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Increase indentation 
	 */
	public void incr() {
		indent = indent + INDENT_SIZE;
	}
	
	/**
	 * Decrease indentation
	 */
	public void decr() {
		indent = indent - INDENT_SIZE;
	}
	
	/**
	 * @return the current indentation as a string
	 */
	private String get() {
		StringBuilder result = new StringBuilder();
	    for (int i = 0; i < indent; i ++) {
	        result.append(" ");
	    }
	    return result.toString();
	}
	
	/**
	 * Print a string with the current indentation to the output file
	 * @param format
	 * @param args
	 */
	public void printf(String format, Object... args) {
		try {
			writer.write(get() + String.format(format, args) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close the writer
	 */
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
