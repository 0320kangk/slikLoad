package SilkLoad.repository;

import SilkLoad.dto.OrderHistoryDto;
import SilkLoad.dto.ProductCategoryDto;
import SilkLoad.dto.ProductRecordDto;
import SilkLoad.entity.Product;
import SilkLoad.entity.ProductEnum.ProductType;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    //ProductType이 sale인것을 가져오는 쿼리(pagenation 가능)
    //select * from Product limit ? ;
    Page<Product> findByProductTypeOrderByIdDesc(ProductType type, Pageable pageable);

    //ProductType이 sale인것을 가져오는 쿼리
    //select * from product where ProductType == sale;
    List<Product> findAllByProductType(ProductType type);

    //Product 안에 원하는 category(second) 빼오는 쿼리
    @Query(value = "SELECT " +
            "new SilkLoad.dto.ProductCategoryDto(p.id, p.name, p.auctionPrice, p.instantPrice, p.explanation, " +
            "p.createdDate, p.productTime ,p.productType, p.category.first, p.category.second, img.uploadFileName, img.storeFileName) " +
            "from Product p " +
            "left join ProductImage img " +
            "on p.id = img.product.id " +
            "where p.category.second = :second "
            )
    Page<ProductCategoryDto> findsecondcategory(@Param("second")String categoryName, Pageable pageable);

    //Product 안에 원하는 category(second) 빼오는 쿼리
    @Query(value = "SELECT " +
            "new SilkLoad.dto.ProductCategoryDto(p.id, p.name, p.auctionPrice, p.instantPrice, p.explanation, " +
            "p.createdDate, p.productTime ,p.productType, p.category.first, p.category.second, img.uploadFileName, img.storeFileName) " +
            "from Product p " +
            "left join ProductImage img " +
            "on p.id = img.product.id " +
            "where p.category.first = :first "
    )
    Page<ProductCategoryDto> findfirstcategory(@Param("first")String categoryName, Pageable pageable);

    //검색해서 Product를 반환하는 쿼리 %aaaaaaaa%
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}

