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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ScriptExecutor implements ApplicationListener<ContextRefreshedEvent> {

    private final boolean scriptsGenericEnabled;
    private final boolean scriptsTestEnabled;
    private final String scriptsPath;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScriptExecutor(
            @Value("${scripts.generic.enabled}") boolean scriptsGenericEnabled,
            @Value("${scripts.test.enabled}") boolean scriptsTestEnabled,
            @Value("${scripts.path}") String scriptsPath,
            JdbcTemplate jdbcTemplate) {
        this.scriptsGenericEnabled = scriptsGenericEnabled;
        this.scriptsTestEnabled = scriptsTestEnabled;
        this.scriptsPath = scriptsPath;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!scriptsGenericEnabled) {
            return;
        }

        // Defina a lista de scripts na sequência desejada
        List<String> scripts = new ArrayList<>(Arrays.asList(
                "V1__insert-role.sql",
                "V2__insert-type_menu.sql",
                "V3__insert-menu.sql",
                "V4__insert-role-menu.sql",
                "V5__insert-vehicle-brand.sql",
                "V6__insert-vehicle-category.sql",
                "V7__insert-vehicle-color.sql",
                "V8__insert-vehicle-fuel-type.sql",
                "V9__insert-vehicle-transmission.sql",
                "V10__insert-address-type.sql",
                "V11__insert-country.sql",
                "V12__insert-payment-method.sql",
                "V13__insert-payment-status.sql",

                // State's
                "state/V1__insert-state-country-brasil.sql",

                // City's
                "city/V1__insert-city-state-sao-paulo-brasil.sql",

                // Vehicle's
                "vehicle/V1__insert-vehicle-chevrolet.sql",
                "vehicle/V1__insert-vehicle-fiat.sql",
                "vehicle/V1__insert-vehicle-honda.sql",
                "vehicle/V1__insert-vehicle-hyundai.sql",
                "vehicle/V1__insert-vehicle-nissan.sql",
                "vehicle/V1__insert-vehicle-toyota.sql",
                "vehicle/V1__insert-vehicle-volkswagen.sql",

                // Vehicle Model's - Chevrolet
                "vehicle-model/chevrolet/onix/V1__insert-vehicle-model-onix.sql",
                "vehicle-model/chevrolet/s10/V1__insert-vehicle-model-s10.sql",

                // Vehicle Model's - Fiat
                "vehicle-model/fiat/palio/V1__insert-vehicle-model-palio.sql",

                // Vehicle Model's - Honda
                "vehicle-model/honda/accord/V1__insert-vehicle-model-accord.sql",
                "vehicle-model/honda/civic/V1__insert-vehicle-model-civic.sql",
                "vehicle-model/honda/hrv/V1__insert-vehicle-model-hrv.sql",

                // Vehicle Model's - Hyundai
                "vehicle-model/hyundai/hb20/V1__insert-vehicle-model-hb20.sql",

                // Vehicle Model's - Fiat
                "vehicle-model/nissan/kicks/V1__insert-vehicle-model-kicks.sql",

                // Vehicle Model's - Toyota
                "vehicle-model/toyota/corolla/V1__insert-vehicle-model-corolla.sql",
                "vehicle-model/toyota/corolla-cross/V1__insert-vehicle-model-corolla-cross.sql",

                // Vehicle Model's - Volkswagen
                "vehicle-model/volkswagen/fusca/V1__insert-vehicle-model-fusca.sql",
                "vehicle-model/volkswagen/jetta/V1__insert-vehicle-model-jetta.sql"
        ));

        if (scriptsTestEnabled) {
            scripts.add("test/customer/V1__insert-customer.sql");
            scripts.add("test/customer-vehicle/V1__insert-customer-vehicle.sql");
            scripts.add("test/address/V1__insert-address.sql");
            scripts.add("test/customer-vehicle-address/V1__insert-customer-vehicle-address.sql");
            scripts.add("test/address-address-type/V1__insert-address-address-type.sql");
        }

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