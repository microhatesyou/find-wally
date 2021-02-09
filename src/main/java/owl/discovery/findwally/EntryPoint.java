package owl.discovery.findwally;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command
public class EntryPoint {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new ComparatorCommand()).execute(args);
        System.exit(exitCode);
    }
}
