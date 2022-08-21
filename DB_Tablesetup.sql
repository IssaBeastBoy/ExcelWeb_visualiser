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
    tasks TEXT NOT NULL,
    meetings TEXT NOT NULL,
    notes TEXT NOT NULL,
    fileStorageDir TEXT NOT NULL,
    tickets TEXT NOT NULL,
    chats TEXT NOT NULL,
    notifications TEXT NOT NULL    
);

-- command to add a new user NOTE: Change fields appropriately
INSERT INTO Users(password,name,surname,email,contact,img,status,tasks,meetings,notes,fileStorageDir,
                 tickets, chats, notifications)
values('Beast', 'Thulani', 'Tshabalala', 'tshabalalaboyt@gmail.com', '0629524422', 
       'C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\LAS0001\\img',
       'Offline', 'Task:','Meet:','Notes','
       C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\LAS0001\\excel',
       'TODO:PROG:DONE:', 'Chats:', 'Notifications:'
      );

INSERT INTO Users(password,name,surname,email,contact,img,status,tasks,meetings,notes,fileStorageDir,
                 tickets, chats, notifications)
values('Mode', 'Lithalethu', 'Hashe', 'LithaCoffee@gmail.com', '0728450122', 
       'C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\LAS0002\\img',
       'Offline', 'Task:','Meet:','Notes','
       C:\\Users\\f5462797\\Applications\\Grad_project\\Grad_Project2022\\backend_springBoot\\src\\main\\resources\\uploads\\LAS0002\\excel',
       'TODO:PROG:DONE:', 'Chats:', 'Notifications:'
      );