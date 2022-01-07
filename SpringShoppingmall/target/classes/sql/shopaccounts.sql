CREATE TABLE SHOP_ACCOUNTS (
      ID NUMBER
    , USERNAME VARCHAR2(15 CHAR)
    , PASSWORD VARCHAR2(20 CHAR)
    , EMAIL VARCHAR2(40 CHAR)
    , USERTYPE VARCHAR2(6 CHAR) 
    , ADDRESS VARCHAR2(100 CHAR)
    , POSTNUMBER NUMBER
    , PHONENUMBER NUMBER
    , BIRTHDAY DATE
);

ALTER TABLE SHOP_ACCOUNTS ADD CONSTRAINT SHOP_ACCOUNTS_ID_PK PRIMARY KEY(ID);


CREATE SEQUENCE SHOP_ACCOUNTS_SEQ NOCACHE;

SELECT * FROM SHOP_ACCOUNTS ORDER BY ID;

SELECT * FROM SHOP_ACCOUNTS WHERE ID IN (2, 3);

INSERT INTO SHOP_ACCOUNTS(ID, USERNAME, PASSWORD, EMAIL, USERTYPE) 
  VALUES(
    SHOP_ACCOUNTS_SEQ.NEXTVAL, 
    'admin1',
    'admin1',
    'admin@admin.com',
    'admin');