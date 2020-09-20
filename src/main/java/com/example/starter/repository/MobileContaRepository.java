package com.example.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MobileContaRepository extends JpaRepository<MobileConta,Long> {
    @Query("from MobileConta i where i.carteiraSUS like :carteiraSUS")
    MobileConta findByCarteiraSUS(String carteiraSUS);
}