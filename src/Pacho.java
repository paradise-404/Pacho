import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * Program Description: Pacho
 * Aesthetic image retriever. Use for art inspo, beautiful phone backgrounds, your own creative projects, or just for browsing. 
 * Pacho returns results from Tumblr, Twitter, and Pinterest. These are social media platforms that rank aesthetic images yet are not easily found in Google Image Search. 
 * Pacho Premium also returns the keywords in Japanese for images inaccessible by English, and searches Pixiv.
 * 
 * Usage:
 * Download the JAR and run it. Pacho should open up as a black search bar on your desktop.
 * Make sure you're logged in to the social media platforms above before using.
 * Type in your search terms like "bubble tea" and hit the play button.
 * 
 * Parameters:
 * Windows, Mac
 */

/*
 * TODO: 
 * 1. web app
 * 2. optional social media platforms with checklist
 * 3. save images within pacho with intial query as a tag
 * 4. auto open in new window
 * 5. rewrite code to make API calls to social media plats rather than adding tokens to website strings
 */

public class Pacho {

	public static void main(String[] args) throws IOException {
		String realQuery = concatenateQuery(args);
		ArrayList<String> urls = createUrls(realQuery);
		for (int i = 0; i < 5; i ++) {
			openUrlInBrowser(urls.get(i));
		}
	}
	
	// Create URLs for Twitter, Tumblr, Pixiv, and Pinterest
	private static ArrayList<String> createUrls(String query) throws IOException {
		String blankTwitterUrl = "https://twitter.com/search?f=images&vertical=default&q=";
		String blankTumblrUrl = "https://www.tumblr.com/search/";
		String blankPinterestUrl = "https://www.pinterest.com/search/pins/?q=";
		ArrayList<String> urls = new ArrayList<String>();
		// if query has spaces in it
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(query);
		boolean spaces = matcher.find();
		if (spaces) {     
			StringBuilder twitterUrl = new StringBuilder();
			twitterUrl.append(blankTwitterUrl);
			StringBuilder tumblrUrl = new StringBuilder();
			tumblrUrl.append(blankTumblrUrl);
			StringBuilder pinterestUrl = new StringBuilder();
			pinterestUrl.append(blankPinterestUrl);
			String[] engResult = query.split("\\s");
			for (int i = 0; i < engResult.length; i++) {
				twitterUrl.append(engResult[i] + "%20");
				tumblrUrl.append(engResult[i] + "+");
				pinterestUrl.append(engResult[i] + "%20");
			}
			urls.add(twitterUrl.toString());
			urls.add(tumblrUrl.toString());
			urls.add(pinterestUrl.toString());
			return urls;
		} else
		urls.add(blankTwitterUrl + query);
		urls.add(blankTumblrUrl + query);
		urls.add(blankPinterestUrl + query);
		return urls;
	}
	
	
	// concatenating strings
	private static String concatenateQuery(String[] query) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < query.length; i++) {
			if (i < query.length - 1) {
		   strBuilder.append(query[i]);
		   strBuilder.append(" ");
			}
		   else
			   strBuilder.append(query[i]);
		}
		String realQuery = strBuilder.toString();
		return realQuery;
	}

	// open query in browser
	private static void openUrlInBrowser(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		
	}
}
