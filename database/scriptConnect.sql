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


INSERT INTO user(Nom, Prenom, Pseudo, MotDePasse, RoleUser) VALUES ('Ozdemir', 'Ilker', 'ilkerrgp951', '951javaee', 'abonne');
INSERT INTO user(Nom, Prenom, Pseudo, MotDePasse, RoleUser) VALUES ('Pessey', 'Julie', 'juuulie' , 'motdepasse', 'abonne');
INSERT INTO user(Nom, Prenom, Pseudo, MotDePasse, RoleUser) VALUES ('Ouziri', 'Mourad', 'mOuziri', 'profJava', 'bibliothecaire');

INSERT INTO document(TypeDoc, TitreDoc, Emprunt) VALUES ('DVD', 'SpiderMan - No way home', 0);
INSERT INTO document(TypeDoc, TitreDoc, Emprunt) VALUES ('DVD', 'La petite sirène', 0);
INSERT INTO document(TypeDoc, TitreDoc, Emprunt) VALUES ('CD', 'Le monde ou rien', 0);
INSERT INTO document(TypeDoc, TitreDoc, Emprunt) VALUES ('CD', 'La machine', 0);
INSERT INTO document(TypeDoc, TitreDoc, Emprunt) VALUES ('Livre', 'Les misérables', 0);
INSERT INTO document(TypeDoc, TitreDoc, Emprunt) VALUES ('Livre', 'Le petit prince', 0);