# Proyecto Origen: http://cristianruizblog.com/
#Ramas avances y desarrollos
Doc de los avances y modificaciones en las diferentes ramas

#####################################################################################
# Rama: feature/ActiveMail
# Fecha Committer: 2021-06-10 17:40
# LastChange: Operativo con ssl de heroku
# Origen: delop
# Situación: Finalizada
# Problem: Activar las cuentas nuevas mediante enlace a un correo
#####################################################################################	
# Rama: feature/Mail
# Fecha Committer: 2021-05-27 10:37:27
# LastChange: Personalizado metodo send para el envio de correos desde la app
# Origen: delop
# Situación: Finalizada
# Problem: Merge a Heroku-pre tras validación.
	Implementado el envio de mail y la activación de usuarios.
#####################################################################################	
#1 Rama: Heroku-pre
#2 Fecha Committer: 2022-06-30 21:52:59
#3 LastChange: Ajustes para el mantenimiento de los usuarios
#4 Origen: Heroku
#5 Situacion: Problemas con los ultimos cambios
#6 Problem: En la BBDD de postgres el campos status tiene valor null en algunos usuarios y falla al recuperar dicho registro.
	* Entorno en el cual estaran las pre-modificaciones para desplegar en Heroku
	--> Punto a resolver operatividad con postges
	* No login por la fuerza de pass creado user kk/kk codificado
	* Pendiente crear cuenta de hotmail. para validación del user
	* Problema recuperando datos de la BBDD tras los ultimos cambios.
	* Waiting for changelog lock - Pendiente poder desbloquear.
#7 Avances: Nuevo controlador mantenimientoCotroller para la gestión del mantenimiento./sql
	* 30/06/22 - Add validación para crear rol si no hay en el alta del usuario.
	* 11/07/22 - Add func create user por para metro de URL /addUser/nameUser
		Error: no valida el usuario. Resuelto.
	* 23/07/22 - Add userdefault user/123
#####################################################################################	
#1 Rama: Heroku-mtn
#2 Fecha Committer: 2022-07-29 22:15:00
#3 LastChange: Versión sin liquibase y funciones nemos seguras para desbloquear postgress
#4 Origen: Heroku-pre
#5 Situacion: Creando rama con la idea se listar usuarios y pasword tras restablecer la BBDD
#6 Problem: En la BBDD de postgres el campos status tiene valor null en algunos usuarios y falla al recuperar dicho registro.
	* Entorno en el cual estaran las pre-modificaciones para desplegar en Heroku
	--> Punto a resolver operatividad con postges
	* No login por la fuerza de pass creado user kk/kk codificado
	* Pendiente crear cuenta de hotmail. para validación del user
	* Problema recuperando datos de la BBDD tras los ultimos cambios.
	* Waiting for changelog lock - Pendiente poder desbloquear.
	* -- No logro lanzar querys sobre la tabla databasechaloglock
#7 Avances: Nuevo controlador mantenimientoCotroller para la gestión del mantenimiento./sql
	* 30/06/22 - Add validación para crear rol si no hay en el alta del usuario.
	* 11/07/22 - Add func create user por para metro de URL /addUser/nameUser
		Error: no valida el usuario. Resuelto.
	* 23/07/22 - Add userdefault user/123
	* 26/07/22 - Sin liquibase. No vale.
	* 27/07/22 - Bloque desbloqueo no funciona desde /sql falta validar cuando este bloqueado el liquibase.
	* 27/07/22 - Forzar el bloqueo de liquibase. En proceso. No funciono.
	* 27/07/22 - Listar los campos de data base change log lock en la vista. Agregados los metodos "/lock" "/log"
				 Falta impl el metodo log y la vista, el lock no reportada datos. 
	
	* 12/11/22 - Cambio de servidor de Heroku a RailWay
	* 12/11/22 - Desplegar RailWay
					
	
#####################################################################################

#1 Rama: Heroku
#2 Fecha Committer: 2021-06-14 11:52
#3 LastChange: Merge witch Heroku-pre
#4 Origen: Heroku-pre -- 2021-06-14 11:52
#5 Situación: Estable
#6 Problem: Tras pruebas en Heroku-pre ok
	* Entorno Producción de Heroku estable.
#####################################################################################
# Rama: delop
# Fecha Committer: 2023-06-16
# LastChange: Merge with feature/todo
# Origen: railWayPre -- 2023-03-27 18:39
# Situación: Estable
# Problem: 
	* applicantion.properties no se le reconoce
		Reason: Failed to determine a suitable driver class
		Resuelto con application.yml
	
#####################################################################################
# Rama: master
# Fecha Committer: 2023-08-07 01:00
# LastChange: Merge with SQL
	
# Origen: delop -- 2021-06-14 12:15
# Situación: Estable
# Problem: 
	Merge with Heroku-mtn
#####################################################################################
# Rama: railWayPre
# Fecha Committer: 2023-06-16 09:10
# LastChange: Merge with feature/to-do v1.3.0
	* Cambio de servidor de BBDD railWay
# Origen: delop -- 2021-06-14 12:15
# Situación: Deploy-RailWay
# Problem: 
	Configuración para desplegar en RailWay.
	Problema con el build.
		[Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
		Parece que segun el enlace anterior hay algun componente de maven que no le gusta.
		
		
		projectlombok
		

		
#####################################################################################
# Rama:SQL
# Fecha Committer: 2023-08-07 01:00
# LastChange: Up code Visual Studio
# Origen: sin determinar
# Situación:Estable
# Problem: 	 

		
# Resueltos:
	
# Avances:
	Recuperación de las tablas y campos de la BBDD con servicio REST
	Cambiar las llamas de la capa service de UserService a SqlService
	Recuperar los campos del nuevo select.
	Operatividad entre la gestión de los campos.
	Limpiar los campos btn al cambiar de tabla.
	Organización y mejoras visuales de la vista tras las cargas de datos de los servicios web
	Imp btn que oculte el textArena de la consulta.
	Implementar el select del where con los campos de la tabla.(En proceso falta quitar el *)
	Borrar los where al cambiar de tabla.
	SelectWhere tiene un blanco al añadir filtros al where.
	Añadir los filtros where al resultado de la consulta.
	Eliminar el textarea si se cambiar de tabla.
	CLEAN CODE in sql.js -- Avanzado pero no finalizado.
	Con una BBDD en blanco:
		Null value was assigned to a property [class com.alquiler.reservas.entity.User.status] of primitive 
		type setter of com.alquiler.reservas.entity.User.status; nested exception is org.hibernate.PropertyAccessException: 
		Null value was assigned to a property [class com.alquiler.reservas.entity.User.status] of primitive 
		type setter of com.alquiler.reservas.entity.User.status	
		Solución: Añadir en liquibase valores en el campo status para los usuarios creados.
	Sincronizado el mismo orde para las sql de CamposAndTipos.
		order by TYPE_NAME(c.user_type_id) 
	
# Next:
	
	Determinar que tipo de campo "date" bigint... se añade al filtro y limitar las opciones segun el campo
		SELECT c.name AS col_name,         TYPE_NAME(c.user_type_id) AS type_name 
		FROM sys.objects AS o JOIN sys.columns AS c  ON o.object_id = c.object_id WHERE o.name = 'dim_Hechos'  
	


	
	

#####################################################################################
# Rama: feature/toDo
# Fecha Committer: 2023-06-15 16:00
# LastChange: Implementación módulo todo y mejoras de la gestion de los menus
# Origen: delop ?
# Situación: Estable.
# Problem: 
		 
		 
		
# Resueltos:
		Falla la edición de usuarios. Faltaba el nuevo Objeto de todoForm -- Mejor no cargar el objeto y ocultar la parte que no se usa.
		Falla el guardar user. Se controla por seguridad no cargar la parte de todo's desde el ata de usuarios.
		No aparece el formulario de ata de todo. --> No estaba el activoTodo a true.
		Falla las validaciones del formulario.
		Tambien falla la validación de usuarios.
# Avances:
		Se implementa el módulo de todo para recuperar el listado.
		Se implementa mejoras en el menú principal.
		Fusionar con otras ramas. - >Con SQL
		Se implementa el alta de to-dos
		Se implementa el edit to-do falta mejorarlo
		Se mejora y optimiza la gestión de los módulos evitando cargar partes de la vista no necesarias ni los objetos de dichas partes.
		En el main hay pruebas de lectura de xml ?¿
		falta la gestión de las prioridades ?¿
		/userForm change for /userList
		Se implementa metodo /AltaUser anteriormente se cargaba con el mismo que el /listUser -> old userForm Creo
		Implementación del Crud:
			Altas.
			Editar el todo Avanzado
			Borrar el to-do							
				Simplificar las variables de acceso o visualización de modulos:
			Todo:
			tabTodo: 	 Con valor "active" para mostrar el tab Todo
		*	activoFormTodo: true o false activa link todo-form.html
		*	formTabTodo: Con valor "active" para mostrar el tab del form del todo 
			editModeTodo: true o false valida si es modo edición del todo.
			listTabTodo: Con valor "active" para mostrar el tab módulo Todo.
			
			User:
			tabUser: 	Con valor "active" para mostrar el tab User
		*	activoFormUser: true o false activa link user-form.html
		*	formTabUser:	Con valor "active" para mostrar el user-form.html
			editModeUser:	Con valor true para activar la opción editar del form
			listTabUser: Con valor "active" muestra el tabUser y tabListUser
			setupTabaUser: Con valor "active" muestra el tab setup		 
			
			Sql:
			tabAdmin: 	Con valor "active" para motrar el tab Admin
			listTabSql: Con valor "active" para mostrar el tab Sql
			listTabLog: Con valor "active" para mostrar el tab Log
		
# Next:

		Testar modulo.			
		
		Exception evaluating SpringEL expression: "#authentication.getPrincipal().getUsername()" (template: "security/user-form/user-view" - line 72, col 60)
		

		
#####################################################################################
# Rama: feature/curso
# Fecha Committer: 16/10/23
# LastChange: Recuperar si el check esta o no marcado.
# Origen: 
# Situación: Avanzada.
	Creadas las views del listado y detalle del curso.
	Y la parte correspondiente del negocio.
	
# Problem: 

	

# Resueltos:
	Creando las pantallas del módulo
	Se muestran todos o casi las pantallas revisión de las propiedades que controlan lo que se ve.
	SQL estructura inicial.
		Alta detalle cursos por BBDD
	Modelo de datos:
	Entitys:
		Curso and CategoriaCurso
	Services:
		CursoService and CursoServiceImp
	Repository JPA:
		CursoRepository
	
	Carga inicial del modelo a la vista.
	Generado el dashboard dinamicamente
	Generar el detalle del curso dinamicamente
		Integrado listado y el recurso 
		Paginado de los recursos sincronizado con el listado.
		
	Listado de apuntes CRUD y su paginado.

	Control de los videos vistos por cada usuario.
		Crear servicio Rest
		Al marcar el check.
		Recuperar si el check esta o no marcado.
		...
		
	Recuperar el usuario logueado.
	
	Crear pestañas nuevas sub menus cursos:
	
	
	Gestiones con los cursos desde el dashboard de cursos:
		Follow curso.
		Unfollow curso.
		Detalle curso. "Actual Join Curso"
		Editar curso.
		Controlar si ya estamos en el curso o no.
		Mejorar el sistema de btns Follow and Unfollow.
	
	Falla la modificación del apunte. OK
	
	Unificar las respuestas REST con objeto Respuesta.
	
	
	
# Avances - Next to:

	Control de los videos vistos por cada usuario.
		Al finalizar el video marcar como visto.
		Reproducion automatica de videos.
		
	
	Importar curso nuevo:
		Fase actual por script sql.
		Fase 2: Formulario web crear y editar los existentes.
			Desde un dashboard de cursos seleccionar curso a editar.
			Mostrar cb con los capitulos, apartados y aportes.
				Opcion de añadir uno nuevo o editar uno que seleccione.
				
		Fase 3: Importar desde un excel.
		
	
			
	Crear pestañas nuevas sub menus cursos:
		Mis cursos:
			Seguimiento de mis cursos actuales.
		Alta:
			Creación de nuevos cursos.
		Editar:
			Edición de los cursos existentes.
		All cursos:
			contiene todos los cursos.
			

		
		
		
			
	

#####################################################################################
