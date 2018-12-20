 --게시판의 CONTENTS CLOB타입
--회원 테이블 생성
CREATE TABLE MEMBER(
    MEMBER_ID VARCHAR2(30 CHAR) PRIMARY KEY,
    MEMBER_PW VARCHAR2(4000) NOT NULL,
    MEMBER_NAME VARCHAR2(30 CHAR) NOT NULL,
    MEMBER_NICKNAME VARCHAR2(30 CHAR) UNIQUE NOT NULL,
    MEMBER_BIRTHDAY DATE,
    MEMBER_PHONENUMBER VARCHAR2(20 CHAR) NOT NULL,
    MEMBER_EMAIL VARCHAR2(50 CHAR) NOT NULL,
    MEMBER_ISWITHDRAWAL VARCHAR2(5 CHAR) NOT NULL CHECK(MEMBER_ISWITHDRAWAL IN('TRUE','FALSE')),
    MEMBER_JOIN_DATE DATE NOT NULL
);

--권한 테이블 생성
CREATE TABLE AUTHORITY(
    AUTHORITY_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    AUTHORITY_ROLE VARCHAR2(20 CHAR) NOT NULL, 
    CONSTRAINT AUTHORITY_PK PRIMARY KEY(AUTHORITY_ID, AUTHORITY_ROLE)
);

--메시지 테이블 생성
CREATE TABLE MESSAGE(
    MESSAGE_PK NUMBER(5) PRIMARY KEY,
    MESSAGE_SENDER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    MESSAGE_RECEIVER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    MESSAGE_TITLE VARCHAR2(50 CHAR) NOT NULL,
    MESSAGE_CONTENTS VARCHAR2(4000),
    MESSAGE_DATE DATE NOT NULL,
    MESSAGE_ISREAD VARCHAR2(5 CHAR) NOT NULL CHECK(MESSAGE_ISREAD IN('TRUE','FALSE')),
    MESSAGE_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(MESSAGE_ISDELETE IN('TRUE','FALSE'))
);

select * from CLASSROOM_INFO
update classroom_info set classroom_info_name = 'java' where CLASSROOM_INFO_name = '웹 프로그래밍'
commit
--클래스 정보 테이블 생성
CREATE TABLE CLASSROOM_INFO(
    CLASSROOM_CODE VARCHAR2(10 CHAR) PRIMARY KEY,
    CLASSROOM_INFO_NAME VARCHAR2(50 CHAR) NOT NULL,
    CLASSROOM_INFO_START_DATE DATE NOT NULL,
    CLASSROOM_INFO_END_DATE DATE NOT NULL,
    CLASSROOM_INFO_TEACHER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    CLASSROOM_INFO_CHAT_FILE VARCHAR2(50 CHAR) NOT NULL
);

--클래스 매퍼 테이블 생성 
CREATE TABLE CLASSROOM(
    CLASSROOM_USER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    CLASSROOM_CODE VARCHAR2(10 CHAR) NOT NULL REFERENCES CLASSROOM_INFO(CLASSROOM_CODE),
    CONSTRAINT CLASSROOM_PK PRIMARY KEY(CLASSROOM_USER_ID, CLASSROOM_CODE)
);


--공지사항 게시판 생성
CREATE TABLE NOTICE_BOARD(
    NOTICE_BOARD_PK NUMBER(5) PRIMARY KEY,
    NOTICE_BOARD_CLASSIFICATION VARCHAR2(10 CHAR) NOT NULL REFERENCES CLASSROOM_INFO(CLASSROOM_CODE),
    NOTICE_BOARD_TITLE VARCHAR2(50 CHAR) NOT NULL,
    NOTICE_BOARD_WRITER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    NOTICE_BOARD_WRITE_DATE DATE NOT NULL,
    NOTICE_BOARD_CONTENTS CLOB NOT NULL, 
    NOTICE_BOARD_VIEWS NUMBER(5) NOT NULL,
    NOTICE_BOARD_ATTACHMENT VARCHAR2(50 CHAR),
    NOTICE_BOARD_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(NOTICE_BOARD_ISDELETE IN('TRUE','FALSE'))
);

--댓글있는 게시판 테이블 생성
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

--좋아요 테이블 생성
--좋아요 -> ISUP = TRUE
--싫어요 -> ISUP = FALSE
--취소 -> DELETE 
CREATE TABLE UPDOWN(
    UPDOWN_PK NUMBER(5) PRIMARY KEY,
    UPDOWN_REPLY_BOARD_PK NOT NULL REFERENCES REPLY_BOARD(REPLHASHTAG VALUES (HASHTAG_SEQ.NEXTVAL, (SELECT MAX(REPLY_BOARD_PK) FROM REPLY_BOARD), NULL, '#해시태그4', 'FALSE');Y_BOARD_PK),
    UPDOWN_USER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    UPDOWN_ISUP VARCHAR2(5 CHAR) NOT NULL CHECK(UPDOWN_ISUP IN('TRUE','FALSE'))
);

--신고 테이블 생성
CREATE TABLE REPORT(
    REPORT_PK NUMBER(5) PRIMARY KEY,
    REPORT_REPLY_BOARD_PK NUMBER(5) NOT NULL REFERENCES REPLY_BOARD(REPLY_BOARD_PK),
    REPORT_REPORTER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    REPORT_DATE DATE NOT NULL,
    REPORT_PURPOSE VARCHAR2(4000) NOT NULL,
    REPORT_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(REPORT_ISDELETE IN('TRUE','FALSE'))
);

--포트폴리오 테이블 생성 
--학생 한명당 하나
CREATE TABLE PORTFOLIO(
    PORTFOLIO_USER_ID VARCHAR2(30 CHAR) PRIMARY KEY REFERENCES MEMBER(MEMBER_ID),
    PORTFOLIO_MAIN_IMAGE VARCHAR2(50 CHAR),
    PORTFOLIO_MAIN_TITLE VARCHAR2(50 CHAR) NOT NULL,
    PORTFOLIO_VISIBILITY VARCHAR2(5 CHAR) NOT NULL CHECK(PORTFOLIO_VISIBILITY IN('TRUE','FALSE')),
    PORTFOLIO_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(PORTFOLIO_ISDELETE IN('TRUE','FALSE'))
);

--포트폴리오 상세
--학생 한명이 여러개
CREATE TABLE PORTFOLIO_DETAIL(
    PORTFOLIO_DETAIL_PK NUMBER(5) PRIMARY KEY,
    PORTFOLIO_DETAIL_USER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    PORTFOLIO_DETAIL_PROJECT_NAME VARCHAR2(50 CHAR) NOT NULL,
    PORTFOLIO_DETAIL_PROJECT_IMAGE VARCHAR2(50 CHAR),
    PORTFOLIO_DETAIL_DESCRIPTION CLOB NOT NULL,
    PORTFOLIO_DETAIL_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(PORTFOLIO_DETAIL_ISDELETE IN('TRUE','FALSE'))
);

--해쉬태그 테이블 생성
CREATE TABLE HASHTAG(
    HASHTAG_PK NUMBER(5) PRIMARY KEY,
    HASHTAG_REPLY_BOARD_PK NUMBER(5) REFERENCES REPLY_BOARD(REPLY_BOARD_PK),
    HASHTAG_PORTFOLIO_DETAIL_PK NUMBER(5) REFERENCES REPLY_BOARD(REPLY_BOARD_PK),
    HASHTAG_NAME VARCHAR2(50 CHAR) NOT NULL,
    HASHTAG_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(HASHTAG_ISDELETE IN('TRUE','FALSE'))
);

----------시퀀스
--테이블명_SEQ

CREATE SEQUENCE MESSAGE_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE NOTICE_BOARD_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE REPLY_BOARD_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE UPDOWN_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE REPORT_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE HASHTAG_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE PORTFOLIO_DETAIL_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE;

ALTER TABLE REPLY_BOARD MODIFY (REPLY_BOARD_TITLE NULL);
ALTER TABLE REPLY_BOARD MODIFY (REPLY_BOARD_VIEWS NULL);

ALTER TABLE CLASSROOM_INFO ADD (CLASSROOM_INFO_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(CLASSROOM_INFO_ISDELETE IN('TRUE','FALSE')));

----------------------------------
INSERT INTO MEMBER VALUES ('kim', '1234', '김호수', '혼또호', '1994-04-01', '01034039195', 'ghtnfhf94@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('lake', 'pw1234', '김호수', '호또혼', '1995-01-01', '01011112222', 'lake@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('mark', '1234', '최동철', '코딩봇', '1991-01-01', '112', 'mark@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('pg13', '1234', '남시욱', '폴조지', '1992-01-01', '119', 'pg13@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('admin', 'admin', '관리자', '관리자', '2018-12-14', '1633', 'jang8253@daum.net', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('stream', 'pw4321', '김내천', '내또혼', '1995-11-01', '010-1111-2222', 'stream@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('ocean', 'pw1002', '김바다', '바또혼', '1995-11-11', '010-2125-4321', 'ocean@gmail.com', 'FALSE', SYSDATE);


insert into message values (MESSAGE_SEQ.NEXTVAL, 'lake', 'admin', '힘들어요', '나는 코딩이 실허요', sysdate, 'FALSE', 'FALSE');
insert into message values (MESSAGE_SEQ.nextval, 'stream', 'admin', '힘들어요', '나는 코딩이 실허요', sysdate, 'FALSE', 'FALSE');
insert into message values (MESSAGE_SEQ.nextval, 'ocean', 'admin', '힘들어요', '나는 코딩이 실허요', sysdate, 'FALSE', 'FALSE');
insert into message values (MESSAGE_SEQ.nextval, 'mark', 'admin', '힘들어요', '나는 코딩이 실허요', sysdate, 'FALSE', 'FALSE');
insert into message values (MESSAGE_SEQ.nextval, 'pg13', 'admin', '힘들어요', '안돼안돼', sysdate, 'FALSE', 'FALSE');

select * from member
SELECT * FROM MESSAGE
SELECT * FROM CLASSROOM_INFO
SELECT * FROM REPLY_BOARD
COMMIT

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', NULL, '게시글제목', 'lake', SYSDATE, '게시판내용', '0', NULL, 'FALSE');
INSERT INTO HASHTAG VALUES (HASHTAG_SEQ.NEXTVAL, (SELECT MAX(REPLY_BOARD_PK) FROM REPLY_BOARD), NULL, '#해시태그1', 'FALSE');
INSERT INTO HASHTAG VALUES (HASHTAG_SEQ.NEXTVAL, (SELECT MAX(REPLY_BOARD_PK) FROM REPLY_BOARD), NULL, '#해시태그2', 'FALSE');
INSERT INTO HASHTAG VALUES (HASHTAG_SEQ.NEXTVAL, (SELECT MAX(REPLY_BOARD_PK) FROM REPLY_BOARD), NULL, '#해시태그3', 'FALSE');

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', NULL, 'tech게시판 제목2', 'lake', SYSDATE, 'tech게시판 내용내용22', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', NULL, 'tech게시판 제목3', 'lake', SYSDATE, 'tech게시판 내용내용33', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', NULL, 'tech게시판 제목4', 'lake', SYSDATE, 'tech게시판 내용내용44', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', null, 'tech게시판 제목', 'lake', SYSDATE, 'tech게시판 내용내용', '0', NULL, 'FALSE');

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', NULL, 'study게시판 제목2', 'lake', SYSDATE, 'study게시판 내용내용22', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', NULL, 'study게시판 제목3', 'lake', SYSDATE, 'study게시판 내용내용33', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', NULL, 'study게시판 제목4', 'lake', SYSDATE, 'study게시판 내용내용44', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', null, 'study게시판 제목', 'lake', SYSDATE, 'study게시판 내용내용', '0', NULL, 'FALSE');

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', NULL, 'lib게시판 제목2', 'lake', SYSDATE, 'lib게시판 내용내용22', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', NULL, 'lib게시판 제목3', 'lake', SYSDATE, 'lib게시판 내용내용33', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', NULL, 'lib게시판 제목4', 'lake', SYSDATE, 'lib게시판 내용내용44', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', null, 'lib게시판 제목', 'lake', SYSDATE, 'lib게시판 내용내용', '0', NULL, 'FALSE');

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', '4', 'tech게시판댓글', 'lake', SYSDATE, 'tech게시판댓글', NULL, 'lake', 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', '4', NULL, 'stream', SYSDATE, '댓글내용용', NULL, 'ocean', 'FALSE');

INSERT INTO REPORT VALUES (REPORT_SEQ.NEXTVAL,1,'lake',sysdate,'욕설','FALSE');
INSERT INTO REPORT VALUES (REPORT_SEQ.NEXTVAL,6,'lake',sysdate,'도배','FALSE');
INSERT INTO REPORT VALUES (REPORT_SEQ.NEXTVAL,10,'lake',sysdate,'상업적 글','FALSE');
INSERT INTO REPORT VALUES (REPORT_SEQ.NEXTVAL,14,'lake',sysdate,'기타','FALSE');
INSERT INTO REPORT VALUES (REPORT_SEQ.NEXTVAL,1,'mark',sysdate,'욕설','FALSE');
INSERT INTO REPORT VALUES (REPORT_SEQ.NEXTVAL,1,'stream',sysdate,'욕설','FALSE');

update Message
set MESSAGE_ISDELETE='FALSE'