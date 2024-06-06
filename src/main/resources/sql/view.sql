-- Get salles Disponibles


create or replace view v_salles_disponibles as
select
    *
from salle
where
    id not in (select id_salle from classe where salle.id_ecole = id_ecole);

select * from v_salles_disponibles;

