package br.com.vagas.parser;

import java.util.ArrayList;
import java.util.List;

import br.com.vagas.enums.Roman;
import br.com.vagas.model.Definitions;

public class DefinitionsParser {
	List<String> lineDefinition = new ArrayList<String>();

	public DefinitionsParser(List<String> lineDefinition) {
		this.lineDefinition = lineDefinition;
	}

	public void addLine(String line)
	{
		lineDefinition.add(line);
	}

	public Definitions parse()
	{
		Definitions definitions = new Definitions();
		for(String line : lineDefinition)
		{

			String alienNumber  = null;
			String romanNumber = null;
			try
			{
				String[] definitionTokens = line.split(" ");
				alienNumber = definitionTokens[0];
				romanNumber = definitionTokens[2];
				Roman romanNumberEnum = Roman.valueOf(romanNumber.toUpperCase());
				definitions.addDefinition(alienNumber.toUpperCase(), romanNumberEnum);
			}catch(IndexOutOfBoundsException | IllegalArgumentException ex){
			}
		}
		return definitions;
	}

}
