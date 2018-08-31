package avdw.java.captain.sonar.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteBoardJsonSchema {
    private final Path schemaPath;

    public static void main(String[] args) throws IOException {
        Guice.createInjector(new BoardModule()).getInstance(WriteBoardJsonSchema.class).execute();
    }

    @Inject
    public WriteBoardJsonSchema(@Named("schema-path") Path schemaPath) {
        this.schemaPath = schemaPath;
    }

    private void execute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
        JsonSchema schema = schemaGen.generateSchema(Board.class);
        String contents = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);

        System.out.println(contents);
        try (
                BufferedWriter writer = Files.newBufferedWriter(schemaPath)) {
            writer.write(contents);
        }
    }
}
