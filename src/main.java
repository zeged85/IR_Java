import java.io.IOException;

public class main {

    public static void main(String args[])  
    {  
        System.out.print("first statement. ");  
        System.out.print("second statement. ");  
        System.out.print("third statement");
        System.out.print("4 statement");
        System.out.print("5 statement");
        System.out.print("6 statement");


        //READ FILE  to String
        readfile reader = new readfile();
        String singleFile = null;
        try {
        	singleFile = reader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        //System.out.print(singleFile);
        

			try {
				reader.parseXML(singleFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
        
        
        
        
        
    }
    
    
	
}
