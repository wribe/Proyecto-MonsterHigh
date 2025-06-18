use MonsterHigh;

insert into especies (especie, descripcion) 
values
('Animal', 'Cualquier monstruo que esté fusionado/tenga rasgos/provenga de un animal'),
('Muerto Viviente', 'Cualquier monstruo que esté muerto y haya revivido'),
('Otro', 'Cualquier monstruo que no sea ni muerto viviente (teoricamente muerto) o pueda incluirse en animal(animales mitológicos, mexclas de animal y normie...)');

insert into subespecies (subespecie, especiePrincipal, descipcion)
values
('Centauro', 'Animal', 'criatura que es mitad normie mitad caballo'),
('Ghoul', 'Animal', 'criatura que viene de las nieves'),
('Vampiro', 'Muerto Viviente', 'criatura inmortal que es una mezcla de humano y murciélago'),
('Licantropo', 'Animal', 'criatura mitad lobo mitad humano'),
('Monstruo', 'Muerto Viviente', 'criatura hecha por partes de mas criaturas'),
('Zombie', 'Muerto Viviente', 'criatura muerta en vida'),
('Monstruo Marino', 'Animal', 'criatura maritima');



INSERT INTO monstruito (nombre, especie, forma, color_piel, color_pelo, colmillos, gafas, alas, zombie)
VALUES 
('Draculaura', 'Vampiro', 'NORMIE', 'rosa', 'negro rosa', TRUE, FALSE, FALSE, FALSE),
('Frankie Stein', 'Monstruo', 'NORMIE', 'verde', 'blanco negro', FALSE, FALSE, FALSE, FALSE),
('Ghoulia Yelps', 'Zombie', 'NORMIE', 'gris', 'azul', FALSE, TRUE, FALSE, TRUE),
('Clawdeen Wolf', 'Licantropo', 'HIBRIDA', 'marron', 'marron', TRUE, FALSE, FALSE, FALSE),
('Lagoona Blue', 'Monstruo marino', 'HIBRIDA',  'azul', 'rubio', FALSE, FALSE, FALSE, FALSE);
