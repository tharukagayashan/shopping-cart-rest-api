package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.model.master.ScMShopCart;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMShopCartMapper {

    @Mappings({
            @Mapping(target = "scMProduct.productId", source = "productId"),
            @Mapping(target = "scMUser.userId", source = "userId"),
    })
    ScMShopCart toEntity(ScMShopCartDto scMShopCartDto);

    @Mappings({
            @Mapping(target = "productId", source = "scMProduct.productId"),
            @Mapping(target = "userId", source = "scMUser.userId"),
    })
    ScMShopCartDto toDto(ScMShopCart scMShopCart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMShopCart partialUpdate(ScMShopCartDto scMShopCartDto, @MappingTarget ScMShopCart scMShopCart);

    @Mappings({
            @Mapping(target = "productId", source = "scMProduct.productId"),
            @Mapping(target = "userId", source = "scMUser.userId"),
    })
    List<ScMShopCartDto> entityListToDtoList(List<ScMShopCart> shopCartList);
}