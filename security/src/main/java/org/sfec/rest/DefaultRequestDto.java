package org.sfec.rest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Default entity used for communicate with http requests in {@link DefaultAuthenticationController} class.
 * Contains only login (as username) and password
 */
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DefaultRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
