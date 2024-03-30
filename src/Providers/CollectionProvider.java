package Providers;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import Currency.Currency;

public class CollectionProvider implements ICollectionProvider {

    private List<Currency> list;

    public CollectionProvider(){
        list = new ArrayList<>();
    }


    public String ToString(){
        String temp = "";
        for (Currency x : list){
            temp += "Code = " + x.getCode() + " | Factor = " + x.getFactor() + " | Rate = " + x.getRate() + "\n";
        }
        return temp;
    }


    public List<Currency> getCurrencyList()
    {
        return this.list;
    }


    public Currency getCurrencyByCode(Currency currency) {
        for (Currency x : list) {
            if (currency.equals(x)) {
                return x;
            }
        }
        return null;
    }





    @Override
    public void provider(String input, CollectionProvider output) throws IOException, SAXException, ParserConfigurationException {
        Document doc = inputToDocument(input);
        XMLToCurrencyList(doc, output);
    }


    private String getStringByName(Element element, String name) {
        return element.getElementsByTagName(name).item(0).getTextContent();
    }

    private Integer getIntByName(Element element, String name) {
        return Integer.parseInt(getStringByName(element, name).replace(",", "."));
    }

    private BigDecimal getBigDecimalByName(Element element, String name) {
        String stringValue = getStringByName(element, name).replace(",", ".");
        return new BigDecimal(stringValue);
    }

    private Currency elementToICurrency(Element element) {
        Currency currency = new Currency();
        String code = getStringByName(element, "kod_waluty");
        String name = getStringByName(element, "nazwa_waluty");
        Integer factor = getIntByName(element, "przelicznik");
        BigDecimal rate = getBigDecimalByName(element, "kurs_sredni"); // Użyj metody getBigDecimalByName tutaj
        currency.setCode(code);
        currency.setName(name);
        currency.setFactor(factor);
        currency.setRate(rate);
        return currency;
    }

    private void addZloty(CollectionProvider output) {
        Currency zloty = new Currency();
        zloty.setCode("PLN");
        zloty.setFactor(1);
        zloty.setRate(BigDecimal.valueOf(1));
        zloty.setName("Polski Złoty");

        output.getCurrencyList().add(zloty);
    }

    private void XMLToCurrencyList(Document doc, CollectionProvider output) {
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("pozycja");
        Node temp;

        addZloty(output);

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            temp = nodeList.item(i);
            if (temp.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) temp;
                Currency tempCurrency = this.elementToICurrency(eElement);
                output.getCurrencyList().add(tempCurrency);
            }
        }
    }

    private Document inputToDocument(String input) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringReader reader = new StringReader(input);
        InputSource inputSource = new InputSource(reader);
        Document document = builder.parse(inputSource);
        return document;
    }
}