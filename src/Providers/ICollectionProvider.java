package Providers;


import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ICollectionProvider {
    public void provider(String input, CollectionProvider output) throws IOException, SAXException, ParserConfigurationException;
}