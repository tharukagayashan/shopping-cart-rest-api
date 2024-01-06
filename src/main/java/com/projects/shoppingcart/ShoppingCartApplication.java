package com.projects.shoppingcart;

import com.projects.shoppingcart.dao.reference.*;
import com.projects.shoppingcart.model.reference.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class ShoppingCartApplication {

    private static ScRRoleRepository roleRepository = null;
    private static ScRStatusRepository statusRepository = null;
    private static ScRProductTypeRepository productTypeRepository = null;
    private static ScRProductCategoryRepository productCategoryRepository = null;
    private static ScRProductSubCategoryRepository productSubCategoryRepository = null;
    private static ScRProductVariableRepository productVariableRepository = null;
    private static ScRProductBrandRepository productBrandRepository = null;

    public ShoppingCartApplication(ScRRoleRepository roleRepository, ScRStatusRepository statusRepository, ScRProductTypeRepository productTypeRepository, ScRProductCategoryRepository productCategoryRepository, ScRProductSubCategoryRepository productSubCategoryRepository, ScRProductVariableRepository productVariableRepository, ScRProductBrandRepository productBrandRepository) {
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
        this.productTypeRepository = productTypeRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productSubCategoryRepository = productSubCategoryRepository;
        this.productVariableRepository = productVariableRepository;
        this.productBrandRepository = productBrandRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
        insertRecordsFromCommandLine();
    }

    static void insertRecordsFromCommandLine() {
        Optional<ScRRole> optRole1 = roleRepository.findById(new Long(1));
        if (!optRole1.isPresent()) {
            ScRRole role1 = new ScRRole();
            role1.setRoleName("Admin");
            role1.setDescription("Admin");
            role1.setIsActive(true);
            roleRepository.save(role1);
            log.info("Admin created");
        } else {
            log.info("Admin already exists");
        }

        Optional<ScRRole> optRole2 = roleRepository.findById(new Long(2));
        if (!optRole2.isPresent()) {
            ScRRole role2 = new ScRRole();
            role2.setRoleName("Customer");
            role2.setDescription("Customer");
            role2.setIsActive(true);
            roleRepository.save(role2);
            log.info("Customer created");
        } else {
            log.info("Customer already exists");
        }

        Optional<ScRStatus> optStatus1 = statusRepository.findById(new Long(1));
        if (!optStatus1.isPresent()) {
            ScRStatus status1 = new ScRStatus();
            status1.setName("New");
            status1.setCode("N");
            status1.setType("Order");
            statusRepository.save(status1);
            log.info("Status New created");
        } else {
            log.info("New already exists");
        }

        Optional<ScRStatus> optStatus2 = statusRepository.findById(new Long(2));
        if (!optStatus2.isPresent()) {
            ScRStatus status2 = new ScRStatus();
            status2.setName("Pending");
            status2.setCode("P");
            status2.setType("Order");
            statusRepository.save(status2);
            log.info("Status Pending created");
        } else {
            log.info("Pending already exists");
        }

        Optional<ScRStatus> optStatus3 = statusRepository.findById(new Long(3));
        if (!optStatus3.isPresent()) {
            ScRStatus status3 = new ScRStatus();
            status3.setName("Approved");
            status3.setCode("A");
            status3.setType("Order");
            statusRepository.save(status3);
            log.info("Status Approved created");
        } else {
            log.info("Approved already exists");
        }

        Optional<ScRProductType> optProductType1 = productTypeRepository.findById(new Long(1));
        if (!optProductType1.isPresent()) {
            ScRProductType productType1 = new ScRProductType();
            productType1.setName("Electronics");
            productType1.setCode("E");
            productType1.setDescription("Electronics");
            productTypeRepository.save(productType1);
            log.info("Electronics created");
        } else {
            log.info("Electronics already exists");
        }

        Optional<ScRProductType> optProductType2 = productTypeRepository.findById(new Long(2));
        if (!optProductType2.isPresent()) {
            ScRProductType productType2 = new ScRProductType();
            productType2.setName("Clothing");
            productType2.setCode("C");
            productType2.setDescription("Clothing");
            productTypeRepository.save(productType2);
            log.info("Clothing created");
        } else {
            log.info("Clothing already exists");
        }

        Optional<ScRProductType> optProductType3 = productTypeRepository.findById(new Long(3));
        if (!optProductType3.isPresent()) {
            ScRProductType productType3 = new ScRProductType();
            productType3.setName("Furniture");
            productType3.setCode("F");
            productType3.setDescription("Furniture");
            productTypeRepository.save(productType3);
            log.info("Furniture created");
        } else {
            log.info("Furniture already exists");
        }

        Optional<ScRProductType> optProductType4 = productTypeRepository.findById(new Long(4));
        if (!optProductType4.isPresent()) {
            ScRProductType productType4 = new ScRProductType();
            productType4.setName("Grocery");
            productType4.setCode("G");
            productType4.setDescription("Grocery");
            productTypeRepository.save(productType4);
            log.info("Grocery created");
        } else {
            log.info("Grocery already exists");
        }

        Optional<ScRProductType> optProductType5 = productTypeRepository.findById(new Long(5));
        if (!optProductType5.isPresent()) {
            ScRProductType productType5 = new ScRProductType();
            productType5.setName("Books");
            productType5.setCode("B");
            productType5.setDescription("Books");
            productTypeRepository.save(productType5);
            log.info("Books created");
        } else {
            log.info("Books already exists");
        }

        Optional<ScRProductType> optProductType6 = productTypeRepository.findById(new Long(6));
        if (!optProductType6.isPresent()) {
            ScRProductType productType6 = new ScRProductType();
            productType6.setName("Stationary");
            productType6.setCode("S");
            productType6.setDescription("Stationary");
            productTypeRepository.save(productType6);
            log.info("Stationary created");
        } else {
            log.info("Stationary already exists");
        }

        Optional<ScRProductType> optProductType7 = productTypeRepository.findById(new Long(7));
        if (!optProductType7.isPresent()) {
            ScRProductType productType7 = new ScRProductType();
            productType7.setName("Toys");
            productType7.setCode("T");
            productType7.setDescription("Toys");
            productTypeRepository.save(productType7);
            log.info("Toys created");
        } else {
            log.info("Toys already exists");
        }

        ScRProductType productType1 = optProductType1.get();
        Optional<ScRProductCategory> optProductCategory1 = productCategoryRepository.findById(new Long(1));
        if (!optProductCategory1.isPresent()) {
            ScRProductCategory productCategory1 = new ScRProductCategory();
            productCategory1.setName("Mobile");
            productCategory1.setCode("M");
            productCategory1.setDescription("Mobile");
            productCategory1.setScRProductType(productType1);
            productCategoryRepository.save(productCategory1);
            log.info("Mobile created");
        } else {
            log.info("Mobile already exists");
        }

        Optional<ScRProductCategory> optProductCategory2 = productCategoryRepository.findById(new Long(2));
        if (!optProductCategory2.isPresent()) {
            ScRProductCategory productCategory2 = new ScRProductCategory();
            productCategory2.setName("Laptop");
            productCategory2.setCode("L");
            productCategory2.setDescription("Laptop");
            productCategory2.setScRProductType(optProductType1.get());
            productCategoryRepository.save(productCategory2);
            log.info("Laptop created");
        } else {
            log.info("Laptop already exists");
        }

        Optional<ScRProductCategory> optProductCategory3 = productCategoryRepository.findById(new Long(3));
        if (!optProductCategory3.isPresent()) {
            ScRProductCategory productCategory3 = new ScRProductCategory();
            productCategory3.setName("Shirt");
            productCategory3.setCode("S");
            productCategory3.setDescription("Shirt");
            productCategory3.setScRProductType(optProductType2.get());
            productCategoryRepository.save(productCategory3);
            log.info("Shirt created");
        } else {
            log.info("Shirt already exists");
        }

        Optional<ScRProductCategory> optProductCategory4 = productCategoryRepository.findById(new Long(4));
        if (!optProductCategory4.isPresent()) {
            ScRProductCategory productCategory4 = new ScRProductCategory();
            productCategory4.setName("T-Shirt");
            productCategory4.setCode("T");
            productCategory4.setDescription("T-Shirt");
            productCategory4.setScRProductType(optProductType2.get());
            productCategoryRepository.save(productCategory4);
            log.info("T-Shirt created");
        } else {
            log.info("T-Shirt already exists");
        }

        Optional<ScRProductCategory> optProductCategory5 = productCategoryRepository.findById(new Long(5));
        if (!optProductCategory5.isPresent()) {
            ScRProductCategory productCategory5 = new ScRProductCategory();
            productCategory5.setName("Pant");
            productCategory5.setCode("P");
            productCategory5.setDescription("Pant");
            productCategory5.setScRProductType(optProductType2.get());
            productCategoryRepository.save(productCategory5);
            log.info("Pant created");
        } else {
            log.info("Pant already exists");
        }

        Optional<ScRProductCategory> optProductCategory6 = productCategoryRepository.findById(new Long(6));
        if (!optProductCategory6.isPresent()) {
            ScRProductCategory productCategory6 = new ScRProductCategory();
            productCategory6.setName("Sofa");
            productCategory6.setCode("SOF");
            productCategory6.setDescription("Sofa");
            productCategory6.setScRProductType(optProductType3.get());
            productCategoryRepository.save(productCategory6);
            log.info("Sofa created");
        } else {
            log.info("Sofa already exists");
        }

        Optional<ScRProductCategory> optProductCategory7 = productCategoryRepository.findById(new Long(7));
        if (!optProductCategory7.isPresent()) {
            ScRProductCategory productCategory7 = new ScRProductCategory();
            productCategory7.setName("Chair");
            productCategory7.setCode("C");
            productCategory7.setDescription("Chair");
            productCategory7.setScRProductType(optProductType3.get());
            productCategoryRepository.save(productCategory7);
            log.info("Chair created");
        } else {
            log.info("Chair already exists");
        }

        Optional<ScRProductCategory> optProductCategory8 = productCategoryRepository.findById(new Long(8));
        if (!optProductCategory8.isPresent()) {
            ScRProductCategory productCategory8 = new ScRProductCategory();
            productCategory8.setName("Table");
            productCategory8.setCode("TA");
            productCategory8.setDescription("Table");
            productCategory8.setScRProductType(optProductType3.get());
            productCategoryRepository.save(productCategory8);
            log.info("Table created");
        } else {
            log.info("Table already exists");
        }

        Optional<ScRProductCategory> optProductCategory9 = productCategoryRepository.findById(new Long(9));
        if (!optProductCategory9.isPresent()) {
            ScRProductCategory productCategory9 = new ScRProductCategory();
            productCategory9.setName("Rice");
            productCategory9.setCode("R");
            productCategory9.setDescription("Rice");
            productCategory9.setScRProductType(optProductType4.get());
            productCategoryRepository.save(productCategory9);
            log.info("Rice created");
        } else {
            log.info("Rice already exists");
        }

        Optional<ScRProductCategory> optProductCategory10 = productCategoryRepository.findById(new Long(10));
        if (!optProductCategory10.isPresent()) {
            ScRProductCategory productCategory10 = new ScRProductCategory();
            productCategory10.setName("Wheat");
            productCategory10.setCode("W");
            productCategory10.setDescription("Wheat");
            productCategory10.setScRProductType(optProductType4.get());
            productCategoryRepository.save(productCategory10);
            log.info("Wheat created");
        } else {
            log.info("Wheat already exists");
        }

        Optional<ScRProductCategory> optProductCategory11 = productCategoryRepository.findById(new Long(11));
        if (!optProductCategory11.isPresent()) {
            ScRProductCategory productCategory11 = new ScRProductCategory();
            productCategory11.setName("Dal");
            productCategory11.setCode("D");
            productCategory11.setDescription("Dal");
            productCategory11.setScRProductType(optProductType4.get());
            productCategoryRepository.save(productCategory11);
            log.info("Dal created");
        } else {
            log.info("Dal already exists");
        }

        Optional<ScRProductCategory> optProductCategory12 = productCategoryRepository.findById(new Long(12));
        if (!optProductCategory12.isPresent()) {
            ScRProductCategory productCategory12 = new ScRProductCategory();
            productCategory12.setName("Novel");
            productCategory12.setCode("N");
            productCategory12.setDescription("Novel");
            productCategory12.setScRProductType(optProductType5.get());
            productCategoryRepository.save(productCategory12);
            log.info("Novel created");
        } else {
            log.info("Novel already exists");
        }

        Optional<ScRProductCategory> optProductCategory13 = productCategoryRepository.findById(new Long(13));
        if (!optProductCategory13.isPresent()) {
            ScRProductCategory productCategory13 = new ScRProductCategory();
            productCategory13.setName("Textbook");
            productCategory13.setCode("TE");
            productCategory13.setDescription("Textbook");
            productCategory13.setScRProductType(optProductType5.get());
            productCategoryRepository.save(productCategory13);
            log.info("Textbook created");
        } else {
            log.info("Textbook already exists");
        }

        Optional<ScRProductCategory> optProductCategory15 = productCategoryRepository.findById(new Long(15));
        if (!optProductCategory15.isPresent()) {
            ScRProductCategory productCategory15 = new ScRProductCategory();
            productCategory15.setName("Pencil");
            productCategory15.setCode("PENS");
            productCategory15.setDescription("Pencil");
            productCategory15.setScRProductType(optProductType6.get());
            productCategoryRepository.save(productCategory15);
            log.info("Pencil created");
        } else {
            log.info("Pencil already exists");
        }

        Optional<ScRProductCategory> optProductCategory16 = productCategoryRepository.findById(new Long(16));
        if (!optProductCategory16.isPresent()) {
            ScRProductCategory productCategory16 = new ScRProductCategory();
            productCategory16.setName("Eraser");
            productCategory16.setCode("ERS");
            productCategory16.setDescription("Eraser");
            productCategory16.setScRProductType(optProductType6.get());
            productCategoryRepository.save(productCategory16);
            log.info("Eraser created");
        } else {
            log.info("Eraser already exists");
        }

        Optional<ScRProductCategory> optProductCategory17 = productCategoryRepository.findById(new Long(17));
        if (!optProductCategory17.isPresent()) {
            ScRProductCategory productCategory17 = new ScRProductCategory();
            productCategory17.setName("Ball");
            productCategory17.setCode("BL");
            productCategory17.setDescription("Ball");
            productCategory17.setScRProductType(optProductType7.get());
            productCategoryRepository.save(productCategory17);
            log.info("Ball created");
        } else {
            log.info("Ball already exists");
        }

        Optional<ScRProductCategory> optProductCategory18 = productCategoryRepository.findById(new Long(18));
        if (!optProductCategory18.isPresent()) {
            ScRProductCategory productCategory18 = new ScRProductCategory();
            productCategory18.setName("Doll");
            productCategory18.setCode("DL");
            productCategory18.setDescription("Doll");
            productCategory18.setScRProductType(optProductType7.get());
            productCategoryRepository.save(productCategory18);
            log.info("Doll created");
        } else {
            log.info("Doll already exists");
        }

        Optional<ScRProductCategory> optProductCategory19 = productCategoryRepository.findById(new Long(19));
        if (!optProductCategory19.isPresent()) {
            ScRProductCategory productCategory19 = new ScRProductCategory();
            productCategory19.setName("Car");
            productCategory19.setCode("CR");
            productCategory19.setDescription("Car");
            productCategory19.setScRProductType(optProductType7.get());
            productCategoryRepository.save(productCategory19);
            log.info("Car created");
        } else {
            log.info("Car already exists");
        }

        Optional<ScRProductCategory> optProductCategory20 = productCategoryRepository.findById(new Long(20));
        if (!optProductCategory20.isPresent()) {
            ScRProductCategory productCategory20 = new ScRProductCategory();
            productCategory20.setName("Bike");
            productCategory20.setCode("BK");
            productCategory20.setDescription("Bike");
            productCategory20.setScRProductType(optProductType7.get());
            productCategoryRepository.save(productCategory20);
            log.info("Bike created");
        } else {
            log.info("Bike already exists");
        }

        Optional<ScRProductCategory> optProductCategory21 = productCategoryRepository.findById(new Long(21));
        if (!optProductCategory21.isPresent()) {
            ScRProductCategory productCategory21 = new ScRProductCategory();
            productCategory21.setName("Scooter");
            productCategory21.setCode("SCO");
            productCategory21.setDescription("Scooter");
            productCategory21.setScRProductType(optProductType7.get());
            productCategoryRepository.save(productCategory21);
            log.info("Scooter created");
        } else {
            log.info("Scooter already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory1 = productSubCategoryRepository.findById(new Long(1));
        if (!optProductSubCategory1.isPresent()) {
            ScRProductSubCategory productSubCategory1 = new ScRProductSubCategory();
            productSubCategory1.setName("Samsung");
            productSubCategory1.setCode("SAM");
            productSubCategory1.setDescription("Samsung");
            productSubCategory1.setScRProductCategory(optProductCategory1.get());
            productSubCategoryRepository.save(productSubCategory1);
            log.info("Samsung created");
        } else {
            log.info("Samsung already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory2 = productSubCategoryRepository.findById(new Long(2));
        if (!optProductSubCategory2.isPresent()) {
            ScRProductSubCategory productSubCategory2 = new ScRProductSubCategory();
            productSubCategory2.setName("Apple");
            productSubCategory2.setCode("APL");
            productSubCategory2.setDescription("Apple");
            productSubCategory2.setScRProductCategory(optProductCategory1.get());
            productSubCategoryRepository.save(productSubCategory2);
            log.info("Apple created");
        } else {
            log.info("Apple already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory3 = productSubCategoryRepository.findById(new Long(3));
        if (!optProductSubCategory3.isPresent()) {
            ScRProductSubCategory productSubCategory3 = new ScRProductSubCategory();
            productSubCategory3.setName("Dell");
            productSubCategory3.setCode("DEL");
            productSubCategory3.setDescription("Dell");
            productSubCategory3.setScRProductCategory(optProductCategory2.get());
            productSubCategoryRepository.save(productSubCategory3);
            log.info("Dell created");
        } else {
            log.info("Dell already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory4 = productSubCategoryRepository.findById(new Long(4));
        if (!optProductSubCategory4.isPresent()) {
            ScRProductSubCategory productSubCategory4 = new ScRProductSubCategory();
            productSubCategory4.setName("HP");
            productSubCategory4.setCode("HP");
            productSubCategory4.setDescription("HP");
            productSubCategory4.setScRProductCategory(optProductCategory2.get());
            productSubCategoryRepository.save(productSubCategory4);
            log.info("HP created");
        } else {
            log.info("HP already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory5 = productSubCategoryRepository.findById(new Long(5));
        if (!optProductSubCategory5.isPresent()) {
            ScRProductSubCategory productSubCategory5 = new ScRProductSubCategory();
            productSubCategory5.setName("Levis");
            productSubCategory5.setCode("LVS");
            productSubCategory5.setDescription("Levis");
            productSubCategory5.setScRProductCategory(optProductCategory3.get());
            productSubCategoryRepository.save(productSubCategory5);
            log.info("Levis created");
        } else {
            log.info("Levis already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory6 = productSubCategoryRepository.findById(new Long(6));
        if (!optProductSubCategory6.isPresent()) {
            ScRProductSubCategory productSubCategory6 = new ScRProductSubCategory();
            productSubCategory6.setName("Peter England");
            productSubCategory6.setCode("PEL");
            productSubCategory6.setDescription("Peter England");
            productSubCategory6.setScRProductCategory(optProductCategory3.get());
            productSubCategoryRepository.save(productSubCategory6);
            log.info("Peter England created");
        } else {
            log.info("Peter England already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory7 = productSubCategoryRepository.findById(new Long(7));
        if (!optProductSubCategory7.isPresent()) {
            ScRProductSubCategory productSubCategory7 = new ScRProductSubCategory();
            productSubCategory7.setName("Allen Solly");
            productSubCategory7.setCode("ALS");
            productSubCategory7.setDescription("Allen Solly");
            productSubCategory7.setScRProductCategory(optProductCategory3.get());
            productSubCategoryRepository.save(productSubCategory7);
            log.info("Allen Solly created");
        } else {
            log.info("Allen Solly already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory8 = productSubCategoryRepository.findById(new Long(8));
        if (!optProductSubCategory8.isPresent()) {
            ScRProductSubCategory productSubCategory8 = new ScRProductSubCategory();
            productSubCategory8.setName("Puma");
            productSubCategory8.setCode("PMA");
            productSubCategory8.setDescription("Puma");
            productSubCategory8.setScRProductCategory(optProductCategory4.get());
            productSubCategoryRepository.save(productSubCategory8);
            log.info("Puma created");
        } else {
            log.info("Puma already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory9 = productSubCategoryRepository.findById(new Long(9));
        if (!optProductSubCategory9.isPresent()) {
            ScRProductSubCategory productSubCategory9 = new ScRProductSubCategory();
            productSubCategory9.setName("Nike");
            productSubCategory9.setCode("NK");
            productSubCategory9.setDescription("Nike");
            productSubCategory9.setScRProductCategory(optProductCategory4.get());
            productSubCategoryRepository.save(productSubCategory9);
            log.info("Nike created");
        } else {
            log.info("Nike already exists");
        }

        Optional<ScRProductSubCategory> optProductSubCategory10 = productSubCategoryRepository.findById(new Long(10));
        if (!optProductSubCategory10.isPresent()) {
            ScRProductSubCategory productSubCategory10 = new ScRProductSubCategory();
            productSubCategory10.setName("Adidas");
            productSubCategory10.setCode("ADS");
            productSubCategory10.setDescription("Adidas");
            productSubCategory10.setScRProductCategory(optProductCategory4.get());
            productSubCategoryRepository.save(productSubCategory10);
            log.info("Adidas created");
        } else {
            log.info("Adidas already exists");
        }

        Optional<ScRProductVariable> optVariable1 = productVariableRepository.findById(new Long(1));
        if (!optVariable1.isPresent()) {
            ScRProductVariable variable1 = new ScRProductVariable();
            variable1.setName("Color");
            productVariableRepository.save(variable1);
            log.info("Color created");
        } else {
            log.info("Color already exists");
        }

        Optional<ScRProductVariable> optVariable2 = productVariableRepository.findById(new Long(2));
        if (!optVariable2.isPresent()) {
            ScRProductVariable variable2 = new ScRProductVariable();
            variable2.setName("Size");
            productVariableRepository.save(variable2);
            log.info("Size created");
        } else {
            log.info("Size already exists");
        }

        Optional<ScRProductVariable> optVariable3 = productVariableRepository.findById(new Long(3));
        if (!optVariable3.isPresent()) {
            ScRProductVariable variable3 = new ScRProductVariable();
            variable3.setName("Weight");
            productVariableRepository.save(variable3);
            log.info("Weight created");
        } else {
            log.info("Weight already exists");
        }

        Optional<ScRProductVariable> optVariable4 = productVariableRepository.findById(new Long(4));
        if (!optVariable4.isPresent()) {
            ScRProductVariable variable4 = new ScRProductVariable();
            variable4.setName("Length");
            productVariableRepository.save(variable4);
            log.info("Length created");
        } else {
            log.info("Length already exists");
        }

        Optional<ScRProductVariable> optVariable5 = productVariableRepository.findById(new Long(5));
        if (!optVariable5.isPresent()) {
            ScRProductVariable variable5 = new ScRProductVariable();
            variable5.setName("Width");
            productVariableRepository.save(variable5);
            log.info("Width created");
        } else {
            log.info("Width already exists");
        }

        Optional<ScRProductVariable> optVariable6 = productVariableRepository.findById(new Long(6));
        if (!optVariable6.isPresent()) {
            ScRProductVariable variable6 = new ScRProductVariable();
            variable6.setName("Height");
            productVariableRepository.save(variable6);
            log.info("Height created");
        } else {
            log.info("Height already exists");
        }

        Optional<ScRProductVariable> optVariable7 = productVariableRepository.findById(new Long(7));
        if (!optVariable7.isPresent()) {
            ScRProductVariable variable7 = new ScRProductVariable();
            variable7.setName("RAM");
            productVariableRepository.save(variable7);
            log.info("RAM created");
        } else {
            log.info("RAM already exists");
        }

        Optional<ScRProductVariable> optVariable8 = productVariableRepository.findById(new Long(8));
        if (!optVariable8.isPresent()) {
            ScRProductVariable variable8 = new ScRProductVariable();
            variable8.setName("ROM");
            productVariableRepository.save(variable8);
            log.info("ROM created");
        } else {
            log.info("ROM already exists");
        }

        Optional<ScRProductVariable> optVariable9 = productVariableRepository.findById(new Long(9));
        if (!optVariable9.isPresent()) {
            ScRProductVariable variable9 = new ScRProductVariable();
            variable9.setName("Processor");
            productVariableRepository.save(variable9);
            log.info("Processor created");
        } else {
            log.info("Processor already exists");
        }

        Optional<ScRProductVariable> optVariable10 = productVariableRepository.findById(new Long(10));
        if (!optVariable10.isPresent()) {
            ScRProductVariable variable10 = new ScRProductVariable();
            variable10.setName("Camera");
            productVariableRepository.save(variable10);
            log.info("Camera created");
        } else {
            log.info("Camera already exists");
        }

        Optional<ScRProductVariable> optVariable11 = productVariableRepository.findById(new Long(11));
        if (!optVariable11.isPresent()) {
            ScRProductVariable variable11 = new ScRProductVariable();
            variable11.setName("Battery");
            productVariableRepository.save(variable11);
            log.info("Battery created");
        } else {
            log.info("Battery already exists");
        }

        Optional<ScRProductBrand> optBrand1 = productBrandRepository.findById(new Long(1));
        if (!optBrand1.isPresent()) {
            ScRProductBrand brand1 = new ScRProductBrand();
            brand1.setName("Samsung");
            productBrandRepository.save(brand1);
            log.info("Samsung created");
        } else {
            log.info("Samsung already exists");
        }

        Optional<ScRProductBrand> optBrand2 = productBrandRepository.findById(new Long(2));
        if (!optBrand2.isPresent()) {
            ScRProductBrand brand2 = new ScRProductBrand();
            brand2.setName("Apple");
            productBrandRepository.save(brand2);
            log.info("Apple created");
        } else {
            log.info("Apple already exists");
        }

        Optional<ScRProductBrand> optBrand3 = productBrandRepository.findById(new Long(3));
        if (!optBrand3.isPresent()) {
            ScRProductBrand brand3 = new ScRProductBrand();
            brand3.setName("Dell");
            productBrandRepository.save(brand3);
            log.info("Dell created");
        } else {
            log.info("Dell already exists");
        }

        Optional<ScRProductBrand> optBrand4 = productBrandRepository.findById(new Long(4));
        if (!optBrand4.isPresent()) {
            ScRProductBrand brand4 = new ScRProductBrand();
            brand4.setName("HP");
            productBrandRepository.save(brand4);
            log.info("HP created");
        } else {
            log.info("HP already exists");
        }

        Optional<ScRProductBrand> optBrand5 = productBrandRepository.findById(new Long(5));
        if (!optBrand5.isPresent()) {
            ScRProductBrand brand5 = new ScRProductBrand();
            brand5.setName("Levis");
            productBrandRepository.save(brand5);
            log.info("Levis created");
        } else {
            log.info("Levis already exists");
        }

    }

}