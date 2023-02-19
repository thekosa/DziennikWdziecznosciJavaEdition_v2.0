package pl.kosieradzki.dw;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Entry {
    private List<String> collection = new ArrayList<>();
    private final Date date = new Date();

    public String getEntry() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.getDate()).append("\n");
        for (String string : collection) {
            stringBuilder.append(" - ").append(string).append("\n");
        }
        return stringBuilder.toString();
    }

    public void setDate(LocalDate date) {
        this.date.setDate(date);
    }

    public void setCollection(List<String> collection) {
        this.collection = collection;
    }

    public List<String> getCollection() {
        return collection;
    }

    public void addThankfulness(String thankfulness) {
        collection.add(thankfulness);
    }

    public String getThankfulness(int index) {
        if (index >= collection.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return collection.get(index);
    }
}