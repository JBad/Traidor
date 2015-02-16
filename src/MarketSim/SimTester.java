package MarketSim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SimTester {

	public static void main(String[] args) {
		/*Jetty HTTPClient code
			HttpClient trader = new HttpClient();
			trader.start();
			ContentExchange exchange = new ContentExchange(true);
			exchange.setURL("http://127.0.0.1:8080/?ticker=GOOG");
			trader.send(exchange);
			int exchangeState = exchange.waitForDone();
			System.out.println(exchange.getResponseContent()); 
		*/
		for (int i = 0; i < 30; ++i) {
			try {
				URL googUrl = new URL("http://127.0.0.1:8080?client=ig&q=GOOG%3a");
				BufferedReader googleReader = new BufferedReader(new InputStreamReader(googUrl.openConnection().getInputStream()));
				String goog = googleReader.readLine();
				URL msftUrl = new URL("http://127.0.0.1:8080?client=ig&q=MSFT%3a");
				BufferedReader msftReader = new BufferedReader(new InputStreamReader(msftUrl.openConnection().getInputStream()));
				String msft = msftReader.readLine();
				URL aaplUrl = new URL("http://127.0.0.1:8080?client=ig&q=AAPL%3a");
				BufferedReader aaplReader = new BufferedReader(new InputStreamReader(aaplUrl.openConnection().getInputStream()));
				String aapl = aaplReader.readLine();
				URL nflxUrl = new URL("http://127.0.0.1:8080?client=ig&q=NFLX%3a");
				BufferedReader nflxReader = new BufferedReader(new InputStreamReader(nflxUrl.openConnection().getInputStream()));
				String nflx = nflxReader.readLine();
				System.out.println("GOOG: " + goog + " MSFT: " + msft + " AAPL: " + aapl + " NFLX: " + nflx);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
