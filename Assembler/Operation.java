//it get the values of operation, destination and jump 
import java.util.HashMap;;
public class Comp
{
    public static String Cvalue(String a) 
    {
        String [] op1 = new String[]{"null=0000000","0=101010","1=111111","-1=111010","D=001100","A=110000","!D=001101","!A=110011",
        "D+1=011111","A+1=110111","D-1=001110","A-1=110010","D+A=000010","D-A=010011","A-D=000111","D&A=000000",
        "D|A=010101","M=110000", "!M=110011","M+1=110111","M-1=110010","D+M=000010","D-M=010011","M-D=000111",
        "D&M=000000","D|M=010101"};

        HashMap <String,String> cmap = new HashMap <String,String>();

        for (int i=0 ; i< op1.length;i++)
        {
            String x = op1[i].split("=")[0];
            String y = op1[i].split("=")[1];
            cmap.put(x, y);
        }

           return cmap.get(a);
        }

        public static String Dvalue( String a )
        {
            String[] dest = new String[]{"null=000","M=001","D=010","MD=011","A=100","AM=101","AD=110","AMD=111"};
            HashMap <String,String> Dmap = new HashMap <String,String>();
            for (int i=0 ; i< dest.length;i++)
        {
            String x = dest[i].split("=")[0];
            String y = dest[i].split("=")[1];
            Dmap.put(x, y);
        }
          return Dmap.get(a);
          

        }


        //Function for the jump Hashmap
        public static String Jvalue( String a )
        {
            String[] jmp = new String[]{"null=000","JGT=001","JEQ=010","JGE=011","JLT=100","JNE=101","JLE=110","JMP=111"};
            HashMap <String,String> Jmap = new HashMap <String,String>();
            for (int i=0 ; i< jmp.length;i++)
        {
            String x = jmp[i].split("=")[0];
            String y = jmp[i].split("=")[1];
            Jmap.put(x, y);
        }
          return Jmap.get(a);

        }

        };

