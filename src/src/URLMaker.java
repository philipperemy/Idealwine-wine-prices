package src;

public class URLMaker
{

    public static String create(String wineName, String millesime, String size)
    {
        return "http://www.idealwine.com/fr/cotes_vins/cote.jsp?vin=" + wineName + "&millesime=" + millesime + "&format=" + size;
    }
}
