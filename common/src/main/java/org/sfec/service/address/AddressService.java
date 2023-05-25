package org.sfec.service.address;

import org.sfec.entity.address.Address;
import org.sfec.entity.address.dto.AddressRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.address.AddressRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends AbstractService<Address, AddressRequest, AddressRepository> {

    public AddressService(CommonMapper<Address, AddressRequest> mapper,
                          AddressRepository repository,
                          TimeManager timeManager) {
        super(mapper, repository, timeManager);
    }
}
