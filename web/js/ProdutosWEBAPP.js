function calcularDesconto() {
    var request = new XMLHttpRequest();
    var id = document.getElementById("pid").value;
    var url = "ProdutosRESTAPP/descontar/" + id;
    request.open('GET', url, true);
    request.send();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            if (request.status == 200) {
                var discount = JSON.parse(request.responseText);
                if (discount.value > 0) {
                    alert("Possible discount of " + discount.value + " becomes available on " + discount.date);
                } else {
                    alert("This product is not eligible for the discount");
                }
            } else {
                alert("Error getting discount: " + request.responseText);
            }
        }
    };
}



