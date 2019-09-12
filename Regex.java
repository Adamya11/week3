import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class Regex
{
    public static String readin() throws Exception
    {
        FileReader fr = new FileReader("/home/admin1/Desktop/week3/readit.txt");
        StringBuilder sb = new StringBuilder();
        int i;
        while((i=fr.read())!= -1)
        {
            sb.append((char)i);
        }
        String s2 = sb.toString();
        return s2;
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name:");
        String name = sc.nextLine();
        System.out.println("Enter full name:");
        String fname = sc.nextLine();
        System.out.println("Enter mobile number");
        String num = sc.nextLine();
        Regex ob = new Regex();
        String str = ob.readin();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);

        Pattern p = Pattern.compile("<<name>>");
        Pattern p1 = Pattern.compile("<<full name>>");
        Pattern p2 = Pattern.compile("91-xxxxxxxxxx");
        Pattern p3 = Pattern.compile("../../....");
        Matcher m = p.matcher(str);
        str = m.replaceFirst(name);
        Matcher m1 = p1.matcher(str);
        str = m1.replaceFirst(fname);
        Matcher m2 = p2.matcher(str);
        str = m2.replaceFirst(num);
        Matcher m3 = p3.matcher(str);
        str = m3.replaceFirst(strDate);
        System.out.println(str);
        
    }
}