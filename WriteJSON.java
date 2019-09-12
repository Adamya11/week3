
import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class WriteJSON
{
    public static void main(String args[]) throws Exception
    {
        JSONObject inventoryDetails = new JSONObject();
        inventoryDetails.put("name","Gehu");
        inventoryDetails.put("weight","100kg");
        inventoryDetails.put("price per kg", "80");

        JSONObject inv = new JSONObject();
        inv.put("Pulses",inventoryDetails);
        
        JSONObject inventory2 = new JSONObject();
        inventory2.put("name", "Barle");
        inventory2.put("weight", "200kg");
        inventory2.put("price per kg", "50");

        JSONObject inv2 = new JSONObject();
        inv.put("Wheats", inventory2);

        JSONObject inventory3 = new JSONObject();
        inventory3.put("name", "Basmati");
        inventory3.put("weight", "88");
        inventory3.put("price per kg", "100");

        JSONObject inv3 = new JSONObject();
        inv.put("Rice", inventory3);


        JSONArray finalInventory = new JSONArray();
        finalInventory.add(inv);
        finalInventory.add(inv2);
        finalInventory.add(inv3);

        try (FileWriter file = new FileWriter("inventory.json")) 
        {
            file.write(finalInventory.toJSONString());
            file.flush();
        }
        catch  (IOException e) {
            e.printStackTrace();
        }
    }
}