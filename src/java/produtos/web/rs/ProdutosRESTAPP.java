package produtos.web.rs;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("ProdutosRESTAPP")
public class ProdutosRESTAPP extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(ProdutoDescontadorREST.class);
        return classes;
    }

}
