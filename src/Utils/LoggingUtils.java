package Utils;

import java.io.FileWriter;

import Stock.Stock;
/*
 * Help with logging, one central location
 */
public class LoggingUtils {
	private static String LogFile = "LogFile.txt";
	private static String TransactionHistory = "Transactions.csv";
	
	
	/*
	 * If we want to log stuff, here is a function
	 * 
	 *  TODO: needs to APPEND! not rewrite, I am on a plane and I don't have Internet
	 * and i forgot how to do that
	 */
	public static void LogToFile(String message, String name) 
	{
		try {
			FileWriter out = new FileWriter(name + "_" + LogFile,true); //the true will append the new data
			out.write(message + "\n");
	//		System.out.println(message);
			out.close();
		} catch (Exception e) {
			System.out.println("CANNOT LOG");
		}
	}
	
	/*
	 * Prints to a CSV the following line:
	 * [BUY/SELL], [TICKER], [PRICE], [NUMBER OF SHARES CHANGED], 
	 *  [CURRENT NUMBER OF SHARES], [CURRENT VALUE OF ACCOUNT]
	 * This will allows us to make graphs and stuff.
	 * 
	 * TODO: needs to APPEND! not rewrite, I am on a plane and I don't have Internet
	 * and i forgot how to do that
	 */
	public static void LogTransaction(String name, String bs, Stock stock, double sharesChanged, double sharesOwned, double d)
	{
		try {
			FileWriter out = new FileWriter(name + "_" + TransactionHistory,true);
		out.write(bs +
				"," + stock.ticker +
				"," + stock.price +
				"," + sharesChanged +
				"," + sharesOwned +
				"," + d + "\n");
		out.close();

/*		System.out.println(bs +
				"," + stock.ticker +
				"," + stock.price +
				"," + sharesChanged +
				"," + sharesOwned +
				"," + d);*/
		} catch (Exception e) {
			System.out.println("CANNOT LOG");
		}
	}

}
