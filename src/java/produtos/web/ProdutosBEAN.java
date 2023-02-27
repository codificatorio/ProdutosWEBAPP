package produtos.web;

import produtos.modelo.ProdutoRECORD;
import produtos.modelo.ProdutosEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped // BEAN será instanciado por SERVLET e utilizado por JSP
@Named("ProdutosBEAN") // encontrável com este nome nos atributos do REQUEST
public class ProdutosBEAN {

    @EJB
    private ProdutosEJB produtosEJB;
    private List<ProdutoRECORD> produtos;
    private ProdutoRECORD produto;
    private String status;
    private boolean errors;

    public ProdutosBEAN() {
    }

    public void atualizar(ProdutoRECORD product) {
        produtosEJB.atualizar(product);
    }

    public ProdutoRECORD encontrarProduto(Integer id) {
        return produtosEJB.encontrarProduto(id);
    }

    public List<ProdutoRECORD> encontrarProduto(String name) {
        return produtosEJB.encontrarProduto(name);
    }

    // utilizado por JSP
    public List<ProdutoRECORD> getProducts() {
        return produtos;
    }

    public void setProducts(List<ProdutoRECORD> products) {
        this.produtos = products;
    }

    // utilizado por JSP
    public ProdutoRECORD getProduct() {
        return produto;
    }

    public void setProduct(ProdutoRECORD product) {
        this.produto = product;
    }

    // utilizado por JSP
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // utilizado por JSP
    public boolean isErrors() {
        return errors;
    }

    public void setErrors(boolean errors) {
        this.errors = errors;
    }

}
