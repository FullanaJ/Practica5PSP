package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Downloader downloader = new Downloader();
        while (true) {
            downloader.capturaURL(pideURL());
        }
    }
    public static String pideURL() throws IOException {
        System.out.print("Introduce la URL del archivo a descargar: ");
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
