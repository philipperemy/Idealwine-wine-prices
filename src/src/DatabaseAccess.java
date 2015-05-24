package src;


public class DatabaseAccess
{

    public synchronized void insertWineQuote(String name, String millesime, String content, String quote)
    {
        System.out.println(name + "', " + millesime + ", " + quote + ", " + content);
    }

}
