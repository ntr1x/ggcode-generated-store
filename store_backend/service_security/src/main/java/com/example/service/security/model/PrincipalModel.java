package com.example.service.security.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ntr1x.common.api.views.Views;

import java.net.URI;
import java.util.Collection;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalModel {

    @JsonView(Views.Default.class)
    private URI issuer;

    @JsonView(Views.Default.class)
    private String subject;

    @JsonView(Views.Default.class)
    private String username;

    @JsonView(Views.Default.class)
    private String fullName;

    @JsonView(Views.Default.class)
    private String email;

    @JsonView(Views.Default.class)
    private String token;

    @JsonView(Views.Default.class)
    private Collection<String> authorities;
}
