drop database if exists alumno;
create database alumno;
use alumno;

create table escuela(
	idEscuela int not null primary key,
    nombre varchar(120) not null
);


create table alumno(
	idAlumno int primary key not null,
    nombre varchar(120) not null,
    app varchar(120) not null,
    apm varchar(120) not null,
    idEscuela int not null,
    foreign key (idEscuela) references escuela (idEscuela)
);


drop procedure if exists spGuardaAlumno;


delimiter :v

create procedure spGuardaAlumno( in nomb varchar (120), in pate varchar (120), in mate varchar (120), in idEsc int)
begin
declare id int;
declare existe int;
declare msj varchar(120);

set existe=( select count(*) from alumno where concat(nombre, app, apm) = concat(nomb, pate, mate));
if existe = 0 then
	set existe=( select count(*) from escuela where idEscuela = idEsc);
    if  existe = 1 then
		set id= (select ifnull(max(idAlumno), 0) +1 from alumno );
    
		insert into alumno values(id, nomb, pate, mate, idEsc);
        set msj='Registrado exitosamente! C:';
    else
		set msj='No existe esa escuela';
    end if;
    
    
else
	set msj='Ya existe ese elumno';

end if;


select msj as mensaje;
end 
:v

delimiter ;



drop procedure if exists spGuardaEscuela;


delimiter :v

create procedure spGuardaEscuela( in nomb varchar (120))
begin
declare id int;
declare existe int;
declare msj varchar(120);

set existe=( select count(*) from escuela where  nombre = nomb);
if existe = 0 then
	
		set id= (select ifnull(max(idEscuela), 0) +1 from escuela );
    
		insert into escuela values (id, nomb); 
        set msj = 'se registro la escuela ';
    
else
	set msj='Ya existe esa escuela';

end if;


select msj as mensaje;
end 
:v

delimiter ;


call spGuardaAlumno('Joshua', 'SOria', 'lascares', 1);

call spGuardaEscuela('CECyT 9');



create view verEscuela as
	select idEscuela as id, nombre as nombre from escuela ;
    
select * from verEscuela;

select * from alumno;