
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   public static void main(String[] argv) {
      UrlValidatorTest urlValidatorTest = new UrlValidatorTest("url test");
      urlValidatorTest.testManualTest();
   }

   public void testManualTest()
   {
      UrlValidator urlValidator = new UrlValidator();
      assertFalse(urlValidator.isValid("asaodkfsdl"));
//      assertTrue(urlValidator.isValid("http://www.google.com"));
//      assertTrue(urlValidator.isValid("https://en.wikipedia.org/"));
//      assertTrue(urlValidator.isValid("https://facebook.com"));
      assertFalse(urlValidator.isValid("http//ww.badurl,net"));
   }

   public void testIsValidScheme()
   {

   }

   public void testIsValidAuthority()
   {

   }

   public void testIsValidPath()
   {

   }

   public void testIsValidQuery()
   {

   }

   public void testIsValid()
   {
      //You can use this function for programming based testing

   }
}
