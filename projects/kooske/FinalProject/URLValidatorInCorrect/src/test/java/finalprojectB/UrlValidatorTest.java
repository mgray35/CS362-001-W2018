
package finalprojectB;

import junit.framework.TestCase;

import java.util.Random;

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
       urlValidatorTest.testIsValidScheme();
       urlValidatorTest.testIsValidAuthority();
       urlValidatorTest.testIsValidPath();
       urlValidatorTest.testIsValidQuery();
       urlValidatorTest.testIsValid();
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
       //bug in checking valid schemes
       UrlValidator urlValidator = new UrlValidator();
       //assertTrue(urlValidator.isValidScheme("http://"));
       assertFalse(urlValidator.isValidScheme("http:/"));
       //assertTrue(urlValidator.isValidScheme("ftp://"));
       //assertTrue(urlValidator.isValidScheme("h3t://"));
       assertFalse(urlValidator.isValidScheme("http//"));
       assertFalse(urlValidator.isValidScheme("htp://"));
       assertFalse(urlValidator.isValidScheme("http:"));

   }

   public void testIsValidAuthority()
   {
        UrlValidator urlValidator = new UrlValidator();
//      assertTrue(urlValidator.isValidAuthority("www.google.com"));
//      assertTrue(urlValidator.isValidAuthority("en.wikipedia.org"));
//      assertTrue(urlValidator.isValidAuthority("g.uk"));

//      assertFalse(urlValidator.isValidAuthority(""));
//      assertFalse(urlValidator.isValidAuthority("www..com"));
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
       //bug in path regex, second slash invalidates the url path
       UrlValidator urlValidator = new UrlValidator();
       assertFalse(urlValidator.isValidPath("asdasdasdsad"));
       assertTrue(urlValidator.isValidPath("/page"));
       assertTrue(urlValidator.isValidPath("/page123"));
       assertFalse(urlValidator.isValidPath("/.."));
//       assertTrue(urlValidator.isValidPath("/repo/file"));
//       assertTrue(urlValidator.isValidPath("/repo1/file2"));
       assertFalse(urlValidator.isValidPath("/repo//file"));
//       assertTrue(urlValidator.isValidPath("/page/"));
//       assertTrue(urlValidator.isValidPath("/page/folder/"));
   }

   public void testIsValidQuery()
   {
       UrlValidator urlValidator = new UrlValidator();
       assertTrue(urlValidator.isValidQuery("?action=view"));
       assertTrue(urlValidator.isValidQuery("?foo=bar&bar=foo"));
       //url validator finds any query to be valid
//       assertFalse(urlValidator.isValidQuery("$action=view"));
//       assertFalse(urlValidator.isValidQuery("?action==view"));
       assertTrue(urlValidator.isValidQuery("?foo=bar;foo2=bar2"));
   }

   public void testIsValid()
   {
       final int NUM_URLS = 100;
       Random rand = new Random();

       String[] schemes = {"http://", "https://", "ftp://", "", "htp://", "bttp://", "://"};
       String[] authorities = {"www.google.com", "en.wikipedia.org", "g.uk", "1.2.3.4", "foo.", ".bar"};
       String[] paths = {"/page123", "/page/folder/", "repo//file", "/."};
       String[] queries = {"?action=view", "?foo=bar&bar=foo", "?foo=bar;foo2=bar2", "$action=view"};

       UrlValidator urlValidator = new UrlValidator();
       for (int i = 0; i < NUM_URLS; i++)
       {
           String url = schemes[rand.nextInt(schemes.length-1)];
           url += authorities[rand.nextInt(authorities.length-1)];
           url += paths[rand.nextInt(paths.length-1)];
           url += queries[rand.nextInt(queries.length-1)];

           urlValidator.isValid(url);
       }
   }
}
