$(document).ready(function() {
	$.validator.addMethod("regexp_email", function(value, element, regexpr) {          
	    return regexpr.test(value);
	}, "Please enter a valid email.");
	
	$.validator.addMethod("regexp_password", function(value, element, regexpr) {          
	    return regexpr.test(value);
	}, "Please enter a valid password.");
	
	$('#registration-form').validate({
		rules : {
			username : {
				maxlength : 15,
				minlength : 5,
				required : true
			},
			firstname : {
				maxlength : 15,
				minlength : 1,
				required : true
			},
			lastname : {
				maxlength : 15,
				minlength : 1,
				required : true
			},
			email : {
				regexp_email: /([a-zA-Z]{3,15})+(@)([a-zA-Z]{3,15})+(.)([a-zA-Z]{2,5})+/,
				maxlength : 15,
				email : true,
				required : true
			},
			password : {
				regexp_password: /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{5,15}$/,
				minlength : 5,
				maxlength : 15,
				required : true
			},
			passwordConfirmation : {
				maxlength : 15,
				minlength : 5,
				required : true,
				equalTo : "#password"
			}
		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('error');
		}
	});
});