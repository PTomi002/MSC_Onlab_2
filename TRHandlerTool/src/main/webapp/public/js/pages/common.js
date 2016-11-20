/*------------------------------*/
/*		GLOBAL VARIABLES		*/ 
/*------------------------------*/
var append_back = "footer";

/*------------------------------*/
/*		GENERAL AJAX FUNCTIONS 	*/ 
/*------------------------------*/
function sendAjaxRequestPost(URL, jsonData, contentType, dataType,
		successCallback, errorCallback) {
	sendAjaxRequest(URL, "POST", jsonData, contentType, dataType,
			successCallback, errorCallback);
}

function sendAjaxRequestGet(URL, jsonData, contentType, dataType,
		successCallback, errorCallback) {
	sendAjaxRequest(URL, "GET", jsonData, contentType, dataType,
			successCallback, errorCallback);
}

function sendAjaxRequest(URL, requestType, jsonData, contentType, dataType,
		successCallback, errorCallback){
	$.ajax({
		type : requestType,
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

/*------------------------------*/
/*	JSON RELATED FUNCTIONS		*/ 
/*------------------------------*/
function jsonify(data) {
	return JSON.stringify(data);
}