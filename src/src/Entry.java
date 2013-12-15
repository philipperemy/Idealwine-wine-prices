package src;

import java.util.Date;

public class Entry
{

    @Override
    public String toString()
    {
        return "Entry [" + (name != null ? "name=" + name + ", " : "") + (millesime != null ? "millesime=" + millesime + ", " : "") + (content != null ? "content=" + content + ", " : "") + (quote != null ? "quote=" + quote + ", " : "")
            + (timestamp != null ? "timestamp=" + timestamp : "") + "]";
    }

    public String toCsv()
    {
        return name + ";" + millesime + ";" + content + ";" + quote + ";" + timestamp;
    }

    public String name;
    public String millesime;
    public String content;
    public String quote;
    public Date   timestamp;

    public String getKey()
    {
        return name + "@" + millesime + "@" + content;
    }

    public Entry()
    {

    }

    public Entry(Entry entry)
    {
        this.name = entry.name;
        this.millesime = entry.millesime;
        this.content = entry.content;
        this.quote = entry.quote;
        this.timestamp = new Date(entry.timestamp.getTime());
    }
}
