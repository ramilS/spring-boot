package mrcpl.validation;

import mrcpl.repository.ProductRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ProductUniqueUrlValidator implements ConstraintValidator<ProductUniqueUrl, String> {

    private final ProductRepository productRepository;

    public ProductUniqueUrlValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return !productRepository.existsByUrl(url);
    }
}
