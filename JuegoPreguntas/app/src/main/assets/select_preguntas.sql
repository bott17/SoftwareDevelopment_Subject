select titulo, tipopregunta, respuesta,correcta,idcategoria,recurso,dificultad from preguntas 
left JOIN respuestas on preguntas.rowid=idpregunta