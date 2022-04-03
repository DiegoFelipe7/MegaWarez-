package com.Sofka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sofka.domain.Download;

public interface DownloadRepository extends JpaRepository<Download, Integer> {

}
