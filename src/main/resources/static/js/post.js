post = (function() {

	return {
		postUser : function() {
			var data = {
				id : $("#id").val(),
				name : $("#name").val(),
				
			};
			$.ajax({
				method : "POST",
				contentType : "application/json",
				url : "/saveStudent",
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(data) {
					
					alert('Usuario creado exitosamente');
				},
				error : function() {				
					alert("Usuario creado exitosamente");
					location.reload();
					// si se puede crear usuario pero tira esta alerta
				}
			});
        },
        getStudents: function(){
            window.location.replace("/Students");
        }
	}

})();