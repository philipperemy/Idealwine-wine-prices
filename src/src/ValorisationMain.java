package src;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class ValorisationMain
{

    public static List<String>   millesimes = Millesime.getAvailableMillesimes();

    public static Constants      constants  = new Constants();

    public static DatabaseAccess da         = new DatabaseAccess();

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, GeneralSecurityException, InterruptedException
    {

        Calendar dateCal = Calendar.getInstance();
        dateCal.set(Calendar.YEAR, 2013);
        dateCal.set(Calendar.MONDAY, Calendar.AUGUST);
        dateCal.set(Calendar.DAY_OF_MONTH, 1);
        dateCal.set(Calendar.HOUR_OF_DAY, 0);
        dateCal.set(Calendar.MINUTE, 0);
        dateCal.set(Calendar.SECOND, 0);

        while (dateCal.get(Calendar.YEAR) <= 2014)
        {
            System.out.println(dateCal.getTime());
            System.out.println(computeValorisationPercentage(dateCal.getTime()));
            System.out.println("_____________");
            dateCal.add(Calendar.DAY_OF_MONTH, 5);
        }

        da.close();
    }

    public static Map<String, Double> lastValorisations = new HashMap<>();

    public static double computeValorisationPercentage(Date date)
    {
        double percentageOverall = 0.0;
        int count = 0;
        for (String millesime : millesimes)
        {
            for (String wineName : constants.getSet().keySet())
            {
                String valo = da.getLastQuoteFromDate(wineName, millesime, "Bouteille", date);
                if (valo != null)
                {

                    Double lastValo = lastValorisations.get(wineName + "@" + millesime);
                    if (lastValo != null && lastValo != 0)
                    {
                        double percentage = (double) Double.valueOf(valo) / lastValo;
                        percentageOverall += percentage;

                        if (Double.isNaN(percentage) || Double.isNaN(percentageOverall))
                        {
                            System.out.println("oups!");
                        }

                        count++;
                    }

                    lastValorisations.put(wineName + "@" + millesime, Double.valueOf(valo));
                }
            }
        }

        if (count > 0)
        {
            return Double.valueOf(new DecimalFormat("#.##").format((double) percentageOverall / count));
        }
        else
        {
            return 0;
        }
    }

    public static double computeValorisation(Date date)
    {
        double value = 0.0;
        for (String millesime : millesimes)
        {
            for (String wineName : constants.getSet().keySet())
            {
                String valo = da.getLastQuoteFromDate(wineName, millesime, "Bouteille", date);
                if (valo != null)
                {
                    value += Double.valueOf(valo);
                }
            }
        }
        return Double.valueOf(new DecimalFormat("#.##").format(value));
    }
}
