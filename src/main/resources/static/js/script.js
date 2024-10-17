let storeIds = JSON.parse(localStorage.getItem("favoriteStores")) || [];
const API_KEY = "78a4b456e0a19ac893591fb5dc67d523"; // JavaScript key
let selectedStore;
let map; // 전역 변수로 지도 저장

kakao.maps.load(() => {
    initMap();
    updateFavoriteList();
});

// 지도 초기화 함수
function initMap() {
    const mapContainer = document.getElementById("map");
    const mapOptions = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 기본 중심 좌표
        level: 3, // 확대 레벨
    };
    map = new kakao.maps.Map(mapContainer, mapOptions); // 전역 변수로 지도 인스턴스 저장
}

function searchStores() {
    const query = document.getElementById("storeQuery").value.trim();
    const searchResults = document.getElementById("searchResults");
    searchResults.innerHTML = ""; // 이전 검색 결과 초기화

    if (!query) {
        alert("검색어를 입력하세요.");
        return;
    }

    fetch(`https://dapi.kakao.com/v2/local/search/keyword.json?query=${encodeURIComponent(query)}`, {
        headers: {
            Authorization: `KakaoAK ${API_KEY}`,
        },
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then((data) => {
        if (!data.documents || data.documents.length === 0) {
            alert("검색 결과가 없습니다.");
            return;
        }

        // 검색 결과가 있을 경우
        data.documents.forEach((store) => {
            const li = document.createElement("li");
            li.textContent = `${store.place_name} - ${store.address_name}`;
            li.onclick = () => {
                showLocation(store.address_name);
                selectedStore = store.place_name;
                document.getElementById("storeId").value = selectedStore;
                document.getElementById("addFavoriteBtn").scrollIntoView({ behavior: "smooth" });
            };

            searchResults.appendChild(li);
        });
    })
    .catch((error) => {
        console.error("Error:", error);
        alert("가게 검색에 실패했습니다: " + error.message);
    });
}

// 주소로 위치 표시
function showLocation(address) {
    const geocoder = new kakao.maps.services.Geocoder();

    geocoder.addressSearch(address, function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
            const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            map.setCenter(coords); // 지도 중심을 결과 좌표로 설정
            const marker = new kakao.maps.Marker({ map: map, position: coords });
            const infowindow = new kakao.maps.InfoWindow({
                content: `<div style="width:150px;text-align:center;padding:6px 0;">${address}</div>`,
            });
            infowindow.open(map, marker);
        }
    });
}

// 즐겨찾기 추가
function addFavorite() {
    if (selectedStore && !storeIds.includes(selectedStore)) {
        storeIds.push(selectedStore);
        localStorage.setItem("favoriteStores", JSON.stringify(storeIds));
        updateFavoriteList();
        alert(`${selectedStore} 가 즐겨찾기 목록에 추가되었습니다.`);
    } else if (storeIds.includes(selectedStore)) {
        alert(`${selectedStore} 는 이미 즐겨찾기 목록에 있습니다.`);
    } else {
        alert("가게를 선택하세요.");
    }
}

// 즐겨찾기 목록 업데이트
function updateFavoriteList() {
    const favoriteList = document.getElementById("favoriteList");
    favoriteList.innerHTML = "";

    storeIds.forEach((id) => {
        const li = document.createElement("li");
        li.textContent = id;

        const removeButton = document.createElement("span");
        removeButton.innerHTML = '<i class="fa fa-times" aria-hidden="true"></i>';
        removeButton.style.cursor = "pointer";
        removeButton.style.marginLeft = "10px";
        removeButton.onclick = () => {
            removeFavorite(id);
        };

        li.appendChild(removeButton);
        favoriteList.appendChild(li);
    });
}

// 즐겨찾기 제거
function removeFavorite(storeId) {
    const index = storeIds.indexOf(storeId);
    if (index > -1) {
        storeIds.splice(index, 1);
        localStorage.setItem("favoriteStores", JSON.stringify(storeIds));
        updateFavoriteList();
        alert(`${storeId} 는 즐겨찾기 목록에서 삭제되었습니다`);
    } else {
        alert(`${storeId} 는 즐겨찾기 목록에 없습니다.`);
    }
}

// 가게 목록 페이지로 이동
function showStoreList() {
    window.location.href = '/favorite/storeList'; // 가게 목록 페이지로 이동
}

// 가게 검색 필터링
function filterStores() {
    const query = document.getElementById('storeQuery').value.toLowerCase();
    const rows = document.querySelectorAll('#storeTableBody tr');

    rows.forEach(row => {
        const storeName = row.cells[1].textContent.toLowerCase(); // 사업장명
        if (storeName.includes(query)) {
            row.style.display = ''; // 보여주기
        } else {
            row.style.display = 'none'; // 숨기기
        }
    });
}
