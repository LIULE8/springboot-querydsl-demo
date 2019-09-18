package com.leo.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/** @author Leo Liu */
@Entity
@Table(name = "T_HOTEL")
@Getter
@Setter
@ToString
public class THotel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String address;
  private Integer cityId;
}
