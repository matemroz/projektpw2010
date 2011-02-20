
    drop table Area cascade constraints;

    drop table AreaPoint cascade constraints;

    drop table Children cascade constraints;

    drop table Family cascade constraints;

    drop table Marker cascade constraints;

    drop table Parent cascade constraints;

    drop table ParentSettings cascade constraints;

    drop sequence AREA_POINT_SEQUENCE;

    drop sequence AREA_SEQUENCE;

    drop sequence CHILDREN_SEQUENCE;

    drop sequence FAMILY_SEQUENCE;

    drop sequence MARKER_SEQUENCE;

    drop sequence PARENT_SEQUENCE;

    drop sequence PARENT_SETTINGS_SEQUENCE;

    create table Area (
        idArea number(19,0) not null,
        idAreaPoint number(19,0) not null,
        idChild number(19,0) not null,
        primary key (idArea)
    );

    create table AreaPoint (
        idAreaPoint number(19,0) not null,
        idArea number(19,0) not null,
        x float not null,
        y float not null,
        primary key (idAreaPoint)
    );

    create table Children (
        idChild number(19,0) not null,
        dateOfBirth timestamp not null,
        idArea number(19,0) not null,
        imei varchar2(50 char) not null,
        shortedPassword varchar2(50 char) not null,
        name varchar2(50 char) not null,
        primary key (idChild)
    );

    create table Family (
        idFamily number(19,0) not null,
        idChild number(19,0) not null,
        idParent number(19,0) not null,
        primary key (idFamily)
    );

    create table Marker (
        idMarker number(19,0) not null,
        datetime timestamp not null,
        x float not null,
        y float not null,
        primary key (idMarker)
    );

    create table Parent (
        idParent number(19,0) not null,
        email varchar2(100 char) not null unique,
        name varchar2(50 char) not null,
        shortedPassword varchar2(50 char) not null,
        primary key (idParent)
    );

    create table ParentSettings (
        idParentSettings number(19,0) not null,
        idChild number(19,0) not null,
        idParent number(19,0) not null,
        stepTime float not null,
        primary key (idParentSettings)
    );

    alter table Area 
        add constraint FK1F44AD62DF6B66 
        foreign key (idArea) 
        references AreaPoint;

    alter table Children 
        add constraint FK66C4C21FA2A0CA53 
        foreign key (idChild) 
        references Area;

    alter table Family 
        add constraint FK7CFD4784FBA11D4 
        foreign key (idParent) 
        references Parent;

    alter table Family 
        add constraint FK7CFD4784262C5D45 
        foreign key (idChild) 
        references Children;

    alter table Marker 
        add constraint FK88F1805AC84C9D79 
        foreign key (idMarker) 
        references Children;

    alter table Parent 
        add constraint FK8E0FF4CA4BF97317 
        foreign key (idParent) 
        references ParentSettings;

    create sequence AREA_POINT_SEQUENCE;

    create sequence AREA_SEQUENCE;

    create sequence CHILDREN_SEQUENCE;

    create sequence FAMILY_SEQUENCE;

    create sequence MARKER_SEQUENCE;

    create sequence PARENT_SEQUENCE;

    create sequence PARENT_SETTINGS_SEQUENCE;
