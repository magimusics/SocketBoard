CREATE TABLE IF NOT EXISTS `graphicboard` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `client` varchar(50) DEFAULT NULL,
  `typeC` varchar(5) DEFAULT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;