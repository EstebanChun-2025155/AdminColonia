# Sistema Integral de Gestión Residencial

## Descripción

Nuestro Sistema Integral de Gestión Residencial es una aplicación web desarrollada para mejorar la administración, organización y control dentro de una colonia o residencial.

El sistema permite centralizar información relacionada con casas, residentes, vehículos, visitas, accesos, personal, amenidades, multas y pagos. Su objetivo principal es facilitar la gestión interna del residencial, reducir errores administrativos y mejorar el control de la información.

## Funcionamiento General

El sistema permite registrar, consultar, actualizar y eliminar información relacionada con las diferentes áreas administrativas del residencial.

Cada módulo cumple una función específica dentro del sistema y se relaciona con otros módulos para mantener un mejor control de la información.

Por ejemplo, una casa puede tener residentes asociados, vehículos registrados, visitas relacionadas y pagos vinculados. De igual forma, el control de accesos permite registrar entradas y salidas de visitas, residentes o personal, dejando un historial de movimientos dentro de la colonia.

## Módulos del Sistema

El sistema cuenta con los siguientes módulos:

### Casas

Permite administrar la información de las viviendas dentro del residencial.

Cada casa debe registrar datos como:

- Número de casa
- Dirección
- Estado
- Propietario
- Precio de la vivienda

### Residentes

Permite registrar y administrar a los residentes asociados a cada vivienda.

Cada residente debe poseer información como:

- Nombre
- DPI
- Teléfono
- Estado
- Casa asignada

### Vehículos

Permite llevar el control de los vehículos registrados dentro de la colonia.

Cada vehículo debe almacenar:

- Placa
- Marca o modelo
- Color
- Propietario
- Casa asociada

### Visitas

Permite registrar las visitas que ingresan al residencial.

Cada visita debe contener:

- Nombre de la visita
- Documento de identificación
- Placa del vehículo
- Motivo de la visita
- Casa que visita

### Accesos

Permite registrar la entrada y salida de personas dentro de la colonia.

Cada acceso debe de incluir:

- Tipo de persona
- Guardia responsable
- Hora de entrada
- Hora de salida

Este módulo ayuda a mantener un historial de movimientos y mejora el control de seguridad.

### Personal de Seguridad

Permite administrar al personal encargado de la vigilancia del residencial.

Cada registro debe de incluir:

- Nombre
- Puesto
- Jornada
- Salario
- Teléfono

### Personal de Limpieza

Permite controlar al personal encargado del mantenimiento y limpieza de las áreas del residencial.

Cada registro debe de incluir:

- Nombre
- Puesto
- Jornada
- Salario
- Teléfono

### Amenidades

Permite administrar las áreas comunes disponibles para los residentes.

Por ejemplo:

- Piscina
- Gimnasio
- Cancha
- Salón social

Cada amenidad debe registrar:

- Nombre
- Horario de uso
- Costo
- Estado
- Capacidad

### Multas

Permite registrar sanciones aplicadas a residentes o visitas.

Cada multa debe de incluir:

- Monto
- Descripción
- Fecha de emisión
- Estado
- Tipo de persona sancionada

### Pagos

Permite registrar pagos realizados dentro del residencial.

Los pagos se clasifican como:

- Pago de multa
- Pago de mantenimiento
- Pago de amenidad

Cada pago debe de contener:

- Monto
- Fecha de pago
- Método de pago
- Referencia

### Reportes 

Permite a los Residentes y al personal de Seguridad reportar quejas sobre incidentes, problemas de ruido, peleas, etc.

Cada Reporte se clasifica con:

- Id del Residente (tanto del que reporta como del reportado)
- Clasificación del reporte (queja, incidente, ruido, seguridad, otro)
- Descripción del Reporte
- Fecha en la que se realizó el Reporte
- Estado del Reporte (pendiente, en revision, resuelto, anulado)

## Roles
 ### Administrador (Seguridad)
    El perosnal de Seguridad cumple el rol de Administrador, tienen el control para poder eliminar, editar, buscar y crear.
    Para poder acceder como administrador se necesita:
        - Nombre con el cual esta registrado.
        - Su DPI.
    Al registrase con estos datos en el login podrá acceder como administrador del programa.

 ### Usuarios (Residentes)
    Los residentes cumplen el rol de Usuarios, ellos únicamente tiene acceso al apartado de Amenidades y de Reportes.
    Pueden relizar lo que son "reservas" de amenidades y reportar a otros residentess.
    Para poder acceder con el rol de Usuario se necesita:
        - Nombre con el cual esta registrado.
        - Su DPI

## Tecnologías Utilizadas

El proyecto utiliza las siguientes tecnologías:

- Java
- Spring Boot
- MySQL
- Thymeleaf
- HTML5
- CSS3
- API REST
- IntelliJ IDEA

## Paleta de Colores

La interfaz del sistema utiliza una paleta de colores cálida y neutral, diseñada para ofrecer una experiencia visual cómoda, ordenada y profesional.

Se emplearon tonos beige y crema como base para mantener una apariencia limpia y amigable hacía la vista. El color terracota se utiliza para destacar botones, acciones principales y elementos, mientras que los tonos oscuros se aplican en los textos para mejorar la legibilidad.

Esta combinación busca transmitir una imagen moderna, minimalista y elegante, manteniendo un equilibrio entre estética, funcionalidad y facilidad de uso.

### Colores
Para definir la paleta de colores se utilizaron los siguientes códigos:

    #D6C2A8 (beige) 
    #3E3A37 (negro grisaseo)
    #7A746E (gris) 
    #C46A3C (terracota) 
    #D98A5F (terracota claro) 
    #EFE6D8 (beige claro/crema) 
    #E4D6C3 (beige grisaseo) 
    #D57A66 (terracota crema) 
    #D8CBB8 (beige oscuro) 

