create database gestion_vie_scolaire;
alter database gestion_vie_scolaire owner to postgres;
\c gestion_vie_scolaire;

create table type_user(
    id serial primary key, 
    nom varchar(50)
);
create table type_ecole(
    id serial primary key,
    nom varchar(20)
);
create table ecole(
    id serial primary key, 
    nom_ecole varchar(50), 
    localisation varchar(50),
    id_type_ecole int references type_ecole(id)
);
create table sexe(
    id serial primary key, 
    type_sexe varchar(50)
);
create table salle(
    id serial primary key,
    numero varchar(30), 
    capacite int, 
    id_ecole int references ecole(id)
);
create table niveau(
    id serial primary key, 
    nom_niveau varchar(50),
    numero int,
    id_ecole int  references ecole(id)
);
create table dirigeant(
    id serial primary key, nom varchar(50), 
    prenom varchar(50), 
    date_naissance date, 
    adresse varchar(50), 
    conctact varchar(50) not null, 
    email varchar(50), 
    mdp varchar(50) not null, 
    id_ecole int references ecole(id), 
    id_sexe int references sexe(id)
);
create table professeur(
    id serial primary key, nom varchar(50), 
    prenom varchar(50), 
    date_naissance date, 
    adresse varchar(50), 
    conctact varchar(50) not null, 
    email varchar(50), 
    mdp varchar(50) not null, 
    id_ecole int references ecole(id), 
    id_sexe int references sexe(id)
);
create table classe(
    id serial primary key, 
    nom_classe varchar(50), 
    id_ecole int references ecole(id), 
    id_niveau int references niveau(id), 
    id_salle int references salle(id), 
    id_titulaire int references professeur(id)
);
create table secretaire(
    id serial primary key, nom varchar(50), 
    prenom varchar(50), 
    date_naissance date, 
    adresse varchar(50), 
    conctact varchar(50) not null, 
    email varchar(50), 
    mdp varchar(50) not null, 
    id_ecole int references ecole(id), 
    id_sexe int references sexe(id)
);
create table tuteur(
    id serial primary key, nom varchar(50), 
    prenom varchar(50), 
    date_naissance date, 
    adresse varchar(50), 
    conctact varchar(50) not null, 
    email varchar(50), 
    mdp varchar(50) not null, 
    id_ecole int references ecole(id), 
    id_sexe int references sexe(id)
);
create table eleve(
    id serial primary key, 
    nom varchar(50), 
    prenom varchar(50), 
    id_sexe int references sexe(id), 
    date_naissance date, 
    etablissement_origine varchar(40), 
    mdp varchar(50) not null, 
    id_classe int references classe(id), 
    id_tuteur int references tuteur(id)
);
create table matiere(
    id serial primary key, 
    nom_matiere varchar(50), 
    coefficient int not null, 
    id_niveau int references niveau(id),
    id_ecole int references ecole(id)
--     id_prof int references professeur(id)
);
create table evenement_scolaire(
    id serial primary key, 
    id_ecole int references ecole(id), 
    date_debut date, 
    date_fin date, 
    libelle varchar(50)
);
create table periode_scolaire(
    id serial primary key, 
    id_ecole int references ecole(id), 
    date_debut date, 
    date_fin date, 
    libelle varchar(50)
);
create table ecolage(
    id serial primary key, 
    id_ecole int references ecole(id), 
    id_niveau int references niveau(id),
    montant decimal(10,2) not null
);
create table annee_scolaire(
    id serial primary key,
    annee_debut int,
    anne_fin int
);
create table periode_note(
    id serial primary key,
    date_debut date,
    date_fin date,
    nom_periode varchar(30),
    id_annee_scolaire int references annee_scolaire(id)
);
create table note(
    id serial primary key, 
    id_ecole int references ecole(id), 
    id_classe int references classe(id), 
    id_matiere int references matiere(id), 
    id_eleve int references eleve(id), 
    note_attribue decimal(6,3) not null,
    id_periode_note int references periode_note(id)
);
create table contenus_pedagogique(
    id serial primary key, 
    id_classe int references classe(id), 
    url_fichier varchar(100),
    date_contenus date
);
create table periode_ecolage(
    id serial primary key,
    date_debut date,
    date_fin date,
    id_annee_scolaire int references annee_scolaire(id)
);
create table paye_ecolage(
    id serial primary key, 
    id_ecole int references ecole(id), 
    id_eleve int references eleve(id),
    date_payement date,
    id_periode_ecolage int references periode_ecolage(id)
);
create table absence(
    id serial primary key,  
    id_classe int references classe(id), 
    id_eleve int references eleve(id), 
    date_absence date
);
create table motif_absence(
    id serial primary key,
    id_classe int references classe(id),
    numero int references eleve(id),
    date_debut date,
    date_fin date,
    motif varchar(50)
);
create table convocation(
    id serial primary key, 
    id_ecole int references ecole(id), 
    id_eleve int references eleve(id), 
    date_convocation date, 
    motif varchar(200)
);
create table utilisateur(
    id bigserial primary key,
    mdp varchar(50),
    id_type_user int references type_user(id)
);
create table prof_matiere(
    id serial primary key,
    id_classe int references classe(id),
    id_matiere int references matiere(id),
    id_prof int references professeur(id)
);

-- create table communique(
--     id serial primary key,
--     id_type_user int references type_user(id),
--     id_classe int references classe(id),
--     id_utilisateur int references utilisateur(id),
--     motif varchar(50)
-- );