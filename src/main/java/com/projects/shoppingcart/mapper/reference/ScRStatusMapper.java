package com.projects.shoppingcart.mapper.reference;

import com.projects.shoppingcart.dto.reference.ScRStatusDto;
import com.projects.shoppingcart.model.reference.ScRStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRStatusMapper {
    ScRStatus toEntity(ScRStatusDto scRStatusDto);

    ScRStatusDto toDto(ScRStatus scRStatus);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRStatus partialUpdate(ScRStatusDto scRStatusDto, @MappingTarget ScRStatus scRStatus);

    List<ScRStatusDto> entityListToDtoList(List<ScRStatus> statuses);
}