-- Get salles Disponibles


create or replace view v_salles_disponibles as
select
    *
from salle
where
    id not in (select id_salle from classe where salle.id_ecole = id_ecole);

select * from v_salles_disponibles;

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
