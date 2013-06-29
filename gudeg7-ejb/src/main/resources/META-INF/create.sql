-- You can use this file to load seed data into the database using SQL statements
-- Manual insertion for backend members
-- Make sure you have included a member in GlassFish file realm (for request login in Gudeg7Session.class)
insert into Member (firstName, lastName, email, password, address, city, province) values ('Admin', 'Gudeg 7', 'admin', 'admin', '-', '-', '-');