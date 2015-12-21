package br.com.vagas.processors;

import java.util.Collection;
import java.util.List;

import br.com.vagas.information.Definitions;
import br.com.vagas.information.Prices;
import br.com.vagas.information.Questions;
import br.com.vagas.util.FileUtil;

public class MerchantsGuideProcessor {
	List<String> linesToProcess;
	Definitions definitions = new Definitions();
	Prices prices = new Prices();
	Questions questions = new Questions();

	public MerchantsGuideProcessor(String filePath)
	{
		linesToProcess = FileUtil.getFileLines(filePath);
	}

	public MerchantsGuideProcessor(List<String> linesToProcess){
		this.linesToProcess = linesToProcess;
	}


	public Collection<String> processAndAnswerQuestions()
	{
		separateInformation();
		parseInformation();
		return questions.getAnswers();
	}

	private void parseInformation()
	{
		definitions.parse();
		prices.parse(definitions);
		questions.parse(definitions, prices);
	}

	private void separateInformation()
	{
		String auxLine = null;
		for(String line : linesToProcess)
		{
			auxLine = line.trim().toUpperCase();
			if(isDefinition(auxLine))//Definition
			{
				definitions.addLine(line);
			}
			else if(isPriceInformation(auxLine))// Price Information
			{
				prices.addLine(line);
			}
			else if(isQuestion(auxLine)){ //Question
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
