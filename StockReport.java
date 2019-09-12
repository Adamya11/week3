import java.util.*;
import java.io.*;

class StockReport{
public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of stocks");
    int n = sc.nextInt();
    StockPortfolio ob = new StockPortfolio(n);
    for(int i=0;i<n;i++)
    {
        System.out.println("Enter Stock Name, Number of Shares, and Share Price");
        String stockName = System.console().readLine();
        int numberOfShares = sc.nextInt();
        int sharePrice = sc.nextInt();
        ob.add(stockName, numberOfShares, sharePrice, i);
    }
    ob.printReport(n);
    sc.close();
}    
}
class Stock{
String stockName;
int numberOfShares;
int sharePrice;
Stock(String stockName,int numberOfShares,int sharePrice)
{
    this.stockName = stockName;
    this.numberOfShares = numberOfShares;
    this.sharePrice = sharePrice;
}
int calculateValue(Stock stock)
{
    return (stock.numberOfShares)*(stock.sharePrice);
}
}

class StockPortfolio{
Stock []portfolio;
StockPortfolio(int n)
    {
        this.portfolio = new Stock[n];
    }
void add(String stockName,int numberOfShares,int sharePrice,int i)
    {
        portfolio[i] = new Stock(stockName, numberOfShares, sharePrice);
    }
void printReport(int n)
{   
    int totalValue = 0;
    int i = 0;
    System.out.println("Stock Report:");
    while(i<n)
    {
        int stockValue = portfolio[i].calculateValue(portfolio[i]);
        System.out.println("The value of " + portfolio[i].stockName + " is " + stockValue);
        totalValue += stockValue;
        i++;
    }
    System.out.println("The total value of the stocks is " + totalValue);
}
}