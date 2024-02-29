function genera(){
	 
	
	if (typeof document.getElementById('thgenera') != 'undefined'){
      document.getElementById('thgenera').disabled=true
	
	}
	//var target = window.parent.name;
	document.FormOrdVenTestataEstratto.thNomeFinestra.value = window.parent.name;
	var res = runActionDirect("GENERA", "action_submit", "OrdineVendita", "", "new", "no");
	return res;
}