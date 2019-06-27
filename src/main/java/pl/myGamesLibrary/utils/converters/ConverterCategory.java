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
}
