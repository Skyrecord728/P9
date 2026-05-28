package com.skyrecord728.p9.repository;

import com.skyrecord728.p9.domain.entity.SysRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

    @Override
    @EntityGraph(attributePaths = "menus")
    List<SysRole> findAll();

    @Override
    @EntityGraph(attributePaths = "menus")
    Optional<SysRole> findById(Long id);
}
