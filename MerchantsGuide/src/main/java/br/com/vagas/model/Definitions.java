package br.com.vagas.model;

import java.util.HashMap;
import java.util.Map;

import br.com.vagas.enums.Roman;

public class Definitions {
	Map<String, Roman> definitionData = new HashMap<String, Roman>();

	public void addDefinition(String alienNumber, Roman romanNumber)
	{
		this.definitionData.put(alienNumber, romanNumber);
	}

	public Roman getRoman(String alienNumber)
	{
		return this.definitionData.get(alienNumber);
	}

}
