![Biểu đồ lớp](database.png)
<pre> ```sql CREATE TABLE USERS ( UserId INT NOT NULL PRIMARY KEY, Email VARCHAR2(50) NOT NULL, Name VARCHAR2(50) NOT NULL, Phone VARCHAR2(50) ); CREATE TABLE ACCOUNT ( Username VARCHAR2(50) NOT NULL PRIMARY KEY, System VARCHAR2(50) NOT NULL, Create_At DATE DEFAULT SYSDATE, Update_At DATE, UserId INT NOT NULL, Status VARCHAR2(50) DEFAULT 'ACTIVE', CONSTRAINT fk_account_user FOREIGN KEY (UserId) REFERENCES USERS(UserId) ON DELETE CASCADE ); -- Xem dữ liệu SELECT * FROM USERS; SELECT * FROM ACCOUNT; ``` </pre>
![Biểu đồ lớp](class_diagram.png)
![Biểu đồ lớp](squence_diagram.png)

