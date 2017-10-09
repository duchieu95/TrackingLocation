
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCKhF2k8xwn80GfatlTVg8BdXdo7MMLs20",
    authDomain: "trackinglocation-c652b.firebaseapp.com",
    databaseURL: "https://trackinglocation-c652b.firebaseio.com",
    projectId: "trackinglocation-c652b",
    storageBucket: "trackinglocation-c652b.appspot.com",
    messagingSenderId: "814803211154"
  };
  firebase.initializeApp(config);
  //function retrieve() {
/*  	var ref = firebase.database().ref().child("Location");
   // var messagesRef = new Firebase("https://glaring-torch-4299.firebaseio.com/");
    ref.once('value', function (snapshot) {
        var data = snapshot.val();
        var lat = data.lati;
        var lng = data.longti;
        console.log(lat+ "-"+lng);

        initMap(lat, lng);
    });
//}
*/


var ref = firebase.database().ref().child("Location");

            ref.on("value", function (snapshot) {
                snapshot.forEach(function (childSnapshot) {
                    // key will be "ada" the first time and "alan" the second time
                    var key = childSnapshot.key;
                    // childData will be the actual contents of the child
                    var childData = childSnapshot.val();
                           var lat = childData.lati;
        var lng = childData.longti;
                     initMap(lat, lng);
                    console.log(snapshot.val());
                });
            }, function (error) {
                console.log("Error: " + error.code);
            });
            

	var map;
     // var latLng = { lat: 10.790476, lng:106.682410 }
     // var latLng = { lat: latitude, longtitude }
      function initMap(lat, lng) {
 var latLng = new google.maps.LatLng(lat, lng);

   //10.790476, 106.682410
        map = new google.maps.Map(document.getElementById('map'), {
          center: latLng,
          zoom: 20
        });

      var marker = new google.maps.Marker({
       position: latLng,
      map: map,
      });
      }

    