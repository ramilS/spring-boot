package mrcpl.controller;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import mrcpl.domain.Product;
import mrcpl.model.ProductRequestModel;
import mrcpl.model.ProductResponseModel;
import mrcpl.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
//TODO add swagger
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    Map<String, ?> index(
            @RequestParam(required = false) Integer max,
            @RequestParam(required = false) Long cursor) {

        int limit = Math.max(Option.of(max).getOrElse(0), 10);

        List<ProductResponseModel> products = productService.getProducts(limit, Option.of(cursor))
                .map(ProductResponseModel::new);

        return HashMap.of("rows", products, "cursor", products.size() < limit ? null : products.last().getId());
    }

    @GetMapping("/{url}")
    Option<ProductResponseModel> show(@Valid @PathVariable String url) {
        return productService.getProduct(url)
                .map(ProductResponseModel::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseModel create(@Valid @RequestBody ProductRequestModel productRequest) {
        //TODO catch Exceptions
        Product product = productService.save(productRequest);
        return new ProductResponseModel(product);
    }

    @DeleteMapping("/{url}")
    public void delete(@PathVariable String url) {
        productService.deleteByUrl(url);
    }

    //TODO add update

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
