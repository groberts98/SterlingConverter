import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SterlingConverterTest {
    public SterlingConverter sterlingConverter = new SterlingConverter();
    
    @Test
    public void emptyInputTest() {
        String input = "";
        assertEquals(false, sterlingConverter.checkValidInput(input));
    }
 
    @Test
    public void invalidSymbolTest() {
        String input = "\u00A31x.34";
        assertEquals(false, sterlingConverter.checkValidInput(input));
    }
 
    @Test
    public void missingDigitsTest() {
        String input = "\u00A3p";
        assertEquals(false, sterlingConverter.checkValidInput(input));
    }
 
    @Test
    public void duplicateSymbolTest() {
        String input = "\u00A313..45p";
        assertEquals(false, sterlingConverter.checkValidInput(input));
    }
 
    @Test
    public void validInputTest() {
        String input = "\u00A313.45p";
        assertEquals(true, sterlingConverter.checkValidInput(input));
    }
 
     @Test
     public void penniesTest() {
         String input = "75";
         assertEquals("1 x 50p, 1 x 20p, 1 x 5p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void penniesWIthSymbolTest() {
         String input = "7p";
         assertEquals("1 x 5p, 1 x 2p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void poundsDecimalTest() {
         String input = "1.35";
         assertEquals("1 x \u00A31, 1 x 20p, 1 x 10p, 1 x 5p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void poundsDecimalWithSymbolTest() {
         String input = "\u00A31.3";
         assertEquals("1 x \u00A31, 1 x 20p, 1 x 10p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void justPoundsTest() {
         String input = "\u00A333";
         assertEquals("16 x \u00A32, 1 x \u00A31", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void bothSymbolTest() {
         String input = "\u00A33.55p";
         assertEquals("1 x \u00A32, 1 x \u00A31, 1 x 50p, 1 x 5p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void missingPenceTest() {
         String input = "\u00A32.p";
         assertEquals("1 x \u00A32", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void missingPoundsTest() {
         String input = "\u00A3.34p";
         assertEquals("1 x 20p, 1 x 10p, 2 x 2p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void bufferedZerosTest() {
         String input = "\u00A3002.65p";
         assertEquals("1 x \u00A32, 1 x 50p, 1 x 10p, 1 x 5p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     } 
     
     @Test
     public void roundingPenceTest() {
         String input = "4.326";
         assertEquals("2 x \u00A32, 1 x 20p, 1 x 10p, 1 x 2p, 1 x 1p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
         input = "4.324";
         assertEquals("2 x \u00A32, 1 x 20p, 1 x 10p, 1 x 2p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
}
