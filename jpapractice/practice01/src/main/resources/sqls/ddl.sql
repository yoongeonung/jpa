drop table if exists MEMBER;
create table MEMBER
(
    MEMBER_ID bigint,
    NAME      varchar(50) not null,
    CITY      varchar(50),
    STREET    varchar(50),
    ZIPCODE   varchar(50),
    primary key (MEMBER_ID)
);


drop table if exists ORDERS;
create table ORDERS
(
    ORDER_ID  bigint,
    ORDERDATE date not null,
    STATUS    enum ('PENDING','ORDER'),
    MEMBER_ID bigint,
    primary key (ORDER_ID),
    foreign key (MEMBER_ID) references MEMBER(MEMBER_ID)
);


drop table if exists ITEM;
create table ITEM
(
    ITEM_ID       bigint,
    NAME          varchar(50) not null ,
    PRICE         int,
    STOCKQUANTITY int,
    primary key (ITEM_ID)
);

drop table if exists ORDER_ITEM;
create table ORDER_ITEM (
    ORDER_ITEM_ID bigint,
    ORDERPRICE int,
    COUNT int,
    ITEM_ID bigint,
    ORDER_ID bigint,
    primary key (ORDER_ITEM_ID),
    foreign key (ITEM_ID) references ITEM(ITEM_ID),
    foreign key (ORDER_ID) references ORDERS(ORDER_ID)
)
