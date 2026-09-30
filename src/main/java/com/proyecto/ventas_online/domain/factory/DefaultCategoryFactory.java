package com.proyecto.ventas_online.domain.factory;

import com.proyecto.ventas_online.domain.model.Category;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DefaultCategoryFactory {
     public static List<Category> createDefaults(){
         return List.of(
                 new Category("Laptops"),
                 new Category("Peripherals"),
                 new Category("Components"),
                 new Category("Audio")
         );
     }
}
