package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.CancellationSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationSubRepository extends JpaRepository<CancellationSub, Long> {
}
