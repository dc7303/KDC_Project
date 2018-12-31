--3.0변경사항
--VISIT TABLE 추가
--VISIT_SEQ 추가

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

--클래스 정보 테이블 생성
CREATE TABLE CLASSROOM_INFO(
    CLASSROOM_CODE VARCHAR2(32) PRIMARY KEY,
    CLASSROOM_INFO_NAME VARCHAR2(50 CHAR) NOT NULL,
    CLASSROOM_INFO_START_DATE DATE NOT NULL,
    CLASSROOM_INFO_END_DATE DATE NOT NULL,
    CLASSROOM_INFO_TEACHER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    CLASSROOM_INFO_CHAT_FILE VARCHAR2(200) NOT NULL,
    CLASSROOM_INFO_DATE DATE NOT NULL,
    CLASSROOM_INFO_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(CLASSROOM_INFO_ISDELETE IN('TRUE','FALSE'))
);

--클래스 매퍼 테이블 생성 
CREATE TABLE CLASSROOM(
    CLASSROOM_USER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    CLASSROOM_CODE VARCHAR2(32) NOT NULL REFERENCES CLASSROOM_INFO(CLASSROOM_CODE),
    CLASSROOM_ISCURRENT VARCHAR2(5 CHAR) NOT NULL CHECK(CLASSROOM_ISCURRENT IN('TRUE','FALSE')),
    CONSTRAINT CLASSROOM_PK PRIMARY KEY(CLASSROOM_USER_ID, CLASSROOM_CODE)
);

--공지사항 게시판 생성
CREATE TABLE NOTICE_BOARD(
    NOTICE_BOARD_PK NUMBER(5) PRIMARY KEY,
    NOTICE_BOARD_CLASSIFICATION VARCHAR2(50 CHAR) NOT NULL,
    NOTICE_BOARD_CLASSROOM_CODE VARCHAR2(10 CHAR) REFERENCES CLASSROOM_INFO(CLASSROOM_CODE),
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
    REPLY_BOARD_TITLE VARCHAR2(50 CHAR),
    REPLY_BOARD_WRITER_ID VARCHAR2(30 CHAR) NOT NULL REFERENCES MEMBER(MEMBER_ID),
    REPLY_BOARD_WRITE_DATE DATE NOT NULL,
    REPLY_BOARD_CONTENTS CLOB NOT NULL,
    REPLY_BOARD_VIEWS NUMBER(5),
    REPLY_BOARD_MENTION VARCHAR2(30 CHAR) REFERENCES MEMBER(MEMBER_ID),
    REPLY_BOARD_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(REPLY_BOARD_ISDELETE IN('TRUE','FALSE'))
);

--좋아요 테이블 생성
--좋아요 -> ISUP = TRUE
--싫어요 -> ISUP = FALSE
--취소 -> DELETE 
CREATE TABLE UPDOWN(
    UPDOWN_PK NUMBER(5) PRIMARY KEY,
    UPDOWN_REPLY_BOARD_PK NOT NULL REFERENCES REPLY_BOARD(REPLY_BOARD_PK),
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
    PORTFOLIO_DETAIL_DATE DATE NOT NULL,
    PORTFOLIO_DETAIL_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(PORTFOLIO_DETAIL_ISDELETE IN('TRUE','FALSE'))
);

--해쉬태그 테이블 생성
CREATE TABLE HASHTAG(
    HASHTAG_PK NUMBER(5) PRIMARY KEY,
    HASHTAG_REPLY_BOARD_PK NUMBER(5) REFERENCES REPLY_BOARD(REPLY_BOARD_PK),
    HASHTAG_PORTFOLIO_DETAIL_PK NUMBER(5) REFERENCES PORTFOLIO_DETAIL(PORTFOLIO_DETAIL_PK),
    HASHTAG_NAME VARCHAR2(50 CHAR) NOT NULL,
    HASHTAG_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(HASHTAG_ISDELETE IN('TRUE','FALSE'))
);

--2.0추가 테이블
--캘린더 테이블 생성
CREATE TABLE CALENDAR(
    CALENDAR_PK NUMBER(5) PRIMARY KEY,
    CLASSROOM_CODE VARCHAR2(10 CHAR) NOT NULL REFERENCES CLASSROOM_INFO(CLASSROOM_CODE),
    CALENDAR_TITLE VARCHAR2(100 CHAR) NOT NULL,
    CALENDAR_START VARCHAR2(22 CHAR) NOT NULL,
    CALENDAR_END VARCHAR2(22 CHAR) NOT NULL,
    CALENDAR_COLOR VARCHAR2(10 CHAR) NOT NULL
);

--3.0추가 테이블
--방문자 테이블 생성
CREATE TABLE VISIT (
	VISIT_PK NUMBER(5) PRIMARY KEY,
	VISIT_DATE DATE NOT NULL,
	VISIT_NUM NUMBER(5) NOT NULL
);

commit

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

--2.0추가 캘린더 시퀀스
CREATE SEQUENCE CALENDAR_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

--3.0추가 방문자 시퀀스
CREATE SEQUENCE VISIT_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

--------------
--수정 1.0 연장해서 사용시 추가
ALTER TABLE REPLY_BOARD MODIFY (REPLY_BOARD_TITLE NULL);
ALTER TABLE REPLY_BOARD MODIFY (REPLY_BOARD_VIEWS NULL);

--수정 1.1 연장해서 사용시 추가
ALTER TABLE CLASSROOM_INFO ADD (CLASSROOM_INFO_ISDELETE VARCHAR2(5 CHAR) NOT NULL CHECK(CLASSROOM_INFO_ISDELETE IN('TRUE','FALSE')));

--수정 1.2 연장해서 사용시 추가
ALTER TABLE HASHTAG MODIFY (HASHTAG_PORTFOLIO_DETAIL_PK REFERENCES PORTFOLIO_DETAIL(PORTFOLIO_DETAIL_PK));
ALTER TABLE HASHTAG DROP CONSTRAINT '해당 제약조건 이름';
--데이터 있는경우 TABLE DROP후 사용
DROP TABLE PORTFOLIO_DETAIL CASCADE CONSTRAINTS;
ALTER TABLE PORTFOLIO_DETAIL ADD (PORTFOLIO_DETAIL_DATE DATE NOT NULL);

--수정 1.3 연장해서 사용시 추가
ALTER TABLE 

---------------------
--모든 데이터 및 테이블 삭제
DROP TABLE PORTFOLIO_DETAIL CASCADE CONSTRAINTS;
DROP TABLE HASHTAG CASCADE CONSTRAINTS;
DROP TABLE PORTFOLIO CASCADE CONSTRAINTS;
DROP TABLE REPORT CASCADE CONSTRAINTS;
DROP TABLE UPDOWN CASCADE CONSTRAINTS;
DROP TABLE REPLY_BOARD CASCADE CONSTRAINTS;
DROP TABLE NOTICE_BOARD CASCADE CONSTRAINTS;
DROP TABLE CLASSROOM CASCADE CONSTRAINTS;
DROP TABLE CLASSROOM_INFO CASCADE CONSTRAINTS;
DROP TABLE MESSAGE CASCADE CONSTRAINTS;
DROP TABLE AUTHORITY CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE VISIT CASCADE CONSTRAINTS;

COMMIT
