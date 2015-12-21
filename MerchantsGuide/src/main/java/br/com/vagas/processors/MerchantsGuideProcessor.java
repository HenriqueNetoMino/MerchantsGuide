package br.com.vagas.processors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.vagas.model.Definitions;
import br.com.vagas.model.Prices;
import br.com.vagas.model.Questions;
import br.com.vagas.parser.DefinitionsParser;
import br.com.vagas.parser.PricesParser;
import br.com.vagas.parser.QuestionsParser;
import br.com.vagas.util.FileUtil;

public class MerchantsGuideProcessor {
	List<String> linesToProcess;
	List<String> listLineDefinitions = new ArrayList<String>();
	List<String> listLinePrices = new ArrayList<String>();
	List<String> listLineQuestions = new ArrayList<String>();
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
		definitions = new DefinitionsParser(listLineDefinitions).parse();
		prices = new PricesParser(listLinePrices, definitions).parse();
		questions = new QuestionsParser(listLineQuestions, definitions, prices).parse();
	}

	private void separateInformation()
	{
		String auxLine = null;
		for(String line : linesToProcess)
		{
			auxLine = line.trim().toUpperCase();
			if(isDefinition(auxLine))//Definition
			{
				listLineDefinitions.add(line);
			}
			else if(isPriceInformation(auxLine))// Price Information
			{
				listLinePrices.add(line);
			}
			else if(isQuestion(auxLine)){ //Question
				listLineQuestions.add(line);
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
