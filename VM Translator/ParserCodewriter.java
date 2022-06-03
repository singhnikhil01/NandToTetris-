//This module will read and write the files.
import java.io.*;
import java.util.Scanner;
public class ParserCodewriter
{
     static String name = "generate.asm";
    //This method will read the file
    public  void Read(String filename) throws FileNotFoundException
    {
        System.out.println(filename);
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
               String line = (scan.nextLine());
               Functions.check(line,filename);
                
             } 
            };
            scan.close();
           }
     //Creating a new asm file and saving everything in the file 
     public void save(String data)
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
     } catch (IOException e) {
       e.printStackTrace();
     }
   } 
 }
 public  void delete()
 {
   File file = new File(name);
   file.delete();
 }
}
