package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMShopCartDto;
import com.project.onlineshop.model.master.ScMShopCart;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMShopCartMapper {
    ScMShopCart toEntity(ScMShopCartDto scMShopCartDto);

    ScMShopCartDto toDto(ScMShopCart scMShopCart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMShopCart partialUpdate(ScMShopCartDto scMShopCartDto, @MappingTarget ScMShopCart scMShopCart);
}