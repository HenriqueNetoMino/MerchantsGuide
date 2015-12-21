package br.com.vagas.processors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import org.junit.Assert;
import org.junit.Test;

public class MerchantsGuideProcessorTest {


	@Test
	public void processExpectedResult(){
		String expected = "[pish tegj glob glob is 42, glob prok Silver is 68 Credits, glob prok Gold is 57800 Credits, glob prok Iron is 782 Credits, I have no idea what you are talking about.]";
		List<String> inputLines = new ArrayList<String>();

		inputLines.add("glob is I");
		inputLines.add("prok is V");
		inputLines.add("pish is X");
		inputLines.add("tegj is L");
		inputLines.add("glob glob Silver is 34 Credits");
		inputLines.add("glob prok Gold is 57800 Credits");
		inputLines.add("pish pish Iron is 3910 Credits");
		inputLines.add("how much is pish tegj glob glob ?");
		inputLines.add("how many Credits is glob prok Silver ?");
		inputLines.add("how many Credits is glob prok Gold ?");
		inputLines.add("how many Credits is glob prok Iron ?");
		inputLines.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");


		MerchantsGuideProcessor merchantsGuideProcessor = new MerchantsGuideProcessor(inputLines);
		Collection<String> answers = merchantsGuideProcessor.processAndAnswerQuestions();
		String returnText = answers.toString();
		Assert.assertEquals(expected, returnText);
	}



}
