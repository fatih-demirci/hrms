import axios from "axios";
import { toast } from "react-toastify";
export default class AdvertisementService{
    getAdvertisements(isOpen,approved){
        return axios.get(`http://localhost:8080/api/employer/getAllJobAdvertisement?approved=${approved}&isOpen=${isOpen}`)
    }

    addAdvertisement(jobAdvertisement,employerId){
        return axios.post("http://localhost:8080/api/jobadvertisement/addAdvertisement?employerId="+employerId,jobAdvertisement).then(function (response) {
          
            console.log(response.data.message)
            toast.success(response.data.message)
         })
         .catch(function (error) {
         toast.success(error.response.data)
         return error.response.data;
         });
    }

    jobAdvertisementOpen(jobAdvertisementId,isJobAdvertisementOpen){
        return axios.post(`http://localhost:8080/api/jobadvertisement/jobAdvertisementOpen?isJobAdvertisementOpen=${isJobAdvertisementOpen}&jobAdvertisementId=${jobAdvertisementId}`)
    }

    jobAdvertisementApprove(jobAdvertisementId, isJobAdvertisementApprove){
        return axios.post(`http://localhost:8080/api/jobadvertisement/jobAdvertisementApprove?isJobAdvertisementApprove=${isJobAdvertisementApprove}&jobAdvertisementId=${jobAdvertisementId}`)
    }

    getAllEmployersJobAdvertisements(employerId) {
        return axios.get("http://localhost:8080/api/employer/getAllEmployersJobAdvertisements?id=" + employerId)
    }

    getAllJobAdvertisementSorted(){
        return axios.get("http://localhost:8080/api/employer/getAllJobAdvertisementSorted")
    }

    getJobAdvertisementById(id){
        return axios.get("http://localhost:8080/api/jobadvertisement/getJobAdvertisementById?id="+id)
    }
}