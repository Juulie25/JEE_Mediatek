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
    Age int,
    PRIMARY KEY(IdUser)
); 

CREATE TABLE document(
    IdDoc int NOT NULL AUTO_INCREMENT,
    TypeDoc varchar(50), 
    TitreDoc varchar(50),
    AuteurDoc varchar(50), 
    Emprunt TINYINT,
    Adulte TINYINT,
    PRIMARY KEY(IdDoc)
);

CREATE TABLE emprunt(
    IdUser int NOT NULL, 
    IdDoc int NOT NULL, 
    DateEmprunt DATE
);


ALTER TABLE user
ADD CONSTRAINT CK_User CHECK (RoleUser LIKE 'abonne' OR RoleUser LIKE 'bibliothecaire');

ALTER TABLE document
ADD CONSTRAINT CK_Doc CHECK (TypeDoc LIKE 'CD' OR TypeDoc LIKE 'DVD' OR TypeDoc LIKE 'Livre');

ALTER TABLE emprunt
ADD CONSTRAINT PK_Emprunt PRIMARY KEY(IdUser,IdDoc);

ALTER TABLE emprunt
ADD CONSTRAINT FK_EmpruntUser FOREIGN KEY(IdUser) REFERENCES user(IdUser) ON DELETE CASCADE; 

ALTER TABLE emprunt
ADD CONSTRAINT FK_EmpruntDoc FOREIGN KEY(IdDoc) REFERENCES document(IdDoc) ON DELETE CASCADE; 


INSERT INTO user(Nom, Prenom, Pseudo, MotDePasse,  RoleUser, Age) VALUES ('Ozdemir', 'Ilker', 'ilkerrgp951','951javaee', 'abonne', 12);
INSERT INTO user(Nom, Prenom, Pseudo, MotDePasse, RoleUser, Age) VALUES ('Pessey', 'Julie', 'juuulie', 'motdepasse', 'abonne', 19);
INSERT INTO user(Nom, Prenom, Pseudo, MotDePasse, RoleUser, Age) VALUES ('Ouziri', 'Mourad', 'mouziri', 'profJava', 'bibliothecaire', 35);

INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES ('DVD', 'SpiderMan - No way home','Marvel', 0, 1);
INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES ('DVD', 'La petite sirène','Disney', 0, 0);
INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES ('CD', 'Le monde ou rien', 'PNL', 0, 0);
INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES ('CD', 'La machine', 'Jul', 0, 0);
INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES ('Livre', 'Les misérables', 'Victor Hugo', 0, 1);
INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES ('Livre', 'Le petit prince', 'Antoine de Saint-Exupéry', 0, 0);