package com.example.mvcauth.config;

import com.example.mvcauth.model.GoogleUserInfo;
import com.example.mvcauth.model.User;
import com.example.mvcauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserService userService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());


        System.out.println(googleUserInfo.getId());

        User userToSave = User.builder()
                .sub(googleUserInfo.getId())
                .name(googleUserInfo.getName())
                .email(googleUserInfo.getEmail()).build();

        userService.register(userToSave);

        return oidcUser;
    }
}