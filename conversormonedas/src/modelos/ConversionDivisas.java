package modelos;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.util.*;

public class ConversionDivisas {


    /*
     Metodo que hace uso de la api Par XXX/XXX/Amount .
     Convierte de una divisa origen a una destino y se pasa la cantidad / monto a convertir

     */

    private List<Conversion> listaConversiones;

    //Constructor
    public ConversionDivisas() {

        this.listaConversiones=new ArrayList<>();


    }

    public void mostrarHistorialReciente()
    {

        /*Si la lista a mostrar esta vacio se intenta leer el ultimo historial almacenado en el historial.txt
        util por si se selecciona la opcion de mostrar historial reciente sin haber hecho una conversion aun
        */
        if(this.listaConversiones.isEmpty())
        {
           try {
               File archivoLectura = new File("historial.txt");

               Scanner lectura = new Scanner(archivoLectura);


               while (lectura.hasNext())
               {
                   String linea= lectura.nextLine();
                   System.out.println(linea);

               }



           }catch (FileNotFoundException e)
           {
               System.out.println("Archivo no encontrado.");
               e.printStackTrace();

           }


        }else {

            /*Ordena la lista de conversiones reciente en forma descendente.
              Se muestran las conversiones recientes primeros
            */

            this.listaConversiones.sort(Comparator.comparing(Conversion::getFechaHoraConversion).reversed());
            System.out.println(this.listaConversiones);

        }

    }

    public  void guardarHistorialReciente() {

        if(!this.listaConversiones.isEmpty()) {
            Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

             /*Ordena la lista de conversiones reciente en forma descendente.
              Se muestran las conversiones recientes primeros
            */

            this.listaConversiones.sort(Comparator.comparing(Conversion::getFechaHoraConversion).reversed());

            try {

                FileWriter escritura = new FileWriter("historial.txt");
                escritura.write(this.listaConversiones.toString());
                escritura.close();


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }


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


                Conversion nuevaConverion= new Conversion(recordConversionPair,valor);
                this.listaConversiones.add(nuevaConverion);


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

    //Muestra el valor del dolar blue la ultima actualisacion o el historico de un dia especifico
    public void dolarBlueVerCotisacion(boolean historico , String fecha)
    {
        String url;

        //Url de la api
        if ((historico))
        {
            System.out.println("Precio Historico Dolar Blue, Fecha:"+fecha);
            System.out.println("============================================================================");
            url="https://api.bluelytics.com.ar/v2/historical?day="+fecha;

        }
        else {
             url = "https://api.bluelytics.com.ar/v2/latest";

        }

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

            RecordBlue recordCotisacion= gson.fromJson(Json, RecordBlue.class);

            //Si la solicitud es exitosa
            if(statusCodigo==200) {

                System.out.println("Precios Dolar Blue (Argentina) - Ultima cotisacion:"+ recordCotisacion.last_update());
                System.out.println("Referencias: value_avg (Precio promedio) - value_sell (Precio venta) - value_buy (Precio compra)");
                System.out.println("============================================================================");
                for(Map.Entry<String,Double> cotisacion : recordCotisacion.blue().entrySet())
                {
                    System.out.println(" Condicion :"+ cotisacion.getKey()+ " - Precio:"+cotisacion.getValue());


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
