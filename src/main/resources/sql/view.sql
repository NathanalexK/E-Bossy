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

--  trouver l'ID de la période d'écolage (periode_ecolage) pour une date donnée,
CREATE VIEW view_periode_ecolage_for_date AS
SELECT pe.id AS periode_ecolage_id, 
       pe.date_debut AS periode_date_debut,
       pe.date_fin AS periode_date_fin,
       pe.id_annee_scolaire,
       as.id_ecole
FROM periode_ecolage pe
JOIN annee_scolaire as ON pe.id_annee_scolaire = as.id;


-- tous les élèves qui n'ont pas payé leurs frais de scolarité pour un mois spécifique,
CREATE VIEW eleve_non_paye_ecolage AS
SELECT e.*
FROM eleve e
LEFT JOIN paye_ecolage pe ON e.id = pe.id_eleve
LEFT JOIN periode_ecolage p ON pe.id_periode_ecolage = p.id
WHERE 
    pe.date_payement IS NULL -- Aucun paiement effectué
    OR 
    (
        -- Ou le paiement est en dehors de la période d'écolage actuelle
        pe.date_payement < p.date_debut OR pe.date_payement > p.date_fin
    );

--  trouver l'ID de la période d'écolage (periode_ecolage) pour une date donnée,
CREATE VIEW v_periode_ecolage_for_date AS
SELECT pe.id AS _id,
       pe.date_debut AS date_debut,
       pe.date_fin AS date_fin,
       pe.id_annee_scolaire,
       ass.id_ecole
FROM periode_ecolage pe
         JOIN annee_scolaire ass ON pe.id_annee_scolaire = ass.id;

select * from v_periode_ecolage_for_date  where id_ecole = 2 and '2024-06-06' BETWEEN date_debut AND date_fin;

CREATE VIEW v_eleve_ecole AS
SELECT el.*,
       cl.id_ecole
FROM eleve el
         JOIN classe cl ON el.id_classe = cl.id;


-- tous les élèves qui n'ont pas payé leurs frais de scolarité pour un mois spécifique,
-- CREATE VIEW eleve_non_paye_ecolage AS
-- SELECT e.*,
--        cl.id_ecole as id_ecole
-- FROM eleve e
--          JOIN classe cl ON e.id_classe = cl.id
--          LEFT JOIN paye_ecolage pe ON e.id = pe.id_eleve
--          LEFT JOIN periode_ecolage p ON pe.id_periode_ecolage = p.id
-- WHERE
--     pe.date_payement IS NULL -- Aucun paiement effectué
--    OR
--     (
--         -- Ou le paiement est en dehors de la période d'écolage actuelle
--         pe.date_payement < p.date_debut OR pe.date_payement > p.date_fin
--         );

-- view comportements Eleve:
    CREATE OR REPLACE VIEW v_comportements_eleve AS
    SELECT 
        e.id As id,
        e.id_eleve As id_eleve,
        e.id_classe As id_classe,
        c.id As id_comportements
    FROM eleve_annee_scolaire e
    Cross JOIN comportements c; 