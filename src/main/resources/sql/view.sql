-- Get salles Disponibles


create or replace view v_salles_disponibles as
select
    *
from salle
where
    id not in (select id_salle from classe where salle.id_ecole = id_ecole);

select * from v_salles_disponibles;


create or replace view v_calendrier_scolaire as
select
    libelle,
    date_debut,
    date_fin,
    id_annee_scolaire,
    0 as type_evenement
from evenement_scolaire
union (SELECT
           nom_periode,
           date_debut,
           date_fin,
           id_annee_scolaire,
           1
       from periode_note)
