/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Model.RendezVous;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;

/**
 *
 * @author asus
 * @param <S>
 * @param <T>
 */
public class DatePickerCell<S, T> extends TableCell<RendezVous, Date> {

    private DatePicker datePicker;
    private ObservableList<RendezVous> birthdayData;

    /**
     * Constructor.
     *
     * @param listBirthdays
     */
    public DatePickerCell(ObservableList<RendezVous> listBirthdays) {
        super();

        this.birthdayData = listBirthdays;

        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                datePicker.requestFocus();
            }
        });
    }

    /**
     * Override TableCell updateItem method.
     *
     * @param item
     * @param empty
     */
    @Override
    public void updateItem(Date item, boolean empty) {

        super.updateItem(item, empty);

        SimpleDateFormat smp = new SimpleDateFormat("yyyy/MM/dd");

        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }
        if (isEditing()) {
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            setDatepikerDate(smp.format(item));
            setText(smp.format(item));
            setGraphic(this.datePicker);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
        if (empty) {
            setText(null);
            setGraphic(null);
        }
    }

    /**
     *
     * @param dateAsStr
     */
    private void setDatepikerDate(String dateAsStr) {

        LocalDate ld = null;
        int jour, mois, annee;
        int jour1, mois1, annee1;

        System.out.println("Date in " + dateAsStr);
        String reverse = "";

        for (int i = dateAsStr.length() - 1; i >= 0; i--) {
            reverse = reverse + dateAsStr.charAt(i);
        }
        System.out.println("Date re" + reverse);
        jour1 = mois1 = annee1 = 0;

        jour = mois = annee = 0;
        try {
            /* annee   = Integer.parseInt(dateAsStr.substring(0,4));
                        System.out.println("" + annee);

            mois = Integer.parseInt(dateAsStr.substring(5, 7));
                        System.out.println("MONTH " + mois);

            jour = Integer.parseInt(dateAsStr.substring(8, dateAsStr.length()));
                        System.out.println("YER " + jour);*/
            //jour1 = Integer.parseInt(dateAsStr.substring(0, 2));
                        jour1 = 10;


            //mois = Integer.parseInt(dateAsStr.substring(3, 5));
                        mois1 =12 ;


            //annee1 = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
                        annee1 =1992;

        } catch (NumberFormatException e) {
            System.out.println("setDatepikerDate / unexpected error " + e);
        }

        ld = LocalDate.of(annee1, mois1, jour1);
        datePicker.setValue(ld);
    }

    /**
     * This method create a textfield with key and focus events.
     */
    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("yyyy/MM/dd");
        datePicker.setEditable(true);

        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                int index = getIndex();

                SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
                cal.set(Calendar.MONTH, date.getMonthValue() - 1);
                cal.set(Calendar.YEAR, date.getYear());

                setText(smp.format(cal.getTime()));
                commitEdit(cal.getTime());

                if (null != getBirthdayData()) {
                    getBirthdayData().get(index).setDateRendezVous((java.sql.Date) cal.getTime());
                }
            }
        });

        setAlignment(Pos.CENTER);
    }

    /**
     *
     */
    @Override
    public void startEdit() {
        super.startEdit();
    }

    /**
     * Override TableCell cancelEdit method.
     */
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    /**
     *
     * @return
     */
    public ObservableList<RendezVous> getBirthdayData() {
        return birthdayData;
    }

    /**
     *
     * @param birthdayData
     */
    public void setBirthdayData(ObservableList<RendezVous> birthdayData) {
        this.birthdayData = birthdayData;
    }

    /**
     *
     * @return
     */
    public DatePicker getDatePicker() {
        return datePicker;
    }

    /**
     *
     * @param datePicker
     */
    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

}
