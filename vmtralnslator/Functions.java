public class Functions 
{
    public static void check(String line,String name) 
    {
        ParserCodewriter pr = new ParserCodewriter();
        pr.save("//"+line);
        
        if(line.equals("add") || line.equals("sub") ||line.equals("neg"))
            ArithmeticLogical.Arithmetic_op(line);
        else if( line.equals("eq") || line.equals("gt") ||line.equals("lt"))
            ArithmeticLogical.Relational_op(line);
        else if( line.equals("and") || line.equals("or") ||line.equals("not"))
            ArithmeticLogical.Logical_op(line);

        else
         {
            
            String []x = line.split(" ");
            String y = x[1];
            switch(y)
        {
            case"constant": 
            Segments.push_constant(line);
            break;
            case "static":
            Segments.change_static(line,name);
            break;
            case "pointer":
            Segments.pointer(line);
            break;
            default:
            Segments.change_local(line);
            break;
            
        }
    }
}
     
        } 

        

    

