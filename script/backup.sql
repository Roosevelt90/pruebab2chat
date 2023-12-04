CREATE DATABASE `db_b2chat`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO db_b2chat.`user`
(id, username, password, email)
VALUES(1, 'rdiazt', '$2a$10$dmPvKzEIFdrWq9pLTj85AuqNFN5FRAaS31E1pOfSSOFbllyIJziI.', 'rdt-2012@hotmail.com');
