package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMOrderDto;
import com.projects.shoppingcart.model.master.ScMOrder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMOrderMapper {

    @Mappings({
            @Mapping(target = "scMUser.userId", source = "userId")
    })
    ScMOrder toEntity(ScMOrderDto scMOrderDto);

    @Mappings({
            @Mapping(target = "userId", source = "scMUser.userId")
    })
    ScMOrderDto toDto(ScMOrder scMOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMOrder partialUpdate(ScMOrderDto scMOrderDto, @MappingTarget ScMOrder scMOrder);
}