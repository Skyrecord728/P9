package com.skyrecord728.p9.repository;

import com.skyrecord728.p9.domain.entity.SysUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    @EntityGraph(attributePaths = "roles")
    Optional<SysUser> findByUsername(String username);

    @Override
    @EntityGraph(attributePaths = "roles")
    List<SysUser> findAll();

    @Override
    @EntityGraph(attributePaths = "roles")
    Optional<SysUser> findById(Long id);
}
