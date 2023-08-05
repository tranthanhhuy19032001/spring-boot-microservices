package com.programming.productservice.service;

import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private ModelMapper modelMapper = new ModelMapper();

    private final ProductRepository productRepository;
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());

        ProductResponse productResponse = modelMapper.map(productRequest, ProductResponse.class);
        productResponse.setId(product.getId());
        return productResponse;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
