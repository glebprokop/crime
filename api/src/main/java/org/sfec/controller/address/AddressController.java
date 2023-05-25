package org.sfec.controller.address;

import org.sfec.controller.CrudController;
import org.sfec.entity.address.dto.AddressRequest;
import org.sfec.entity.common.EntityStatus;
import org.sfec.service.address.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressController implements CrudController<AddressRequest> {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<AddressRequest>> findAllByEntityStatus(
            @RequestParam(name = "status") EntityStatus entityStatus) {
        List<AddressRequest> objects = addressService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AddressRequest> findOne(@PathVariable Long id) {
        AddressRequest object = addressService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable Long id) {
        addressService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@PathVariable Long id) {
        addressService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<AddressRequest> create(@RequestBody AddressRequest addressRequest) {
        AddressRequest created = addressService.create(addressRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<AddressRequest> update(@RequestBody AddressRequest addressRequest) {
        AddressRequest updated = addressService.update(addressRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
