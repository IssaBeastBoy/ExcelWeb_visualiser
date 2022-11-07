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
