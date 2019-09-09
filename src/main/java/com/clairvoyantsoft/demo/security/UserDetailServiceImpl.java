package com.clairvoyantsoft.demo.security;

import com.clairvoyantsoft.demo.domain.User;
import com.clairvoyantsoft.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println("Load user by username");
        return userRepository.findOneByUsername(s).map(user -> createSpringSecurityUser(user))
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists."));
    }

    private UserDetails createSpringSecurityUser(User user) {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> Stream.concat(Stream.of(role.getName()),
                        role.getPermissions().stream().map(permission -> permission.getName()))
                ).map(authoritys -> new SimpleGrantedAuthority(authoritys)).
                        collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }
}
