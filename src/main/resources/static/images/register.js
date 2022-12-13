$(document).ready(function(e){
	$('#signUp').submit(function(e) {
		e.preventDefault();
		var formData = $('#signUp').serializeArray();
		var data = {};
		
		$.each(formData, function(i, v) {
			data[v.name] = v.value;
		});
		data.id='0';
		console.log(data);
		
			$.ajax({
				type: 'POST',
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(data),
				url: 'signup',
				success: function(data) {
					if (data.isSuccess) {
						alert("Registered Successfully! Go to Login?");
						window.location.href = "login.html";
					} else {
						alert("Error Occured! Try again?");
					}
				},
			});
		
	});


		
});