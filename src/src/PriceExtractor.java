package src;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PriceExtractor {

	public static String extractPrice(HtmlPage pageContentPage) {
		String pageContent = pageContentPage.asXml();
		int idx = pageContent.indexOf("<strong id=\"cote_indicateur\">");
		idx += "<strong id=\"cote_indicateur\">".length();
		String azerTrop = pageContent.substring(idx);
		int snd_idx = azerTrop.indexOf("EUR");
		String price = azerTrop.substring(0, snd_idx);
		return price.trim();
	}
	
}
