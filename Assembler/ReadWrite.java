import java.io.*;
import java.util.Scanner;
public class ReadWrite
{
    public String data;

    //This method will read the file
    public static void Read(String filename) throws FileNotFoundException
    {
        FileInputStream fis = new FileInputStream(filename);
        Scanner scan = new Scanner(fis);
            while(scan.hasNextLine())
            { 
              
              if(scan.hasNext("//"))
              {
                 scan.nextLine();
              }
              else 
              {
               String line = (scan.next());
               //System.out.println(line+i);
               //Send to check whether the line is variable , Symbols , labels or computation
               Check.check(line);
             } 
            };
            scan.close();
           }
     //Creating a new asm file and saving everything in the file 
  public static void save(String data)
  { 
    File asmfile = new File ("generate.hack");
    if(asmfile.exists())
      {
        try {
        FileWriter fr = new FileWriter("generate.hack",true);
         PrintWriter pw = new PrintWriter(fr);
         pw.println();
         pw.print(data);
         pw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
  else
  {
    try {
      if(asmfile.createNewFile());
    } catch (IOException e) {
    
      e.printStackTrace();
     }
     try {
      FileWriter fr = new FileWriter("generate.hack");
      PrintWriter pw = new PrintWriter(fr);
      pw.print(data);
      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  } 
}
public static void delete()
{
  File file = new File("generate.hack");
  file.delete();
}
}
