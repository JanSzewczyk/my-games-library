package pl.myGamesLibrary.utils.converters;

import pl.myGamesLibrary.database.models.Product;
import pl.myGamesLibrary.modelFx.ProductFx;

public class ConverterProduct {

    public static Product convertToProduct(ProductFx productFx){
        Product product = new Product();
        product.setId(productFx.getId());
        product.setMyRating(productFx.getMyRating());
        product.setMyOpinion(productFx.getMyOpinion());
        return product;
    }

    public static ProductFx convertToProductFx(Product product) {
        ProductFx productFx = new ProductFx();
        productFx.setId(product.getId());
        productFx.setUserFx(ConverterUser.convertToUserFx(product.getUser()));
        productFx.setGameFx(ConverterGame.convertToGameFx(product.getGame()));
        productFx.setMyRating(product.getMyRating());
        productFx.setMyOpinion(product.getMyOpinion());
        return productFx;
    }

}
