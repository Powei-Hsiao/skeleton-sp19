import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("catc"));
        assertTrue(palindrome.isPalindrome("c"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("raar"));
    }

    @Test
    public void testIsPalindrome2() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("f", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("flabke", obo));
        assertFalse(palindrome.isPalindrome("flaka", obo));
        assertFalse(palindrome.isPalindrome("flaake", obo));
    }
}
