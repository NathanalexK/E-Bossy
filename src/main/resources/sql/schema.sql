create database gestion_vie_scolaire;
gestion_vie_scolaire;

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
-- create table annee_scolaire(
--     id serial primary key,
--     annee_debut int,
--     anne_fin int
-- );
create table annee_scolaire (
    id serial primary key,
    id_ecole int references ecole(id),
    nom varchar(20),
    date_debut date,
    date_fin date
);
-- alter table  annee_scolaire add column date_fin date;

create table periode_note(
    id serial primary key,
    date_debut date,
    date_fin date,
    nom_periode varchar(30),
    id_ecole int references ecole(id),
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
alter table periode_ecolage add column nom varchar(40);
drop table paye_ecolage;

create table paye_ecolage(
     id serial primary key,
     id_eleve_annee_scolaire int references eleve_annee_scolaire(id),
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

alter table utilisateur add column email varchar(40);

alter table matiere drop column id_niveau;
alter table matiere drop column coefficient;
alter table ecole add column id_annee_scolaire int references annee_scolaire(id);
alter table evenement_scolaire add column  id_annee_scolaire int references annee_scolaire(id);

create table matiere_niveau (
    id serial primary key,
    id_niveau int references niveau(id),
    id_matiere int references matiere(id),
    coefficient int,
    volume_horaire int
);

create table annee_scolaire_actuel (
    id serial primary key,
    id_ecole int unique references ecole(id),
    id_annee_scolaire int references annee_scolaire(id)
)

create table status(
    id int primary key,
    nom varchar(20)
);

alter table tuteur drop column id_ecole;
alter table eleve add column identifiant varchar(100);
alter table evenement_scolaire add column description text;

alter table eleve add column id_niveau int references niveau(id);
alter table professeur add column debut_carriere date;
alter table professeur add column identifiant varchar(100);
alter table prof_matiere add column id_annee_scolaire int references annee_scolaire(id);

alter table note drop column id_ecole;

alter table note drop column note_attribue;
alter table note add column note decimal(4,2) check ( note >= 0 and note <= 20 );
alter table note add column appreciation text;

alter table eleve drop column id_classe;
alter table eleve drop column id_niveau;

create table eleve_annee_scolaire(
    id serial primary key,
    id_annee_scolaire int references annee_scolaire(id),
    id_eleve int references eleve(id),
    id_niveau int references niveau(id),
    id_classe int references classe(id)
);

alter table eleve drop column etablissement_origine;
alter table eleve add column id_ecole int references ecole(id);

alter table niveau add column id_annee_scolaire int references annee_scolaire(id);

alter table eleve add column photo varchar(255);

alter table note drop column id_classe;
alter table note drop column id_matiere;

alter table note add column id_matiere_prof int references prof_matiere(id);
alter table note add constraint check_unique_note UNIQUE (id_eleve, id_periode_note, id_matiere_prof);

alter table dirigeant add column identifiant varchar(100) unique;

alter table salle add column id_annee_scolaire int references annee_scolaire(id);


-- create table communique(
--     id serial primary key,
--     id_type_user int references type_user(id),
--     id_classe int references classe(id),
--     id_utilisateur int references utilisateur(id),
--     motif varchar(50)
-- );