import axios from "axios";
import { toast } from "react-toastify";
export default class AdvertisementService {
    getAdvertisements(isOpen, approved, pageNo, pageSize) {
        return axios.get(`http://localhost:8080/api/employer/getAllJobAdvertisement?approved=${approved}&isOpen=${isOpen}&pageNo=${pageNo}&pageSize=${pageSize}`)

    }

    addAdvertisement(jobAdvertisement, employerId) {
        return axios.post("http://localhost:8080/api/jobadvertisement/addAdvertisement?employerId=" + employerId, jobAdvertisement).then(function (response) {

            console.log(response.data.message)
            toast.success(response.data.message)
        })
            .catch(function (error) {
                toast.success(error.response.data)
                return error.response.data;
            });
    }

    jobAdvertisementOpen(jobAdvertisementId, isJobAdvertisementOpen) {
        return axios.post(`http://localhost:8080/api/jobadvertisement/jobAdvertisementOpen?isJobAdvertisementOpen=${isJobAdvertisementOpen}&jobAdvertisementId=${jobAdvertisementId}`)
    }

    jobAdvertisementApprove(jobAdvertisementId, isJobAdvertisementApprove) {
        return axios.post(`http://localhost:8080/api/jobadvertisement/jobAdvertisementApprove?isJobAdvertisementApprove=${isJobAdvertisementApprove}&jobAdvertisementId=${jobAdvertisementId}`)
    }

    getAllJobAdvertisementSorted() {
        return axios.get("http://localhost:8080/api/employer/getAllJobAdvertisementSorted")
    }

    getJobAdvertisementById(id) {
        return axios.get("http://localhost:8080/api/jobadvertisement/getJobAdvertisementById?id=" + id)
    }

    countByIsJobAdvertisementOpenAndApproved(isOpen, approved) {
        return axios.get(`http://localhost:8080/api/jobadvertisement/countByIsJobAdvertisementOpenAndApproved?approved=${approved}&isOpen=${isOpen}`)
    }


    getEmployerWithAdvertisementDetailsByCityAndTypeOfWork(approved, city, isOpen, pageNo, pageSize, typeOfWork) {
       return axios.get(`http://localhost:8080/api/employer/getEmployerWithAdvertisementDetailsByCityAndTypeOfWork?approved=${approved}&city=${city}&isOpen=${isOpen}&pageNo=${pageNo}&pageSize=${pageSize}&typeOfWork=${typeOfWork}`)
    }

    countByIsOpenAndApprovedAndCityAndTypeOfWork(approved, city, isOpen, typeOfWork) {
        return axios.get(`http://localhost:8080/api/jobadvertisement/countByIsOpenAndApprovedAndCityAndTypeOfWork?approved=${approved}&city=${city}&isOpen=${isOpen}&typeOfWork=${typeOfWork}`)

    }
    jobAdvertisementUpdateApprove(jobAdvertisementId){
        return axios.post(`http://localhost:8080/api/jobadvertisement/jobAdvertisementUpdateApprove?jobAdvertisementId=${jobAdvertisementId}`).then(function (response) {

            console.log(response.data.message)
            toast.success(response.data.message)
        })
            .catch(function (error) {
                toast.success(error.response.data)
                return error.response.data;
            });
    }                    

}