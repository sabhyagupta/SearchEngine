package crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import netscape.ldap.util.GetOpt;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.ContentHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Crawl {

	int h = 1;
	int i = 1;

	@SuppressWarnings("unchecked")
	public void crawl(String Url, String depth) throws Exception {

		// opening url pool
		URL url = new URL(Url);
		URLConnection hpCon = url.openConnection();// creates url connection
		InputStream input = url.openStream(); // reads from connected url

		// creates new file dir
		File theDir = new File("D:\\Prats");
		if (!theDir.exists()) {
			try {
				theDir.mkdir();
			} catch (SecurityException se) {
			}
		}

		// Initialize tika dependency methods
		LinkContentHandler linkHandler = new LinkContentHandler();
		ContentHandler textHandler = new BodyContentHandler(); // creates connection to write html body
		ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
		TeeContentHandler teeHandler = new TeeContentHandler(linkHandler,
				textHandler, toHTMLHandler);
		Metadata metadata = new Metadata();
		ParseContext parseContext = new ParseContext(); //passes the html content to tika parser
		AutoDetectParser parser = new AutoDetectParser();

		// parse the incoming url
		parser.parse(input, teeHandler, metadata, parseContext);

		JSONObject obj = new JSONObject();
		JSONArray company = new JSONArray();

		// get all the links in a list
		List<Link> links = linkHandler.getLinks(); // returns the collected links

		ArrayList<String> linklist2 = new ArrayList<String>();
		Queue<String> myQueue = new LinkedList<String>();

		// put all the data in json object
		obj.put("Title", metadata.get("title"));
		obj.put("URL", url);
		obj.put("Content-Type", hpCon.getContentType());
		obj.put("Last entry date", new Date(hpCon.getLastModified()));

		@SuppressWarnings("unused")
		String dir = "D:\\Prats";
		try {
			String w = hpCon.getContentType(); // gets the content type of html
			String id = UUID.randomUUID().toString();

			if (w.contains("html")) {
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());//channel reads byte by byte
				@SuppressWarnings("resource")
				FileOutputStream fos = new FileOutputStream("D:/Prats/" + id
						+ ".html");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				String path = "D:/Prats/" + id + ".html";
				linklist2.add(path);
				i++;
				obj.put("storage", path);
			}

		} catch (NullPointerException e) {

		}

		for (Link link : links) {
			String anchor = link.getUri();
			if (!anchor.startsWith("htt")) {
				anchor = url + anchor;
			}

			JSONObject obj2 = new JSONObject();
			int dep = Integer.parseInt(depth);
			if (h < dep) {
				if (!myQueue.contains(anchor)) {
					myQueue.add(anchor);
				}
			}
			String name = link.getText();
			obj2.put("text", name);
			obj2.put("URL", anchor);
			company.add(obj2);
		}
		obj.put("Links", company);
		h++;

		for (String v : myQueue) {
			if (!v.startsWith("#") && !v.startsWith("/")
					|| v.startsWith("http") || v.startsWith("https"))
				try {

					crawl(v, depth);
				} catch (MalformedURLException malformedException) {

				} catch (FileNotFoundException e) {

				} catch (IOException ex) {
				}
		}

		File f = new File("D:/Prats/Crawl.json");

		BufferedWriter file = new BufferedWriter(new FileWriter(f, true));
		try {
			ObjectMapper mapper = new ObjectMapper();
			file.write(mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(obj));
			System.out.println(mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(obj));
			file.newLine();
			file.newLine();
			file.newLine();
			file.newLine();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			file.flush();
			file.close();
		}
	}
	public static void main(String[] args) throws Exception {

		// ----for command line----//
		@SuppressWarnings("unused")
		String v = args[0];
		@SuppressWarnings("unused")
		String d = args[1];
		GetOpt options = new GetOpt("u:d:H", args);
		// Get the arguments specified for each option.
		String hostname = options.getOptionParam('u');

		String dep = options.getOptionParam('d');

		new Crawl().crawl(hostname, dep);
	}
}