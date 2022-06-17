public class ArithmeticLogical 
{
    //static int num = 0;
    public static void Arithmetic_op(String line){
        StringBuilder sb = new StringBuilder();
        ParserCodewriter par = new ParserCodewriter();
        if(line.equals("neg"))
        {
           
            sb.append("@SP\n");
            sb.append("M=M-1\n");
            sb.append("A=M\n");
            sb.append("D=M\n");
            sb.append("@SP\n");     
            sb.append("A=M\n");
            sb.append("M=-D\n");
        }
        else
        {
        sb.append("@SP\n");
        sb.append("AM=M-1\n");
        sb.append("D=M\n");
        sb.append("@SP\n");
        sb.append("AM=M-1\n");
        
        if(line.equals("add"))
            sb.append("M=M+D\n");
        else //for sub
        sb.append("M=M-D\n");
        }
        sb.append("@SP\n");
        sb.append("M=M+1");
        par.save(sb.toString());
    }  



    public static void Relational_op(String line)
    {
        int num = 0;
        //"@SP","A=M", "A=A-1","D=M","A=A-1","D=M-D","@SP", "M=M-1", "M=M-1","@w"+"."+ew, "D;JEQ" ,  "@SP", "A=M", "M=0", "@END"+"."+ew, "0;JMP","(w"+"."+ew+")","@SP","A=M","M=-1","(END"+"."+ew+")","@SP","M=M+1"          
        StringBuilder sb = new StringBuilder();
        ParserCodewriter par = new ParserCodewriter();
        String Start_label =  line+"_"+num;
        String End_label ="END_"+num;
        num = num+1;
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("A=A-1\n");
        sb.append("D=M\n");
        sb.append("A=A-1\n");
        sb.append("D=M-D\n");
        sb.append("@SP\n");
        sb.append("M=M-1\n");
        sb.append("M=M-1\n");
        sb.append("@"+Start_label+"\n"); 

        if(line.equals("gt"))
             sb.append("D;JEQ\n");
        else if(line.equals("lt"))
            sb.append("D;JLT\n"); 
        else if(line.equals("eq"))
            sb.append("D;JGT\n");  

         sb.append("@SP\n");
         sb.append("A=M\n");
         sb.append("M=0\n");
         sb.append("@"+End_label+"\n");
         sb.append("0;JMP\n");
         sb.append("("+Start_label+")\n");
         sb.append("@SP\n");
         sb.append("A=M\n");
         sb.append("M=-1\n");
         sb.append("("+End_label+")\n"); //think of label over here 
         sb.append("@SP\n");
         sb.append("M=M+1");

         par.save(sb.toString());
    }

    public static void Logical_op(String line)
    {
        StringBuilder sb = new StringBuilder();
        ParserCodewriter par = new ParserCodewriter();
        if(line.equals("not"))
        {
            sb.append("@SP\n");
            sb.append("M=M-1\n");
            sb.append("A=M\n");
            sb.append("D=M\n");
            sb.append("@SP\n");
            sb.append("A=M\n");
            sb.append("M=!D\n");
        }
        else
        {
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("A=A-1\n");
        sb.append("D=M\n");
        sb.append("A=A-1\n");

        if(line.equals("and"))
            sb.append("D=D&M\n");
        else if(line.equals("or"))
            sb.append("D=D|M\n"); 
        
        sb.append("@SP\n");
        sb.append("M=M-1\n");
        sb.append("M=M-1\n");
        sb.append("A=M\n");
        sb.append("M=D\n"); 
        }

        sb.append("@SP\n");
        sb.append("M=M+1");

        par.save(sb.toString());

    }
    
}
