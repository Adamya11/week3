import org.json.simple.JSONArray;  
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import java.io.*;
import java.util.*;

class StockAccount{
CustomerShares customer;
CompanyShares companyShares;

StockAccount(String filename,boolean newCust)throws Exception
{
    this.companyShares = new CompanyShares();
    String companyFile = "/home/admin1/Desktop/week3/stockss.json";
    this.companyShares = companyShares.read(companyFile);
    this.customer = new CustomerShares();
    if(!newCust)
    customer = customer.read(filename);
}

double valueOf(){
    try
    {
        int totalValue = 0;
        Node current = customer.head;
        while(current.next != null)
        {
            totalValue += current.sharePrice * current.numberOfShares;
            current = current.next;
        }
        totalValue += current.sharePrice * current.numberOfShares;
        return totalValue;
    }
    catch (NullPointerException e )
    {
        return 0;
    }
}

void buy(int amount,String symbol){
    if(companyShares.search(symbol,amount))
    {
        companyShares.update(symbol,amount);
        if(this.customer.search(symbol))
        {
            this.customer.update(symbol,amount);           
        }
        else 
        {
            int p = companyShares.priceShare(symbol);
            this.customer.add(symbol,p,amount);
        }
    }
}

void sell(int amount,String symbol){
    if(this.customer.search(symbol,amount))
    {
        this.customer.update(symbol,-amount);
        companyShares.update(symbol,-amount);
    }
    else System.out.println("Customer does not have enough shares");
}

void save(String filename)throws Exception{
    this.customer.write(filename);
    String companyFile = "/home/admin1/Desktop/week3/transaction.json";
    this.companyShares.write(companyFile);
}

void printReport(){
    System.out.println("\nReport");
    this.customer.print();
    System.out.println("");
}

public static void main(String args[])throws Exception{
    String customerFile = "/home/admin1/Desktop/week3/customerRecord.json";
    Scanner sc = new Scanner(System.in);
    System.out.println("Press n for New customer or o for Old customer?");
    char ch = sc.next().charAt(0);
    boolean newcust;
    if(ch == 'n')
        newcust = true;
    else newcust =false;
    StockAccount ob = new StockAccount(customerFile, newcust);    
    while(true)
    {   
        System.out.println("Press 1 for buying");
        System.out.println("Press 2 for selling");
        System.out.println("Press 3 for printing final report");
        System.out.println("Press 4 for saving in file");
        System.out.println("Press e to exit");
        char action = sc.next().charAt(0);
        if (action == 'e')
        break;
        switch (action) {
            case '1':
                System.out.println("Enter the Stock and its number of shares you want to buy");
                String stockBuy = System.console().readLine();
                int nBuy = sc.nextInt();
                ob.buy(nBuy, stockBuy);
                break;
            case '2':
                System.out.println("Enter the Stock and its number of shares you want to sell");
                String stockSell = System.console().readLine();
                int nSell = sc.nextInt();
                ob.sell(nSell,stockSell);
                break;
            case '3':
                ob.printReport();
                break;
            case '4':
                ob.save(customerFile);
                break;
        }
    }   
    sc.close();
}
}

class Node{
    String symbol;
    int sharePrice;
    int numberOfShares;
    Node next;
    Node(String d,int p,int n)
    {
        symbol = d;
        sharePrice = p;
        numberOfShares = n;
        next = null;
    }
}

class Shares{
Node head;
    
public void add(String d,int p,int n){
    Node temp = new Node(d,p,n);
    if(head==null)
    {
        head = temp;
    }
    else 
    {
        Node current = head;
        while(current.next != null)
        {
            current = current.next;
        }
        current.next = temp;
    }
}

public boolean search(String s,int n){
    Node current = head;
    while(current.next != null)
    {
        if((current.symbol).equals(s) && current.numberOfShares>n)
        {
            return true; 
        }
        current = current.next;
    }
    if(current.symbol.equals(s) && current.numberOfShares>n)
    return true;
    else return false;
}
    
public void delete(String del){
    Node current = head;
    Node previous = head;
    if(current.symbol.equals(del))
    {
        head = current.next;
        previous.next = null;
        previous.symbol = null;
    }    
    else 
    {
        current = current.next;
        while(!(current.symbol.equals(del)))
        {
            current = current.next;
            previous = previous.next;
        }
        previous.next = current.next;
        current.next = null;
        current.symbol = null;
    }
}

public void print(){
    Node current = head;
    while(current.next != null)
    {
        System.out.println(current.symbol + "\n" + "Price: " + current.sharePrice + "   Number of shares: " + current.numberOfShares);
        current = current.next;
    }
    System.out.println(current.symbol + "\n" + "Price: " + current.sharePrice + "   Number of shares: " + current.numberOfShares);
}

public void write(String filename) throws Exception{
    FileWriter file = new FileWriter(filename);
    JSONArray allShares = new JSONArray();
    Node current = head;
    while(current.next != null)
    {
        JSONObject shareDetails = new JSONObject();
        shareDetails.put("stockName",current.symbol);
        shareDetails.put("sharePrice",current.sharePrice);
        shareDetails.put("totalShare",current.numberOfShares);
        allShares.add(shareDetails);
        current = current.next;
    }
    JSONObject shareDetails = new JSONObject();
    shareDetails.put("stockName",current.symbol);
    shareDetails.put("sharePrice",current.sharePrice);
    shareDetails.put("totalShare",current.numberOfShares);
    allShares.add(shareDetails);
    file.write(allShares.toJSONString());
    file.flush();
    file.close();
}
}

class CompanyShares extends Shares{
public void update(String s,int n){
    Node current = head;
    while(current.next != null)
    {
        if((current.symbol).equals(s))
        {
            //current.sharePrice = p;
            current.numberOfShares -= n; 
        }
        current = current.next;
    }
    if(current.symbol.equals(s))
    {
        //current.sharePrice = p;
        current.numberOfShares -= n; 
    }
}

public int priceShare(String s){
    Node current = head;
    while(current.next != null)
    {
        if((current.symbol).equals(s))
        {
            return current.sharePrice; 
        }
        current = current.next;
    }
    if(current.symbol.equals(s))
    return current.sharePrice;
    else return 0;
}

public CompanyShares read(String filename) throws Exception {
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader(filename));
    JSONArray arr = (JSONArray) obj;
    CompanyShares list = new CompanyShares();
    Iterator itr = arr.iterator();
    while(itr.hasNext())
    {
        JSONObject ob = (JSONObject) itr.next();
        String name = (String) ob.get("stockName");
        long price1 = (long) ob.get("sharePrice");
        long shares1 = (long) ob.get("totalShare");
        int price = (int) price1;
        int shares = (int) shares1;
        list.add(name,price,shares);
    }
    return list;
}
}

class CustomerShares extends Shares{
public boolean search(String s) {
    try
    {
        Node current = head;
        while(current.next != null)
        {
            if((current.symbol).equals(s) )
            {
                return true; 
            }
            current = current.next;
        }
        if(current.symbol.equals(s))
        return true;
        else return false;
    }
    catch (NullPointerException e )
    {
        return false;
    }
}

public void update(String s,int n){
    Node current = head;
    while(current.next != null)
    {
        if((current.symbol).equals(s))
        {
            //current.sharePrice = p;
            current.numberOfShares += n; 
        }
        current = current.next;
    }
    if(current.symbol.equals(s))
    {
        //current.sharePrice = p;
        current.numberOfShares += n; 
    }
}

public CustomerShares read(String filename) throws Exception {
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader(filename));
    JSONArray arr = (JSONArray) obj;
    CustomerShares list = new CustomerShares();
    Iterator itr = arr.iterator();
    while(itr.hasNext())
    {
        JSONObject ob = (JSONObject) itr.next();
        String name = (String) ob.get("stockName");
        long price1 = (long) ob.get("sharePrice");
        long shares1 = (long) ob.get("totalShare");
        int price = (int) price1;
        int shares = (int) shares1;
        list.add(name,price,shares);
    }
    return list;
}
}