DROP TABLE IF EXISTS TASK;

create table TASK(
  ID int not null AUTO_INCREMENT,
  TITLE varchar(100) not null,
  IS_COMPLETED boolean,
  PRIMARY KEY (ID)
);