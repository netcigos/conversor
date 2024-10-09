import modelos.ConversionDivisas;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner((System.in));

        String menu = """
                
                Conversor de Divisas Version 1.0 by Cigos Emmanuel
                **************************************************
                
                1- Dolar Americano USD -> Peso Argentino ARS (Oficial BCRA)
                2- Pesos Argentino ARS (Oficial BCRA) -> Dolar Americano USD
                3- Dolar Americano USD - > Real Brasilero BRL
                4- Real Brasilero - > Dolar Americano USD
                5- Dolar Americano USD -> Guarani Paraguay PYG
                6- Guarani Paraguay PYG -> Dolar Americano USD
                7- Dolar USD -> Euro EUR
                8- Euro EUR -> Dolar USD
                9- Ingrese otro par Divisas ORIGEN/DESTINO Disponibles
                10-Ver Historial recientes de conversiones 
                11-Ingrese un Divisa Origen y vea todas las tasas de conversion de 161 Paises Disponibles
                12- Ver ultima Cotizacion Dolar Blue Argentina
                13- Ver Dolar Blue valor historico de un dia especifico   
                14- Salir
                *************************************************  
                Ingrese una opcion del menu:             
                """;


        int opcion=0;
        double cantidadDivisa;

        ConversionDivisas conversion=new ConversionDivisas();

        while (opcion!=14)
        {

            try {

                System.out.println(menu);

                opcion = teclado.nextInt();


                String divisaOrigen;
                switch (opcion) {

                    case 1:
                        System.out.println("Ingrese la cantidad USD a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("USD", "ARS", cantidadDivisa);
                        break;

                    case 2:
                        System.out.println("Ingrese la cantidad ARS a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("ARS", "USD", cantidadDivisa);

                        break;

                    case 3:
                        System.out.println("Ingrese la cantidad USD a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("USD", "BRL", cantidadDivisa);

                        break;
                    case 4:
                        System.out.println("Ingrese la cantidad BRL a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("BRL", "USD", cantidadDivisa);

                        break;
                    case 5:
                        System.out.println("Ingrese la cantidad USD a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("USD", "PYG", cantidadDivisa);

                        break;
                    case 6:
                        System.out.println("Ingrese la cantidad PYG a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("PYG", "USD", cantidadDivisa);

                        break;
                    case 7:
                        System.out.println("Ingrese la cantidad USD a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("USD", "EUR", cantidadDivisa);

                        break;
                    case 8:
                        System.out.println("Ingrese la cantidad EUR a convertir");
                        cantidadDivisa = teclado.nextDouble();
                        conversion.conversionPar("EUR", "USD", cantidadDivisa);

                        break;
                    case 9:
                        String listaDivisas= """
                                Listado de Divisas Disponibles
                                ===========================================================================                                
                                Codigo            Divisa                                Pais
                                AED             UAE Dirham	                        United Arab Emirates
                                AFN	            Afghan Afghani	                    Afghanistan
                                ALL	            Albanian Lek	                    Albania
                                AMD	            Armenian Dram                       Armenia
                                ANG	            Netherlands Antillian Guilder	    Netherlands Antilles
                                AOA	            Angolan Kwanza	                    Angola
                                ARS             Argentine Peso                    	Argentina
                                AUD	            Australian Dollar	                Australia
                                AWG	            Aruban Florin                       Aruba
                                AZN	            Azerbaijani Manat	                Azerbaijan
                                BAM	            Bosnia and Herzegovina Mark    	    Bosnia and Herzegovina
                                BBD	            Barbados Dollar	                    Barbados
                                BDT	            Bangladeshi Taka	                Bangladesh
                                BGN	            Bulgarian Lev	                    Bulgaria
                                BHD	            Bahraini Dinar	                    Bahrain
                                BIF	            Burundian Franc	                    Burundi
                                BMD	            Bermudian Dollar	                Bermuda
                                BND	            Brunei Dollar	                    Brunei
                                BOB	            Bolivian Boliviano	                Bolivia
                                BRL	            Brazilian Real	                    Brazil
                                BSD	            Bahamian Dollar	                    Bahamas
                                BTN	            Bhutanese Ngultrum	                Bhutan
                                BWP	            Botswana Pula	                    Botswana
                                BYN	            Belarusian Ruble	                Belarus
                                BZD	            Belize Dollar	                    Belize
                                CAD	            Canadian Dollar	                    Canada
                                CDF	            Congolese Franc	                    Democratic Republic of the Congo
                                CHF         	Swiss Franc	                        Switzerland
                                CLP	            Chilean Peso	                    Chile
                                CNY	            Chinese Renminbi                	China
                                COP	            Colombian Peso	                    Colombia
                                CRC	            Costa Rican Colon               	Costa Rica
                                CUP	            Cuban Peso	                        Cuba
                                CVE	            Cape Verdean Escudo	                Cape Verde
                                CZK	            Czech Koruna                    	Czech Republic
                                DJF	            Djiboutian Franc                	Djibouti
                                DKK         	Danish Krone	                    Denmark
                                DOP	            Dominican Peso	                    Dominican Republic
                                DZD         	Algerian Dinar                  	Algeria
                                EGP	            Egyptian Pound	                    Egypt
                                ERN	            Eritrean Nakfa	                    Eritrea
                                ETB	            Ethiopian Birr	                    Ethiopia
                                EUR	            Euro	                            European Union
                                FJD         	Fiji Dollar                     	Fiji
                                FKP         	Falkland Islands Pound	            Falkland Islands
                                FOK         	Faroese Króna	                    Faroe Islands
                                GBP         	Pound Sterling	                    United Kingdom
                                GEL         	Georgian Lari	                    Georgia
                                GGP         	Guernsey Pound                  	Guernsey
                                GHS         	Ghanaian Cedi                   	Ghana
                                GIP         	Gibraltar Pound                 	Gibraltar
                                GMD         	Gambian Dalasi	                    The Gambia
                                GNF         	Guinean Franc	                    Guinea
                                GTQ         	Guatemalan Quetzal              	Guatemala
                                GYD         	Guyanese Dollar                 	Guyana
                                HKD         	Hong Kong Dollar                	Hong Kong
                                HNL	            Honduran Lempira                	Honduras
                                HRK         	Croatian Kuna                   	Croatia
                                HTG          	Haitian Gourde                  	Haiti
                                HUF         	Hungarian Forint                	Hungary
                                IDR         	Indonesian Rupiah               	Indonesia
                                ILS         	Israeli New Shekel              	Israel
                                IMP         	Manx Pound	                        Isle of Man
                                INR         	Indian Rupee                        India
                                IQD         	Iraqi Dinar                         Iraq
                                IRR         	Iranian Rial                    	Iran
                                ISK         	Icelandic Króna	                    Iceland
                                JEP         	Jersey Pound	                    Jersey
                                JMD	            Jamaican Dollar                 	Jamaica
                                JOD	            Jordanian Dinar	                    Jordan
                                JPY         	Japanese Yen	                    Japan
                                KES         	Kenyan Shilling	                    Kenya
                                KGS         	Kyrgyzstani Som	                    Kyrgyzstan
                                KHR         	Cambodian Riel                  	Cambodia
                                KID         	Kiribati Dollar	                    Kiribati
                                KMF         	Comorian Franc	                    Comoros
                                KRW	            South Korean Won	                South Korea
                                KWD         	Kuwaiti Dinar                   	Kuwait
                                KYD         	Cayman Islands Dollar	            Cayman Islands
                                KZT         	Kazakhstani Tenge               	Kazakhstan
                                LAK         	Lao Kip                             Laos
                                LBP         	Lebanese Pound                  	Lebanon
                                LKR         	Sri Lanka Rupee                 	Sri Lanka
                                LRD         	Liberian Dollar	                    Liberia
                                LSL         	Lesotho Loti                        Lesotho
                                LYD         	Libyan Dinar                    	Libya
                                MAD         	Moroccan Dirham                 	Morocco
                                MDL         	Moldovan Leu	                    Moldova
                                MGA         	Malagasy Ariary                 	Madagascar
                                MKD         	Macedonian Denar                	North Macedonia
                                MMK         	Burmese Kyat	                    Myanmar
                                MNT         	Mongolian Tögrög                	Mongolia
                                MOP         	Macanese Pataca	                    Macau
                                MRU         	Mauritanian Ouguiya             	Mauritania
                                MUR         	Mauritian Rupee                 	Mauritius
                                MVR         	Maldivian Rufiyaa               	Maldives
                                MWK         	Malawian Kwacha                 	Malawi
                                MXN         	Mexican Peso	                    Mexico
                                MYR         	Malaysian Ringgit               	Malaysia
                                MZN         	Mozambican Metical              	Mozambique
                                NAD         	Namibian Dollar                 	Namibia
                                NGN         	Nigerian Naira                  	Nigeria
                                NIO         	Nicaraguan Córdoba              	Nicaragua
                                NOK         	Norwegian Krone                 	Norway
                                NPR         	Nepalese Rupee	                    Nepal
                                NZD         	New Zealand Dollar              	New Zealand
                                OMR         	Omani Rial	                        Oman
                                PAB         	Panamanian Balboa               	Panama
                                PEN	            Peruvian Sol	                    Peru
                                PGK         	Papua New Guinean Kina          	Papua New Guinea
                                PHP         	Philippine Peso	                    Philippines
                                PKR         	Pakistani Rupee                 	Pakistan
                                PLN         	Polish Złoty	                    Poland
                                PYG         	Paraguayan Guaraní              	Paraguay
                                QAR         	Qatari Riyal	                    Qatar
                                RON         	Romanian Leu	                    Romania
                                RSD         	Serbian Dinar                   	Serbia
                                RUB         	Russian Ruble                   	Russia
                                RWF         	Rwandan Franc	                    Rwanda
                                SAR         	Saudi Riyal	                        Saudi Arabia
                                SBD         	Solomon Islands Dollar          	Solomon Islands
                                SCR         	Seychellois Rupee	                Seychelles
                                SDG         	Sudanese Pound	                    Sudan
                                SEK         	Swedish Krona	                    Sweden
                                SGD         	Singapore Dollar	                Singapore
                                SHP         	Saint Helena Pound	                Saint Helena
                                SLE         	Sierra Leonean Leone            	Sierra Leone
                                SOS         	Somali Shilling                 	Somalia
                                SRD	            Surinamese Dollar               	Suriname
                                SSP         	South Sudanese Pound            	South Sudan
                                STN         	São Tomé and Príncipe Dobra     	São Tomé and Príncipe
                                SYP	            Syrian Pound	                    Syria
                                SZL         	Eswatini Lilangeni              	Eswatini
                                THB         	Thai Baht	                        Thailand
                                TJS         	Tajikistani Somoni              	Tajikistan
                                TMT         	Turkmenistan Manat              	Turkmenistan
                                TND         	Tunisian Dinar                  	Tunisia
                                TOP         	Tongan Paʻanga                  	Tonga
                                TRY         	Turkish Lira	                    Turkey
                                TTD         	Trinidad and Tobago Dollar          Trinidad and Tobago
                                TVD         	Tuvaluan Dollar                 	Tuvalu
                                TWD         	New Taiwan Dollar               	Taiwan
                                TZS            	Tanzanian Shilling              	Tanzania
                                UAH         	Ukrainian Hryvnia               	Ukraine
                                UGX         	Ugandan Shilling	                Uganda
                                USD         	United States Dollar            	United States
                                UYU         	Uruguayan Peso	                    Uruguay
                                UZS	            Uzbekistani So'm                	Uzbekistan
                                VES         	Venezuelan Bolívar Soberano	        Venezuela
                                VND         	Vietnamese Đồng                 	Vietnam
                                VUV         	Vanuatu Vatu	                    Vanuatu
                                WST         	Samoan Tālā                     	Samoa
                                XAF         	Central African CFA Franc       	CEMAC
                                XCD         	East Caribbean Dollar	            Organisation of Eastern Caribbean States
                                XDR         	Special Drawing Rights	            International Monetary Fund
                                XOF         	West African CFA franc          	CFA
                                XPF         	CFP Franc	                        Collectivités d'Outre-Mer
                                YER         	Yemeni Rial	                        Yemen
                                ZAR         	South African Rand              	South Africa
                                ZMW         	Zambian Kwacha	                    Zambia
                                ZWL         	Zimbabwean Dollar               	Zimbabwe
                                ===========================================================================================
                                Ingrese el codigo de tres letras - Ejemplo pesos argentino ARS
                                """;

                        teclado.nextLine();
                        System.out.println(listaDivisas);

                        System.out.println("Elija una Divisa XXX origen a convertir: ");
                        divisaOrigen= teclado.nextLine();


                        System.out.println("Elija una Divisa XXX destino para el resultado de conversion:");
                        String divisaDestino= teclado.nextLine();

                        System.out.println("Ingrese la cantidad/monto a convertir");
                        cantidadDivisa = teclado.nextDouble();


                         conversion.conversionPar(divisaOrigen,divisaDestino,cantidadDivisa);

                        break;
                    case 10:

                        System.out.println("===============================================================================");
                        System.out.println("Historial reciente de las ultimas conversiones:");
                        conversion.mostrarHistorialReciente();
                        System.out.println("===============================================================================");


                        break;

                        case 11:
                        teclado.nextLine();
                        System.out.println("Elija una Divisa XXX origen para ver las tasas de conversiones de otros paises: ");
                        divisaOrigen= teclado.nextLine();
                        conversion.verTasasConversion(divisaOrigen);

                        break;

                    case 12:
                        conversion.dolarBlueVerCotisacion(false,"");
                        break;

                    case 13:


                    System.out.println("Ingrese la Fecha en formato YYYY-MM-DD , ejemplo 2024-08-10");
                    System.out.println("Ingrese un valor para el  Año:");
                    int ano= teclado.nextInt();
                    System.out.println("Ingrese un valor para el Mes:");
                    int mes= teclado.nextInt();
                    System.out.println("Ingrese un valor para el Dia");
                    int dia= teclado.nextInt();

                     try {
                         LocalDate fecha = LocalDate.of(ano, mes, dia);
                         DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                         String fechaFormateada = fecha.format(formato);

                         conversion.dolarBlueVerCotisacion(true,fechaFormateada);

                     }
                     catch (DateTimeException e)
                     {
                         System.out.println("Error al ingresar la fecha , verifique formato "+e.getMessage());

                     }
                    break;


                    case 14:

                        conversion.guardarHistorialReciente();
                        System.out.println("Fin del Programa");
                        break;

                    default:
                        System.out.println("Numero de opcion ingresado incorrecto");
                        break;


                }

            }catch (InputMismatchException e)
            {

                System.out.println("Error: Ingrese un numero de entrada valido");
                teclado.next();

            }


        }






    }
}
