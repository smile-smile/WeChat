create table Account(
	userId varchar2(20) primary key,
	password varchar2(20) not null,
	phone number(11) constraint uk_user_phone unique,
	email varchar2(20) not null,
	name varchar2(20),
	nickName varchar2(20),
	sex char(4),
	birthday date,
	remark1 varchar2(20),
	remark2 varchar2(20),
	remark3 varchar2(20)
)
alter table Account modify phone not null

create table Friends(
	myselfId varchar2(20),
	friendId varchar2(20),
	remarkName varchar2(20),
	remark1 varchar2(20),
	remark2 varchar2(20),
	remark3 varchar2(20),
	primary key(myselfId, friendId)
)
alter table Friends add constraint fk_friend_account_myselfId foreign key(myselfId) references Account(userId);
alter table Friends add constraint fk_friend_account_friendId foreign key(friendId) references Account(userId);

