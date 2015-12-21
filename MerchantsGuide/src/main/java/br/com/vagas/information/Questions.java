package br.com.vagas.information;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.vagas.util.NumberConverter;

public class Questions {
	Map<String, String> questionAndAnswers = new LinkedHashMap<String, String>();
	List<String> lineQuestions = new ArrayList<String>();
	Prices prices = null;
	Definitions definitions = null;
	public static final String NO_IDEA_ANSWER = "I have no idea what you are talking about.";

	public Collection<String> getAnswers() {
		return questionAndAnswers.values();
	}

	public void addLine(String line) {
		lineQuestions.add(line);
	}

	public void parse(Definitions definitions, Prices prices) {
		this.prices = prices;
		this.definitions = definitions;
		String auxLine = null;
		for (String line : lineQuestions) {
			auxLine = line.trim().toUpperCase();
			String answer = null;
			if (isHowMuchQuestion(auxLine)) {
				answer = processHowMuchQuestion(line);
			} else if (isHowManyQuestion(auxLine)) {
				answer = processHowManyQuestion(line);
			}
			if (answer == null) {
				answer = NO_IDEA_ANSWER;
			}
			questionAndAnswers.put(line, answer);
		}

	}

	private String processHowManyQuestion(String line) {
		String lineWithoutPrefix = line.substring(20);
		String lineWithoutSufix = lineWithoutPrefix.substring(0,lineWithoutPrefix.length() - 1).trim();
		String[] questionTokens = lineWithoutSufix.split(" ");
		String itemName = questionTokens[questionTokens.length - 1];
		BigDecimal itemValue = prices.getItemPrice(itemName.toUpperCase());
		if (itemValue == null) {
			return null;
		}
		String[] alienNumbers = lineWithoutSufix.replace(itemName, "").split(" ");
		int arabicNumber = NumberConverter.convertAlienToArabic(alienNumbers,definitions);
		if (arabicNumber == 0) {
			return null;
		}

		BigDecimal valorTotal = itemValue.multiply(new BigDecimal(arabicNumber)).setScale(0,RoundingMode.HALF_UP);
		String retorno = String.format("%s is %s Credits", lineWithoutSufix,valorTotal.toPlainString());
		return retorno;
	}

	private String processHowMuchQuestion(String line) {
		String lineWithoutPrefix = line.substring(12);
		String lineWithoutSufix = lineWithoutPrefix.substring(0,lineWithoutPrefix.length() - 1).trim();
		String[] alienNumbers = lineWithoutSufix.split(" ");
		int arabicNumber = NumberConverter.convertAlienToArabic(alienNumbers,definitions);
		if (arabicNumber == 0) {
			return null;
		}
		String retorno = String.format("%s is %s", lineWithoutSufix,arabicNumber);
		return retorno;
	}

	private boolean isHowManyQuestion(String line) {
		return line.trim().startsWith("HOW MANY CREDITS IS");
	}

	private boolean isHowMuchQuestion(String line) {
		return line.trim().startsWith("HOW MUCH IS");
	}
}
