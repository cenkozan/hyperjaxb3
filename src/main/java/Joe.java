import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import generated.*;

public class Joe {
    private EntityManagerFactory entityManagerFactory;

    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("generated");
    }

    public static void main(String[] args) throws IOException {
        Joe joe = new Joe();
        joe.work();
    }

    private void work() throws IOException {
        final Properties persistenceProperties = new Properties();
        InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream(
                    "persistence.properties");
            persistenceProperties.load(is);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(
                "generated", persistenceProperties);
        ObjectFactory objectFactory = new ObjectFactory();
        final generated.PurchaseOrderType alpha = objectFactory.createPurchaseOrderType();
        alpha.setShipTo(objectFactory.createUSAddress());
        alpha.getShipTo().setCity("Sacramento");
    }
}
