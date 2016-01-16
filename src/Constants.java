
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

public class Constants {

	public static void main(String[] args) {
		Constants constants = new Constants();
		constants.getSet();
	}

	public synchronized Map<String, Wine> getSet() {
		Map<String, Wine> map = new TreeMap<>();
		try {
			Field[] fields = Constants.class.getFields();
			for (Field field : fields) {
				String name = field.getName();
				Wine value = (Wine) field.get(this);
				map.put(name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}

	public final static Wine TROPLONG_MONDOT = new Wine("707",
			"Bordeaux-Saint-Emilion-Chateau-Troplong-Mondot-1er-Grand-Cru-Classe-B-Rouge");
	public final static Wine SOCIANDO_MALLET = new Wine("658",
			"Bordeaux-Haut-Medoc-Chateau-Sociando-Mallet-Rouge");
	
	public final static Wine HAUT_MARBUZET = new Wine("343", "Bordeaux-Saint-Estephe-Chateau-Haut-Marbuzet-Cru-Bourgeois-Exceptionnel-Rouge");
	public final static Wine PAVIE = new Wine("527", "Bordeaux-Saint-Emilion-Chateau-Pavie-1er-Grand-Cru-Classe-A-Rouge");
	public final static Wine PETRUS = new Wine("541", "Bordeaux-Pomerol-Petrus-Rouge");
	public final static Wine LEOVILLE_BARTON = new Wine("424", "Bordeaux-Saint-Julien-Chateau-Leoville-Barton-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine LAFITE_ROTHSCHILD = new Wine("385", "Bordeaux-Pauillac-Chateau-Lafite-Rothschild-1er-Grand-Cru-Classe-Rouge");
	public final static Wine LATOUR = new Wine("418", "Bordeaux-Pauillac-Chateau-Latour-1er-Grand-Cru-Classe-Rouge");
	public final static Wine MOUTON_ROTHSCHILD = new Wine("508", "Bordeaux-Pauillac-Chateau-Mouton-Rothschild-1er-Grand-Cru-Classe-Rouge");
	public final static Wine MARGAUX = new Wine("466", "Bordeaux-Margaux-Chateau-1er-Grand-Cru-Classe-Rouge");
	public final static Wine HAUT_BRION = new Wine("334", "Bordeaux-Pessac-Leognan-Chateau-Haut-Brion-1er-Grand-Cru-Classe-Rouge");
	public final static Wine LONGUEVILLE_BARON = new Wine("550", "Bordeaux-Pauillac-Chateau-Pichon-Longueville-Baron-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine DUCRU_BEAUCAILLOU = new Wine("227", "Bordeaux-Saint-Julien-Chateau-Ducru-Beaucaillou-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine LEOVILLE_LAS_CASES = new Wine("425", "Bordeaux-Saint-Julien-Chateau-Leoville-Las-Cases-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine DURFORT_VIVENS = new Wine("232", "Bordeaux-Margaux-Chateau-Durfort-Vivens-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine LEOVILLE_POYFERRE = new Wine("426", "Bordeaux-Saint-Julien-Chateau-Leoville-Poyferre-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine COS_ESTOURNEL = new Wine("170", "Bordeaux-Saint-Estephe-Cos-d'Estournel-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine RAUZAN_SEGLA = new Wine("591", "Bordeaux-Margaux-Chateau-Rauzan-Segla-(Rausan-Segla)-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine MONTROSE = new Wine("494", "Bordeaux-Saint-Estephe-Chateau-Montrose-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine GRUAUD_LAROSE = new Wine("316", "Bordeaux-Saint-Julien-Chateau-Gruaud-Larose-2eme-Grand-Cru-Classe-Rouge");
	public final static Wine ISSAN = new Wine("366", "Bordeaux-Margaux-Chateau-d'-Issan-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine GISCOURS = new Wine("282", "Bordeaux-Margaux-Chateau-Giscours-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine KIRWAN = new Wine("374", "Bordeaux-Margaux-Chateau-Kirwan-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine LAGRANGE = new Wine("392", "Bordeaux-Saint-Julien-Chateau-Lagrange-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine CALON_SEGUR = new Wine("97", "Bordeaux-Saint-Estephe-Chateau-Calon-Segur-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine LA_LAGUNE = new Wine("393", "Bordeaux-Haut-Medoc-Chateau-La-Lagune-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine PALMER = new Wine("517", "Bordeaux-Margaux-Chateau-Palmer-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine BRANAIRE_DUCRU = new Wine("77", "Bordeaux-Saint-Julien-Chateau-Branaire-Ducru-4eme-Grand-Cru-Classe-Rouge");
	public final static Wine BEYCHEVELLE = new Wine("58", "Bordeaux-Saint-Julien-Chateau-Beychevelle-4eme-Grand-Cru-Classe-Rouge");
	public final static Wine PONTET_CANET = new Wine("567", "Bordeaux-Pauillac-Chateau-Pontet-Canet-5eme-Grand-Cru-Classe-Rouge");
	public final static Wine GRAND_PUY_DUCASSE = new Wine("305", "Bordeaux-Pauillac-Chateau-Grand-Puy-Ducasse-5eme-Cru-Classe-Rouge");
	public final static Wine GRAND_PUY_LACOSTE = new Wine("306", "Bordeaux-Pauillac-Chateau-Grand-Puy-Lacoste-5eme-Cru-Classe-Rouge");
	public final static Wine BELGRAVE = new Wine("44", "Bordeaux-Haut-Medoc-Chateau-Belgrave-5eme-Grand-Cru-Classe-Rouge");
	public final static Wine LYNCH_BAGES = new Wine("449", "Bordeaux-Pauillac-Chateau-Lynch-Bages-5eme-Grand-Cru-Classe-Rouge");
	public final static Wine CANTEMERLE = new Wine("107", "Bordeaux-Haut-Medoc-Chateau-Cantemerle-5eme-Grand-Cru-Classe-Rouge");
	public final static Wine YQUEM = new Wine("738", "Bordeaux-Sauternes-Chateau-d'-Yquem-1er-Cru-Classe-Superieur-Blanc-Liquoreux");
	public final static Wine CLIMENS = new Wine("145", "Bordeaux-Barsac-Chateau-Climens-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine COUTET = new Wine("183", "Bordeaux-Barsac-Chateau-Coutet-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine GUIRAUD = new Wine("320", "Bordeaux-Sauternes-Chateau-Guiraud-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine LAFAURIE_PEYRAGUET = new Wine("383", "Bordeaux-Sauternes-Chateau-Lafaurie-Peyraguey-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine TOUR_BLANCHE = new Wine("679", "Bordeaux-Sauternes-Chateau-la-Tour-Blanche-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine RABAUD_PROMIS = new Wine("584", "Bordeaux-Sauternes-Chateau-Rabaud-Promis-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine RAYNE_VIGNEAU = new Wine("594", "Bordeaux-Sauternes-Chateau-de-Rayne-Vigneau-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine RIEUSSEC = new Wine("602", "Bordeaux-Sauternes-Chateau-Rieussec-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine SIGALAS_RABAUD = new Wine("652", "Bordeaux-Sauternes-Chateau-Sigalas-Rabaud-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine SUDUIRAUT = new Wine("662", "Bordeaux-Sauternes-Chateau-Suduiraut-1er-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine HAUT_BAGES_LIBERAL = new Wine("325", "Bordeaux-Pauillac-Chateau-Haut-Bages-Liberal-5eme-Grand-Cru-Classe-Rouge");
	public final static Wine HAUT_BAILLY = new Wine("327", "Bordeaux-Pessac-Leognan-Chateau-Haut-Bailly-Cru-Classe-de-Graves-Rouge");
	public final static Wine MALARTIC_LAGRAVIERE = new Wine("459", "Bordeaux-Pessac-Leognan-Chateau-Malartic-Lagraviere-Cru-Classe-de-Graves-Rouge");
	public final static Wine PAVIE_MACQUIN = new Wine("529", "Bordeaux-Saint-Emilion-Chateau-Pavie-Macquin-1er-Grand-Cru-Classe-B-Rouge");
	public final static Wine PAVILLON_ROUGE = new Wine("531", "Bordeaux-Margaux-Pavillon-Rouge-du-Chateau-Second-Vin-");
	public final static Wine POUJEAUX = new Wine("574", "Bordeaux-Moulis-Chateau-Poujeaux-Cru-Bourgeois-Exceptionnel-Rouge");
	public final static Wine CANTENAC_BROWN = new Wine("109", "Bordeaux-Margaux-Chateau-Cantenac-Brown-3eme-Grand-Cru-Classe-Rouge");
	public final static Wine CHASSE_SPLEEN = new Wine("132", "Bordeaux-Moulis-Chateau-Chasse-Spleen-Cru-Bourgeois-Exceptionnel-Rouge");
	public final static Wine CHEVALIER_ROUGE = new Wine("219", "Bordeaux-Pessac-Leognan-Domaine-de-Chevalier-Cru-Classe-Graves-Rouge");
	public final static Wine CHEVALIER_BLANC = new Wine("218", "Bordeaux-Pessac-Leognan-Domaine-de-Chevalier-Cru-Classe-Graves-Blanc");
	public final static Wine DE_FARGUES = new Wine("238", "Bordeaux-Sauternes-Chateau-de-Fargues-Blanc-Liquoreux");
	public final static Wine DOISY_DAENE = new Wine("215", "Bordeaux-Barsac-Chateau-Doisy-Daene-2eme-Grand-Cru-Classe-Blanc-Liquoreux");
	public final static Wine DE_FIEUZAL = new Wine("247", "Bordeaux-Pessac-Leognan-Chateau-de-Fieuzal-Cru-Classe-Graves-Rouge");
	public final static Wine FERRIERE = new Wine("244", "Bordeaux-Margaux-Chateau-Ferriere-3eme-Grand-Cru-Classe-Rouge");
//	public final static Wine GILETTE = new Wine("281", "");
	public final static Wine GLORIA = new Wine("284", "Bordeaux-Saint-Julien-Chateau-Gloria-Rouge");
	public final static Wine LA_FLEUR_PETRUS = new Wine("256", "Bordeaux-Pomerol-Chateau-la-Fleur-Petrus-Rouge");
	public final static Wine LARCIS_DUCASSE = new Wine("408", "Bordeaux-Saint-Emilion-Chateau-Larcis-Ducasse-1er-Grand-Cru-Classe-B-Rouge");
	public final static Wine MARQUIS_DU_TERME = new Wine("468", "Bordeaux-Margaux-Chateau-Marquis-de-Terme-4eme-Grand-Cru-Classe-Rouge");
	public final static Wine PETIT_VILLAGE = new Wine("540", "Bordeaux-Pomerol-Chateau-Petit-Village-Rouge");
	public final static Wine PRIEURE_LICHINE = new Wine("577", "Bordeaux-Margaux-Chateau-Prieure-Lichine-4eme-Grand-Cru-Classe-Rouge");
	public final static Wine SAINT_PIERRE = new Wine("636", "Bordeaux-Saint-Julien-Chateau-Pierre-4eme-Grand-Cru-Classe-Rouge");
	public final static Wine SMITH_HAUT_LAFITTE = new Wine("657", "Bordeaux-Pessac-Leognan-Chateau-Smith-Haut-Lafitte-Cru-Classe-de-Graves-Rouge");
	public final static Wine SOUTARD = new Wine("660", "Bordeaux-Saint-Emilion-Chateau-Soutard-Grand-Cru-Classe-Rouge");
	public final static Wine LA_TOUR_CARNET = new Wine("680", "Bordeaux-Haut-Medoc-Chateau-La-Tour-Carnet-4eme-Grand-Cru-Classe-Rouge");
	public final static Wine TOUR_HAUT_BRION = new Wine("691", "Bordeaux-Pessac-Leognan-Chateau-La-Tour-Haut-Brion-Cru-Classe-de-Graves-Rouge");
	public final static Wine TROTANOY = new Wine("708", "Bordeaux-Pomerol-Chateau-Trotanoy-Rouge");
	public final static Wine GAZIN = new Wine("279", "Bordeaux-Pomerol-Chateau-Gazin-Rouge");
}
