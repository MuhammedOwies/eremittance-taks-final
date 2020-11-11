package com.evision.finance.eremittance.authenticator.security;

import com.evision.finance.eremittance.authenticator.entity.UserVO;
import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
import com.evision.finance.eremittance.authenticator.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserServiceImpl service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = service.findUser(UserVO.of().setCredentials(username).setStatus(StatusRef.ACTV));
        if (/*"admin"*/userVO.getPrincipal().equals(username)) {
            return new User(
                    userVO.getPrincipal(), /*"admin"*/
                    userVO.getCredentials(), /*"$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6"*/
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
