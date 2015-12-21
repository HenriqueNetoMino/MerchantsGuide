package br.com.vagas.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.vagas.enums.Roman;
import br.com.vagas.model.Definitions;

public class NumberConverterTest {



	Definitions definitions = null;

	@Before
	public void init(){
		definitions = new Definitions();
		definitions.addDefinition("GLOB", Roman.I);
		definitions.addDefinition("PROK", Roman.V);
	}

	@Test
	public void convertAlienToRomanNullAlienNumbers(){
		String retorno = NumberConverter.convertAlienToRoman(null, definitions);
		Assert.assertNull(retorno);

	}

	@Test
	public void convertAlienToRomanInvalidCharacter(){
		String[] alienNumbers = new String[]{"zug"};
		String retorno = NumberConverter.convertAlienToRoman(alienNumbers, definitions);
		Assert.assertNull(retorno);

	}

	@Test
	public void convertAlienToRoman(){
		String[] alienNumbers = new String[]{"glob"};
		String retorno = NumberConverter.convertAlienToRoman(alienNumbers, definitions);
		Assert.assertEquals(retorno, Roman.I.getRomanCharacter());
	}




	@Test
	public void convertAlienToRomanInvalidRomanNumber(){
		String[] alienNumbers = new String[]{"prok", "prok"};
		String retorno = NumberConverter.convertAlienToRoman(alienNumbers, definitions);
		Assert.assertNull(retorno);

	}

	@Test
	public void isValidRomanNumberNull(){
		String romanNumber = null;
		boolean retorno = NumberConverter.isValidRomanNumber(romanNumber);
		Assert.assertFalse(retorno);

	}


	@Test
	public void isValidRomanNumberEmpty(){
		String romanNumber = "";
		boolean retorno = NumberConverter.isValidRomanNumber(romanNumber);
		Assert.assertFalse(retorno);

	}

	@Test
	public void isValidRomanNumber(){
		String romanNumber = "X";
		boolean retorno = NumberConverter.isValidRomanNumber(romanNumber);
		Assert.assertTrue(retorno);
	}

	@Test
	public void convertAlienToArabicNull(){
		int  retorno = NumberConverter.convertAlienToArabic(null, definitions);
		Assert.assertEquals(retorno, 0);
	}

	@Test
	public void convertAlienToArabicNotValid(){
		String[] alienNumbers = new String[]{"zug"};
		int  retorno = NumberConverter.convertAlienToArabic(alienNumbers, definitions);
		Assert.assertEquals(retorno, 0);

	}


	@Test
	public void convertAlienToArabic(){
		String[] alienNumbers = new String[]{"glob"};
		int  retorno = NumberConverter.convertAlienToArabic(alienNumbers, definitions);
		Assert.assertEquals(retorno, 1);

	}


	@Test
	public void convertRomanToArabicNull(){
		String romanNumber = null;
		int  retorno = NumberConverter.convertRomanToArabic(romanNumber);
		Assert.assertEquals(retorno, 0);

	}


	@Test
	public void convertRomanToArabicEmpty(){
		String romanNumber = "";
		int  retorno = NumberConverter.convertRomanToArabic(romanNumber);
		Assert.assertEquals(retorno, 0);

	}

	@Test
	public void convertRomanToArabicSimple(){
		String romanNumber = "X";
		int  retorno = NumberConverter.convertRomanToArabic(romanNumber);
		Assert.assertEquals(retorno, 10);

	}

	@Test
	public void convertRomanToArabicNotValid(){
		String romanNumber = "Z";
		int  retorno = NumberConverter.convertRomanToArabic(romanNumber);
		Assert.assertEquals(retorno, 0);

	}

	@Test
	public void convertRomanToArabicComplex(){
		String romanNumber = "IX";
		int  retorno = NumberConverter.convertRomanToArabic(romanNumber);
		Assert.assertEquals(retorno, 9);

	}


}
