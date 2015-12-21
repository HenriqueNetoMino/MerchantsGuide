package br.com.vagas.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.vagas.model.Definitions;
import br.com.vagas.model.Prices;
import br.com.vagas.util.NumberConverter;

public class PricesParser {

	List<String> linePrice = new ArrayList<String>();
	Definitions definitions;

	public void addLine(String line) {
		linePrice.add(line);
	}


	public PricesParser(List<String> linePrice, Definitions definitions) {
		this.linePrice = linePrice;
		this.definitions = definitions;
	}

	public Prices parse() {
		Prices prices = new Prices();
		for (String line : linePrice) {
			String itemName = getItemName(line);
			BigDecimal itemPrice = calculateItemPrice(line, itemName,definitions);
			if (itemName != null && !"".equals(itemName.trim()) && itemPrice != BigDecimal.ZERO) {
				prices.addItemPrince(itemName.toUpperCase(), itemPrice);
			}
		}
		return prices;
	}


	private BigDecimal calculateItemPrice(String line, String itemName,Definitions definitions) {
		BigDecimal itemPrice = BigDecimal.ZERO;
		if (itemName != null && !"".equals(itemName.trim())) {
			String[] breakLineQuantityAndValue = line.split(" [i|I][s|S] ");
			String quantityInAlienNumberAndItem = breakLineQuantityAndValue[0];
			String totalValue = breakLineQuantityAndValue[1].replaceAll(" [c|C][r|R][e|E][d|D][i|I][t|T][s|S]", "");
			String[] quatityInAlienNumber = quantityInAlienNumberAndItem.substring(0,quantityInAlienNumberAndItem.lastIndexOf(itemName)).split(" ");
			int quantityArabic = NumberConverter.convertAlienToArabic(quatityInAlienNumber, definitions);
			if (quantityArabic != 0) {
				BigDecimal totalValueArabic = new BigDecimal(totalValue);
				itemPrice = totalValueArabic.divide(new BigDecimal(quantityArabic));
			}
		}
		return itemPrice;
	}

	private String getItemName(String line) {
		String[] breakLineQuantityAndValue = line.split(" [i|I][s|S] ");
		String quantityInAlienNumberAndItem = breakLineQuantityAndValue[0];
		String[] quantityInAlienNumberAndItemTokens = quantityInAlienNumberAndItem.split(" ");
		String itemName = quantityInAlienNumberAndItemTokens[quantityInAlienNumberAndItemTokens.length - 1];
		return itemName;
	}

}
