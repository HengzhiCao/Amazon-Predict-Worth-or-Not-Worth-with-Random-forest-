import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;public class DataPreprocessingController {

    public DataPreprocessingController() {
        // 构造函数
    }

    public List<Product> preprocessProducts(List<Product> products) {
        for (Product product : products) {
            if (product.getPrice() <= 0.0) {
                product.setPrice(0.0);  // 将价格为空的产品价格设置为 0
            }
            if (product.getName() == null) {
                product.setName("Unknown");  // 将名称为空的产品名称设置为 "null"
            }
            // 检查并处理其他字符串字段，如描述等
            if (product.getDescription() == null) {
                product.setDescription("No description available");
            }
            // 对其他需要预处理的字段进行相似的处理
        }
        return products;
    }
}
