
INSERT INTO MEMBER VALUES ('lake', 'pw1234', '��ȣ��', 'ȣ��ȥ', '1995-01-01', '01011112222', 'lake@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('stream', 'pw4321', '�賻õ', '����ȥ', '1995-11-01', '010-1111-2222', 'stream@gmail.com', 'FALSE', SYSDATE);
INSERT INTO MEMBER VALUES ('ocean', 'pw1002', '��ٴ�', '�ٶ�ȥ', '1995-11-11', '010-2125-4321', 'ocean@gmail.com', 'FALSE', SYSDATE);

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', NULL, 'tech�Խ��� ����2', 'lake', SYSDATE, 'tech�Խ��� ���볻��22', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', NULL, 'tech�Խ��� ����3', 'lake', SYSDATE, 'tech�Խ��� ���볻��33', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', NULL, 'tech�Խ��� ����4', 'lake', SYSDATE, 'tech�Խ��� ���볻��44', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', null, 'tech�Խ��� ����', 'lake', SYSDATE, 'tech�Խ��� ���볻��', '0', NULL, 'FALSE');

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', NULL, 'study�Խ��� ����2', 'lake', SYSDATE, 'study�Խ��� ���볻��22', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', NULL, 'study�Խ��� ����3', 'lake', SYSDATE, 'study�Խ��� ���볻��33', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', NULL, 'study�Խ��� ����4', 'lake', SYSDATE, 'study�Խ��� ���볻��44', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'study', null, 'study�Խ��� ����', 'lake', SYSDATE, 'study�Խ��� ���볻��', '0', NULL, 'FALSE');

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', NULL, 'lib�Խ��� ����2', 'lake', SYSDATE, 'lib�Խ��� ���볻��22', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', NULL, 'lib�Խ��� ����3', 'lake', SYSDATE, 'lib�Խ��� ���볻��33', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', NULL, 'lib�Խ��� ����4', 'lake', SYSDATE, 'lib�Խ��� ���볻��44', '0', NULL, 'FALSE');
INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'lib', null, 'lib�Խ��� ����', 'lake', SYSDATE, 'lib�Խ��� ���볻��', '0', NULL, 'FALSE');

INSERT INTO REPLY_BOARD VALUES (REPLY_BOARD_SEQ.NEXTVAL, 'tech', '4', 'tech�Խ��Ǵ��', 'lake', SYSDATE, 'tech�Խ��Ǵ��', NULL, 'lake', 'FALSE');
INSERT INTO UPDOWN VALUES (UPDOWN_SEQ.NEXTVAL, '4', 'lake', 'TRUE');
INSERT INTO UPDOWN VALUES (UPDOWN_SEQ.NEXTVAL, '4', 'stream', 'TRUE');
INSERT INTO UPDOWN VALUES (UPDOWN_SEQ.NEXTVAL, '4', 'ocean', 'FALSE');

commit;