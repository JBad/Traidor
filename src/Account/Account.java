package Account;
import java.util.HashMap;

import Stock.Stock;


/*
 * Simulates the account
 */
public class Account {
	public double cash;
	public HashMap<Stock, Float> portfolio = new HashMap<Stock, Float>();
	//public Stock myStock;
	//public float numShares;
	
	public Account(double principle) {
		cash = principle;
	}
	
	/*
	 * Only occurs if the purchase order is less then the capitol
	 * @param stock: stock to purchase
	 * 		  shares: number of shares to purchase
	 */
	public void buyStock(Stock stock, int shares){
		if(shares * stock.price <= cash) {
			if(portfolio.containsKey(stock)){
				portfolio.put(stock, portfolio.get(stock) + shares);
			}else {
				portfolio.put(stock, (float) shares);
			}
			cash -= stock.price * shares;
		}
	}
	/*
	 * similar to above, but it sells the stock and adds cash to the account
	 */
	public void sellStock(Stock stock, int shares){
		if(portfolio.get(stock)!= null &&shares <= portfolio.get(stock)) {
			portfolio.put(stock, portfolio.get(stock)-shares);
			cash += stock.price * shares;
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
}
