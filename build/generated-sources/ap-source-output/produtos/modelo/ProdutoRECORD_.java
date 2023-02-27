package produtos.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-27T13:12:03")
@StaticMetamodel(ProdutoRECORD.class)
public class ProdutoRECORD_ { 

    public static volatile SingularAttribute<ProdutoRECORD, LocalDate> bestBefore;
    public static volatile SingularAttribute<ProdutoRECORD, BigDecimal> price;
    public static volatile SingularAttribute<ProdutoRECORD, String> name;
    public static volatile SingularAttribute<ProdutoRECORD, Integer> id;
    public static volatile SingularAttribute<ProdutoRECORD, Integer> version;

}