package pl.myGamesLibrary.utils.converters;

import pl.myGamesLibrary.database.models.Category;
import pl.myGamesLibrary.modelFx.CategoryFx;

public class ConverterCategory {
    public static CategoryFx covertToCategoryFx(Category category){
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;
    }

    public static Category covertToCategory(CategoryFx categoryFx){
        Category category = new Category();
        category.setId(categoryFx.getId());
        category.setName(categoryFx.getName());
        return category;
    }
}
