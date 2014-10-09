package Traders;
import java.io.IOException;
import java.util.Scanner;

import Stock.Stock;
import Utils.MarketUtils;

/*
 * Another class to trade with. feel free to move the main.
 */
public class Trader {

	public static void main(String [] args) throws IOException
	{
		System.out.println("Enter the Ticker:");
		Scanner sc = new Scanner(System.in);
		Stock stock = new Stock(sc.nextLine());
		System.out.println(MarketUtils.getPrice(stock.ticker));
		System.out.println(stock.price);
	}
}
