import desktop.week3;
import java.util.*;
import java.lang.reflect.Constructor;

public class ReflectionSingletonTest {

    public static void main(String[] args) throws ClassNotFoundException
    {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                //Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
        System.out.println(" hashCode of both the instances is not same that destroys the singleton pattern. Reflection is very powerful and used in a lot of frameworks like Spring and Hibernate");
    }

}