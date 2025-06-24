package com.tc.reactor.support.languages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HSL {

    public static final Map<String, List<String>> LANGUAGE_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> SCOPE_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> TYPES_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> BLOCKS_KEYWORDS = new HashMap<>();

    static {

        // Define keywords for HSL
        LANGUAGE_KEYWORDS.put("hsl", Arrays.asList("break","return","abort","pause","goto","resume","next","lock","unlock",
                "synchronized","if","else","for","while","loop"));

        // Define keywords for HS_
        LANGUAGE_KEYWORDS.put("hs_", Arrays.asList("break","return","abort","pause","goto","resume","next","lock","unlock",
                "synchronized","if","else","for","while","loop"));

        // Define keywords for HSI
        LANGUAGE_KEYWORDS.put("hsi", Arrays.asList("break","return","abort","pause","goto","resume","next","lock","unlock",
                "synchronized","if","else","for","while","loop"));

        // Define keywords for SUB
        LANGUAGE_KEYWORDS.put("sub", Arrays.asList("break","return","abort","pause","goto","resume","next","lock","unlock",
                "synchronized","if","else","for","while","loop"));
    }

    static {
        SCOPE_KEYWORDS.put("hsl", Arrays.asList("global","once","static","const","private"));

        SCOPE_KEYWORDS.put("hs_", Arrays.asList("global","once","static","const","private"));

        SCOPE_KEYWORDS.put("hsi", Arrays.asList("global","once","static","const","private"));

        SCOPE_KEYWORDS.put("sub", Arrays.asList("global","once","static","const","private"));
    }

    static {
        TYPES_KEYWORDS.put("hsl", Arrays.asList("variable","sequence","string","device","resource","timer","dialog",
                "object","event","file","struct","short","long","char","float","void"));

        TYPES_KEYWORDS.put("hs_", Arrays.asList("variable","sequence","string","device","resource","timer","dialog",
                "object","event","file","struct","short","long","char","float","void"));

        TYPES_KEYWORDS.put("hsi", Arrays.asList("variable","sequence","string","device","resource","timer","dialog",
                "object","event","file","struct","short","long","char","float","void"));

        TYPES_KEYWORDS.put("sub", Arrays.asList("variable","sequence","string","device","resource","timer","dialog",
                "object","event","file","struct","short","long","char","float","void"));
    }

    static {
        BLOCKS_KEYWORDS.put("hsl", Arrays.asList("function","namespace"));

        BLOCKS_KEYWORDS.put("hs_", Arrays.asList("function","namespace"));

        BLOCKS_KEYWORDS.put("hsi", Arrays.asList("function","namespace"));

        BLOCKS_KEYWORDS.put("sub", Arrays.asList("function","namespace"));
    }

}
