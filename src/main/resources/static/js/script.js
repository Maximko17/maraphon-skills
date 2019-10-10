function openImage(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

function PreviewImage() {
    var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

    oFReader.onload = function (oFREvent) {
        document.getElementById("uploadPreview").src = oFREvent.target.result;
    };
};

function onlineUpdate() {
    var input = document.getElementById("amount").value;
    var demo = document.getElementById("demo");
    demo.innerHTML = "$" + input;
}

function checkYear() {
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;

    if (month < 0 || month > 12) {
        alert("Ведите корректный месяц действия карты!");
        return false;
    }

    if (year < 2018 || year > 2025) {
        alert("Ведите корректный год действия карты!");
        return false;
    }

}

function validateForm() {
    var password1 = document.getElementById('password1');
    var password2 = document.getElementById('password2');
    if (password1.value !== password2.value) {
        alert('Проверьте пароли!');
        return false;
    }
}