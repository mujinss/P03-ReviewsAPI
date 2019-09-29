package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        // assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setProduct_name("a new product");
        product.setProduct_desc("test test test");

        entityManager.persist(product);
        Optional<Product> prod = productRepository.findById(1);
        assertThat(prod).isNotNull();
        assertEquals(product.getProduct_name(), prod.get().getProduct_name());
        assertEquals(product.getProduct_desc(), prod.get().getProduct_desc());
    }

    @Test
    public void testListAllProducts() {
        Product prod1 = new Product();
        Product prod2 = new Product();

        prod1.setProduct_name("coffee maker");
        prod2.setProduct_name("mattress");

        entityManager.persist(prod1);
        entityManager.persist(prod2);

        List<Product> prodList = productRepository.findAll();
        assertEquals(prodList.size(), 2);
    }

}
