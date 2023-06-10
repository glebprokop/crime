package org.sfec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.sfec.entity.address.Address;
import org.sfec.entity.address.dto.AddressRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper extends CommonMapper<Address, AddressRequest> {
}