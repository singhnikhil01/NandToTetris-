import java.util.Arrays;
import java.util.List;
import java.util.Stack;
public class Tokenizer 
{
    static char z = '"';
    static String k = ""+z; 
    static String string = "";
    static boolean con = true ;
    static Stack<String> stack = new Stack<String>();
    static  List<Character> list = Arrays.asList('{','}',',','(',')','[',']','.','=',';','+','-','*','/','&','|','>','<','~');
   static List<String> key = Arrays.asList("class","constructor","method" ,"Program", "components","int", 
   "boolean", "char", "void", "var", "static", "let", "do", "if", "else", "while", "return" ,"true", "false", "null", 
   "this","field","function");
   static List<String> identifier  = Arrays.asList("new");
                                                                                 
    public static void distiguish(String line)
    { 
      if(line.contains("//"))
        line = line.split("//")[0];
        String [] arr = line.split("[\\s]");
        for(int i=0 ; i<arr.length;i++) 
        {
          
           if(arr[i].contains(k)&&!con)
            {
              System.out.println(arr[i]);
              string = string+arr[i];
              String abc = string.substring(1, string.length()-2);
              ParserCodewriter.save("<stringConstant>"+ abc +"</stringConstant>");
              ParserCodewriter.save("<symbol>"+string.charAt(string.length()-1)+"</symbol>"); 
              con = true;
              
            }
           else if(arr[i].startsWith(k))
          {
            System.out.println(arr[i]);
            string = string +arr[i]+" ";
            con = false;
            
            }
            else if(con)
            converter(arr[i]);
        }
          
    }
    public static void converter(String line)
    {
      String word = "";
      for(int i=0 ; i<line.length();i++)
      {

        char x = line.charAt(i);
        String y = word+x ;
       
        if(!list.contains(x))
        word = word+x ;

        if(i==line.length()-1 || list.contains(x))
        {
          checkword(word);
          word = "";
        }

        if(list.contains(x))
        ParserCodewriter.save("<symbol>"+line.charAt(i)+"</symbol>"); 
        
            
        }
       
      }
    

    public static void checkword(String data)
    {
          if(data.matches("[0-9]+"))
          {ParserCodewriter.save("<integerConstant>"+ data+"</integerConstant>");
          return;}
          if(key.contains(data))
          ParserCodewriter.save("<keyword>"+ data +"</keyword>");
          else if(!key.contains(data)) // (data.startsWith("_") || data.matches("\\b([A-Z]\\w*)\\b") || identifier.contains(data) )
        {
          if(data !="")
          ParserCodewriter.save("<identifier>"+ data +"</identifier>");
        }
    }
   
  }
    

