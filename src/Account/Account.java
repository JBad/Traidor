package Account;
import java.util.HashMap;

import Stock.Stock;
import Utils.LoggingUtils;


/*
 * Simulates the account
 */
public class Account {
	public double cash;
	public HashMap<Stock, Double> portfolio = new HashMap<Stock, Double>();
	public HashMap<Stock, Double> purchasedValues = new HashMap<Stock, Double>();
	
	public String name;
	
	public Account(double principle, String name) {
		cash = principle;
		this.name = name;
	}
	
	/*
	 * Only occurs if the purchase order is less then the capitol
	 * @param stock: stock to purchase
	 * 		  shares: number of shares to purchase
	 */
	public void buyStock(Stock stock, double shares){
		if(shares * stock.price <= cash) {
			if(portfolio.containsKey(stock)){
				portfolio.put(stock, portfolio.get(stock) + shares);
				purchasedValues.put(stock, purchasedValues.get(stock) + shares * stock.price);
			}else {
				portfolio.put(stock, shares);
				purchasedValues.put(stock, shares * stock.price);
			}
			cash -= stock.price * shares;
				LoggingUtils.LogTransaction(
						name, "BUY", stock, shares, portfolio.get(stock), getNetWorth());
		}
	}
	/*
	 * similar to above, but it sells the stock and adds cash to the account
	 */
	public void sellStock(Stock stock, Double shares){
		if(portfolio.get(stock)!= null && shares <= portfolio.get(stock)) {
			portfolio.put(stock, portfolio.get(stock)-shares);
			//This is wrong
			purchasedValues.put(stock, 
					purchasedValues.get(stock) - shares * stock.price < 0 ? 0 :  purchasedValues.get(stock) - shares * stock.price );
			cash += stock.price * shares;

				LoggingUtils.LogTransaction(
						name, "SELL", stock, shares, portfolio.get(stock), getNetWorth());
		}
	}
	
	public double getNetWorth(){
		double value = 0;
		for(Stock s: portfolio.keySet()){
			value += portfolio.get(s) * s.price;
		}
		value += cash;
		return value;
	}
	
	public double getCash(){
		return cash;
	}
	
	public double getStockValue(){
		double value = 0;
		for(Stock s: portfolio.keySet()){
			value += portfolio.get(s) * s.price;
		}
		return value;
	}
	
	public double getPurchaseValue(Stock stock){
		return purchasedValues.get(stock);
	}

	public void sellAllStock(Stock stock) {
		sellStock(stock, portfolio.get(stock));
	}
}
