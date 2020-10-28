package mrcpl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mrcpl.validation.ProductUniqueUrl;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ProductRequestModel {

    @NotBlank String title;

    @NotBlank String description;

    @NotBlank @URL String image;

    @NotBlank
    @ProductUniqueUrl
    String url;
}
