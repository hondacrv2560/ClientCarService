package com.example.client.Models;

public class ServiceClient {
    public String idService;
    public String title_service;
<<<<<<< Updated upstream
    public String imagePath;
    public ServiceClient() {
    }

    public ServiceClient(String idService, String title_service, String imagePath) {
        this.idService = idService;
        this.title_service = title_service;
        this.imagePath = imagePath;
=======
    int imgId;

    public ServiceClient() {
    }

    public ServiceClient(String idService, String title_service, int imgId) {
        this.idService = idService;
        this.title_service = title_service;
        this.imgId = imgId;
>>>>>>> Stashed changes
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getTitle_service() {
        return title_service;
    }

    public void setTitle_service(String title_service) {
        this.title_service = title_service;
    }

<<<<<<< Updated upstream
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
=======
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
>>>>>>> Stashed changes
    }
}
