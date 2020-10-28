package mrcpl.model;

import lombok.ToString;
import lombok.Value;
import mrcpl.domain.Product;

@Value
@ToString
public class ProductResponseModel {
    Long id;
    String title;
    String description;
    String image;
    String url;

    public ProductResponseModel(Product product) {
        this.id = product.getId();
        this.url = product.getUrl();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.image = product.getImage();
    }
}
