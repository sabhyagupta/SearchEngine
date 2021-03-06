package extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import netscape.ldap.util.GetOpt;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.xml.sax.SAXException;

public class Extraction {

	@SuppressWarnings("unchecked")
	public void extract(String c) throws IOException, SAXException,
			TikaException {

		// String cf = "D:/crawler3-app/target/"+c;
		// InputStream in = new ByteArrayInputStream(cf.getBytes("UTF-8"));

		final InputStream in = new FileInputStream("D:/Prats/" + c);

		try {
			for (@SuppressWarnings("rawtypes")
			Iterator it = new ObjectMapper().readValues(
					new JsonFactory().createJsonParser(in), Map.class); it
					.hasNext();) {

				LinkedHashMap<String, String> keyValue = (LinkedHashMap<String, String>) it
						.next();
				try {
					BodyContentHandler handler = new BodyContentHandler();
					Metadata metadata = new Metadata();
					System.out.println(keyValue.get("storage"));
					FileInputStream inputstream = new FileInputStream(
							keyValue.get("storage"));
					ParseContext pcontext = new ParseContext();
					AutoDetectParser msofficeparser = new AutoDetectParser();
					msofficeparser.parse(inputstream, handler, metadata,
							pcontext);

					JSONObject obj = new JSONObject();
					obj.put("path", keyValue.get("storage"));
					obj.put("url", keyValue.get("URL"));
					for (int i = 0; i < metadata.names().length; i++) {
						String name = metadata.names()[i];
						obj.put(name, metadata.get(name));
					}
					File f2 = new File("D:/Prats/Extract.json");
					BufferedWriter file2 = new BufferedWriter(new FileWriter(
							f2, true));
					try {

						ObjectMapper mapper = new ObjectMapper();
						file2.write(mapper.writerWithDefaultPrettyPrinter()
								.writeValueAsString(obj));
						System.out.println(mapper
								.writerWithDefaultPrettyPrinter()
								.writeValueAsString(obj));
						file2.newLine();
						file2.newLine();
						file2.newLine();
						file2.newLine();

					} catch (IOException e) {
						e.printStackTrace();

					} finally {
						file2.flush();
						file2.close();
					}

				} catch (FileNotFoundException e) {

				} catch (RuntimeException e) {
					System.out.println(e);
				}
			}
		} finally {
			in.close();
		}
	}

	public static void main(String[] args) throws IOException, SAXException,
			TikaException {

		File f = new File("Extract.json");
		String controlfile = "Crawl.json";
		if (f.exists()) {
			f.delete();
		}
		//GetOpt options = new GetOpt( "c:H", args );
		// Get the arguments specified for each option.
		// String controlfile = options.getOptionParam( 'c' );
	
		 
		 try{
		 new Extraction().extract(controlfile);
		 }
		 catch(Exception e){
			
		 }
		// InputStream in = new
		// FileInputStream("D:/crawler3-app/target/"+controlfile);

	}

}
