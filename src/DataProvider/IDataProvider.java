package DataProvider;

import java.io.IOException;

public interface IDataProvider {
    public String acquireData(String address) throws IOException;
}