package modelos;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.util.Map;

public class ConversionDivisas {


    /*
     Metodo que hace uso de la api Par XXX/XXX/Amount .
     Convierte de una divisa origen a una destino y se pasa la cantidad / monto a convertir

     */

    public void conversionPar(String divisaOrigen , String divisaDestino , double valor)
    {
        //El valor convertido a la divisa destino
        double valorConversion;

        //Url de la api
        String url="https://v6.exchangerate-api.com/v6/3aaea947d4bfce2e5965bf70/pair/"+divisaOrigen+"/"+divisaDestino+"/"+valor;


        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();


        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request= HttpRequest.newBuilder(URI.create(url)).build();

            HttpResponse<String>  response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCodigo= response.statusCode();

            //Recuperdando el json de la respuesta
            String Json = response.body();

            //System.out.println(Json);

            //Pasando el Json a un records
            RecordPairAmount recordConversionPair = gson.fromJson(Json, RecordPairAmount.class);

            //Si la solicitud es exitosa
            if(statusCodigo==200) {



                System.out.println("========================================================================================================================================");
                System.out.println("La cantidad de " + valor + " " + divisaOrigen + " convertidos son " + recordConversionPair.conversion_result() + " " + divisaDestino

                        + " - Ratio de Conversion: " + recordConversionPair.conversion_rate() + ", ultima actualizacion: " + recordConversionPair.time_last_update_utc());
                System.out.println("=========================================================================================================================================");

            } else if (statusCodigo==404) {

                System.out.println(" Recurso no encontrado. La conversion no se pudo realizar . Verifique si los datos ingresados son correctos.");
                System.out.println(Json);


            }
            else {


                System.out.println("Codigo de Respuestas inesperado "+statusCodigo);
                System.out.println(Json);


            }


        } catch (HttpTimeoutException e) {
            // Captura si la solicitud supera el tiempo de espera
            System.out.println("La solicitud superó el tiempo de espera: " + e.getMessage());
        } catch (IOException e) {
            // Captura errores relacionados con I/O, como problemas de red
            System.out.println("Error de Red o entrada/salida: " + e.getMessage());
        } catch (InterruptedException e) {
            // Captura si el hilo es interrumpido
            System.out.println("La solicitud fue interrumpida: " + e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otra excepción
            System.out.println("Ocurrió un error: " + e.getMessage());
        }



    }


    //Metodo que muestra todas los rations de conversion dado una divisa de origen . Hace uso de la api standar.

    public void verTasasConversion(String divisaOrigen)
    {

        //Url de la api
        String url="https://v6.exchangerate-api.com/v6/3aaea947d4bfce2e5965bf70/latest/"+divisaOrigen;


        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();


        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request= HttpRequest.newBuilder(URI.create(url)).build();

            HttpResponse<String>  response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCodigo= response.statusCode();

            //Recuperdando el json de la respuesta
            String Json = response.body();

            //System.out.println(Json);

            //Pasando el Json a un records

            RecordAll  recordTasas= gson.fromJson(Json, RecordAll.class);

            //Si la solicitud es exitosa
            if(statusCodigo==200) {

                System.out.println("Divisa origien: "+divisaOrigen);
                System.out.println("Lista de Rations/tasas de converion de otros paises");
                System.out.println("============================================================================");
                for(Map.Entry<String,Double> ration : recordTasas.conversion_rates().entrySet())
                {
                    System.out.println("Divisa :"+ ration.getKey()+ " - Ratio Conversion :"+ration.getValue());


                }

                System.out.println("============================================================================");


            } else if (statusCodigo==404) {

                System.out.println(" Recurso no encontrado. Verifique si los datos ingresados son correctos.");
                System.out.println(Json);


            }
            else {


                System.out.println("Codigo de Respuestas inesperado "+statusCodigo);
                System.out.println(Json);


            }


        } catch (HttpTimeoutException e) {
            // Captura si la solicitud supera el tiempo de espera
            System.out.println("La solicitud superó el tiempo de espera: " + e.getMessage());
        } catch (IOException e) {
            // Captura errores relacionados con I/O, como problemas de red
            System.out.println("Error de Red o entrada/salida: " + e.getMessage());
        } catch (InterruptedException e) {
            // Captura si el hilo es interrumpido
            System.out.println("La solicitud fue interrumpida: " + e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otra excepción
            System.out.println("Ocurrió un error: " + e.getMessage());
        }




    }


}
