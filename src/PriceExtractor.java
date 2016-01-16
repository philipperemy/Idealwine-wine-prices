
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PriceExtractor
{

    public static String extractPrice(HtmlPage pageContentPage)
    {
        String pageContent = pageContentPage.asXml();
        String tag = "<p class=\"red\" align=\"right\">";
        int idx = pageContent.indexOf(tag);
        idx += tag.length();
        String azerTrop = pageContent.substring(idx);
        int snd_idx = azerTrop.indexOf("EUR");
        String price = azerTrop.substring(0, snd_idx);
        return price.trim();
    }

}
