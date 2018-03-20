CREATE DATABASE IF NOT EXISTS Test-Sites DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
-- tsproduct
CREATE TABLE IF NOT EXISTS tsproduct(
	tsproductid INTEGER(11) AUTO_INCREMENT,
	tsname VARCHAR(100) NOT NULL,
	tsurl varchar(100) NOT NULL,
	tsdburl varchar(100) not null,
	tsdbname varchar(100) not null,
	tsusername varchar(100) not null,
	tspassword varchar(100) not null,
	PRIMARY KEY (tsproductid)
) ENGINE=InnoDB;
-- tsmodule
create table if not exists tsmodule(
  tsmoduleid integer(11) auto_increment,
  tsproductid integer(11) not null,
  tsame varchar(100) not null,
  primary key (tsmoduleid)
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
-- tsbusiness
create table if not exists tsbusiness(
	tsbusinessid integer(11) auto_increment,
	tsname varchar(100) not null,
	tsproductid integer(11) not null,
	tssmoke integer(11) default 0,
	tsregress integer(11) default 0,
	tsorder integer(11) default 5,
	primary key(tsbusinessid)
)engine=innodb;
-- tsbusinesscase
create table if not exists tsbusinesscase(
	tsbusinesscaseid integer(11) auto_increment,
	tsbusinessid integer(11) not null,
	tsuitestcaseid integer(11) not null,
	tsorder integer(11) default 0,
	primary key(tsbusinesscaseid) 
)engine=innodb;
-- tsuitestcase
create table if not exists tsuitestcase(
  tsuitestcaseid integer(11) auto_increment,
  tsname varchar(100) unique not null,
  tsproductid integer(11) not null,
  tsmodulename varchar(100) not null, 
  tsgrade integer(11) default 5,
  tsresultid integer(11) DEFAULT null,
  tscommon integer(11) default 0,
  tscreatetime timestamp default current_timestamp,    
  tscreator varchar(100) not null,
  primary key (tsuitestcaseid)
  -- foreign key(tsUItestcaseproductid)references ts_product(tsproductid),
  -- foreign key(tsUItestcasemoduleid)references ts_module(tsmoduleid)
) engine=innodb;
-- tsautosteps
create table if not exists tsautosteps(
	tsautostepsid integer(11) auto_increment,
	tsautostepsname varchar(100) unique not null,
	tsproductid integer(11) not null,
	tsmodulename varchar(100) not null,
	tsselecttype varchar(100) not null,
	tsselectcontent varchar(100) not null,
	tsactiontype varchar(100) not null,	
	tsactioncontent varchar(100) default null,
	tsframepath varchar(100) default null,
	tswait integer(4) default null,
	tsverificationtype varchar(100) default null,
	tsverificationcontent varchar(2048) default null,
	tscommon integer(11) default null,
	tssuccess integer(11) default null,
	tsremarks varchar(100) default null,
	tscreatetime timestamp default current_timestamp,
	tscreator varchar(100) not null,
	primary key (tsautostepsid)
) engine=innodb;
-- tscasesteps
create table if not exists tscasesteps(
	tscasestepsid integer(11) auto_increment,
	tsuitestcaseid integer(11) not null,
	tsautostepsid integer(11) not null,
	tsorder integer(11) default 0,
	primary key(tscasestepsid) 
)engine=innodb;
-- tsresult
create table if not exists tsresult(
	tsresultid integer(11) auto_increment,
	tsproductid INTEGER (11) not null,
	tsbusinessid INTEGER (11) not null,
	tstotaltime integer(11) default null,
	tstotalsteps integer(11) default null,
	tsrunsteps integer(11) default null,
	tsresult integer(11) default null,
	tscount integer(11) default null,
	tsexecutive varchar(100) default null,
	tsexecutiontime timestamp default current_timestamp,
	primary key(tsresultid)
)engine=innodb;
-- tsuser
create table if not exists tsuser(
  tsuserid integer(11) auto_increment,
  tsname varchar(100) not null,
  tspassword varchar(100) not null,
  primary key(tsuserid)
)engine=innodb;
-- tsuserproduct
create table if not exists tsuserproduct(
  tsuserproductid integer(11) auto_increment,
  tsuserid integer(11) not null,
  tsproductid INTEGER(11) not null,
  primary key(tsuserproductid)
)engine=innodb;