import Currency.Currency;
import Providers.CollectionProvider;
import Providers.ICollectionProvider;

import java.util.InputMismatchException;
import java.util.Scanner;
public class ConsoleView {
    private Exchanger iExchanger;
    private CollectionProvider iDataCollection;
    private Scanner scanner = new Scanner(System.in);


    public void viewAll(CollectionProvider collection)
    {
        System.out.println(collection.ToString());
    }


    public void setDataCollection(CollectionProvider collection)
    {
        this.iDataCollection = collection;
    }


    public void setExchange(Exchanger exchanger)
    {
        this.iExchanger = exchanger;
    }


    public Currency stringToCurrency(String code){
        Currency obj = new Currency();
        obj.setCode(code);
        return iDataCollection.getCurrencyByCode(obj);
    }

    private String parseWithMessageString(String label){
        System.out.println(label);
        String temp;
        try{
            temp = scanner.next();
        }
        catch (Exception exception){
            System.err.print("Wprowadz poprawne dane!\n");
            temp = parseWithMessageString(label);
        }
        return temp.toUpperCase();
    }

    private double parseWithMessageDouble(String label){
        System.out.println(label);
        Double temp;
        try{
            temp = scanner.nextDouble();
            if (temp < 0){
                throw new Exception();
            }
        }
        catch (InputMismatchException exception){
            System.err.print("Wprowadz poprawne dane!\n");
            scanner = new Scanner(System.in);
            temp = parseWithMessageDouble(label);
        }
        catch (Exception exception){
            System.err.print("Ilosc pieniedzy moze byc tylko dodatnia!\n");
            temp = parseWithMessageDouble(label);
        }
        return temp;
    }


    public Currency chooseCurrency(String label)
    {
        return stringToCurrency(parseWithMessageString(label));
    }


    public void menu()
    {
        char option;
        while (true){
            System.out.println("1. Wymien");
            System.out.println("2. Wypisz dostepne waluty");
            System.out.println("0. EXIT");
            System.out.print("Wybierz:\n");
            option = scanner.next().charAt(0);
            switch (option){
                case '1':
                    exchange();
                    break;
                case '2':
                    viewAll(iDataCollection);
                    break;
                case '0':
                    System.out.println("Zamykanie");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Niepoprawna opcja");
            }
        }
    }


    public void exchange(){
        Currency sourceCurrency = chooseCurrency("Wpisz kod waluty wymienianej: \n");
        if (sourceCurrency == null){
            System.err.print("Waluta o takim kodzie nie istenie!\n");
            exchange();
        }
        else
        {

            Currency finalCurrency = chooseCurrency("Wpisz kod waluty na ktora chcesz wymienic: \n");
            if (finalCurrency == null){
                System.err.print("Waluta o takim kodzie nie istenie!\n");
                exchange();
            }
            else{
                double amount = parseWithMessageDouble("Wpisz ile pieniedzy chcesz wymienic: \n");
                System.out.println("Po wymianie masz: " + iExchanger.exchange(sourceCurrency, finalCurrency, amount) + " " + finalCurrency.getCode());
            }
        }
    }
}
