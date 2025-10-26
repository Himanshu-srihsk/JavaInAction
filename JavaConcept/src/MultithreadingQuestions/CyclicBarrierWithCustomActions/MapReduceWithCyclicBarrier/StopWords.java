package MultithreadingQuestions.CyclicBarrierWithCustomActions.MapReduceWithCyclicBarrier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class StopWords {
    public static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "and", "or", "but", "if", "to", "of", "in", "on", "for", "with"
    ));

    public static boolean isValidWord(String word) {
        return word.length() >= 3 && !STOP_WORDS.contains(word.toLowerCase());
    }

    public static String cleanWord(String word) {
        return word.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }
}
