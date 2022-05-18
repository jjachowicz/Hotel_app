package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestPagingRepository extends PagingAndSortingRepository<GuestEntity, Long> {
    Page<GuestEntity> findAll(Pageable pageable);
}
