CREATE TABLE MESSAGE(
    MESSAGE_PK NUMBER(5) PRIMARY KEY,
    MESSAGE_SENDER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    MESSAGE_RECEIVER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    MESSAGE_TITLE VARCHAR2(50 CHAR) NOT NULL,
    MESSAGE_CONTENTS VARCHAR2(4000),
    MESSAGE_DATE DATE NOT NULL,
    MESSAGE_ISREAD VARCHAR2(5 CHAR) NOT NULL CHECK(MESSAGE_ISREAD IN('TRUE','FALSE')),
    MESSAGE_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(MESSAGE_ISREAD IN('TRUE','FALSE'))
);

CREATE TABLE REPLY_BOARD(
    REPLY_BOARD_PK NUMBER(5) PRIMARY KEY,
    REPLY_BOARD_CLASSIFICATION VARCHAR2(10 CHAR) NOT NULL,
    REPLY_BOARD_REPLY_NO NUMBER(5) REFERENCES REPLY_BOARD(REPLY_BOARD_PK),
    REPLY_BOARD_TITLE VARCHAR2(50 CHAR) NOT NULL,
    REPLY_BOARD_WRITER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    REPLY_BOARD_WRITE_DATE DATE NOT NULL,
    REPLY_BOARD_CONTENTS CLOB NOT NULL,
    REPLY_BOARD_VIEWS NUMBER(5) NOT NULL,
    REPLY_BOARD_MENTION VARCHAR2(30 CHAR) REFERENCES MEMBER(MEMBER_ID),
    REPLY_BOARD_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(REPLY_BOARD_ISDELETE IN('TRUE','FALSE'))
);

--Ŭ���� ���� ���̺� ����
CREATE TABLE CLASSROOM_INFO(
    CLASSROOM_CODE VARCHAR2(10 CHAR) PRIMARY KEY,										--Ŭ�����ڵ�
    CLASSROOM_INFO_NAME VARCHAR2(50 CHAR) NOT NULL,										--Ŭ������
    CLASSROOM_INFO_START_DATE DATE NOT NULL,											--Ŭ����������
    CLASSROOM_INFO_END_DATE DATE NOT NULL,												--Ŭ����������
    CLASSROOM_INFO_TEACHER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),	--����ID
    CLASSROOM_INFO_CHAT_FILE VARCHAR2(50 CHAR) NOT NULL									--Ŭ������ ä�ð�������
);

--Ŭ���� ���� ���̺� ���� 
CREATE TABLE CLASSROOM(
    CLASSROOM_USER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),			--Ŭ���� UserId
    CLASSROOM_CODE VARCHAR2(10 CHAR) NOT NULL REFERENCES CLASSROOM_INFO(CLASSROOM_CODE),--Ŭ�����ڵ�
    CONSTRAINT CLASSROOM_PK PRIMARY KEY(CLASSROOM_USER_ID, CLASSROOM_CODE)				--Ŭ����PK
);


update message set MESSAGE_ISDELETE='FALSE';
update message set MESSAGE_ISREAD='FALSE';
update REPLY_BOARD set REPLY_BOARD_ISDELETE='FALSE';

select * from message;
select * from member;
select * from REPLY_BOARD;
select * from CLASSROOM;
select * from CLASSROOM_INFO;

insert into CLASSROOM_INFO values('A01A','189�� �����߰���',to_date('2018/08/26','yyyy/mm/dd'),to_date('2019/08/04','yyyy/mm/dd'),'heejung','189.txt');
insert into CLASSROOM values('aa','A01A');
insert into CLASSROOM values('bb','A01A');

insert into MEMBER values ('aa','1234','aa','aa',sysdate,'010-000-0000','aa@gmail.com','FALSE',sysdate);
insert into MEMBER values ('bb','1234','bb','bb',sysdate,'010-000-0000','bb@gmail.com','FALSE',sysdate);
insert into MEMBER values ('cc','1234','cc','cc',sysdate,'010-000-0000','cc@gmail.com','FALSE',sysdate);
insert into MEMBER values ('heejung','1234','heejung','heejung',sysdate,'010-000-0000','jang8354@gmail.com','FALSE',sysdate);

insert into message values(MESSAGE_SEQ.nextval,'bb','aa','�Ƕ�','����Ƕ�Ƕ�Ƕ�...',sysdate,'FALSE','FALSE');
insert into message values(MESSAGE_SEQ.nextval,'bb','aa','�ȴ�','����Ƕ�Ƕ�Ƕ�...',sysdate,'FALSE','FALSE');
insert into message values(MESSAGE_SEQ.nextval,'bb','aa','����','����Ƕ�Ƕ�Ƕ�...',sysdate,'FALSE','FALSE');

insert into REPLY_BOARD values(REPLY_BOARD_SEQ.nextval,'����Խ���',0,'����','aa',sysdate,'������ ����ִ� �κ��Դϴ�...��.��.',1,'aa','FALSE');
insert into REPLY_BOARD values(REPLY_BOARD_SEQ.nextval,'Q&A',0,'����','bb',sysdate,'��.��.��',1,'bb','FALSE');
insert into REPLY_BOARD values(REPLY_BOARD_SEQ.nextval,'Q&A',0,'����','bb',sysdate,'��.��.��',1,'bb','FALSE');
insert into REPLY_BOARD values(REPLY_BOARD_SEQ.nextval,'����Խ���',0,'ȣȣ','bb',sysdate,'ȣȣȣȣ',1,'bb','FALSE');

drop table MESSAGE;

update REPLY_BOARD set REPLY_BOARD_REPLY_NO='';

select COUNT(DECODE(MESSAGE_ISREAD,'FALSE','1')) from MESSAGE;
select count(case when MESSAGE_ISREAD='FALSE' then 1 end) as unRead from MESSAGE;

select count(case when MESSAGE_ISREAD='FALSE' then 1 end) from MESSAGE where MESSAGE_RECEIVER_ID='aa';

commit