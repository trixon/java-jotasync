/*
 * Copyright 2019 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.jota.client.ui;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.codecentric.centerdevice.MenuToolkit;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.lang3.SystemUtils;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import se.trixon.almond.util.AboutModel;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.PomInfo;
import se.trixon.almond.util.SystemHelper;
import se.trixon.almond.util.fx.AlmondFx;
import se.trixon.almond.util.fx.FxHelper;
import se.trixon.almond.util.fx.dialogs.about.AboutPane;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.jota.client.Manager;
import se.trixon.jota.client.Preferences;

/**
 *
 * @author Patrik Karlström
 */
public class MainApp extends Application {

    public static final String APP_TITLE = "JotaSync";
    public static final int ICON_SIZE_MODULE = 32;
    public static final int ICON_SIZE_MODULE_TOOLBAR = 40;
    public static final int ICON_SIZE_PROFILE = 32;
    public static final int ICON_SIZE_TOOLBAR = 40;
    public static final int ICON_SIZE_DRAWER = ICON_SIZE_TOOLBAR / 2;
    private static final boolean IS_MAC = SystemUtils.IS_OS_MAC;
    private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());
    private Action mAboutAction;
    private Action mAboutRsyncAction;
    private final AlmondFx mAlmondFX = AlmondFx.getInstance();
    private final ResourceBundle mBundle = SystemHelper.getBundle(MainApp.class, "Bundle");
    private Action mClientConnectAction;
    private Action mClientDisconnectAction;
    private Action mHelpAction;
    private Action mHistoryAction;
    private LogModule mHistoryModule;
    private final Manager mManager = Manager.getInstance();
    private Action mOptionsAction;
    private final Preferences mPreferences = Preferences.getInstance();
    private PreferencesModule mPreferencesModule;
    private Action mServerShutdownAction;
    private Action mServerShutdownQuitAction;
    private Action mServerStartAction;
    private Stage mStage;
    private StartModule mStartModule;
    private Workbench mWorkbench;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public MainApp() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        MaterialIcon.setDefaultColor(Color.LIGHTGRAY);
        mStage = stage;
        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("logo.png")));

        mAlmondFX.addStageWatcher(stage, MainApp.class);
        createUI();
        if (IS_MAC) {
            initMac();
        }
        mStage.setTitle(APP_TITLE);
        mStage.show();

        initAccelerators();
        mWorkbench.openModule(mStartModule);
    }

    @Override
    public void stop() throws Exception {
        mManager.disconnect();
        super.stop();
    }

    private void activateModule(int moduleIndexOnPage) {
        if (moduleIndexOnPage == 0) {
            moduleIndexOnPage = 10;
        }

        int pageIndex = 0;//TODO get actual page index
        int moduleIndex = pageIndex * mWorkbench.getModulesPerPage() + moduleIndexOnPage - 1;
        try {
            mWorkbench.openModule(mWorkbench.getModules().get(moduleIndex));
        } catch (IndexOutOfBoundsException e) {
            //nvm
        }
    }

    private void createUI() {
        mStartModule = new StartModule();
        mHistoryModule = new LogModule();
        mPreferencesModule = new PreferencesModule();

        mWorkbench = Workbench.builder(mStartModule, mHistoryModule, mPreferencesModule)
                .tabFactory(CustomTab::new)
                .modulesPerPage(99)
                .build();

        mWorkbench.getStylesheets().add(MainApp.class.getResource("baseTheme.css").toExternalForm());

        setNightMode(mPreferences.general().isNightMode());

        initActions();
        initListeners();

        mWorkbench.getNavigationDrawerItems().setAll(
                ActionUtils.createMenuItem(mHistoryAction),
                ActionUtils.createMenuItem(mOptionsAction),
                ActionUtils.createMenuItem(mHelpAction),
                ActionUtils.createMenuItem(mAboutRsyncAction),
                ActionUtils.createMenuItem(mAboutAction)
        );

        Scene scene = new Scene(mWorkbench);

        mStage.setScene(scene);
    }

    private void displayOptions() {
        mWorkbench.openModule(mPreferencesModule);
    }

    private void initAccelerators() {
        final ObservableMap<KeyCombination, Runnable> accelerators = mStage.getScene().getAccelerators();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            accelerators.put(new KeyCodeCombination(KeyCode.valueOf("DIGIT" + i), KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
                activateModule(index);
            });

            accelerators.put(new KeyCodeCombination(KeyCode.valueOf("NUMPAD" + i), KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
                activateModule(index);
            });
        }

        accelerators.put(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
            mStage.fireEvent(new WindowEvent(mStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

        accelerators.put(new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
            if (mWorkbench.getActiveModule() != mStartModule && mWorkbench.getActiveModule() != null) {
                mWorkbench.closeModule(mWorkbench.getActiveModule());
            }
        });

        mHelpAction.setAccelerator(KeyCombination.keyCombination("F1"));
        mClientConnectAction.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        mClientDisconnectAction.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN));

        mServerStartAction.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mServerShutdownAction.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mServerShutdownQuitAction.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));

        if (!IS_MAC) {
            mOptionsAction.setAccelerator(new KeyCodeCombination(KeyCode.COMMA, KeyCombination.SHORTCUT_DOWN));
            accelerators.put(new KeyCodeCombination(KeyCode.COMMA, KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
                mOptionsAction.handle(null);
            });
        }
    }

    private void initActions() {
        // DRAWER
        //history
        mHistoryAction = new Action(Dict.HISTORY.toString(), (ActionEvent event) -> {
            mWorkbench.hideNavigationDrawer();
            mWorkbench.openModule(mHistoryModule);
        });
        mHistoryAction.setGraphic(MaterialIcon._Action.HISTORY.getImageView(ICON_SIZE_DRAWER));

        //options
        mOptionsAction = new Action(Dict.OPTIONS.toString(), (ActionEvent event) -> {
            mWorkbench.hideNavigationDrawer();
            displayOptions();
        });
        mOptionsAction.setGraphic(MaterialIcon._Action.SETTINGS.getImageView(ICON_SIZE_DRAWER));

        //help
        mHelpAction = new Action(Dict.HELP.toString(), (ActionEvent event) -> {
            mWorkbench.hideNavigationDrawer();
            SystemHelper.desktopBrowse("https://trixon.se/projects/jotasync/documentation/");
        });

        //about rsync
        mAboutRsyncAction = new Action(String.format(Dict.ABOUT_S.toString(), "rsync"), (ActionEvent event) -> {
            mWorkbench.hideNavigationDrawer();
            throw new UnsupportedOperationException("Not supported yet.");
        });

        //about
        mAboutAction = new Action(Dict.ABOUT.toString(), (ActionEvent event) -> {
            mWorkbench.hideNavigationDrawer();
            PomInfo pomInfo = new PomInfo(MainApp.class, "se.trixon", "jotasync");
            AboutModel aboutModel = new AboutModel(
                    SystemHelper.getBundle(getClass(), "about"),
                    SystemHelper.getResourceAsImageView(MainApp.class, "logo.png")
            );
            aboutModel.setAppVersion(pomInfo.getVersion());
            AboutPane aboutPane = new AboutPane(aboutModel);

            double scaledFontSize = FxHelper.getScaledFontSize();
            Label appLabel = new Label(aboutModel.getAppName());
            appLabel.setFont(new Font(scaledFontSize * 1.8));
            Label verLabel = new Label(String.format("%s %s", Dict.VERSION.toString(), aboutModel.getAppVersion()));
            verLabel.setFont(new Font(scaledFontSize * 1.2));
            Label dateLabel = new Label(aboutModel.getAppDate());
            dateLabel.setFont(new Font(scaledFontSize * 1.2));

            VBox box = new VBox(appLabel, verLabel, dateLabel);
            box.setAlignment(Pos.CENTER_LEFT);
            box.setPadding(new Insets(0, 0, 0, 22));
            BorderPane topBorderPane = new BorderPane(box);
            topBorderPane.setLeft(aboutModel.getImageView());
            topBorderPane.setPadding(new Insets(22));
            BorderPane mainBorderPane = new BorderPane(aboutPane);
            mainBorderPane.setTop(topBorderPane);

            WorkbenchDialog dialog = WorkbenchDialog.builder(Dict.ABOUT.toString(), mainBorderPane, ButtonType.CLOSE).build();
            mWorkbench.showDialog(dialog);
        });

        // CONNECTION
        //Connect
        mClientConnectAction = new Action(Dict.CONNECT_TO_SERVER.toString(), (ActionEvent event) -> {
        });
        mClientConnectAction.setGraphic(MaterialIcon._Communication.CALL_MADE.getImageView(ICON_SIZE_DRAWER));

        //Disconnect
        mClientDisconnectAction = new Action(Dict.DISCONNECT.toString(), (ActionEvent event) -> {
        });
        mClientDisconnectAction.setGraphic(MaterialIcon._Communication.CALL_RECEIVED.getImageView(ICON_SIZE_DRAWER));

        //Server Start
        mServerStartAction = new Action(Dict.START.toString(), (ActionEvent event) -> {
        });

        //Server Stop
        mServerShutdownAction = new Action(Dict.SHUTDOWN.toString(), (ActionEvent event) -> {
        });

        //Server Stop and Quit
        mServerShutdownQuitAction = new Action(Dict.SHUTDOWN_AND_QUIT.toString(), (ActionEvent event) -> {
        });
    }

    private void initListeners() {
        mPreferences.general().nightModeProperty().addListener((observable, oldValue, newValue) -> setNightMode(newValue));
    }

    private void initMac() {
        MenuToolkit menuToolkit = MenuToolkit.toolkit();
        Menu applicationMenu = menuToolkit.createDefaultApplicationMenu(APP_TITLE);
        menuToolkit.setApplicationMenu(applicationMenu);

        applicationMenu.getItems().remove(0);
        MenuItem aboutMenuItem = new MenuItem(String.format(Dict.ABOUT_S.toString(), APP_TITLE));
        aboutMenuItem.setOnAction(mAboutAction);

        MenuItem settingsMenuItem = new MenuItem(Dict.PREFERENCES.toString());
        settingsMenuItem.setOnAction(mOptionsAction);
        settingsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.COMMA, KeyCombination.SHORTCUT_DOWN));

        applicationMenu.getItems().add(0, aboutMenuItem);
        applicationMenu.getItems().add(2, settingsMenuItem);

        int cnt = applicationMenu.getItems().size();
        applicationMenu.getItems().get(cnt - 1).setText(String.format("%s %s", Dict.QUIT.toString(), APP_TITLE));
    }

    private void setNightMode(boolean state) {
        String lightTheme = getClass().getResource("lightTheme.css").toExternalForm();
        String darkTheme = getClass().getResource("darkTheme.css").toExternalForm();
        String darculaTheme = FxHelper.class.getResource("darcula.css").toExternalForm();

        ObservableList<String> stylesheets = mWorkbench.getStylesheets();

        if (state) {
            stylesheets.remove(lightTheme);
            stylesheets.add(darkTheme);
            stylesheets.add(darculaTheme);
        } else {
            stylesheets.remove(darkTheme);
            stylesheets.remove(darculaTheme);
            stylesheets.add(lightTheme);
        }
    }
}