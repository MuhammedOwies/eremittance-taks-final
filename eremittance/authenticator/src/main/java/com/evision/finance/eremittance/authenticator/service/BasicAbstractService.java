package com.evision.finance.eremittance.authenticator.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BasicAbstractService<E> {
    protected final ModelMapper modelMapper;
    protected final JpaRepository<E, Long> jpaRepository;

    protected BasicAbstractService(ModelMapper modelMapper,
                                   @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") JpaRepository<E, Long> jpaRepository) {
        this.modelMapper   = modelMapper;
        this.jpaRepository = jpaRepository;
    }

    protected void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    protected boolean exists(E example, String[] ignorePaths) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withIgnorePaths(ignorePaths);
        return jpaRepository.exists(Example.of(example, matcher));
    }

    protected boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    protected Page<E> findAll(E example, String[] ignorePaths, Integer pageNumber, Integer pageSize, String[] sortBy,
                              Sort.Direction order) {
        pageNumber = (pageNumber == null || pageNumber == 0) ? 0 : pageNumber - 1;
        pageSize   = (pageSize == null) ? Integer.MAX_VALUE : pageSize;
        sortBy     = (sortBy == null || sortBy.length == 0) ? new String[]{"id"} : sortBy;
        order      = (order == null) ? Sort.Direction.ASC : order;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withIgnorePaths(ignorePaths == null ? new String[]{"id"} : ignorePaths);
        return jpaRepository.findAll(Example.of(example, matcher),
                                     PageRequest.of(pageNumber, pageSize, Sort.by(order, sortBy)));
    }

    protected E findById(Long id) {
        return jpaRepository.findById(id).orElse(null);
    }

    protected E findOne(E example, String[] ignorePaths) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withIgnorePaths(ignorePaths == null ? new String[]{"id"} : ignorePaths);
        return jpaRepository.findOne(Example.of(example, matcher)).orElse(null);
    }

    protected E save(E e) {
        return jpaRepository.save(e);
    }

    protected List<E> saveAll(Iterable<E> entities) {
        return jpaRepository.saveAll(entities);
    }
}
