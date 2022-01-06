CREATE TABLE SHOP_WISHLIST(
    ID NUMBER,
    USERID NUMBER,
    USERNAME VARCHAR2(15 CHAR),
    WISHLIST VARCHAR2(4000)
);

ALTER TABLE SHOP_WISHLIST ADD CONSTRAINT SHOP_WISHLIST_ID_PK PRIMARY KEY(ID);


CREATE SEQUENCE SHOP_WISHLIST_SEQ NOCACHE;

SELECT * FROM SHOP_WISHLIST ORDER BY ID;

INSERT INTO SHOP_WISHLIST VALUES(SHOP_WISHLIST_SEQ.NEXTVAL, 1, 'aaaa', '2_3');

UPDATE SHOP_WISHLIST 