package com.prototype.loader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.prototype.constant.Constants;


public class Loader extends ClassLoader {

	public Loader() {

		//
	}

	public String readText(Path path) throws Exception {

		return Files.readString(path, Constants.CHARSET);
	}

	public byte[] read(Path path) throws Exception {

		return Files.readAllBytes(path);
	}

	public void writeText(Path path, String data) throws Exception {

		Files.writeString(path, data, Constants.CHARSET);
	}

	public void write(Path path, byte[] data) throws Exception {

		Files.write(path, data);
	}

	public File[] loadFiles(Path path) {

		return loadFiles(path, ".*");
	}

	public File[] loadFiles(Path path, String pattern) {

		List<File> files = new ArrayList<>();
		File directory = path.toFile();

		if(directory.exists() && directory.isDirectory()) {

			for(File file : directory.listFiles()) {

				if(file.isFile()) {

					if(file.getName().matches(pattern)) {

						files.add(file);
					}
				}
				else if(file.isDirectory()) {

					for(File subfile : loadFiles(file.toPath(), pattern)) {

						files.add(subfile);
					}
				}
			}
		}

		return files.toArray(new File[files.size()]);
	}

	public Class<?> loadClass(String name, Path path) throws Exception {

		byte[] data = read(path);

		return defineClass(name, data, 0, data.length);
	}

	public <T> T loadClass(String name, Path path, Class<T> type) throws Exception {

		byte[] data = read(path);

		Class<?> loadedClass = defineClass(name, data, 0, data.length);

		if(loadedClass != null) {

			if(type.isAssignableFrom(loadedClass)) {

				return type.cast(loadedClass.getConstructor().newInstance());
			}
		}

		return null;
	}
}
