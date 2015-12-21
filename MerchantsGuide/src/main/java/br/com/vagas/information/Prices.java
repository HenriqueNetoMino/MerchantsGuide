package br.com.vagas.information;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.vagas.util.NumberConverter;

public class Prices {

	Map<String, BigDecimal> itemPrices = new HashMap<String, BigDecimal>();
	List<String> linePrice = new ArrayList<String>();

	public void addItemPrince(String item, BigDecimal price) {
		itemPrices.put(item, price);
	}

	public void addLine(String line) {
		linePrice.add(line);
	}

	public void parse(Definitions definitions) {
		for (String line : linePrice) {
			String itemName = getItemName(line);
			BigDecimal itemPrice = calculateItemPrice(line, itemName,definitions);
			if (itemName != null && !"".equals(itemName.trim()) && itemPrice != BigDecimal.ZERO) {
				addItemPrince(itemName.toUpperCase(), itemPrice);
			}
		}
	}

	public BigDecimal getItemPrice(String itemName) {
		return itemPrices.get(itemName);
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
