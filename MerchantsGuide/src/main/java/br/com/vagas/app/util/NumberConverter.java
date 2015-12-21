package br.com.vagas.app.util;


import br.com.vagas.enums.Roman;
import br.com.vagas.pojo.Definitions;

public class NumberConverter {

	public static final String ROMAN_NUMBER_REGEX = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
	public static String convertAlienToRoman(String[] alienNumbers, Definitions definitions) {
		String romanNumber = "";
		Roman currentRoman = null;
		for (int i = 0; i < alienNumbers.length; i++) {
			currentRoman = definitions.getRoman(alienNumbers[i]);
			if (currentRoman == null) {
				return null;
			}
			romanNumber += currentRoman.getRomanCharacter();
		}
		if(isValidRomanNumber(romanNumber))
		{
			return romanNumber;
		}
		else{
			return null;
		}

	}
	
	public static boolean isValidRomanNumber(String romanNumber) {
		return romanNumber.matches(ROMAN_NUMBER_REGEX);
	}

	public static int convertAlienToArabic(String[] alienNumbers, Definitions definitions)
	{
		String romanNumber = convertAlienToRoman(alienNumbers, definitions);
		int arabicNumber = convertRomanToArabic(romanNumber);
		return arabicNumber;
		
	}

	public static int convertRomanToArabic(String romanNumber) {
		int arabicNumberValue = 0;
		int lastRomanValue = 0;
		int currentRomanValue = 0;
		String currentRomanCharacter  = null;
		if(romanNumber != null && !"".equals(romanNumber.trim()) && isValidRomanNumber(romanNumber))
		{
			for(int i = romanNumber.length() -1; i >= 0; i--)
			{
				currentRomanCharacter = romanNumber.substring(i, i+1);
				currentRomanValue = Roman.valueOf(currentRomanCharacter).getValue();
				if(lastRomanValue > currentRomanValue)
				{
					arabicNumberValue -= currentRomanValue;
				}
				else{
					arabicNumberValue += currentRomanValue;
				}
				lastRomanValue = currentRomanValue;
			}
		}
		return arabicNumberValue;
	}
}
