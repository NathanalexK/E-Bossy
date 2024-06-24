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
            rank() over (partition by id_annee_scolaire order by date_debut desc) as id,
            *,
            case when date_debut < current_date and date_fin < current_date then 3
                 when date_debut < current_date and date_fin > current_date then 2
                 else 1
                end as status,
            date_debut::date - CURRENT_DATE as date_diff
from(select
         libelle,
         id as id_event,
         date_debut,
         date_fin,
         id_ecole,
         id_annee_scolaire,
         description,
         0 as type_evenement
     from evenement_scolaire
     union (SELECT
                nom_periode,
                id,
                date_debut,
                date_fin,
                id_ecole,
                id_annee_scolaire,
                null,
                1
            from periode_note)) as a;

-- Prendre les periiode ecolage non payer par eleve
SELECT pe.*
FROM eleve_annee_scolaire eas
         JOIN periode_ecolage pe ON eas.id_annee_scolaire = pe.id_annee_scolaire
         LEFT JOIN paye_ecolage p ON eas.id = p.id_eleve_annee_scolaire AND pe.id = p.id_periode_ecolage
WHERE p.id IS NULL and eas.id = 101;


