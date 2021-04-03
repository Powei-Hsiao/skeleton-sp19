public class Palindrome {

    /**Return Deque within every chac in the word.
     *
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /**Compare first and last character in the deque.
     *
     * @param d
     * @return
     */
    private  boolean compare(Deque<Character> d) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        if (d.removeFirst() == d.removeLast()) {
            return compare(d);
        } else {
            return false;
        }
    }

    /**Compare first and last character which difference is one in the deque.
     *
     * @param d
     * @return
     */
    private  boolean compare(Deque<Character> d, CharacterComparator cc) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        if (cc.equalChars(d.removeFirst(), d.removeLast())) {
            return compare(d, cc);
        } else {
            return false;
        }
    }

    /**The word is palindrome or not.
     *
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return compare(d);
    }

    /**The word is off by one palindrome or not.
     *
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return compare(d, cc);
    }
}
