package com.devGong.leakmasterserver.repository;

import com.devGong.leakmasterserver.domain.PreInstallCheckModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreInstallChkRepository extends JpaRepository<PreInstallCheckModel,Integer> {
}
