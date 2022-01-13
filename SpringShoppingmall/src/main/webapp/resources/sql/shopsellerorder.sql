CREATE TABLE SHOP_SELLER_ORDER(
    ID NUMBER,
    ORDERID NUMBER,
    SELLERID NUMBER,
    ORDERNO VARCHAR2(30 CHAR),
    ITEMLIST VARCHAR2(1000 CHAR)
);

ALTER TABLE SHOP_SELLER_ORDER ADD CONSTRAINT SHOP_SELLER_ORDER_ID_PK PRIMARY KEY(ID);
ALTER TABLE SHOP_SELLER_ORDER ADD CONSTRAINT 
    SHOP_SELLER_ORDER_SELLERID_FK FOREIGN KEY(SELLERID) REFERENCES SHOP_ACCOUNTS(ID);
ALTER TABLE SHOP_SELLER_ORDER ADD CONSTRAINT 
    SHOP_SELLER_ORDER_ORDERID_FK FOREIGN KEY(ORDERID) REFERENCES SHOP_ORDER(ID);

CREATE SEQUENCE SHOP_SELLER_ORDER_SEQ NOCACHE;

SELECT * FROM SHOP_SELLER_ORDER;

SELECT 
    A.SELLERID,
    B.ID,
    A.ORDERNO,
    A.ITEMLIST,
    B.BUYERNAME,
    B.PACKAGENAME,
    B.ADDRESS,
    LPAD(B.POSTNUMBER, 5,'0') AS POSTNUMBER,
    LPAD(B.PHONENUMBER, 10, '0') AS PHONENUMBER
 FROM SHOP_SELLER_ORDER A, SHOP_ORDER B
    WHERE A.ORDERID = B.ID 
    AND A.SELLERID = 1;

/*
ResultMap
<resultMap type="orderseller" id="OrderSellerMap">
    <id column="ID" property="id" />
    <result column="ORDERID" property="orderid" />
    <result column="SELLERID" property="sellerid" />
    <result column="ORDERNO" property="orderno" />
    <result column="ITEMLIST" property="itemlist" />
</resultMap>

    <resultMap type="order" id="OrderMap">
        <id column="ID" property="id" />
        <result column="ORDERLIST" property="orderlist" />
        <result column="BUYERNAME" property="buyername" />
        <result column="PACKAGENAME" property="packagename" />
        <result column="ADDRESS" property="address" />
        <result column="POSTNUMBER" property="postnumber" />
        <result column="PAYMETHOD" property="paymethod" />
        <result column="PRICE" property="price" />
        <result column="ORDERNO" property="orderno" />
        <result column="ORDERSTATE" property="orderstate" />
        <result column="PACKAGENO" property="packageno" />
    </resultMap>

<resultMap type="OrderAndSeller" id="OrderAndSellerMap">
    <collection property="orderseller" result="OrderSellerMap" />
    <collection property="order" result="OrderMap" />
</resultMap>
*/