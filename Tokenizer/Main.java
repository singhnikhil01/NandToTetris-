//package Tokenizer;
import java.io.FileNotFoundException;

public class Main 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
       //System.out.println("main");
       ParserCodewriter.delete();
       String filename = "Main.jack";
       ParserCodewriter par = new ParserCodewriter();
       par.Read(filename);
        
    }
    
}
