import java.util.*;
public class StaticBlockSingleton
{
    private static StaticBlockSingleton instance;
    private StaticBlockSingleton(){}

    static{
        try{
            instance = new StaticBlockSingleton();
        }
        catch (Exception e)
        {
            throw new RuntimeException ("Exception occured at Runtime.");
        }
    }
    public static StaticBlockSingleton getInstance()
    {
        System.out.println("Static block initialization implementation is similar to eager initialization, except that instance of class is created in the static block that provides option for exception handling.");
        return instance;
    }
    public static void main(String args[])
    {
        StaticBlockSingleton obj = new StaticBlockSingleton();
        obj.getInstance();
    }
}