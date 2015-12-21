package br.com.vagas.util;


import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FileUtilTest {

	@Rule
	public ExpectedException ilegalArgumentException = ExpectedException.none();

	@Test
	public void fileExistsFalse(){
		boolean retorno = FileUtil.fileExists("");
		Assert.assertFalse(retorno);
	}

	@Test
	public void fileExistsTrue(){
		boolean retorno = FileUtil.fileExists("src/test/resources/inputFile.txt");
		Assert.assertTrue(retorno);
	}

	@Test
	public void fileIsEmptyFalseNotExist(){
		boolean retorno = FileUtil.fileIsEmpty("");
		Assert.assertFalse(retorno);
	}

	@Test
	public void fileIsEmptyFalseNotEmpty(){
		boolean retorno = FileUtil.fileIsEmpty("src/test/resources/inputFile.txt");
		Assert.assertFalse(retorno);
	}

	@Test
	public void fileIsEmptyTrue(){
		boolean retorno = FileUtil.fileIsEmpty("src/test/resources/emptyFile.txt");
		Assert.assertTrue(retorno);
	}

	@Test
	public void getFileLinesFileNotFound(){
		ilegalArgumentException.expect(IllegalArgumentException.class);
		ilegalArgumentException.expectMessage("File not found.");
		FileUtil.getFileLines("fileNotFound.txt");
	}

	@Test
	public void getFileLinesFile(){
		List<String> retorno = FileUtil.getFileLines("src/test/resources/inputFile.txt");
		Assert.assertNotNull(retorno);
		Assert.assertTrue(retorno.size() > 0);
	}

}
