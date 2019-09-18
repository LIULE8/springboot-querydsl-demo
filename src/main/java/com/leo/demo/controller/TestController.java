package com.leo.demo.controller;

import com.leo.demo.dto.Result;
import com.leo.demo.entity.QTCity;
import com.leo.demo.entity.QTHotel;
import com.leo.demo.entity.TCity;
import com.leo.demo.entity.THotel;
import com.leo.demo.repository.TCityRepository;
import com.leo.demo.service.TestServiceImpl;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Leo Liu
 * @create 17/09/2019
 */
@RestController
@RequestMapping("test")
public class TestController {

  @Autowired private TCityRepository tCityRepository;
  @Autowired private TestServiceImpl testService;

  /**
   * 单表条件查询
   *
   * @param pageable
   * @return
   */
  @GetMapping("criteria-query")
  public Page<TCity> searchCityByCriteria(Pageable pageable) {
    QTCity qtCity = QTCity.tCity;
    Predicate predicate = qtCity.id.longValue().lt(3).and(qtCity.name.like("%海%"));
    PageRequest pageRequest = PageRequest.of((int) pageable.getOffset(), pageable.getPageSize());
    return tCityRepository.findAll(predicate, pageRequest);
  }

  /**
   * 多表条件查询
   *
   * @return
   */
  @GetMapping("associated-query")
  public List<Result> findCityAndHotel() {
    List<Tuple> tuples = testService.findCityAndHotel(QTCity.tCity.name.like("%海%"));
    return tuples.stream()
        .map(
            tuple -> {
              TCity tCity = tuple.get(QTCity.tCity);
              THotel tHotel = tuple.get(QTHotel.tHotel);
              return Result.builder()
                  .cityName(tCity != null ? tCity.getName() : null)
                  .countryName(tCity != null ? tCity.getCountry() : null)
                  .stateName(tCity != null ? tCity.getState() : null)
                  .hotelName(tHotel != null ? tHotel.getName() : null)
                  .hotelAddress(tHotel != null ? tHotel.getAddress() : null)
                  .build();
            })
        .collect(Collectors.toList());
  }

  /**
   * 多表条件分页查询
   *
   * @param pageable
   * @return
   */
  @GetMapping("associated-query-page")
  public Page<Result> findCityAndHotelPage(Pageable pageable) {
    QueryResults<Tuple> resultTuples =
        testService.findCityAndHotelPage(QTCity.tCity.name.like("%海%"), pageable);
    List<Result> results =
        resultTuples.getResults().stream()
            .map(
                tuple -> {
                  TCity tCity = tuple.get(QTCity.tCity);
                  THotel tHotel = tuple.get(QTHotel.tHotel);
                  return Result.builder()
                      .cityName(tCity != null ? tCity.getName() : null)
                      .countryName(tCity != null ? tCity.getCountry() : null)
                      .stateName(tCity != null ? tCity.getState() : null)
                      .hotelName(tHotel != null ? tHotel.getName() : null)
                      .hotelAddress(tHotel != null ? tHotel.getAddress() : null)
                      .build();
                })
            .collect(Collectors.toList());
    return new PageImpl<>(results, pageable, resultTuples.getTotal());
  }



}
