package com.skyrecord728.p9.config;

import com.skyrecord728.p9.domain.entity.SysRole;
import com.skyrecord728.p9.domain.entity.SysUser;
import com.skyrecord728.p9.repository.SysUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SysUserRepository userRepository;

    public CustomUserDetailsService(SysUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(SysRole::getRoleCode)
                .map(code -> new SimpleGrantedAuthority("ROLE_" + code))
                .map(GrantedAuthority.class::cast)
                .toList();

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(user.getStatus() == null || user.getStatus() == 0)
                .build();
    }
}
