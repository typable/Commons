package com.core.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;


public class Loader {

	public static InputStream loadResource(Path path) throws FileNotFoundException {

		return new FileInputStream(path.toFile());
	}
}
