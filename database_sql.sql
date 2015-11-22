BEGIN TRANSACTION;
CREATE TABLE "truefalse" (
	`question_id`	integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	`question`	longtext NOT NULL,
	`answer`	tinyint NOT NULL,
	`answered`	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE "shortanswer" (
	`question_id`	integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	`question`	longtext NOT NULL,
	`answer`	longtext NOT NULL,
	`keywords`	longtext NOT NULL,
	`answered`	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE "multiplechoice" (
	`question_id`	integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	`question`	longtext NOT NULL,
	`correct_answer`	INTEGER NOT NULL,
	`option1`	varchar(75) NOT NULL,
	`option2`	varchar(75) NOT NULL,
	`option3`	varchar(75) NOT NULL,
	`option4`	varchar(75) NOT NULL,
	`answered`	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE "hints" (
	`question_id`	integer NOT NULL,
	`type`	varchar(30) NOT NULL,
	`hint`	longtext NOT NULL,
	PRIMARY KEY(question_id,type)
);
COMMIT;
