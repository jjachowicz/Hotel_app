package com.example.demo;

import com.example.demo.Entities.GuestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestPagingRepository extends PagingAndSortingRepository<GuestEntity, Long> {
    Page<GuestEntity> findAll(Pageable pageable);
}
