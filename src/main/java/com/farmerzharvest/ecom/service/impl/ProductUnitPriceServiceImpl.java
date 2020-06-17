package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.ProductUnitPriceAddUpdateRequest;
import com.farmerzharvest.ecom.entitymapper.ProductUnitPriceMapper;
import com.farmerzharvest.ecom.model.product.ProductUnitPrice;
import com.farmerzharvest.ecom.repository.ProductUnitPriceRepository;
import com.farmerzharvest.ecom.service.ProductUnitPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public ProductUnitPriceAddUpdateRequest addProductUnitPrice(ProductUnitPriceAddUpdateRequest request) {
        ProductUnitPrice unitPrice = reqMapper.apply(request);
        unitPrice.setCreatedAt(LocalDateTime.now());
        return respMapper.apply(unitPriceRepository.save(unitPrice));
    }

    @Override
    public List<ProductUnitPriceAddUpdateRequest> addOrUpdateProductUnitPrices(
            List<ProductUnitPriceAddUpdateRequest> request) {
        List<ProductUnitPrice> unitPrices = request.stream()
                .map(unitPrice -> {
                    ProductUnitPrice unit = reqMapper.apply(unitPrice);
                    if (unit.getCreatedAt() == null) {
                        unit.setCreatedAt(LocalDateTime.now());
                    }
                    unit.setUpdatedAt(LocalDateTime.now());
                    return unit;
                })
                .collect(Collectors.toList());
        return unitPriceRepository.saveAll(unitPrices).stream()
                .map(respMapper::apply).collect(Collectors.toList());
    }

    @Override
    public ProductUnitPriceAddUpdateRequest updateProductUnitPrice(ProductUnitPriceAddUpdateRequest request) {
        ProductUnitPrice unitPrice = reqMapper.apply(request);
        unitPrice.setId(request.getProductUnitPriceId());
        return respMapper.apply(unitPriceRepository.save(unitPrice));
    }

    @Override
    public void deleteProductUnitPrice(long productUnitPriceId) {
        unitPriceRepository.deleteById(productUnitPriceId);
    }
}
