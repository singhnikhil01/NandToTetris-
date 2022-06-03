public class Functions 
{
    public static void check(String line,String name) 
    {
        if(line.equals("add") || line.equals("sub"))
        {
            //ParserCodewriter pr = new ParserCodewriter();
            //pr.save(line);
            //System.out.println(line);
            add_sub(line);

        }

        else
         {
            //ParserCodewriter pr = new ParserCodewriter();
            //pr.save(line);
            String []x = line.split(" ");
            String y = x[1];
            switch(y)
        {
            case"constant": 
            push_constant(line);
            break;
            case "static":
            change_static(line,name);
            break;
            case "pointer":
            pointer(line);
            break;
            default:
            change_local(line);
            break;
            
        }
    }
}
    

        
            public static void push_constant(String line)
    {
                String []x = line.split(" ");
                StringBuilder sb = new StringBuilder();
                ParserCodewriter par = new ParserCodewriter();
                sb.append("@");
                sb.append(x[2]);
                sb.append("\nD=A\n");
                sb.append("@SP\n");
                sb.append("A=M\n");
                sb.append("M=D\n");
                sb.append("@SP\n");
                sb.append("M=M+1");
                par.save(sb.toString());
    }

    public static void change_static(String line,String fileName)
    {
        String[]x = line.split(" ");
        StringBuilder sb = new StringBuilder();
        String[] file = fileName.split("\\.(?=[^\\.]+$)");
        ParserCodewriter par = new ParserCodewriter();
        if(line.equals("push")){
            sb.append("@"+file[0]+"."+x[2]+"\n");
            sb.append("D=M\n");
            sb.append("@SP\n");
            sb.append("A=M\n");
            sb.append("M=D\n");
            sb.append("@SP\n");
            sb.append("M=M+1\n");
        }
        if(line.contains("pop")){
            sb.append("@SP\n");
            sb.append("AM=M-1\n");
            sb.append("D=M\n");
            sb.append("@"+file[0]+"."+x[2]+"\n");
            sb.append("M=D\n");
        }
        par.save(sb.toString());
    }
    

    //change the pointer to Assembly code 
        public static void pointer(String line){
        String []x = line.split(" "); 
        StringBuilder sb = new StringBuilder();
        ParserCodewriter par = new ParserCodewriter();
        if(x[2].equals("0")){
            if(line.contains("pop")){
                sb.append("@SP"+"\n");
                sb.append("AM=M-1"+"\n");
                sb.append("D=M"+"\n");
                sb.append("@THIS"+"\n");
                sb.append("M=D");
            }
            if(line.contains("push")){
                sb.append("@THIS\n");
                sb.append("D=M\n");
                sb.append("@SP\n");
                sb.append("A=M\n");
                sb.append("M=D\n");
                sb.append("@SP\n");
                sb.append("M=M+1");

            }
            

        }
        if(x[2].equals("1"))
        {
            if(line.contains("pop")){
                sb.append("@SP"+"\n");
                sb.append("AM=M-1"+"\n");
                sb.append("D=M"+"\n");
                sb.append("@THAT"+"\n");
                sb.append("M=D");
            }

            if(line.contains("push"))
            {
                sb.append("@THAT\n");
                sb.append("D=M\n");
                sb.append("@SP\n");
                sb.append("A=M\n");
                sb.append("M=D\n");
                sb.append("@SP\n");
                sb.append("M=M+1");

            }

        }
        par.save(sb.toString());
        
    }
             
        
        public static void change_local(String line) // for the common memory segments 
        {
                String []x = line.split(" ");
                ParserCodewriter par = new ParserCodewriter();
                StringBuilder sb = new StringBuilder();
                String t = "@"+x[2];
                String u = null;
                
                sb.append(t);
                //sb.append();
                sb.append("\nD=A\n");
                if(x[1].equals("local"))
             {
                 
                sb.append("@LCL\n");
                sb.append("D=D+M\n");
            }
                if(x[1].equals("argument"))
            {
                
                sb.append("@ARG\n");
                sb.append("D=D+M\n");
            }
                if(x[1].equals("this"))
            {
                sb.append("@THIS\n");
                sb.append("D=D+M\n");
            }
                if(x[1].equals("that"))
            {
               
                sb.append("@THAT\n");
                sb.append("D=D+M\n");
            }
                if(x[1].equals("temp"))
            {
                sb.append("@5\n");
                sb.append("D=D+A\n");
            }
            if(line.contains("push")){
                sb.append("A=D\n");
                sb.append("D=M\n");
                sb.append("@SP\n");
                sb.append("A=M\n");
                sb.append("M=D\n");
                sb.append("@SP\n");
                sb.append("M=M+1");
    
            }
    
            else if(line.contains("pop")){
                sb.append("@R15");
                sb.append("\nM=D\n");
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("@R15");
                sb.append("\nA=M\n");
                sb.append("M=D");
            }
    
    
            par.save(sb.toString());
            
        }  
        public static void add_sub(String line){
            
            StringBuilder sb = new StringBuilder();
            ParserCodewriter par = new ParserCodewriter();
            sb.append("@SP\n");
            sb.append("AM=M-1\n");
            sb.append("D=M\n");
            sb.append("@SP\n");
            sb.append("AM=M-1\n");
            if(line.equals("add")){
                sb.append("M=M+D\n");
            }
            else{
                sb.append("M=M-D\n");
            }
            sb.append("@SP\n");
            sb.append("M=M+1");
            par.save(sb.toString());
        }   

        }

    

