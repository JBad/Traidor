package MarketSim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class MarketSimulator extends AbstractHandler {

	public static final int SIMPORT = 8080;

	private BufferedReader marketReader;
	Scanner sc = new Scanner(System.in);

	private HashMap<String, StockData> market;
	private ArrayList<String> tickers;

	public MarketSimulator(String filename) {
		market = new HashMap<String, StockData >();
		tickers = new ArrayList<String>();
		while (!openStreamAndPopulate(filename)) {
			System.out.print("Invalid file name: " + filename + ". Try again: ");
			filename = sc.nextLine();
		}
		sc.close();
	}

	private boolean openStreamAndPopulate(String filename) {
		try {
			marketReader = new BufferedReader(new FileReader(filename));
			if (!marketReader.ready()) {
				return false;
			}

			readTickers();
			updateMarket();
			return true;
		} catch (Exception e) {
			try {
				marketReader.close();
				return false;
			} catch (Exception e2) {
				return false;
			}
		}
	}
	
	private void readTickers() throws IOException {
		String stockTickers = marketReader.readLine();
		StringTokenizer tokenizer = new StringTokenizer(stockTickers, ",");
		while (tokenizer.hasMoreTokens()) {
			String ticker = tokenizer.nextToken();
			market.put(ticker, new StockData());
			tickers.add(ticker);
		}
	}
	
	private void updateMarket() throws NumberFormatException, IOException {
		String prices = marketReader.readLine();
		StringTokenizer tokenizer = new StringTokenizer(prices, ",");
		for (int i = 0; tokenizer.hasMoreTokens(); ++i) {
			market.get(tickers.get(i)).update(tokenizer.nextToken());
		}
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(SIMPORT);
		server.setHandler(new MarketSimulator("market.csv"));
		server.start();
		server.join();
	}

	@Override
	public void handle(String target,
					Request baseRequest,
					HttpServletRequest request,
					HttpServletResponse response)
						throws IOException, ServletException {
		if (market.get(request.getParameter("q")).isStale()) {
			updateMarket();
		};
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println(market.get(request.getParameter("q")).read());
	}

}
