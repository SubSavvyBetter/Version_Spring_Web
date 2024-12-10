package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.CancellationSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CancellationSubRepository extends JpaRepository<CancellationSub, UUID> {
    List<CancellationSub> findAllByUserId(UUID userId);
}
