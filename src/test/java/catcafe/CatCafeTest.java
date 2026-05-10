package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class CatCafeTest {
    @Test
    void newCafeShouldHaveNoCats() {
        CatCafe cafe = new CatCafe();
        assertEquals(0, cafe.getCatCount());
    }

    @Test
    void catAddedCatCountRising() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("name", 4);
        cafe.addCat(cat);
        assertEquals(1, cafe.getCatCount());
    }

    @Test
    void multipleCatsAddedCounterRising() {
        CatCafe cafe = new CatCafe();
        List<FelineOverLord> cats =
                List.of(
                        new FelineOverLord("sven", 4),
                        new FelineOverLord("dieter", 5),
                        new FelineOverLord("gerhard", 2),
                        new FelineOverLord("adalbert", 3));

        cats.forEach(cafe::addCat);
        assertEquals(cats.size(), cafe.getCatCount());
    }

    @Test
    void findCatByName() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Sven", 3);
        cafe.addCat(cat);
        FelineOverLord name = cafe.getCatByName("Sven");
        assertEquals("Sven", name.name());
    }

    @Test
    void findCatByNameNotFound() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Sven", 3);
        cafe.addCat(cat);
        FelineOverLord name = cafe.getCatByName("Harald");
        assertNull(name);
    }

    @Test
    void findCatByWeight() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Sven", 3);
        cafe.addCat(cat);
        FelineOverLord weight = cafe.getCatByWeight(2, 5);
        assertEquals("Sven", weight.name());
    }

    @Test
    void findCatByWeightNotFound() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Sven", 3);
        cafe.addCat(cat);
        FelineOverLord weight = cafe.getCatByWeight(4, 6);
        assertNull(weight);
    }

    @Test
    void findCatByWeightMinMaxValueSwapped() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Sven", 3);
        cafe.addCat(cat);
        FelineOverLord weight = cafe.getCatByWeight(5, 2);
        assertNull(weight);
    }

    @Test
    void findCatByWeightMinMaxValueSwappedOutsideOfRange() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Sven", 6);
        cafe.addCat(cat);
        FelineOverLord weight = cafe.getCatByWeight(5, 2);
        assertNull(weight);
    }

    @Test
    void catDuplicateIsSingleCat() {
        CatCafe cafe = new CatCafe();
        FelineOverLord cat1 = new FelineOverLord("Sven", 3);
        FelineOverLord cat2 = new FelineOverLord("Sven", 3);

        cafe.addCat(cat1);
        cafe.addCat(cat2);

        long count = cafe.getCatCount();
        FelineOverLord[] names = {cafe.getCatByName("Sven"), cafe.getCatByName("Sven")};
        assertEquals(1, count);
        assertEquals("Sven", names[0].name());
        assertEquals("Sven", names[1].name());
    }
}
