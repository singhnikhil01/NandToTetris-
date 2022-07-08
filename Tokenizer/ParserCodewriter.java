//This module will read and write the files.
//package Tokenizer;
import java.io.*;
import java.util.Scanner;
public class ParserCodewriter
{

  static String name="generate.XML";
 
     
    //This method will read the file
    public  void Read(String filename) throws FileNotFoundException
    {
        //System.out.println(filename);
        //String preocessed_line = " ";
        FileInputStream fis = new FileInputStream(filename);
        Scanner scan = new Scanner(fis);
        save("<tokens>");;
            while(scan.hasNextLine())
            { 
              if(scan.hasNext("//")   || scan.hasNext("///") || scan.hasNext("/*"))
              {
              
                 scan.nextLine();
              }
              else 
              {
                String line = (scan.nextLine());
                if((!line.contains("/*") && !line.contains("/**"))&& !line.contains("**\\"))
                {
                  Tokenizer.distiguish(line);
                }
             } 
            };
            save("</tokens>");
            scan.close();
          }
     public static void save(String data)
   { 
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
     } catch (IOException e)
      {
       e.printStackTrace();
     }
   } 
 }
 //write a function to delete generate.xml file if it exists
  public static void delete()
  {
    File asmfile = new File (name);
    if(asmfile.exists())
    {
      asmfile.delete();
    }
  }
}
