import java.io.FileNotFoundException;

public class Main 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
       String filename = "C:\\Users\\Singh Sansar\\OneDrive\\Desktop\\Singh\\NandToTetris-\\VM Translator\\PointerTest.vm";
       ParserCodewriter par = new ParserCodewriter();
       par.delete();
       par.Read(filename);
        
    }
    
}
