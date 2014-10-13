package Strategies;

import Stock.Stock;

public class HistoricalAverage implements Strategy{

	@Override
	public int evaluate(Stock stock) {
		if(stock.averagePrive(200) < stock.price){
			return 1;
		}
		return 0;
	}

}
