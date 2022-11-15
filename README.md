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
# Fecha Committer: 2021-06-21 16:55
# LastChange: Activar user cifrando username
# Origen: Heroku -- 2021-06-14 11:52
# Situación: Estable
# Problem: Estable
	* Entorno principal de desarrollo
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
# Fecha Committer: 2022-11-15 15:00
# LastChange: Merge with Heroku-mtn
	* Cambio de servidor de BBDD railWay
# Origen: delop -- 2021-06-14 12:15
# Situación: No-Deploy-RailWay
# Problem: 
	Configuración para desplegar en RailWay
	
#####################################################################################