package com.tc.reactor.support.languages.prism;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrismKeywords {

    public static final Map<String, List<String>> CONTROL_FLOW_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> FILE_OPERATIONS_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> DATA_MANIPULATION_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> EXPORT_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> DISPLAY_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> TABLE_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> VARIABLE_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> SPECIAL_KEYWORDS = new HashMap<>();

    static {
        CONTROL_FLOW_KEYWORDS.put("pzt", Arrays.asList("GoTo","ForEach","ForEachSheet","Next","Regenerate"));
    }

    static {
        FILE_OPERATIONS_KEYWORDS.put("pzt", Arrays.asList("SetPath","Open","Close","Save","Import","ImportLink",
                "InsertData","InsertDataLink","OpenOutput","Table","WTable","NextRow","EndTable","WText",
                "WDate","WTitle","WCell","WRow","WCol","WSheet","WBlock","WTCol","WTRow","WTSheet","WTBlock","WVersion",
                "CloseOutput"));
    }

    static {
        DATA_MANIPULATION_KEYWORDS.put("pzt", Arrays.asList("DuplicateDataTable","DuplicateFamily","CreateLayout",
                "SetYReplicates","ClearTable","DeleteAllDataSets","Copy","Paste","SetInfo","SetValueToInfo"));
    }

    static {
        DISPLAY_KEYWORDS.put("pzt", Arrays.asList("ApplyColorScheme","SetAxisTitle","SetGraphTitle","SetAxisLimits"));
    }

    static {
        EXPORT_KEYWORDS.put("pzt", Arrays.asList("ExportWMF","ExportEMF","ExportTIF","ExportBMP","ExportPNG",
                "ExportJPG","ExportEPS","ExportPDF","ExportAllPDF","ExportTable","SendToWord","SendToPP"));
    }

    static {
        SPECIAL_KEYWORDS.put("pzt", Arrays.asList("SetProjectName","SetScriptDialogTitle","RecalcAll","Beep",
                "Createlog","SetSheetTitle","CloseScriptDialog","Print","Exit"));
    }

    static {
        TABLE_KEYWORDS.put("pzt", Arrays.asList("Prism","Text","CSV","HTML","XML","SQL"));
    }

    static {
        VARIABLE_KEYWORDS.put("pzt", Arrays.asList("%F","%X","%S","%N","%%"));
    }
}
