import java.io.FileNotFoundException;

public class Main 
 {
     static String name;
     public static void main(String[] args) throws FileNotFoundException 
     {
       String filename ="Add.asm";
       Hashing.scan(filename);
       ReadWrite.Read(filename);
       //System.out.println("Hello world");
     }
    
} 

