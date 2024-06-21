INSERT INTO status VALUES (1, 'Prochainement');
INSERT INTO status VALUES (2, 'En cours');
INSERT INTO status VALUES (3, 'Terminé');

INSERT INTO sexe VALUES (1, 'Mâle');
INSERT INTO sexe VALUES (2, 'Femelle');

INSERT INTO ecole (nom_ecole, localisation, id_type_ecole) VALUES ('Saint Andre', 'Ambohipo', NULL);
INSERT INTO ecole (nom_ecole, localisation, id_type_ecole) VALUES ('Saint Xavier', 'Antanimena', NULL);

INSERT INTO type_ecole VALUES (1, 'Colege');

INSERT INTO annee_scolaire (id,id_ecole,nom )VALUES (1, 2,'annee1');

INSERT INTO dirigeant (nom, prenom, date_naissance, adresse, conctact, email, mdp, id_ecole, id_sexe, identifiant) VALUES 
('Andriamanantena', 'Avotra', '1990-04-12', 'Lot II D 23, Antananarivo', '0380989055', 'Avotra.Andriamanantena@gmail.com','dirigeant1',2,1,0101);

INSERT INTO tuteur (nom, prenom, date_naissance, adresse, conctact, email, mdp, id_sexe) VALUES
 ('Rasolofomanana', 'Mamy', '1990-04-12', 'Lot II D 23, Antananarivo', '0321234567', 'mamy.rasolofomanana@example.com', 'Mamy123', 1),
 ('Rajoelina', 'Hanta', '1985-06-19', 'Lot 5 B 12, Antananarivo', '0339876543', 'hanta.rajoelina@example.com', 'Hanta123', 2),
 ('Rakoto', 'Tiana', '1975-11-30', 'Lot III C 45, Antananarivo', '0345678901', 'tiana.rakoto@example.com', 'Tiana123', 1),
 ('Randriamalala', 'Voahirana', '1993-02-15', 'Lot IV D 56, Antananarivo', '0321122334', 'voahirana.randriamalala@example.com', 'Voahirana123', 2),
 ('Andrianarisoa', 'Lova', '1988-09-25', 'Lot V A 67, Antananarivo', '0334433221', 'lova.andrianarisoa@example.com', 'Lova123', 1),
 ('Rakotomalala', 'Hasina', '1983-03-14', 'Lot VI B 78, Antananarivo', '0348765432', 'hasina.rakotomalala@example.com', 'Hasina123', 2),
 ('Randrianantenaina', 'Miora', '1995-07-05', 'Lot VII C 89, Antananarivo', '0323344556', 'miora.randrianantenaina@example.com', 'Miora123', 2),
 ('Rasoamalala', 'Feno', '1978-12-22', 'Lot VIII D 10, Antananarivo', '0339988776', 'feno.rasoamalala@example.com', 'Feno123', 1),
 ('Rakotomandimby', 'Voary', '1982-08-10', 'Lot IX A 11, Antananarivo', '0343456789', 'voary.rakotomandimby@example.com', 'Voary123', 2),
 ('Ranaivoson', 'Antsa', '1997-01-01', 'Lot X B 12, Antananarivo', '0325566778', 'antsa.ranaivoson@example.com', 'Antsa123', 1),
 ('Ravelojaona', 'Kanto', '1980-05-20', 'Lot XI C 13, Antananarivo', '0336677889', 'kanto.ravelojaona@example.com', 'Kanto123', 2),
 ('Raharimalala', 'Niry', '1992-09-09', 'Lot XII D 14, Antananarivo', '0347788990', 'niry.raharimalala@example.com', 'Niry123', 1),
 ('Ravohangy', 'Soa', '1984-11-11', 'Lot XIII A 15, Antananarivo', '0328899001', 'soa.ravohangy@example.com', 'Soa123', 2),
 ('Randriamanana', 'Faly', '1979-10-02', 'Lot XIV B 16, Antananarivo', '0339900112', 'faly.randriamanana@example.com', 'Faly123', 1),
 ('Rakotondraibe', 'Lalao', '1987-03-27', 'Lot XV C 17, Antananarivo', '0340011223', 'lalao.rakotondraibe@example.com', 'Lalao123', 2),
 ('Ravelonarivo', 'Fara', '1989-06-17', 'Lot XVI D 18, Antananarivo', '0321122334', 'fara.ravelonarivo@example.com', 'Fara123', 2),
 ('Rasamimanana', 'Hery', '1977-07-18', 'Lot XVII A 19, Antananarivo', '0332233445', 'hery.rasamimanana@example.com', 'Hery123', 1),
 ('Randriamanampisoa', 'Faly', '1991-02-24', 'Lot XVIII B 20, Antananarivo', '0343344556', 'faly.randriamanampisoa@example.com', 'Faly123', 1),
 ('Ratsimba', 'Sitraka', '1986-04-15', 'Lot XIX C 21, Antananarivo', '0324455667', 'sitraka.ratsimba@example.com', 'Sitraka123', 2),
 ('Raveloarisoa', 'Miora', '1981-08-05', 'Lot XX D 22, Antananarivo', '0335566778', 'miora.raveloarisoa@example.com', 'Miora123', 2);
;

INSERT INTO eleve (nom, prenom, id_sexe, date_naissance, mdp, id_tuteur, identifiant, id_ecole, photo) VALUES
('Rasolofomanana', 'Ando', 1, '2007-03-12','Ando123', NULL, 'ando.rasolofomanana@lcsmi.itaosy', 2, NULL),
('Rajoelina', 'Emma', 2, '2006-08-19','Emma123', NULL, 'emma.rajoelina@lcsmi.itaosy', 2, NULL),
('Rakoto', 'Noah', 1, '2008-02-25','Noah123', NULL, 'noah.rakoto@lcsmi.itaosy', 2, NULL),
('Randriamalala', 'Lova', 2, '2009-01-15','Lova123', NULL, 'lova.randriamalala@lcsmi.itaosy', 2, NULL),
('Andrianarisoa', 'Lucas', 1, '2007-07-25','Lucas123', NULL, 'lucas.andrianarisoa@lcsmi.itaosy', 2, NULL),
('Rakotomalala', 'Alice', 2, '2006-12-30','Alice123', NULL,  'alice.rakotomalala@lcsmi.itaosy', 2, NULL),
('Randrianantenaina', 'Tiana', 1, '2008-05-10','Tiana123', NULL,  'tiana.randrianantenaina@lcsmi.itaosy', 2, NULL),
('Rasoamalala', 'Liam', 2, '2009-09-14','Liam123', NULL,  'liam.rasoamalala@lcsmi.itaosy', 2, NULL),
('Rakotomandimby', 'Chloe', 1, '2006-11-22','Chloe123', NULL,  'chloe.rakotomandimby@lcsmi.itaosy', 2, NULL),
('Ranaivoson', 'Miora', 2, '2008-03-12','Miora123', NULL,  'miora.ranaivoson@lcsmi.itaosy', 2, NULL),
('Ravelojaona', 'Lanto', 1, '2007-06-18','Lanto123', NULL,  'lanto.ravelojaona@lcsmi.itaosy', 2, NULL),
('Raharimalala', 'Hery', 2, '2006-09-09','Hery123', NULL,  'hery.raharimalala@lcsmi.itaosy', 2, NULL),
('Ravohangy', 'Soa', 1, '2009-04-21','Soa123', NULL,  'soa.ravohangy@lcsmi.itaosy', 2, NULL),
('Randriamanana', 'Antsa', 2, '2008-08-01','Antsa123', NULL,  'antsa.randriamanana@lcsmi.itaosy', 2, NULL),
('Rakotondraibe', 'Kanto', 1, '2007-12-17','Kanto123', NULL,  'kanto.rakotondraibe@lcsmi.itaosy', 2, NULL),
('Ravelonarivo', 'Sitraka', 2, '2006-07-29','Sitraka123', NULL,  'sitraka.ravelonarivo@lcsmi.itaosy', 2, NULL),
('Rasamimanana', 'Faly', 1, '2009-01-02','Faly123', NULL,  'faly.rasamimanana@lcsmi.itaosy', 2, NULL),
('Randriamanampisoa', 'Zo', 2, '2008-10-24','Zo123', NULL,  'zo.randriamanampisoa@lcsmi.itaosy', 2, NULL),
('Ratsimba', 'Niry', 1, '2007-04-15','Niry123', NULL,  'niry.ratsimba@lcsmi.itaosy', 2, NULL),
('Raveloarisoa', 'Hery', 2, '2006-11-05','Hery123', NULL,  'hery.raveloarisoa@lcsmi.itaosy', 2, NULL),
('Randrianarisoa', 'Fara', 1, '2008-05-17','Fara123', NULL, 'fara.randrianarisoa@lcsmi.itaosy', 2, NULL),
('Raharisoa', 'Mamy', 2, '2009-08-18','Mamy123', NULL, 'mamy.raharisoa@lcsmi.itaosy', 2, NULL),
('Rasolofoson', 'Niry', 1, '2007-03-23','Niry123', NULL, 'niry.rasolofoson@lcsmi.itaosy', 2, NULL),
('Rakotovao', 'Hasina', 2, '2006-10-12','Hasina123', NULL, 'hasina.rakotovao@lcsmi.itaosy', 2, NULL),
('Ravelonirina', 'Tiana', 1, '2008-12-20','Tiana123', NULL, 'tiana.ravelonirina@lcsmi.itaosy', 2, NULL);
;

INSERT INTO eleve_annee_scolaire (id_annee_scolaire, id_eleve, id_niveau, id_classe) VALUES
(1, 28, 27, NULL),
(1, 29, 29, NULL),
(1, 30, 27, NULL),
(1, 31, 29, NULL),
(1, 32, 27, NULL),
(1, 33, 29, NULL),
(1, 34, 27, NULL),
(1, 35, 29, NULL),
(1, 36, 27, NULL),
(1, 37, 29, NULL),
(1, 38, 27, NULL),
(1, 39, 29, NULL),
(1, 40, 27, NULL),
(1, 41, 29, NULL),
(1, 42, 27, NULL),
(1, 43, 29, NULL),
(1, 44, 27, NULL),
(1, 45, 29, NULL),
(1, 46, 27, NULL),
(1, 47, 29, NULL),
(1, 48, 27, NULL),
(1, 49, 29, NULL),
(1, 50, 27, NULL),
(1, 51, 29, NULL),
(1, 52, 27, NULL);


