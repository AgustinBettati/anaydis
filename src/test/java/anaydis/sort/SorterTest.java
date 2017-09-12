package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class SorterTest extends AbstractSorterTest {

    @Override
    protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @Override
    protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Override
    protected SorterProvider getSorterProvider() {
        return new SorterProviderImpl();
    }


    /*
    Asigno el parametro que va a variar para cada "instancia" del test
     */
    @Parameterized.Parameter
    public Sorter sorter;

    @Test
    public void testSorter() {
        testSorter(createIntegerDataSetGenerator(), sorter.getType(), 100);
        testSorter(createIntegerDataSetGenerator(), sorter.getType(), 1000);
        testSorter(createStringDataSetGenerator(), sorter.getType(), 10);
        testSorter(createStringDataSetGenerator(), sorter.getType(), 100);
    }

    /*
    Defino la List<Object[]> que va a definir los diferentes valores que toma el parametro.
    Cada parametro va a tener como nombre "sorter", y el valor que se encuentre en el indice 0 del array de Objects
     */
    @Parameterized.Parameters(name = "sorter {0}")
    public static List<Object[]> sorters() {
        final SorterProvider provider = new SorterProviderImpl();
        final List<Object[]> sorters = new ArrayList<>();
        provider.getAllSorters().forEach(sorter -> sorters.add(new Object[]{sorter}));
        return sorters;
    }
}
