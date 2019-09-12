import java.io.*;
import java.util.*;
public class SerializedSingleton implements Serializable
{
    private static final long serialVersionUID = -7604766932017737115L;
    private SerializedSingleton(){}
    private static class SingletonHelper{
        private static final SerializedSingleton instance = new SerializedSingleton();
    }
    
    public static SerializedSingleton getInstance(){
        return SingletonHelper.instance;
    }
    protected Object readResolve() {
    System.out.println("After this, you will notice that hashCode of both the instances is same in the test program.");
    return getInstance();}

    public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException
    {
       try{ SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        ObjectOutput obj = new ObjectOutputStream(new FileOutputStream("ss.txt"));
        obj.writeObject(instanceOne);
        obj.close();
        ObjectInput obi = new ObjectInputStream(new FileInputStream("ss2.txt"));
        SerializedSingleton instanceTwo = (SerializedSingleton) obi.readObject();
        obi.close();   
    }
       catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("instanceOne hashCode=" +instanceOne.hashCode());
        System.out.println("instanceTwo hashCode=" +instanceTwo.hashCode());
       
    }
}