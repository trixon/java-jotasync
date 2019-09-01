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
package se.trixon.jota.client.ui.module;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.control.LogPanel;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.jota.client.ui.MainApp;

/**
 *
 * @author Patrik Karlström
 */
public class LogModule extends WorkbenchModule {

    private BorderPane mBorderPane;
    private LogPanel mLogPanel;

    public LogModule() {
        super(Dict.LOG.toString(), MaterialIcon._Action.HISTORY.getImageView(MainApp.MODULE_ICON_SIZE).getImage());
        createUI();
    }

    @Override
    public Node activate() {
        return mBorderPane;
    }

    private void createUI() {
        mLogPanel = new LogPanel();
        mBorderPane = new BorderPane(mLogPanel);

        Tab jobsTab = new Tab(Dict.JOBS.toString());
        ListView<String> jobsList = new ListView<>();
        jobsTab.setContent(jobsList);
        jobsTab.setClosable(false);

        Tab tasksTab = new Tab(Dict.TASKS.toString());
        ListView<String> tasksList = new ListView<>();
        tasksTab.setContent(tasksList);
        tasksTab.setClosable(false);

        TabPane tabPane = new TabPane(jobsTab, tasksTab);
        tabPane.setPrefWidth(300);
        mBorderPane.setLeft(tabPane);
    }
}
