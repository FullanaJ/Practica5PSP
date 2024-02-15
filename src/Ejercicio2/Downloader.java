package Ejercicio2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class Downloader {
    private ObservableList<String> list = FXCollections.observableArrayList();
    Path path = Path.of("src");
    public Downloader() {
        list.addListener((javafx.collections.ListChangeListener.Change<? extends String> change) -> {
            descargaURL(change.getList().get(0));
        });
    }

    public void capturaURL(String s) {
        list.add(s);
    }

    public void descargaURL(String url){

        CompletableFuture<HttpResponse<String>> completableFuture = CompletableFuture.supplyAsync(
                () ->{
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET() // GET is default
                            .build();
                    try {
                        return client.send(request, HttpResponse.BodyHandlers.ofString());
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).thenApply(
                (response) -> {
                    return response;
                }
        ).thenApply(
                (response) -> {
                    String name = url.split("/")[2].split("\\.")[1];

                    if (!Files.exists(Path.of(path.toString(),name+".html" ))) {
                        try {
                            Files.createFile(Path.of(path.toString(),name+".html" ));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Files.writeString(Path.of(path.toString(),name+".html"), response.body());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }

        );
        completableFuture.join();
    }
}
