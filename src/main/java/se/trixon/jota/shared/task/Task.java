/* 
 * Copyright 2016 Patrik Karlsson.
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
package se.trixon.jota.shared.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import se.trixon.jota.client.ui.editor.module.task.TaskExecutePanel;
import se.trixon.util.BundleHelper;
import se.trixon.util.dictionary.Dict;

/**
 *
 * @author Patrik Karlsson
 */
public class Task implements Comparable<Task>, Serializable {

    private final List<String> mCommand = new ArrayList<>();
    private String mDescription = "";
    private String mDestination;
    private String mDetails = "";
    private String mEnvironment = "";
    private final ExcludeSection mExcludeSection;
    private final TaskExecuteSection mExecuteSection;
    private String mHistory = "";
    private long mId = System.currentTimeMillis();
    private String mName = "";
    private boolean mNoAdditionalDir;
    private final OptionSection mOptionSection;
    private String mSource;
    private StringBuilder mSummaryBuilder;

    public Task() {
        mExecuteSection = new TaskExecuteSection();
        mExcludeSection = new ExcludeSection();
        mOptionSection = new OptionSection();
    }

    public void addHistory(String history) {
        mHistory = history + mHistory;
    }

    @Override
    public int compareTo(Task o) {
        return mName.compareTo(o.getName());
    }

    public List<String> getCommand() {
        mCommand.clear();

        if (!StringUtils.isBlank(StringUtils.join(mOptionSection.getCommand(), ""))) {
            mCommand.addAll(mOptionSection.getCommand());
        }

        if (!StringUtils.isBlank(StringUtils.join(mExcludeSection.getCommand(), ""))) {
            mCommand.addAll(mExcludeSection.getCommand());
        }

        String source;
        String destination;

        if (SystemUtils.IS_OS_WINDOWS) {
            source = "/cygdrive/" + mSource.replace(":", "").replace("\\", "/");
            destination = "/cygdrive/" + mDestination.replace(":", "").replace("\\", "/");
        } else {
            source = mSource;
            destination = mDestination;
        }

        add(source);
        add(destination);

        return mCommand;
    }

    public String getCommandAsString() {
        return StringUtils.join(getCommand(), " ");
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDestination() {
        return mDestination;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getEnvironment() {
        return mEnvironment;
    }

    public ExcludeSection getExcludeSection() {
        return mExcludeSection;
    }

    public TaskExecuteSection getExecuteSection() {
        return mExecuteSection;
    }

    public String getHistory() {
        return mHistory;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public OptionSection getOptionSection() {
        return mOptionSection;
    }

    public String getSource() {
        return mSource;
    }

    public String getSummaryAsHtml() {
        mSummaryBuilder = new StringBuilder("<h2>").append(getName()).append("</h2>");

        addOptionalToSummary(true, getSource(), Dict.SOURCE.toString());
        addOptionalToSummary(true, getDestination(), Dict.DESTINATION.toString());

        ResourceBundle bundle = BundleHelper.getBundle(TaskExecutePanel.class, "Bundle");

        addOptionalToSummary(mExecuteSection.isBefore(), mExecuteSection.getBeforeCommand(), bundle.getString("TaskExecutePanel.beforePanel.header"));
        if (mExecuteSection.isBefore() && mExecuteSection.isBeforeHaltOnError()) {
            mSummaryBuilder.append(Dict.STOP_ON_ERROR.toString());
        }

        addOptionalToSummary(mExecuteSection.isAfterFailure(), mExecuteSection.getAfterFailureCommand(), bundle.getString("TaskExecutePanel.afterFailurePanel.header"));
        if (mExecuteSection.isAfterFailure() && mExecuteSection.isAfterFailureHaltOnError()) {
            mSummaryBuilder.append(Dict.STOP_ON_ERROR.toString());
        }

        addOptionalToSummary(mExecuteSection.isAfterSuccess(), mExecuteSection.getAfterSuccessCommand(), bundle.getString("TaskExecutePanel.afterSuccessPanel.header"));
        if (mExecuteSection.isAfterSuccess() && mExecuteSection.isAfterSuccessHaltOnError()) {
            mSummaryBuilder.append(Dict.STOP_ON_ERROR.toString());
        }

        addOptionalToSummary(mExecuteSection.isAfter(), mExecuteSection.getAfterCommand(), bundle.getString("TaskExecutePanel.afterPanel.header"));
        if (mExecuteSection.isAfter() && mExecuteSection.isAfterHaltOnError()) {
            mSummaryBuilder.append(Dict.STOP_ON_ERROR.toString());
        }

        if (mExecuteSection.isJobHaltOnError()) {
            mSummaryBuilder.append("<p>").append(bundle.getString("TaskExecutePanel.jobHaltOnErrorCheckBox.text")).append("</p>");
        }

        mSummaryBuilder.append("<h2>rsync</h2>").append(getCommandAsString());

        return mSummaryBuilder.toString();
    }

    public boolean isNoAdditionalDir() {
        return mNoAdditionalDir;
    }

    public boolean isValid() {
        return !getName().isEmpty();
    }

    public void setDescription(String comment) {
        mDescription = comment;
    }

    public void setDestination(String destination) {
        mDestination = destination;
    }

    public void setDetails(String string) {
        mDetails = string;
    }

    public void setEnvironment(String environment) {
        mEnvironment = environment;
    }

    public void setHistory(String history) {
        mHistory = history == null ? "" : history;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setNoAdditionalDir(boolean value) {
        mNoAdditionalDir = value;
    }

    public void setSource(String source) {
        mSource = source;
    }

    @Override
    public String toString() {
        String description = StringUtils.isBlank(mDescription) ? "&nbsp;" : mDescription;

        return String.format("<html><b>%s</b><br /><i>%s</i></html>", mName, description);
    }

    private void add(String command) {
        if (!mCommand.contains(command)) {
            mCommand.add(command);
        }
    }

    private void addOptionalToSummary(boolean active, String command, String header) {
        if (active) {
            mSummaryBuilder.append(String.format("<p><b>%s</b><br /><i>%s</i></p>", header, command));
        }
    }
}
