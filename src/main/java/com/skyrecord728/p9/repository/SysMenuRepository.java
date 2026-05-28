package com.skyrecord728.p9.repository;

import com.skyrecord728.p9.domain.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {

    @Query("select distinct m from SysUser u join u.roles r join r.menus m where u.id = :userId and m.visible = 1 order by m.orderNo asc, m.id asc")
    List<SysMenu> findVisibleMenusByUserId(Long userId);

    List<SysMenu> findAllByOrderByOrderNoAscIdAsc();
}
