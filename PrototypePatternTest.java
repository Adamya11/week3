import java.util.*;
import java.io.*;
public class PrototypePatternTest
{
    public static void main(String args[]) throws CloneNotSupportedException
    {
        Employees emps = new Employees();
        emps.loadData();
        Employees emps2 = (Employees)emps.clone();
        Employees emps3 = (Employees)emps.clone();
        List<String> l1 =  emps2.getEmplist();
        List<String> l2 = emps3.getEmplist();
        l1.add("Pizi");
        l1.add("Ruben");
        l2.remove("Silva");
        System.out.println("Original List: " +emps.getEmplist());
        System.out.println("Cloned List1: "+l1);
        System.out.println("Cloned List2: "+l2);
    }
}
public class Employees
{
    private List<String> emplist;
    public Employees()
    {
        emplist = new ArrayList<String>();
     
    }
    public Employees(List<String> list)
    {
        this.emplist = list;
    }
    public void loadData()
    {
        emplist.add("Silva");
        emplist.add("felix");
        emplist.add("gonzalo");
        emplist.add("bruno");
    }
    public List<String> getEmplist()
    {
        return emplist;
    }
    public Object clone()  throws CloneNotSupportedException
    {
        List<String> temp = new ArrayList<String>();
        for(String s : this.getEmplist())
        {temp.add(s);}
        return new Employees(temp);
    }
}