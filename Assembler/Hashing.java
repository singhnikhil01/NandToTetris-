import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Hashing{
    static String [] symbol = new String[] {"R0=0","R1=1","R2=2","R3=3","R4=4","R5=5","R6=6","R7=7","R8=8",
        "R9=9","R10=10","R11=11","R12=12","R13=13","R14=14","R15=15","SCREEN=16384","KBD=24576","SP=0","LCL=1","ARG=2","THIS=3",
        "THAT=4"};
        static int j =16;
        
public static void savesymbol()
    {
        
        
            String x ;
            String y;
    
            for (String i: symbol )
            {
                x = i.split("=")[0];
                y = i.split("=")[1];
               Symbol_table.Map(x, y);
                
            }
        }
    
   public static void scan(String filename) throws FileNotFoundException 
    {   
         savesymbol();
        FileInputStream file = new FileInputStream(filename);
        try (Scanner scan = new Scanner(file)) {
            while(scan.hasNextLine())
            {
                if(scan.hasNext("//"))
                {
                    scan.nextLine();
                }
                else
                {
                    String line = scan.nextLine();
                    //System.out.println(line);
                    if (line.contains("@")) { // A-instruction
                        String x = line.trim();
                        line = x.split("@")[1]; 
                        //System.out.println(line);
                        find(line,filename);
                    }
                } 

            }
            }
            }
            //System.out.println();
        
               


     //Check if already a symbol or varialble 
     public static  void find(String line,String filename) throws FileNotFoundException
     {
        List<String> list = Arrays.asList(symbol);
         if(!(list.contains(line) & Character.isDigit(line.charAt(0))))
         {
            label(line,filename);

         }
      

     }

     //check if label and if label put value 
     public static void label(String data,String filename) throws FileNotFoundException 
    {
         int x =  -1;
        FileInputStream file = new FileInputStream(filename);//here we should enter the file name
        try (Scanner scan = new Scanner(file)) {
            
            while(scan.hasNextLine())
            { 
              if(scan.hasNext("//"))
              {
                 scan.nextLine();
              }
              else 
              {
                String line = scan.nextLine();
                if(!(line.contains("(")))
                {
                    x++;
                }
                else if(line.startsWith("(") & line.endsWith(")") )
                {
                 Matcher m = Pattern.compile("\\((.*?)\\)").matcher(line);
                 while (m.find()) {
                      String label = m.group(1);
                      String y = Integer.toString(x);
                      Symbol_table.Map(label, y);
                      break;
                      //System.out.println(label+"="+y);
                 }
                
            }
           
            };

    }
}
                 if (!Character.isDigit(data.charAt(0)) & !Symbol_table.intable(data) )
                    {
                        String k = Integer.toString(j);
                        Symbol_table.Map(data,k );
                        j++;
           
                    }
                   
                }

    }






          


