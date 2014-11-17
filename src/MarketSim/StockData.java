package MarketSim;

public class StockData {

	private String mPrice;
	private boolean mRead;
	
	public StockData() {
		mPrice = "";
		mRead = true;
	}

	public String read() {
		mRead = true;
		return mPrice;
	}

	public void update(String price) {
		mRead = false;
		mPrice = price;
	}
	
	public boolean isStale() {
		return mRead;
	}

}
