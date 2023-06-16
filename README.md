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
# Fecha Committer: 2022-06-12 18:00
# LastChange: Merge with Heroku-mtn
	* Cambio de servidor de BBDD railWay
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
# Fecha Committer: 2022-12-13 11:00
# LastChange: Avances de JS para el generador
# Origen: sin determinar
# Situación:Beta 1
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


