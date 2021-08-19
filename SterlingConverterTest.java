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
        String input = "£1x.34";
        assertEquals(false, sterlingConverter.checkValidInput(input));
    }
 
    @Test
    public void missingDigitsTest() {
        String input = "£p";
        assertEquals(false, sterlingConverter.checkValidInput(input));
    }
 
    @Test
    public void duplicateSymbolTest() {
        String input = "£13..45p";
        assertEquals(false, sterlingConverter.checkValidInput(input));
    }
 
    @Test
    public void validInputTest() {
        String input = "£13.45p";
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
         assertEquals("1 x £1, 1 x 20p, 1 x 10p, 1 x 5p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void poundsDecimalWithSymbolTest() {
         String input = "£1.3";
         assertEquals("1 x £1, 1 x 20p, 1 x 10p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void justPoundsTest() {
         String input = "£33";
         assertEquals("16 x £2, 1 x £1", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void bothSymbolTest() {
         String input = "£3.55p";
         assertEquals("1 x £2, 1 x £1, 1 x 50p, 1 x 5p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void missingPenceTest() {
         String input = "£2.p";
         assertEquals("1 x £2", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void missingPoundsTest() {
         String input = "£.34p";
         assertEquals("1 x 20p, 1 x 10p, 2 x 2p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
 
     @Test
     public void bufferedZerosTest() {
         String input = "£002.65p";
         assertEquals("1 x £2, 1 x 50p, 1 x 10p, 1 x 5p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     } 
     
     @Test
     public void roundingPenceTest() {
         String input = "4.326";
         assertEquals("2 x £2, 1 x 20p, 1 x 10p, 1 x 2p, 1 x 1p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
         input = "4.324";
         assertEquals("2 x £2, 1 x 20p, 1 x 10p, 1 x 2p", sterlingConverter.convert(sterlingConverter.formatInput(input)));
     }
}
