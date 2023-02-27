package produtos.web;

import produtos.modelo.ProdutoRECORD;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Produtos", urlPatterns = {"/procurar"})
public class ProdutosSERVLET extends HttpServlet {

    @Inject
    ProdutosBEAN produtosBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("nome");
        List<ProdutoRECORD> products = produtosBean.encontrarProduto(name);
        if (!products.isEmpty()) {
            produtosBean.setProducts(products);
        } else {
            produtosBean.setErrors(true);
            produtosBean.setStatus("Unable to find any products matching name '" + name + "'");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ProdutosListar.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = request.getParameter("error");
        if (error != null) {
            throw new ServletException("Test Servlet Error");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ProdutosProcurar.html");
        rd.forward(request, response);
    }

}
