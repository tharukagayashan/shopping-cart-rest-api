package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMCustomerDto;
import com.projects.shoppingcart.model.master.ScMCustomer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMCustomerMapper {
    ScMCustomer toEntity(ScMCustomerDto scMCustomerDto);

    ScMCustomerDto toDto(ScMCustomer scMCustomer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMCustomer partialUpdate(ScMCustomerDto scMCustomerDto, @MappingTarget ScMCustomer scMCustomer);
}