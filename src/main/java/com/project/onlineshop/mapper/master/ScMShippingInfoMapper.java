package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMShippingInfoDto;
import com.project.onlineshop.model.master.ScMShippingInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMShippingInfoMapper {
    ScMShippingInfo toEntity(ScMShippingInfoDto scMShippingInfoDto);

    ScMShippingInfoDto toDto(ScMShippingInfo scMShippingInfo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMShippingInfo partialUpdate(ScMShippingInfoDto scMShippingInfoDto, @MappingTarget ScMShippingInfo scMShippingInfo);
}