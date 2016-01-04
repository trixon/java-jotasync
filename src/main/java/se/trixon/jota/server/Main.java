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
package se.trixon.jota.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import se.trixon.jota.shared.Jota;
import se.trixon.util.SystemHelper;
import se.trixon.util.Xlog;

/**
 *
 * @author Patrik Karlsson
 */
public class Main {

    private final ResourceBundle mJotaBundle = Jota.getBundle();

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException, IOException {
        System.setProperty("java.rmi.server.hostname", SystemHelper.getHostname());
        Options options = initOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("help")) {
                displayHelp(options);
            } else if (cmd.hasOption("version")) {
                displayVersion();
            } else {
                Server server = new Server(cmd);
            }
        } catch (ParseException ex) {
            Xlog.timedErr(ex.getMessage());
            System.out.println("Try 'jotaserver --help' for more information.");
        }
    }

    private static void displayHelp(Options options) {
        String header = "rsync front end with built in cron\n\n";
        String footer = "\nPlease report issues to patrik@trixon.se";

        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp("jotaserver", header, options, footer, true);
    }

    private static void displayVersion() {
        System.out.println(Jota.getVersionInfo("Server"));
    }

    private static Options initOptions() {
        Option help = new Option("?", "help", false, "print this message");
        Option version = new Option("v", "version", false, "print the version information and exit\n");
        Option port = Option.builder("p").longOpt("port").argName("port").hasArg(true).desc("listen for connection on port [1099]").build();

        Options options = new Options();
        options.addOption(help);
        options.addOption(version);
        options.addOption(port);

        return options;
    }
}