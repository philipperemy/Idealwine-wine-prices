
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Main
{
    public static int          processorCount = Runtime.getRuntime().availableProcessors();

    public static List<String> millesimes     = Millesime.getAvailableMillesimes();

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, GeneralSecurityException, InterruptedException
    {
    	//processorCount = 1;
    	System.setErr(new PrintStream("NUL"));
        long n1 = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newFixedThreadPool(processorCount);

        List<MultithreadedClient> clients = new ArrayList<>();
        for (int i = 0; i < processorCount; i++)
        {
            clients.add(new MultithreadedClient());
        }
        DatabaseAccess da = new DatabaseAccess();
        Constants constants = new Constants();

        Map<String, Wine> constantsMap = constants.getSet();
        Iterator it = new Iterator(processorCount);

        for (String wineName : constantsMap.keySet())
        {
        	Wine wine = constantsMap.get(wineName);
            threadPool.execute(new ThreadTask(clients.get(it.getNext()), da, wine.id, wineName, wine.techName));
        }

        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        for (int i = 0; i < processorCount; i++)
        {
            clients.get(i).closeAllWindows();
        }

      
        System.out.println("update took " + (System.currentTimeMillis() - n1) + " ms");

    }

    public static class ThreadTask implements Runnable
    {

        private MultithreadedClient multithreadedClient;
        private DatabaseAccess      da;
        private String              idealwineID;
        private String              wineName;
        private String 				wineTechnicalName;

        public ThreadTask(MultithreadedClient multithreadedClient, DatabaseAccess da, String idealwineID, String wineName, String wineTechnicalName)
        {
            this.multithreadedClient = multithreadedClient;
            this.da = da;
            this.idealwineID = idealwineID;
            this.wineName = wineName;
            this.wineTechnicalName = wineTechnicalName;
        }

        @Override
        public void run()
        {
            for (String millesime : millesimes)
            {
                try
                {
                    final HtmlPage page = multithreadedClient.getHtmlPage(idealwineID, millesime, "Bouteille", wineTechnicalName);

                    String quoteStr = PriceExtractor.extractPrice(page);
                    Double.valueOf(quoteStr);

                    da.insertWineQuote(wineName, millesime, "Bouteille", quoteStr);
                }
                catch (Exception e)
                {
                    System.out.println("Quote unavailable for " + wineName + " " + millesime);
                }
            }

        }

    }

}
