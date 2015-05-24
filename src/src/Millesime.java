package src;

import java.util.ArrayList;
import java.util.List;

public class Millesime
{

    public static List<String> getAllMillesimes()
    {
        List<String> millesimes = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            if (i < 10)
            {
                millesimes.add("190" + i);
            }
            else
            {
                millesimes.add("19" + i);
            }
        }
        millesimes.add("2000");
        millesimes.add("2001");
        millesimes.add("2002");
        millesimes.add("2003");
        millesimes.add("2004");
        millesimes.add("2005");
        millesimes.add("2006");
        millesimes.add("2007");
        millesimes.add("2008");
        millesimes.add("2009");
        millesimes.add("2010");
        millesimes.add("2011");
        millesimes.add("2012");
        millesimes.add("2013");

        return millesimes;
    }

    public static List<String> getAvailableMillesimes()
    {
        List<String> millesimes = new ArrayList<>();
        millesimes.add("1956");
        millesimes.add("1962");
        millesimes.add("1988");
        millesimes.add("1989");
        millesimes.add("1990");
        millesimes.add("1991");
        millesimes.add("1992");
        millesimes.add("1993");
        millesimes.add("1994");
        millesimes.add("1995");
        millesimes.add("1996");
        millesimes.add("1997");
        millesimes.add("1998");
        millesimes.add("1999");
        millesimes.add("2000");
        millesimes.add("2001");
        millesimes.add("2002");
        millesimes.add("2003");
        millesimes.add("2004");
        millesimes.add("2005");
        millesimes.add("2006");
        millesimes.add("2007");
        millesimes.add("2008");
        millesimes.add("2009");
        millesimes.add("2010");
        millesimes.add("2011");
        millesimes.add("2012");
        millesimes.add("2013");
        return millesimes;
    }

}
