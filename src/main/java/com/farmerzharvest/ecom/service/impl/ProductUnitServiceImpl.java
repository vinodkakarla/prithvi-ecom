package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.ProductUnitDTO;
import com.farmerzharvest.ecom.entitymapper.ProductUnitsMapper;
import com.farmerzharvest.ecom.model.product.ProductUnits;
import com.farmerzharvest.ecom.repository.ProductUnitsRepository;
import com.farmerzharvest.ecom.service.ProductUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductUnitServiceImpl implements ProductUnitService {

    private final ProductUnitsRepository productUnitsRepository;
    private ProductUnitsMapper mapper;

    @PostConstruct
    public void setup() {
        mapper = new ProductUnitsMapper();
    }

    @Override
    public List<ProductUnitDTO> getProductUnits() {
        return productUnitsRepository.findAll(Sort.by("orderBy").ascending()).stream()
                .map(mapper::mapProductModelToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductUnitDTO getProductUnitById(long id) {
        Optional<ProductUnits> unit = productUnitsRepository.findById(id);
        if (unit.isPresent()) {
            return mapper.mapProductModelToDTO(unit.get());
        }
        return null;
    }

    @Override
    public List<String> getValidProductUnitTypes() {
        return productUnitsRepository.findDistinctUnitType();
    }

    @Override
    public ProductUnitDTO addProductUnit(ProductUnitDTO productUnit) {
        return mapper.mapProductModelToDTO(productUnitsRepository.save(mapper.mapProductDtoToModel(productUnit)));
    }

    @Override
    public ProductUnitDTO updateProductUnit(ProductUnitDTO productUnit) {
        ProductUnits unit = mapper.mapProductDtoToModel(productUnit);
        unit.setId(productUnit.getProductUnitId());
        return mapper.mapProductModelToDTO(productUnitsRepository.save(unit));
    }

    @Override
    public void deleteProductUnit(Long productUnitId) {
        productUnitsRepository.deleteById(productUnitId);
    }
}
