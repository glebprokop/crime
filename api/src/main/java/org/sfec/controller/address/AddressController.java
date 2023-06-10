package org.sfec.controller.address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.sfec.controller.CrudController;
import org.sfec.entity.address.dto.AddressRequest;
import org.sfec.entity.common.EntityStatus;
import org.sfec.service.address.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class AddressController implements CrudController<AddressRequest> {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(
            summary = "Find all addresses",
            description = "Find all addresses according it`s status",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Addresses successfully get",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressRequest.class))
                    )
            }
    )
    @Override
    @GetMapping()
    public ResponseEntity<List<AddressRequest>> findAllByEntityStatus(
            @RequestParam(name = "status") EntityStatus entityStatus) {
        List<AddressRequest> objects = addressService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Operation(
            summary = "Find one address",
            description = "Find one address by it`s id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Address successfully get",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressRequest.class))
                    )
            }
    )
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AddressRequest> findOne(@Valid @PathVariable Long id) {
        AddressRequest object = addressService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(
            summary = "Soft delete one address",
            description = "Soft delete one address by it`s id. Have no any response")
    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@Valid @PathVariable Long id) {
        addressService.softDelete(id);
    }

    @Operation(
            summary = "Hard delete one address",
            description = "Hard delete one address by it`s id. Have no any response")
    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@Valid @PathVariable Long id) {
        addressService.hardDelete(id);
    }

    @Operation(
            summary = "Create address",
            description = "Create one address according the JSON performance",
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Address successfully created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressRequest.class))
                    )
            }
    )
    @Override
    @PostMapping()
    public ResponseEntity<AddressRequest> create(@Valid @RequestBody AddressRequest addressRequest) {
        AddressRequest created = addressService.create(addressRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update address",
            description = "Update one address according the JSON performance (with id)",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Address successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressRequest.class))
                    )
            }
    )
    @Override
    @PutMapping()
    public ResponseEntity<AddressRequest> update(@Valid @RequestBody AddressRequest addressRequest) {
        AddressRequest updated = addressService.update(addressRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
