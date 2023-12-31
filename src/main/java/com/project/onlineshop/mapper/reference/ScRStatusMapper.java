package com.project.onlineshop.mapper.reference;

import com.project.onlineshop.dto.reference.ScRStatusDto;
import com.project.onlineshop.model.reference.ScRStatus;
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