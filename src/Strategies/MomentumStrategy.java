package Strategies;

import Stock.Stock;

public class MomentumStrategy implements Strategy {

	/*
	 * This evaluation checks if the stock has been moving up for the past: 10,20,40,80,160
	 * time period for each time this is true, the return value will have one added to it, 
	 * so the result will be in a range of [-5,5]
	 */
	@Override
	public int evaluate(Stock stock) {
		int value = 0;
		int step = 10;
		if(stock.movingUp(step)){
			while (step < 200) {
				if(stock.movingUp(step)){
					++value;
				}
				step *= 2;
			}
		}
		else if(stock.movingDown(step)){
			while (step < 200) {
				if(stock.movingDown(step)){
					++value;
				}
				step *= 2;
			}
		}
		return value;
	}

}
