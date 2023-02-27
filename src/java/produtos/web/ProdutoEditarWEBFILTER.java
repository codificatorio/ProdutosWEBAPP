package produtos.web;

import produtos.modelo.ProdutoRECORD;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

// era: Product FILER
@WebFilter(urlPatterns = "/ProdutoEditar.jsp")
public class ProdutoEditarWEBFILTER implements Filter {

    @Inject
    private ProdutosBEAN produtosBean;
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String pid = request.getParameter("p_id");
        Integer id = Integer.parseInt(pid);
        ProdutoRECORD product = produtosBean.encontrarProduto(id);
        if (product == null) {
            produtosBean.setErrors(true);
            produtosBean.setStatus("Product with id '" + pid + "' not found");
        } else {
            produtosBean.setProduct(product);
            if (req.getMethod().equalsIgnoreCase("POST")) {
                String pname = request.getParameter("p_name");
                String pprice = request.getParameter("p_price");
                String pdate = request.getParameter("p_date");
                try {
                    product.setName(pname);
                    product.setPrice(new BigDecimal(pprice));
                    if (pdate.length() != 0) {
                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        product.setBestBefore(LocalDate.parse(pdate, fmt));
                    } else {
                        product.setBestBefore(null);
                    }
                    produtosBean.atualizar(product);
                    produtosBean.setStatus("Product Updated sucessfully");
                } catch (NumberFormatException ex) {
                    produtosBean.setErrors(true);
                    produtosBean.setStatus("Price '" + pprice + "' is not a valid number");
                } catch (DateTimeParseException ex) {
                    produtosBean.setErrors(true);
                    produtosBean.setStatus("Best before date '" + pdate + "' format should be: yyyy-MM-dd");
                } catch (Exception ex) {
                    Throwable cause = ex.getCause();
                    produtosBean.setErrors(true);
                    if (cause instanceof ConstraintViolationException) {
                        StringBuilder status = new StringBuilder("Error updating product: ");
                        ConstraintViolationException e = (ConstraintViolationException) cause;
                        e.getConstraintViolations().stream().forEach(v -> status.append(v.getMessage() + " "));
                        produtosBean.setStatus(status.toString());
                    } else if (cause instanceof OptimisticLockException) {
                        produtosBean.setStatus("Product has been changed by another user.");
                    } else {
                        throw ex;
                    }
                }
            }
        }
        chain.doFilter(request, response); // continuar para ProdutoEditar.jsp
    }

    @Override
    public void destroy() {

    }

}
