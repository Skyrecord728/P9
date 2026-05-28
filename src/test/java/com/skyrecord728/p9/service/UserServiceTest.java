package com.skyrecord728.p9.service;

import com.skyrecord728.p9.domain.entity.SysUser;
import com.skyrecord728.p9.dto.user.UserRequest;
import com.skyrecord728.p9.repository.SysRoleRepository;
import com.skyrecord728.p9.repository.SysUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private SysUserRepository userRepository;
    @Mock
    private SysRoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void updateShouldKeepPasswordWhenBlank() {
        SysUser user = new SysUser();
        user.setId(1L);
        user.setUsername("u");
        user.setPassword("old-hash");
        user.setStatus((byte) 1);

        UserRequest request = new UserRequest();
        request.setUsername("u2");
        request.setStatus((byte) 1);
        request.setPassword("   ");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(SysUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.update(1L, request);

        Assertions.assertEquals("old-hash", user.getPassword());
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void updateShouldEncodePasswordWhenProvided() {
        SysUser user = new SysUser();
        user.setId(1L);
        user.setUsername("u");
        user.setPassword("old-hash");
        user.setStatus((byte) 1);

        UserRequest request = new UserRequest();
        request.setUsername("u2");
        request.setStatus((byte) 1);
        request.setPassword("new-pass");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("new-pass")).thenReturn("new-hash");
        when(userRepository.save(any(SysUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.update(1L, request);

        Assertions.assertEquals("new-hash", user.getPassword());
        verify(passwordEncoder).encode("new-pass");
    }
}
