package produtos.modelo;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@Stateless
@LocalBean
public class ProdutosEJB {

    @PersistenceContext(unitName = "ProdutosTABELA")
    private EntityManager armazenador;

    public void atualizar(@Valid ProdutoRECORD product) {
        armazenador.merge(product);
    }

    public ProdutoRECORD encontrarProduto(Integer id) {
        return armazenador.find(ProdutoRECORD.class, id);
    }

    public List<ProdutoRECORD> encontrarProduto(String name) {
        Query productNameQuery = armazenador.createNamedQuery("procuraPorNome");
        productNameQuery.setParameter("name", name);
        return productNameQuery.getResultList();
    }

}
