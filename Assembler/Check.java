import java.io.FileNotFoundException;

//this java program will seperate the Labels , symbols and variables and send to respective part for binary conversion 
//This file will seperate Symbols , labels and variables  

public class  Check {

    public static void check(String line) throws FileNotFoundException
    {
           String dest="";
           String comp="";
           String jump="";
          // String binaryvalue="";
          
       if(line.startsWith("@") )
       {
        
         line =line.split("@")[1];
        //System.out.println(line);
        //this is a digit
         if(Character.isDigit(line.charAt(0)))
         {
          //System.out.println("int");
          Binaryout.binary(line); 
         }
         //this is for symbols , characters 
         else if(!Character.isDigit(line.charAt(0)))
          {
            //System.out.println("sym");
            int value = Symbol_table.getvalue(line);
            Binaryout.binary(value);
            
          }
           //
          }
          
       
       else if(line.matches("[A-Z]+=[A-Z!+&-|]+;[A-Z]{3}") )
       {
         System.out.print(line);
          dest = line.split("=")[0];
          String x = line.split("=")[1];
          comp = x.split(";")[0];
          jump = x.split(";")[1];
          Binaryout.getbinary(comp, dest, jump);
       }
       else if (line.matches("[A-Z]+=[A-Z!+&-|]+"))
       {
           dest = line.split("=")[0];
           comp = line.split("=")[1];
           Binaryout.getbinary(comp, dest, jump);

       }
       else if ( line.matches("[A-Z!+&-|]+;[A-Z]{3}"))
       {
          comp = line.split(";")[0];
          jump = line.split(";")[1];
          Binaryout.getbinary(comp, dest, jump);  
       
     }
    }
}

