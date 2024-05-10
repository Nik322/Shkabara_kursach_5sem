package org.example.cafe.config;

import org.example.cafe.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class AppUserDetailsTest {
    @Mock
    private User mockUser;
    private AppUserDetails appUserDetails;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        appUserDetails = new AppUserDetails(mockUser);
    }
    @Test
    void getAuthorities() {
        String roles = "ROLE_USER, ROLE_ADMIN";
        when(mockUser.getRoles()).thenReturn(roles);
        Collection<? extends GrantedAuthority> authorities = appUserDetails.getAuthorities();
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void getPassword() {
        String password = "password";
        when(mockUser.getPassword()).thenReturn(password);

        String retrievedPassword = appUserDetails.getPassword();

        assertEquals(password, retrievedPassword);
    }

    @Test
    void getUsername() {
        String name = "username";
        when(mockUser.getName()).thenReturn(name);

        String retrievedName = appUserDetails.getUsername();

        assertEquals(name, retrievedName);
    }

}
