package com.example.subsavvy.Service;

import com.example.subsavvy.Data.CancellationSub;
import com.example.subsavvy.Repository.CancellationSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CancellationSubService {

    @Autowired
    private CancellationSubRepository cancellationSubRepository;

    public CancellationSub addCancellation(CancellationSub cancellationSub) {
        return cancellationSubRepository.save(cancellationSub);
    }

    public List<CancellationSub> getAllCancellations() {
        return cancellationSubRepository.findAll();
    }

    public List<CancellationSub> getCancellationsByUserId(UUID userid) {
        return cancellationSubRepository.findAllByUserid(userid);
    }

    public void deleteCancellation(UUID id) {
        cancellationSubRepository.deleteById(id);
    }
}
