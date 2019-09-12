import java.util.*;
public class LazyInitialization
{
    private static LazyInitialization instance;
    private LazyInitialization(){}

    public static LazyInitialization getInstance()
    {
        if(instance == null)
        {
            instance = new LazyInitialization();
            System.out.println(" works fine in case of the single-threaded environment but when it comes to multithreaded systems, it can cause issues if multiple threads are inside the if condition at the same time. It will destroy the singleton pattern and both threads will get the different instances of the singleton class.");
        }
        return instance;
    }
     public static void main(String args[])
        {
            LazyInitialization obj = new LazyInitialization();
            obj.getInstance();
        }
    }
    