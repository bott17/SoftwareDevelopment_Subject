select titulo, respuesta,correcta,idcategoria from preguntas 
left JOIN respuestas on preguntas.rowid=idpregunta