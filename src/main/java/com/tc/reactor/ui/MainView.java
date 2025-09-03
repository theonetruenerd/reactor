package com.tc.reactor.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.reactor.support.editor.CodeAutocompletion;
import com.tc.reactor.support.editor.CodeFormatter;
import com.tc.reactor.support.editor.ContextMenuSetup;
import com.tc.reactor.support.editor.SyntaxManager;
import com.tc.reactor.support.git.GitUtils;
import com.tc.reactor.support.languages.hsl.RealTimeSyntaxChecker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.fxmisc.richtext.CodeArea;
import com.tc.reactor.support.languages.hsl.LibraryHandler;
import org.fxmisc.richtext.LineNumberFactory;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class MainView {

    // Defining the FXML classes:
    @FXML private MenuBar menuBar;
    @FXML private TreeView<String> projectTree;
    @FXML private TabPane mainTabPane;
    @FXML public TabPane bottomTabPane;
    @FXML public TextArea outputTextArea;
    @FXML public TextArea logsTextArea;
    @FXML public Tab logTab;
    @FXML private TreeView<String> gitCommitTreeView;
    @FXML private TextArea commitMessageTextArea;
    @FXML private SplitMenuButton runConfigSplitMenu;
    @FXML private MenuItem venusFourOpMan;
    @FXML private MenuItem venusFourProgMan;
    @FXML private MenuItem hslDocs;

    private final GitUtils gitUtils = new GitUtils();
    private final Map<String, String> fileMap = new HashMap<>();

    private enum HtmlEditorType {
        TEXT, HTML
    }

    private HtmlEditorType preferredEditor = HtmlEditorType.HTML;

    /**
     * Initializes the window, setting up initial tabs
     */
    @FXML
    private void initialize() throws IOException {
        setupInitialTabs();
        Platform.runLater(this::setupKeyboardShortcuts);
        try {
            RunConfig runConfig = new RunConfig();
            runConfig.loadRunConfigsFromFile();
        } catch (IOException e) {
            logsTextArea.appendText("\n> "+e.getMessage());
            bottomTabPane.getSelectionModel().select(logTab);
        }
        updateRunConfigMenu();
    }

    private void setupKeyboardShortcuts() {
        menuBar.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN).match(event))
            {
                saveCurrentFile();
                event.consume();
            }
        });
    }

    /**
     * Function to handle opening of help documents
     */
    private void openDocument(Path path) throws IOException {
        if (Desktop.isDesktopSupported()) {
            try {
                File document = new File(path.toFile().getAbsolutePath());
                Desktop.getDesktop().open(document);
            } catch (IOException ex) {
                logsTextArea.appendText("\n> "+ex.getMessage());
                bottomTabPane.getSelectionModel().select(logTab);
            }
        }
    }

    @FXML
    public void onV4ProgManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/VENUS four Programmers Manual.pdf"));
    }

    @FXML
    public void onV4OpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/VENUS four Operators Manual.pdf"));
    }

    @FXML
    public void onHslOverviewClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/HSL Materials/HamiltonStandardLanguage_HSL_Overview.ppt"));
    }

    @FXML
    public void onTadmManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/624031_03_TADM_Programmers_Guide.pdf"));
    }

    @FXML
    public void onV2OpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Manuals prior to VENUS four/VENUS two Operators Manual.pdf"));
    }

    @FXML
    public void onV2ProgManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Manuals prior to VENUS four/VENUS two Programmers Manual.pdf"));
    }

    @FXML
    public void onV3OpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Manuals prior to VENUS four/VENUS three Operators Manual.pdf"));
    }

    @FXML
    public void onV3ProgManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Manuals prior to VENUS four/VENUS three Programmers Manual.pdf"));
    }

    @FXML
    public void onOrbitGuideClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Honeywell Orbit Scanner Documents/7120 Orbit User's Guide - English.pdf"));
    }

    @FXML
    public void onMetroSelectConfigClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Honeywell Orbit Scanner Documents/MetroSelect Configuration Guide.pdf"));
    }

    @FXML
    public void onHslAndDllsClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/HSL Materials/HSL_and_DLLs_Overview.pptx"));
    }

    @FXML
    public void onVectorHslLibIntClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/HSL Materials/Vector_HSL_LibraryIntegration.pdf"));
    }

    @FXML
    public void onMicrosideSQEffClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Microcide SQ/Microside SQ Efficacy.pdf"));
    }

    @FXML
    public void onMicrocideSQMSDSClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Microcide SQ/Microcide SQ MSDS_.doc"));
    }

    @FXML
    public void onQGExpImportClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_ExportImport_Instructions_v1.pdf"));
    }

    @FXML
    public void onQGFixSyntASWClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_FixingSyntaxErrorsASWLibraries_v1.pdf"));
    }

    @FXML
    public void onQGPrepVectorDBClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_PrepareVectorDatabase_v1.pdf"));
    }

    @FXML
    public void onQGRabbitMQCLicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_RabbitMQ and VENUS_v1.pdf"));
    }

    @FXML
    public void onQGRunTwoStarsClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_RunTwoSTARsFromOnePCandOneMethod_v1.pdf"));
    }

    @FXML
    public void onQGiSWAPTeachingClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_TeachingOffDeckWithiSWAP_v1.pdf"));
    }

    @FXML
    public void onUsersCfgCorruptClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_Userscfg_CorruptionSolution_v1.pdf"));
    }

    @FXML
    public void onVenusReinstallUpgradeClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_VENUS_ReinstallationAndUpgrades.pdf"));
    }

    @FXML
    public void onVidRecordClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Quick Guides/QuickGuide_VideoRecorderLibrary_v1.pdf"));
    }

    @FXML
    public void onDbIntClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/SQL Materials/Database_Integration.pptx"));
    }

    @FXML
    public void onSqlRelDbClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/SQL Materials/SQL_Statements&RelationalDatabases_v1.pdf"));
    }

    @FXML
    public void onVectorDbClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Vector Database Materials/Vector Database.ppt"));
    }

    @FXML
    public void onVectorDbModelClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Vector Database Materials/Vector Database Model.pdf"));
    }

    @FXML
    public void onVectorDbPlusClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Vector Database Materials/Vector Database Plus.ppt"));
    }

    @FXML
    public void onVenusDynSchedClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/624030_01_VENUS_Dynamic_Scheduler_Manual.pdf"));
    }

    @FXML
    public void onVen62SoftManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/10175292_00 VENUS 6-2 Software Programmer's Manual.pdf"));
    }

    @FXML
    public void onLiqHandRefGuideClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/eBook_Liquid Handling Reference Guide_Revision2_Final.pdf"));
    }

    @FXML
    public void onLVKClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/Liquid Verification Kit User's Manual Rev A.pdf"));
    }

    @FXML
    public void onSqlDbManInstallClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/SQL Materials/SQL Server Database Manual Installation v1.pdf"));
    }

    @FXML
    public void onUvLightClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/STAR UV Light Installation and User's Manual Rev E.pdf"));
    }

    @FXML
    public void onLimsClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/TN_NMBI-STRI-VTGI_HowToInterfaceLaboratoryInfoManagementSystems-TN052.pdf"));
    }

    @FXML
    public void onProgPracticesClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/VENUS Programming Practices v5.pdf"));
    }

    @FXML
    public void onV6SoftProgManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/VENUS 6 Software Programmer's Manual.pdf"));
    }

    @FXML
    public void onVenusCFRClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/hamilton/VENUS 21 CFR Part 11 Guide Rev B.pdf"));
    }

    @FXML
    public void onD300OpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/d300e/D300e Digital Dispenser Operating Manual.pdf"));
    }

    @FXML
    public void onEvoOpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evo/392886_EVO OpM_V10_1.pdf"));
    }

    @FXML
    public void onEvo75OpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evo/393248_EVO75 OpM_V3_3.pdf"));
    }

    @FXML
    public void onEvoAppManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evo/394802_en_V2_3 Application Software Manual Fdm EVOlution-1.pdf"));
    }

    @FXML
    public void onEvolInstManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evolyzer/392888_en_V6_5 Instrument Software Manual.pdf"));
    }

    @FXML
    public void onEvol2OpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evolyzer/393035_EVOlyzer2 OpM_en_V2_10.pdf"));
    }

    @FXML
    public void onEvolRunManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evolyzer/394803_en_v2_3 Runtime Controller Manual Fdm EVOlution-1.pdf"));
    }

    @FXML
    public void onEvol3OpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evolyzer/402249 EVOlyzer3 OpM V1.1.pdf"));
    }

    @FXML
    public void onEvoWaGSClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evoware/Getting Started Guide Freedom EVOware 2.8 SP7.pdf"));
    }

    @FXML
    public void onEvoWaGPClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evoware/Manual Freedom EVOware 2.8 SP7 General Purpose.pdf"));
    }

    @FXML
    public void onEvoWaRUClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evoware/Manual Freedom EVOware 2.8 SP7 Research Use Only.pdf"));
    }

    @FXML
    public void onEvoWaRMClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/evoware/Readme Freedom EVOware 2.8 SP7.pdf"));
    }

    @FXML
    public void onFC37Clicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-control/FluentControl Application Software Manual 3.7 SP1.pdf"));
    }

    @FXML
    public void onFC33Clicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-control/FluentControl 3.3 Manual.pdf"));
    }

    @FXML
    public void onFC34Clicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-control/FluentControl 3.4 Manual.pdf"));
    }

    @FXML
    public void onFC35Clicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-control/FluentControl 3.5 Manual.pdf"));
    }

    @FXML
    public void onFC36Clicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactpr/documentation/tecan/fluent-control/FluentControl 3.6 pdf"));
    }

    @FXML
    public void onFC371Clicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-control/FluentControl 3.7 Manual 1.pdf"));
    }

    @FXML
    public void onFDXOpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-dx/403096_en.pdf"));
    }

    @FXML
    public void onFDXSecManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-dx/403097_OM_Secure_Fluent_V1.1.pdf"));
    }

    @FXML
    public void onFDXRefManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-dx/403190_Reference Manual Fluent Dx V1.0.pdf"));
    }

    @FXML
    public void onFGXOpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-gx/399706_en V2_7.pdf"));
    }

    @FXML
    public void onFGXSecManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-gx/403097_OM_Secure_Fluent_v1.1.pdf"));
    }

    @FXML
    public void onFGXRefManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/fluent-gx/399937_Fluent Reference Manual V1.8.pdf"));
    }

    @FXML
    public void onHydroManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/hydroflex/30115019_IFU_HYDROFLEX_ENGLISH_GP_V1_3-1.pdf"));
    }

    @FXML
    public void onHydroPlusManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/hydroflex/30199906_IFU_HYDROFLEX_PLUS_ENGLISH_V1_3.pdf"));
    }

    @FXML
    public void onHydroSpeedManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/hydrospeed/30086672_IFU_HYDROSPEED_ENGLISH_GP_V2_0.pdf"));
    }

    @FXML
    public void onHydroCtrlClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/hydrowasher/30064355_IFU_HydroControl_V2_5_English.pdf"));
    }

    @FXML
    public void onHydroWashDrivClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/hydrowasher/30064372_IFU_HydroWasher_Driver_V1_4_English.pdf"));
    }

    @FXML
    public void onInfProManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/infinite-200-pro/30125943_IFU_Infinite200-PRO_V1_5_English.pdf"));
    }

    @FXML
    public void onInfPlusManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/infinite-f50-plus/30186912_IFU_INFINITE_F50_PLUS_V1_2_English.pdf"));
    }

    @FXML
    public void onMagManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/magellan/30143531_IFU_MAGELLAN_ENGLISH_V1_5.pdf"));
    }

    @FXML
    public void onMagProManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/magellan/30169003_IFU_Magellan_Pro_7-4_English_V1_1.pdf"));
    }

    @FXML
    public void onResManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/resolvex/253-5286-REV-L DOC RESOLVEX A200 OP MANUAL ENGLISH 1.pdf"));
    }

    @FXML
    public void onSamTrackClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/sample-tracking/393933.pdf"));
    }

    @FXML
    public void onSparCtrlClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/spark/30145640_IFU_SparkControl_V2_4.pdf"));
    }

    @FXML
    public void onSparRefClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/spark/30246429_IFU_SPARK_REF_ENGLISH_V2_5.pdf"));
    }

    @FXML
    public void onSparBGClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/spark/30246431_IFU_SPARK_BASIC_GUIDE_V2_4_ENGLISH.pdf"));
    }

    @FXML
    public void onSunriseManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/sunrise/30086638_IFU_SUNRISE_GP_V2_9_ENGLISH-1.pdf"));
    }

    @FXML
    public void onSunriseIFUClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/sunrise/30213635_IFU_SUNRISE_GP_ENGLISH_V1_0.pdf"));
    }

    @FXML
    public void onVeyaOpManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/veya/402664_en.pdf"));
    }

    @FXML
    public void onVeyaSecManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/veya/403037 sec.pdf"));
    }

    @FXML
    public void onVeyaDxManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/tecan/veya/403062_en DX.pdf"));
    }

    @FXML
    public void onBio4000SoftManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/beckman-coulter/biomek-4000/B08852AA.pdf"));
    }

    @FXML
    public void onBioI57IFUClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/beckman-coulter/biomek-i-series/B54473AD.pdf"));
    }

    @FXML
    public void onBioIRefManClicked() throws IOException {
        openDocument(Path.of("src/main/resources/com/tc/reactor/documentation/beckman-coulter/biomek-i-series/B56358AC.pdf"));
    }

    /**
     * Handles the click event for the open project menu item. Opens a folder browser and
     * populates the project tree tab with files from the selected directory.
     */
    @FXML
    private void onOpenProjectClick() throws GitAPIException, IOException {
        // Opens folder browser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Project Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        // Checks if the user did select a directory
        if (selectedDirectory != null) {
            // Populates the project tree tab from the directory
            populateProjectTree(selectedDirectory);
            handleRepositoryInitialization(selectedDirectory);
        } else {
            logsTextArea.appendText("\n> "+"No directory selected");
            bottomTabPane.getSelectionModel().select(logTab);
        }
        logsTextArea.appendText("\n> "+gitUtils.getRepository().toString());
        bottomTabPane.getSelectionModel().select(logTab);
    }


    private void handleRepositoryInitialization(File selectedDirectory) {
        try {
            gitUtils.setRepository(selectedDirectory.getAbsolutePath());
            logsTextArea.appendText("\n> "+"Git repository loaded: " + gitUtils.getRepository().getDirectory().getAbsolutePath());
            bottomTabPane.getSelectionModel().select(logTab);
        } catch (RepositoryNotFoundException e) {
            showGitRepositoryDialog(selectedDirectory);
        } catch (IOException e) {
            showErrorDialog("Error while accessing Git repository.", e.getMessage());
        }
    }

    private void showGitRepositoryDialog(File selectedDirectory) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Git Repository Not Found");
        alert.setHeaderText("This folder is not a Git repository.");
        alert.setContentText("Would you like to create a new Git repository in the selected folder?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                createGitRepository(selectedDirectory);
            }
        });
    }

    private void createGitRepository(File selectedDirectory) {
        try {
            gitUtils.createRepository(selectedDirectory.getAbsolutePath());
            logsTextArea.appendText("\n> "+"Git repository created: " + gitUtils.getRepository().getDirectory().getAbsolutePath());
            bottomTabPane.getSelectionModel().select(logTab);
        } catch (Exception e) {
            showErrorDialog("Error while creating Git repository.", e.getMessage());
        }
    }

    @FXML
    private void onRunButtonClick() throws IOException, InterruptedException {
        String runConfig = runConfigSplitMenu.getText();

        if (runConfig == null || runConfig.isBlank() || runConfig.equals("Run Configs")) {
            logsTextArea.appendText("\n> "+"No run configuration selected.");
            bottomTabPane.getSelectionModel().select(logTab);
            return;
        }

        Tab currentTab = mainTabPane.getSelectionModel().getSelectedItem();
        String currentFilePath = currentTab.getUserData().toString();

        currentFilePath = currentFilePath.replace("\\", "\\\\");
        currentFilePath = "\"" + currentFilePath + "\"";

        String[] configParts = runConfig.split(",");
        String exe = configParts[1].split("::")[0].substring(1);
        String args = configParts[1].split("::")[1];

        exe = exe.replace("\\", "\\\\");
        exe = "\"" + exe + "\"";

        logsTextArea.appendText("\n> "+Arrays.toString(configParts));
        bottomTabPane.getSelectionModel().select(logTab);
        String command = String.format("%s %s %s", exe, currentFilePath, args);

        logsTextArea.appendText("\n> "+command);
        bottomTabPane.getSelectionModel().select(logTab);

        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(command);
        proc.waitFor();

        // Handle output from the process
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logsTextArea.appendText("\n> "+line);
                bottomTabPane.getSelectionModel().select(logTab);
            }
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Populates the project tree tab with files from the selected directory.
     *
     * @param rootDirectory the root directory to start populating the tree
     */
    private void populateProjectTree(File rootDirectory) {
        // Gets the root file as the root directory
        TreeItem<File> rootItem = new TreeItem<>(rootDirectory);

        // Sets it to be expanded in 1 tier by default
        rootItem.setExpanded(true);
        // Adds the files to the tree tab
        addFilesToTreeItem(rootDirectory, rootItem, fileMap);

        TreeView<String> view = new TreeView<>();
        projectTree.setRoot(new TreeItem<>(rootDirectory.getName())); // Set placeholder
        projectTree.setRoot(wrapTreeWithFileNames(rootItem)); // Convert back to TreeView<String>
        projectTree.setOnMouseClicked(this::handleTreeClick); // Opens the file when the file is clicked
    }

    /**
     * Wraps the given file tree with file names.
     *
     * @param fileTree the root of the file tree to wrap
     * @return a new TreeItem representing the wrapped file tree
     */
    private TreeItem<String> wrapTreeWithFileNames(TreeItem<File> fileTree) {
        TreeItem<String> result = new TreeItem<>(fileTree.getValue().getName());
        for (TreeItem<File> child : fileTree.getChildren()) {
            result.getChildren().add(wrapTreeWithFileNames(child));
        }
        // Attach File as user data (optional but useful)
//        result.setValue(fileTree.getValue().getAbsolutePath()); // Use a full path in value
        return result;
    }

    /**
     * Recursively adds files to the specified tree item.
     *
     * @param directory the directory to start populating the tree
     * @param parentItem the parent tree item to add the files to
     * @param fileMap the hashmap being used to associate file names with file paths
     */
    private void addFilesToTreeItem(File directory, TreeItem<File> parentItem, Map<String, String> fileMap) {
        // Makes a list of files from the directory
        File[] files = directory.listFiles();
        // If there are no files, returns
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName(); // Extract the file name
                    parentItem.getChildren().add(new TreeItem<>(file));
                    fileMap.put(fileName, file.getAbsolutePath()); // Map file name to full path
                }
            }
        }

    }

    /**
     * Handles the click event to close a project.
     * This method performs two actions:
     * - Clears the project tree to remove all listed files and directories.
     * - Closes all open tabs in the main tab pane, effectively resetting the workspace.
     */
    public void onCloseProjectClick() {
        clearTree();
        closeAllTabs();
    }

    /**
     * Clears the content of the project tree by resetting its root node.
     * This effectively removes all displayed items from the tree view.
     */
    private void clearTree() {
    	projectTree.setRoot(new TreeItem<>(""));
    }

    private void saveCurrentFile() {
        Tab currentTab = mainTabPane.getSelectionModel().getSelectedItem();
        if (currentTab == null || currentTab.getUserData() == null) {
            logsTextArea.appendText("\n> "+"No active file to save.");
            bottomTabPane.getSelectionModel().select(logTab);
            return; // Skip if no file is loaded
        }

        // Retrieve file path from the tab's userData
        String filePath = currentTab.getUserData().toString();
        if (filePath.isBlank()) {
            logsTextArea.appendText("\n> "+"Invalid file path.");
            bottomTabPane.getSelectionModel().select(logTab);
            return;
        }

        CodeArea editor = (CodeArea) currentTab.getContent();
        String fileContent = editor.getText();

        saveFile(filePath, fileContent);
        logsTextArea.appendText("\n> "+"File saved: " + filePath);
        bottomTabPane.getSelectionModel().select(logTab);
    }


    public void saveFile(String filePath, String fileContent) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            if (!dirsCreated) {
                logsTextArea.appendText("\n> ERROR: Failed to create directories for: " + parentDir.getAbsolutePath());
                bottomTabPane.getSelectionModel().select(logTab);
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent);
            logsTextArea.appendText("\n> "+"File saved successfully: " + file.getAbsolutePath());
            bottomTabPane.getSelectionModel().select(logTab);
        } catch (IOException e) {
            logsTextArea.appendText("\n> ERROR: Failed to save file: " + filePath);
            bottomTabPane.getSelectionModel().select(logTab);
            e.printStackTrace();

        }
    }

    @FXML
    private void updateRunConfigMenu() {
        ObjectMapper objectMapper = new ObjectMapper();

        RunConfig runConfig = new RunConfig();

        File file = new File(runConfig.configFilePath.toUri());

        runConfigSplitMenu.getItems().clear();

        if (file.exists()) {
            if (file.exists()) {
                try {
                    RunConfig.RunConfigSave[] configs = objectMapper.readValue(file, RunConfig.RunConfigSave[].class);

                    for (RunConfig.RunConfigSave config : configs) {
                        String menuText = String.format("%s,[%s::%s]", config.configName, config.exeName, config.args);

                        MenuItem menuItem = new MenuItem(menuText);

                        menuItem.setOnAction(event -> {
                            logsTextArea.appendText("\n> "+"Selected configuration: " + config.configName);
                            bottomTabPane.getSelectionModel().select(logTab);
                            runConfigSplitMenu.setText(menuText);
                        });

                        runConfigSplitMenu.getItems().add(menuItem);
                    }
                } catch (IOException e) {
                    logsTextArea.appendText("\n> ERROR: Failed to load configurations: " + e.getMessage());
                    bottomTabPane.getSelectionModel().select(logTab);
                }
            } else {
                logsTextArea.appendText("\n> "+"Configuration file not found: " + file.getAbsolutePath());
                bottomTabPane.getSelectionModel().select(logTab);
            }

        }

        runConfigSplitMenu.getItems().add(new SeparatorMenuItem());
        MenuItem runConfigAddMenuItem = new MenuItem("Add Config");
        runConfigAddMenuItem.setOnAction(event -> {
            onAddConfigClick();
        });
        runConfigSplitMenu.getItems().add(runConfigAddMenuItem);
        MenuItem runConfigDeleteMenuItem = new MenuItem("Delete Config");
        runConfigDeleteMenuItem.setOnAction(event -> {
            onDeleteConfigClick();
        });
        runConfigSplitMenu.getItems().add(runConfigDeleteMenuItem);
    }

    @FXML
    public void onAddConfigClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tc/reactor/fxml/RunConfig.fxml"));
            Parent root = loader.load();

            RunConfig controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Run Configuration");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            updateRunConfigMenu();

            String exePath = controller.exeComboBox.getValue();

            logsTextArea.appendText("\n> "+"Exe path: " + exePath);
            bottomTabPane.getSelectionModel().select(logTab);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onDeleteConfigClick() {
        ObjectMapper objectMapper = new ObjectMapper();
        RunConfig runConfig = new RunConfig();
        File file = new File(runConfig.configFilePath.toUri());

        String selectedConfig = runConfigSplitMenu.getText();

        logsTextArea.appendText("\n> "+"Delete config selected");
        bottomTabPane.getSelectionModel().select(logTab);

        if (selectedConfig == null || selectedConfig.isBlank() || selectedConfig.equals("Run Configs")) {
            logsTextArea.appendText("\n> "+"No run configuration selected.");
            bottomTabPane.getSelectionModel().select(logTab);
            return;
        }

        if(!file.exists()) {
            logsTextArea.appendText("\n> "+"Configuration file not found: " + file.getAbsolutePath());
            bottomTabPane.getSelectionModel().select(logTab);
            return;
        }

        try {
            RunConfig.RunConfigSave[] configs = objectMapper.readValue(file, RunConfig.RunConfigSave[].class);
            List<RunConfig.RunConfigSave> configList = new ArrayList<>(Arrays.asList(configs));
            boolean removed = configList.removeIf(config -> {
                String menuText = String.format("%s,[%s::%s]", config.configName, config.exeName, config.args);
                return menuText.equals(selectedConfig);
            });

            if (!removed) {
                logsTextArea.appendText("\n> "+"No matching configuration found to delete.");
                bottomTabPane.getSelectionModel().select(logTab);
                return;
            }

            // Write the updated configurations back to the file
            objectMapper.writeValue(file, configList);

            // Update the menu to reflect the changes
            updateRunConfigMenu();

            runConfigSplitMenu.setText("Run Configs");

            logsTextArea.appendText("\n> "+"Configuration deleted successfully.");
            bottomTabPane.getSelectionModel().select(logTab);
        } catch (IOException e) {
            logsTextArea.appendText("\n> ERROR: Failed to update configurations: " + e.getMessage());
            bottomTabPane.getSelectionModel().select(logTab);
        }

    }

    @FXML
    public void onCreateHslLibraryClick() {
        try {
            // Load NewFile.fxml for the input form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tc/reactor/fxml/NewFile.fxml"));
            Parent root = loader.load();

            // Get the controller to retrieve user inputs
            NewFile controller = loader.getController();
            controller.setMainView(this);

            // Create and show the modal dialog
            Stage stage = new Stage();
            stage.setTitle("Create New HSL Library");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Block other UI interaction
            stage.showAndWait(); // Wait for the user to close the dialog

            // Retrieve user inputs from the controller
            String libraryName = controller.getLibraryName();
            String majorId = controller.getMajorId();
            Float libraryVersion = controller.getLibraryVersion();
            Path libraryPath = controller.getLibraryPath();
            Optional<String> parentNamespace = controller.getParentNamespace();
            Optional<String> libraryDescription = controller.getLibraryDescription();

            // Validate inputs and create the library
            if (libraryName != null && majorId != null && libraryVersion != null && libraryPath != null) {
                LibraryHandler libraryHandler = new LibraryHandler();
                libraryHandler.setMainView(this);
                libraryHandler.CreateLibrary(
                        libraryName, majorId, libraryVersion, libraryPath, parentNamespace, libraryDescription
                );
                openFileInTab(libraryPath.resolve(libraryName + ".hsl").toString());
            } else {
                logsTextArea.appendText("\n> "+"Library creation aborted: Missing required fields.");
                bottomTabPane.getSelectionModel().select(logTab);
            }
        } catch (IOException e) {
            logsTextArea.appendText("\n> ERROR: Error loading NewFile.fxml: " + e.getMessage());
            bottomTabPane.getSelectionModel().select(logTab);
        }

    }

    @FXML
    public void onCommitButtonClick() {
        try {
            gitUtils.commit(commitMessageTextArea.getText());
            logsTextArea.appendText("\n> "+"Commit successful.");
            bottomTabPane.getSelectionModel().select(logTab);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRefreshCommitButtonClick() throws GitAPIException {
        TreeItem<String> changes = gitUtils.getUncommittedChanges();
        Platform.runLater(() -> {
            gitCommitTreeView.setRoot(changes);
        });
    }

    /**
     * Closes all open tabs in the main tab pane.
     * This method removes all tabs currently displayed in the main tab pane
     * by clearing its tab list.
     */
    private void closeAllTabs() {
        mainTabPane.getTabs().clear();
    }

    /**
     * Handles the click event on a tree item.
     *
     * @param event the mouse event that triggered this method call
     */
    private void handleTreeClick(MouseEvent event) {
        // Gets the selected item
        TreeItem<String> selectedItem = projectTree.getSelectionModel().getSelectedItem();

        // Checks if the selected item exists and whether it is a file
        if (selectedItem != null && selectedItem.isLeaf()) {

            // Use fileMap to retrieve the full path using the displayed file name
            String fileName = selectedItem.getValue();
            String fullPath = fileMap.get(fileName);

            if (fullPath != null) {
                // Opens the file
                openFileInTab(fullPath);
            } else {
                logsTextArea.appendText("\n> ERROR: File path not found for: " + fileName);
                bottomTabPane.getSelectionModel().select(logTab);
            }
        }
    }


    /**
     * Opens a file in the current tab. If a tab for the same file already exists, it is selected instead.
     *
     * @param filePath the path to the file to be opened
     */
    private void openFileInTab(String filePath) {
        // Check if a tab for the file already exists
        for (Tab tab : mainTabPane.getTabs()) {
            if (tab.getUserData() != null && tab.getUserData().equals(filePath)) {
                // Switch to the existing tab
                mainTabPane.getSelectionModel().select(tab);
                return;
            }
        }

        boolean readOnly = checkReadOnly(getFileExtension(filePath));
        System.out.println("File is read only: " + readOnly);

        // If no tab exists for the file, create a new one
        File file = new File(filePath);
        System.out.println("File path: " + filePath);
        String tabName = file.getName();
        if (readOnly) {
            tabName += " (Read Only)";
        }
        Tab tab = new Tab(tabName);
        tab.setUserData(filePath); // Store a file path for future reference
        String extension = getFileExtension(file.getName());

        System.out.println("File extension: " + extension);

        if (extension.equals("html") || extension.equals("htm")) {
            if (preferredEditor == HtmlEditorType.HTML) {
                HTMLEditor htmlEditor = new HTMLEditor();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    htmlEditor.setHtmlText(content.toString());
                } catch (IOException e) {
                    showErrorDialog("Error", "Failed to open HTML file: " + e.getMessage());

                }
                tab.setContent(htmlEditor);
                mainTabPane.getTabs().add(tab);
                mainTabPane.getSelectionModel().select(tab);
                return;
            }
        }

        CodeArea editor = new CodeArea();
        System.out.println("Editor: " + editor);
        tab.setContent(editor);
        System.out.println("Tab content: " + tab.getContent());
        if (readOnly) {
            System.out.println("Read only");
            editor.setEditable(false);
            System.out.println("Editor editable: " + editor.isEditable());
        }

        editor.setParagraphGraphicFactory(LineNumberFactory.get(editor));

        SyntaxManager syntaxManager = new SyntaxManager();
        syntaxManager.setupSyntaxHighlighting(extension, editor);
        System.out.println("Syntax manager: " + syntaxManager);
        CodeFormatter codeFormatter = new CodeFormatter();
        codeFormatter.setupAutoFormatting(editor, extension);
        System.out.println("Code formatter: " + codeFormatter);
        ContextMenuSetup contextMenuSetup = new ContextMenuSetup();
        contextMenuSetup.setupContextMenu(editor);
        contextMenuSetup.setMainView(this);
        System.out.println("Context menu setup: " + contextMenuSetup);

        // Setup code autocompletion for supported languages
        if ("hsl".equals(extension)) {
            new CodeAutocompletion(editor, extension);
            RealTimeSyntaxChecker syntaxChecker = new RealTimeSyntaxChecker();
            syntaxChecker.SetMainView(this);
            System.out.println("Syntax checker: " + syntaxChecker);
        } else {
            System.out.println("No syntax checker for file type: " + extension);
        }

        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("StringBuilder: " + stringBuilder);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Reading file: " + file.getAbsolutePath());
            String line = "";
            System.out.println("Line: " + line);
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
                System.out.println("String builder: " + stringBuilder);
            }
            editor.appendText(stringBuilder.toString());
            System.out.println("Editor text: " + editor.getText());
        } catch (IOException e) {
            editor.appendText("Error reading file: " + e.getMessage());
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("Editor text: " + editor.getText());
        mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }

    /**
     * Utility method to extract the file extension from a file name.
     *
     * @param fileName The name of the file
     * @return The file extension (e.g., "java", "css") or an empty string if none
     */
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return ""; // No extension
    }

    @FXML
    private void onToggleEditorPreference() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Toggle Editor Preference");
        alert.setHeaderText("Select Default Editor for HTML/HTM Files");
        alert.setContentText("Would you like to use the HTML editor or the text editor?");
        ButtonType htmlButtonType = new ButtonType("HTML Editor");
        ButtonType textButtonType = new ButtonType("Text Editor");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(htmlButtonType, textButtonType);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()){
            if (result.get() == htmlButtonType){
                preferredEditor = HtmlEditorType.HTML;
                logsTextArea.appendText("\n> "+"HTML Editor selected");
                bottomTabPane.getSelectionModel().select(logTab);
            } else if (result.get() == textButtonType) {
                preferredEditor = HtmlEditorType.TEXT;
                logsTextArea.appendText("\n> "+"Text Editor selected");
                bottomTabPane.getSelectionModel().select(logTab);
            } else {
                logsTextArea.appendText("\n> "+"Editor preference not changed");
                bottomTabPane.getSelectionModel().select(logTab);
            }
        }
    }

    @FXML
    private void onSwitchEditor() {
        Tab selectedTab = mainTabPane.getSelectionModel().getSelectedItem();
        String filePath = (String) selectedTab.getUserData();
        Node currentEditor = selectedTab.getContent();

        if (currentEditor instanceof HTMLEditor && preferredEditor == HtmlEditorType.TEXT) {
            // Switch from HTMLEditor to Text Editor
            CodeArea codeEditor = new CodeArea();
            codeEditor.replaceText(((HTMLEditor) currentEditor).getHtmlText());
            selectedTab.setContent(codeEditor);
        } else if (currentEditor instanceof CodeArea && preferredEditor == HtmlEditorType.HTML) {
            // Switch from Text Editor to HTMLEditor
            HTMLEditor htmlEditor = new HTMLEditor();
            htmlEditor.setHtmlText(((CodeArea) currentEditor).getText());
            selectedTab.setContent(htmlEditor);
        }

    }

    /**
     * Initializes the window by setting up initial tabs.
     */
    private void setupInitialTabs() {
    }

    private boolean checkReadOnly(String extension) {
        switch (extension) {
            case "med", "stp":
                try {
                    // Load ReadOnly.fxml for the input form
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tc/reactor/fxml/ReadOnly.fxml"));
                    Parent root = loader.load();

                    // Get the controller to retrieve user inputs
                    ReadOnly controller = loader.getController();

                    // Create and show the modal dialog
                    Stage stage = new Stage();
                    stage.setTitle("Check for read only");
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL); // Block other UI interaction
                    stage.showAndWait(); // Wait for the user to close the dialog

                    return controller.isReadOnly();
                } catch (IOException e) {
                    System.err.println("Error loading ReadOnly.fxml: " + e.getMessage());
                    return false;
                }
            default:
                return false;
        }
    }

    /**
     * Handles the click event for the close menu item.
     * Exits the application when clicked.
     */
    @FXML
    protected void onCloseMenuItemClick() {
        Platform.exit();
    }

}
