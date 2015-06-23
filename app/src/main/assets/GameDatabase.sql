BEGIN TRANSACTION;
CREATE TABLE "M_Clue" (
	`Clue_ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Clue_Text`	TEXT,
	`Actor_ID`	INTEGER,
	FOREIGN KEY(`Actor_ID`) REFERENCES Actors ( _ID )
);
INSERT INTO `M_Clue` VALUES (1,'A pirate Radio DJ',101);
INSERT INTO `M_Clue` VALUES (2,'An Arms Dealer using flight attendants to smuggle goods',NULL);
CREATE TABLE "H_Clue" (
	`Clue_ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Clue_Text`	TEXT,
	`Actor_ID`	INTEGER,
	FOREIGN KEY(`Actor_ID`) REFERENCES "Actors" ( _ID )
);
INSERT INTO `H_Clue` VALUES (1,'A skateboarding vigilante',101);
INSERT INTO `H_Clue` VALUES (2,'A Boxing Promoter',102);
CREATE TABLE "GA_Clue" (
	`Clue_ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Clue_Text`	TEXT,
	`Actor_ID`	INTEGER,
	FOREIGN KEY(`Actor_ID`) REFERENCES Actors ( _ID )
);
INSERT INTO `GA_Clue` VALUES (1,'A Naval Captian chasing after a stolen Nuke',101);
INSERT INTO `GA_Clue` VALUES (2,'I''m tired of these motha fucking snakes on this motha fucking plane',102);
CREATE TABLE "E2_Clue" (
	`Clue_ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Clue_Text`	TEXT,
	`Actor_ID`	INTEGER,
	FOREIGN KEY(`Actor_ID`) REFERENCES Actors ( _ID )
);
INSERT INTO `E2_Clue` VALUES (1,'I have killed the popular kids with my girlfriend',101);
INSERT INTO `E2_Clue` VALUES (2,'A Jedi Knight',102);
CREATE TABLE "E1_Clue" (
	`Clue_ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Clue_Text`	TEXT,
	`Actor_ID`	INTEGER,
	FOREIGN KEY(`Actor_ID`) REFERENCES Actors ( _ID )
);
INSERT INTO `E1_Clue` VALUES (1,'A journalist writing about Vampires',101);
INSERT INTO `E1_Clue` VALUES (2,'A vigilante Ex-Cop',102);
CREATE TABLE "Actors" (
	`_ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Name`	TEXT NOT NULL,
	`Photo_ID`	REAL
);
INSERT INTO `Actors` VALUES (101,'Christian Slater',NULL);
INSERT INTO `Actors` VALUES (102,'Samual L Jackson',NULL);
COMMIT;
