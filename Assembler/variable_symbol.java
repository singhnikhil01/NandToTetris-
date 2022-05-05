//to check if the line is variable symbol or label 
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class variable_symbol
 {
     public static void find(String x) throws FileNotFoundException
     {
         int j = 16;
         String [] str = new String[] {"R0","R1","R2","R3","R4","R5","R6","R7","R8",
        "R9","R10","R11","R12","R13","R14","R15","SCREEN","KBD","sp","LCL","ARG","THIS",
        "THAT"}; 
        List<String> list = Arrays.asList(str);
        if(Labe.label().contains(x))
        {
            //System.out.println(x);
            //System.out.println(Labe.index(x));
           Binaryout.binary(Labe.index(x));
        
        }
         else if (list.contains(x))
         {
             int temp = getvalue(x);
             Binaryout.binary(temp); 

         }
         else if(!Character.isDigit(x.charAt(0)))
        { 
            Binaryout.binary(j);
            j++;

           }
           else if(Character.isDigit(x.charAt(0)))
           { 
               Binaryout.binary(x); 
   
              }
            

     }
     public static Integer getvalue(String data)
     {
             String [] symbol = new String[] {"R0=0","R1=1","R2=2","R3=3","R4=4","R5=5","R6=6","R7=7","R8=8",
             "R9=9","R10=10","R11=11","R12=12","R13=13","R14=14","R15=15","SCREEN=16384","KBD=24576","SP=0","LCL=1","ARG=2","THIS=3",
             "THAT=4","LOOP=4","STOP=18","END=22"};
             String x ;
             String y;
     
             HashMap<String,Integer> map = new HashMap<String,Integer>();
             for (int i=0;i < symbol.length;i++) 
             {
                 x = symbol[i].split("=")[0];
                 y = symbol[i].split("=")[1];

                 map.put(x, (Integer. parseInt(y)));
             }
             return map.get(data);
         }
 
    
}
