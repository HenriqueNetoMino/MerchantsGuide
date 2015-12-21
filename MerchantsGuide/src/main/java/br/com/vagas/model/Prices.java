package br.com.vagas.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Prices {

	Map<String, BigDecimal> itemPrices = new HashMap<String, BigDecimal>();

	public void addItemPrince(String item, BigDecimal price) {
		itemPrices.put(item, price);
	}


	public BigDecimal getItemPrice(String itemName) {
		return itemPrices.get(itemName);
	}



}
