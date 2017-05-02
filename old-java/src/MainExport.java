
import java.io.IOException;

public class MainExport
{
    public static void main(String[] args) throws IOException
    {
        DatabaseAccess dao = new DatabaseAccess();
        Export export = new Export(dao);
        export.toCsv("test-csv.csv");
    }
}
