-- ============================================================
-- ====================== TABLAS ======================
  CREATE TABLE PROVEEDORES(
   ID_PROVEEDOR NUMBER(22) PRIMARY KEY,
   NOMBRE VARCHAR2(25),
   NUM_TELEFONO VARCHAR2(10),
   DIRECCION VARCHAR2(100),
   DETALLES VARCHAR2(200)
);

CREATE TABLE TIPO_PRODUCTO(
   ID_TIPO_PRODUCTO NUMBER(10) PRIMARY KEY,
   NOMBRE VARCHAR2(25),
   DETALLES VARCHAR2(100)
);

CREATE TABLE EMPLEADOS(
   ID_EMPLEADO NUMBER(22) PRIMARY KEY,
   NOMBRE VARCHAR2(25),
   APELLIDO VARCHAR2(25),
   DIRECCION VARCHAR2(100),
   NUM_TELEFONO VARCHAR2(10),
   CORREO_ELECT VARCHAR2(25),
   SALARIO VARCHAR2(1000),
   FECHA_INGRE VARCHAR2(100),
   FECHA_NAC VARCHAR2(100)
);

CREATE TABLE CLIENTES (
   ID_CLIENTE NUMBER (22) PRIMARY KEY,
   USERNAME VARCHAR2 (25),
   CONTRASENA VARCHAR2 (25),
   NOMBRE VARCHAR2 (25),
   APELLIDO VARCHAR2 (25),
   DIRECCION VARCHAR2 (100),
   NUM_TELEFONO VARCHAR2 (10),
   CORREO_ELECT VARCHAR2 (25)
);

CREATE TABLE TIPO_TRABAJO (
   ID_TIPO_TRABAJO NUMBER (10) PRIMARY KEY,
   NOMBRE VARCHAR2 (25),
   REQUISITOS VARCHAR2 (25),
   CONTENIDO VARCHAR2 (100),
   DETALLES VARCHAR2 (100)
);

CREATE TABLE VEHICULOS (
   NUM_PLACA VARCHAR2 (22) PRIMARY KEY,
   NUM_MOTOR NUMBER(15),
   MARCA VARCHAR2 (12),
   COLOR VARCHAR2 (10),
   MODELO VARCHAR2 (10),
   ANO VARCHAR(4)
);

CREATE TABLE VALORACIONES (
   ID_VALORACION NUMBER (10) PRIMARY KEY,
   ID_CLIENTE NUMBER (22),
   COMENTARIO VARCHAR2 (150),
   VALORACION NUMBER(5),
   FECHA_EMI varchar2(100),
   FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES (ID_CLIENTE)
);

CREATE TABLE INVENTARIO(
   ID_PRODUCTO NUMBER(10) PRIMARY KEY,
   ID_TIPO_PRODUCTO NUMBER(10),
   NOMBRE VARCHAR2(100),
   FECHA_INGRE varchar2(100),
   STOCK NUMBER,
   DETALLES VARCHAR2(100),
   CONSTRAINT FK_INVENTARIO FOREIGN KEY (ID_TIPO_PRODUCTO) REFERENCES TIPO_PRODUCTO(ID_TIPO_PRODUCTO)
);

CREATE TABLE TRABAJOS (
   ID_TRABAJO NUMBER (22) PRIMARY KEY,
   ID_TIPO_TRABAJO NUMBER (22),
   FECHA VARCHAR(100),
   ID_CLIENTE NUMBER (22),
   NUM_PLACA VARCHAR2 (100),
   CANT_PRODUCTOS NUMBER (10),
   ID_EMPLEADO NUMBER (22),
   FOREIGN KEY (ID_TIPO_TRABAJO) REFERENCES TIPO_TRABAJO (ID_TIPO_TRABAJO),
   FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES (ID_CLIENTE),
   FOREIGN KEY (NUM_PLACA) REFERENCES VEHICULOS (NUM_PLACA),
   FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADOS (ID_EMPLEADO)
);

-- ============================================================
-- ===================== INSERTS ======================
-- PROVEEDORES Table
INSERT INTO PROVEEDORES (ID_PROVEEDOR, NOMBRE, NUM_TELEFONO, DIRECCION, DETALLES) VALUES (1, 'ABC Electronics', '1234567890', '123 Main St, City', 'Electronics supplier');
INSERT INTO PROVEEDORES (ID_PROVEEDOR, NOMBRE, NUM_TELEFONO, DIRECCION, DETALLES) VALUES (2, 'Fashion World', '9876543210', '456 Fashion Ave, Town', 'Clothing supplier');

-- TIPO_PRODUCTO Table
INSERT INTO TIPO_PRODUCTO (ID_TIPO_PRODUCTO, NOMBRE, DETALLES) VALUES (1, 'Electronics', 'Various electronic products');
INSERT INTO TIPO_PRODUCTO (ID_TIPO_PRODUCTO, NOMBRE, DETALLES) VALUES (2, 'Clothing', 'Fashion and apparel');

--  EMPLEADOS Table
INSERT INTO EMPLEADOS(ID_EMPLEADO, NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT, SALARIO, FECHA_INGRE, FECHA_NAC)
VALUES (1, 'Juan', 'González', 'Calle A 123', '1234567890', 'juan.gonzalez@example.com', 50000, '2023-01-01', '1990-01-15');
INSERT INTO EMPLEADOS(ID_EMPLEADO, NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT, SALARIO, FECHA_INGRE, FECHA_NAC)
VALUES (2, 'Maria', 'Lopez', 'Avenida B 456', '9876543210', 'maria.lopez@example.com', 60000, '2023-02-01', '1985-02-20');
INSERT INTO EMPLEADOS(ID_EMPLEADO, NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT, SALARIO, FECHA_INGRE, FECHA_NAC)
VALUES (5, 'Ana', 'Martinez', 'Avenida D 012', '1112223333', 'ana.martinez@example.com', 80000, '2023-04-01', '1991-12-05');

-- CLIENTES Table
INSERT INTO CLIENTES (ID_CLIENTE, USERNAME, CONTRASENA, NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT) VALUES (1, 'user1', 'password1', 'Alice', 'Johnson', '123 Maple St, City', '5551111', 'alice@example.com');
INSERT INTO CLIENTES (ID_CLIENTE, USERNAME, CONTRASENA, NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT) VALUES (2, 'user2', 'password2', 'Bob', 'Smith', '456 Oak St, Town', '5552222', 'bob@example.com');

-- TIPO_TRABAJO Table
INSERT INTO TIPO_TRABAJO (ID_TIPO_TRABAJO, NOMBRE, REQUISITOS, CONTENIDO, DETALLES) VALUES (1, 'Repair', 'Mechanical skills', 'Fixing and repairing products', 'General repair tasks');
INSERT INTO TIPO_TRABAJO (ID_TIPO_TRABAJO, NOMBRE, REQUISITOS, CONTENIDO, DETALLES) VALUES (2, 'Assembly', 'Assembly skills', 'Assembling products', 'Putting together components');

-- VEHICULOS Table
INSERT INTO VEHICULOS (NUM_PLACA, NUM_MOTOR, MARCA, COLOR, MODELO, AnO) VALUES ('ABC123', 123456789, 'Toyota', 'Blue', 'Camry', '2020');
INSERT INTO VEHICULOS (NUM_PLACA, NUM_MOTOR, MARCA, COLOR, MODELO, AnO) VALUES ('XYZ789', 987654321, 'Ford', 'Red', 'Mustang', '2022');
commit;

-- Valoraciones Table
INSERT INTO VALORACIONES (ID_VALORACION, ID_CLIENTE, COMENTARIO, VALORACION, FECHA_EMI) VALUES (1, 1, 'Great service!', 5, '2023-02-01');
INSERT INTO VALORACIONES (ID_VALORACION, ID_CLIENTE, COMENTARIO, VALORACION, FECHA_EMI) VALUES (2, 2, 'Quick and efficient', 4, '2023-03-04');

-- INVENTARIO Table
INSERT INTO INVENTARIO (ID_PRODUCTO, ID_TIPO_PRODUCTO, NOMBRE, FECHA_INGRE, STOCK, DETALLES) VALUES (1, 1, 'Laptop', '2023-03-01', 50, 'High-end laptops');
INSERT INTO INVENTARIO (ID_PRODUCTO, ID_TIPO_PRODUCTO, NOMBRE, FECHA_INGRE, STOCK, DETALLES) VALUES (2, 2, 'T-shirt', '2023-03-05', 100, 'Cotton T-shirts');

-- TRABAJOS Table
INSERT INTO TRABAJOS (ID_TRABAJO, ID_TIPO_TRABAJO, FECHA, ID_CLIENTE, NUM_PLACA, CANT_PRODUCTOS, ID_EMPLEADO) VALUES (1, 1, '2023-04-01', 1, 'ABC123', 2, 1);
INSERT INTO TRABAJOS (ID_TRABAJO, ID_TIPO_TRABAJO, FECHA, ID_CLIENTE, NUM_PLACA, CANT_PRODUCTOS, ID_EMPLEADO) VALUES (2, 2, '2023-04-05', 2, 'XYZ789', 3, 2);

-- ============================================================
-- ================ PROCEDIMIENTOS Y CRUD ======================

--                          CRUD PROVEDORES

-- ==============================SELECT===============
CREATE OR REPLACE PROCEDURE seleccionar_proveedor(
    p_id_proveedor IN NUMBER,
    p_nombre OUT VARCHAR2,
    p_num_telefono OUT VARCHAR2,
    p_direccion OUT VARCHAR2,
    p_detalles OUT VARCHAR2
)
IS
BEGIN
    SELECT Nombre, Num_Telefono, Direccion, Detalles
    INTO p_nombre, p_num_telefono, p_direccion, p_detalles
    FROM proveedores
    WHERE ID_PROVEEDOR = p_id_proveedor;
END;

-- ==============================DELETE==========
CREATE OR REPLACE PROCEDURE eliminar_proveedor(
    p_id_proveedor IN NUMBER
)
IS
BEGIN
    DELETE FROM proveedores
    WHERE ID_PROVEEDOR = p_id_proveedor;
    COMMIT;
END;

-- ==============================UPDATE==============
CREATE OR REPLACE PROCEDURE actualizar_proveedor(
    p_id_proveedor IN NUMBER,
    p_nombre IN VARCHAR2,
    p_num_telefono IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    UPDATE proveedores
    SET Nombre = p_nombre, Num_Telefono = p_num_telefono, Direccion = p_direccion, Detalles = p_detalles
    WHERE ID_PROVEEDOR = p_id_proveedor;
    COMMIT;
END;

-- ===============================INSERT============
CREATE OR REPLACE PROCEDURE insertar_proveedor(
    p_id_proveedor IN NUMBER,
    p_nombre IN VARCHAR2,
    p_num_telefono IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    INSERT INTO proveedores(ID_PROVEEDOR, Nombre, Num_Telefono, Direccion, Detalles)
    VALUES (p_id_proveedor, p_nombre, p_num_telefono, p_direccion, p_detalles);
    COMMIT;
END;

-- ==============================OBTENER ULTIMO===========
CREATE OR REPLACE PROCEDURE ObtenerUltimoProveedor(
    p_id_proveedor OUT NUMBER
)
AS  
BEGIN
    SELECT ID_PROVEEDOR
    INTO p_id_proveedor
    FROM proveedores
    ORDER BY ID_PROVEEDOR DESC
    FETCH FIRST 1 ROWS ONLY;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD Tipo Producto

-- ==============================SELECT===========
CREATE OR REPLACE PROCEDURE seleccionar_tipo_producto(
    p_id IN NUMBER,
    p_nombre OUT VARCHAR2,
    p_detalles OUT VARCHAR2
)
IS
BEGIN
    SELECT NOMBRE, DETALLES INTO p_nombre, p_detalles
    FROM TIPO_PRODUCTO
    WHERE ID_TIPO_PRODUCTO = p_id;
END;
-- ==============================DELETE===========
CREATE OR REPLACE PROCEDURE eliminar_tipo_producto(
    p_id IN NUMBER
)
IS
BEGIN
    DELETE FROM TIPO_PRODUCTO
    WHERE ID_TIPO_PRODUCTO = p_id;
    COMMIT;
END;
-- ==============================UPDATE===========
CREATE OR REPLACE PROCEDURE actualizar_tipo_producto(
    p_id IN NUMBER,
    p_nombre IN VARCHAR2,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    UPDATE TIPO_PRODUCTO
    SET NOMBRE = p_nombre, DETALLES = p_detalles
    WHERE ID_TIPO_PRODUCTO = p_id;
    COMMIT;
END;
-- ==============================INSERT===========
CREATE OR REPLACE PROCEDURE insertar_tipo_producto(
    p_id IN NUMBER,
    p_nombre IN VARCHAR2,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    INSERT INTO TIPO_PRODUCTO(ID_TIPO_PRODUCTO, NOMBRE, DETALLES)
    VALUES (p_id, p_nombre, p_detalles);
    COMMIT;
END;
-- ==============================ULTIMO===========
CREATE OR REPLACE PROCEDURE obtener_ultimo_tipo_producto(
    p_id OUT NUMBER
)
AS  
BEGIN
    SELECT ID_TIPO_PRODUCTO
    INTO p_id
    FROM TIPO_PRODUCTO
    ORDER BY ID_TIPO_PRODUCTO DESC
    FETCH FIRST 1 ROWS ONLY;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD  Empleado

-- ==============================SELECT===========
CREATE OR REPLACE PROCEDURE seleccionar_empleado(
    p_id_empleado IN NUMBER,
    p_nombre OUT VARCHAR2,
    p_apellido OUT VARCHAR2,
    p_direccion OUT VARCHAR2,
    p_num_telefono OUT VARCHAR2,
    p_correo OUT VARCHAR2,
    p_salario OUT VARCHAR2,
    p_fecha_ingreso OUT VARCHAR2,
    p_fecha_nacimiento OUT VARCHAR2
)
IS
BEGIN
    SELECT Nombre, Apellido, Direccion, Num_Telefono, Correo_Elect, Salario, Fecha_Ingre, Fecha_Nac
    INTO p_nombre, p_apellido, p_direccion, p_num_telefono, p_correo, p_salario, p_fecha_ingreso, p_fecha_nacimiento
    FROM empleados
    WHERE ID_EMPLEADO = p_id_empleado;
END;

-- ==============================DELETE==============
CREATE OR REPLACE PROCEDURE eliminar_empleado(
    p_id_empleado IN NUMBER
)
IS
BEGIN
    DELETE FROM empleados
    WHERE ID_EMPLEADO = p_id_empleado;
    COMMIT;
END;

-- ==============================UPDATE==============
CREATE OR REPLACE PROCEDURE actualizar_empleado(
    p_id_empleado IN NUMBER,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_num_telefono IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_salario IN VARCHAR2,
    p_fecha_ingreso IN VARCHAR2,
    p_fecha_nacimiento IN VARCHAR2
)
IS
BEGIN
    UPDATE empleados
    SET Nombre = p_nombre,
        Apellido = p_apellido,
        Direccion = p_direccion,
        Num_Telefono = p_num_telefono,
        Correo_Elect = p_correo,
        Salario = p_salario,
        Fecha_Ingre = p_fecha_ingreso,
        Fecha_Nac = p_fecha_nacimiento
    WHERE ID_EMPLEADO = p_id_empleado;
    COMMIT;
END;

-- ===============================INSERT==============
CREATE OR REPLACE PROCEDURE insertar_empleado(
    p_id_empleado IN NUMBER,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_num_telefono IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_salario IN VARCHAR2,
    p_fecha_ingreso IN VARCHAR2,
    p_fecha_nacimiento IN VARCHAR2
)
IS
BEGIN
    INSERT INTO empleados(ID_EMPLEADO, Nombre, Apellido, Direccion, Num_Telefono, Correo_Elect, Salario, Fecha_Ingre, Fecha_Nac)
    VALUES (p_id_empleado, p_nombre, p_apellido, p_direccion, p_num_telefono, p_correo, p_salario, p_fecha_ingreso, p_fecha_nacimiento);
    COMMIT;
END;

-- ==============================OBTENER ULTIMO============
CREATE OR REPLACE PROCEDURE ObtenerUltimoEmpleado(
    p_id_empleado OUT NUMBER
)
AS  
BEGIN
    SELECT ID_EMPLEADO
    INTO p_id_empleado
    FROM empleados
    ORDER BY ID_EMPLEADO DESC
    FETCH FIRST 1 ROWS ONLY;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD Clientes

-- ==============================SELECT============
CREATE OR REPLACE PROCEDURE seleccionar_cliente(
    p_id_cliente IN NUMBER,
    p_username OUT VARCHAR2,
    p_contrasena OUT VARCHAR2,
    p_nombre OUT VARCHAR2,
    p_apellido OUT VARCHAR2,
    p_direccion OUT VARCHAR2,
    p_num_telefono OUT VARCHAR2,
    p_correo_elect OUT VARCHAR2
)
IS
BEGIN
    SELECT USERNAME, CONTRASENA, NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT
    INTO p_username, p_contrasena, p_nombre, p_apellido, p_direccion, p_num_telefono, p_correo_elect
    FROM CLIENTES
    WHERE ID_CLIENTE = p_id_cliente;
END;

-- ==============================DELETE=========
CREATE OR REPLACE PROCEDURE eliminar_cliente(
    p_id_cliente IN NUMBER
)
IS
BEGIN
    DELETE FROM CLIENTES
    WHERE ID_CLIENTE = p_id_cliente;
    COMMIT;
END;

-- ==============================UPDATE============
CREATE OR REPLACE PROCEDURE actualizar_cliente(
    p_id_cliente IN NUMBER,
    p_username IN VARCHAR2,
    p_contrasena IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_num_telefono IN VARCHAR2,
    p_correo_elect IN VARCHAR2
)
IS
BEGIN
    UPDATE CLIENTES
    SET USERNAME = p_username,
        CONTRASENA = p_contrasena,
        NOMBRE = p_nombre,
        APELLIDO = p_apellido,
        DIRECCION = p_direccion,
        NUM_TELEFONO = p_num_telefono,
        CORREO_ELECT = p_correo_elect
    WHERE ID_CLIENTE = p_id_cliente;
    COMMIT;
END;

-- ===============================INSERT=============
CREATE OR REPLACE PROCEDURE insertar_cliente(
    p_id_cliente IN NUMBER,
    p_username IN VARCHAR2,
    p_contrasena IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_num_telefono IN VARCHAR2,
    p_correo_elect IN VARCHAR2
)
IS
BEGIN
    INSERT INTO CLIENTES(ID_CLIENTE, USERNAME, CONTRASENA, NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT)
    VALUES (p_id_cliente, p_username, p_contrasena, p_nombre, p_apellido, p_direccion, p_num_telefono, p_correo_elect);
    COMMIT;
END;

-- ==============================OBTENER ULTIMO=======
CREATE OR REPLACE PROCEDURE ObtenerUltimoCliente(
    p_id_cliente OUT NUMBER
)
AS
BEGIN
    SELECT ID_CLIENTE
    INTO p_id_cliente
    FROM CLIENTES
    ORDER BY ID_CLIENTE DESC
    FETCH FIRST 1 ROWS ONLY;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD Tipo Trabajo

-- ==============================SELECT=================
CREATE OR REPLACE PROCEDURE seleccionar_tipo_trabajo(
    p_id_tipo_trabajo IN NUMBER,
    p_nombre OUT VARCHAR2,
    p_requisitos OUT VARCHAR2,
    p_contenido OUT VARCHAR2,
    p_detalles OUT VARCHAR2
)
IS
BEGIN
    SELECT NOMBRE, REQUISITOS, CONTENIDO, DETALLES
    INTO p_nombre, p_requisitos, p_contenido, p_detalles
    FROM TIPO_TRABAJO
    WHERE ID_TIPO_TRABAJO = p_id_tipo_trabajo;
END;

-- ==============================DELETE================
CREATE OR REPLACE PROCEDURE eliminar_tipo_trabajo(
    p_id_tipo_trabajo IN NUMBER
)
IS
BEGIN
    DELETE FROM TIPO_TRABAJO
    WHERE ID_TIPO_TRABAJO = p_id_tipo_trabajo;
    COMMIT;
END;

-- ==============================UPDATE=============
CREATE OR REPLACE PROCEDURE actualizar_tipo_trabajo(
    p_id_tipo_trabajo IN NUMBER,
    p_nombre IN VARCHAR2,
    p_requisitos IN VARCHAR2,
    p_contenido IN VARCHAR2,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    UPDATE TIPO_TRABAJO
    SET NOMBRE = p_nombre,
        REQUISITOS = p_requisitos,
        CONTENIDO = p_contenido,
        DETALLES = p_detalles
    WHERE ID_TIPO_TRABAJO = p_id_tipo_trabajo;
    COMMIT;
END;

-- ===============================INSERT==============
CREATE OR REPLACE PROCEDURE insertar_tipo_trabajo(
    p_id_tipo_trabajo IN NUMBER,
    p_nombre IN VARCHAR2,
    p_requisitos IN VARCHAR2,
    p_contenido IN VARCHAR2,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    INSERT INTO TIPO_TRABAJO(ID_TIPO_TRABAJO, NOMBRE, REQUISITOS, CONTENIDO, DETALLES)
    VALUES (p_id_tipo_trabajo, p_nombre, p_requisitos, p_contenido, p_detalles);
    COMMIT;
END;

-- ==============================OBTENER ULTIMO============
CREATE OR REPLACE PROCEDURE ObtenerUltimoTipoTrabajo(
    p_id_tipo_trabajo OUT NUMBER
)
AS
BEGIN
    SELECT ID_TIPO_TRABAJO
    INTO p_id_tipo_trabajo
    FROM TIPO_TRABAJO
    ORDER BY ID_TIPO_TRABAJO DESC
    FETCH FIRST 1 ROWS ONLY;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD Vehiculos

-- ==============================SELECT===============
CREATE OR REPLACE PROCEDURE seleccionar_vehiculo(
    p_num_placa IN VARCHAR2,
    p_num_motor OUT VARCHAR2,
    p_marca OUT VARCHAR2,
    p_color OUT VARCHAR2,
    p_modelo OUT VARCHAR2,
    p_year OUT VARCHAR2
)
IS
BEGIN
    SELECT NUM_MOTOR, MARCA, COLOR, MODELO, ANO
    INTO p_num_motor, p_marca, p_color, p_modelo, p_year
    FROM VEHICULOS
    WHERE NUM_PLACA = p_num_placa;
END;

-- ==============================DELETE====================
CREATE OR REPLACE PROCEDURE eliminar_vehiculo(
    p_num_placa IN VARCHAR2
)
IS
BEGIN
    DELETE FROM VEHICULOS
    WHERE NUM_PLACA = p_num_placa;
    COMMIT;
END;

-- ==============================UPDATE==============
CREATE OR REPLACE PROCEDURE actualizar_vehiculo(
    p_num_placa IN VARCHAR2,
    p_num_motor IN VARCHAR2,
    p_marca IN VARCHAR2,
    p_color IN VARCHAR2,
    p_modelo IN VARCHAR2,
    p_year IN VARCHAR2
)
IS
BEGIN
    UPDATE VEHICULOS
    SET NUM_MOTOR = p_num_motor,
        MARCA = p_marca,
        COLOR = p_color,
        MODELO = p_modelo,
        ano = p_year
    WHERE NUM_PLACA = p_num_placa;
    COMMIT;
END;

-- ===============================INSERT==============
CREATE OR REPLACE PROCEDURE insertar_vehiculo(
    p_num_placa IN VARCHAR2,
    p_num_motor IN VARCHAR2,
    p_marca IN VARCHAR2,
    p_color IN VARCHAR2,
    p_modelo IN VARCHAR2,
    p_year IN VARCHAR2
)
IS
BEGIN
    INSERT INTO VEHICULOS(NUM_PLACA, NUM_MOTOR, MARCA, COLOR, MODELO, ano)
    VALUES (p_num_placa, p_num_motor, p_marca, p_color, p_modelo, p_year);
    COMMIT;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD Valoraciones

-- ==============================SELECT==============
CREATE OR REPLACE PROCEDURE seleccionar_valoracion(
    p_id_valoracion IN NUMBER,
    p_id_cliente OUT NUMBER,
    p_comentario OUT VARCHAR2,
    p_valoracion OUT NUMBER,
    p_fecha_emi OUT VARCHAR2
)
IS
BEGIN
    SELECT ID_CLIENTE, COMENTARIO, VALORACION, FECHA_EMI
    INTO p_id_cliente, p_comentario, p_valoracion, p_fecha_emi
    FROM VALORACIONES
    WHERE ID_VALORACION = p_id_valoracion;
END;

-- ==============================DELETE==================
CREATE OR REPLACE PROCEDURE eliminar_valoracion(
    p_id_valoracion IN NUMBER
)
IS
BEGIN
    DELETE FROM VALORACIONES
    WHERE ID_VALORACION = p_id_valoracion;
    COMMIT;
END;

-- ==============================UPDATE============
CREATE OR REPLACE PROCEDURE actualizar_valoracion(
    p_id_valoracion IN NUMBER,
    p_id_cliente IN NUMBER,
    p_comentario IN VARCHAR2,
    p_valoracion IN NUMBER,
    p_fecha_emi IN VARCHAR2
)
IS
BEGIN
    UPDATE VALORACIONES
    SET ID_CLIENTE = p_id_cliente,
        COMENTARIO = p_comentario,
        VALORACION = p_valoracion,
        FECHA_EMI = p_fecha_emi
    WHERE ID_VALORACION = p_id_valoracion;
    COMMIT;
END;

-- ===============================INSERT==========
CREATE OR REPLACE PROCEDURE insertar_valoracion(
    p_id_valoracion IN NUMBER,
    p_id_cliente IN NUMBER,
    p_comentario IN VARCHAR2,
    p_valoracion IN NUMBER,
    p_fecha_emi IN VARCHAR2
)
IS
BEGIN
    INSERT INTO VALORACIONES(ID_VALORACION, ID_CLIENTE, COMENTARIO, VALORACION, FECHA_EMI)
    VALUES (p_id_valoracion, p_id_cliente, p_comentario, p_valoracion, p_fecha_emi);
    COMMIT;
END;

-- ==============================OBTENER ULTIMO==============
CREATE OR REPLACE PROCEDURE ObtenerUltimaValoracion(
    p_id_valoracion OUT NUMBER
)
AS
BEGIN
    SELECT ID_VALORACION
    INTO p_id_valoracion
    FROM VALORACIONES
    ORDER BY ID_VALORACION DESC
    FETCH FIRST 1 ROWS ONLY;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD Inventario

-- ==============================SELECT=======
CREATE OR REPLACE PROCEDURE seleccionar_producto(
    p_id_producto IN NUMBER,
    p_id_tipo_producto OUT NUMBER,
    p_nombre OUT VARCHAR2,
    p_fecha_ingreso OUT VARCHAR2,
    p_stock OUT NUMBER,
    p_detalles OUT VARCHAR2
)
IS
BEGIN
    SELECT ID_TIPO_PRODUCTO, NOMBRE, FECHA_INGRE, STOCK, DETALLES
    INTO p_id_tipo_producto, p_nombre, p_fecha_ingreso, p_stock, p_detalles
    FROM INVENTARIO
    WHERE ID_PRODUCTO = p_id_producto;
END;

-- ==============================DELETE============
CREATE OR REPLACE PROCEDURE eliminar_producto(
    p_id_producto IN NUMBER
)
IS
BEGIN
    DELETE FROM INVENTARIO
    WHERE ID_PRODUCTO = p_id_producto;
    COMMIT;
END;

-- ==============================UPDATE==========
CREATE OR REPLACE PROCEDURE actualizar_producto(
    p_id_producto IN NUMBER,
    p_id_tipo_producto IN NUMBER,
    p_nombre IN VARCHAR2,
    p_fecha_ingreso IN VARCHAR2,
    p_stock IN NUMBER,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    UPDATE INVENTARIO
    SET ID_TIPO_PRODUCTO = p_id_tipo_producto,
        NOMBRE = p_nombre,
        FECHA_INGRE = p_fecha_ingreso,
        STOCK = p_stock,
        DETALLES = p_detalles
    WHERE ID_PRODUCTO = p_id_producto;
    COMMIT;
END;

-- ===============================INSERT==========
CREATE OR REPLACE PROCEDURE insertar_producto(
    p_id_producto IN NUMBER,
    p_id_tipo_producto IN NUMBER,
    p_nombre IN VARCHAR2,
    p_fecha_ingreso IN VARCHAR2,
    p_stock IN NUMBER,
    p_detalles IN VARCHAR2
)
IS
BEGIN
    INSERT INTO INVENTARIO(ID_PRODUCTO, ID_TIPO_PRODUCTO, NOMBRE, FECHA_INGRE, STOCK, DETALLES)
    VALUES (p_id_producto, p_id_tipo_producto, p_nombre, p_fecha_ingreso, p_stock, p_detalles);
    COMMIT;
END;

-- ==============================OBTENER ULTIMO========================================
CREATE OR REPLACE PROCEDURE ObtenerUltimoProducto(
    p_id_producto OUT NUMBER
)
AS
BEGIN
    SELECT ID_PRODUCTO
    INTO p_id_producto
    FROM INVENTARIO
    ORDER BY ID_PRODUCTO DESC
    FETCH FIRST 1 ROWS ONLY;
END;

-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

--                          CRUD Trabajos

-- ==============================SELECT========
CREATE OR REPLACE PROCEDURE seleccionar_trabajo(
    p_id_trabajo IN NUMBER,
    p_id_tipo_trabajo OUT NUMBER,
    p_fecha OUT VARCHAR2,
    p_id_cliente OUT NUMBER,
    p_num_placa OUT VARCHAR2,
    p_cant_productos OUT NUMBER,
    p_id_empleado OUT NUMBER
)
IS
BEGIN
    SELECT ID_TIPO_TRABAJO, FECHA, ID_CLIENTE, NUM_PLACA, CANT_PRODUCTOS, ID_EMPLEADO
    INTO p_id_tipo_trabajo, p_fecha, p_id_cliente, p_num_placa, p_cant_productos, p_id_empleado
    FROM TRABAJOS
    WHERE ID_TRABAJO = p_id_trabajo;
END;

-- ==============================DELETE========
CREATE OR REPLACE PROCEDURE eliminar_trabajo(
    p_id_trabajo IN NUMBER
)
IS
BEGIN
    DELETE FROM TRABAJOS
    WHERE ID_TRABAJO = p_id_trabajo;
    COMMIT;
END;

-- ==============================UPDATE=========
CREATE OR REPLACE PROCEDURE actualizar_trabajo(
    p_id_trabajo IN NUMBER,
    p_id_tipo_trabajo IN NUMBER,
    p_fecha IN VARCHAR2,
    p_id_cliente IN NUMBER,
    p_num_placa IN VARCHAR2,
    p_cant_productos IN NUMBER,
    p_id_empleado IN NUMBER
)
IS
BEGIN
    UPDATE TRABAJOS
    SET ID_TIPO_TRABAJO = p_id_tipo_trabajo,
        FECHA = p_fecha,
        ID_CLIENTE = p_id_cliente,
        NUM_PLACA = p_num_placa,
        CANT_PRODUCTOS = p_cant_productos,
        ID_EMPLEADO = p_id_empleado
    WHERE ID_TRABAJO = p_id_trabajo;
    COMMIT;
END;

-- ===============================INSERT========
CREATE OR REPLACE PROCEDURE insertar_trabajo(
    p_id_trabajo IN NUMBER,
    p_id_tipo_trabajo IN NUMBER,
    p_fecha IN VARCHAR2,
    p_id_cliente IN NUMBER,
    p_num_placa IN VARCHAR2,
    p_cant_productos IN NUMBER,
    p_id_empleado IN NUMBER
)
IS
BEGIN
    INSERT INTO TRABAJOS(ID_TRABAJO, ID_TIPO_TRABAJO, FECHA, ID_CLIENTE, NUM_PLACA, CANT_PRODUCTOS, ID_EMPLEADO)
    VALUES (p_id_trabajo, p_id_tipo_trabajo, p_fecha, p_id_cliente, p_num_placa, p_cant_productos, p_id_empleado);
    COMMIT;
END;

-- ==============================OBTENER ULTIMO=======
CREATE OR REPLACE PROCEDURE ObtenerUltimoTrabajo(
    p_id_trabajo OUT NUMBER
)
AS
BEGIN
    SELECT ID_TRABAJO
    INTO p_id_trabajo
    FROM TRABAJOS
    ORDER BY ID_TRABAJO DESC
    FETCH FIRST 1 ROWS ONLY;
END;


-- ============================================================
-- ====================== VISTAS ======================
--1. VISTA DE VEHICULO
CREATE OR REPLACE VIEW VISTA_VEHICULO AS
SELECT NUM_PLACA, NUM_MOTOR, ANO FROM VEHICULOS;

SELECT * FROM VISTA_VEHICULO;

--2. VISTA DE VALORACIONES
CREATE OR REPLACE VIEW VISTA_VALORACIONES_BASICA AS
SELECT ID_VALORACION, ID_CLIENTE, COMENTARIO FROM VALORACIONES;

SELECT * FROM VISTA_VALORACIONES;

--3. VISTA DE EMPLEADOS
CREATE OR REPLACE VIEW VISTA_EMPLEADOS AS
SELECT ID_EMPLEADO, NOMBRE, APELLIDO, SALARIO, TO_DATE(FECHA_INGRE, 'DD-MON-YY') AS FECHA_INGRE
FROM EMPLEADOS;

SELECT * FROM VISTA_EMPLEADOS;

--4. VISTA TIPOS DE TRABAJOS
CREATE OR REPLACE VIEW VISTA_TIPOS_TRABAJOS AS
SELECT  ID_TIPO_TRABAJO, NOMBRE, REQUISITOS, CONTENIDO
FROM TIPO_TRABAJO;

SELECT * FROM VISTA_TIPOS_TRABAJOS;

--5. VISTA DE CLIENTES
CREATE OR REPLACE VIEW VISTA_CLIENTES AS
SELECT ID_CLIENTE, USERNAME, NOMBRE || ' ' || APELLIDO AS NOMBRE_COMPLETO, NUM_TELEFONO
FROM CLIENTES;

SELECT * FROM VISTA_CLIENTES;

--6. VISTA QUE MUESTRA INFORMACION BASICA DE LOS PRODCUTOS EN INVENTARIO
CREATE OR REPLACE VIEW VISTA_INVENTARIO AS
SELECT 
    I.ID_PRODUCTO,
    TP.NOMBRE AS TIPO_PRODUCTO,
    I.NOMBRE,
    TO_DATE(I.FECHA_INGRE, 'DD-MON-YY') FECHA_PROD,
    I.STOCK,
    I.DETALLES
FROM INVENTARIO I
JOIN TIPO_PRODUCTO TP ON I.ID_TIPO_PRODUCTO = TP.ID_TIPO_PRODUCTO;

SELECT * FROM VISTA_INVENTARIO;

--7. VISTA QUE MUESTRA DETALLES DE LOS TRABAJOS REALIZADOS
CREATE OR REPLACE VIEW VISTA_TRABAJOS AS
SELECT 
    T.ID_TRABAJO,
    TT.NOMBRE AS TIPO_TRABAJO,
    TO_DATE(T.FECHA, 'DD-MON-YY') AS FECHA,
    C.NOMBRE || ' ' || C.APELLIDO AS CLIENTE,
    V.NUM_PLACA AS VEHICULO,
    T.CANT_PRODUCTOS,
    E.NOMBRE || ' ' || E.APELLIDO AS EMPLEADO
FROM TRABAJOS T
JOIN TIPO_TRABAJO TT ON T.ID_TIPO_TRABAJO = TT.ID_TIPO_TRABAJO
JOIN CLIENTES C ON T.ID_CLIENTE = C.ID_CLIENTE
JOIN VEHICULOS V ON T.NUM_PLACA = V.NUM_PLACA
JOIN EMPLEADOS E ON T.ID_EMPLEADO = E.ID_EMPLEADO;

SELECT * FROM VISTA_TRABAJOS;

--8. VISTA QUE MUESTRA LA VALORACION Y COMENTARIOS DE LOS CLIENTES
CREATE OR REPLACE VIEW VISTA_VALORACIONES AS
SELECT 
    V.ID_VALORACION,
    C.NOMBRE || ' ' || C.APELLIDO AS CLIENTE,
    V.COMENTARIO,
    V.VALORACION,
    TO_DATE(V.FECHA_EMI, 'DD-MON-YY') AS FECHA
FROM VALORACIONES V
JOIN CLIENTES C ON V.ID_CLIENTE = C.ID_CLIENTE;

SELECT * FROM VISTA_VALORACIONES;

--9. VISTA DE LOS PROVEEDORES
CREATE OR REPLACE VIEW VISTA_PROVEEDORES AS
SELECT NOMBRE, NUM_TELEFONO FROM PROVEEDORES;

SELECT * FROM VISTA_PROVEEDORES;

--10. VISTA DEL INVENTARIO
CREATE OR REPLACE VIEW VISTA_STOCK AS
SELECT STOCK FROM INVENTARIO;

SELECT * FROM VISTA_STOCK;


-- ============================================================
-- ====================== FUNCIONES ======================

--1. Eliminicacion de provedores
CREATE OR REPLACE FUNCTION F_eliminar_proveedor(
    p_id_proveedor IN NUMBER
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM proveedores
    WHERE ID_PROVEEDOR = p_id_proveedor;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1; 
    ELSE
        RETURN 0; 
    END IF;
END;

--2. Eliminicacion de tipo de producto
CREATE OR REPLACE FUNCTION F_eliminar_tipo_producto(
    p_id IN NUMBER
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM TIPO_PRODUCTO
    WHERE ID_TIPO_PRODUCTO = p_id;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1;
    ELSE
        RETURN 0;
    END IF;
END;

--3. Eliminicacion de empleados
CREATE OR REPLACE FUNCTION F_eliminar_empleado(
    p_id_empleado IN NUMBER
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM empleados
    WHERE ID_EMPLEADO = p_id_empleado;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1;
    ELSE
        RETURN 0; 
    END IF;
END;

--4. Eliminicacion de valoraaciones
CREATE OR REPLACE FUNCTION F_eliminar_valoracion(
    p_id_valoracion IN NUMBER
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM VALORACIONES
    WHERE ID_VALORACION = p_id_valoracion;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1;
    ELSE
        RETURN 0; 
    END IF;
END;

--5. Eliminicacion de productos
CREATE OR REPLACE FUNCTION F_eliminar_producto(
    p_id_producto IN NUMBER
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM INVENTARIO
    WHERE ID_PRODUCTO = p_id_producto;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1; 
    ELSE
        RETURN 0; 
    END IF;
END;

--6. Eliminicacion de trabajos
CREATE OR REPLACE FUNCTION F_eliminar_trabajo(
    p_id_trabajo IN NUMBER
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM TRABAJOS
    WHERE ID_TRABAJO = p_id_trabajo;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1;
    ELSE
        RETURN 0; 
    END IF;
END;

--7. Eliminicacion de clientes
CREATE OR REPLACE FUNCTION F_eliminar_cliente(
    p_id_cliente IN NUMBER
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM CLIENTES
    WHERE ID_CLIENTE = p_id_cliente;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1;
    ELSE
        RETURN 0;
    END IF;
END;

-- ============================================================
-- ====================== CURSORES ======================
--1. Ver los datos de la tabla producto
CREATE OR REPLACE PROCEDURE RECUPERAR_TIPOS_PRODUCTO IS
    CURSOR c_tipos_producto IS
        SELECT * FROM TIPO_PRODUCTO;
    v_id_tipo_producto TIPO_PRODUCTO.ID_TIPO_PRODUCTO%TYPE;
    v_nombre TIPO_PRODUCTO.NOMBRE%TYPE;
    v_detalles TIPO_PRODUCTO.DETALLES%TYPE;
BEGIN
    OPEN c_tipos_producto;
    LOOP
        FETCH c_tipos_producto INTO v_id_tipo_producto, v_nombre, v_detalles;

        EXIT WHEN c_tipos_producto%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('ID_TIPO_PRODUCTO: ' || v_id_tipo_producto);
        DBMS_OUTPUT.PUT_LINE('NOMBRE: ' || v_nombre);
        DBMS_OUTPUT.PUT_LINE('DETALLES: ' || v_detalles);
        DBMS_OUTPUT.PUT_LINE('------------------------');
    END LOOP;
    CLOSE c_tipos_producto;
END;

EXECUTE RECUPERAR_TIPOS_PRODUCTO;

--2. Cursor para mostrar el nombre y la dirección de los proveedores 
CREATE OR REPLACE PROCEDURE MOSTRAR_NOMBRE_Y_DIRECCION IS
    CURSOR c_proveedores IS
        SELECT NOMBRE, DIRECCION FROM PROVEEDORES;

    v_nombre PROVEEDORES.NOMBRE%TYPE;
    v_direccion PROVEEDORES.DIRECCION%TYPE;
BEGIN
    OPEN c_proveedores;

    LOOP
        FETCH c_proveedores INTO v_nombre, v_direccion;

        EXIT WHEN c_proveedores%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_nombre);
        DBMS_OUTPUT.PUT_LINE('Dirección: ' || v_direccion);
        DBMS_OUTPUT.PUT_LINE('------------------------');
    END LOOP;

    CLOSE c_proveedores;
END;

EXECUTE MOSTRAR_NOMBRE_Y_DIRECCION;

/*3. cursor para mostrar los nombres de los empleados que comienzan con la 
letra 'E', y si no hay empleados que cumplan con esa condicion, muestra un 
mensaje indicando que no existen empleados*/
CREATE OR REPLACE PROCEDURE MOSTRAR_EMPLEADOS_CON_E IS
    CURSOR c_empleados IS
        SELECT NOMBRE
        FROM EMPLEADOS
        WHERE UPPER(SUBSTR(NOMBRE, 1, 1)) = 'E';

    v_nombre EMPLEADOS.NOMBRE%TYPE;
    v_empleado_encontrado BOOLEAN := FALSE;
BEGIN
    OPEN c_empleados;

    LOOP
        FETCH c_empleados INTO v_nombre;

        EXIT WHEN c_empleados%NOTFOUND;

        v_empleado_encontrado := TRUE;
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_nombre);
    END LOOP;

    CLOSE c_empleados;

    IF NOT v_empleado_encontrado THEN
        DBMS_OUTPUT.PUT_LINE('No hay empleados cuyos nombres comiencen con la letra E.');
    END IF;
END;

EXECUTE MOSTRAR_EMPLEADOS_CON_E;

/*4 Cursor para buscar un cliente por su nombre de usuario. Si encuentra al menos un cliente
con el nombre de usuario proporcionado, mostrará la información del cliente. Si no encuentra
ningún cliente, mostrará un mensaje indicando que el cliente no existe */
CREATE OR REPLACE PROCEDURE BUSCAR_CLIENTE_POR_USERNAME(
    p_username IN CLIENTES.USERNAME%TYPE
) IS
    CURSOR c_cliente IS
        SELECT *
        FROM CLIENTES
        WHERE UPPER(USERNAME) = UPPER(p_username);

    v_id_cliente CLIENTES.ID_CLIENTE%TYPE;
    v_username CLIENTES.USERNAME%TYPE;
    v_contrasena CLIENTES.CONTRASENA%TYPE;
    v_nombre CLIENTES.NOMBRE%TYPE;
    v_apellido CLIENTES.APELLIDO%TYPE;
    v_direccion CLIENTES.DIRECCION%TYPE;
    v_num_telefono CLIENTES.NUM_TELEFONO%TYPE;
    v_correo_elect CLIENTES.CORREO_ELECT%TYPE;
    v_cliente_encontrado BOOLEAN := FALSE;
BEGIN
    OPEN c_cliente;

    LOOP
        FETCH c_cliente INTO v_id_cliente, v_username, v_contrasena, v_nombre, v_apellido, v_direccion, v_num_telefono, v_correo_elect;

        EXIT WHEN c_cliente%NOTFOUND;

        v_cliente_encontrado := TRUE;
        DBMS_OUTPUT.PUT_LINE(
            'ID_CLIENTE: ' || v_id_cliente ||
            ', USERNAME: ' || v_username ||
            ', CONTRASEÑA: ' || v_contrasena ||
            ', NOMBRE: ' || v_nombre ||
            ', APELLIDO: ' || v_apellido ||
            ', DIRECCIÓN: ' || v_direccion ||
            ', NÚMERO DE TELÉFONO: ' || v_num_telefono ||
            ', CORREO ELECTRÓNICO: ' || v_correo_elect ||
            '--------------------------------'
        );
    END LOOP;

    CLOSE c_cliente;

    IF NOT v_cliente_encontrado THEN
        DBMS_OUTPUT.PUT_LINE('Cliente con username ' || p_username || ' no encontrado.');
    END IF;
END;

EXECUTE BUSCAR_CLIENTE_POR_USERNAME('user1');

--5.Cursor para buscar vehículos por placa
CREATE OR REPLACE PROCEDURE BUSCAR_VEHICULOS_POR_PLACA(
    p_inicio_placa IN VARCHAR2
) IS
    CURSOR c_vehiculos IS
        SELECT *
        FROM VEHICULOS
        WHERE SUBSTR(NUM_PLACA, 1, 1) IN ('9', '7')
        AND NUM_PLACA LIKE p_inicio_placa || '%';
    
    v_num_placa VEHICULOS.NUM_PLACA%TYPE;
    v_num_motor VEHICULOS.NUM_MOTOR%TYPE;
    v_marca VEHICULOS.MARCA%TYPE;
    v_color VEHICULOS.COLOR%TYPE;
    v_modelo VEHICULOS.MODELO%TYPE;
    v_ano VEHICULOS.AÑO%TYPE;
BEGIN
    OPEN c_vehiculos;

    LOOP
        FETCH c_vehiculos INTO v_num_placa, v_num_motor, v_marca, v_color, v_modelo, v_ano;

        EXIT WHEN c_vehiculos%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('NUM_PLACA: ' || v_num_placa);
        DBMS_OUTPUT.PUT_LINE('NUM_MOTOR: ' || v_num_motor);
        DBMS_OUTPUT.PUT_LINE('MARCA: ' || v_marca);
        DBMS_OUTPUT.PUT_LINE('COLOR: ' || v_color);
        DBMS_OUTPUT.PUT_LINE('MODELO: ' || v_modelo);
        DBMS_OUTPUT.PUT_LINE('AÑO: ' || v_ano);
        DBMS_OUTPUT.PUT_LINE('------------------------');
    END LOOP;

    CLOSE c_vehiculos;
END;

EXEC BUSCAR_VEHICULOS_POR_PLACA('9');

--6. Cursor para buscar valoraciones con calificacion mayor a 3
CREATE OR REPLACE PROCEDURE BUSCAR_VALORACIONES_MAYOR_A_3 IS
    CURSOR c_valoraciones IS
        SELECT *
        FROM VALORACIONES
        WHERE VALORACION > 3;
    
    v_id_valoracion VALORACIONES.ID_VALORACION%TYPE;
    v_id_cliente VALORACIONES.ID_CLIENTE%TYPE;
    v_comentario VALORACIONES.COMENTARIO%TYPE;
    v_valoracion VALORACIONES.VALORACION%TYPE;
    v_fecha_emision VALORACIONES.FECHA_EMI%TYPE;
BEGIN
    OPEN c_valoraciones;

    LOOP
        FETCH c_valoraciones INTO v_id_valoracion, v_id_cliente, v_comentario, v_valoracion, v_fecha_emision;

        EXIT WHEN c_valoraciones%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('ID_VALORACION: ' || v_id_valoracion);
        DBMS_OUTPUT.PUT_LINE('ID_CLIENTE: ' || v_id_cliente);
        DBMS_OUTPUT.PUT_LINE('COMENTARIO: ' || v_comentario);
        DBMS_OUTPUT.PUT_LINE('VALORACION: ' || v_valoracion);
        DBMS_OUTPUT.PUT_LINE('FECHA_EMISION: ' || v_fecha_emision);
        DBMS_OUTPUT.PUT_LINE('------------------------');
    END LOOP;

    CLOSE c_valoraciones;
END;

EXEC BUSCAR_VALORACIONES_MAYOR_A_3;

--7. Cursor para buscar productos en inventario por ID y cantidad en stock
CREATE OR REPLACE PROCEDURE BUSCAR_PRODUCTO_POR_ID_Y_STOCK(
    p_id_producto IN INVENTARIO.ID_PRODUCTO%TYPE
) IS
    CURSOR c_inventario IS
        SELECT *
        FROM INVENTARIO
        WHERE ID_PRODUCTO = p_id_producto
        AND STOCK BETWEEN 10 AND 30;
    
    v_id_producto INVENTARIO.ID_PRODUCTO%TYPE;
    v_id_tipo_producto INVENTARIO.ID_TIPO_PRODUCTO%TYPE;
    v_nombre INVENTARIO.NOMBRE%TYPE;
    v_fecha_ingreso INVENTARIO.FECHA_INGRE%TYPE;
    v_stock INVENTARIO.STOCK%TYPE;
    v_detalles INVENTARIO.DETALLES%TYPE;
BEGIN
    OPEN c_inventario;

    LOOP
        FETCH c_inventario INTO v_id_producto, v_id_tipo_producto, v_nombre, v_fecha_ingreso, v_stock, v_detalles;

        EXIT WHEN c_inventario%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('ID_PRODUCTO: ' || v_id_producto);
        DBMS_OUTPUT.PUT_LINE('ID_TIPO_PRODUCTO: ' || v_id_tipo_producto);
        DBMS_OUTPUT.PUT_LINE('NOMBRE: ' || v_nombre);
        DBMS_OUTPUT.PUT_LINE('FECHA_INGRESO: ' || v_fecha_ingreso);
        DBMS_OUTPUT.PUT_LINE('STOCK: ' || v_stock);
        DBMS_OUTPUT.PUT_LINE('DETALLES: ' || v_detalles);
        DBMS_OUTPUT.PUT_LINE('------------------------');
    END LOOP;

    CLOSE c_inventario;
END;

EXEC BUSCAR_PRODUCTO_POR_ID_Y_STOCK(2);

--8. Procedimiento para buscar trabajos en TIPO_TRABAJO por nombre
CREATE OR REPLACE PROCEDURE BUSCAR_TRABAJO_POR_NOMBRE(
    p_letra_inicial IN VARCHAR2,
    p_longitud_maxima IN NUMBER
) IS
    CURSOR c_tipo_trabajo IS
        SELECT *
        FROM TIPO_TRABAJO
        WHERE LENGTH(NOMBRE) < p_longitud_maxima
        AND UPPER(SUBSTR(NOMBRE, 1, 1)) = UPPER(p_letra_inicial);
    
    v_id_tipo_trabajo TIPO_TRABAJO.ID_TIPO_TRABAJO%TYPE;
    v_nombre TIPO_TRABAJO.NOMBRE%TYPE;
    v_requisitos TIPO_TRABAJO.REQUISITOS%TYPE;
    v_contenido TIPO_TRABAJO.CONTENIDO%TYPE;
    v_detalles TIPO_TRABAJO.DETALLES%TYPE;
BEGIN
    OPEN c_tipo_trabajo;

    LOOP
        FETCH c_tipo_trabajo INTO v_id_tipo_trabajo, v_nombre, v_requisitos, v_contenido, v_detalles;

        EXIT WHEN c_tipo_trabajo%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            'ID_TIPO_TRABAJO: ' || v_id_tipo_trabajo ||
            ', NOMBRE: ' || v_nombre ||
            ', REQUISITOS: ' || v_requisitos ||
            ', CONTENIDO: ' || v_contenido ||
            ', DETALLES: ' || v_detalles ||
            '---------------------------------'
        );
    END LOOP;

    CLOSE c_tipo_trabajo;
END;

EXEC BUSCAR_TRABAJO_POR_NOMBRE('R', 10);

-- ============================================================
COMMIT;