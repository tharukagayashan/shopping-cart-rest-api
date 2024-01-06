package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMProductVariableMappingDto;
import com.projects.shoppingcart.model.master.ScMProductVariableMapping;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMProductVariableMappingMapper {

    @Mappings({
            @Mapping(target = "scMProduct.productId", source = "productId"),
            @Mapping(target = "scRProductVariable.variableId", source = "variableId")
    })
    ScMProductVariableMapping toEntity(ScMProductVariableMappingDto scMProductVariableMappingDto);

    @Mappings({
            @Mapping(target = "productId", source = "scMProduct.productId"),
            @Mapping(target = "variableId", source = "scRProductVariable.variableId")
    })
    ScMProductVariableMappingDto toDto(ScMProductVariableMapping scMProductVariableMapping);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMProductVariableMapping partialUpdate(ScMProductVariableMappingDto scMProductVariableMappingDto, @MappingTarget ScMProductVariableMapping scMProductVariableMapping);

    @Mappings({
            @Mapping(target = "productId", source = "scMProduct.productId"),
            @Mapping(target = "variableId", source = "scRProductVariable.variableId")
    })
    List<ScMProductVariableMappingDto> entityListToDtoList(List<ScMProductVariableMapping> productVariableMappingList);
}