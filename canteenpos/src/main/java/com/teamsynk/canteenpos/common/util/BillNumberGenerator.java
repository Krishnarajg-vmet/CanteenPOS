package com.teamsynk.canteenpos.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BillNumberGenerator {
	
private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yy");
    
    private static final ConcurrentHashMap<String, AtomicLong> branchCounters = new ConcurrentHashMap<>();
    private static String lastYear = LocalDate.now().format(YEAR_FORMATTER);

    public static synchronized String generate(String branchCode) {
        String currentYear = LocalDate.now().format(YEAR_FORMATTER);
        
        if (!currentYear.equals(lastYear)) {
            branchCounters.clear();
            lastYear = currentYear;
        }

        AtomicLong counter = branchCounters.computeIfAbsent(branchCode.toUpperCase(), k -> new AtomicLong(1));

        return String.format("%s%s%05d", branchCode.toUpperCase(), currentYear, counter.getAndIncrement());
    }


}
