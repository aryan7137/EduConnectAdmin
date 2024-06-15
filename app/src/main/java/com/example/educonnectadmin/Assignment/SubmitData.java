package com.example.educonnectadmin.Assignment;

public class SubmitData {
    String name,PdfUrl,key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SubmitData() {
    }

    public SubmitData(String name, String pdfUrl, String key) {
        this.name = name;
        PdfUrl = pdfUrl;
        this.key = key;
    }
}
