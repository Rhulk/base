# reservas


#Ramas avances y desarrollos

Doc de los avandes y modificaciones en las diferentes ramas

# Rama: feature/Mail
# Fecha Committer: 2021-05-27 10:37:27
# LastChange: Personalizado metodo send para el envio de correos desde la app
# Problem: Merge a Heroku-pre tras validación.
	Implementado el envio de mail y la activación de usuarios.
	
# Rama: Heroku-pre
# Fecha Committer: 2021-05-31 16:02:59
# LastChange: Ajustes para el mantenimiento de los usuarios
# Problem: En la BBDD de postgres el campos status tiene valor null en algunos usuarios y falla al recuperar dicho registro.
	* Entorno en el cual estaran las pre-modificaciones para desplegar en Heroku
	--> Punto a resolver operatividad con postges
	

# Rama: Heroku
# Fecha Committer: 2021-05-23 23:29:43
# LastChange: Merge remote-tracking branch 'origin/master' into delop (i18n)
# Problem: Merge de Heroku-pre tras validacion
	* Entorno Producción de Heroku estable.
	
	
# Rama: delop
# Fecha Committer: 2021-05-31 23:45
# LastChange: In line with branch Heroku-pre
# Problem:
	* Entorno principal de desarrollo
	
# Rama: master
# Fecha Committer: 2021-05-23 23:29:43
# LastChange: En linea con i18n, SearchList branch heroku,delop
	* Entorno estable base