# Rama:SQL
	Fecha Committer: 2023-08-07 01:00
 	LastChange: Up code Visual Studio
  	Origen: sin determinar
  	Situación:Estable
  
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
