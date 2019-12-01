var FindRoof = (function (){
	
	var baseApiUrl = "/api/"; 
	
	function initUpdateRequestStatus()
	{
		$(document).on("click", "[id^='btn-request-']", function(e){
			var params = {
				contractId : $(this).data("id"),
				status : $(this).data("status")	
			}; 
			
			var $this = $(this);
			
			$.get(baseApiUrl + "contractUpdateStatus", params)
				.done(function (data){
					if(data == false)
						alert("Impossible de mettre à jour votre annonce.");
					else {
						$("#label-request-"+params.contractId+"-status").html("Valider");
						$("#card-"+params.contractId).css("backgroundColor","#96d696");
						$this.parent().html("");
					}
				})
				.fail(function (err){
					alert("Impossible de mettre à jour votre annonce.");
				});
		});
	}
	
	return {
		init : function(){
			initUpdateRequestStatus();
		}
	};
	
})();

	
	
$(function(){
	FindRoof.init();
});