package owl.discovery.findwally;

import japicmp.cmp.JApiCmpArchive;
import japicmp.cmp.JarArchiveComparator;
import japicmp.cmp.JarArchiveComparatorOptions;
import japicmp.model.JApiClass;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

@Command(name = "comparator", mixinStandardHelpOptions = true, version = "comparator 1.0",
        description = "Compare left and right file and display differences")
@Slf4j
public class ComparatorCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"-left", "-l"}, description = "The left file to compare (jar or ear)", required = true)
    protected Path left;
    @CommandLine.Option(names = {"-right", "-r"}, description = "The right file to compare (jar or ear)", required = true)
    protected Path right;

    @Override
    public Integer call() {
        log.info("comparing <{}> to <{}>", left.toAbsolutePath(), right.toAbsolutePath());
        if (!isInputValid())
            return 1;


        JarArchiveComparatorOptions comparatorOptions = new JarArchiveComparatorOptions();
        JarArchiveComparator jarArchiveComparator = new JarArchiveComparator(comparatorOptions);
        List<JApiClass> jApiClasses = jarArchiveComparator.compare(new JApiCmpArchive(left.toFile(), "left"), new JApiCmpArchive(right.toFile(), "right"));
        log.info("result: {}", jApiClasses);

        return 0;
    }


    private boolean isInputValid() {
        if (!left.toFile().exists()){
            log.error("left file does not exists {}", left);
            return false;
        }
        if (!left.toString().toLowerCase(Locale.ROOT).endsWith(".jar")) {
            log.error("invalid left: only jar file are supported {}", left);
            return false;
        }
        if (!right.toFile().exists()){
            log.error("right file does not exists {}", right);
            return false;
        }
        if (!right.toString().toLowerCase(Locale.ROOT).endsWith(".jar")) {
            log.error("invalid right: only jar file are supported {}", right);
            return false;
        }
        return true;
    }


}
