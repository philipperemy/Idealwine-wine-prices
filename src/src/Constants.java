package src;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

public class Constants
{

    public static void main(String[] args)
    {
        Constants constants = new Constants();
        constants.getSet();
    }

    public synchronized Map<String, String> getSet()
    {
        Map<String, String> map = new TreeMap<String, String>();
        try
        {
            Field[] fields = Constants.class.getFields();
            for (Field field : fields)
            {
                String name = field.getName();
                String value = field.get(this).toString();
                map.put(name, value);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public Map<String, String> getSet(String wineName)
    {
        Map<String, String> map = new TreeMap<String, String>();
        try
        {
            Field[] fields = Constants.class.getFields();
            for (Field field : fields)
            {
                String name = field.getName();
                if (name != null && name.equalsIgnoreCase(wineName))
                {
                    String value = field.get(this).toString();
                    map.put(name, value);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public final static String TROPLONG_MONDOT     = "707";
    public final static String SOCIANDO_MALLET     = "658";
    public final static String HAUT_MARBUZET       = "343";
    public final static String PAVIE               = "527";
    public final static String PETRUS              = "541";
    public final static String LEOVILLE_BARTON     = "424";
    public final static String LAFITE_ROTHSCHILD   = "385";
    public final static String LATOUR              = "418";
    public final static String MOUTON_ROTHSCHILD   = "508";
    public final static String MARGAUX             = "466";
    public final static String HAUT_BRION          = "334";
    public final static String LONGUEVILLE_BARON   = "550";
    public final static String DUCRU_BEAUCAILLOU   = "227";
    public final static String LEOVILLE_LAS_CASES  = "425";
    public final static String DURFORT_VIVENS      = "232";
    public final static String LEOVILLE_POYFERRE   = "426";
    public final static String COS_ESTOURNEL       = "170";
    public final static String RAUZAN_SEGLA        = "591";
    public final static String MONTROSE            = "494";
    public final static String GRUAUD_LAROSE       = "316";
    public final static String ISSAN               = "366";
    public final static String GISCOURS            = "282";
    public final static String KIRWAN              = "374";
    public final static String LAGRANGE            = "392";
    public final static String CALON_SEGUR         = "97";
    public final static String LA_LAGUNE           = "393";
    public final static String PALMER              = "517";
    public final static String BRANAIRE_DUCRU      = "77";
    public final static String BEYCHEVELLE         = "58";
    public final static String PONTET_CANET        = "567";
    public final static String GRAND_PUY_DUCASSE   = "305";
    public final static String GRAND_PUY_LACOSTE   = "306";
    public final static String BELGRAVE            = "44";
    public final static String LYNCH_BAGES         = "449";
    public final static String CANTEMERLE          = "107";
    public final static String YQUEM               = "738";
    public final static String CLIMENS             = "145";
    public final static String COUTET              = "183";
    public final static String GUIRAUD             = "320";
    public final static String HAUT_PEYRAGUET      = "153";
    public final static String LAFAURIE_PEYRAGUET  = "383";
    public final static String TOUR_BLANCHE        = "679";
    public final static String RABAUD_PROMIS       = "584";
    public final static String RAYNE_VIGNEAU       = "594";
    public final static String RIEUSSEC            = "602";
    public final static String SIGALAS_RABAUD      = "652";
    public final static String SUDUIRAUT           = "662";
    public final static String HAUT_BAGES_LIBERAL  = "325";
    public final static String HAUT_BAILLY         = "327";
    public final static String MALARTIC_LAGRAVIERE = "459";
    public final static String PAVIE_MACQUIN       = "529";
    public final static String PAVILLON_ROUGE      = "531";
    public final static String POUJEAUX            = "574";
    public final static String CANTENAC_BROWN      = "109";
    public final static String CHASSE_SPLEEN       = "132";
    public final static String CHEVALIER_ROUGE     = "219";
    public final static String CHEVALIER_BLANC     = "218";
    public final static String DE_FARGUES          = "238";
    public final static String DOISY_DAENE         = "215";
    public final static String DE_FIEUZAL          = "247";
    public final static String FERRIERE            = "244";
    public final static String GILETTE             = "281";
    public final static String GLORIA              = "284";
    public final static String LA_FLEUR_PETRUS     = "256";
    public final static String LARCIS_DUCASSE      = "408";
    public final static String MARQUIS_DU_TERME    = "468";
    public final static String PETIT_VILLAGE       = "540";
    public final static String PRIEURE_LICHINE     = "577";
    public final static String SAINT_PIERRE        = "636";
    public final static String SMITH_HAUT_LAFITTE  = "657";
    public final static String SOUTARD             = "660";
    public final static String LA_TOUR_CARNET      = "680";
    public final static String TOUR_HAUT_BRION     = "691";
    public final static String TROTANOY            = "708";
    public final static String GAZIN               = "279";
}
