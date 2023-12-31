package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMCustomerDto;
import com.project.onlineshop.model.master.ScMCustomer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMCustomerMapper {
    ScMCustomer toEntity(ScMCustomerDto scMCustomerDto);

    ScMCustomerDto toDto(ScMCustomer scMCustomer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMCustomer partialUpdate(ScMCustomerDto scMCustomerDto, @MappingTarget ScMCustomer scMCustomer);
}