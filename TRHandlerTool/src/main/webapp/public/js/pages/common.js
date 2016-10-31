//Global variables
var append_back = "footer";

//AJAX
function sendAjaxRequestPost(URL, jsonData, contentType, dataType,
		successCallback, errorCallback) {
	$.ajax({
		type : "POST",
		url : URL,
		data : jsonData,
		contentType : contentType,
		dataType : dataType,
		success : function(data, textStatus, jqXHR) {
			if (typeof successCallback === 'function') {
				successCallback(data, textStatus, jqXHR);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (typeof errorCallback === 'function') {
				errorCallback(jqXHR, textStatus, errorThrown);
			}
		}
	});
}

function sendAjaxRequestGet(URL, jsonData, contentType, dataType,
		successCallback, errorCallback) {
	$.ajax({
		type : "GET",
		url : URL,
		data : jsonData,
		contentType : contentType,
		dataType : dataType,
		success : function(data, textStatus, jqXHR) {
			if (typeof successCallback === 'function') {
				successCallback(data, textStatus, jqXHR);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (typeof errorCallback === 'function') {
				errorCallback(jqXHR, textStatus, errorThrown);
			}
		}
	});
}

// JSON.stringify data
function jsonify(data) {
	return JSON.stringify(data);
}