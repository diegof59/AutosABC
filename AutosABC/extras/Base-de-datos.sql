--

DROP TABLE IF EXISTS usuario CASCADE;
CREATE TABLE usuario(
	id_usuario VARCHAR(10) NOT NULL,
	password VARCHAR(16) NOT NULL,
	cedula VARCHAR(11) UNIQUE NOT NULL,
	primer_nombre VARCHAR(20),
	segundo_nombre VARCHAR(20),
	primer_apellido VARCHAR(20),
	segundo_apellido VARCHAR(20),
	telefono1 VARCHAR(20),   	
	telefono2 VARCHAR(20),	
	direccion VARCHAR(20),	
	foto VARCHAR(100),
	fecha_nacimiento DATE,
	estado VARCHAR(10),
	id_sede VARCHAR(10) NOT NULL,
	tipo VARCHAR(8),
	CONSTRAINT usuario_pk PRIMARY KEY (id_usuario)
);


DROP TABLE IF EXISTS sede CASCADE;
CREATE TABLE sede(
	id_sede VARCHAR(10),
	nombre VARCHAR(20),
	ciudad VARCHAR(20),
	direccion VARCHAR(30),
	telefono1 VARCHAR (10),
	telefono2 VARCHAR (10),
	CONSTRAINT sede_pk PRIMARY KEY (id_sede)
);

ALTER TABLE usuario ADD	CONSTRAINT sede_fk FOREIGN KEY (id_sede) REFERENCES sede (id_sede);


DROP TABLE IF EXISTS orden_trabajo CASCADE;
CREATE TABLE orden_trabajo(
	id_orden SERIAL,
	fecha_radicacion DATE,
	placa VARCHAR(7), 
	marca VARCHAR(15),
	referencia VARCHAR(15),
	modelo VARCHAR(15), 
	descripcion VARCHAR(50), 
	precio REAL,
	id_jefe_taller VARCHAR(10) NOT NULL,
	id_sede VARCHAR(10) NOT NULL, 
	CONSTRAINT orden_trabajo_pk PRIMARY KEY (id_orden),
	CONSTRAINT jefe_taller_fk FOREIGN KEY (id_jefe_taller) REFERENCES usuario (id_usuario),
	CONSTRAINT sede_fk FOREIGN KEY (id_sede) REFERENCES sede (id_sede)
);


DROP TABLE IF EXISTS repuesto CASCADE;
CREATE TABLE repuesto (
	id_repuesto VARCHAR (10),
	proveedor VARCHAR(20),
	marca VARCHAR(20),
	descripcion VARCHAR(20),
	cantidad INTEGER,
	id_sede VARCHAR(10),
	CONSTRAINT repuesto_pk PRIMARY KEY (id_repuesto, id_sede),
	CONSTRAINT sede_fk FOREIGN KEY  (id_sede) REFERENCES sede (id_sede)
);


DROP TABLE IF EXISTS repuestos_orden CASCADE;
CREATE TABLE repuestos_orden (
	id_repuesto VARCHAR(10),
	id_orden SERIAL,
	fecha DATE,
	cantidad INTEGER,
	id_sede VARCHAR(10),
	id_jefe_taller VARCHAR(10),
	CONSTRAINT repuestos_orden_pk PRIMARY KEY (id_repuesto, id_orden, fecha),
	CONSTRAINT repuesto_fk FOREIGN KEY (id_repuesto, id_sede) REFERENCES repuesto (id_repuesto, id_sede),
	CONSTRAINT orden_fk FOREIGN KEY (id_orden) REFERENCES orden_trabajo (id_orden),
	CONSTRAINT sede_fk FOREIGN KEY (id_sede) REFERENCES sede (id_sede),
	CONSTRAINT jefe_taller_fk FOREIGN KEY (id_jefe_taller) REFERENCES usuario (id_usuario)
);


DROP TABLE IF EXISTS carro CASCADE;
CREATE TABLE carro (
	id_carro VARCHAR (10),
	marca VARCHAR (15),
	referencia VARCHAR (15),
	modelo VARCHAR (15),
	foto VARCHAR(100),
	todo_terreno BOOLEAN,			
	precio REAL,
	peso VARCHAR(15),
	cilindraje VARCHAR(15),
	color VARCHAR(10),
	estado VARCHAR(10),
	id_sede VARCHAR(10) NOT NULL,
	CONSTRAINT carro_pk PRIMARY KEY (id_carro),
	CONSTRAINT sede_fk FOREIGN KEY (id_sede) REFERENCES sede (id_sede)
);


DROP TABLE IF EXISTS manejo_inventarioCarros CASCADE;
CREATE TABLE manejo_inventarioCarros (
	id_vendedor VARCHAR(10) NOT NULL,
	id_carro VARCHAR(10) NOT NULL,
	fecha_hora TIMESTAMP,
	tipo_moviento VARCHAR(8), --INGRESO o EGRESO
	CONSTRAINT manejo_inventarioCarros_pk PRIMARY KEY (id_vendedor, id_carro, fecha_hora),
	CONSTRAINT id_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES usuario (id_usuario),
	CONSTRAINT id_carro_fk FOREIGN KEY (id_carro) REFERENCES carro (id_carro)
);


DROP TABLE IF EXISTS manejo_inventarioRepuestos CASCADE;
CREATE TABLE manejo_inventarioRepuestos (
	id_jefe_taller VARCHAR(10) NOT NULL,
	id_repuesto VARCHAR(10) NOT NULL,
	fecha_hora TIMESTAMP,
	tipo_moviento VARCHAR(8),
	cantidad INTEGER,
	id_sede VARCHAR(10),
	CONSTRAINT manejo_inventarioRepuestos_pk PRIMARY KEY (id_jefe_taller, id_repuesto, fecha_hora),
	CONSTRAINT id_jefe_taller_fk FOREIGN KEY (id_jefe_taller) REFERENCES usuario (id_usuario),
	CONSTRAINT id_repuesto_fk FOREIGN KEY (id_repuesto, id_sede) REFERENCES repuesto (id_repuesto, id_sede)
);


DROP TABLE IF EXISTS cliente CASCADE;
CREATE TABLE cliente(
	cedula VARCHAR(15),
	primer_nombre_cliente VARCHAR(20),
	segundo_nombre_cliente VARCHAR(20),
	primer_apellido_cliente VARCHAR(20),
	segundo_apellido_cliente VARCHAR(20),
	telefono_cliente VARCHAR(10),
	CONSTRAINT cliente_pk PRIMARY KEY (cedula)
);

DROP TABLE IF EXISTS cotizacion CASCADE;
CREATE TABLE cotizacion (
	id_cotizacion SERIAL,
	fecha DATE NOT NULL,
	cedula_cliente VARCHAR (15),
	valor REAL NOT NULL,
	id_vendedor VARCHAR(10) NOT NULL,
	id_sede VARCHAR(10) NOT NULL,
	forma_pago VARCHAR(20),
	cuota_inicial REAL,
	cuota REAL,
	numero_cuotas INTEGER,
	CONSTRAINT cotizacion_pk PRIMARY KEY (id_cotizacion),
	CONSTRAINT cedula_fk FOREIGN KEY (cedula_cliente) REFERENCES cliente (cedula),
	CONSTRAINT id_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES usuario (id_usuario),
	CONSTRAINT sede_fk FOREIGN KEY (id_sede) REFERENCES sede (id_sede)
);


DROP TABLE IF EXISTS carros_por_cotizacion CASCADE;
CREATE TABLE carros_por_cotizacion (
	id_cotizacion INTEGER,
	id_carro VARCHAR (10) NOT NULL,
	CONSTRAINT carros_por_cotizacion_pk PRIMARY KEY (id_cotizacion, id_carro)
);


DROP TABLE IF EXISTS venta CASCADE;
CREATE TABLE venta(
    id_venta SERIAL,
    subtotal REAL,
	descuentos REAL,
    iva integer,
    precio_total REAL,
    fecha_venta date,
    id_cliente character varying(15),
    id_vendedor character varying(10),
	id_sede VARCHAR(10),
	forma_pago character varying(20),
	cuota_inicial REAL,
	numero_cuotas integer,
    valor_cuotas REAL,
    CONSTRAINT venta_pk PRIMARY KEY (id_venta),
    CONSTRAINT cliente_fk FOREIGN KEY (id_cliente)
        REFERENCES cliente (cedula) ,
    CONSTRAINT vendedor_fk FOREIGN KEY (id_vendedor)
        REFERENCES usuario (id_usuario),
	CONSTRAINT sede_fk FOREIGN KEY (id_sede) REFERENCES sede (id_sede)
);


DROP TABLE IF EXISTS carros_venta CASCADE;
CREATE TABLE carros_venta (
	id_venta INTEGER,
	id_carro VARCHAR(10) NOT NULL,
	descripcion VARCHAR(100),
	CONSTRAINT carros_venta_pk PRIMARY KEY (id_venta,id_carro),
	CONSTRAINT id_venta_fk FOREIGN KEY (id_venta) REFERENCES venta(id_venta),
	CONSTRAINT id_carro_fk FOREIGN KEY (id_carro) REFERENCES carro(id_carro)	
);


