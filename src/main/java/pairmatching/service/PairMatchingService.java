package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Condition;
import pairmatching.domain.Pair;
import pairmatching.repository.PairMatchingRepository;
import pairmatching.repository.PairRepository;

import java.util.*;
import java.util.stream.IntStream;

public class PairMatchingService {

    public static final String QUIT = "Q";
    public static final int PAIR_SIZE = 2;

    private final PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    public Set<Pair> match(Condition condition, List<String> crews) {
        if (isEvenSize(crews)) {
            Set<Pair> pairs = getEvenCrews(getShuffledCrews(crews));
            pairMatchingRepository.add(condition, pairs);
            return pairs;
        }
        Set<Pair> pairs = getOddCrews(getShuffledCrews(crews));
        pairMatchingRepository.add(condition, pairs);
        return pairs;
    }

    private boolean isEvenSize(List<String> crews) {
        return crews.size() % 2 == 0;
    }

    private Set<Pair> getEvenCrews(List<String> crews) {
        Set<Pair> pairs = new LinkedHashSet<>();
        for (int i = 0; i < crews.size(); i += PAIR_SIZE) {
            pairs.add(new Pair(crews.subList(i, i + PAIR_SIZE)));
        }
        return pairs;
    }
}
