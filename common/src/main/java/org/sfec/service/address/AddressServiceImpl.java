package org.sfec.service.address;

import org.sfec.entity.Address;
import org.sfec.exception.EntityNotFoundException;
import org.sfec.repository.address.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Address findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id", id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Address create(Address address) {
        return repository.save(address);
    }

    @Override
    public Address update(Address address) {
        return this.create(address);
    }
}
