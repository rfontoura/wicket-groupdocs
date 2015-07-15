package br.com.dataeasy.chronus.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <b>Description:</b> Service de autenticação que implementa o padrão do spring-security ({@link UserDetailsService} <br>
 * <b>Project:</b> chronus-business <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 10/06/2015
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    public static final String ROLE_USUARIO = "ROLE_USUARIO";
    public static final String ROLE_ADMIN   = "ROLE_ADMIN";


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails ud = new UserDetails() {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public String getUsername() {
                return "user";
            }

            @Override
            public String getPassword() {
                return "123";
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptyList();
            }
        };

        return ud;
    }

}
