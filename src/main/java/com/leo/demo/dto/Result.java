package com.leo.demo.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author Leo Liu
 * @create 18/09/2019
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
  private static final long serialVersionUID = -5804535257519187365L;
  private String cityName;
  private String stateName;
  private String countryName;
  private String hotelName;
  private String hotelAddress;
}
