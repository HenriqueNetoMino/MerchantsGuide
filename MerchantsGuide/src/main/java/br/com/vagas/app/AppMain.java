package br.com.vagas.app;



import java.util.Collection;
import br.com.vagas.app.util.FileUtil;
import br.com.vagas.parser.MerchantsGuideProcessor;

public class AppMain {
	public static void main(String[] args) {
		validateInputArgs(args);
		MerchantsGuideProcessor merchantsGuideProcessor = new MerchantsGuideProcessor(args[0]);
		Collection<String> answers = merchantsGuideProcessor.processAndAnswerQuestions();
		printAnswers(answers);
	}
	
	
	public static void printAnswers(Collection<String> answers) 
	{
		for(String answer : answers)
		{
			System.out.println(answer);
		}
	}

	private static void validateInputArgs(String[] args) throws IllegalArgumentException {
		validateQuantityInputArgs(args);
		validateContentArgs(args);
	}


	private static void validateQuantityInputArgs(String[] args) throws IllegalArgumentException {
		if(args == null || args.length == 0)
		{
			throw new IllegalArgumentException("Input file path is needed.");
		}
	}

	private static void validateContentArgs(String[] args) throws IllegalArgumentException{
		if(!FileUtil.fileExists(args[0]))
		{
			throw new IllegalArgumentException("Input file doesn't exist.");
		}
		if(FileUtil.fileIsEmpty(args[0]))
		{
			throw new IllegalArgumentException("Input file is empty.");
		}
	}
}
