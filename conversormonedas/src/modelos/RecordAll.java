package modelos;

import java.util.Map;
/*

Records para ver todas las tasas de conversiones , segun una divisa de origen dada

 */
public record RecordAll(String result, String base_code, String target_code, String time_last_update_utc , Map<String, Double> conversion_rates , double conversion_result) {
}
