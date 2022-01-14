CREATE TABLE SHOP_ITEMS (
    ID NUMBER,
    ITEMNAME VARCHAR2(15 CHAR),
    SELLERNAME VARCHAR2(15 CHAR),
    UPLOADTIME DATE,
    THUMURL VARCHAR2(50 CHAR),
    INFOURL VARCHAR2(50 CHAR),
    INFOTEXT VARCHAR2(200 CHAR),
	PRICE NUMBER,
	CATEGORY NUMBER
);

CREATE SEQUENCE SHOP_ITEMS_SEQ NOCACHE;

SELECT * FROM SHOP_ITEMS ORDER BY ID;

ALTER TABLE SHOP_ITEMS ADD CONSTRAINT SHOP_ITEMS_ID_PK PRIMARY KEY(ID);
ALTER TABLE SHOP_ITEMS ADD PRICE NUMBER;
ALTER TABLE SHOP_ITEMS ADD CATEGORY NUMBER;

SELECT * FROM SHOP_ITEMS WHERE SELLERNAME = 'aaaa';
SELECT * FROM SHOP_ITEMS WHERE ID IN (2,3,7,11,15) AND SELLERNAME = 'aaaa';


SELECT CAST(SYSDATE AS TIMESTAMP) AT TIME ZONE 'ASIA/SEOUL' D1 FROM DUAL;

CREATE TABLE TIMETE (
    ID NUMBER,
    TEDATE DATE
);

INSERT INTO TIMETE VALUES(3, FROM_TZ(CAST(SYSDATE AS TIMESTAMP), 'GMT') AT TIME ZONE 'ASIA/SEOUL');
INSERT INTO TIMETE VALUES(2, SYSDATE);
SELECT 
    ID,
    TO_CHAR(TEDATE, 'YYYY-MM-DD hh24:mm:ss')
 FROM TIMETE;

 INSERT INTO SHOP_ITEMS(
			ID,
			ITEMNAME,
			SELLERNAME,
			UPLOADTIME,
			INFOTEXT
		) VALUES (
			SHOP_ITEMS_SEQ.NEXTVAL,
			'aaaa',
			'aaaa',
			FROM_TZ(CAST(SYSDATE AS TIMESTAMP), 'GMT') AT TIME ZONE 'ASIA/SEOUL',
			'aaaa'
		);

SELECT * FROM SHOP_ITEMS ORDER BY ID;
SELECT * FROM SHOP_ITEMS
		WHERE ID = 1;

SELECT COUNT(*) FROM SHOP_ITEMS;

UPDATE SHOP_ITEMS SET PRICE = 20000, CATEGORY = 3 WHERE ID = 35;