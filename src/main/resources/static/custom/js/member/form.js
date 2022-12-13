$(document).ready(function() {
	$('#saveBtn').on('click', function() {
	
		$('#member-form').submit();
	});

	function isValidateBirthDate() {
		var dateStr = $('#dateOfBirth').val();
		var timestamp = Date.parse(dateStr)
		return !isNaN(timestamp)
	}

	$('#gotoListBtn').on('click', function() {
		window.location = "/member/list";
	});
});