package src;

public class Iterator
{

    private int size;
    private int current = 0;

    public Iterator(int size)
    {
        this.size = size;
    }

    public synchronized int getNext()
    {
        if (current >= size)
        {
            current = 0;
        }
        return current++;
    }

}
