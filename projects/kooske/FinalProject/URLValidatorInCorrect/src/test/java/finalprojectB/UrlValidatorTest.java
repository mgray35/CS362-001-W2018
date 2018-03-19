
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
      urlValidatorTest.testIsValidAuthority();
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
      UrlValidator urlValidator = new UrlValidator();
//      assertTrue(urlValidator.isValidAuthority("www.google.com"));
//      assertTrue(urlValidator.isValidAuthority("en.wikipedia.org"));
//      assertTrue(urlValidator.isValidAuthority("g.uk"));

//      assertFalse(urlValidator.isValidAuthority(""));
//      assertFalse(urlValidator.isValidAuthority("www..com"));
//      assertFalse(urlValidator.isValidAuthority("foo.bar"));
//      assertFalse(urlValidator.isValidAuthority("foo."));
//      assertFalse(urlValidator.isValidAuthority(".bar"));

//      assertTrue(urlValidator.isValidAuthority("0.0.0.0"));
//      assertTrue(urlValidator.isValidAuthority("255.255.255.255"));

//      assertFalse(urlValidator.isValidAuthority("256.256.256.256"));
//      assertFalse(urlValidator.isValidAuthority("0.0.0"));
//      assertFalse(urlValidator.isValidAuthority("0.0.0.0.0"));
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
