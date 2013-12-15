package src;

import java.io.IOException;
import java.net.MalformedURLException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Client
{

    public static WebClient getIdealwineBrowserClient() throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
        final WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setAppletEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setPrintContentOnFailingStatusCode(true);
        client.getOptions().setPopupBlockerEnabled(true);
        client.getOptions().setThrowExceptionOnScriptError(false);

        final HtmlPage homePage = client.getPage("http://www.idealwine.com/fr/home/index_home.jsp");

        String htmlStr = homePage.asText();
        if (!htmlStr.contains("Mes alertes"))
        {
            System.out.print("Submitting... ");
            final HtmlForm form = homePage.getFormByName("loginForm");

            final HtmlTextInput identifier = form.getInputByName("ident");
            final HtmlPasswordInput password = form.getInputByName("pswd");
            final HtmlSubmitInput submit = form.getInputByName("ok");

            identifier.setValueAttribute("REMYB");
            password.setValueAttribute("HORN2296");

            final HtmlPage page2 = submit.click();
            htmlStr = page2.asText();
            System.out.println("Logging successful");
        }
        else
        {
            System.out.println("Already logged... Resuming");
        }
        if (htmlStr.contains("Mes alertes"))
        {
        }
        else
        {
            System.out.println("Still not OK");
            System.exit(0);
        }

        return client;
    }

    public static HtmlPage getHtmlPage(WebClient client, String wineName, String millesime, String size) throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {

        return client.getPage(URLMaker.create(wineName, millesime, size));

    }
}
