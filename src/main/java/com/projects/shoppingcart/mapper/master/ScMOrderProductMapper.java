package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMOrderProductDto;
import com.projects.shoppingcart.model.master.ScMOrderProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMOrderProductMapper {

    @Mappings({
            @Mapping(target = "scMOrder.orderId", source = "orderId"),
            @Mapping(target = "scMProduct.productId", source = "productId")
    })
    ScMOrderProduct toEntity(ScMOrderProductDto scMOrderProductDto);

    @Mappings({
            @Mapping(target = "orderId", source = "scMOrder.orderId"),
            @Mapping(target = "productId", source = "scMProduct.productId")
    })
    ScMOrderProductDto toDto(ScMOrderProduct scMOrderProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMOrderProduct partialUpdate(ScMOrderProductDto scMOrderProductDto, @MappingTarget ScMOrderProduct scMOrderProduct);

    @Mappings({
            @Mapping(target = "orderId", source = "scMOrder.orderId"),
            @Mapping(target = "productId", source = "scMProduct.productId")
    })
    List<ScMOrderProductDto> entityListToDtoList(List<ScMOrderProduct> orderProductList);
}