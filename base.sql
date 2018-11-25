
/* les personnes */

CREATE TABLE IF NOT EXISTS Personne 
(
	id int not null,
	nom varchar(255) not null,
	prenom varchar(255) not null,
	adresse varchar(255) not null,
	noTelephone bigint not null,
	primary key (id)
);
CREATE TABLE IF NOT EXISTS Passager
(
	id int not null,
	numPasseport varchar(255) not null UNIQUE,
	foreign key (id) references Personne(id),
	primary key(id)
);

CREATE TABLE IF NOT EXISTS Personnel
( 
	id int not null,
	/* pas besoin d'un identifiant? car le numéro est unique */
	motDePasse varchar(255) not null,
	foreign key (id) references Personne(id),
	primary key(id)
);

/* personnel qui ne monte pas dans l'avion */
CREATE TABLE IF NOT EXISTS TypeNonNavigant
(
	id int not null,
	typeNonNavigant varchar(255) not null UNIQUE,
	primary key(id)
);
CREATE TABLE IF NOT EXISTS NonNavigant
(
	id int not null,
	typeNonNavigant int not null,
	foreign key (id) references Personnel(id),
	foreign key (typeNonNavigant) references TypeNonNavigant(id),
	primary key (id,typeNonNavigant)
);

/* personnel qui monte dans l'avion */
CREATE TABLE IF NOT EXISTS TypeNavigant
(
	id int not null,
	typeNavigant varchar(255) not null UNIQUE,
	primary key(id)
);

CREATE TABLE IF NOT EXISTS Navigant
(
	id int not null,
	typeNavigant int not null,
	foreign key (id) references Personnel(id),
	foreign key (typeNavigant) references TypeNavigant(id),
	primary key (id,typeNavigant)
);

CREATE TABLE IF NOT EXISTS Pilote
(
	id int not null,
	nombreHeureTotal time not null,
	/* ATTENTION : '-838:59:59' to '838:59:59'*/
	foreign key (id) references Navigant(id),
	primary key(id)
);

/* LES AVIONS */
CREATE TABLE IF NOT EXISTS TypeAvion
(
	id int not null,
	typeAvion varchar(255) not null,
	primary key(id)
);

CREATE TABLE IF NOT EXISTS Avion
(
	id int not null,
	nom varchar(255) not null,
	capacite int not null,
	typeAvion int not null,
	foreign key (typeAvion) references TypeAvion(id),	
	primary key(id)
);

/* temps de vol par type par pilote */
CREATE TABLE IF NOT EXISTS TempsVolType
(
	pilote varchar(255) not null,
	typeAvion int not null,
	temps time not null, 
	/* ATTENTION : '-838:59:59' to '838:59:59'*/
	foreign key (pilote) references Pilote(id),
	foreign key (typeAvion) references TypeAvion(id),
	primary key (pilote, typeAvion)
);
CREATE TABLE IF NOT EXISTS Troncon
(
	id int not null,
	villeDepart varchar(255) not null,
	villeArrivee varchar(255) not null,
	distance int not null,
	primary key (id)
);

CREATE TABLE IF NOT EXISTS Vol
(
	id int not null,
	frequence int not null,
	/* ?????? */
	primary key (id)
);


CREATE TABLE IF NOT EXISTS VolTroncon
(
	vol int not null,
	troncon int not null,
	heureDepart int not null,
	heureArrivee int not null,
	foreign key (vol) references Vol(id),
	foreign key (troncon) references Troncon(id),	
	primary key (vol,troncon)
);

CREATE TABLE IF NOT EXISTS Depart
(
	id int not null,
	dateDepart date not null,
	vol int not null,
	foreign key (vol) references Vol(id),	
	primary key(id)
);

CREATE TABLE IF NOT EXISTS DepartAvion
(
	depart int not null,
	avion int not null,
	qteCarburant int not null,
	foreign key (depart) references Depart(id),
	foreign key (avion) references Avion(id),
	primary key(depart,avion)
);

CREATE TABLE IF NOT EXISTS DepartPassager
(
	passager int not null,
	depart int not null,
	noPlace int not null,
	foreign key (passager) references Passager(id),	
	foreign key (depart) references Depart(id),	
	primary key(passager,depart)
);

/* il manque la table entre pilote et depart contenant le rapport */

CREATE TABLE IF NOT EXISTS RapportPilote
(
	pilote int not null,
	depart int not null,
	rapport text not null,
	foreign key (depart) references Depart(id),
	foreign key (pilote) references Pilote(id),
	primary key(depart,pilote)
);

/* A chaque insertion d'un temps pour un type le trigger va modifier le temps de vol total */ 
DELIMITER $
CREATE TRIGGER after_insert_TempsVolType AFTER INSERT
ON TempsVolType FOR EACH ROW
BEGIN
	UPDATE Pilote set nombreHeureTotal = (select nombreHeureTotal from Pilote where Pilote.id = NEW.pilote)+NEW.temps where Pilote.id = NEW.pilote;
END$
DELIMITER ;

/* A chaque modification d'un temps pour un type le trigger va modifier le temps de vol total */ 
DELIMITER $
CREATE TRIGGER after_update_TempsVolType AFTER UPDATE
ON TempsVolType FOR EACH ROW
BEGIN
	UPDATE Pilote set nombreHeureTotal = (select nombreHeureTotal from Pilote where Pilote.id = NEW.pilote)+NEW.temps-OLD.temps where Pilote.id = NEW.pilote;
END$
DELIMITER ;