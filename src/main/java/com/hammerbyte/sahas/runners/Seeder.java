package com.hammerbyte.sahas.runners;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hammerbyte.sahas.models.ModelAuthority;
import com.hammerbyte.sahas.models.ModelCategory;
import com.hammerbyte.sahas.models.ModelProduct;
import com.hammerbyte.sahas.models.ModelPurchase;
import com.hammerbyte.sahas.models.ModelTestiMony;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryCategory;
import com.hammerbyte.sahas.repositories.RepositoryProduct;
import com.hammerbyte.sahas.repositories.RepositoryPurchase;
import com.hammerbyte.sahas.repositories.RepositoryTestiMony;
import com.hammerbyte.sahas.repositories.RepositoryUser;
import com.hammerbyte.sahas.services.ServiceUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@AllArgsConstructor
@Getter
@Setter
@Profile("dev")
public class Seeder implements CommandLineRunner {

    private RepositoryCategory repositoryCategory;
    private RepositoryProduct repositoryProduct;
    private RepositoryUser repositoryUser;


    private RepositoryTestiMony repositoryTestiMony;
    private RepositoryPurchase repositoryPurchase;

    @Override
    public void run(String... args) throws Exception {

        // seed Categories
        seedCategories();
        // seed Products
        seedProducts();
        // seed users()
        seedUsers();
        //seed purchases
        seedPurchases();
        //seed testimonies
        seedTestiMonies();

    }

    private void seedCategories() {

        ModelCategory category1 = new ModelCategory();
        category1.setCategoryName("Category1");
        category1.setCategoryPhoto("Category1.png");

        ModelCategory category2 = new ModelCategory();
        category2.setCategoryName("Category2");
        category2.setCategoryPhoto("Category2.jpg");

        ModelCategory category3 = new ModelCategory();
        category3.setCategoryName("Category3");
        category3.setCategoryPhoto("Category3.jpg");

        Set<ModelCategory> categories = new HashSet<ModelCategory>(Arrays.asList(category1, category2, category3));

        repositoryCategory.saveAll(categories);

    }

    private void seedProducts() {

        ModelProduct product1 = new ModelProduct();
        product1.setProductName("Product1");
        product1.setProductImage("Product1.jpg");
        product1.setProductDescription("Product 1 Description that shows description 1");
        product1.setProductPrice(699.99);
        product1.setProductCategory(repositoryCategory.findById(1L).get());

        ModelProduct product2 = new ModelProduct();
        product2.setProductName("Product2");
        product2.setProductImage("Product2.jpg");
        product2.setProductDescription("A powerful smartphone with a sleek design.");
        product2.setProductPrice(54.88);
        product2.setProductCategory(repositoryCategory.findById(1L).get());

        ModelProduct product3 = new ModelProduct();
        product3.setProductName("Product3");
        product3.setProductImage("Product3.jpg");
        product3.setProductDescription("A sample description for product 3");
        product3.setProductPrice(144.55);
        product3.setProductCategory(repositoryCategory.findById(1L).get());

        ModelProduct product4 = new ModelProduct();
        product4.setProductName("Product4");
        product4.setProductImage("Product4.jpg");
        product4.setProductDescription("That's the 4th product");
        product4.setProductPrice(132.55);
        product4.setProductCategory(repositoryCategory.findById(2L).get());

        Set<ModelProduct> products = new HashSet<ModelProduct>(Arrays.asList(product1, product2, product3, product4));

        repositoryProduct.saveAll(products);

    }

    private void seedUsers() {

        ModelAuthority authority1 = new ModelAuthority();
        authority1.setAuthority("manage_sec1"); //

        ModelAuthority authority2 = new ModelAuthority();
        authority2.setAuthority("manage_sec2"); //

        ModelAuthority authority3 = new ModelAuthority();
        authority3.setAuthority("manage_sec3"); //


        Set<ModelAuthority> authorities = new HashSet<>();
        authorities.add(authority1);
        

        ModelUser user1 = new ModelUser();
        user1.setUserName("user1");
        user1.setUserEmail("user1@gmail.com");
        user1.setUserPassword("1234");
        user1.setUserAuthorities(new HashSet<>(Arrays.asList(authority1,authority3,authority2)));

        ModelUser user2 = new ModelUser();
        user2.setUserName("user2");
        user2.setUserEmail("user2@gmail.com");
        user2.setUserPassword("1234");
        user2.setUserAuthorities(new HashSet<>(Arrays.asList(authority1)));


        ModelUser user3 = new ModelUser();
        user3.setUserName("user3");
        user3.setUserEmail("user3@gmail.com");
        user3.setUserPassword("1234");
        user2.setUserAuthorities(new HashSet<>(Arrays.asList(authority2)));


        Set<ModelUser> users = new HashSet<>(Arrays.asList(user1, user2, user3));

        repositoryUser.saveAll(users);
    }

    private void seedPurchases(){

        ModelUser user1 = repositoryUser.findByUserEmail("user1@gmail.com").get();
        ModelUser user2 = repositoryUser.findByUserEmail("user2@gmail.com").get();

        ModelProduct product1 = repositoryProduct.findById(1L).get();
        ModelProduct product2 = repositoryProduct.findById(2L).get();


        System.out.println(product1.getProductId());
        System.out.println(product2.getProductId());



         ModelPurchase purchase1 = new ModelPurchase(); 
         purchase1.setPurchaseProduct(product1);
         purchase1.setPurchaseUser(user1);


         ModelPurchase purchase2 = new ModelPurchase(); 
         purchase2.setPurchaseProduct(product1);
         purchase2.setPurchaseUser(user2);


        
        repositoryPurchase.saveAll(new HashSet<ModelPurchase>(Arrays.asList(purchase1,purchase2)));

    }

    private void seedTestiMonies(){
        ModelPurchase purchase1 = repositoryPurchase.findById(1L).get();
        ModelPurchase purchase2 = repositoryPurchase.findById(2L).get();


        ModelTestiMony t1= new ModelTestiMony();
        t1.setTestiMony("This is sample testimony for Purchase 1");

        ModelTestiMony t2= new ModelTestiMony();
        t2.setTestiMony("This is sample testimony 2 for Purchase");

        purchase1.setUserTestiMony(t1);
        purchase2.setUserTestiMony(t2);

        repositoryPurchase.saveAll(new HashSet<>(Arrays.asList(purchase1,purchase2)));



    }

}
