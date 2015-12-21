package br.com.vagas.parser;

import java.util.Collection;
import java.util.List;

import br.com.vagas.app.util.FileUtil;
import br.com.vagas.pojo.Definitions;
import br.com.vagas.pojo.Prices;
import br.com.vagas.pojo.Questions;

public class MerchantsGuideProcessor {
	List<String> linesToProcess;
	Definitions definitions = new Definitions();
	Prices prices = new Prices();
	Questions questions = new Questions();
	public MerchantsGuideProcessor(String filePath) {
		linesToProcess = FileUtil.getFileLines(filePath);
	}
	
	
	public Collection<String> processAndAnswerQuestions(){
		separateInformation();
		parseInformation();
		return questions.getAnswers();
	}

	private void parseInformation() {
		definitions.parse();
		prices.parse(definitions);
		questions.parse(definitions, prices);
	}

	private void separateInformation() {
		for(String line : linesToProcess)
		{
			line = line.trim().toUpperCase();
			if(isDefinition(line))//Definition
			{
				definitions.addLine(line);
			}
			else if(isPriceInformation(line))// Price Information
			{
				prices.addLine(line);
			}
			else if(isQuestion(line)){ //Question
				questions.addLine(line);
			}
		}
		
	}

	private boolean isQuestion(String line) 
	{
		return line.endsWith("?");
	}

	private boolean isDefinition(String line) 
	{
		String[] tokens  = line.split(" ");
		return tokens.length == 3 && tokens[1].equals("IS");
	}

	private boolean isPriceInformation(String line) 
	{
		String patternPriceInformation = "^([A-Za-z]+)([A-Za-z\\s]*) IS ([0-9]+) (CREDITS)$";
		return line.matches(patternPriceInformation);
	}

	

}
