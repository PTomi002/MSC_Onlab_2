$(document).on('click', '#menu-button', function() {
	var menu_items = $('#menu-items');
	var class_hidden = "hidden";
	
	var dialog = bootbox.dialog({
		title: "Side menu",
		message : menu_items,
		closeButton: false,
		buttons : {
			back : {
				label : "Back",
				className : "btn-primary"
			}
		}
	}).on('shown.bs.modal', function() {
		menu_items.removeClass(class_hidden);
	}).on('hide.bs.modal', function() {
		menu_items.addClass(class_hidden);
		menu_items.appendTo(append_back);
	});
});