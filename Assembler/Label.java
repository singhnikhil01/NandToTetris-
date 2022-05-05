import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Labe{

  public static List label() throws FileNotFoundException
  {   
        ArrayList<String> arr = new ArrayList<String>();
         File filename = new File("Max.asm");
         try (Scanner scan = new Scanner(filename)) {
          //String y = "("+ x +")"; //checking for the label
           while(scan.hasNextLine())
           { 
            String line = (scan.next());
             if(scan.hasNext("//"))
             {
                scan.nextLine();
             }
             else if(line.startsWith("(") & line.endsWith(")") )
             {
              Matcher m = Pattern.compile("\\((.*?)\\)").matcher(line);
              while (m.find()) {
                   String label = m.group(1);
                   //System.out.println(label);
                   arr.add(label);
              }
              
              }
          }
        }
    
       return arr;

      }
      public static int index(String x ) throws FileNotFoundException
      {   
        int i = 0;
            HashMap<String, Integer> hash = new HashMap<String,Integer>() ; 
             File filename = new File("Max.asm");
             try (Scanner scan = new Scanner(filename)) {
              //String y = "("+ x +")"; //checking for the label
              while(scan.hasNextLine())
              { 
                if(scan.hasNext("//"))
                {
                   scan.nextLine();
                }
                 
                 else {
                   i++;
                   String line = (scan.next());
                   if(line.startsWith("(") & line.endsWith(")") )
                   { i--;
                   Matcher m = Pattern.compile("\\((.*?)\\)").matcher(line);
                   while (m.find()) {
                     String lable =  m.group(1);
                     hash.put(lable,i);
                  }
              }
                  } 
              };
            }
            return hash.get(x);
            
  }
}