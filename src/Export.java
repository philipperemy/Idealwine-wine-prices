
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Export
{
    private DatabaseAccess databaseAccess;

    public Export(DatabaseAccess databaseAccess)
    {
        this.databaseAccess = databaseAccess;
    }

    public void toCsv(String filename) throws IOException
    {
        toCsv(filename, false, true);
    }

    public void toCsv(String filename, boolean lastEntries, boolean orderByWineNameAndMillesime) throws IOException
    {
        List<Entry> entries = null;

        if (lastEntries)
        {
            //entries = databaseAccess.getMostRecentEntries();
        }
        else
        {
            //entries = databaseAccess.getAllEntries();
        }

        if (orderByWineNameAndMillesime)
        {
            entries = order(entries);
        }

        writeToCsv(filename, entries);
    }

    private List<Entry> order(List<Entry> _entries)
    {
        List<Entry> orderedEntries = new ArrayList<>();
        Map<String, List<Entry>> entriesByNameMap = new TreeMap<>();

        for (Entry _entry : _entries)
        {
            String key = _entry.name;
            if (entriesByNameMap.get(key) == null)
            {
                entriesByNameMap.put(key, new ArrayList<Entry>());
            }

            entriesByNameMap.get(key).add(_entry);
        }

        // Wine name fixed
        for (String wineName : entriesByNameMap.keySet())
        {
            List<Entry> _entriesByName = entriesByNameMap.get(wineName);
            Map<String, List<Entry>> entriesByMillesimeMap = new TreeMap<String, List<Entry>>();

            for (Entry _entryByName : _entriesByName)
            {
                String key = _entryByName.millesime;
                if (entriesByMillesimeMap.get(key) == null)
                {
                    entriesByMillesimeMap.put(key, new ArrayList<Entry>());
                }

                entriesByMillesimeMap.get(key).add(_entryByName);
            }

            // Wine name fixed and millesime fixed
            for (String millesime : entriesByMillesimeMap.keySet())
            {
                List<Entry> _entriesByMillesime = entriesByMillesimeMap.get(millesime);
                Entry[] _entriesByMillesimeSortedArray = new Entry[_entriesByMillesime.size()];
                for (int k = 0; k < _entriesByMillesime.size(); k++)
                {
                    _entriesByMillesimeSortedArray[k] = _entriesByMillesime.get(k);
                }

                int min_index = -1;
                for (int i = 0; i < _entriesByMillesimeSortedArray.length; i++)
                {
                    min_index = i;
                    Date minDate = _entriesByMillesimeSortedArray[i].timestamp;
                    for (int j = i + 1; j < _entriesByMillesimeSortedArray.length; j++)
                    {
                        Date currentDate = _entriesByMillesimeSortedArray[j].timestamp;
                        if (currentDate.before(minDate))
                        {
                            minDate = currentDate;
                            min_index = j;
                        }
                    }

                    if (min_index != i)
                    {
                        // Exchange i and min_index
                        Entry entryMinIndex = new Entry(_entriesByMillesimeSortedArray[min_index]);
                        Entry entryI = _entriesByMillesimeSortedArray[i];
                        _entriesByMillesimeSortedArray[min_index] = entryI;
                        _entriesByMillesimeSortedArray[i] = entryMinIndex;
                    }
                }

                for (Entry entry : _entriesByMillesimeSortedArray)
                {
                    orderedEntries.add(entry);
                }
            }
        }

        return orderedEntries;
    }

    private void writeToCsv(String filename, List<Entry> entries) throws IOException, FileNotFoundException
    {
        File file = new File(filename);

        if (file.exists())
        {
            file.delete();
        }

        // File should not exist at this time
        file.createNewFile();

        PrintWriter pw = new PrintWriter(file);
        for (Entry entry : entries)
        {
            pw.println(entry.toCsv());
        }
        pw.close();
    }
}
