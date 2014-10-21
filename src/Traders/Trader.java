package Traders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import Account.Account;
import Stock.Stock;
import Strategies.MomentumStrategy;
import Strategies.Strategy;
import Utils.LoggingUtils;
import Utils.MarketUtils;

/*
 * Another class to trade with. feel free to move the main.
 */
public class Trader implements Runnable{
	private Account account;
	private ArrayList<Stock> watched;
	private Map<Stock, Integer> scores;
	private ArrayList<Strategy> strategyProfile;
	private long waitTime;
	private String name;
	
	public static void main(String [] args) throws IOException
	{	
		System.out.println("Enter the Ticker:");
		Scanner sc = new Scanner(System.in);
		Stock stock = new Stock(sc.nextLine());
		System.out.println(MarketUtils.getPrice(stock.ticker));
		System.out.println(stock.price);
		String[] stocks = {"GOOG","AAPL","MSFT","NFLX","SPY", "PG"};//not too tech heavy :p
		Trader sillyMan = new Trader(1000000, stocks, 10000, "SillyMan");
		sillyMan.addStrategy(new MomentumStrategy());
		sillyMan.run();
	}
	
	/*
	 * @params:
	 *  initialValue - initial cash we have given the Trader
	 *  toWatch - a list of ticker symbols that the trader will be aware of
	 *  checkTime - how often the trader looks at the market
	 */
	public Trader (double initialValue, String[] toWatch, long checkTime, String name) throws IOException{
		this.name = name;
		account = new Account(initialValue, name);
		watched = new ArrayList<Stock>();
		strategyProfile = new ArrayList<Strategy>();
		scores = new ConcurrentHashMap<Stock, Integer>();
		for(String ticker : toWatch){
			watched.add(new Stock(ticker));
		}
		waitTime = checkTime;
	}

	public void addStrategy(Strategy s){
		strategyProfile.add(s);
	}
	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep(waitTime);
				int newScore = 0;
				if(MarketUtils.IsMarketOpen){
					for(Stock stock : watched){
						for(Strategy strategy : strategyProfile){
							newScore += strategy.evaluate(stock);
						}
						//If we ever make 15% on a stock, take it. --> 
						//we can never make more then 15k at a time -- this is OK for now
						if(stock.price > account.getPurchaseValue(stock) * 1.15){
							account.sellAllStock(stock);
							scores.put(stock, 0);
						}
						//NOT SURE WE WANT OT DO THIS, currently it just owns score*100000 dollars
						//worth of stock, if score < 0, then it sells everything.
						if(newScore < 0 ){
							account.sellAllStock(stock);
							scores.put(stock, newScore);
						}
						else if(newScore > scores.get(stock)){
							account.buyStock(stock, (10000 * (newScore - scores.get(stock)) / stock.price));
							scores.put(stock, newScore);
						}
						else if(newScore < scores.get(stock)) {
							account.sellStock(stock, (5000 * (newScore - scores.get(stock)) / stock.price));
						}
						newScore = 0;
					}
				}
				
			}
		} catch (Exception e ){
			LoggingUtils.LogToFile(name, "Shit went down, lost a trader :(");
		}
		
	}
	
	
}
