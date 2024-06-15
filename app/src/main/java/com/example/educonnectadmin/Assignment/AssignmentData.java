package com.example.educonnectadmin.Assignment;

public class AssignmentData {
    String PdfTitle,PdfUrl,selectedClass,key;

    public AssignmentData(String pdfTitle, String pdfUrl, String selectedClass, String key) {
        PdfTitle = pdfTitle;
        PdfUrl = pdfUrl;
        this.selectedClass = selectedClass;
        this.key = key;
    }

    public String getPdfTitle() {
        return PdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        PdfTitle = pdfTitle;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }

    public String getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(String selectedClass) {
        this.selectedClass = selectedClass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AssignmentData() {
    }
}
