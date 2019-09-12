import java.util.*;
public class BillPughSingleton
{
    private BillPughSingleton(){}
    private static class SingletonHelper
    {
        private static BillPughSingleton instance = new BillPughSingleton();
    }
    public static BillPughSingleton getInstance()
    {
        System.out.println("When the singleton class is loaded, SingletonHelper class is not loaded into memory and only when someone calls the getInstance method, this class gets loaded and creates the Singleton class instance.");
        return SingletonHelper.instance;
    }
    public static void main(String args[])
    {
        BillPughSingleton obj = new BillPughSingleton();
        obj.getInstance();
    }
}