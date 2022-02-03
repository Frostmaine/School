
DROP TABLE IF EXISTS error_logs;
CREATE TABLE IF NOT EXISTS error_logs (
  `EVENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` datetime DEFAULT NULL,
  `LEVEL` varchar(15) DEFAULT NULL,
  `LOGGER` varchar(60) DEFAULT NULL,
  `MSG` varchar(240) DEFAULT NULL,
  `THROWABLE` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS properties;
CREATE TABLE IF NOT EXISTS properties (
   propertyNumber INT NOT NULL UNIQUE AUTO_INCREMENT,
   propertyName VARCHAR(64) NOT NULL UNIQUE,
   propertyValue VARCHAR(128) NOT NULL,
   description VARCHAR (1024)DEFAULT '',
   previousValue VARCHAR(128),
   defaultValue VARCHAR(128),
   PRIMARY KEY (propertyNumber)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
