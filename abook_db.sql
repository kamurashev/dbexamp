-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: abook_db
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `audiobooks`
--

DROP TABLE IF EXISTS `audiobooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `audiobooks` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `speaker` varchar(100) NOT NULL,
  `duration` time NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0',
  `rate` float NOT NULL DEFAULT '0',
  `description` varchar(1200) NOT NULL,
  `cover` varchar(200) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `UK_6xjoildb5q2e5i3vu1wj5hmwj` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audiobooks`
--

LOCK TABLES `audiobooks` WRITE;
/*!40000 ALTER TABLE `audiobooks` DISABLE KEYS */;
INSERT INTO `audiobooks` VALUES (35,'Вскрытие показало...','Детектив','Корнуэлл Патрисия','Ерисанова Ирина','10:39:00',12,4.5,'Еженедельно ночь с пятницы на субботу для жителей Ричмонда становится ночью страха.  Женщины боятся оставлять окна открытыми, ведь согласно новостям маньяк забирается в квартиры своих жертв именно через них.  Четыре женщины уже погибли от руки убийцы... Кто же будет следующей?  Патологоанатом Кэй Скарпетта начинает свое расследование, которое оборачивается против нее самой.  Начальство хочет отстранить от работы, коллеги начинают смотреть косо, и кто-то с завидным постоянством набирает ее домашний номер и молча дышит в трубку...','upbooks\\Вскрытие показало~8\\1560325606_1-22x.jpg','upbooks\\Вскрытие показало~8\\vskrytie-005.mp3'),(40,'Истинные чудеса','Жизнь','Жуковец Руслан','Абрамов Владимир','08:29:00',7,2.5,'Книга проливает свет на возможное развитие человека и его ступени, причины человеческих страданий и особенности взаимодействия с Высшим. Автор развенчивает ряд неверных толкований ключевых понятий, связанных с духовным ростом, предлагая читателю новое понимание реалий внутреннего мира человека. Это издание может быть полезно всем тем, кто хочет познакомиться с более тонкими и глубокими нюансами пути личного совершенствования, кто желает открыть для себя истинный, неискажённый смысл тех фундаментальных категорий, которые лежат в основе духовной науки.','upbooks\\Истинные чудеса~\\1560410937_1-22x.jpg','upbooks\\Истинные чудеса~\\16-istinnye-chudesa.mp3'),(41,'Адские конструкции','Фэнтези','Рив Филип','Головин Кирилл','11:10:00',9,3,'Перед вами третья книга трилогии «Хроники хищных городов», созданная Филипом Ривом. Описанные в ней события разворачиваются спустя шестнадцать лет с того момента, как город Анкоридж нашел себе пристанище среди берегов Мертвого континента. Но мир, охваченный войной и болью, снова вспомнил о нем…  Городок стал неподвижным сонным поселением, а его жители жили спокойной размеренной жизнью, забыв об изнуряющих странствиях по ледяным просторам. Но все изменилось, когда вернулись Пропащие Мальчишки. У Мальчишек есть цель – заполучить Жестяную Книгу, и на этот раз они готовы рисковать чем угодно, чтобы стать ее обладателями. Ведь тот, кто расшифрует ее записи, сможет одержать победу в кровопролитной войне.','upbooks\\Адские конструкции~\\cover.jpg','upbooks\\Адские конструкции~\\01-riv-filip-adskie-konstrukcii.mp3'),(42,'S-T-I-K-S. Игра в кошки-мышки','Фэнтези','Баковец Михаил','Чайцын Александр','14:40:00',3,3.5,'Простому городскому водиле Тарасу, заброшенному неведомой силой в Улей, составленный из обломков иных миров, ​повезло. Он оказался одним из немногих, переживших атаку вируса, превращающего заражённых в живых мертвецов — ​зомби. А неожиданный Дар Улья — ​невидимость — выручал его в самых кровавых передрягах. В этой жестокой и странной реальности каждый шаг Тараса и его спутников мог обернуться встречей не только с монстрами, но и с бандитами или с охотниками за органами уцелевших. Ещё страшнее оказались тайные фанатики, именующие себя детьми Стикса. Они подвергали несчастных пленников ритуальным пыткам. Кривая ухмылка Судьбы — ​встреча здесь с любимой девушкой, покинувшей его в том родном и далёком мире, — ​разбила сердце Тараса. Как жить дальше? Кто и что даст ответ?','upbooks\\STIKS Игра в кошкимышки~\\1536671799_1-22x.jpg','upbooks\\STIKS Игра в кошкимышки~\\00.mp3'),(43,'Красное по белому','Детектив','Дойл Артур Конан','Пинскер Максим, Бордуков Александр','07:34:00',1,4,'Детектив Красное по белому (другое название Этюд в багровых тонах) был опубликован в 1887 году. Именно в этом произведении читатели впервые познакомились с Шерлоком Холмсом.  \"Красное по белому\" - лондонские полицейские вынуждены признать поражение. Они не в силах раскрыть череду таинственных убийств. На помощь к ним приходит Шерлок Холмс. Используя свой излюбленный метод дедукции, он находит убийцу и раскрывает драматическую историю кровавой мести.','upbooks\\Красное по белому Этюд в багровых тонах~\\1531458406_1-22x.jpg','upbooks\\Красное по белому Этюд в багровых тонах~\\01_01.mp3');
/*!40000 ALTER TABLE `audiobooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supervisor`
--

DROP TABLE IF EXISTS `supervisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `supervisor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `role` enum('ROOT','ADMIN') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `UK_9hyue0iadt7hw2jrxsckhbe3e` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supervisor`
--

LOCK TABLES `supervisor` WRITE;
/*!40000 ALTER TABLE `supervisor` DISABLE KEYS */;
INSERT INTO `supervisor` VALUES (1,'root','root','ROOT'),(2,'george brown','000000','ADMIN'),(4,'frank smith','000000','ADMIN');
/*!40000 ALTER TABLE `supervisor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-30 22:26:17
