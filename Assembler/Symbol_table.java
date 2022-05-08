import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Symbol_table 
{
    private static String key;
    static HashMap<String,String> map = new HashMap<String,String>();
  
    public static List symbol()
    {
        List<String> list = new ArrayList<String>();
        list.add(key);
        return list;

    }
    public static void Map(String key , String value)
    {
        
       
        if(!(map.containsKey(key)))
        {
          //System.out.println(key+"="+value);
          map.put(key, value);
        }

    }
    public static int getvalue(String key)
    {
        String  x = map.get(key);
        int y = Integer.parseInt(x);

        return y;

    }
    public static Boolean intable(String key)
    {
        return map.containsKey(key);
    }
    
}
