package DataToGraph;

import java.util.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileWalker extends SimpleFileVisitor<Path> {

	public static final Path js = Paths.get("ss/src/js");
	public static final Path css = Paths.get("ss/src/css");

	// -----------------------------------------
	// Make project directory
	// -----------------------------------------
	public boolean mkProject(Path outDir) {

		System.out.println("Creating directory: " + outDir.toString());
		boolean result = false;

		// see if we can make a new directory	
		if (! Files.exists(outDir)) {
			File copy = new File(outDir.toString());
			result = copy.mkdir(); // returns true if directory is made
			System.out.println("Directory created");
		}		
		return result;
	}

	// -----------------------------------------
	// Copy the js and css directories
	// -----------------------------------------
	public FileVisitResult preVisitDirectory(Path sourceDir, Path outDir, BasicFileAttributes attribs) {
		// before visiting entries in a directory we copy the directory
		// (okay if directory already exists).

		try {
			Files.copy(sourceDir, outDir);
		} catch (FileAlreadyExistsException e) {
			// ignore
		} catch (IOException e) {
			System.err.format("Unable to create: %s: %s%n", outDir, e);
			return FileVisitResult.SKIP_SUBTREE;
		}
		return FileVisitResult.CONTINUE;
	}

	// -----------------------------------------
	// Copy files to the project directory
	// -----------------------------------------

	// Method to copy files. Preserve copies/preserves file attributes
	public static void copyFile(Path sourceDir, Path outDir) {
		if (Files.notExists(target)) {
			try {
				Files.copy(sourceDir, outDir, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.format("Unable to copy: %s: %s%n", sourcDir, e);
			}
		}
	}

	// Copy files
	public FileVisitResult visitFile(Path sourceDir, Path outDir, BasicFileAttributes attribs) {
		copyFile(sourceDir, outDir);
		return FileVisitResult.CONTINUE;
	}

	// -----------------------------------------
	// Monster FileWalker will never fail
	// -----------------------------------------
	public FileVisitResult visitFileFailed(Path p, IOException e){
		System.err.format("Visit failed: %s%n", e);
		return FileVisitResult.TERMINATE;
	}
}

