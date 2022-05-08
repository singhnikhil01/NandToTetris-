import java.io.*;
import java.util.Scanner;
public class ReadWrite
{
  static String name = "generate.hack";
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
               //System.out.println(line);
               Check.check(line);
             } 
            };
            scan.close();
           }
     //Creating a new asm file and saving everything in the file 
  public static void save(String data)
  { 
    //System.out.println(data);
    
    File asmfile = new File (name);
    if(asmfile.exists())
      {
        try {
        FileWriter fr = new FileWriter(name,true);
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
      FileWriter fr = new FileWriter(name);
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
  File file = new File(name);
  file.delete();
}
}
