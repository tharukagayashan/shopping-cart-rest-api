package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMShippingInfoDto;
import com.projects.shoppingcart.model.master.ScMShippingInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMShippingInfoMapper {
    ScMShippingInfo toEntity(ScMShippingInfoDto scMShippingInfoDto);

    ScMShippingInfoDto toDto(ScMShippingInfo scMShippingInfo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMShippingInfo partialUpdate(ScMShippingInfoDto scMShippingInfoDto, @MappingTarget ScMShippingInfo scMShippingInfo);
}