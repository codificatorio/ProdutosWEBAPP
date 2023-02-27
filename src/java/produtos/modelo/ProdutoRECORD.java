package produtos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "PRODUCTS")
@NamedQueries({
    @NamedQuery(name = "procuraPorNome", query = "SELECT p FROM ProdutoRECORD p WHERE p.name like :name")
})
public class ProdutoRECORD implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "pidGen", sequenceName = "PID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "pidGen")
    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 3, max = 40, message = "{prod.name}")
    private String name;

    @Max(value = 1000, message = "{prod.price.max}") @Min(value = 1, message = "{prod.price.min}")
    private BigDecimal price;

    @Column(name = "BEST_BEFORE")
    @Temporal(TemporalType.DATE)
    private LocalDate bestBefore;

    @Version
    private Integer version;

    public ProdutoRECORD() {
    }

    public ProdutoRECORD(Integer id) {
        this.id = id;
    }

    // utilizado por JSP
    public Integer getId() {
        return id;
    }

    // utilizado por JSP
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoRECORD)) {
            return false;
        }
        ProdutoRECORD other = (ProdutoRECORD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + price + " " + bestBefore + " " + version;
    }

}
