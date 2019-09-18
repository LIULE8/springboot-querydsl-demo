package com.leo.demo.service;

import com.leo.demo.entity.QTCity;
import com.leo.demo.entity.QTHotel;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Leo Liu
 * @create 18/09/2019
 */
@Service
public class TestServiceImpl {
  @Autowired private EntityManager entityManager;

  private JPAQueryFactory queryFactory;

  @PostConstruct
  public void init() {
    queryFactory = new JPAQueryFactory(entityManager);
  }

  public List<Tuple> findCityAndHotel(Predicate predicate) {
    QTCity tCity = QTCity.tCity;
    QTHotel tHotel = QTHotel.tHotel;
    return queryFactory
        .select(tCity, tHotel)
        .from(tCity)
        .leftJoin(tHotel)
        .on(tHotel.cityId.longValue().eq(tCity.id.longValue()))
        .where(predicate)
        .orderBy(tCity.id.desc())
        .fetch();
  }

  public QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable) {
    QTCity tCity = QTCity.tCity;
    QTHotel tHotel = QTHotel.tHotel;
    return queryFactory
        .select(tCity, tHotel)
        .from(tCity)
        .leftJoin(tHotel)
        .on(tHotel.cityId.longValue().eq(tCity.id.longValue()))
        .where(predicate)
        .orderBy(tCity.id.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();
  }
}
