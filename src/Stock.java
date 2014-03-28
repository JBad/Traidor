import java.io.IOException;
import java.util.ArrayList;

/*
 * representation of an individual stock
 */
public class Stock {
	
	/*
	 * grabs the ticker and updates the price. fills the history array with the
	 * initial value (Removes annoying edge conditions, each day would start with
	 * a "hold" rating I suppose
	 */
	public Stock(String ticker) throws IOException {
		this.ticker = ticker;
		price = MarketUtils.getPrice(ticker);
		priceHistory = new ArrayList<Double>();
		for(int i = 0; i < historySize; ++i){
			priceHistory.add(price);
		}
	}
	/*
	 * updates the current price of the stock, and places the 
	 * previous one in history
	 */
	public void update() throws IOException {
		if(priceHistory.size() >= historySize) {
			priceHistory.remove(historySize-1);
		}
		priceHistory.add(0, price);
		price = MarketUtils.getPrice(ticker);
	}
	
	/*
	 * @return the history arrayList so that we can run some tests
	 */
	public ArrayList<Double> getHistory(){
		return priceHistory;
	}
	
	/*
	 * @param steps - number of past values that should be checked, if it is
	 * 			larger then the size of the array, we will simply check the 
	 * 			whole array.
	 * @return true if the stock has been moving up in the past steps
	 */
	public boolean movingUp(int steps){
		if(steps >= historySize) {
			steps = historySize-1;
		}
		for(int i = 0; i < steps; ++i){
			if(priceHistory.get(i) < priceHistory.get(i+1)) {
				return false;
			}
		}
		return true;
	}
	/*
	 * Same as above but in the opposite direction
	 */
	public boolean movingDown(int steps){
		if(steps >= historySize) {
			steps = historySize-1;
		}
		for(int i = 0; i < steps; ++i){
			if(priceHistory.get(i) > priceHistory.get(i+1)) {
				return false;
			}
		}
		return true;
	}
	/*
	 * @return the value of the time weighted value this is calculated by the following:
	 * 		[x_i-x_(i-1)] * i
	 * (we can change this to be a better, just a simple idea)
	 */
	public double timeWeightedMovment(int steps){
		if(steps >= historySize) {
			steps = historySize-1;
		}
		double result = 0.0;
		for(int i = 0; i < steps; ++i){
			result = (priceHistory.get(i) - priceHistory.get(i+1))*(steps - i);
		}
		return result;
	}
	
	/*
	 * Find the average movement of the past steps prices in priceHistory
	 * @return if the majority of the past movements have been up, then true
	 * 			if not then false;
	 */
	public boolean averageUp(int steps){
		if(steps >= historySize) {
			steps = historySize-1;
		}
		int up = 0, down = 0;
		for(int i = 0; i < steps; ++i){
			if(priceHistory.get(i) > priceHistory.get(i+1)){
				++up;
			}
			else if(priceHistory.get(i) < priceHistory.get(i+1)){
				++down;
			}
		}
		return up > down;
	}
	
	public String ticker;
	public double price;
	public ArrayList<Double> priceHistory;
	public static final int historySize = 20; 
}
