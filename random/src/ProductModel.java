import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private String csvFilePath;

    public ProductModel(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public List<Product> getFilteredProducts(double priceFrom, double priceTo, String searchKeyword) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                try {
                    double price = Double.parseDouble(values[1]);
                    if (price >= priceFrom && price <= priceTo) {
                        if (searchKeyword == null || searchKeyword.isEmpty() || values[6].toLowerCase().contains(searchKeyword.toLowerCase())) {
                            Product product = new Product(values[6], values[8], price, values[7]);
                            products.add(product);
                        }
                    }
                } catch (NumberFormatException e) {
                    // 处理解析错误
                }
            }
        }
        return products;
    }
}
