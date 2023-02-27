<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>
        <link rel='stylesheet' type='text/css' href='/ProdutosWEBAPP/css/pm.css'>
        <title>Product List</title>
    </head>
    <body>
        <header class='header'>Products</header>
        <nav class='nav'><a href="/ProdutosWEBAPP/ProdutosProcurar.html">ProdutosProcurar</a></nav>
        <section class='content'>                   
            <c:choose>
                <c:when test="${!ProdutosBEAN.errors}">
                    <c:forEach items="${ProdutosBEAN.products}" var="produto">
                        <div class='data'>
                            <a href="ProdutoEditar.jsp?p_id=${produto.id}">${produto.id}</a>
                            ${produto.name}
                            ${produto.price}
                            ${produto.bestBefore}
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class='error'>${ProdutosBEAN.status}</div>
                </c:otherwise>
            </c:choose>
        </section>
        <footer class='footer'>Invoker used method ${pageContext.request.method}</footer>
    </body>
</html>
