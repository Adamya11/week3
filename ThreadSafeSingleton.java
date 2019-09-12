import java.util.*;
public class ThreadSafeSingleton
{
    private static ThreadSafeSingleton instance;
    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance()
    {
        if(instance == null)
        {
            instance = new ThreadSafeSingleton();
            System.out.println("implementation works fine and provides thread-safety but it reduces the performance because of the cost associated with the synchronized method");
            
        }
        return instance;
    }

    public static ThreadSafeSingleton getInstanceUsingDoubleLocking()
    {
        if(instance == null)
        {
            synchronized (ThreadSafeSingleton.class)
            {
                if(instance == null)
                {
                    instance = new ThreadSafeSingleton();
                    System.out.println(" the synchronized block is used inside the if condition with an additional check to ensure that only one instance of a singleton class is created.");
                    
                }
            }
        }
        return instance;
    }
    public static void main(String args[])
    {
        ThreadSafeSingleton obj = new ThreadSafeSingleton();
        obj.getInstance();
        obj.getInstanceUsingDoubleLocking();
    }
}