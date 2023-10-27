package com.example.foodapp;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service.UserService;
import com.example.foodapp.auth.service.admin.service.BusinessOwnerAdminService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.TimeOpenedDay;
import com.example.foodapp.business.model.TimeOpenedWeek;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.repo.BusinessWorkingTimeRepo;
import com.example.foodapp.business.repo.TimeOpenedDayRepo;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import com.example.foodapp.product.enumeration.Availability;
import com.example.foodapp.product.model.*;
import com.example.foodapp.product.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories("com.example.foodapp")
//@EntityScan("com.example.foodapp.auth.user.UserProfiles")
public class FoodApp {

	public static void main(String[] args) {
		SpringApplication.run(FoodApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			BusinessOwnerAdminService adminService,
			AdminBusinessService adminBusinessService,
			UserRepository userRepository,
			BusinessRepo businessRepo,
			ProductCategoryRepo productCategoryRepo,
			ProductRepo productRepo,
			ProductImageRepo productImageRepo,
			ProductVariationRepo productVariationRepo,
			VariationRepo variationRepo,
			ProductTagRepo tagRepo,
			AppendicesCategoryRepo appendicesCategoryRepo,
			AppendicesRepo appendicesRepo,
			ImageRepo imageRepo
	) {
		return args -> {
			Business business = adminBusinessService.get(252L);

			ProductCategory category = new ProductCategory();
			category.setNameOfCategory("Name of category");
			category.setDescOfCategory("Description of category");
			category.setCategoryVisible(true);
			category.setBusiness(business);
			productCategoryRepo.save(category);

			business.getProductCategories().add(category);
			businessRepo.save(business);

			Product product = new Product();
			product.setNameOfProduct("Name of product");
			product.setCodeOfProduct("Code 123");
			product.setPriceOfProduct(321);
			product.setDiscountPrice(311);
			product.setIsOnDiscount(true);
			product.setAboutProduct("About product");
			product.setPreparationTime(21);
			product.setAvailability(Availability.AVAILABLE);
			product.setWeight(300);
			product.setProductVisible(true);
			product.setProductCategory(category);

			if (category.getProductList() == null){
				List<Product> categoryList = new ArrayList<>();
				categoryList.add(product);
				category.setProductList(categoryList);
			} else {
				category.getProductList().add(product);
			}
			productCategoryRepo.save(category);

			ProductVariation productVariation = new ProductVariation();
			productVariation.setName("Product variation");
			productVariation.setValue("Value");
			productVariation.setDoesAffectPrice(true);
			productVariation.setPriceOfVariation(123);
			productVariation.setPriceOfVariationDiscount(121);
			productVariation.setIsOnDiscount(true);
			productVariation.setCodeOfVariation("Code 123");

			productVariationRepo.save(productVariation);

			ProductImage productImage = new ProductImage();
			productImage.setNameOfImage("name of image");
			productImageRepo.save(productImage);

			Variation variation = new Variation();
			variation.setName("Variation");
			variation.setProductVariationList(new ArrayList<>());
			variation.getProductVariationList().add(productVariation);
			variationRepo.save(variation);

			AppendicesCategory appendicesCategory = new AppendicesCategory();
			appendicesCategory.setNameOfCategory("Name of appendices category");
			appendicesCategory.setIsRequired(true);
			appendicesCategory.setNumberOfAllowed(2);

			Image img = new Image();
			img.setImageUrl("123");
			imageRepo.save(img);
			appendicesCategory.setImage(img);
			appendicesCategoryRepo.save(appendicesCategory);
			product.addToAppendicesCategoryList(appendicesCategory);

			Appendices appendices = new Appendices();
			appendices.setNameOfAppendices("Name of appendices");
			appendices.setDoesAffectPrice(true);
			appendices.setPrice(321);

			appendices.setAppendicesCategory(appendicesCategory);

			appendicesRepo.save(appendices);
			productRepo.save(product);

			ProductTag tag = new ProductTag();
			tag.setName("BBQ");
			tag.setProduct(product);
			tagRepo.save(tag);
			ProductTag productTag = new ProductTag();
			productTag.setName("ITALIAN");
			productTag.setProduct(product);
			tagRepo.save(productTag);
			appendicesCategory.setProduct(product);
			appendicesCategoryRepo.save(appendicesCategory);

		};
	}

}

//			var restaurant = new Business();
//			restaurant.setName("Restaurant 1");
//			restaurant.setDescription("Description");
//			restaurant.setActive(true);
//
//			var timeOpened = new TimeOpenedWeek();
//
//			var timeOpenedMonday = new TimeOpenedDay();
//			timeOpenedMonday.setTimeOpen("08:00");
//			timeOpenedMonday.setTimeClose("22:00");
//
//			timeOpened.setTimeOpenedDayMonday(timeOpenedMonday);
//
//			var timeOpenedTuesday = new TimeOpenedDay();
//			timeOpenedTuesday.setTimeOpen("08:00");
//			timeOpenedTuesday.setTimeClose("22:00");
//
//			timeOpened.setTimeOpenedDayTuesday(timeOpenedTuesday);
//
//
//			restaurant.setTimeOpened(timeOpened);
//			timeOpenedDayRepo.save(timeOpenedMonday);
//
//			timeOpenedDayRepo.save(timeOpenedTuesday);
//
//			timeOpenedWeekRepo.save(timeOpened);
//
//			adminBusinessService.create(restaurant);