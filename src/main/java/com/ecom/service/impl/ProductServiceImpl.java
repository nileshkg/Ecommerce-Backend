package com.ecom.service.impl;

import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.ProductDto;
import com.ecom.repository.CategoryRepo;
import com.ecom.repository.ProductRepo;
import com.ecom.service.ProductService;
import net.bytebuddy.pool.TypePool;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public ProductDto create(ProductDto product, Integer catId) {
        Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category", "category id ", catId + ""));
        Product product1 = this.modelMapper.map(product, Product.class);
        product1.setCategory(category);
        Product product2 = this.productRepo.save(product1);
        return this.modelMapper.map(product2, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto product, Integer productId) {

        Product product1 = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product ", "product id", productId + ""));

        product1.setImageName(product.getImageName());
        product1.setDescription(product.getDescription());
        product1.setIsLive(product.getIsLive());
        product1.setPrice(product.getPrice());
        product1.setInStock(product.getInStock());
        product1.setRating(product.getRating());

        Product updatedProduct = this.productRepo.save(product1);

        return this.modelMapper.map(updatedProduct, ProductDto.class);
        //TODO
    }

    @Override
    public ProductDto getById(Integer productId) {
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product", "product id ", productId + ""));

        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void delete(Integer productId) {
//TODO
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> all = this.productRepo.findAll();
        return all.stream().map((product -> this.modelMapper.map(product, ProductDto.class))).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductOfCategory(Integer catId) {
        Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category", "category id ", catId + ""));
        List<Product> products = this.productRepo.findByCategory(category);
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductNotLive() {


        List<Product> products = this.productRepo.findByIsLiveFalse();
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<ProductDto> getProductOutOfStock() {
        List<Product> products = this.productRepo.findByInStockFalse();
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<ProductDto> searchProduct(String titleKey) {
        List<Product> products = this.productRepo.searchProducts("%" + titleKey + "%");
        List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }
}
