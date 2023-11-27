package org.example.repository;


import org.example.enums.RoleName;
import org.example.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleModelRepository extends JpaRepository<RoleModel, Long> {

    List<RoleModel> findByRoleNameIn(List<RoleName> roles);

    RoleModel findByRoleName(String nome);

}
