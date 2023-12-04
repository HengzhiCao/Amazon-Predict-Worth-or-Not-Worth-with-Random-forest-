public class PredictionController {
    private RandomForest randomForest;

    public PredictionController(RandomForest randomForest) {
        this.randomForest = randomForest;
    }

    public String predictProduct(Product product) {
        double[] features = extractFeatures(product);
        Instance instance = new Instance(features, "", "");
        return randomForest.predict(instance);
    }

    private double[] extractFeatures(Product product) {
        double[] features = new double[5];
        features[0] = product.getDiscountedPrice();
        features[1] = product.getActualPrice();
        features[2] = product.getDiscountPercentage();
        features[3] = product.getRating();
        features[4] = product.getRatingCount();
        return features;
    }

    public void handleProductSelection(Product product) {
        ProductDetailView detailView = new ProductDetailView(product, this);
        detailView.setVisible(true);
    }
}
