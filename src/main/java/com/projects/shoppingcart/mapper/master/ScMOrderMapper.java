package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMOrderDto;
import com.projects.shoppingcart.model.master.ScMOrder;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMOrderMapper {

    @Mappings({
            @Mapping(target = "scMUser.userId", source = "userId"),
            @Mapping(target = "scRStatus.statusId", source = "statusId")
    })
    ScMOrder toEntity(ScMOrderDto scMOrderDto);

    @Mappings({
            @Mapping(target = "userId", source = "scMUser.userId"),
            @Mapping(target = "statusId", source = "scRStatus.statusId")
    })
    ScMOrderDto toDto(ScMOrder scMOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMOrder partialUpdate(ScMOrderDto scMOrderDto, @MappingTarget ScMOrder scMOrder);

    @Mappings({
            @Mapping(target = "userId", source = "scMUser.userId"),
            @Mapping(target = "statusId", source = "scRStatus.statusId")
    })
    List<ScMOrderDto> entityListToDtoList(List<ScMOrder> scMOrders);
}