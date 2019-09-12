import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSON
{
    public static void main(String args[]) throws Exception
    {
        JSONParser jp = new JSONParser();
        try (FileReader reader = new FileReader("inventory.json"))
        {
            Object obj = jp.parse(reader);
            JSONArray finalInventory = (JSONArray) obj;
            System.out.println(finalInventory);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}