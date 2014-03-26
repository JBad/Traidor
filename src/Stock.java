import java.io.IOException;

/*
 * representation of an individual stock
 */
public class Stock {
	
	public Stock(String ticker) throws IOException {
		this.ticker = ticker;
		price = MarketUtils.getPrice(ticker);
	}
	public String ticker;
	public double price;
}
