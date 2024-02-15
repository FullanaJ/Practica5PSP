package Ejercicio1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Downloader {
    private ObservableList<String> list = FXCollections.observableArrayList();

    public Downloader() {
        list.addListener((javafx.collections.ListChangeListener.Change<? extends String> change) -> {
            System.out.println("Se ha iniciado la descarga del archivo URL: " + change.getList().get(0));
        });
    }

    public void capturaURL(String s) {
        list.add(s);
    }
}
