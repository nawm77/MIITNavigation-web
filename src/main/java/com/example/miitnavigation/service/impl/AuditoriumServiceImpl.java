package com.example.miitnavigation.service.impl;

import com.example.miitnavigation.model.Auditorium;
import com.example.miitnavigation.repository.AuditoriumRepository;
import com.example.miitnavigation.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {
    private final AuditoriumRepository auditoriumRepository;

    @Autowired
    public AuditoriumServiceImpl(AuditoriumRepository auditoriumRepository) {
        this.auditoriumRepository = auditoriumRepository;
    }

    @Override
    public Auditorium findOrCreate(Auditorium auditorium) {
        return auditoriumRepository.findByAuditoriumNumber(auditorium.getAuditoriumNumber())
                .orElseGet(() -> auditoriumRepository.save(auditorium));
    }

    @Async
    @Override
    public CompletableFuture<Optional<Auditorium>> getAuditoriumById(Long id) {
        return CompletableFuture.completedFuture(auditoriumRepository.findById(id));
    }

    @Async
    @Override
    public CompletableFuture<List<Auditorium>> getAllAudiences() {
        return CompletableFuture.completedFuture(auditoriumRepository.findAll());
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteProductById(Long id) {
        auditoriumRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
