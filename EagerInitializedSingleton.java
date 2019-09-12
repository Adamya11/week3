package desktop.week3;
public class EagerInitializedSingleton
{
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();
    private EagerInitializedSingleton(){}

    public static EagerInitializedSingleton getInstance()
    {
        System.out.println("In eager initialization, the instance of Singleton Class is created at the time of class loading, this is the easiest method to create a singleton class but it has a drawback that instance is created even though client application might not be using it.");
        System.out.println("We should avoid the instantiation until unless client calls the getInstance method. Also, this method doesn’t provide any options for exception handling.");
        return instance;
    }
    public static void main(String args[])
    {
        EagerInitializedSingleton obj = new EagerInitializedSingleton();
        obj.getInstance();
    }
}