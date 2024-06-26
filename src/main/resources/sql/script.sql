create or replace view v_note_general as
select
    note.id as id,
    note.id_eleve,
    eas.id_eleve as eas,
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
join eleve_annee_scolaire eas
on eas.id_eleve = eas.id_eleve and mp.id_annee_scolaire=eas.id_annee_scolaire
;

create or replace view v_note_with_annee_scolaire as
select
    note.id,
    id_eleve,
    note,
    id_periode_note,
    id_annee_scolaire
from note
join periode_note
on note.id_periode_note = periode_note.id;

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
    sum(note_general) as total,
    sum(coefficient) as coefficient
from v_note_general
group by id_eleve, id_periode_note, id_niveau, id_classe
;


create or replace view v_moyenne_par_eleve_et_periode as
select
    id_classe,
    id_eleve,
    id_periode_note,
    sum(total) / sum(coefficient) as moyenne
from v_total_note_par_eleve_and_periode note
group by id_eleve, id_periode_note, id_classe
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


create or replace view v_moyenne_generale_by_annee_scolaire as
select
    id_annee_scolaire,
    avg(moyenne)
from v_moyenne_par_eleve_et_periode v1
join periode_note pn
on v1.id_periode_note = pn.id
group by id_annee_scolaire
;



create or replace view v_dashboard_moyenne_par_periode as
select
    id_periode_note,
    id_annee_scolaire,
    avg(note)
from v_note_with_annee_scolaire
group by id_periode_note, id_annee_scolaire
;



create or replace view v_nombres_eleves_par_annee_scolaire as
select
    *
from annee_scolaire
join(
select
    id_annee_scolaire,
    count(*) FILTER ( WHERE  id_classe is not null)
from eleve_annee_scolaire
group by id_annee_scolaire)







create or replace view v_capacite_ecole_par_annee_scolaire as
select
    id_annee_scolaire,
    sum(capacite)
from salle
group by id_annee_scolaire;




create or replace view v_sexe_par_annee_scolaire as;
select
    *
--     coalesce(nombre, 0) as nombre
from annee_scolaire
left join(
select

    id_sexe,
    count(*) as nombre
from eleve_annee_scolaire eas
join eleve e
on eas.id_eleve = e.id
where eas.id_classe is not null
group by id_annee_scolaire, id_sexe) as a
on annee_scolaire.id = a.id_annee_scolaire
;

create or replace view v_eleve_qui_a_une_classe as
select
    *
from eleve_annee_scolaire
where id_classe is not null;


create or replace view v_sexe_par_annee_scolaire as
select
    a.id_annee_scolaire,
    a.id_sexe,
    coalesce(count, 0) as nombre
from ( select
        annee_scolaire.id as id_annee_scolaire,
        sexe.id as id_sexe
    from annee_scolaire
    left join sexe
    on true) as a
    left join (select
            id_annee_scolaire,
            id_sexe,
            count(*)
        from v_eleve_qui_a_une_classe eas
        join eleve
        on eleve.id = eas.id_eleve
        group by id_annee_scolaire, id_sexe ) as b
    on a.id_annee_scolaire = b.id_annee_scolaire and a.id_sexe = b.id_sexe
    ;





select
    id_eleve,
    id_annee_scolaire,
    avg(moyenne)
from v_moyenne_par_eleve_et_periode v1
join periode_note pn
on pn.id = v1.id_periode_note
group by id_annee_scolaire, id_eleve
;


create or replace view v_moyenne_par_matiere_et_annee_scolaire as
select
    id_annee_scolaire,
    id_matiere,
    avg(note)
from v_note_general v1
join periode_note pn
on pn.id = v1.id_periode_note
group by id_annee_scolaire, id_matiere
;

select
    *
from matiere_niveau;
