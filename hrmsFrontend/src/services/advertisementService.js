import axios from "axios";

export default class AdvertisementService{
    getAdvertisements(){
        return axios.get("http://localhost:8080/api/employer/getAllJobAdvertisement")
    }

    addAdvertisement(jobAdvertisement,employerId){
        return axios.post("http://localhost:8080/api/jobadvertisement/addAdvertisement?employerId="+employerId,jobAdvertisement)
    }

    jobAdvertisementOpen(jobAdvertisementId,isJobAdvertisementOpen){
        return axios.post("http://localhost:8080/api/jobadvertisement/jobAdvertisementOpen?isJobAdvertisementOpen=true&jobAdvertisementId="+jobAdvertisementId,isJobAdvertisementOpen)
    }

    getAllEmployersJobAdvertisements(employerId) {
        return axios.get("http://localhost:8080/api/employer/getAllEmployersJobAdvertisements?id=" + employerId)
    }

    getAllJobAdvertisementSorted(){
        return axios.get("http://localhost:8080/api/employer/getAllJobAdvertisementSorted")
    }
}