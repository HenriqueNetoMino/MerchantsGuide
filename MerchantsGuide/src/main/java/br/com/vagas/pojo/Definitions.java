package br.com.vagas.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.vagas.enums.Roman;

public class Definitions {
	Map<String, Roman> definitionData = new HashMap<String, Roman>();
	List<String> lineDefinition = new ArrayList<String>();
	
	public void addDefinition(String alienNumber, Roman romanNumber){
		this.definitionData.put(alienNumber, romanNumber);
	}
	
	public Roman getRoman(String alienNumber)
	{
		return this.definitionData.get(alienNumber);
	}
	
	public void addLine(String line)
	{
		lineDefinition.add(line);
	}
	
	public void parse()
	{
		for(String line : lineDefinition)
		{
			
			String alienNumber  = null;
			String romanNumber = null;
			try
			{
				String[] definitionTokens = line.split(" ");
				alienNumber = definitionTokens[0];
				romanNumber = definitionTokens[2];
				Roman romanNumberEnum = Roman.valueOf(romanNumber);
				addDefinition(alienNumber, romanNumberEnum);
			}catch(IndexOutOfBoundsException | IllegalArgumentException ex){
			}
		}
	}

}
