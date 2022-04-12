create table T_PERS
(
    ID   INTEGER identity
        constraint T_PERS_PK
        primary key,
    NAME VARCHAR(60),
    URL  VARCHAR(255)
);

comment on table T_PERS is '权限表';

create unique index T_PERS_ID_UINDEX
    on T_PERS (ID);

create table T_ROLE
(
    ID   INTEGER identity
        constraint T_ROLE_PK
        primary key,
    NAME VARCHAR(60)
);

comment on table T_ROLE is ' 角色表';

create unique index T_ROLE_ID_UINDEX
    on T_ROLE (ID);

create table T_ROLE_PERMS
(
    ID       INTEGER identity
        constraint T_ROLE_PERMS_PK
        primary key,
    ROLEID   INTEGER,
    PERMISID INTEGER
);

create unique index T_ROLE_PERMS_ID_UINDEX
    on T_ROLE_PERMS (ID);

create table T_USER
(
    ID       INTEGER identity
        constraint T_USER_PK
        primary key,
    USERNAME VARCHAR(40),
    PASSWORD VARCHAR(40),
    SALT     VARCHAR(255)
);

create unique index T_USER_ID_UINDEX
    on T_USER (ID);

create table T_USER_ROLE
(
    ID     INTEGER identity
        constraint T_USER_ROLE_PK
        primary key,
    USERID INTEGER,
    ROLEID INTEGER
);

create unique index T_USER_ROLE_ID_UINDEX
    on T_USER_ROLE (ID);