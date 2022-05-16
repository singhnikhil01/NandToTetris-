public class Check {
    public static void check(String line)
    {
        if(line.contains("push"))
        {
            String[] arr =  line.split(" ");
            System.out.println(arr[1].length());
            if(arr[1].equals("constant")) 
            {
                String data ="@"+arr[2]+"\n"+"D=M";
                System.out.println(data);

            }
           //System.out.println(arr[0]); 
           //System.out.println(arr[1]); 
           //System.out.println(arr[2]); 


        }
        else if(line.contains("pop"))
        {
            //System.out.println("----------------pop-----------------");
            //System.out.println(line);
        }
    }
    
}
