package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {

    LocalDateTime fechaHoraConversion;
    String divisaOrigen;
    String divisaDestino;
    Double rationConversion;
    Double cantidad;
    Double resultadoConversion;

    public Conversion(RecordPairAmount recordConversion, double cantidad)
    {
        this.fechaHoraConversion=LocalDateTime.now();
        this.divisaOrigen=recordConversion.base_code();
        this.divisaDestino=recordConversion.target_code();
        this.rationConversion=recordConversion.conversion_rate();
        this.cantidad=cantidad;
        this.resultadoConversion=recordConversion.conversion_result();

    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = this.fechaHoraConversion.format(formatter);
        return "Fecha y Hora: "+formattedDateTime
             +" - Divisa Origen:" +this.divisaOrigen +" Cantidad: "+this.cantidad  +" Divisa Destino: "+this.divisaDestino
             +" Resultado: "+this.resultadoConversion+ " - Ratio: "+this.rationConversion +"\n";


    }
}
