package test;

import java.time.Instant;

public class TestJava8TimeInstant {
    public static void main(String[] args) {
        Instant now = Instant.now();
        Instant instant = now.plusSeconds(3);
        Instant instant1 = now.minusSeconds(3);
        long epochSecond = now.getEpochSecond();
        System.out.println("emochSecong"+epochSecond);
        int nano = now.getNano();
        System.out.println("nano"+nano);
        System.out.println(now);
    }
}
