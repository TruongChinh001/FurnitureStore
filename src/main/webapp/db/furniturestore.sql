-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: furniturestore
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `username` varchar(50) NOT NULL,
  `create_date` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enable` bit(1) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('beobp18','2022-11-02','truongchinh.dev@gmail.com',_binary '','Nguyễn Trường Chinh','$2a$10$SWGhHq/sN48yZf4jBz90NuvboRHvItx/IEbVnlhWaCEUmFoLR7UN6','0985235240',NULL,NULL),('beobp186','2022-11-02','truongchinh.dev@gmail.com',_binary '','Nguyễn Trường Chinh','$2a$10$NEd7aXOcms6YbNoiV.0aHOkOAb.GGFeIfFVVxUgNOZa.S3JHYKdmO','0985235240',NULL,NULL),('taibeo','2022-10-20','taibeo@gmail.com',_binary '','Nguyễn Trọng Tài','abcxyz','1234587985','abc.png',NULL);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address_line_1` varchar(255) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `account_id` varchar(50) DEFAULT NULL,
  `district_id` int DEFAULT NULL,
  `hamlet_id` int DEFAULT NULL,
  `province_city_id` int DEFAULT NULL,
  `ward_village_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaf33clpk1s4svpok3x9egrvji` (`account_id`),
  KEY `FKdu4j0drj57p0x9avyatpq2v5` (`district_id`),
  KEY `FKar3gv2w97s37b1u7mefavt6cs` (`hamlet_id`),
  KEY `FKly9s6o5670j12pa6r0qamjnua` (`province_city_id`),
  KEY `FKgmtsxvvfyve4j7acwl0badr3v` (`ward_village_id`),
  CONSTRAINT `FKaf33clpk1s4svpok3x9egrvji` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`username`),
  CONSTRAINT `FKar3gv2w97s37b1u7mefavt6cs` FOREIGN KEY (`hamlet_id`) REFERENCES `hamlets` (`id`),
  CONSTRAINT `FKdu4j0drj57p0x9avyatpq2v5` FOREIGN KEY (`district_id`) REFERENCES `districts` (`id`),
  CONSTRAINT `FKgmtsxvvfyve4j7acwl0badr3v` FOREIGN KEY (`ward_village_id`) REFERENCES `wards_villages` (`id`),
  CONSTRAINT `FKly9s6o5670j12pa6r0qamjnua` FOREIGN KEY (`province_city_id`) REFERENCES `provinces_cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'abczyc',_binary '','beobp18',1,1,1,1);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `role_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk1rm8k6yrom4ojm9sxju0yhsr` (`username`,`role_id`),
  KEY `FK7kj74d912rytfp5su91jfv0tg` (`role_id`),
  CONSTRAINT `FK7kj74d912rytfp5su91jfv0tg` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKlw0sapcjvuxfa1kvyjw7jml88` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'beobp18','ADMIN'),(2,'beobp18','GUEST'),(3,'beobp18','STAFF'),(4,'beobp186','GUEST');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Nội thất A Concept'),(2,'Nội thất Baya'),(3,'Nội thất MOHO'),(4,'Nội thất JYSK'),(5,'Nội thất Nhà Xinh'),(6,'Nội thất Index Living Mall'),(7,'Nội thất Chilai'),(8,'Nội thất Sonder Living Hà Nội'),(9,'Sofa & Nội thất Cozy Living'),(10,'Nội thất cao cấp BoConcept'),(11,'Nội thất Aaron (Mỹ)'),(12,'Nội thất Row'),(13,'Nội thất ASHLEY'),(14,'Nội thất Dunelm Mill (Anh)'),(15,'Nội thất Dietiker (Thụy Sĩ)'),(16,'Harvey Norma'),(17,'Sofa Workshop'),(18,'IKEA'),(19,'La-Z-Boy'),(20,'Restoration Hardware'),(21,'UMA'),(22,'Phố Xinh'),(23,'Hoàng Anh Gia Lai'),(24,'Erado'),(25,'Hoàn Mỹ'),(26,'Hòa Phát'),(27,'Art Home');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `account_id` varchar(50) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhu7yrgm18fpcjyw1vcimil0fl` (`account_id`),
  KEY `FK1re40cjegsfvw58xrkdp6bac6` (`product_id`),
  CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKhu7yrgm18fpcjyw1vcimil0fl` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category_group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgu4k4el1ajuv8d97x70fg8wr6` (`category_group_id`),
  CONSTRAINT `FKgu4k4el1ajuv8d97x70fg8wr6` FOREIGN KEY (`category_group_id`) REFERENCES `categories_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Sofa',1),(2,'Sofa góc',1),(3,'Ghế thư giã',1),(4,'Ghế dài & đô',1),(5,'Bàn sofa',1),(6,'Bàn nước',1),(7,'Bàn Console',1),(8,'Kệ trưng bày',1),(9,'Tủ giày',1),(10,'Bàn bê',1),(11,'Armchair',1),(12,'Bàn ă',2),(13,'Ghế ă',2),(14,'Ghế bar',2),(15,'Tủ ly',2),(16,'Xe đẩy',2),(17,'Tủ bếp',3),(18,'Thiết bị bếp',3),(19,'Giường ngủ',4),(20,'Tab đầu giường',4),(21,'Bàn trang điểm',4),(22,'Tủ quần áo',4),(23,'Tủ âm tường',4),(24,'Tủ hốc kéo',4),(25,'Nệm',4),(26,'Bàn làm việc',5),(27,'Ghế làm việc',5),(28,'Kệ sách',5),(29,'Bàn ngoài trời',7),(30,'Ghế ngoài trời',7),(31,'Đèn trang trí',6),(32,'Thảm trang trí',6),(33,'Michael Aram',6),(34,'Bình trang trí',6),(35,'Tranh',6),(36,'Gương',6),(37,'Hoa và cây',6),(38,'Đồng hồ ',6),(39,'Khung hình',6),(40,'Tượng trang trí',6),(41,'Gối và thú bông',6),(42,'Nến và chân nế',6),(43,'Dụng cụ bếp',3),(44,'Hàng trang trí khác',6);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories_group`
--

DROP TABLE IF EXISTS `categories_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_group`
--

LOCK TABLES `categories_group` WRITE;
/*!40000 ALTER TABLE `categories_group` DISABLE KEYS */;
INSERT INTO `categories_group` VALUES (1,'Phòng Khách'),(2,'Phòng Ăn'),(3,'Tủ Bếp'),(4,'Phòng Ngủ'),(5,'Phong Làm Việc'),(6,'Đồ Trang Trí'),(7,'Ngoại Thất');
/*!40000 ALTER TABLE `categories_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `districts`
--

DROP TABLE IF EXISTS `districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `districts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `province_city_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcb5pv2oiofhgeho263qi25uko` (`province_city_id`),
  CONSTRAINT `FKcb5pv2oiofhgeho263qi25uko` FOREIGN KEY (`province_city_id`) REFERENCES `provinces_cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
INSERT INTO `districts` VALUES (1,'Thành phố Long Xuyên',1),(2,'Thị xã Châu Đốc',1),(3,'Huyện An Phú',1),(4,'Huyện Châu Phú',1),(5,'Huyện Châu Thành',1),(6,'Huyện Chợ Mới',1),(7,'Huyện Phú Tân',1),(8,'Huyện Tân Châu',1),(9,'Huyện Tịnh Biên',1),(10,'Huyện Thoại Sơn',1),(11,'Huyện Tri Tôn',1);
/*!40000 ALTER TABLE `districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hamlets`
--

DROP TABLE IF EXISTS `hamlets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hamlets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hamlets`
--

LOCK TABLES `hamlets` WRITE;
/*!40000 ALTER TABLE `hamlets` DISABLE KEYS */;
INSERT INTO `hamlets` VALUES (1,'test');
/*!40000 ALTER TABLE `hamlets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'Nhôm'),(2,'Da'),(3,'Ðá marble (dá c?m th?ch)'),(4,'Ðá granite.'),(5,'Mây, tre'),(6,'G? t? nhiê'),(7,'Kính'),(8,'Ván nh?a'),(9,'G?ch nung'),(10,'Bê tông/ xi mang'),(11,'T?m ?p tu?ng gi? da'),(12,'T?m ?p tu?ng 3D'),(13,'G? công nghi?p'),(14,'Chân kim lo?i son, n?m b?c da t? nhiên, n?m ng?i & lung r?i'),(15,'Chân Inox màu gold, tay gỗ, bọc vải, nệm ngồi tháo rời'),(16,'Khung g? s?i d?c t? nhiên nh?p kh?u t? M?- Da Ý t? nhiên cao c?p'),(17,'Vải cao cấp'),(18,'Gỗ cao cấp');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,26900000,2,3,2),(2,61860000,1,2,3),(3,26900000,1,3,4),(4,289000000,1,1,5),(5,61860000,1,2,5),(6,26900000,1,3,5),(7,289000000,1,1,6),(8,61860000,1,2,7),(9,42900000,1,11,8),(10,61860000,1,2,9),(11,26900000,1,3,9),(12,61860000,1,2,10),(13,26900000,1,3,10),(14,109890000,1,4,10),(15,51940000,1,12,11),(16,29900000,1,10,11);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address_line_1` varchar(255) NOT NULL,
  `create_date` date DEFAULT NULL,
  `district` varchar(45) DEFAULT NULL,
  `hamlet` varchar(45) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `province_city` varchar(45) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `ward_village` varchar(45) DEFAULT NULL,
  `account_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKagh5svlor3slbay6tq5wqor1o` (`account_id`),
  CONSTRAINT `FKagh5svlor3slbay6tq5wqor1o` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'','2022-11-07','','',NULL,NULL,'',NULL,'','beobp18'),(3,'','2022-11-07','','',NULL,NULL,'',NULL,'','beobp18'),(4,'','2022-11-07','','',NULL,NULL,'',NULL,'','beobp18'),(5,'','2022-11-07','','',NULL,NULL,'',NULL,'','beobp18'),(6,'','2022-11-07','','',NULL,NULL,'',NULL,'','beobp18'),(7,'','2022-11-07','','',NULL,NULL,'',NULL,'','beobp18'),(8,'','2022-11-08','','',NULL,NULL,'',NULL,'','beobp18'),(9,'','2022-11-08','','',NULL,NULL,'',NULL,'','beobp18'),(10,'','2022-11-08','','',NULL,NULL,'',NULL,'','beobp18'),(11,'','2022-11-08','','',NULL,NULL,'',NULL,'','beobp18'),(12,'','2022-11-08','','',NULL,NULL,'Chọn tỉnh thành',NULL,'','beobp18'),(13,'','2022-11-08','','',NULL,NULL,'Chọn tỉnh thành',NULL,'','beobp18'),(14,'','2022-11-08','','',NULL,NULL,'1',NULL,'','beobp18'),(15,'','2022-11-08','','',NULL,NULL,'An Giang',NULL,'','beobp18'),(16,'','2022-11-08','Thành phố Long Xuyên','',NULL,NULL,'An Giang',NULL,'','beobp18'),(17,'','2022-11-08','Chọn quận huyện','',NULL,NULL,'Chọn tỉnh thành',NULL,'','beobp18');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnq71xsohugpqwf3c9gxmsuy` (`product_id`),
  CONSTRAINT `FKqnq71xsohugpqwf3c9gxmsuy` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (1,'sofa-1-1.png',1),(2,'sofa-1-2.png',1),(3,'sofa-1-3.png',1),(4,'sofa-1-4.png',1),(5,'ban-lam-viec-1-1.png',12),(6,'ban-lam-viec-1-2.png',12),(7,'ban-lam-viec-1-3.png',12),(8,'ban-lam-viec-1-4.png',12),(9,'ban-lam-viec-1-5.png',12);
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `average_rating` float DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `day_of_manufacture` date DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `height` float DEFAULT NULL,
  `in_stock` bit(1) DEFAULT NULL,
  `inventory` int NOT NULL,
  `length` float DEFAULT NULL,
  `long_desc` text,
  `main_image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `review_count` int DEFAULT NULL,
  `short_desc` text,
  `weight` float DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `material_id` int DEFAULT NULL,
  `unit_product_id` int DEFAULT NULL,
  `width` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv` (`brand_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKj2d4f35svu15l83nru8t5k593` (`material_id`),
  KEY `FKnrr3shktolgko0nxfev107k84` (`unit_product_id`),
  CONSTRAINT `FKa3a4mpsfdf4d2y6r8ra3sc8mv` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
  CONSTRAINT `FKj2d4f35svu15l83nru8t5k593` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  CONSTRAINT `FKnrr3shktolgko0nxfev107k84` FOREIGN KEY (`unit_product_id`) REFERENCES `unit_products` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,NULL,'2022-03-11','2020-09-12',0,70,_binary '',1000,180,'Ưu điểm của Sofa CortinaChân ghế: Thiết kế thanh mảnh, được làm từ gỗ tự nhiên nên cứng cáp và chắn chắn','sofa-1.png','Sofa 3 chỗ Osaka mẫu 1 vải 29',289000000,NULL,'Sofa Cortina có thiết kế gọn gàng, màu sắc trẻ trung nên được nhiều người sử dụng cho phòng khách. Sản phẩm với kiểu dáng hiện đại, khung sườn chắc chắn, đệm mút êm ái mang lại cảm giác thư giãn. Chất liệu bọc bằng vải thoáng mát, dễ sử dụng và vệ sinh khi dính vết bẩn.',50,5,1,15,2,50),(2,NULL,'2022-03-11','2019-12-30',0,70,_binary '',1000,180,NULL,'sofa-2.png','Sofa Penny 3 chỗ da Veneza 506 MB',61860000,NULL,NULL,50,5,1,14,2,50),(3,NULL,'2022-03-11','2019-12-22',0,70,_binary '',1000,180,NULL,'sofa-3.png','Sofa 3 chỗ PENNY – vải màu xanh lá',26900000,NULL,NULL,50,5,1,14,2,50),(4,NULL,'2022-03-11','2021-10-12',0,70,_binary '',1000,180,NULL,'sofa-4.png','Sofa Bridge 3 chỗ hiện đại da Beige',109890000,NULL,NULL,50,5,1,16,2,50),(5,NULL,'2022-03-11','2021-10-12',0,70,_binary '',1000,180,NULL,'sofa-5.png','Sofa Maxine 3 chỗ hiện đại da English Saddle',143250000,NULL,NULL,50,5,1,2,2,50),(6,NULL,'2022-03-11','2021-09-09',0,100,_binary '',1000,180,NULL,'ghe-thu-gian-1.png','Ghế Lazboy 10T352 Shannon da Sienna',27490000,NULL,NULL,50,5,3,2,2,50),(7,NULL,'2022-03-11','2021-09-09',0,100,_binary '',1000,180,NULL,'ghe-thu-gian-2.png','Ghế Lazboy H.Town 10T532 Da Pebble',36900000,NULL,NULL,50,5,3,2,2,50),(8,NULL,'2022-03-11','2022-02-11',0,100,_binary '',1000,180,NULL,'sofa-goc-1.png','Sofa Poppy góc phải vải màu cam',29360000,NULL,NULL,50,5,3,2,2,50),(9,NULL,'2022-03-11','2022-02-11',0,80,_binary '',1000,180,NULL,'sofa-goc-2.png','Sofa góc phải Rumba Vải 28',19540000,NULL,NULL,50,5,2,17,2,50),(10,NULL,'2022-03-11','2022-02-11',0,80,_binary '',1000,180,NULL,'sofa-goc-3.png','Sofa góc phải Shadow vải beige FY-02',29900000,NULL,NULL,50,5,2,17,2,50),(11,NULL,'2022-03-11','2022-02-11',0,80,_binary '',1000,180,NULL,'sofa-goc-4.png','Sofa góc phải Monaco + Đôn vải 559/03 + 141/23',42900000,NULL,NULL,50,5,2,17,2,50),(12,NULL,'2022-07-11','2021-12-11',5,70,_binary '',8,200,'Một thiết kế bàn làm việc đẳng cấp cho không gian làm việc của bạn, Maxine sử dụng chất liệu da trên bề mặt, khung gỗ hoàn thiện mdf veneer nâu trầm sang trọng tạo cảm giác thư thái, nhẹ nhàng. Công năng của chiếc bàn được sắp tối ưu với các hộc kéo rộng. Maxine – Nét nâu trầm sang trọng Maxine, mang thiết kế vượt thời gian, gửi gắm và gói gọn lại những nét đẹp của thiên nhiên và con người nhưng vẫn đầy tính ứng dụng cao trong suốt hành trình phiêu lưu của nhà thiết kế người Pháp Dominique Moal. Bộ sưu tập nổi bật với màu sắc nâu trầm sang trọng, là sự kết hợp giữa gỗ, da bò và kim loại vàng bóng. Đặc biệt trên mỗi sản phẩm, những nét bo viên, chi tiết kết nối được sử dụng kéo léo tạo ra điểm nhất rất riêng cho bộ sưu tập. Maxine thể hiện nét trầm tư, thư giãn để tận hưởng không gian sống trong nhịp sống hiện đại. Sản phẩm thuộc BST Maxine được sản xuất tại Việt Nam.','ban-lam-viec-1.png','Bàn làm việc Maxine',51940000,NULL,NULL,40,5,26,18,2,70);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provinces_cities`
--

DROP TABLE IF EXISTS `provinces_cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provinces_cities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provinces_cities`
--

LOCK TABLES `provinces_cities` WRITE;
/*!40000 ALTER TABLE `provinces_cities` DISABLE KEYS */;
INSERT INTO `provinces_cities` VALUES (1,'An Giang'),(2,'Bà Rịa - Vũng Tàu'),(3,'Bắc Giang'),(4,'Bắc Kạn'),(5,'Bạc Liêu'),(6,'Bắc Ninh'),(7,'Bến Tre'),(8,'Bình Định'),(9,'Bình Dương'),(10,'Bình Phước'),(11,'Bình Thuận'),(12,'Cà Mau'),(13,'Cần Thơ'),(14,'Cao Bằng'),(15,'Đà Nẵng'),(16,'Đắk Lắk'),(17,'Đắk Nông'),(18,'Điện Biên'),(19,'Đồng Nai'),(20,'Đồng Tháp'),(21,'Gia Lai'),(22,'Hà Giang'),(23,'Hà Nam'),(24,'Hà Nội'),(25,'Hà Tĩnh'),(26,'Hải Dương'),(27,'Hải Phòng'),(28,'Hậu Giang'),(29,'Hòa Bình'),(30,'Hưng Yên'),(31,'Khánh Hòa'),(32,'Kiên Giang'),(33,'Kon Tum'),(34,'Lai Châu'),(35,'Lâm Đồng'),(36,'Lạng Sơn'),(37,'Lào Cai'),(38,'Long An'),(39,'Nam Định'),(40,'Nghệ An'),(41,'Ninh Bình'),(42,'Ninh Thuận'),(43,'Phú Thọ'),(44,'Phú Yên'),(45,'Quảng Bình'),(46,'Quảng Nam'),(47,'Quảng Ngãi'),(48,'Quảng Ninh'),(49,'Quảng Trị'),(50,'Sóc Trăng'),(51,'Sơn La'),(52,'Tây Ninh'),(53,'Thái Bình'),(54,'Thái Nguyên'),(55,'Thanh Hóa'),(56,'Thừa Thiên Huế'),(57,'Tiền Giang'),(58,'TP Hồ Chí Minh'),(59,'Trà Vinh'),(60,'Tuyên Quang'),(61,'Vĩnh Long'),(62,'Vĩnh Phúc'),(63,'Yên Bái');
/*!40000 ALTER TABLE `provinces_cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(300) NOT NULL,
  `headline` varchar(255) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `review_time` date DEFAULT NULL,
  `account_id` varchar(50) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpia3ckbv4qpt6ert2akln0x6l` (`account_id`),
  KEY `FKpl51cejpw4gy5swfar8br9ngi` (`product_id`),
  CONSTRAINT `FKpia3ckbv4qpt6ert2akln0x6l` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`username`),
  CONSTRAINT `FKpl51cejpw4gy5swfar8br9ngi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADMIN','Administration'),('GUEST','Guests'),('STAFF','Staffs');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shops`
--

DROP TABLE IF EXISTS `shops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shops` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shops`
--

LOCK TABLES `shops` WRITE;
/*!40000 ALTER TABLE `shops` DISABLE KEYS */;
/*!40000 ALTER TABLE `shops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_products`
--

DROP TABLE IF EXISTS `unit_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit_products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_products`
--

LOCK TABLES `unit_products` WRITE;
/*!40000 ALTER TABLE `unit_products` DISABLE KEYS */;
INSERT INTO `unit_products` VALUES (1,'Bộ'),(2,'Cái'),(3,'Sỉ (10 cái)');
/*!40000 ALTER TABLE `unit_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wards_villages`
--

DROP TABLE IF EXISTS `wards_villages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wards_villages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wards_villages`
--

LOCK TABLES `wards_villages` WRITE;
/*!40000 ALTER TABLE `wards_villages` DISABLE KEYS */;
INSERT INTO `wards_villages` VALUES (1,'test');
/*!40000 ALTER TABLE `wards_villages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-08 19:23:11
