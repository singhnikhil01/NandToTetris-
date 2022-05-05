import java.util.Arrays;
import java.util.List;

//it will change address to binary
public class Binaryout {
    public static void binary (String data)
    {
        int a = Integer.parseInt(data);
        String x = Integer.toBinaryString(a);
         int len = 16;
         int y = x.length();
       
         
         while(y < len)
         {
             x = "0"+ x;
             y++;
         }
         //System.out.print(x);
         ReadWrite.save(x);
         
    }

    public static void binary(int a)
    {
        String x = Integer.toBinaryString(a);
         int len = 16;
         int y = x.length();
       
         
         while(y < len)
         {
             x = "0"+ x;
             y++;
         }
         //System.out.print(x);
         ReadWrite.save(x);
        
    }

    public static void getbinary(String com , String dst , String jmp )
    {
        String[] op2 = new String[] {"0","1","-1","D","A","!D","!A",
        "D+1","A+1","D-1","A-1","D+A","D-A","A-D","D&A","D|A"};
        String [] op3 = new String[] {"M", "!M","M+1","M-1","D+M","D-M","M-D","D&M","D|M"};
        int a=0;
        String dest="000";
        String jump="000";
        String comp="0000000";
        List<String> list1 = Arrays.asList(op2);
        List<String> list2 = Arrays.asList(op3);
        if(list1.contains(com))
        {
            a = 0;    
            comp= Comp.Cvalue(com);
        }
        else if (list2.contains(com))
        {
            a = 1;   
            comp= Comp.Cvalue(com); 
        }
        dest = Comp.Dvalue(dst);
        jump = Comp.Jvalue(jmp);
        

        if (comp!= null & dest !=null & jump !=null)
        {
            String data = "111"+ a + comp +dest + jump; 
            ReadWrite.save(data);

        }
        else if(comp !=null & jump !=null)
        {
            dest ="000";
            String data = "111"+ a + comp +dest + jump; 
            ReadWrite.save(data);
        } 
        else if(comp !=null & dest !=null)
        {
            jump ="000";
            String data = "111"+ a + comp +dest + jump; 
            ReadWrite.save(data);
        } 
        else if(jump !=null & dest !=null)
        {
            comp ="0000000";
            String data = "111"+ a + comp +dest + jump; 
            ReadWrite.save(data);
        }
    }
}