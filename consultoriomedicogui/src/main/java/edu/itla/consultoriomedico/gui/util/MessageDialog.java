package edu.itla.consultoriomedico.gui.util;

import javafx.scene.control.Alert;

public final class MessageDialog {

    private static Alert alert;

    public static Alert showPopup(String title, String message, Alert.AlertType typeAlert) {
        alert = new Alert(typeAlert);
        alert.setTitle(title);
        alert.setHeaderText(message);
        return alert;
    }
}
