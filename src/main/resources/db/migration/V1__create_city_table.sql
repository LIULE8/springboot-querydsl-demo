-- 城市
DROP TABLE IF EXISTS T_CITY;
CREATE TABLE T_CITY
(
    ID      BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME    VARCHAR2(11) COMMENT '城市名',
    STATE   VARCHAR2(11) COMMENT '省份名',
    COUNTRY VARCHAR2(11) COMMENT '国家名'
);

INSERT INTO T_CITY(NAME, STATE, COUNTRY)
VALUES ('上海', '上海', '中国'),
       ('广州', '广东', '中国'),
       ('佛山', '广东', '中国'),
       ('长沙', '湖南', '中国');