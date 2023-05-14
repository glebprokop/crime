package org.sfec.controller.address;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sfec.entity.Address;
import org.sfec.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

//    AddressService addressService;
//
//    public AddressController(AddressService addressService) {
//        this.addressService = addressService;
//    }
//
    AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Address>> findAllAddresses(){
        List<Address> addresses = addressRepository.findAll();

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<Address>> createAddress(@RequestBody Address address){


        List<Address> addresses = addressRepository.findAll();

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }


}
