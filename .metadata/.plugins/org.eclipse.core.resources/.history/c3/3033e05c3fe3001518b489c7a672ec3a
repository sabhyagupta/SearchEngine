package crawler;

import netscape.ldap.util.GetOpt;



public class Main {

	public static void main(String[] args) throws Exception {
		
		//----for command line----//
		String v = args[0];
	    String d = args[1];
	      GetOpt options = new GetOpt( "u:d:H", args );
	       //Get the arguments specified for each option.
	       String hostname = options.getOptionParam( 'u' );
	      
	       String dep = options.getOptionParam( 'd' );
	       
	       new Crawl().crawl(hostname,dep);
	
	       
	       
		//new Crawl().crawl("http://www.google.com/","2");
		
		
	
}
}