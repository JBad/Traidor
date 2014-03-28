
/*
 * Simulates the account
 */
public class Account {
	public double cash;
	public Stock myStock;
	public float numShares;
	
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
			myStock = stock;
			numShares = shares;
			cash -= stock.price * numShares;
		}
	}
	/*
	 * similar to above, but it sells the stock and adds cash to the account
	 */
	public void sellStock(Stock stock, int shares){
		if(shares <= numShares && myStock.ticker.equals(stock.ticker)) {
			myStock = null;
			numShares -= shares;
			cash += stock.price * shares;
		}
	}
}
