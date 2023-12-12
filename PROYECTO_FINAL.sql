-- ============================================================
-- ====================== USUARIOS ======================
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER proyecto IDENTIFIED BY proyecto;
grant DBA to proyecto;

-- ============================================================
-- ===================== TABLAS =======================
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
   SALARIO VARCHAR2(100),
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
   ID_CLIENTE NUMBER (22),
   NUM_MOTOR NUMBER(15),
   MARCA VARCHAR2 (12),
   COLOR VARCHAR2 (10),
   MODELO VARCHAR2 (10),
   ANO VARCHAR(4),
   CONSTRAINT FK_VEHICULO FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES (ID_CLIENTE)
);

CREATE TABLE VALORACIONES (
   ID_VALORACION NUMBER (10) PRIMARY KEY,
   ID_CLIENTE NUMBER (22),
   COMENTARIO VARCHAR2 (150),
   VALORACION NUMBER(5),
   FECHA_EMI varchar2(100),
   CONSTRAINT FK_VALORACIONES FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES (ID_CLIENTE)
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
-- ==================== INSERTS =======================
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
INSERT INTO VEHICULOS (NUM_PLACA, ID_CLIENTE, NUM_MOTOR, MARCA, COLOR, MODELO, ANO) VALUES ('ABC123', 1, 123456789, 'Toyota', 'Blue', 'Camry', '2020');
INSERT INTO VEHICULOS (NUM_PLACA, ID_CLIENTE, NUM_MOTOR, MARCA, COLOR, MODELO, ANO) VALUES ('XYZ789', 2, 987654321, 'Ford', 'Red', 'Mustang', '2022');

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
-- ============= PROCEDIMIENTOS Y CRUD ================

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
CREATE OR REPLACE PROCEDURE seleccionar_vehiculo(
   p_num_placa IN VARCHAR2,
    p_id_cliente OUT NUMBER,
    p_num_motor OUT NUMBER,
    p_marca OUT VARCHAR2,
    p_color OUT VARCHAR2,
    p_modelo OUT VARCHAR2,
    p_ano OUT VARCHAR2
)
IS
BEGIN
    SELECT ID_CLIENTE, NUM_MOTOR, MARCA, COLOR, MODELO, ANO
    INTO p_id_cliente, p_num_motor, p_marca, p_color, p_modelo, p_ano
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
    p_id_cliente IN NUMBER,
    p_num_motor IN NUMBER,
    p_marca IN VARCHAR2,
    p_color IN VARCHAR2,
    p_modelo IN VARCHAR2,
    p_ano IN VARCHAR2
)
IS
BEGIN
    UPDATE VEHICULOS
    SET ID_CLIENTE = p_id_cliente, NUM_MOTOR = p_num_motor, MARCA = p_marca, COLOR = p_color, MODELO = p_modelo, ANO = p_ano
    WHERE NUM_PLACA = p_num_placa;
    COMMIT;
END;
-- ===============================INSERT==============
CREATE OR REPLACE PROCEDURE insertar_vehiculo(
    p_num_placa IN VARCHAR2,
    p_id_cliente IN NUMBER,
    p_num_motor IN NUMBER,
    p_marca IN VARCHAR2,
    p_color IN VARCHAR2,
    p_modelo IN VARCHAR2,
    p_ano IN VARCHAR2
)
IS
BEGIN
    INSERT INTO VEHICULOS(NUM_PLACA, ID_CLIENTE, NUM_MOTOR, MARCA, COLOR, MODELO, ANO)
    VALUES (p_num_placa, p_id_cliente, p_num_motor, p_marca, p_color, p_modelo, p_ano);
    COMMIT;
END;
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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

-- ==============================SELECT===========
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
-- ==============================DELETE===========
CREATE OR REPLACE PROCEDURE eliminar_producto(
    p_id_producto IN NUMBER
)
IS
BEGIN
    DELETE FROM INVENTARIO
    WHERE ID_PRODUCTO = p_id_producto;
    COMMIT;
END;
-- ==============================UPDATE===========
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
-- ==============================OBTENER ULTIMO===
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
-- ===================== VISTAS =======================

--1. VISTA DE VEHICULO
CREATE OR REPLACE VIEW VISTA_VEHICULO AS
SELECT NUM_PLACA, NUM_MOTOR, ANO FROM VEHICULOS;

SELECT * FROM VISTA_VEHICULO;

--2. VISTA DE VALORACIONES
CREATE OR REPLACE VIEW VISTA_VALORACIONES_BASICA AS
SELECT ID_VALORACION, ID_CLIENTE, COMENTARIO FROM VALORACIONES;

SELECT * FROM VISTA_VALORACIONES_BASICA;

--3. VISTA DE EMPLEADOS
CREATE OR REPLACE VIEW VISTA_EMPLEADOS AS
SELECT ID_EMPLEADO, NOMBRE, APELLIDO, SALARIO, FECHA_INGRE AS FECHA_INGRE
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
    I.ID_PRODUCTO as ID_PRODUCTO,
    TP.NOMBRE AS TIPO_PRODUCTO,
    I.NOMBRE NOMBRE_PRODUCTO,
    I.FECHA_INGRE FECHA_PROD,
    I.STOCK as STOCK,
    I.DETALLES as DETALLES
FROM INVENTARIO I
JOIN TIPO_PRODUCTO TP ON I.ID_TIPO_PRODUCTO = TP.ID_TIPO_PRODUCTO;

SELECT * FROM VISTA_INVENTARIO;

--7. VISTA QUE MUESTRA DETALLES DE LOS TRABAJOS REALIZADOS
CREATE OR REPLACE VIEW VISTA_TRABAJOS AS
SELECT 
    T.ID_TRABAJO ID_TRABAJOS,
    TT.NOMBRE AS TIPO_TRABAJO,
    T.FECHA AS FECHA,
    C.NOMBRE || ' ' || C.APELLIDO AS CLIENTE,
    V.NUM_PLACA AS VEHICULO,
    T.CANT_PRODUCTOS as CANTIDAD_PRODUCTOS,
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
    V.ID_VALORACION ID_VALORACION,
    C.NOMBRE || ' ' || C.APELLIDO AS CLIENTE,
    V.COMENTARIO VALO_COMENTARIO,
    V.VALORACION VALO_VALORACION,
    V.FECHA_EMI AS FECHA
FROM VALORACIONES V
JOIN CLIENTES C ON V.ID_CLIENTE = C.ID_CLIENTE;

SELECT * FROM VISTA_VALORACIONES;

--9. VISTA DE LOS PROVEEDORES
CREATE OR REPLACE VIEW VISTA_PROVEEDORES AS
SELECT NOMBRE, NUM_TELEFONO FROM PROVEEDORES;

SELECT * FROM VISTA_PROVEEDORES;

--10. VISTA DEL INVENTARIO
CREATE OR REPLACE VIEW VISTA_STOCK AS
SELECT nombre,STOCK FROM INVENTARIO;

SELECT * FROM VISTA_STOCK;

-- ============================================================
-- ==================== CURSORES ======================
--1. Ver los datos de la tabla producto
CREATE OR REPLACE PROCEDURE c_RECUPERAR_TIPOS_PRODUCTO (
    p_id_tipo_producto out NUMBER,
    p_nombre OUT VARCHAR2,
    p_detalles OUT VARCHAR2,
    p_output OUT VARCHAR2
)
IS
    -- Cursor para para ver la información
    CURSOR c_tipos_producto IS
        SELECT * FROM TIPO_PRODUCTO;
    v_id_tipo_producto TIPO_PRODUCTO.ID_TIPO_PRODUCTO%TYPE;
    v_nombre TIPO_PRODUCTO.NOMBRE%TYPE;
    v_detalles TIPO_PRODUCTO.DETALLES%TYPE;
BEGIN
    OPEN c_tipos_producto;
    FETCH c_tipos_producto INTO v_id_tipo_producto, v_nombre, v_detalles;
    
    -- Inicializar la variable de salida
    p_output := '';
    
    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_tipos_producto%FOUND LOOP
        p_output := p_output || 'ID del Tipo de : ' || v_id_tipo_producto || '<br>' ||  
        'Nombre: ' || v_nombre || '<br>'  || ' Detalles: ' || v_detalles || '<br><br>' ;
        FETCH c_tipos_producto INTO v_id_tipo_producto, v_nombre, v_detalles;
    END LOOP;
    
    CLOSE c_tipos_producto;
END;
EXECUTE c_RECUPERAR_TIPOS_PRODUCTO;

--2. Cursor para mostrar el nombre y la direcci n de los proveedores 
CREATE OR REPLACE PROCEDURE c_seleccionar_proveedor(
    p_id_proveedor IN NUMBER,
    p_nombre OUT VARCHAR2,
    p_num_telefono OUT VARCHAR2,
    p_direccion OUT VARCHAR2,
    p_detalles OUT VARCHAR2,
    p_output OUT VARCHAR2
)
IS
    -- Cursor para recuperar datos de proveedores
    CURSOR c_proveedor IS
        SELECT Nombre, Num_Telefono, Direccion, Detalles
        FROM proveedores
        WHERE ID_PROVEEDOR = p_id_proveedor;
BEGIN
    OPEN c_proveedor;
    FETCH c_proveedor INTO p_nombre, p_num_telefono, p_direccion, p_detalles;

    -- Inicializar la variable de salida
    p_output := '';

    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_proveedor%FOUND LOOP
        p_output := p_output || 'Nombre: ' || p_nombre || '<br>' ||  'Tel fono: ' || p_num_telefono || '<br>'  || ' Direcci n: ' || p_direccion || '<br>'  || ' Detalles: ' || p_detalles ;
        FETCH c_proveedor INTO p_nombre, p_num_telefono, p_direccion, p_detalles;
    END LOOP;

    CLOSE c_proveedor;
END;
EXECUTE c_seleccionar_proveedor;

/*3. cursor para mostrar los nombres de los empleados que comienzan con la 
letra 'E', y si no hay empleados que cumplan con esa condicion, muestra un 
mensaje indicando que no existen empleados*/
CREATE OR REPLACE PROCEDURE MOSTRAR_EMPLEADOS_CON_E(
    e_id_empleado out NUMBER,
    e_nombre OUT VARCHAR2,
    e_output OUT VARCHAR2
)
IS
    -- Cursor para recuperar datos de proveedores
    CURSOR c_empleados IS
        SELECT NOMBRE
        FROM EMPLEADOS
        WHERE UPPER(SUBSTR(NOMBRE, 1, 1)) = 'E' or SUBSTR(NOMBRE, 1, 1)='e';
        v_nombre EMPLEADOS.NOMBRE%TYPE;
        v_empleado_encontrado BOOLEAN := FALSE;
BEGIN
    OPEN c_empleados;
    FETCH c_empleados INTO v_nombre;
    
    -- Inicializar la variable de salida
    e_output := '';
    
    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_empleados%FOUND LOOP
        v_empleado_encontrado := TRUE;
        e_output := e_output || 'Nombre: ' || v_nombre || '<br>' ;
        FETCH c_empleados INTO v_nombre;
    END LOOP;

    CLOSE c_empleados;

    IF NOT v_empleado_encontrado THEN
        e_output := 'No hay empleados cuyos nombres comiencen con la letra E.';
    END IF;
END;
EXECUTE MOSTRAR_EMPLEADOS_CON_E;

/*4 Cursor para buscar un cliente por su nombre de usuario. Si encuentra al menos un cliente
con el nombre de usuario proporcionado, mostrar  la informaci n del cliente. Si no encuentra
ning n cliente, mostrar  un mensaje indicando que el cliente no existe */
CREATE OR REPLACE PROCEDURE BUSCAR_CLIENTE_POR_USERNAME(
    c_username IN VARCHAR2,
    
    c_nombre OUT VARCHAR2,
    c_apellido OUT VARCHAR2,
    c_direccion OUT VARCHAR2,
    c_num_telefono OUT VARCHAR2,
    c_correo_elect OUT VARCHAR2,
    p_output OUT VARCHAR2
) 
IS
    CURSOR c_cliente IS
        SELECT NOMBRE, APELLIDO, DIRECCION, NUM_TELEFONO, CORREO_ELECT
        FROM CLIENTES
        WHERE UPPER(USERNAME) = UPPER(c_username);
        v_nombre VARCHAR2(500);
        v_apellido VARCHAR2(500);
        v_direccion VARCHAR2(500);
        v_num_telefono VARCHAR2(500);
        v_correo_elect VARCHAR2(500);
        v_cliente_encontrado BOOLEAN := FALSE;
BEGIN
    OPEN c_cliente;
    FETCH c_cliente INTO v_nombre, v_apellido, v_direccion, v_num_telefono, v_correo_elect;

    p_output := '';
    
    WHILE c_cliente%FOUND LOOP
        p_output := p_output || 'Nombre Completo: ' || v_nombre || ' ' || v_apellido || '<br>' || 
        ' Direcci n: ' || v_direccion || '<br>'  || ' N mero de Tel fono: ' || v_num_telefono || '<br>' || 
        ' Correo Electr nico: ' || v_correo_elect;
         v_cliente_encontrado := true;
        FETCH c_cliente INTO v_nombre, v_apellido, v_direccion, v_num_telefono, v_correo_elect;
    END LOOP;

    CLOSE c_cliente;

    IF NOT v_cliente_encontrado THEN
        p_output := 'Cliente con username ' || c_username || ' no encontrado.';
    END IF;
END;
EXECUTE BUSCAR_CLIENTE_POR_USERNAME('user1');

--5.Cursor para buscar veh culos por placa
CREATE OR REPLACE PROCEDURE BUSCAR_VEHICULOS_POR_PLACA(
    p_inicio_placa IN VARCHAR2,
    p_num_placa OUT VARCHAR2,
    p_num_motor OUT NUMBER,
    p_marca OUT VARCHAR2,
    p_color OUT VARCHAR2,
    p_modelo OUT VARCHAR2,
    p_ano OUT VARCHAR2,
    p_output OUT VARCHAR2
)
IS
    CURSOR c_vehiculos IS
        SELECT NUM_PLACA, NUM_MOTOR, MARCA, COLOR, MODELO, ANO
        FROM VEHICULOS
        WHERE INSTR('123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', SUBSTR(NUM_PLACA, 1, 1)) > 0
        AND NUM_PLACA LIKE p_inicio_placa || '%';
BEGIN
    OPEN c_vehiculos;
    FETCH c_vehiculos INTO p_num_placa, p_num_motor, p_marca, p_color, p_modelo, p_ano;
    
    -- Inicializar la variable de salida
    p_output := '';

    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_vehiculos%FOUND LOOP
        p_output := p_output || 'N mero de Placa: ' || p_num_placa || '<br>' ||  
        ' N mero de Motor: ' || p_num_motor || '<br>'  || ' Marca: ' || p_marca || '<br>'  || 
        ' Color: ' || p_color || '<br>' || ' Modelo: ' || p_modelo || '<br>'  || 
        ' A o: ' || p_ano;
        FETCH c_vehiculos INTO p_num_placa, p_num_motor, p_marca, p_color, p_modelo, p_ano;
    END LOOP;

    CLOSE c_vehiculos;
END;
EXECUTE BUSCAR_VEHICULOS_POR_PLACA('9');

--6. Cursor para buscar valoraciones con calificacion mayor a 3
CREATE OR REPLACE PROCEDURE BUSCAR_VALORACIONES_MAYOR_A_3 (
    v_id_cliente OUT NUMBER,
    v_comentario OUT VARCHAR2,
    v_valoracion OUT NUMBER,
    v_fecha_emision OUT VARCHAR2,
    v_output OUT VARCHAR2
)
IS
    -- Cursor para buscar valoraciones mayores a 3
    CURSOR c_valoraciones IS
        SELECT CLIENTE, VALO_COMENTARIO, VALO_VALORACION, FECHA
        FROM VISTA_VALORACIONES
        WHERE VALO_VALORACION > 3;

    -- Variables para almacenar datos del cursor
    v_cliente VISTA_VALORACIONES.CLIENTE%TYPE;
    v_comentario_valoracion VISTA_VALORACIONES.VALO_COMENTARIO%TYPE;
    v_valoracion_valoracion VISTA_VALORACIONES.VALO_VALORACION%TYPE;
    v_fecha_valoracion VISTA_VALORACIONES.FECHA%TYPE;
BEGIN
    OPEN c_valoraciones;
    
    -- Inicializar la variable de salida
    v_output := '';
    
    FETCH c_valoraciones INTO v_cliente, v_comentario_valoracion, v_valoracion_valoracion, v_fecha_valoracion;
    
    -- Procesar los datos del cursor y almacenar en v_output
    WHILE c_valoraciones%FOUND LOOP
        v_output := v_output || 'Cliente: ' || v_cliente || '<br>'  || 
                    'Comentario: ' || v_comentario_valoracion || '<br>'  || 
                    'Valoraci n: ' || v_valoracion_valoracion || '<br>'  || 
                    'Fecha: ' || v_fecha_valoracion || '<br><br>';
        
        FETCH c_valoraciones INTO v_cliente, v_comentario_valoracion, v_valoracion_valoracion, v_fecha_valoracion;
    END LOOP;

    CLOSE c_valoraciones;
END;
EXECUTE BUSCAR_VALORACIONES_MAYOR_A_3;

--7. Cursor para buscar productos en inventario por cantidad en stock
CREATE OR REPLACE PROCEDURE BUSCAR_PRODUCTO_POR_ID_Y_STOCK(
    p_id_producto IN NUMBER,    
    p_id_tipo_producto OUT VARCHAR2,
    p_nombre OUT VARCHAR2,
    p_fecha_ingreso OUT VARCHAR2,
    p_stock OUT NUMBER,
    p_detalles OUT VARCHAR2,
    p_output OUT VARCHAR2
) IS
    CURSOR c_inventario IS
        SELECT TIPO_PRODUCTO, NOMBRE_PRODUCTO, FECHA_PROD, STOCK, DETALLES
        FROM VISTA_INVENTARIO
        WHERE ID_PRODUCTO LIKE p_id_producto
        AND STOCK BETWEEN 15 AND 1000;
BEGIN
    OPEN c_inventario;
    FETCH c_inventario INTO p_id_tipo_producto, p_nombre, p_fecha_ingreso, p_stock, p_detalles;

     -- Inicializar la variable de salida
    p_output := '';
    
    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_inventario%FOUND LOOP
        p_output := p_output || 'Tipo del Producto: ' || p_id_tipo_producto || '<br>'  || 
        'Nombre : ' || p_nombre || '<br>'  || 
        'Fecha de Ingreso: ' || p_fecha_ingreso || '<br>'  ||
        'Cantidad de Stock: ' || p_stock || '<br>'  ||
        'Detalles: ' || p_detalles;
        FETCH c_inventario INTO p_id_tipo_producto, p_nombre, p_fecha_ingreso, p_stock, p_detalles;
    END LOOP;

    CLOSE c_inventario;
END;
EXECUTE BUSCAR_PRODUCTO_POR_ID_Y_STOCK(2);

--8. Procedimiento para buscar trabajos en TIPO_TRABAJO por nombre
CREATE OR REPLACE PROCEDURE BUSCAR_TRABAJO_POR_NOMBRE(
    p_letra_inicial IN VARCHAR2,
    v_id_tipo_trabajo OUT NUMBER,
    v_nombre OUT VARCHAR2,
    v_requisitos OUT VARCHAR2,
    v_contenido OUT VARCHAR2,
    p_output OUT VARCHAR2
) IS
    CURSOR c_tipo_trabajo IS
        SELECT *
        FROM VISTA_TIPOS_TRABAJOS
        WHERE UPPER(SUBSTR(NOMBRE, 1, 1)) = UPPER(p_letra_inicial);
BEGIN
    OPEN c_tipo_trabajo;
    FETCH c_tipo_trabajo INTO v_id_tipo_trabajo, v_nombre, v_requisitos, v_contenido;

    -- Inicializar la variable de salida
    p_output := '';

    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_tipo_trabajo%FOUND LOOP
        p_output := p_output || 'ID: ' || v_id_tipo_trabajo || '<br>' ||  
        'Nombre de la Reparaci n: ' || v_nombre || '<br>'  || 
        'Requisitos: ' || v_requisitos || '<br>'  || 
        'Contenido: ' || v_contenido;
        FETCH c_tipo_trabajo INTO v_id_tipo_trabajo, v_nombre, v_requisitos, v_contenido;
    END LOOP;
    
    CLOSE c_tipo_trabajo;
END;
EXECUTE BUSCAR_TRABAJO_POR_NOMBRE('R');

--9.obtener informaci n de todos los empleados con salario mayor a 60000
CREATE OR REPLACE PROCEDURE BUSCAR_EMPLEADO_CON_SALARIO_MAYOR(
    e_id_empleado OUT NUMBER,
    e_nombre OUT VARCHAR2,
    e_apellido OUT VARCHAR2,
    e_num_telefono OUT VARCHAR2,
    e_correo_elect OUT VARCHAR2,
    e_salario OUT VARCHAR2,
    e_fecha_ingre OUT VARCHAR2,
    p_output OUT VARCHAR2
) IS
    CURSOR c_empleados IS
        SELECT ID_EMPLEADO, NOMBRE, APELLIDO, NUM_TELEFONO, CORREO_ELECT, SALARIO, FECHA_INGRE
        FROM EMPLEADOS
        WHERE SALARIO >= 60000;
BEGIN
    OPEN c_empleados;
    FETCH c_empleados INTO e_id_empleado, e_nombre, e_apellido, e_num_telefono, e_correo_elect, e_salario, e_fecha_ingre;
    
    -- Inicializar la variable de salida
    p_output := '';
    
    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_empleados%FOUND LOOP
        p_output := p_output || 'ID: ' || e_id_empleado || '<br>' ||  
        'Nombre Completo: ' || e_nombre || ' ' || e_apellido || '<br>'  ||
        'Tel fono: ' || e_num_telefono || '<br>'  || 
        'Correo Electr nico: ' || e_correo_elect || '<br>' || 'Salario: ' || e_salario || '<br>'  || 
        'Fecha de Ingreso: ' || e_fecha_ingre|| '<br><br>' ;
        FETCH c_empleados INTO e_id_empleado, e_nombre, e_apellido, e_num_telefono, e_correo_elect, e_salario, e_fecha_ingre;
    END LOOP;
    
    CLOSE c_empleados;
END;
EXECUTE BUSCAR_EMPLEADO_CON_SALARIO_MAYOR;

--10.obtener informacion de trabajos segun el empleado
CREATE OR REPLACE PROCEDURE BUSCAR_TRABAJOS_POR_EMPLEADO(
    t_nombre_emp IN VARCHAR2,
    t_id_trabajo OUT VARCHAR2,
    t_tipo_trabajo OUT VARCHAR2, 
    t_fecha OUT VARCHAR2, 
    t_cliente OUT VARCHAR2, 
    t_vehiculo OUT VARCHAR2, 
    t_cant_productos OUT NUMBER,
    t_empleado OUT VARCHAR2,
    p_output OUT VARCHAR2
)IS
    CURSOR c_clientes IS
        SELECT *
        FROM VISTA_TRABAJOS
        WHERE EMPLEADO LIKE t_nombre_emp;
BEGIN
    OPEN c_clientes;
    FETCH c_clientes INTO t_id_trabajo, t_tipo_trabajo, t_fecha, t_cliente, t_vehiculo, t_cant_productos, t_empleado;

    WHILE c_clientes%FOUND LOOP
        p_output := p_output || 'ID: ' || t_id_trabajo || '<br>' ||  
        'Tipo de Trabajo: ' || t_tipo_trabajo || '<br>'  || 'Fecha: ' || t_fecha || '<br>'  || 
        'Cliente: ' || t_cliente || '<br>' || 'Placa del Vehiculo: ' || t_vehiculo || '<br>'  || 
        'Cantidad de Productos a Usar: ' || t_cant_productos || '<br>' || 'Empleado a cargo: ' || t_empleado;
        FETCH c_clientes INTO t_id_trabajo, t_tipo_trabajo, t_fecha, t_cliente, t_vehiculo, t_cant_productos, t_empleado;
    END LOOP;
        
    CLOSE c_clientes;
END;
EXECUTE BUSCAR_TRABAJOS_POR_EMPLEADO('Bob');

--11. Cursor para mostrar los provedores segun el nombre 
CREATE OR REPLACE PROCEDURE c_seleccionar_proveedor(
    p_nombre_buscar IN VARCHAR,
    p_id_proveedor OUT NUMBER,
    p_nombre OUT VARCHAR2,
    p_num_telefono OUT VARCHAR2,
    p_direccion OUT VARCHAR2,
    p_detalles OUT VARCHAR2,
    p_output OUT VARCHAR2
)
IS
    -- Cursor para recuperar datos de proveedores
    CURSOR c_proveedor_buscar IS
        SELECT ID_PROVEEDOR, NOMBRE, NUM_TELEFONO, DIRECCION, DETALLES
        FROM PROVEEDORES
        WHERE NOMBRE = p_nombre_buscar;
BEGIN
    OPEN c_proveedor_buscar;
    FETCH c_proveedor_buscar INTO p_id_proveedor, p_nombre, p_num_telefono, p_direccion, p_detalles;

    -- Inicializar la variable de salida
    p_output := '';

    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_proveedor_buscar%FOUND LOOP
        p_output := p_output || 'ID: ' || p_id_proveedor || '<br>' || 
        'Nombre: ' || p_nombre || '<br>' ||  'Tel fono: ' || p_num_telefono || '<br>'  ||
        ' Direcci n: ' || p_direccion || '<br>'  || ' Detalles: ' || p_detalles ;
        FETCH c_proveedor_buscar INTO p_id_proveedor, p_nombre, p_num_telefono, p_direccion, p_detalles;
    END LOOP;

    CLOSE c_proveedor_buscar;
END;
EXECUTE c_seleccionar_proveedor('Fashion World');

--12. Cursor para mostrar los empleados por el correo dado 
CREATE OR REPLACE PROCEDURE BUSCAR_EMPLEADOS_POR_CORREO(
    e_nombre_correo IN VARCHAR2,
    e_nombre OUT VARCHAR2,
    e_apellido OUT VARCHAR2,
    e_num_telefono OUT VARCHAR2,
    e_correo_elect OUT VARCHAR2,
    e_salario OUT VARCHAR2,
    e_fecha_ingre OUT VARCHAR2,
    p_output OUT VARCHAR2
)IS
    CURSOR c_empleados_correo IS
        SELECT NOMBRE, APELLIDO, NUM_TELEFONO, CORREO_ELECT, SALARIO, FECHA_INGRE
        FROM EMPLEADOS
        WHERE e_correo_elect LIKE e_nombre_correo;
BEGIN
    OPEN c_empleados_correo;
    FETCH c_empleados_correo INTO e_nombre, e_apellido, e_num_telefono, e_correo_elect, e_salario, e_fecha_ingre;

    WHILE c_empleados_correo%FOUND LOOP
        p_output := p_output || 'Nombre Completo: ' || e_nombre || ' ' || e_apellido || '<br>'  ||
        'Tel fono: ' || e_num_telefono || '<br>'  || 
        'Correo Electr nico: ' || e_correo_elect || '<br>' || 'Salario: ' || e_salario || '<br>'  || 
        'Fecha de Ingreso: ' || e_fecha_ingre;
        FETCH c_empleados_correo INTO e_nombre, e_apellido, e_num_telefono, e_correo_elect, e_salario, e_fecha_ingre;
    END LOOP;
        
    CLOSE c_empleados_correo;
END;
EXECUTE c_seleccionar_proveedor('juan.gonzalez@example.com');

--13. Cursor para mostrar los trabajos segun el cliente
CREATE OR REPLACE PROCEDURE BUSCAR_TRABAJOS_SEGUN_CLIENTE(
    c_nombre_cliente IN VARCHAR2,
    t_id_trabajo OUT VARCHAR2,
    t_tipo_trabajo OUT VARCHAR2, 
    t_fecha OUT DATE, 
    t_cliente OUT VARCHAR2,
    t_vehiculo OUT VARCHAR2, 
    t_cant_productos OUT NUMBER,
    p_output OUT VARCHAR2
)IS
    CURSOR c_trabajos_cliente IS
        SELECT ID_TRABAJOS, TIPO_TRABAJO, FECHA, CLIENTE, VEHICULO, CANTIDAD_PRODUCTOS
        FROM VISTA_TRABAJOS
        WHERE CLIENTE LIKE c_nombre_cliente;
BEGIN
    OPEN c_trabajos_cliente;
    FETCH c_trabajos_cliente INTO t_id_trabajo, t_tipo_trabajo, t_fecha, t_cliente, t_vehiculo, t_cant_productos;

    WHILE c_trabajos_cliente%FOUND LOOP
        p_output := p_output || 'ID: ' || t_id_trabajo || '<br>' ||  
        'Tipo de Trabajo: ' || t_tipo_trabajo || '<br>'  || 'Fecha: ' || t_fecha || '<br>'  || 'Cliente: ' || t_cliente || '<br>'  ||
        'Placa del Vehiculo: ' || t_vehiculo || '<br>'  || 'Cantidad de Productos a Usar: ' || t_cant_productos;
        FETCH c_trabajos_cliente INTO t_id_trabajo, t_tipo_trabajo, t_fecha, t_cliente, t_vehiculo, t_cant_productos;
    END LOOP;
        
    CLOSE c_trabajos_cliente;
END;
EXECUTE BUSCAR_TRABAJOS_SEGUN_CLIENTE;

--14. Cursor para mostrar vehiculos por modelo
CREATE OR REPLACE PROCEDURE BUSCAR_VEHICULOS_POR_MODELO(
    p_modelo_buscar IN VARCHAR2,
    p_num_placa OUT VARCHAR2,
    p_cliente OUT NUMBER,
    p_num_motor OUT NUMBER,
    p_marca OUT VARCHAR2,
    p_color OUT VARCHAR2,
    p_modelo OUT VARCHAR2,
    p_ano OUT VARCHAR2,
    p_output OUT VARCHAR2
)
IS
    CURSOR c_vehiculos_modelo IS
        SELECT NUM_PLACA, ID_CLIENTE, NUM_MOTOR, MARCA, COLOR, MODELO, ANO
        FROM VEHICULOS
        WHERE MODELO LIKE p_modelo_buscar;
BEGIN
    OPEN c_vehiculos_modelo;
    FETCH c_vehiculos_modelo INTO p_num_placa, p_cliente, p_num_motor, p_marca, p_color, p_modelo, p_ano;
    
    -- Inicializar la variable de salida
    p_output := '';

    -- Procesar los datos del cursor y almacenar en p_output
    WHILE c_vehiculos_modelo%FOUND LOOP
        p_output := p_output || 'N mero de Placa: ' || p_num_placa || '<br>' || 'Cliente No. : ' || p_cliente || '<br>' ||
        ' N mero de Motor: ' || p_num_motor || '<br>'  || ' Marca: ' || p_marca || '<br>'  || 
        ' Color: ' || p_color || '<br>' || ' Modelo: ' || p_modelo || '<br>'  || 
        ' A o: ' || p_ano;
        FETCH c_vehiculos_modelo INTO p_num_placa, p_cliente, p_num_motor, p_marca, p_color, p_modelo, p_ano;
    END LOOP;

    CLOSE c_vehiculos_modelo;
END;
EXECUTE BUSCAR_VEHICULOS_POR_MODELO;

--15. Cursor para mostrar las valoraciones de 4
CREATE OR REPLACE PROCEDURE BUSCAR_VALORACIONES_IGUALES_A_4 (
    v_id OUT NUMBER,
    v_id_cliente OUT NUMBER,
    v_comentario OUT VARCHAR2,
    v_valoracion OUT NUMBER,
    v_fecha_emision OUT VARCHAR2,
    v_output OUT VARCHAR2
)
IS
    -- Cursor para buscar valoraciones mayores a 3
    CURSOR c_valoraciones_iguales_a_4 IS
        SELECT ID_VALORACION, CLIENTE, VALO_COMENTARIO, VALO_VALORACION, FECHA
        FROM VISTA_VALORACIONES
        WHERE VALO_VALORACION = 4;

BEGIN
    OPEN c_valoraciones_iguales_a_4;
    FETCH c_valoraciones_iguales_a_4 INTO v_id, v_id_cliente, v_comentario, v_valoracion, v_fecha_emision;
    
    -- Inicializar la variable de salida
    v_output := '';   
    
    -- Procesar los datos del cursor y almacenar en v_output
    WHILE c_valoraciones_iguales_a_4%FOUND LOOP
        v_output := v_output || 'ID: ' || v_id || '<br>'  || 'Cliente: ' || v_id_cliente || '<br>'  || 
                    'Comentario: ' || v_comentario || '<br>'  || 
                    'Fecha: ' || v_fecha_emision || '<br><br>';
        
        FETCH c_valoraciones_iguales_a_4 INTO v_id, v_id_cliente, v_comentario, v_valoracion, v_fecha_emision;
    END LOOP;

    CLOSE c_valoraciones_iguales_a_4;
END;
EXECUTE BUSCAR_VALORACIONES_IGUALES_A_4;

-- ============================================================
-- ============== PAQUETES Y FUNCIONES ================
--1)
CREATE OR REPLACE PACKAGE PKG_PROVEEDORES AS
    -- Declaraci n de la funci n
    FUNCTION F_eliminar_proveedor(
        p_id_proveedor IN NUMBER
    ) RETURN NUMBER;
END PKG_PROVEEDORES;

CREATE OR REPLACE PACKAGE BODY PKG_PROVEEDORES AS
    -- Implementaci n de la funci n
    FUNCTION F_eliminar_proveedor(
        p_id_proveedor IN NUMBER
    ) RETURN NUMBER IS
    BEGIN
        DELETE FROM proveedores
        WHERE ID_PROVEEDOR = p_id_proveedor;

        IF SQL%ROWCOUNT > 0 THEN
            COMMIT;
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END F_eliminar_proveedor;
END PKG_PROVEEDORES;

-- =================================================================
-- 2)
CREATE OR REPLACE PACKAGE PKG_TIPO_PRODUCTO AS
    -- Declaraci n de la funci n
    FUNCTION F_eliminar_tipo_producto(
        p_id IN NUMBER
    ) RETURN NUMBER;
END PKG_TIPO_PRODUCTO;

CREATE OR REPLACE PACKAGE BODY PKG_TIPO_PRODUCTO AS
    -- Implementaci n de la funci n
    FUNCTION F_eliminar_tipo_producto(
        p_id IN NUMBER
    ) RETURN NUMBER IS
    BEGIN
        DELETE FROM TIPO_PRODUCTO
        WHERE ID_TIPO_PRODUCTO = p_id;

        IF SQL%ROWCOUNT > 0 THEN
            COMMIT;
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END F_eliminar_tipo_producto;
END PKG_TIPO_PRODUCTO;

-- =================================================================
-- 3)
CREATE OR REPLACE PACKAGE PKG_EMPLEADOS AS
    -- Declaraci n de la funci n
    FUNCTION F_eliminar_empleado(
        p_id_empleado IN NUMBER
    ) RETURN NUMBER;
END PKG_EMPLEADOS;

CREATE OR REPLACE PACKAGE BODY PKG_EMPLEADOS AS
    -- Implementaci n de la funci n
    FUNCTION F_eliminar_empleado(
        p_id_empleado IN NUMBER
    ) RETURN NUMBER IS
    BEGIN
        DELETE FROM empleados
        WHERE ID_EMPLEADO = p_id_empleado;

        IF SQL%ROWCOUNT > 0 THEN
            COMMIT;
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END F_eliminar_empleado;
END PKG_EMPLEADOS;

-- =================================================================
-- 4)
CREATE OR REPLACE PACKAGE PKG_VALORACIONES AS
    -- Declaraci n de la funci n
    FUNCTION F_eliminar_valoracion(
        p_id_valoracion IN NUMBER
    ) RETURN NUMBER;
END PKG_VALORACIONES;

CREATE OR REPLACE PACKAGE BODY PKG_VALORACIONES AS
    -- Implementaci n de la funci n
    FUNCTION F_eliminar_valoracion(
        p_id_valoracion IN NUMBER
    ) RETURN NUMBER IS
    BEGIN
        DELETE FROM VALORACIONES
        WHERE ID_VALORACION = p_id_valoracion;

        IF SQL%ROWCOUNT > 0 THEN
            COMMIT;
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END F_eliminar_valoracion;
END PKG_VALORACIONES;

-- =================================================================
-- 5)
CREATE OR REPLACE PACKAGE PKG_PRODUCTOS AS
    -- Declaraci n de la funci n
    FUNCTION F_eliminar_producto(
        p_id_producto IN NUMBER
    ) RETURN NUMBER;
END PKG_PRODUCTOS;

CREATE OR REPLACE PACKAGE BODY PKG_PRODUCTOS AS
    -- Implementaci n de la funci n
    FUNCTION F_eliminar_producto(
        p_id_producto IN NUMBER
    ) RETURN NUMBER IS
    BEGIN
        DELETE FROM INVENTARIO
        WHERE ID_PRODUCTO = p_id_producto;

        IF SQL%ROWCOUNT > 0 THEN
            COMMIT;
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END F_eliminar_producto;
END PKG_PRODUCTOS;

-- =================================================================
-- 6)
CREATE OR REPLACE PACKAGE PKG_TRABAJOS AS
    -- Declaraci n de la funci n
    FUNCTION F_eliminar_trabajo(
        p_id_trabajo IN NUMBER
    ) RETURN NUMBER;
END PKG_TRABAJOS;

CREATE OR REPLACE PACKAGE BODY PKG_TRABAJOS AS
    -- Implementaci n de la funci n
    FUNCTION F_eliminar_trabajo(
        p_id_trabajo IN NUMBER
    ) RETURN NUMBER IS
    BEGIN
        DELETE FROM TRABAJOS
        WHERE ID_TRABAJO = p_id_trabajo;

        IF SQL%ROWCOUNT > 0 THEN
            COMMIT;
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END F_eliminar_trabajo;
END PKG_TRABAJOS;

-- =================================================================
-- 7)
CREATE OR REPLACE PACKAGE PKG_CLIENTES AS
    -- Declaraci n de la funci n
    FUNCTION F_eliminar_cliente(
        p_id_cliente IN NUMBER
    ) RETURN NUMBER;
END PKG_CLIENTES;

CREATE OR REPLACE PACKAGE BODY PKG_CLIENTES AS
    -- Implementaci n de la funci n
    FUNCTION F_eliminar_cliente(
        p_id_cliente IN NUMBER
    ) RETURN NUMBER IS
    BEGIN
        DELETE FROM CLIENTES
        WHERE ID_CLIENTE = p_id_cliente;

        IF SQL%ROWCOUNT > 0 THEN
            COMMIT;
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END F_eliminar_cliente;
END PKG_CLIENTES;

-- =================================================================
-- 8)
CREATE OR REPLACE PACKAGE PKG_EMPLEADOS_SALARIO AS
    -- Declaraci n de la funci n
    FUNCTION f_csalario RETURN NUMBER;
END PKG_EMPLEADOS_SALARIO;

CREATE OR REPLACE PACKAGE BODY PKG_EMPLEADOS_SALARIO AS
    -- Implementaci n de la funci n
    FUNCTION f_csalario RETURN NUMBER IS
        salario_promedio NUMBER;
    BEGIN
        SELECT AVG(SALARIO) INTO salario_promedio FROM EMPLEADOS;
        RETURN salario_promedio;
    END f_csalario;
END PKG_EMPLEADOS_SALARIO;

-- =================================================================
-- 9)
CREATE OR REPLACE PACKAGE PKG_CLIENTES_CANTIDAD AS
    -- Declaraci n de la funci n
    FUNCTION t_clientes RETURN NUMBER;
END PKG_CLIENTES_CANTIDAD;


CREATE OR REPLACE PACKAGE BODY PKG_CLIENTES_CANTIDAD AS
    -- Implementaci n de la funci n
    FUNCTION t_clientes RETURN NUMBER IS
        total_clientes NUMBER;
    BEGIN
        SELECT COUNT(*) INTO total_clientes FROM CLIENTES;
        RETURN total_clientes;
    END t_clientes;
END PKG_CLIENTES_CANTIDAD;

-- =================================================================
-- 10)
CREATE OR REPLACE PACKAGE PKG_TIPO_TRABAJO_ULTIMO AS
    -- Declaraci n de la funci n
    FUNCTION obtener_lastjob RETURN VARCHAR2;
END PKG_TIPO_TRABAJO_ULTIMO;

CREATE OR REPLACE PACKAGE BODY PKG_TIPO_TRABAJO_ULTIMO AS
    -- Implementaci n de la funci n
    FUNCTION obtener_lastjob RETURN VARCHAR2 IS
        ultimotrabajo VARCHAR2(25);
    BEGIN
        SELECT NOMBRE INTO ultimotrabajo
        FROM TIPO_TRABAJO
        WHERE ROWNUM = 1
        ORDER BY ID_TIPO_TRABAJO DESC;
        
        RETURN ultimotrabajo;
    END obtener_lastjob;
END PKG_TIPO_TRABAJO_ULTIMO;



--11. Eliminicacion de vehiculos
CREATE OR REPLACE FUNCTION F_eliminar_vehiculo(
    p_num_placa IN VARCHAR2
)
RETURN NUMBER
IS
BEGIN
    DELETE FROM VEHICULOS
    WHERE NUM_PLACA = p_num_placa;
    
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        RETURN 1;
    ELSE
        RETURN 0; 
    END IF;
END;

--12. Obtener la fecha del  ltimo trabajo realizado
CREATE OR REPLACE FUNCTION fecha_lastjob 
RETURN varchar2 IS
    ultima_fecha varchar2(500);
BEGIN
    SELECT MAX(FECHA) INTO ultima_fecha FROM TRABAJOS;
    RETURN ultima_fecha;
END fecha_lastjob;

--13. cliente con el mayor numero de valoraciones
CREATE OR REPLACE FUNCTION obtener_cliente_mas_valorado RETURN VARCHAR2 IS
    c_cliente VARCHAR2(22);
BEGIN
    SELECT c.nombre || ' ' || c.apellido AS "Nombre Completo" INTO c_cliente
    FROM CLIENTES c
    JOIN VALORACIONES val ON c.ID_CLIENTE = val.ID_CLIENTE
    GROUP BY c.ID_CLIENTE, c.nombre, c.apellido
    ORDER BY COUNT(val.ID_CLIENTE) DESC
    FETCH FIRST 1 ROWS ONLY;

    RETURN c_cliente;
END;

--14.obtener el producto m s antiguo en inventario
CREATE OR REPLACE FUNCTION f_oldestProduct 
RETURN VARCHAR2 IS
    producto_v VARCHAR2(25);
BEGIN
    SELECT NOMBRE INTO producto_v
    FROM INVENTARIO
    WHERE ROWNUM = 1
    ORDER BY FECHA_INGRE ASC;

    RETURN producto_v;
END;


--15. Edad promedio de los empleados
CREATE OR REPLACE FUNCTION edad_p_emp
RETURN NUMBER IS
    e_promedio NUMBER;
BEGIN
    SELECT FLOOR(AVG(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(FECHA_NAC, 'YY-MM-DD')))) AS "Edad Promedio" INTO e_promedio
    FROM EMPLEADOS;
    
    RETURN e_promedio;
END;

-- ============================================================
-- =================== TRIGGERS =======================
CREATE TABLE auditoria_clientes (
    ID_AUDITORIA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    TIPO_EVENTO VARCHAR2(10),
    USUARIO_BD VARCHAR2(30),
    FECHA_HORA_EVENTO TIMESTAMP,
    USUARIO_SO VARCHAR2(30),
    IP_MAQUINA VARCHAR2(15),
    ID_CLIENTE NUMBER,
    USERNAME_ANTES VARCHAR2(25),
    CONTRASENA_ANTES VARCHAR(25),
    NOMBRE_ANTES VARCHAR2(25),
    APELLIDO_ANTES VARCHAR2(25),
    DIRECCION_ANTES VARCHAR2(100),
    NUM_TELEFONO_ANTES VARCHAR2(10),
    CORREO_ELECT_ANTES VARCHAR2(25),
    CONSTRAINT PK_AUDITORIA_EMPLEADOS PRIMARY KEY (ID_AUDITORIA)
);

CREATE TABLE auditoria_sesiones (
    ID_SESION NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    TIPO_EVENTO VARCHAR2(30),
    USUARIO_BD VARCHAR2(30),
    FECHA_HORA_EVENTO TIMESTAMP,
    CONSTRAINT PK_AUDITORIA_SESIONES PRIMARY KEY (ID_SESION)
);
 
-- TRIGGER PARA GUARDAR ANTES DE BORRAR
CREATE OR REPLACE TRIGGER trg_auditoria_empleados_borrar
BEFORE DELETE ON CLIENTES
FOR EACH ROW
DECLARE
    tipo_evento VARCHAR2(10);
    c_id_cliente CLIENTES.ID_CLIENTE%TYPE;
    c_username CLIENTES.USERNAME%TYPE;
    c_contrasena CLIENTES.CONTRASENA%TYPE;
    c_nombre CLIENTES.NOMBRE%TYPE;
    c_apellido CLIENTES.APELLIDO%TYPE;
    c_direccion CLIENTES.DIRECCION%TYPE;
    c_telefono CLIENTES.NUM_TELEFONO%TYPE;
    c_correo CLIENTES.CORREO_ELECT%TYPE;
BEGIN
    IF DELETING THEN
        tipo_evento := 'DELETE';
    END IF;

    -- Guarda los valores antes de la operaci n en variables locales
    SELECT 
        :OLD.ID_CLIENTE,
        :OLD.USERNAME,
        :OLD.CONTRASENA,
        :OLD.NOMBRE,
        :OLD.APELLIDO,
        :OLD.DIRECCION,
        :OLD.NUM_TELEFONO,
        :OLD.CORREO_ELECT
    INTO 
        c_id_cliente,
        c_username,
        c_contrasena, 
        c_nombre, 
        c_apellido,
        c_direccion, 
        c_telefono, 
        c_correo
    FROM DUAL;

    -- Inserta en la tabla de auditor a
    INSERT INTO auditoria_clientes (
        TIPO_EVENTO, USUARIO_BD, FECHA_HORA_EVENTO, USUARIO_SO,
        IP_MAQUINA, ID_CLIENTE, USERNAME_ANTES, CONTRASENA_ANTES, NOMBRE_ANTES,
        APELLIDO_ANTES, DIRECCION_ANTES, NUM_TELEFONO_ANTES, CORREO_ELECT_ANTES
    )
    VALUES (
        tipo_evento, USER, CURRENT_TIMESTAMP, SYS_CONTEXT('USERENV', 'OS_USER'),
        SYS_CONTEXT('USERENV', 'IP_ADDRESS'), c_id_cliente, c_username, c_contrasena,
        c_nombre, c_apellido, c_direccion, c_telefono, c_correo
    );
END;

-- TRIGGER PARA GUARDAR ANTES DE ACTUALIZAR
CREATE OR REPLACE TRIGGER trg_auditoria_empleados_update
BEFORE UPDATE ON CLIENTES
FOR EACH ROW
DECLARE
    tipo_evento VARCHAR2(10);
    c_id_cliente CLIENTES.ID_CLIENTE%TYPE;
    c_username CLIENTES.USERNAME%TYPE;
    c_contrasena CLIENTES.CONTRASENA%TYPE;
    c_nombre CLIENTES.NOMBRE%TYPE;
    c_apellido CLIENTES.APELLIDO%TYPE;
    c_direccion CLIENTES.DIRECCION%TYPE;
    c_telefono CLIENTES.NUM_TELEFONO%TYPE;
    c_correo CLIENTES.CORREO_ELECT%TYPE;
BEGIN
    IF UPDATING THEN
        tipo_evento := 'UPDATE';
    END IF;

    -- Guarda los valores antes de la operaci n en variables locales
    SELECT 
        :OLD.ID_CLIENTE,
        :OLD.USERNAME,
        :OLD.CONTRASENA,
        :OLD.NOMBRE,
        :OLD.APELLIDO,
        :OLD.DIRECCION,
        :OLD.NUM_TELEFONO,
        :OLD.CORREO_ELECT
    INTO 
        c_id_cliente,
        c_username,
        c_contrasena, 
        c_nombre, 
        c_apellido,
        c_direccion, 
        c_telefono, 
        c_correo
    FROM DUAL;

    -- Inserta en la tabla de auditor a
    INSERT INTO auditoria_clientes (
        TIPO_EVENTO, USUARIO_BD, FECHA_HORA_EVENTO, USUARIO_SO,
        IP_MAQUINA, ID_CLIENTE, USERNAME_ANTES, CONTRASENA_ANTES, NOMBRE_ANTES,
        APELLIDO_ANTES, DIRECCION_ANTES, NUM_TELEFONO_ANTES, CORREO_ELECT_ANTES
    )
    VALUES (
        tipo_evento, USER, CURRENT_TIMESTAMP, SYS_CONTEXT('USERENV', 'OS_USER'),
        SYS_CONTEXT('USERENV', 'IP_ADDRESS'), c_id_cliente, c_username, c_contrasena,
        c_nombre, c_apellido, c_direccion, c_telefono, c_correo
    );
END;

-- TRIGGER QUE REGISTRA EL LOG ON DE LA BASE DE DATOS DE LENGUAJES
CREATE OR REPLACE TRIGGER auditoria_sesiones_trg_logon
AFTER LOGON ON proyecto.SCHEMA
DECLARE
    tipo_evento VARCHAR2(15);
BEGIN
    tipo_evento := 'LOG ON';
    
    INSERT INTO auditoria_sesiones (TIPO_EVENTO, USUARIO_BD, FECHA_HORA_EVENTO)
    VALUES (tipo_evento, SYS_CONTEXT('USERENV', 'OS_USER'), CURRENT_TIMESTAMP);
END;

-- TRIGGER QUE REGISTRA EL LOG OFF DE LA BASE DE DATOS DE LENGUAJES
CREATE OR REPLACE TRIGGER auditoria_sesiones_trg_logoff
BEFORE LOGOFF ON proyecto.SCHEMA
DECLARE
    tipo_evento VARCHAR2(15);
BEGIN
    tipo_evento := 'LOG OFF';
    
    INSERT INTO auditoria_sesiones (TIPO_EVENTO, USUARIO_BD, FECHA_HORA_EVENTO)
    VALUES (tipo_evento, SYS_CONTEXT('USERENV', 'OS_USER'), CURRENT_TIMESTAMP);
END;

-- TRIGGER QUE GUARDA LA INFORMACION DESPUES DE UN INSERT
CREATE OR REPLACE TRIGGER trg_auditoria_empleados_insert
AFTER INSERT ON CLIENTES
FOR EACH ROW
DECLARE
    tipo_evento VARCHAR2(10);
BEGIN
    tipo_evento := 'INSERT';

    -- Inserta en la tabla de auditor a
    INSERT INTO auditoria_clientes (
        TIPO_EVENTO, USUARIO_BD, FECHA_HORA_EVENTO, USUARIO_SO,
        IP_MAQUINA, ID_CLIENTE, USERNAME_ANTES, CONTRASENA_ANTES, NOMBRE_ANTES,
        APELLIDO_ANTES, DIRECCION_ANTES, NUM_TELEFONO_ANTES, CORREO_ELECT_ANTES
    )
    VALUES (
        tipo_evento, USER, CURRENT_TIMESTAMP, SYS_CONTEXT('USERENV', 'OS_USER'),
        SYS_CONTEXT('USERENV', 'IP_ADDRESS'), :NEW.ID_CLIENTE, :NEW.USERNAME, :NEW.CONTRASENA,
        :NEW.NOMBRE, :NEW.APELLIDO, :NEW.DIRECCION, :NEW.NUM_TELEFONO, :NEW.CORREO_ELECT
    );
END;

-- ========================================================

COMMIT;