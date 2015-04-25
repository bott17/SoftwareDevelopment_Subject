select titulo, respuesta,correcta,idcategoria,dificultad from preguntas 
left JOIN respuestas on preguntas.rowid=idpregunta