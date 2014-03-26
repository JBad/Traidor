import java.io.IOException;
import java.util.Scanner;


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
