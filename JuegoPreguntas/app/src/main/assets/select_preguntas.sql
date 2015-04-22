select titulo, respuesta, solucion, idcategoria from preguntas 
left JOIN respuestas on idpregunta=idres 
left join soluciones on idpregunta=idsol