//package Tokenizer;
import java.io.FileNotFoundException;

public class Main 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
       //System.out.println("main");

       String filename = "Square.jack";
       ParserCodewriter par = new ParserCodewriter();
       par.Read(filename);
        
    }
    
}
