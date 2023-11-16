package com.azubu.schoolmanagementapplication.user;

import com.azubu.schoolmanagementapplication.roles.RoleRepository;
import com.azubu.schoolmanagementapplication.roles.Roles;
import com.azubu.schoolmanagementapplication.roles.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserService userService;

    private final RoleRepository roleRepository;

    public UserDetailsServiceImplementation(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        List<Roles> roles = roleRepository.getUserRoles(user.getId());

        if(user == null) {
            throw new UsernameNotFoundException("User " + email + " not found");
        }
        if(roleRepository.getUserRole(user.getId()) == null) {
            throw new RuntimeException("User has no roles");
        }

        System.out.println(user.getId());
        System.out.println(roles.stream().map(roles1 -> roles1.getName()));

        List<GrantedAuthority> authorities = roleRepository.getUserRoles(user.getId()).stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    // role.getName() jest tutaj NULL !!!


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isActivated(), user.isExpired(), user.isCredentialsExpired(), !user.isLocked(), authorities);
    }
}
