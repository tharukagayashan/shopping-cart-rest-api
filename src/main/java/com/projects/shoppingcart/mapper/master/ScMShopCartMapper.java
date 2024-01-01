package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.model.master.ScMShopCart;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMShopCartMapper {
    ScMShopCart toEntity(ScMShopCartDto scMShopCartDto);

    ScMShopCartDto toDto(ScMShopCart scMShopCart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMShopCart partialUpdate(ScMShopCartDto scMShopCartDto, @MappingTarget ScMShopCart scMShopCart);
}