DROP TABLE ACCOUNT;
CREATE TABLE EMPLOYEE(
MaNV INT NOT NULL PRIMARY KEY,
Email VARCHAR2(50),
Ten VARCHAR2(50)
);
CREATE TABLE ACCOUNT(
Username VARCHAR2(50) NOT NULL PRIMARY KEY,
System VARCHAR2(50),
Create_at DATE ,
Update_at DATE ,
MaNV INT NOT NULL,
CONSTRAINT fk_account_employee FOREIGN KEY (MaNV)
    REFERENCES EMPLOYEE(MaNV)
    ON DELETE CASCADE
);
INSERT INTO EMPLOYEE (MaNV,Email,Ten) 
VALUES (1, 'a@example.com', 'Nguyen Van A');
INSERT INTO EMPLOYEE (MaNV,Email,Ten) 
VALUES (2, 'b@example.com', 'Nguyen Van B');

INSERT INTO ACCOUNT (Username, System, Create_at, Update_at, MaNV)
VALUES ('Abc', 'Quay', CURRENT_TIMESTAMP, NULL, 1);
INSERT INTO ACCOUNT (Username, System, Create_at, Update_at, MaNV)
VALUES ('Aaa', 'QLthe', CURRENT_TIMESTAMP, NULL, 1);
INSERT INTO ACCOUNT (Username, System, MaNV)
VALUES ('Bbb', 'Quay', 2);
SELECT * FROM EMPLOYEE;
SELECT 
    Username, 
    System, 
    TO_CHAR(Create_at, 'YYYY-MM-DD HH24:MI:SS') AS Created_at,
    TO_CHAR(Update_at, 'YYYY-MM-DD HH24:MI:SS') AS Updated_at,
    MaNV
FROM ACCOUNT;
ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'YYYY-MM-DD HH24:MI:SS';
