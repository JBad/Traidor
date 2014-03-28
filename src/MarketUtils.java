import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/*
 * Utils to deal with the web--quite ugly/shitty
 * Ticker MUST BE CORRECT
 * ONLY NASDAQ 
 */
public class MarketUtils {
	private static String preUrl = "http://finance.google.com/finance/info?client=ig&q=NASDAQ%3a";
	
	public static double getPrice(String ticker) throws IOException {
		URL valUrl = new URL(preUrl+ticker);
		URLConnection urlC = valUrl.openConnection();
		//System.out.println(valUrl.toString());
		 BufferedReader in = new BufferedReader(
				 new InputStreamReader(urlC.getInputStream()));
		 String result = "";
		 String inputLine = "";
		 while ((inputLine = in.readLine()) != null)
	            result += inputLine;
	     in.close();
		 int valLocation = result.indexOf("NASDAQ");
		 int valLocationDot = result.indexOf(".", valLocation+15);
		 String value = result.substring(valLocation+15, valLocationDot+3);
		 return Double.parseDouble(value);
		
	}

}
