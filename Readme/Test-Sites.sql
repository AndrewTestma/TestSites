CREATE DATABASE IF NOT EXISTS Test-Sites DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

-- tsproduct
CREATE TABLE IF NOT EXISTS tsproduct(
	tsproductid INTEGER(3) AUTO_INCREMENT,
	tsname VARCHAR(100) NOT NULL,
	tsurl varchar(100) NOT NULL,
	tsdburl varchar(100) not null,
	tsdbname varchar(100) not null,
	tspassword varchar(100) not null,
	PRIMARY KEY (tsproductid)
) ENGINE=InnoDB;
-- tsmodule
create table if not exists tsmodule(
  tsmoduleid integer(3) auto_increment,
  tsproductid integer(3) not null,
  tsame varchar(100) not null,
  primary key (tsmoduleid)
) engine=innodb;
-- tsUItestcase
create table if not exists tsuitestcase(
  tsuitestcaseid integer(3) auto_increment,
  tsnum varchar(100) not null,
  tsproductid integer(3) not null,
  tsmodulename varchar(100) not null,
  tsautostepsname varchar(100) not null,
  tsfrontcase varchar(100) default null,
  tsexpected varchar(100) not null,
  tsverificationid varchar(100) not null,
  tsverificationcontent varchar(100)not null,
  tsdebug TINYINT(0),
  tsgrade integer(3) default 5,
  tscreatetime timestamp default current_timestamp,
  tsregress tinyint(0),
  tssmoke tinyint(0),
  tscreator varchar(100) not null,
  primary key (tsuitestcaseid)
  -- foreign key(tsUItestcaseproductid)references ts_product(tsproductid),
  -- foreign key(tsUItestcasemoduleid)references ts_module(tsmoduleid)
) engine=innodb;
-- tsautosteps
create table if not exists tsautosteps(
	tsautostepsid integer(3) auto_increment,
	tsname varchar(100) not null,
	tssearchid varchar(100) not null,
	tssearchcontent varchar(100) not null,
	tsexecutionid varchar(100) not null,	
	tsexecutioncontent varchar(100) default null,
	tsiframe varchar(100) default null,
	tswaittime integer(4) default null,
	tsproductid integer(3) default null,
	tsmodulename varchar(100) default null,
	tspublic tinyint(0),
	tsremarks varchar(100) default null,
	tscreatetime timestamp default current_timestamp,
	tscreator varchar(100) not null,
	primary key (tsautostepsid)
) engine=innodb;
-- tsverification
create table if not exists tsverification (
	tsverificationid integer(3) auto_increment,
	tsvalue varchar(100) not null,
	primary key (tsverificationid)
) engine=innodb;
-- tssearch
create table if not exists tssearch (
	tssearchid integer(3) auto_increment,
	tsvalue varchar(100) not null,
	primary key (tssearchid)
) engine=innodb;
-- tsexecution
create table if not exists tsexecution (
	tsexecutionid integer(3) auto_increment,
	tsvalue varchar(100) not null,
	primary key (tsexecutionid)
) engine=innodb;
-- tsoperatingenv
create table if not exists tsoperatingenv(
  tsoperatingenvid integer(3) auto_increment,
  tsname varchar(100) not null,
  tsurl varchar(100) not null,
  tsdriver varchar(100) not null,
  tsdirverpath varchar(100) not null,
  primary key(tsoperatingenvid)
) engine=innodb;
-- tsuser
create table if not exists tsuser(
	tsuserid integer(3) auto_increment,
	tsloginname  varchar(100) not null,
	tsusername  varchar(100) not null,
	tspassword varchar(100) not null,
	tsemail varchar(100) not null,
	tsidentity integer(3) not null,
	tspermissionname varchar(100) default null,
	primary key(tsuserid)
)engine=innodb;
INSERT INTO tsuser (tsloginname, tsusername, tspassword, tsemail, tsidentity, tspermissionname) 
VALUES ('andrew', 'mjw', '123456', '425780724@qq.com', 1, '');
-- tspermissions
create table if not exists tspermissions(
	tspermissionsid integer(3) auto_increment,
	tsname varchar(100) not null,
	tsproduct tinyint(0),
	tstestcase tinyint(0),
	tsscenes tinyint(0),
	tsreserved1 tinyint(0),
	tsreserved2 tinyint(0),
	tsreserved3 tinyint(0),
	primary key(tspermissionsid)
)engine=innodb;