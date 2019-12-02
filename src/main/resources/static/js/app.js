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
	
	function initSendRequest()
	{
		$(document).on("click", "[id^='btn-possession-']", function(e){
			
			var params = {
				possessionId : $(this).data("id"),
				holderId : $(this).data("holder")	
			}; 
			
			var $this = $(this);
			
			$.get(baseApiUrl + "createRequest?", params)
				.done(function (data){
					if(data == null)
						alert("Impossible d'effectuer la réservation.");
					else {
						$("#card-"+params.possessionId).css("backgroundColor","#96d696");
						$this.parent().html("");
						alert("Votre demande a était bien envoyée. Vous allez revecoir un retour dés que le propriétaire valide votre demande.");
					}
				})
				.fail(function (err){
					alert("Impossible d'effectuer la réservation.");
				});
		});
	}
	
	return {
		init : function(){
			initUpdateRequestStatus();
			initSendRequest();
		}
	};
	
})();

	
	
$(function(){
	FindRoof.init();
});