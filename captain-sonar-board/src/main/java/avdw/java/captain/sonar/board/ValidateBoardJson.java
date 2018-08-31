package avdw.java.captain.sonar.board;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ValidateBoardJson {
    private Path basePath;
    private final Path schemaPath;

    public static void main(String[] args) throws IOException, ProcessingException {
        Guice.createInjector(new BoardModule()).getInstance(ValidateBoardJson.class).execute();
    }

    @Inject
    public ValidateBoardJson(@Named("base-path") Path basePath, @Named("schema-path") Path schemaPath) {
        this.basePath = basePath;
        this.schemaPath = schemaPath;
    }

    private void execute() throws IOException, ProcessingException {
        JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(String.valueOf(schemaPath.toUri()));
        try (Stream<Path> paths = Files.walk(basePath)) {
            paths.filter(Files::isRegularFile).filter(path -> !path.equals(schemaPath))
                    .forEach(boardPath -> {
                        try {
                            final JsonNode json = JsonLoader.fromPath(String.format("%s", boardPath));
                            final ProcessingReport report = schema.validate(json);
                            if (report.isSuccess()) {
                                System.out.println(String.format("%s => VALID", boardPath.getName(boardPath.getNameCount() - 1)));
                            } else {
                                System.out.println(String.format("%s => %s", boardPath.getName(boardPath.getNameCount() - 1), report));
                            }
                        } catch (IOException | ProcessingException e) {
                            System.out.println(e.getMessage());
                        }
                    });
        }
    }
}
