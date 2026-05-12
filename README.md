# Sistema Integral de Gestión Residencial

---

## Descripción
Este proyecto consiste en una aplicación web diseñada para centralizar y mejorar la administración, organización y control dentro de una colonia o residencial. 
El sistema permite gestionar de manera eficiente la información de casas, residentes, seguridad y finanzas, con el objetivo de reducir errores administrativos y 
optimizar el acceso a la información.


---

## Funcionamiento General
El sistema opera bajo una arquitectura modular donde cada sección cumple una función específica pero permanece interconectada. Por ejemplo, al registrar una 
vivienda, esta queda vinculada a sus respectivos residentes, vehículos y estados de cuenta. El control de accesos permite llevar un historial detallado de 
movimientos, mejorando la seguridad interna mediante el registro de ingresos y egresos en tiempo real.

---

## Módulos del Sistema

### Gestión de Viviendas y Residentes
* **Casas:** Administración de la base de datos de inmuebles, incluyendo dirección, número de casa, estado actual y valor.
* **Residentes:** Registro de información personal, DPI y teléfonos, vinculando a cada persona con su hogar asignado.
* **Vehículos:** Control de acceso vehicular mediante el registro de placas, marcas, modelos y colores por propietario.

### Seguridad y Control de Accesos
* **Visitas:** Registro de personas externas, incluyendo documento de identificación, placa del vehículo y motivo de la visita.
* **Accesos:** Monitoreo de entradas y salidas gestionado por el personal de seguridad, registrando la hora exacta y el guardia responsable.
* **Personal:** Administración de empleados de seguridad y mantenimiento, con control de puestos, jornadas laborales y salarios.

### Servicios y Administración Financiera
* **Amenidades:** Control de áreas comunes como piscinas, gimnasios o salones sociales, gestionando horarios, costos de uso y capacidad permitida.
* **Multas:** Registro y seguimiento de sanciones aplicadas, detallando el monto, la descripción de la falta y el estado de la misma.
* **Pagos:** Gestión de cobros por mantenimiento, multas o uso de amenidades, incluyendo fechas, métodos de pago y números de referencia.
* **Reportes:** Registro de incidentes, quejas u otras situaciones ocurridas en el residencial, incluyendo fecha, descripción, estado y responsable.

---

## Tecnologías Utilizadas
El desarrollo se realizó integrando las siguientes herramientas tecnológicas:
* **Lenguaje:** Java
* **Framework:** Spring Boot (API REST)
* **Base de Datos:** MySQL
* **Motor de Plantillas:** Thymeleaf
* **Frontend:** HTML5 y CSS3
* **Entorno de Desarrollo:** IntelliJ IDEA

---

## Paleta de Colores e Interfaz
La interfaz del sistema se diseñó bajo una estética neutral y profesional para garantizar la facilidad de uso:
* **Colores Base:** Se utilizaron tonos beige y crema para una apariencia limpia que no canse la vista del usuario.
* **Elementos de Acción:** El color terracota se empleó exclusivamente para botones y funciones principales, facilitando la navegación.
* **Legibilidad:** Se aplicaron tonos oscuros en las tipografías para asegurar un contraste óptimo en cualquier pantalla.
