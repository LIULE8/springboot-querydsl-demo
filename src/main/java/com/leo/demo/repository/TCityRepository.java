package com.leo.demo.repository;

import com.leo.demo.entity.TCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author Leo Liu
 * @create 17/09/2019
 */
public interface TCityRepository
    extends JpaRepository<TCity, Integer>, QuerydslPredicateExecutor<TCity> {}
