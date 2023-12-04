import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
public class ViewController {
    private MainView mainView;
    private ProductDetailView productDetailView;
    private PredictionResultView predictionResultView;

    public ViewController(MainView mainView, ProductDetailView productDetailView, PredictionResultView predictionResultView) {
        this.mainView = mainView;
        this.productDetailView = productDetailView;
        this.predictionResultView = predictionResultView;
    }

    public void updateProductList(List<Product> products) {
        mainView.displayProducts(products);
    }

    public void showProductDetails(Product product) {
        if (product != null) {
            productDetailView.displayProductDetails(product);
            productDetailView.setVisible(true);
        } else {
            // 处理产品为空的情况，例如显示错误消息
        }
    }

    public void showPredictionResult(String prediction) {
        if (prediction != null && !prediction.isEmpty()) {
            predictionResultView.displayPredictionResult(prediction);
            predictionResultView.setVisible(true);
        } else {
            // 处理预测结果为空的情况，例如显示错误消息
        }
    }

    // 其他可能的方法，如隐藏视图、更新其他视图元素等
}
