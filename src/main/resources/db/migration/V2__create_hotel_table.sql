-- 酒店
DROP TABLE IF EXISTS T_HOTEL;
CREATE TABLE T_HOTEL
(
    ID      INT AUTO_INCREMENT PRIMARY KEY,
    NAME    VARCHAR2(11) COMMENT '酒店名',
    ADDRESS VARCHAR2(11) COMMENT '酒店地址',
    CITY_ID VARCHAR2(11) COMMENT '城市ID'
);

INSERT INTO T_HOTEL(NAME, ADDRESS, CITY_ID)
VALUES ('上海酒店', '上海', 1),
       ('广州酒店', '广州', 2),
       ('佛山酒店', '佛山', 3),
       ('长沙酒店', '长沙', 4);