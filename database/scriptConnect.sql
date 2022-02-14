DROP TABLE IF EXISTS user; 
DROP TABLE IF EXISTS document; 
DROP TABLE IF EXISTS emprunt; 

CREATE TABLE user(
    IdUser int NOT NULL AUTO_INCREMENT,
    Nom varchar(50),
    Prenom varchar(50), 
    Pseudo varchar(50),
    MotDePasse varchar(50),
    RoleUser varchar(50),
    PRIMARY KEY(IdUser)
); 

CREATE TABLE document(
    IdDoc int NOT NULL AUTO_INCREMENT,
    TypeDoc varchar(50), 
    TitreDoc varchar(50), 
    Emprunt TINYINT,
    PRIMARY KEY(IdDoc)
);

CREATE TABLE emprunt(
    IdUser int NOT NULL, 
    IdDoc int NOT NULL, 
    DateEmprunt DATE
);


ALTER TABLE user
ADD CONSTRAINT CK_User CHECK (RoleUser LIKE 'abonne' OR RoleUser LIKE 'bibliothecaire');

ALTER TABLE emprunt
ADD CONSTRAINT PK_Emprunt PRIMARY KEY(IdUser,IdDoc);

ALTER TABLE emprunt
ADD CONSTRAINT FK_EmpruntUser FOREIGN KEY(IdUser) REFERENCES user(IdUser) ON DELETE CASCADE; 

ALTER TABLE emprunt
ADD CONSTRAINT FK_EmpruntDoc FOREIGN KEY(IdDoc) REFERENCES document(IdDoc) ON DELETE CASCADE; 