CREATE TABLE IF NOT EXISTS plants (
 id int PRIMARY KEY auto_increment,
 plantName VARCHAR,
 daysToMaturity INT,
 plantSpacing VARCHAR,
 rowSpacing VARCHAR
);

CREATE TABLE IF NOT EXISTS events (
 id int PRIMARY KEY auto_increment,
 startDate DATE,
 endDate DATE,
 type VARCHAR,
 plantId INT
);

