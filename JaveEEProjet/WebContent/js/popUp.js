function init(){
	let btnPopup = document.querySelectorAll("[data-popup-ref]");
	
	btnPopup.forEach( btn => {
	btn.addEventListener('click', activePopup );
	
	function activePopup(){
	    let popupId = btn.getAttribute('data-popup-ref');
	    let popup = document.querySelector(`[data-popup-id='${popupId}']`);
	                        let popupContent = popup.querySelector('.popup-content');
	                        let popupClose = popup.querySelectorAll('[data-dismiss-popup]');
	
	                        popupClose.forEach(btn => {
	                            btn.addEventListener("click", onPopupClose);
	                        });
	
	                        popup.classList.add('active');
	                        popupContent.classList.add('active');
	
	                        function onPopupClose(){
	                            popup.classList.remove('active');
	                            popupContent.classList.remove('active');
	                        };
	                    }
	});
};