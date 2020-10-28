package mrcpl.service;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import mrcpl.domain.Product;
import mrcpl.model.ProductRequestModel;
import mrcpl.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Option<Product> getProduct(@NotNull String url) {
        return productRepository.findByUrl(url);
    }

    @Transactional(readOnly = true)
    public List<Product> getProducts(int max, Option<Long> cursor) {
        return productRepository.findAll(max, cursor);
    }

    public Product save(@NotNull ProductRequestModel product) {
        log.info("Creating new product. {}", product);
        Product p = Product.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .image(product.getImage())
                .url(product.getUrl().trim()).build();
        return productRepository.save(p);
    }

    public void deleteByUrl(@NotNull String url) {
        log.info("Delete product by url = {}", url);
        productRepository.deleteByUrl(url);
    }
}
