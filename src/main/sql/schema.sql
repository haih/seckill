-- db init script

create DATABASE seckill;

use seckill;

CREATE TABLE seckill( 
	seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT 'production stock id',
	name VARCHAR(120) NOT NULL COMMENT 'production name',
	number int NOT NULL COMMENT 'stock number',
	start_time timestamp NOT NULL COMMENT 'seckill start_time',
	end_time timestamp not null comment 'seckill end_time',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
	PRIMARY KEY (seckill_id),
    key idx_start_time(start_time),
	key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='seckill table'

INSERT into 
	seckill(name,number)
values 
	('1000 sec kill iphone6',100),
	('1000 sec kill iphone5',200),
	('1000 sec kill iphone4',300),
	('1000 sec kill xiaomi4',500)；
-- init data

-- seckill succeed detail information
-- user login and authentication related info








