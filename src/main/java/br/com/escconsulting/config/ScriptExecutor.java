package br.com.escconsulting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class ScriptExecutor implements ApplicationListener<ContextRefreshedEvent> {

    private final boolean scriptsEnabled;
    private final String scriptsPath;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScriptExecutor(
            @Value("${scripts.enabled}") boolean scriptsEnabled,
            @Value("${scripts.path}") String scriptsPath,
            JdbcTemplate jdbcTemplate) {
        this.scriptsEnabled = scriptsEnabled;
        this.scriptsPath = scriptsPath;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!scriptsEnabled) {
            return;
        }

        // Defina a lista de scripts na sequência desejada
        List<String> scripts = Arrays.asList(
                "V1__insert-vehicle-brand.sql",
                "V2__insert-vehicle-category.sql"
                // Adicione mais scripts conforme necessário
        );

        // Execute os scripts
        scripts.forEach(this::executeScript);
    }

    private void executeScript(String scriptFileName) {
        // Obter o caminho absoluto do diretório do projeto
        Path projectRoot = Paths.get("").toAbsolutePath();
        // Concatena o caminho relativo do script ao caminho absoluto do projeto
        Path scriptPath = projectRoot.resolve(scriptsPath).resolve(scriptFileName);

        try {
            String sql = Files.readString(scriptPath);
            jdbcTemplate.execute(sql);
            System.out.println("Executado script: " + scriptPath.toString());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o script: " + scriptPath.toString(), e);
        }
    }
}