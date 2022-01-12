CREATE TABLE SHOP_ORDER(
    ID NUMBER,
    ORDERLIST VARCHAR2(1000 CHAR),
    BUYERNAME VARCHAR2(15 CHAR),
    PACKAGENAME VARCHAR2(15 CHAR),
    ADDRESS VARCHAR2(100 CHAR),
    POSTNUMBER NUMBER,
    PHONENUMBER NUMBER,
    PAYMETHOD VARCHAR2(15 CHAR),
    PRICE NUMBER,
    ORDERNO VARCHAR2(30 CHAR),
    ORDERSTATE NUMBER,
    PACKAGENO NUMBER
);

CREATE SEQUENCE SHOP_ORDER_SEQ NOCACHE;
DROP SEQUENCE SHOP_ORDER_SEQ;

SELECT * FROM SHOP_ORDER ORDER BY ID;

ALTER TABLE SHOP_ORDER ADD CONSTRAINT SHOP_ORDER_ID_PK PRIMARY KEY(ID);

INSERT INTO SHOP_ORDER(
    ID,
    ORDERLIST,
    BUYERNAME,
    PACKAGENAME,
    ADDRESS,
    POSTNUMBER,
    PHONENUMBER,
    PAYMETHOD,
    PRICE,
    ORDERNO
) VALUES (
    SHOP_ORDER_SEQ.NEXTVAL,
    '1_2_3',
    'aaaa',
    '김철수',
    '서울시 테헤란로 123길 123',
    01234,
    0100000000,
    'card',
    10000,
    CONCAT(
        TO_CHAR(FROM_TZ(CAST(SYSDATE AS TIMESTAMP), 'GMT') AT TIME ZONE 'ASIA/SEOUL','YYMMDDHH24MISS'), 
        DBMS_RANDOM.STRING('X', 13))
);

SELECT DBMS_RANDOM.STRING('X', 20) RAND FROM DUAL;
SELECT FROM_TZ(CAST(SYSDATE AS TIMESTAMP), 'GMT') AT TIME ZONE 'ASIA/SEOUL' TSTDAY,
    TO_CHAR(FROM_TZ(CAST(SYSDATE AS TIMESTAMP), 'GMT') AT TIME ZONE 'ASIA/SEOUL','YYMMDDHH24MISS') TSTDACHAR,
    DBMS_RANDOM.STRING('X', 18) RAND,
    CONCAT(
        TO_CHAR(FROM_TZ(CAST(SYSDATE AS TIMESTAMP), 
    'GMT') AT TIME ZONE 'ASIA/SEOUL','YYMMDDHH24MISS'), DBMS_RANDOM.STRING('X', 13)
    ) ORDERDATE
    FROM DUAL;