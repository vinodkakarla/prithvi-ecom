package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.dto.ProductUnitPriceAddUpdateRequest;
import com.farmerzharvest.ecom.entitymapper.ProductUnitPriceMapper;
import com.farmerzharvest.ecom.model.product.ProductUnitPrice;
import com.farmerzharvest.ecom.repository.ProductUnitPriceRepository;
import com.farmerzharvest.ecom.service.ProductUnitPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductUnitPriceServiceImpl implements ProductUnitPriceService {

    private final ProductUnitPriceRepository unitPriceRepository;
    private ProductUnitPriceMapper.RequestMapper reqMapper;
    private ProductUnitPriceMapper.ResponseMapper respMapper;

    @PostConstruct
    public void setup() {
        ProductUnitPriceMapper mapper = new ProductUnitPriceMapper();
        reqMapper = mapper.new RequestMapper();
        respMapper = mapper.new ResponseMapper();
    }

    @Override
    public ProductResponse.UnitDetail addProductUnitPrice(ProductUnitPriceAddUpdateRequest request) {
        ProductUnitPrice unitPrice = reqMapper.apply(request);
        unitPrice.setCreatedAt(LocalDateTime.now());
        return respMapper.apply(unitPriceRepository.save(unitPrice));
    }

    @Override
    public ProductResponse.UnitDetail updateProductUnitPrice(ProductUnitPriceAddUpdateRequest request) {
        ProductUnitPrice unitPrice = reqMapper.apply(request);
        unitPrice.setId(request.getProductUnitPriceId());
        return respMapper.apply(unitPriceRepository.save(unitPrice));
    }

    @Override
    public void deleteProductUnitPrice(long productUnitPriceId) {
        unitPriceRepository.deleteById(productUnitPriceId);
    }
}
