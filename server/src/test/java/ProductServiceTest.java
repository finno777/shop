import com.shop.server.model.Product;
import com.shop.server.server.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app.xml"})
public class ProductServiceTest {


    @Autowired
    ProductService productService;


    @Test
    public  void test(){
        Product product=new Product(2L,"test2",111L);
        productService.saveProduct(product);
    }
}
