package ecommerce_angular_spring.ecommerce_angular_spring;

import ecommerce_angular_spring.ecommerce_angular_spring.dao.CategoryRepository;
import ecommerce_angular_spring.ecommerce_angular_spring.dao.ProductRepository;
import ecommerce_angular_spring.ecommerce_angular_spring.entities.Category;
import ecommerce_angular_spring.ecommerce_angular_spring.entities.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EcommerceAngularSpringApplication implements CommandLineRunner{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(EcommerceAngularSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Product.class,Category.class);
        categoryRepository.save(new Category(null,"Computers",null,null,null));
        categoryRepository.save(new Category(null,"Printers",null,null,null));
        categoryRepository.save(new Category(null,"Smart phones",null,null,null ));
        Random rnd = new Random();
        categoryRepository.findAll().forEach(c->{
            for(int i=0;i<10;i++){
                Product p =new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100+rnd.nextInt(10000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setCategory(c);
                p.setPhotoName("unknown.png");
                productRepository.save(p);
            }
        });
    }
}
