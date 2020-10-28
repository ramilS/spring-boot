package mrcpl.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.vavr.collection.List;
import io.vavr.control.Option;
import mrcpl.controller.ProductController;
import mrcpl.domain.Product;
import mrcpl.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@ContextConfiguration(classes = { ProductController.class, ProductService.class })
@WebMvcTest
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        Product product = new Product();
        product.setTitle("Product");
        product.setDescription("Product description");
        product.setUrl("product_url");
        product.setImage("some_url_image");

        given(this.productRepository.findByUrl(product.getUrl())).willReturn(Option.of(product));
        given(this.productRepository.findByUrl("fake")).willReturn(Option.none());
        given(this.productRepository.findAll(10, Option.none())).willReturn(List.of(product));
        given(this.productRepository.existsByUrl(product.getUrl())).willReturn(true);
        given(this.productRepository.existsByUrl("fake")).willReturn(false);
    }

    @Test
    void should_fail_for_unauthorized() throws Exception {
        mockMvc.perform(get("/api/products/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void should_return_list_of_products() throws Exception {
        //TODO check response JSON
        mockMvc.perform(get("/api/products/"))
                .andExpect(status().isOk());
    }

}