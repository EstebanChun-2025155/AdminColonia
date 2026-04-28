   const botones = document.querySelectorAll(".tile-btn");
   const panel = document.getElementById("panelInfo");
   const cerrar = document.getElementById("cerrarPanel");

   const titulo = document.getElementById("panelTitulo");
   const texto = document.getElementById("panelTexto");

   const contenido = {
       quienes: {
           titulo: "¿Quiénes somos?",
           texto: "Somos una plataforma digital enfocada en la administración y control eficiente dentro de una colonia, residencial o condominio. Nuestro sistema permite centralizar la información más importante de residentes y viviendas brindando una experiencia organizada, moderna y segura."
       },

       proposito: {
           titulo: "Propósito",
           texto: "Nuestro propósito es optimizar la gestión administrativa dentro de una colonia, facilitando el acceso a información clave, mejorando la organización interna y permitiendo una toma de decisiones más rápida y efectiva."
       },

       vision: {
           titulo: "Visión",
           texto: "Queremos convertirnos en una herramienta innovadora y confiable en cuanto a la administración residencial, integrando funciones modernas que permitan una gestión inteligente, automatizada y accesible para todos los usuarios."
       },

       funcionalidad: {
           titulo: "Funcionalidad",
           texto: "El sistema permite registrar, consultar, actualizar y administrar información sobre casas, residentes y procesos internos. Además, brinda acceso rápido a las distintas entidades mediante una interfaz intuitiva y visualmente atractiva."
       }
   };

   botones.forEach(boton => {
       boton.addEventListener("click", () => {
           const tipo = boton.getAttribute("data-contenido");

           if (contenido[tipo]) {
               titulo.textContent = contenido[tipo].titulo;
               texto.textContent = contenido[tipo].texto;

               panel.classList.remove("oculto");
           }
       });
   });

   cerrar.addEventListener("click", () => {
       panel.classList.add("oculto");
   });

   panel.addEventListener("click", (e) => {
       if (e.target === panel) {
           panel.classList.add("oculto");
       }
   });