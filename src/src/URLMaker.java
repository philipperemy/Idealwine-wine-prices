package src;

public class URLMaker
{

    public static String create(String wineId, String millesime, String size, String wineName)
    {
    	return "http://www.idealwine.com/fr/prix-vin/" + wineId + "-" + millesime + "-" + size + "-" + wineName +".jsp";
    }
}
