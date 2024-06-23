create or replace view v_note_general as
select
    note.id as id,
    id_eleve as id_eleve,
    id_periode_note,
    note,
    coefficient,
    appreciation,
    mn.id_niveau,
    mn.id_matiere,
    classe.id as id_classe,
    mp.id_prof,
    note * coefficient as note_general
from note
join prof_matiere mp
on note.id_matiere_prof = mp.id
join matiere
on mp.id_matiere = matiere.id
join classe
on mp.id_classe = classe.id
join matiere_niveau mn
on classe.id_niveau = mn.id_niveau and matiere.id = mn.id_matiere
;


create or replace view v_total_coef_par_niveau as
select
    id_niveau,
    sum(coefficient) as coef
from matiere_niveau
group by id_niveau
;


create or replace view v_total_note_par_eleve_and_periode as
select
    id_eleve,
    id_periode_note,
    id_niveau,
    id_classe,
    sum(note_general) as total
from v_note_general
group by id_eleve, id_periode_note, id_niveau, id_classe
;


create or replace view v_moyenne_par_eleve_et_periode as
select
    id_classe,
    id_eleve,
    id_periode_note,
    sum(total) / sum(coef.coef) as moyenne
from v_total_note_par_eleve_and_periode note
join v_total_coef_par_niveau coef
on coef.id_niveau = note.id_niveau
group by id_eleve, id_periode_note, id_classe
order by id_classe, id_periode_note
;

create or replace view v_rang_par_classe_et_periode as
select
    row_number() over () as id,
    rank() over (partition by id_classe, id_periode_note order by moyenne desc),
    count(*) over (partition by id_classe, id_periode_note) as participant,
    *
from v_moyenne_par_eleve_et_periode;


create or replace view v_moyenne_min_par_classe_et_periode as
select
    id_classe,
    id_periode_note,
    max(moyenne)
from v_moyenne_par_eleve_et_periode
group by id_classe, id_periode_note
;

create or replace view v_moyenne_max_par_classe_et_periode as
select
    id_classe,
    id_periode_note,
    min(moyenne)
from v_moyenne_par_eleve_et_periode
group by id_classe, id_periode_note
;

create or replace view v_moyenne_classe_par_classe_et_periode as
select
    id_classe,
    id_periode_note,
    avg(moyenne)
from v_moyenne_par_eleve_et_periode
group by id_classe, id_periode_note
;

create or replace view v_stat_classe_par_classe_et_periode as
select
    *,
    nombre_moyenne*100/total_eleve as pourcentage
from(
select
    id_classe,
    id_periode_note,
    max(moyenne),
    min(moyenne),
    avg(moyenne),
    count(*) filter ( where moyenne >= 10 ) as nombre_moyenne,
    count(*) as total_eleve
from v_moyenne_par_eleve_et_periode
group by id_periode_note, id_classe) as a;



select
    *
from matiere_niveau;
