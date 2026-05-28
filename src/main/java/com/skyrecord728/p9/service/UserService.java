package com.skyrecord728.p9.service;

import com.skyrecord728.p9.domain.entity.SysRole;
import com.skyrecord728.p9.domain.entity.SysUser;
import com.skyrecord728.p9.dto.auth.MeResponse;
import com.skyrecord728.p9.dto.user.UserRequest;
import com.skyrecord728.p9.dto.user.UserResponse;
import com.skyrecord728.p9.repository.SysRoleRepository;
import com.skyrecord728.p9.repository.SysUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final SysUserRepository userRepository;
    private final SysRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(SysUserRepository userRepository, SysRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> listAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse getById(Long id) {
        return toResponse(findById(id));
    }

    public MeResponse getMe(String username) {
        SysUser user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        return new MeResponse(user.getId(), user.getUsername(), user.getStatus(),
                user.getRoles().stream().map(SysRole::getRoleCode).sorted().toList());
    }

    public UserResponse create(UserRequest request) {
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        SysUser user = new SysUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(request.getStatus());
        return toResponse(userRepository.save(user));
    }

    public UserResponse update(Long id, UserRequest request) {
        SysUser user = findById(id);
        user.setUsername(request.getUsername());
        user.setStatus(request.getStatus());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return toResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    @Transactional
    public UserResponse updateStatus(Long id, Byte status) {
        SysUser user = findById(id);
        user.setStatus(status);
        return toResponse(user);
    }

    @Transactional
    public UserResponse assignRoles(Long id, Set<Long> roleIds) {
        SysUser user = findById(id);
        Set<SysRole> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        if (roles.size() != roleIds.size()) {
            throw new IllegalArgumentException("部分角色不存在");
        }
        user.setRoles(roles);
        return toResponse(user);
    }

    public SysUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
    }

    private SysUser findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
    }

    private UserResponse toResponse(SysUser user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getRoles().stream().map(SysRole::getRoleCode).sorted().toList()
        );
    }
}
