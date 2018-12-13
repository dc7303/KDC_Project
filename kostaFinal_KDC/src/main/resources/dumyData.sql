
INSERT INTO MEMBER VALUES ('lake', 'pw1234', '김호수', '호또혼', '1995-01-01', '01011112222', 'lake@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('stream', 'pw4321', '김내천', '내또혼', '1995-11-01', '010-1111-2222', 'stream@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('ocean', 'pw1002', '김바다', '바또혼', '1995-11-11', '010-2125-4321', 'ocean@gmail.com', 'FALSE', SYSDATE);

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
INSERT INTO UPDOWN VALUES (UPDOWN_SEQ.NEXTVAL, '4', 'lake', 'TRUE');
INSERT INTO UPDOWN VALUES (UPDOWN_SEQ.NEXTVAL, '4', 'stream', 'TRUE');
INSERT INTO UPDOWN VALUES (UPDOWN_SEQ.NEXTVAL, '4', 'ocean', 'FALSE');

commit;