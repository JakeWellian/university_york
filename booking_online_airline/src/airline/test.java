package airline;

public class test {

	public static void main(String[] args) { 
	      String s1=new String ("Sky");
	      String s2=new String ("Earth"); 
	      String s3=new String ("Sea");
	      s1=s2=s3;
	      System.out.println(s2);        // first word 
	


	      s3=s2;
	      System.out.println(s2);        // second word 
	


	      s2=s3=s2;
	      System.out.println(s3);  
	
}
}
