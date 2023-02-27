<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>
        <link rel='stylesheet' type='text/css' href='css/pm.css'>
        <script type="text/javascript" src="js/ProdutosWEBAPP.js"></script> 
        <title>Edit Product</title>
    </head>
    <body>
        <header class='header'>Edit Product</header>
        <nav class='nav'><a href="/ProdutosWEBAPP/ProdutosProcurar.html">ProdutosProcurar.html</a></nav>
        <section class='content'>                   
            <c:if test="${ProdutosBEAN.status != null}">
                <div class="${(ProdutosBEAN.errors)?'error':'info'}">${ProdutosBEAN.status}</div>
            </c:if>
                <form action="ProdutoEditar.jsp" method="POST">
                    <div class='field'>
                        <label for="pid">ID:</label>
                        <input type="text" id="pid" name="p_id" value="${ProdutosBEAN.product.id}" readonly>
                    </div>
                    <div class='field'>
                        <label for="pname">Name:</label>
                        <input type="text" id="pname" name="p_name" value="${ProdutosBEAN.product.name}" required>
                    </div>
                    <div class='field'>
                        <label for="pprice">Price:</label>
                        <input type="text" id="pprice" name="p_price" value="${ProdutosBEAN.product.price}" required>
                    </div>
                    <div class='field'>
                        <label for="pdate">Best Before:</label>
                        <input type="text" id="pdate" name="p_date" value="${ProdutosBEAN.product.bestBefore}">
                    </div>
                    <div class='field'>
                        <input type="submit" id="submit" value="Recarregar página (via WEBFILTER)">
                        <input type="button" value="Verificar desconto (via RESTAPP)" onclick="calcularDesconto()">
                    </div>
                </form>
        </section>
        <footer class='footer'>Invoker used method ${pageContext.request.method}</footer>
    </body>
</html>
