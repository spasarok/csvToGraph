package dataToGraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Builder {

	private RickshawGUI gui;
	private String outString;
	private Path projPath;
	
	public Builder(RickshawGUI gui) {
		this.gui = gui;
		projPath = gui.getProjPath();
	}
	
	// -----------------------------------------
	/*
	 * Create project directory if directory does not already exist.
	 * @return boolean true if directory is successfully created, false otherwise
	 */
	public boolean makeDirs() {
		boolean result = false;


		System.out.println("Creating directory: " + projPath.toString());

		// build the project directory
		if (!Files.exists(projPath)) {
			try {
				// make the project directory
				File projDir = new File(outString);
				projDir.mkdir();

				// make the local ss directory for project dependencies
				File ssBuilder = new File(outString + "/ss");
				ssBuilder.mkdir();

				// make the ss/examples directory
				File exampBuilder = new File(outString + "/ss/examples");
				exampBuilder.mkdir();

				// hopefully we haven't thrown an error

				result = true;
			} catch (Exception e) {
				System.err.format(
						"Unable to create one or more directories: %s%n", e);
				return result = false;
			}
		}

		if(result)
			return true;
		else {
			System.out.println("Directory " + projPath.toString()
					+ " already exists");
			return false;
		}
	}

	// -----------------------------------------
	/*
	 * Copy dependencies to local project directory
	 * @return void
	 */
	public void copyDepends() throws IOException {

		// copying dependencies to local project folder

	}

	// -----------------------------------------
	/*
	 * Build working project directory
	 * @return void
	 */
	public void buildDir() throws IOException {

		// make the project directory
		System.out.println("Making project directory");
		boolean result = false;

		// if the project doesn't already exist, make it
		if (!Files.exists(projPath)) {
			try {
				// make the project directory
				File projDir = new File(projPath.toString());
				projDir.mkdir();
				result = true;
			} catch (Exception e) {
				System.err.format(
						"Unable to create one or more directories: %s%n", e);
				return;
			}
		}

		// abort if project exists
		if(!result) {
			System.out.println("Directory " + projPath.toString() + " already exists");
			return;
		}

		// copy project dependencies
		System.out.println("Copying project dependencies");

		Copy copier = new Copy();
		copier.copyDir(Paths.get("ss"), projPath);

		System.out.println("Dependencies copied");
		System.out.println("Project directory built");
	}

}
