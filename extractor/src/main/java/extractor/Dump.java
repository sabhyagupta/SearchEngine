package extractor;

import java.io.File;

import netscape.ldap.util.GetOpt;

public class Dump {
	public static void main(String args[]) {

		File f = new File("Extract.json");
		String controlfile = "Crawl.json";
		if (f.exists()) {
			f.delete();
		}
		GetOpt options = new GetOpt("c:H", args);
		// Get the arguments specified for each option.
		// String controlfile = options.getOptionParam( 'c' );

		try {
			new Extraction().extract(controlfile);
		} catch (Exception e) {

		}

	}

}
