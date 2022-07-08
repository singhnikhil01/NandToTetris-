import java.util.Arrays;
import java.util.List;

public class Tokenizer 
{
   
  static  List<String> symbols = Arrays.asList("{","}",",","(",")","[","]",".","=",";","+","-","*","/","&","|",">","<","~");
   static List<String> key = Arrays.asList("class","constructor","method" ,"Program", "components","int", 
   "boolean", "char", "void", "var", "static", "let", "do", "if", "else", "while", "return" ,"true", "false", "null", 
   "this","field","function");
                                                                                 
    public static void distiguish(String line)
    {
      line = line.split("//")[0];
      System.out.println(line);
      int length = line.length();
      String word = "";
      //reading each letter form the line
      for (int i = 0; i < length; i++) {
        String s = Character.toString(line.charAt(i));
        //For String Constant
        if (s.equals("\"")) {
            String some = s;
            i++;
            s = Character.toString(line.charAt(i));
            while (!(s.equals("\""))) {
                s = Character.toString(line.charAt(i));
                i++;
                some = some + s;
            }
            i--;
            ParserCodewriter.save(CodeWriter(some));
        //To break word if symbol or space comes
        } else if (symbols.contains(s) || line.charAt(i) == ' ') {
            if (word != "")
            ParserCodewriter.save(CodeWriter(word));
            if (symbols.contains(s)) {
              ParserCodewriter.save(CodeWriter(s));
            }
            word = "";
        } else {
            word = word + s;
        }
    }

}

public static String token(String token) {

  // if it is a digit
  if (token.matches("[0-9]+")) {
      return "integerConstant";
  }
  // if it is a symbol
  else if (symbols.contains(token)) {
      return "symbol";
  }
  // if it is a string
  else if (token.matches("\"[^\"\n]*\"")) {
      return "stringConstant";
  }
  // if it is a keyword
  else if (key.contains(token)) {
      return "keyword";
  }
  // if it is an identifier
  else if (token.matches("[\\w_]+")) {
      return "identifier";
  }
  return null;
}

public static String CodeWriter(String word) {
  String s = Character.toString(word.charAt(0));
  String token = token(word);
//To remove " if word is String constant
  if (s.equals("\"")) {
    word = word.substring(1, word.length() - 1);
                                          }
     String out = "<" + token + "> " + word + " </" + token + ">";
    return out;
}
  
}

    

