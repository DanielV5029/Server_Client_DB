
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParseBooks {

	boolean inBooks = false;
	boolean inBook = false;
	boolean inId = false;
	boolean inAuthor = false;
	boolean inTitle = false;
	boolean inYear = false;

	Book currentBook;
	List<Book> currentBookList;

	public List<Book> doParseBooks(String s) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentBookList;
	}

	public void processDocument(XmlPullParser pullParser) throws XmlPullParserException, IOException {
		int eventType = pullParser.getEventType();
		do {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("Start document");
			} else if (eventType == XmlPullParser.END_DOCUMENT) {
				System.out.println("End document");
			} else if (eventType == XmlPullParser.START_TAG) {
				processStartElement(pullParser);
			} else if (eventType == XmlPullParser.END_TAG) {
				processEndElement(pullParser);
			} else if (eventType == XmlPullParser.TEXT) {
				processText(pullParser);
			}
			eventType = pullParser.next();
		} while (eventType != XmlPullParser.END_DOCUMENT);
	}
	
	public void processStartElement(XmlPullParser event)
	{
		String name = event.getName();
		if(name.equals("books"))
		{
			inBooks = true;
			currentBookList = new ArrayList<Book>();
		}
		else if(name.equals("book")) {
			inBook = true;
			currentBook = new Book();
		}else if(name.equals("id")) {
			inId = true;
		}else if(name.equals("author")) {
			inAuthor = true;
		}else if(name.equals("title")) {
			inTitle = true;
		}else if(name.equals("year")) {
			inYear = true;
		}
	}

	public void processText(XmlPullParser event)throws XmlPullParserException{
		if(inId) {
			String s = event.getText();
			currentBook.setId(Integer.parseInt(s));
		}
		if(inAuthor) {
			String s = event.getText();
			currentBook.setAuthor(s);
		}
		if(inTitle) {
			String s = event.getText();
			currentBook.setTitle(s);
		}
		if(inYear) {
			String s = event.getText();
			currentBook.setYear(Integer.parseInt(s));
		}
	}
	
	public void processEndElement(XmlPullParser event) {
		String name = event.getName();
		
		if(name.equals("books")) {
			inBooks = false;
		}else if(name.equals("book")) {
			inBook = false;
			currentBookList.add(currentBook);
			currentBook = null;
		}else if(name.equals("id")) {
			inId = false;
		}else if(name.equals("author")) {
			inAuthor = false;
		}else if(name.equals("title")) {
			inTitle = false;
		}else if(name.equals("year")) {
			inYear = false;
		}
	}
}
