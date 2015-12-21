package br.com.vagas.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Questions {
	Map<String, String> questionAndAnswers = new LinkedHashMap<String, String>();

	public Collection<String> getAnswers() {
		return questionAndAnswers.values();
	}

	public void addQuestionAndAnswer(String question, String answer){
		questionAndAnswers.put(question, answer);
	}



}
