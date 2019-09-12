import java.io.*;
import java.util.*;

public class TestFactory
{
    public static void main(String args[])
    {
        ComputerFactory pc = new ComputerFactory();
        ComputerFactory server = new ComputerFactory();
        System.out.println(pc.getComputer("pc","2 GB ","500 GB ","2.4 GHz "));
        System.out.println(server.getComputer("server","16 GB ","1 TB ","2.9 GHz "));
    }
}
public abstract class Computer
{
    public abstract String getRAM();
    public abstract String getHDD();
    public abstract String getCPU();
    public String toString()
    {
        return "ram ="+this.getRAM() +"HDD ="+this.getHDD() +"CPU ="+this.getCPU();        
    }
}

public class PC extends Computer
{
    private String ram;
    private String hdd;
    private String cpu;

    public PC (String ram, String hdd, String cpu)
    {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }
    public String getRAM()
    {
        return this.ram;
    }
    public String getHDD()
    {
        return this.hdd;
    }
    public String getCPU()
    {
        return this.cpu;
    }
} 
public class Server extends Computer
{
    private String ram;
    private String hdd;
    private String cpu;

    public Server (String ram, String hdd, String cpu)
    {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }
    public String getRAM()
    {
        return this.ram;
    }
    public String getHDD()
    {
        return this.hdd;
    }
    public String getCPU()
    {
        return this.cpu;
    }
} 
public class ComputerFactory
{
    public static Computer getComputer(String type,String ram, String hdd, String cpu)
    {
        if("PC".equalsIgnoreCase(type))
        return new PC(ram,hdd,cpu);
        else if ("Server".equalsIgnoreCase(type))
        return new Server(ram,hdd,cpu);

        return null;
    }
}
