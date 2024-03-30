import DataProvider.IDataProvider;
import org.xml.sax.SAXException;
import Providers.ICollectionProvider;
import Providers.CollectionProvider;
import DataProvider.DataProvider;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main
{
    static Exchanger exchanger;
    static ConsoleView view;
    static IDataProvider provider;
    static CollectionProvider lasta;
    static ICollectionProvider xmlProv;

    public static void main(String[] args)
    {
        xmlProv = new CollectionProvider();
        lasta = new CollectionProvider();
        view = new ConsoleView();
        provider = new DataProvider();
        exchanger = Exchanger.getInstance();


        try
        {
            String result = provider.acquireData("https://www.nbp.pl/kursy/xml/lasta.xml");
            xmlProv.provider(result, lasta);
            System.out.println(lasta.ToString());
            view.setDataCollection(lasta);
            view.setExchange(exchanger);

            view.menu();

        } catch (IOException exception)
        {
            exception.printStackTrace();
        } catch (SAXException exception)
        {
            exception.printStackTrace();
        } catch (ParserConfigurationException exception)
        {
            exception.printStackTrace();
        }
    }
}