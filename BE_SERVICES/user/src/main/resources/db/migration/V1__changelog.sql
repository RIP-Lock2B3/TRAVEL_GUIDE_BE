CREATE TABLE USER_DETAILS (
USER_ID VARCHAR(50),
FIRST_NAME VARCHAR(50),
MIDDLE_NAME VARCHAR(50),
LAST_NAME VARCHAR(50),
EMAIL VARCHAR(50),
MOBILE_NO VARCHAR(50),
PASSWORD VARCHAR(100),
MFA BOOLEAN,
CONSTRAINT USER_PKEY PRIMARY KEY (USER_ID)
);