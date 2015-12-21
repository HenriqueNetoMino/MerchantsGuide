package br.com.vagas.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static boolean fileExists(String filePath)
	{
		File file  = new File(filePath);
		return file.exists();
	}
	
	public static boolean fileIsEmpty(String filePath)
	{
		File file  = new File(filePath);
		return file.exists() && file.length() ==  0;
	}


	public static List<String> getFileLines(String path) throws IllegalArgumentException{
		List<String> fileLines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) 
		{
			String currentLine = null;
			while ((currentLine = br.readLine()) != null) {
				fileLines.add(currentLine);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		} catch (IOException e) {
			throw new IllegalArgumentException("Error while reading file: " + path + ". \n Error: " + e.getMessage());
		}
		return fileLines;
		
	}
}
