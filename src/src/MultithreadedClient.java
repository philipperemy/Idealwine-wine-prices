package src;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MultithreadedClient {

	private WebClient webClient = null;

	public MultithreadedClient() {
		try {
			webClient = Client.getIdealwineBrowserClient();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public synchronized HtmlPage getHtmlPage(String wineName, String millesime,
			String content) {

		HtmlPage htmlPage = null;
		try {
			htmlPage = Client.getHtmlPage(webClient, wineName, millesime,
					content);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return htmlPage;

	}

	public synchronized void closeAllWindows() {
		webClient.closeAllWindows();
	}

}
