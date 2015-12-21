package br.com.vagas.enums;

public enum Roman {

	I("I", 1), V("V", 5), X("X", 10), L("L", 50), C("C", 100), D("D", 500), M("M", 1000);

	private String romanCharacter;
	private int value;

	Roman(String romanCharacter, int value) {
		this.romanCharacter = romanCharacter;
		this.value = value;
	}

	public String getRomanCharacter() {
		return this.romanCharacter;
	}

	public int getValue() {
		return this.value;
	}

}
