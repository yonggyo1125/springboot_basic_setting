const tourGo = {
	/**
	* 경도 위도의 반경에 있는 관광지 정보 조회
	*
	 */
	search(lng, lat, radius) {
		if (!lng || !lat) {
			throw new Error("경도, 위도를 입력하세요.");
		}
		
		radius = radius || 20000;
		
		const url = `tour_info?lng=${lng}&lat=${lat}&radius=${radius}`;
		const xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		
		xhr.addEventListener("readystatechange", function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				const items = JSON.parse(xhr.responseText);
				const ul = document.getElementById("search_result");
				ul.innerHTML = "";
				for (const item of items) {
					const li = document.createElement("li");
					li.innerHTML = `
						<img src='${item.firstimage}' width='200'><br>
						<div>${item.addr1} ${item.addr2}</div>
						<div>${item.title}</div>
						<div>${item.tel}</div>
					`;
					ul.appendChild(li);
				}
				
			}
		});
		
		xhr.send(null);
		
		
	}
};

window.addEventListener("DOMContentLoaded", function() {
		/** 지도 띄우기 S  */
		const container = document.getElementById("map");
		const options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};
		
		const map = new kakao.maps.Map(container, options);
		
		/** 지도 띄우기 E */
		
		const marker = new kakao.maps.Marker({
			position: map.getCenter(),
		});
		
		marker.setMap(map);
		
		kakao.maps.event.addListener(map, 'click', function(e) {
				const latLng = e.latLng;
				marker.setPosition(latLng);
				
				const lng = latLng.getLng(); // 경도
				const lat = latLng.getLat(); // 위도
				try {
					tourGo.search(lng, lat, 20000);
				} catch (err) {
					alert(err.message);
				}
		});
});