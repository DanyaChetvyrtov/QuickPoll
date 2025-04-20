package com.REST.example.v3.service;

import com.REST.example.model.MyUser;
import com.REST.example.v3.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userServiceV3")
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepository.findByUsername(username);

        System.out.println(username);
        System.out.println(myUser);

        if (myUser == null) throw new UsernameNotFoundException("No such user: " + username);
        System.out.println(myUser.getPassword());

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (myUser.isAdmin())
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");

        return new User(myUser.getUsername(), myUser.getPassword(), authorities);
    }
}
