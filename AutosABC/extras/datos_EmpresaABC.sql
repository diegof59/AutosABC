INSERT INTO sede VALUES('S1','Sede1','Cali','Carrera 1 # 34-42','12345','67890');

INSERT INTO usuario VALUES('Gerente','Gerente','54875685','Gerente','Principal','Nuestro','Programa','12345','67890','Carrera 1 # 34-42',null,'1992-05-03','Activo','S1','Gerente');
INSERT INTO usuario VALUES('Vendedor','Vendedor','5323933','Vendedor','Principal','Nuestro','Programa','12345','67890','Carrera 1 # 34-42',null,'1992-05-03','Activo','S1','Vendedor');
INSERT INTO usuario VALUES('JefTall','JefTall','78293562','JefeTaller','Principal','Nuestro','Programa','12345','67890','Carrera 1 # 34-42',null,'1992-05-03','Activo','S1','Jefe');

INSERT INTO carro VALUES ('1','Mazda','M200','2016',null,'True',25000000,'2000','2','Rojo','Nuevo','S1');
INSERT INTO cliente VALUES ('1472548369','Primer','Cliente','Del','Programa','245693154');
INSERT INTO cotizacion VALUES (1,'2016-12-10','1472548369',25000000,'Vendedor','S1','Efectivo de contado');
INSERT INTO venta VALUES (1,25000000,0,19,29750000,'10-12-2016','1472548369','Vendedor','S1','Efectivo de contado');
INSERT INTO carros_venta VALUES(1,'1');
INSERT INTO carros_por_cotizacion VALUES(1,'1');
INSERT INTO repuesto VALUES ('1','EmpresaMazda','Mazda','Repuesto marca Mazda',100,'S1');
INSERT INTO manejo_inventariorepuestos VALUES('JefTall','1',now(),'Traspaso');
INSERT INTO orden_trabajo VALUES(1,'22-12-2016','123456','Mazda','M001','2016','Repuesto_Mazda',200000,'JefTall','S1');
INSERT INTO repuestos_orden VALUES('1',1,'15-12-2016','100','S1','JefTall');

--------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO carro VALUES ('2','Chevrolet','Spark','2016',null,'False',18000000,'1400','2','Azul','Nuevo','S1');
INSERT INTO venta VALUES (2,43000000,0,19,51170000,'22-12-2016','1472548369','Vendedor','S1','Efectivo de contado');
INSERT INTO carros_venta VALUES(2,'1');
INSERT INTO carros_venta VALUES(2,'2');

INSERT INTO sede VALUES('S2','Sede2','Jamundi','Carrera 1 # 34-42','5973240','3152940');