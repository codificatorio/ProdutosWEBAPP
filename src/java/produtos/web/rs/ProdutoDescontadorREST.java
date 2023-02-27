package produtos.web.rs;

import produtos.modelo.ProdutoRECORD;
import produtos.web.ProdutosBEAN;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// Belangrijk: deze class zal pas als RS class herkend worden indien er  ook een Application class bestaat die deze class oplijst in getClasses()
@RequestScoped
@Path("descontar")
@Produces({MediaType.APPLICATION_JSON})
//@Produces({MediaType.TEXT_PLAIN})
public class ProdutoDescontadorREST {

    @Inject
    private ProdutosBEAN produtosBean;

    @GET
    @Path("{id}")
    public ProdutoDesconto descontar(@PathParam("id") Integer id) {
        ProdutoDesconto desconto = new ProdutoDesconto();
        ProdutoRECORD produto = produtosBean.encontrarProduto(id);
        if (produto == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
        BigDecimal price = produto.getPrice();
        LocalDate date = produto.getBestBefore();
        BigDecimal value = price.multiply(BigDecimal.valueOf(0.1));
        if (price.subtract(value).compareTo(BigDecimal.ONE) > 0 && (date != null && date.compareTo(LocalDate.now()) > 0)) {
            desconto.setValue(value);
            desconto.setDate(date.minusDays(1));
        } else {
            desconto.setValue(BigDecimal.ZERO);
        }
        return desconto;
    }
}
