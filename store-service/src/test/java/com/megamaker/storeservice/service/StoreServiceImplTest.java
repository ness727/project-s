package com.megamaker.storeservice.service;

import com.megamaker.storeservice.entity.Category;
import com.megamaker.storeservice.entity.Store;
import com.megamaker.storeservice.repository.CategoryRepository;
import com.megamaker.storeservice.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
class StoreServiceImplTest {
    private final StoreService storeService;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    private final Category category = Category.builder().name("중식").build();
    private final Store store = Store.builder()
            .category(category)
            .name("매장1")
            .regionCode("1234512345")
            .build();

    @Autowired
    public StoreServiceImplTest(StoreRepository storeRepository, CategoryRepository categoryRepository) {
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
        this.storeService = new StoreServiceImpl(storeRepository, categoryRepository);
    }

    @Test
    public void 매장_저장에_성공한다() {
        // given
//        Category category = Category.builder().name("중식").build();
//        Store store = Store.builder()
//                .category(category)
//                .name("매장1")
//                .build();

        // when
        //Store savedStore = storeRepository.save(store);

        // then
        //assertThat(store.getName()).isEqualTo(savedStore.getName());
        //assertThat(category.getName()).isEqualTo(savedStore.getCategory().getName());
    }

    @Test
    public void 지역과_카테고리_지정해서_매장_조회에_성공한다() {
        
    }
}