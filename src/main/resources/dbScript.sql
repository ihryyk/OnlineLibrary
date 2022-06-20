
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
                        `book_id` bigint NOT NULL AUTO_INCREMENT,
                        `year` int DEFAULT NULL,
                        `publishing_house` varchar(100) NOT NULL,
                        `number` int NOT NULL,
                        `deleted` tinyint NOT NULL DEFAULT '0',
                        PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,2001,'Folio',20,'0'),(2,2019,'Vivat',19,'0'),
                          (3,2019,'Terra Incognita',19,'0'),(4,2019,'Folio',19,'0'),
                          (5,2021,'Folio',18,'0'),(6,2021,'Folio',20,'0'),
                          (7,2021,'Folio',20,'0'),(8,2021,'Apriori',20,'0'),
                          (9,2017,'Знання',20,'0');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
                            `language_id` bigint NOT NULL AUTO_INCREMENT,
                            `name_language` varchar(45) DEFAULT NULL,
                            PRIMARY KEY (`language_id`),
                            UNIQUE KEY `name_language_UNIQUE` (`name_language`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'eng'),(2,'ua');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                         `order_id` bigint NOT NULL AUTO_INCREMENT,
                         `order_type` enum('SUBSCRIPTION','READING_ROOM') NOT NULL,
                         `order_status` enum('IN_PROCESSING','SUBMITTED','CANCELED') NOT NULL DEFAULT 'IN_PROCESSING',
                         `arrival_date` date DEFAULT NULL,
                         `id_user` bigint NOT NULL,
                         PRIMARY KEY (`order_id`),
                         KEY `fk_order_user1_idx1` (`id_user`),
                         CONSTRAINT `fk_order_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (50,'SUBSCRIPTION','SUBMITTED','2022-03-27',14);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_book`
--

DROP TABLE IF EXISTS `order_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_book` (
                              `id_order` bigint NOT NULL,
                              `id_book` bigint NOT NULL,
                              PRIMARY KEY (`id_order`,`id_book`),
                              KEY `fk_order_book_order1_idx` (`id_order`),
                              KEY `fk_order_book_book1_idx` (`id_book`),
                              CONSTRAINT `fk_order_book_book1` FOREIGN KEY (`id_book`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `fk_order_book_order1` FOREIGN KEY (`id_order`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_book`
--

LOCK TABLES `order_book` WRITE;
/*!40000 ALTER TABLE `order_book` DISABLE KEYS */;
INSERT INTO `order_book` VALUES (50,5);
/*!40000 ALTER TABLE `order_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pass`
--

DROP TABLE IF EXISTS `pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pass` (
                        `pass_id` bigint NOT NULL AUTO_INCREMENT,
                        `start_date` date NOT NULL,
                        `end_date` date NOT NULL,
                        `pass_status` enum('ACTIVE','FINISH') NOT NULL DEFAULT 'ACTIVE',
                        `penalty` int DEFAULT NULL,
                        `id_order` bigint NOT NULL,
                        PRIMARY KEY (`pass_id`),
                        UNIQUE KEY `pass_id_UNIQUE` (`pass_id`),
                        KEY `fk_pass_penalty1_idx` (`penalty`),
                        KEY `fk_pass_order1_idx1` (`id_order`),
                        CONSTRAINT `fk_pass_order1` FOREIGN KEY (`id_order`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass`
--

LOCK TABLES `pass` WRITE;
/*!40000 ALTER TABLE `pass` DISABLE KEYS */;
INSERT INTO `pass` VALUES  (35,'2022-03-28','2022-03-31','ACTIVE',0,50);
/*!40000 ALTER TABLE `pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `role_id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) NOT NULL,
                        PRIMARY KEY (`role_id`),
                        UNIQUE KEY `role_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'admin'),(3,'librarian'),(1,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `translation_book`
--

DROP TABLE IF EXISTS `translation_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `translation_book` (
                                    `id_language` bigint NOT NULL,
                                    `id_book` bigint NOT NULL,
                                    `title` varchar(45) NOT NULL,
                                    `author` varchar(45) NOT NULL,
                                    PRIMARY KEY (`id_language`,`id_book`),
                                    KEY `fk_id_book_idx` (`id_book`),
                                    CONSTRAINT `fk_id_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT `fk_id_language` FOREIGN KEY (`id_language`) REFERENCES `language` (`language_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `translation_book`
--

LOCK TABLES `translation_book` WRITE;
/*!40000 ALTER TABLE `translation_book` DISABLE KEYS */;
INSERT INTO `translation_book` VALUES (1,1,'Tiger hunters','Ivan Bahrianyi'),
                                      (1,2,'Enejida','Ivan Kotlyarevsky'),
                                      (1,3,'Kaidasheva family','Ivan Nechuy-Levytsky'),
                                      (1,4,'I (Romance)','Mykola Khvylovy'),
                                      (1,5,'Beyond pain: a novel-poem','Osyp Turyansʹkyy'),
                                      (1,6,'Fata morgana','Mykhaylo Kotsyubynsʹkyy'),
                                      (1,7,'Land (Native)','Olʹha Kobylyansʹka'),
                                      (1,8,'Dovbush','Hnat Khotkevych'),
                                      (1,9,'Klymko','Hryhir Tyutyunnyk'),
                                      (2,1,'Тигролови','Іван Багряний'),
                                      (2,2,'Енеїда','Іван Котляревький'),
                                      (2,3,'Кайдашева сім\'я','Іван Нечуй-Левицький'),
                                      (2,4,'Я (Романтика)','Микола Хвильовий'),
                                      (2,5,'Поза межами болю: повість-поема','Осип Турянський'),
                                      (2,6,'Fata morgana','Михайло Коцюбинський'),
                                      (2,7,'Земля (Рідне)','Ольга Кобилянська'),
                                      (2,8,'Довбуш','Гнат Хоткевич'),(2,9,'Климко','Григір Тютюнник');
/*!40000 ALTER TABLE `translation_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT,
                        `user_name` varchar(45) NOT NULL,
                        `email` varchar(65) NOT NULL,
                        `password` varchar(50) NOT NULL,
                        `lock_status` enum('UNLOCKED','BLOCKED') NOT NULL DEFAULT 'UNLOCKED',
                        `id_role` bigint NOT NULL DEFAULT '4',
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `email_UNIQUE` (`email`),
                        KEY `fk_user_role1_idx` (`id_role`),
                        CONSTRAINT `fk_id_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (14,'IhorBerezovskyi','123467@gmail.com','7c4a8d09ca3762af61e59520943dc26494f8941b','UNLOCKED',1),(18,'adminnnn','ihorr12r@gmail.com','7c4a8d09ca3762af61e59520943dc26494f8941b','UNLOCKED',2),(52,'librarian','librarian@gmail.com','7c4a8d09ca3762af61e59520943dc26494f8941b','UNLOCKED',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'library'
--

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-27  0:00:11
