-- Command below to create sqLITE database 
-- sqlite3 userInformationDB.db
-- Command below to create user table
CREATE TABLE Users(
    loginName INTEGER PRIMARY KEY AUTOINCREMENT,
    password TEXT NOT NULL,
    name TEXT  NOT NULL,
    surname  TEXT NOT NULL,
    email TEXT NOT NULL,
    contact TEXT NOT NULL,
    img TEXT NOT NULL,
    status TEXT NOT NULL,
    calendar TEXT NOT NULL,
    notes TEXT NOT NULL,
    fileStorageDir TEXT NOT NULL,
    tickets TEXT NOT NULL,
    chats TEXT NOT NULL,
    notifications TEXT NOT NULL    
);

-- command to add a new user NOTE: Change fields appropriately

INSERT INTO Users(password,name,surname,email,contact,img,status, calendar,notes,fileStorageDir,
                 tickets, chats, notifications)
values('Beast', 'Thulani', 'Tshabalala', 'tshabalalaboyt@gmail.com', '0629524422', 
       'C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\LAS00001\\img\\',
       'Offline', '','',
       'C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\LAS00001\\excel\\',
       'Id-1^Priority-Minor^Summary-Solving a problem^Color-#FFE000^Status-Backlog^Estimate-15>Id-2^Priority-Critical^Summary-Fix the issues reported in the IE browser.^Color-#FF0000^Status-Open^Estimate-2.5', '', ''
      );
