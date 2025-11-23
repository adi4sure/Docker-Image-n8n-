import java.io.FileWriter;
import java.io.IOException;

public class DockerImageGenerator {

    public static void main(String[] args) {
        // Define the output file name
        String fileName = "Dockerfile";

        // The content of the Dockerfile for n8n on Render
        String dockerContent = 
            "FROM n8n/n8n:latest\n" +
            "\n" +
            "# Switch to root to install system dependencies\n" +
            "USER root\n" +
            "\n" +
            "# Install Python3 to allow Python scripts in n8n workflows\n" +
            "RUN apk add --update --no-cache python3 py3-pip\n" +
            "\n" +
            "# Security: Switch back to the non-root 'node' user\n" +
            "USER node\n" +
            "\n" +
            "# Expose the internal port\n" +
            "EXPOSE 5678\n" +
            "\n" +
            "# specific command to start the n8n process\n" +
            "CMD [\"n8n\"]";

        // Logic to write the file to disk
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(dockerContent);
            System.out.println("SUCCESS: Dockerfile generated successfully for n8n deployment.");
            System.out.println("Target Platform: Render PaaS");
        } catch (IOException e) {
            System.err.println("ERROR: Could not generate Dockerfile.");
            e.printStackTrace();
        }
    }
}
