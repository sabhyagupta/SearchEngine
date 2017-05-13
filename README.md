# CS454_Search Engine Projects


###Part 1 :[Crawler]
* Crawls the given URL to the specified depth of the URL. 
* Creates a Json file, and write the crawled URL(path), Name of the link and Title of the page.



###Part 2 :[Extractor]
* Extracts the meta data from the path specified in Crawl.json and Stores all the Metadata in New Json file.
* Output Example :
* {
  "path" : "D:/Neil/c9ca726d-e76f-4c37-a1db-23f71e0adb72.html",
  "X-Parsed-By" : "org.apache.tika.parser.DefaultParser",
  "dc:title" : "Google",
  "Content-Encoding" : "UTF-8",
  "description" : "Search the world's information, including webpages, images, videos and more. Google has many special features to help you find exactly what you're looking for.",
  "robots" : "noodp",
  "title" : "Google",
  "url" : "https://www.google.com",
  "Content-Type" : "text/html; charset=UTF-8"
}

###Part 3 :[Indexing] 
* Use Extracted File OR can Extract Data from locally Stored web pages(Folders with in folders)
* Removes the Stop words like and,a,an etc etc stored in Stop_Words_List.txt
* Calculate the FREQUENCY(do indexing of the document) of the words in a document and store it JSON format.
* Calculate TF-IDF, RANK(weight) of the document.
* Output the highest Ranked page based on the Word/Query Search.     

###Part 4 :[Searching] 


